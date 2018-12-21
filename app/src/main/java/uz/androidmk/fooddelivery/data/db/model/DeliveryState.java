package uz.androidmk.fooddelivery.data.db.model;

/**
 * Created by Azamat on 9/17/2018.
 */

public class DeliveryState {

    boolean delivered;
    boolean dispatching;
    boolean placed;
    boolean preparing;

    public DeliveryState() {
    }

    public DeliveryState(boolean delivered, boolean dispatching, boolean placed, boolean preparing) {
        this.delivered = delivered;
        this.dispatching = dispatching;
        this.placed = placed;
        this.preparing = preparing;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isDispatching() {
        return dispatching;
    }

    public void setDispatching(boolean dispatching) {
        this.dispatching = dispatching;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public boolean isPreparing() {
        return preparing;
    }

    public void setPreparing(boolean preparing) {
        this.preparing = preparing;
    }


}
