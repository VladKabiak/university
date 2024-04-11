package Views;

import Controllers.SMSController;
import Models.BaseControllerStation;
import Models.BaseTransmissionStation;
import Views.Events.NewBCSLListener;
import Views.Events.NewBCSListener;
import Views.Events.NewBTSListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MiddlePanelView extends JPanel {

    private int layerNumber = 1;
    private final ArrayList<BaseControllerStationLayerView> BSCsLayersList;
    private final JPanel BSCsLayersPanel;
    private final BaseTransmissionStationLayerView leftBaseTransmissionStationLayerView;
    private final BaseTransmissionStationLayerView rightBaseTransmissionStationLayerView;
    private final JPanel devicesPanel;

    private NewBCSLListener newBCSLListener;


    public MiddlePanelView() {
        BSCsLayersPanel = new JPanel();
        devicesPanel = new JPanel();
        BSCsLayersList = new ArrayList<>();
        setLayout(new BorderLayout());
        devicesPanel.setLayout(new BoxLayout(devicesPanel, BoxLayout.X_AXIS));
        JScrollPane scrollPane = new JScrollPane(devicesPanel);
        add(scrollPane, BorderLayout.CENTER);

        leftBaseTransmissionStationLayerView = new BaseTransmissionStationLayerView(true);
        devicesPanel.add(leftBaseTransmissionStationLayerView, BorderLayout.EAST);
        for (BaseControllerStationLayerView BCSLW: BSCsLayersList) {
            BSCsLayersPanel.add(BCSLW);
        }
        devicesPanel.add(BSCsLayersPanel);
        rightBaseTransmissionStationLayerView = new BaseTransmissionStationLayerView(false);
        devicesPanel.add(rightBaseTransmissionStationLayerView, BorderLayout.WEST);

        scrollPane.setViewportView(devicesPanel);

        add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add BSC Layer");

        add(addButton, BorderLayout.PAGE_END);

        addButton.addActionListener(e -> {
            fireNewBCSLW();
        });
    }

    public void updateThisGUI() {
        BSCsLayersPanel.removeAll();

        for (BaseControllerStationLayerView BCSLW : BSCsLayersList) {
            BSCsLayersPanel.add(BCSLW);
        }

        revalidate();
        repaint();
    }

    private void fireNewBCSLW() {
        if (newBCSLListener != null) {
            newBCSLListener.CreateNewBSCL();
            updateThisGUI();
        }
    }

    public BaseTransmissionStationLayerView getLeftBaseTransmissionStationLayerView() {
        return leftBaseTransmissionStationLayerView;
    }

    public BaseTransmissionStationLayerView getRightBaseTransmissionStationLayerView() {
        return rightBaseTransmissionStationLayerView;
    }

    public void createNewBTS(BaseTransmissionStation BTS, SMSController controller, boolean isLeft){
        if (isLeft) {
            getLeftBaseTransmissionStationLayerView().createNewBTSW(BTS, controller);
        } else {
            getRightBaseTransmissionStationLayerView().createNewBTSW(BTS, controller);
        }
    }

    public void CreateNewBCSLW(SMSController controller){
        BaseControllerStationLayerView BCSLW = new BaseControllerStationLayerView(layerNumber);
        BCSLW.setAddNewBCSButtonListener(controller);
        BCSLW.setPreferredSize(new Dimension(390, 510));
        setLayerNumber(1);
        BSCsLayersList.add(BCSLW);
    }

    public void createNewBCSW(BaseControllerStation BCS, SMSController controller, int layerNumber) {
        for (BaseControllerStationLayerView BCSLW:
                BSCsLayersList) {
            if (BCSLW.getLayerNumber() == layerNumber) {
                BCSLW.CreateNewBCSW(BCS, controller);
                break;
            }
        }
    }

    public void setAddNewBTSButtonListener(NewBTSListener newBTSListener) {
        leftBaseTransmissionStationLayerView.setAddNewBTSButtonListener(newBTSListener);
        rightBaseTransmissionStationLayerView.setAddNewBTSButtonListener(newBTSListener);
    }

    public void setNewBCSLButtonListener(NewBCSLListener newBCSLListener){
        this.newBCSLListener = newBCSLListener;
    }

    public void RemoveDevice(String deviceNumber) {
        leftBaseTransmissionStationLayerView.RemoveDevice(deviceNumber);
        for (BaseControllerStationLayerView baseControllerStationLayerView : BSCsLayersList) {
            baseControllerStationLayerView.RemoveDevice(deviceNumber);
        }
        rightBaseTransmissionStationLayerView.RemoveDevice(deviceNumber);
    }

    public void updateGUI(){
        leftBaseTransmissionStationLayerView.updateGUI();
        rightBaseTransmissionStationLayerView.updateGUI();
    }

    public void setAddNewBCSButtonListener(NewBCSListener newBCSListener){
        for (BaseControllerStationLayerView BCSLW:
                BSCsLayersList) {
            BCSLW.setAddNewBCSButtonListener(newBCSListener);
        }
    }

    public void setLayerNumber(int i){
        this.layerNumber = layerNumber+i;
    }

    public int getMaxLayer() {
        return layerNumber;
    }
}
