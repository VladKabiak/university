package Views.Events;

import Models.BaseTransmissionStation;

public class NewBTSEvent {
    private BaseTransmissionStation baseTransmissionStation;
    private boolean isLeft;

    public NewBTSEvent(BaseTransmissionStation baseTransmissionStation, boolean isLeft) {
        this.baseTransmissionStation = baseTransmissionStation;
        this.isLeft = isLeft;
    }

    public BaseTransmissionStation getBaseTransmissionStation() {
        return baseTransmissionStation;
    }

    public boolean isLeft() {
        return isLeft;
    }
}
