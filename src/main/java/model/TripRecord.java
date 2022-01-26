package model;

import java.util.Objects;

public class TripRecord {
    private final String pickupDateTime;
    private final GPSCoordinate pickupLocation;
    private final GPSCoordinate dropOffLocation;
    private final float tripDistance;

    public TripRecord(String[] data) {
        this.pickupDateTime = data[4];
        this.pickupLocation = new GPSCoordinate(data[8], data[9]);
        this.dropOffLocation = new GPSCoordinate(data[12], data[13]);
        this.tripDistance = Float.parseFloat(data[7]);
    }

    public TripRecord(String pickupDateTime, GPSCoordinate pickupLocation, GPSCoordinate dropOffLocation, float tripDistance) {
        this.pickupDateTime = pickupDateTime;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.tripDistance = tripDistance;
    }

    public GPSCoordinate getPickupLocation() {
        return pickupLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripRecord that = (TripRecord) o;
        return Float.compare(that.tripDistance, tripDistance) == 0 && Objects.equals(pickupDateTime, that.pickupDateTime) && Objects.equals(pickupLocation, that.pickupLocation) && Objects.equals(dropOffLocation, that.dropOffLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickupDateTime, pickupLocation, dropOffLocation, tripDistance);
    }
}
