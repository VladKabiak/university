package Models;

import Models.Events.MessageToNextLayer;

import java.util.ArrayList;
import java.util.Random;

public class BaseControllerStation implements Runnable, Device{
    private MessageToNextLayer listener;
    private final String stationNumber;
    private boolean State = true;
    private final Random random = new Random();
    private final int layerNumber;
    private ArrayList<Message> messages = new ArrayList<>();

    public BaseControllerStation(String stationNumber, int layerNumber) {
        this.stationNumber = stationNumber;
        this.layerNumber = layerNumber;
    }

    public String getBaseControllerStationNumber() {
        return stationNumber;
    }

    @Override
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
            if (!messages.isEmpty()) {
                try {
                    int randomDelay = random.nextInt(10001) + 5000;
                    Thread.sleep(randomDelay);
                    FireMessageToNextLayer(messages.get(0));
                    messages.remove(0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void FireMessageToNextLayer(Message message) {
        if (listener != null) {
            listener.SendMessageToNextBCS(message, layerNumber);
        }
    }

    public void SetNewMessageToNextLayer(MessageToNextLayer listener){
        this.listener = listener;
    }

    public int getLayerNumber() {
        return layerNumber;
    }

    public int GetNumOfMessages(){
        return messages.size();
    }

    public void AddMessageToQueue(Message message){
        messages.add(message);
    }
}
