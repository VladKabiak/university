package Views.Events;

import Models.VirtualSendingDevice;

public class FrequencyChangeEvent {
    private int frequency;
    private final VirtualSendingDevice VBD;

    public FrequencyChangeEvent(int frequency, VirtualSendingDevice VBD) {
        this.frequency = frequency;
        this.VBD = VBD;
    }

    public VirtualSendingDevice getVBD() {
        return VBD;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
