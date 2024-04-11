package Views.Events;

import Models.VirtualSendingDevice;

public class CheckBoxStateChangeEvent {
    private boolean state;
    private final VirtualSendingDevice VBD;

    public CheckBoxStateChangeEvent(boolean state, VirtualSendingDevice VBD) {
        this.state = state;
        this.VBD = VBD;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public VirtualSendingDevice getVBD() {
        return VBD;
    }
}
