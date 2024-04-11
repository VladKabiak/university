package Models;

import Models.Events.NewMessageToBTS;

public class VirtualSendingDevice implements Runnable, Device{
    private final String deviceNumber;
    private boolean isActive = true;
    private int frequency = 2;
    private final String text;
    private boolean State = true;
    private NewMessageToBTS listener;


    public VirtualSendingDevice(String deviceNumber, String text) {
        this.deviceNumber = deviceNumber;
        this.text = text;
    }

    @Override
    public void run() {
        while (State) {
            if (getFrequency() != 0) {
                try {
                    int delay = 1000/getFrequency();
                    Thread.sleep(delay);
                    FireNewMessageToBTS(text);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void FireNewMessageToBTS(String text){
        if (listener != null) {
            listener.SentToBTS(text);
        }
    }

    public void SetNewMessageToBTS(NewMessageToBTS listener){
        this.listener = listener;
    }

    @Override
    public String getDeviceNumber() {
        return deviceNumber;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void SetState(boolean state) {
        this.State = false;
    }
}
