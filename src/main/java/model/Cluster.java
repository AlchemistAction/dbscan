package model;

import java.util.List;
import java.util.Objects;

public class Cluster {
    private final GPSCoordinate averageCoordinate;
    private final List<TripRecord> points;

    public Cluster(List<TripRecord> points) {
        this.averageCoordinate = generateAverageCoordinate(points);
        this.points = points;
    }

    public GPSCoordinate generateAverageCoordinate(List<TripRecord> points) {
        double longitude = points.stream()
                .mapToDouble(tripRecord -> (tripRecord.getPickupLocation().getLongitude()))
                .reduce(0, Double::sum) / points.size();

        double latitude = points.stream()
                .mapToDouble(tripRecord -> (tripRecord.getPickupLocation().getLatitude()))
                .reduce(0, Double::sum) / points.size();

        return new GPSCoordinate(longitude, latitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cluster cluster = (Cluster) o;
        return Objects.equals(averageCoordinate, cluster.averageCoordinate) && Objects.equals(points, cluster.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageCoordinate, points);
    }
}
