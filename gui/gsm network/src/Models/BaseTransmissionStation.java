package Models;

import Models.Events.MessageToBSC;

import java.util.ArrayList;

public class BaseTransmissionStation implements Runnable, Device{
    private MessageToBSC listener;
    private final String stationNumber;
    private boolean State = true;
    private ArrayList<Message> messages = new ArrayList<>();

    public BaseTransmissionStation(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getBaseTransmissionStationNumber() {
        return stationNumber;
    }

    public void SetState(boolean state) {
        this.State = false;
    }

    @Override
    public String getDeviceNumber() {
        return stationNumber;
    }

    @Override
    public void run() {
        while (State && messages.isEmpty() || State && !messages.isEmpty()) {
            System.out.print("");
            if (!messages.isEmpty()){
                try {
                    Thread.sleep(3000);
                    FireMessageToBSC(messages.get(0));
                    messages.remove(0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void FireMessageToBSC(Message message) {
        if (listener != null) {
            listener.SentMessageToBSC(message, 1);
        }
    }

    public void SetNewMessageToBCS(MessageToBSC listener){
        this.listener = listener;
    }

    public int GetNumOfMessages(){
        return messages.size();
    }

    public void AddMessageToQueue(Message message){
        messages.add(message);
    }
}
