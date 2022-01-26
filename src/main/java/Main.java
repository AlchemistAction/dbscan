import model.Cluster;
import model.TripRecord;
import utils.SCVParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SCVParser parser = new SCVParser();

        List<TripRecord> data = parser.parse("data.csv");

        DBScan dbScan = new DBScan(3, 0.005, data);

        List<Cluster> result = dbScan.scan();
        System.out.println(result);
    }
}
