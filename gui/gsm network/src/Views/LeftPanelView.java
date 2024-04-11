package Views;

import Controllers.SMSController;
import Models.VirtualSendingDevice;
import Views.Events.NewVBDListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class LeftPanelView extends JPanel{
    private final ArrayList<VirtualSendingDeviceView> devicesList = new ArrayList<>();
    private final JPanel devicesPanel;
    private NewVBDListener newVBDListener;

    public LeftPanelView() {
        devicesPanel = new JPanel();
        setLayout(new BorderLayout());
        devicesPanel.setLayout(new BoxLayout(devicesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(devicesPanel);
        add(scrollPane, BorderLayout.CENTER);

        for (VirtualSendingDeviceView VBDW:
             devicesList) {
            devicesPanel.add(VBDW, BorderLayout.CENTER);
        }
        scrollPane.setViewportView(devicesPanel);


        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String message = showAddDialog();
            UUID uniqueId = UUID.randomUUID();
            VirtualSendingDevice vbd = new VirtualSendingDevice(uniqueId.toString(), message);
            fireNewVBDW(vbd);
            Thread t = new Thread(vbd);
            t.start();
        });
        add(addButton, BorderLayout.SOUTH);
    }

    public void fireNewVBDW(VirtualSendingDevice vbd) {
        if (newVBDListener != null) {
            newVBDListener.createNewVBD(vbd);
            updateGUI();
        }
    }

    public void setAddNewVbdButtonListener(NewVBDListener newVBDListener){
        this.newVBDListener = newVBDListener;
    }

    private String showAddDialog() {
        String message = JOptionPane.showInputDialog(this, "Enter a short text message:");
        if (message != null && !message.isEmpty()) {
            return message;
        }
        return showAddDialog();
    }

    public void createNewVBDW(VirtualSendingDevice VBD, SMSController controller){
        VirtualSendingDeviceView VBDW = new VirtualSendingDeviceView(VBD);
        VBDW.setCheckBoxStateListener(controller);
        VBDW.setFrequencyListener(controller);
        VBDW.setTerminateButtonListener(controller);
        VBDW.getModel().SetNewMessageToBTS(controller);

        devicesList.add(VBDW);
    }

    public void updateGUI() {
        devicesPanel.removeAll();

        for (VirtualSendingDeviceView VBDW : devicesList) {
            devicesPanel.add(VBDW);
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
