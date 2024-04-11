package Views.Events;

import Models.BaseControllerStation;

public class NewBCSEvent {
    private final BaseControllerStation baseControllerStation;
    private final int LayerNumber;

    public NewBCSEvent(BaseControllerStation baseControllerStation, int layerNumber) {
        this.baseControllerStation = baseControllerStation;
        LayerNumber = layerNumber;
    }

    public BaseControllerStation getBaseControllerStation() {
        return baseControllerStation;
    }

    public int getLayerNumber() {
        return LayerNumber;
    }
}
