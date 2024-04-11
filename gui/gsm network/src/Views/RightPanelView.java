package Views;

import Controllers.SMSController;
import Models.VirtualReceivingDevice;
import Views.Events.NewVRDListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class RightPanelView extends JPanel{
    private final ArrayList<VirtualReceivingDeviceView> devicesList = new ArrayList<>();
    private final JPanel devicesPanel;
    private NewVRDListener newVRDListener;

    public RightPanelView() {
        devicesPanel = new JPanel();
        setLayout(new BorderLayout());
        devicesPanel.setLayout(new BoxLayout(devicesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(devicesPanel);
        add(scrollPane, BorderLayout.CENTER);

        for (VirtualReceivingDeviceView VRDW:
                devicesList) {
            devicesPanel.add(VRDW, BorderLayout.CENTER);
        }
        scrollPane.setViewportView(devicesPanel);


        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            UUID uniqueId = UUID.randomUUID();
            VirtualReceivingDevice VRD = new VirtualReceivingDevice(uniqueId.toString());
            fireNewVRDW(VRD);
            Thread t = new Thread(VRD);
            t.start();
        });
        add(addButton, BorderLayout.SOUTH);
    }

    public void fireNewVRDW(VirtualReceivingDevice vrd) {
        if (newVRDListener != null) {
            newVRDListener.createNewVRD(vrd);
            updateGUI();
        }
    }

    public void setAddNewVrdButtonListener(NewVRDListener newVRDListener){
        this.newVRDListener = newVRDListener;
    }

    public void createNewVRDW(VirtualReceivingDevice VRD, SMSController controller){
        VirtualReceivingDeviceView VRDW = new VirtualReceivingDeviceView(VRD);
        VRDW.setTerminateButtonListener(controller);

        devicesList.add(VRDW);
    }

    public void updateGUI() {
        devicesPanel.removeAll();

        for (VirtualReceivingDeviceView VRDW : devicesList) {
            devicesPanel.add(VRDW);
        }

        revalidate();
        repaint();
    }

    public void RemoveDevice(String deviceNumber){
        for (int i = 0; i < devicesList.size(); i++) {
            if (devicesList.get(i).getDeviceNumber().equals(deviceNumber)){
                devicesList.remove(i);
                break;
            }
        }
    }
}