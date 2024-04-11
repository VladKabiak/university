package Views;
import Models.VirtualReceivingDevice;
import Models.VirtualSendingDevice;
import Views.Events.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VirtualSendingDeviceView extends JPanel implements ActionListener, DeviceView {
    private final VirtualSendingDevice model;
    private CheckBoxStateListener checkBoxStateListener;
    private FrequencyListener frequencyListener;
    private TerminateButtonListener terminateButtonListener;

    public VirtualSendingDeviceView(VirtualSendingDevice model) {
        this.model = model;

        JSlider frequencySlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 2);
        frequencySlider.setPaintTicks(true);
        frequencySlider.setMajorTickSpacing(2);
        frequencySlider.setMinorTickSpacing(1);
        frequencySlider.addChangeListener(e -> fireFrequencyChangePerformedEvent(new FrequencyChangeEvent(frequencySlider.getValue(), model)));

        JButton terminateButton = new JButton("Terminate");
        terminateButton.addActionListener(this);

        JCheckBox stateCheckBox = new JCheckBox("Active", true);
        stateCheckBox.addItemListener(e -> fireCheckBoxStatePerformedEvent(new CheckBoxStateChangeEvent(stateCheckBox.isSelected(), model)));

        add(frequencySlider);
        add(terminateButton);
        add(stateCheckBox);
    }

    public void setCheckBoxStateListener(CheckBoxStateListener checkBoxStateListener) {
        this.checkBoxStateListener = checkBoxStateListener;
    }

    public void setFrequencyListener(FrequencyListener frequencyListener){
        this.frequencyListener = frequencyListener;
    }

    public void setTerminateButtonListener(TerminateButtonListener terminateButtonListener){
        this.terminateButtonListener = terminateButtonListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireTerminateButtonPressEvent(new TerminateButtonPressEvent(model));
    }

    public void fireFrequencyChangePerformedEvent(FrequencyChangeEvent e) {
        if (frequencyListener != null) {
            frequencyListener.FrequencyChangePerformed(e);
        }
    }

    public void fireTerminateButtonPressEvent(TerminateButtonPressEvent e) {
        if (terminateButtonListener != null) {
            terminateButtonListener.TerminateButtonPressed(e);
        }
    }

    public void fireCheckBoxStatePerformedEvent(CheckBoxStateChangeEvent e) {
        if (checkBoxStateListener != null) {
            checkBoxStateListener.StateChangePerformed(e);
        }
    }

    public String getDeviceNumber(){
        return model.getDeviceNumber();
    }

    public VirtualSendingDevice getModel(){return model;}
}