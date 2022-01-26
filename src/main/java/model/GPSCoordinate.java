package model;

import java.util.Objects;

public class GPSCoordinate {
    private final double longitude;
    private final double latitude;

    public GPSCoordinate(String longitude, String latitude) {
        this.longitude = Double.parseDouble(longitude);
        this.latitude = Double.parseDouble(latitude);
    }

    public GPSCoordinate(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPSCoordinate GPSCoordinate = (GPSCoordinate) o;
        return Double.compare(GPSCoordinate.longitude, longitude) == 0 && Double.compare(GPSCoordinate.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }
}
