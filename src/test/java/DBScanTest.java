import model.Cluster;
import model.GPSCoordinate;
import model.TripRecord;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBScanTest {

    @Test
    void test() {
        List<TripRecord> data = Arrays.asList(
                new TripRecord("test", new GPSCoordinate(3, 3), new GPSCoordinate(0, 0), 0),
                new TripRecord("test", new GPSCoordinate(4, 4), new GPSCoordinate(0, 0), 0),
                new TripRecord("test", new GPSCoordinate(7, 7), new GPSCoordinate(0, 0), 0),
                new TripRecord("test", new GPSCoordinate(8, 8), new GPSCoordinate(0, 0), 0),
                new TripRecord("test", new GPSCoordinate(2, 2), new GPSCoordinate(0, 0), 0),
                new TripRecord("test", new GPSCoordinate(9, 9), new GPSCoordinate(0, 0), 0),
                new TripRecord("test", new GPSCoordinate(15, 15), new GPSCoordinate(0, 0), 0),
                new TripRecord("test", new GPSCoordinate(12, 12), new GPSCoordinate(0, 0), 0),
                new TripRecord("test", new GPSCoordinate(25, 25), new GPSCoordinate(0, 0), 0),
                new TripRecord("test", new GPSCoordinate(100, 100), new GPSCoordinate(0, 0), 0));

        DBScan dbScan = new DBScan(3, 2, data);

        List<Cluster> result = dbScan.scan();

        List<Cluster> expected = Arrays.asList(
                new Cluster(Arrays.asList(data.get(0), data.get(1), data.get(4))),
                new Cluster(Arrays.asList(data.get(2), data.get(3), data.get(5))));

        assertEquals(expected, result);
    }
}
