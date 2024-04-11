package Models;

public class VirtualReceivingDevice implements Runnable, Device{
    private final String deviceNumber;
    private int receivedMessages;
    private boolean State = true;

    public VirtualReceivingDevice(String deviceNumber) {
        this.deviceNumber = deviceNumber;
        this.receivedMessages = 0;
    }

    @Override
    public String getDeviceNumber() {
        return deviceNumber;
    }

    public int getReceivedMessages() {
        return receivedMessages;
    }

    public void MessageReceived() {
        this.receivedMessages = receivedMessages+1;
    }

    @Override
    public void SetState(boolean state) {
        this.State = state;
    }

    @Override
    public void run() {
        while (State && getReceivedMessages() == 0 || State && getReceivedMessages() != 0) {
            try {
                Thread.sleep(10000);
                System.out.println(getDeviceNumber() + " received: " + getReceivedMessages() + " SMS");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
