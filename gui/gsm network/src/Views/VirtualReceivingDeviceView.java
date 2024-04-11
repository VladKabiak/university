package Views;

import Models.VirtualReceivingDevice;
import Views.Events.TerminateButtonListener;
import Views.Events.TerminateButtonPressEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VirtualReceivingDeviceView extends JPanel implements ActionListener, DeviceView {
    private final VirtualReceivingDevice model;
    private TerminateButtonListener terminateButtonListener;

    public VirtualReceivingDeviceView(VirtualReceivingDevice model) {
        this.model = model;
        // Create and configure the visual components
        JLabel deviceNumberLabel = new JLabel("Device Number: " + model.getDeviceNumber());

        JButton terminateButton = new JButton("Terminate");
        terminateButton.addActionListener(this);

        // Add the visual components to the sendingDevicePanel
        add(deviceNumberLabel);
        add(terminateButton);
    }

    public void setTerminateButtonListener(TerminateButtonListener terminateButtonListener){
        this.terminateButtonListener = terminateButtonListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireTerminateButtonPressEvent(new TerminateButtonPressEvent(model));
    }

    public void fireTerminateButtonPressEvent(TerminateButtonPressEvent e) {
        if (terminateButtonListener != null) {
            terminateButtonListener.TerminateButtonPressed(e);
        }
    }

    public String getDeviceNumber(){
        return model.getDeviceNumber();
    }
}
