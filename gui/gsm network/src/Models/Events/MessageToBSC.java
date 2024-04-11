package Models.Events;

import Models.Message;

public interface MessageToBSC {
    void SentMessageToBSC(Message message, int layer);
}
