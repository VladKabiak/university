package Models.Events;

import Models.Message;

public interface MessageToNextLayer {
    void SendMessageToNextBCS (Message message, int layer);
}
