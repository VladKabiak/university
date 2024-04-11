package Controllers;

import Models.*;
import Models.Events.MessageToBSC;
import Models.Events.MessageToNextLayer;
import Models.Events.NewMessageToBTS;
import Views.*;
import Views.Events.*;

import java.util.ArrayList;
import java.util.Random;

public class SMSController implements CheckBoxStateListener, FrequencyListener, TerminateButtonListener, NewVBDListener,
        NewVRDListener, NewBTSListener, NewBCSListener, NewBCSLListener, NewMessageToBTS, MessageToBSC, MessageToNextLayer {
    private final ArrayList<VirtualSendingDevice> sendingDevices;
    private final ArrayList<VirtualReceivingDevice> receivingDevices;
    private final ArrayList<BaseTransmissionStation> baseTransmissionStations;
    private final ArrayList<BaseControllerStation> baseControllerStations;
    Random random = new Random();

    private final LeftPanelView lp;
    private final RightPanelView rp;
    private final MiddlePanelView mp;

    public SMSController(LeftPanelView lp, MiddlePanelView mp, RightPanelView rp) {
        this.lp = lp;
        this.rp = rp;
        this.mp = mp;
        sendingDevices = new ArrayList<>();
        receivingDevices = new ArrayList<>();
        baseTransmissionStations = new ArrayList<>();
        baseControllerStations = new ArrayList<>();
    }

    public void terminateDevice(Device device) {
        device.SetState(false);
        for (VirtualSendingDevice sendingDevice : sendingDevices) {
            if (sendingDevice.getDeviceNumber().equals(device.getDeviceNumber())) {
                lp.RemoveDevice(device.getDeviceNumber());
                lp.updateGUI();
                break;
            }
        }
        for (VirtualReceivingDevice receivingDevice: receivingDevices) {
            if (receivingDevice.getDeviceNumber().equals(device.getDeviceNumber())){
                rp.RemoveDevice(device.getDeviceNumber());
                rp.updateGUI();
                break;
            }
        }
        for (BaseTransmissionStation baseTransmissionStation: baseTransmissionStations) {
            if (baseTransmissionStation.getDeviceNumber().equals(device.getDeviceNumber())){
                mp.RemoveDevice(device.getDeviceNumber());
                mp.updateGUI();
                break;
            }
        }
        for (BaseControllerStation baseControllerStation: baseControllerStations) {
            if (baseControllerStation.getDeviceNumber().equals(device.getDeviceNumber())){
                mp.RemoveDevice(device.getDeviceNumber());
                mp.updateThisGUI();
                break;
            }
        }
    }
    public void suspendThread(VirtualSendingDevice vbd) {
        vbd.setFrequency(0);
    }
    public void resumeThread(VirtualSendingDevice vbd) {
        vbd.setFrequency(2);
    }
    public void changeFrequency(VirtualSendingDevice vbd, int frequency) {
        vbd.setFrequency(frequency);
    }

    public VirtualSendingDevice findVBDByNumber(ArrayList<VirtualSendingDevice> deviceList, String deviceNumber) {
        for (VirtualSendingDevice VBD : deviceList) {
            if (VBD.getDeviceNumber().equals(deviceNumber)) {
                return VBD;
            }
        }
        return null;
    }

    public VirtualReceivingDevice findVRDByNumber(ArrayList<VirtualReceivingDevice> deviceList, String deviceNumber) {
        for (VirtualReceivingDevice VRD : deviceList) {
            if (VRD.getDeviceNumber().equals(deviceNumber)) {
                return VRD;
            }
        }
        return null;
    }

    @Override
    public void StateChangePerformed(CheckBoxStateChangeEvent e) {
        VirtualSendingDevice vbd = findVBDByNumber(sendingDevices, e.getVBD().getDeviceNumber());
        vbd.setActive(!vbd.getIsActive());
        if (vbd.getIsActive()) {
            resumeThread(vbd);
        } else {
            suspendThread(vbd);
        }
    }

    @Override
    public void FrequencyChangePerformed(FrequencyChangeEvent e) {
        VirtualSendingDevice vbd = findVBDByNumber(sendingDevices, e.getVBD().getDeviceNumber());
        changeFrequency(vbd, e.getFrequency());
    }

    @Override
    public void TerminateButtonPressed(TerminateButtonPressEvent e) {
        terminateDevice(e.getDevice());
    }

    @Override
    public void createNewVBD(VirtualSendingDevice VBD) {
        sendingDevices.add(VBD);
        lp.createNewVBDW(VBD, this);
    }

    @Override
    public void createNewVRD(VirtualReceivingDevice VRD) {
        receivingDevices.add(VRD);
        rp.createNewVRDW(VRD, this);
    }

    @Override
    public void createNewBTS(NewBTSEvent e) {
        baseTransmissionStations.add(e.getBaseTransmissionStation());
        mp.createNewBTS(e.getBaseTransmissionStation(), this, e.isLeft());
    }

    @Override
    public void CreateNewBCS(NewBCSEvent e) {
        baseControllerStations.add(e.getBaseControllerStation());
        mp.createNewBCSW(e.getBaseControllerStation(), this, e.getLayerNumber());
    }

    @Override
    public void CreateNewBSCL() {
        mp.CreateNewBCSLW(this);
    }

    @Override
    public void SentToBTS(String text) {
        getUnloadedBTS(baseTransmissionStations).AddMessageToQueue(new Message(text, getRandomReceiver(receivingDevices).getDeviceNumber()));
    }

    public BaseTransmissionStation getUnloadedBTS(ArrayList<BaseTransmissionStation> baseTransmissionStations) {
        BaseTransmissionStation unloadedBts = baseTransmissionStations.get(0);
        for (BaseTransmissionStation BTS:baseTransmissionStations) {
            if (BTS.GetNumOfMessages() < unloadedBts.GetNumOfMessages()) unloadedBts=BTS;
        }
        return unloadedBts;
    }

    public VirtualReceivingDevice getRandomReceiver(ArrayList<VirtualReceivingDevice> virtualReceivingDevices) {
        return virtualReceivingDevices.get(random.nextInt(virtualReceivingDevices.size()));
    }

    @Override
    public void SentMessageToBSC(Message message, int layer) {
        getUnloadedBCS(baseControllerStations, layer).AddMessageToQueue(message);
    }

    public BaseControllerStation getUnloadedBCS(ArrayList<BaseControllerStation> baseControllerStations, int layer) {
        BaseControllerStation unloadedBcs = baseControllerStations.get(0);
        for (BaseControllerStation BCS: baseControllerStations) {
            if (BCS.getLayerNumber() == layer){
                if (BCS.GetNumOfMessages() < unloadedBcs.GetNumOfMessages()) unloadedBcs=BCS;
            }
        }
        return unloadedBcs;
    }

    @Override
    public void SendMessageToNextBCS(Message message, int layer) {
        if (mp.getMaxLayer()-1 == layer){
            findReceiver(message);
        } else {
            SentMessageToBSC(message, layer+1);
        }
    }

    private void findReceiver(Message message) {
        VirtualReceivingDevice receiver = findVRDByNumber(receivingDevices, message.getReceiver());
        receiver.MessageReceived();
    }


}
