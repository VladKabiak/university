package Views;

import Controllers.SMSController;
import Models.BaseTransmissionStation;
import Models.Message;
import Views.Events.NewBTSEvent;
import Views.Events.NewBTSListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class BaseTransmissionStationLayerView extends JPanel implements LayerDeviceView{
    private final ArrayList<BaseTransmissionStationView> BTSWList = new ArrayList<>();
    private final JPanel devicesPanel;
    private NewBTSListener newBTSListener;
    private boolean isLeft;

    public BaseTransmissionStationLayerView(boolean isLeft) {
        this.isLeft = isLeft;
        devicesPanel = new JPanel();
        setLayout(new BorderLayout());
        devicesPanel.setLayout(new BoxLayout(devicesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(devicesPanel);
        add(scrollPane, BorderLayout.CENTER);

        for (BaseTransmissionStationView BTSW:
                BTSWList) {
            devicesPanel.add(BTSW, BorderLayout.CENTER);
        }
        scrollPane.setViewportView(devicesPanel);


        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            UUID uniqueId = UUID.randomUUID();
            BaseTransmissionStation bts = new BaseTransmissionStation(uniqueId.toString());
            fireNewBTSW(bts);
            Thread t = new Thread(bts);
            t.start();
        });
        add(addButton, BorderLayout.SOUTH);
    }

    public void fireNewBTSW(BaseTransmissionStation bts) {
        if (newBTSListener != null) {
            newBTSListener.createNewBTS(new NewBTSEvent(bts, isLeft));
            updateGUI();
        }
    }

    public void setAddNewBTSButtonListener(NewBTSListener newBTSListener){
        this.newBTSListener = newBTSListener;
    }

    public void updateGUI() {
        devicesPanel.removeAll();

        for (BaseTransmissionStationView BTSW : BTSWList) {
            devicesPanel.add(BTSW);
        }

        revalidate();
        repaint();
    }

    public void RemoveDevice(String deviceNumber){
        for (int i = 0; i < BTSWList.size(); i++) {
            if (BTSWList.get(i).getDeviceNumber().equals(deviceNumber)){
                BTSWList.remove(i);
                break;
            }
        }
    }

    public void createNewBTSW(BaseTransmissionStation BTS, SMSController controller){
        BaseTransmissionStationView BTSW = new BaseTransmissionStationView(BTS);
        BTSW.setTerminateButtonListener(controller);
        BTSW.getModel().SetNewMessageToBCS(controller);
        BTSWList.add(BTSW);
    }
}
