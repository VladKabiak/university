package Views.Events;

import Models.Device;
import Models.VirtualSendingDevice;

public class TerminateButtonPressEvent {
    private Device device;

    public TerminateButtonPressEvent(Device device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }
}
