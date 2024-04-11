package Views;

import Models.BaseControllerStation;
import Views.Events.TerminateButtonListener;
import Views.Events.TerminateButtonPressEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class BaseControllerStationView extends JPanel implements ActionListener, DeviceView {
    private final BaseControllerStation model;
    private TerminateButtonListener terminateButtonListener;

    public BaseControllerStationView(BaseControllerStation model) {
        this.model = model;
        JLabel stationNumberLabel = new JLabel("Station Number: " + model.getBaseControllerStationNumber());
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
    public BaseControllerStation getModel(){return model;}
}
