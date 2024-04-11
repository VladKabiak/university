package Views;

import Controllers.SMSController;
import Models.BaseControllerStation;
import Views.Events.NewBCSEvent;
import Views.Events.NewBCSListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class BaseControllerStationLayerView extends JPanel implements LayerDeviceView {
    private final ArrayList<BaseControllerStationView> BCSWList = new ArrayList<>();
    private final JPanel devicesPanel;
    private NewBCSListener newBCSListener;
    private final int layerNumber;

    public BaseControllerStationLayerView(int layerNumber) {
        this.layerNumber = layerNumber;
        devicesPanel = new JPanel();
        setLayout(new BorderLayout());
        devicesPanel.setLayout(new BoxLayout(devicesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(devicesPanel);
        add(scrollPane, BorderLayout.CENTER);

        for (BaseControllerStationView BCSW:
                BCSWList) {
            devicesPanel.add(BCSW, BorderLayout.CENTER);
        }
        scrollPane.setViewportView(devicesPanel);


        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            UUID uniqueId = UUID.randomUUID();
            BaseControllerStation bcs = new BaseControllerStation(uniqueId.toString(), layerNumber);
            fireNewBCSW(bcs);
            Thread t = new Thread(bcs);
            t.start();
        });
        add(addButton, BorderLayout.SOUTH);
    }

    public void fireNewBCSW(BaseControllerStation bcs) {
        if (newBCSListener != null) {
            newBCSListener.CreateNewBCS(new NewBCSEvent(bcs, layerNumber));
            updateGUI();
        }
    }

    public void setAddNewBCSButtonListener(NewBCSListener newBCSListener){
        this.newBCSListener = newBCSListener;
    }

    public void updateGUI() {
        devicesPanel.removeAll();

        for (BaseControllerStationView BCSW : BCSWList) {
            devicesPanel.add(BCSW);
        }

        revalidate();
        repaint();
    }

    public void CreateNewBCSW(BaseControllerStation BCS, SMSController controller){
        BaseControllerStationView BCSW = new BaseControllerStationView(BCS);
        BCSW.setTerminateButtonListener(controller);
        BCSW.getModel().SetNewMessageToNextLayer(controller);
        BCSWList.add(BCSW);
    }

    public void RemoveDevice(String deviceNumber){
        for (int i = 0; i < BCSWList.size(); i++) {
            if (BCSWList.get(i).getDeviceNumber().equals(deviceNumber)){
                BCSWList.remove(i);
                break;
            }
        }
    }

    public int getLayerNumber() {
        return layerNumber;
    }
}
