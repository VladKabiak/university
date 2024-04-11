package Views;

import Models.BaseTransmissionStation;
import Views.Events.TerminateButtonListener;
import Views.Events.TerminateButtonPressEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseTransmissionStationView extends JPanel implements ActionListener, DeviceView{
    private final BaseTransmissionStation model;
    private TerminateButtonListener terminateButtonListener;

    public BaseTransmissionStationView(BaseTransmissionStation model) {
        this.model = model;
        JLabel stationNumberLabel = new JLabel("Station Number: " + model.getBaseTransmissionStationNumber());
        JButton terminateButton = new JButton("Terminate");
        terminateButton.addActionListener(this);
        add(stationNumberLabel);
        add(terminateButton);
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

    public void setTerminateButtonListener(TerminateButtonListener terminateButtonListener){
        this.terminateButtonListener = terminateButtonListener;
    }

    public String getDeviceNumber(){
        return model.getDeviceNumber();
    }

    public BaseTransmissionStation getModel() {
        return model;
    }
}
