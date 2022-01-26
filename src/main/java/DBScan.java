import model.Cluster;
import model.GPSCoordinate;
import model.TripRecord;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DBScan {
    private final double minPts;
    private final double eps;
    private final HashSet<TripRecord> visitedPoints = new HashSet<>();
    private final List<TripRecord> data;
    private final List<Cluster> clusters;

    public DBScan(double minPts, double eps, List<TripRecord> data) {
        this.minPts = minPts;
        this.eps = eps;
        this.data = data;
        this.clusters = new ArrayList<>();
    }

    public List<Cluster> scan() {
        List<TripRecord> neighbours;
        int count = 0;

        while (data.size() > count) {
            TripRecord point = data.get(count);
            if (!visitedPoints.contains(point)) {
                visitedPoints.add(point);
                neighbours = getNeighbours(point);

                if (neighbours.size() >= minPts) {
                    generateCluster(neighbours);
                }
            }
            count++;
        }
        return clusters;
    }

    private void generateCluster(List<TripRecord> neighbours) {
        int innerCount = 0;
        while (neighbours.size() > innerCount) {
            TripRecord innerPoint = neighbours.get(innerCount);
            if (!visitedPoints.contains(innerPoint)) {
                visitedPoints.add(innerPoint);
                List<TripRecord> innerNeighbours = getNeighbours(innerPoint);
                if (innerNeighbours.size() >= minPts) {
                    mergeRightToLeftCollection(neighbours, innerNeighbours);
                }
            }
            innerCount++;
        }
        Cluster cluster = new Cluster(neighbours);
        clusters.add(cluster);
    }

    private List<TripRecord> getNeighbours(TripRecord point) {
        return data.parallelStream()
                .filter(candidate -> calculateDistance(point.getPickupLocation(), candidate.getPickupLocation()) <= eps)
                .collect(Collectors.toList());
    }

    private void mergeRightToLeftCollection(List<TripRecord> neighbours, List<TripRecord> individualNeighbours) {
        individualNeighbours.parallelStream()
                .filter(tempPoint -> !neighbours.contains(tempPoint))
                .forEach(neighbours::add);
    }

    private double calculateDistance(GPSCoordinate startPoint, GPSCoordinate endPoint) {
        return Math.sqrt((endPoint.getLatitude() - startPoint.getLatitude()) * (endPoint.getLatitude() - startPoint.getLatitude())
                + (endPoint.getLongitude() - startPoint.getLongitude()) * (endPoint.getLongitude() - startPoint.getLongitude()));
    }
}
