package utils;

import model.TripRecord;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SCVParser {

    public List<TripRecord> parse(String fileName) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Can't read file");
            return new ArrayList<>();
        }
        List<TripRecord> records = new ArrayList<>();

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            records.add(new TripRecord(scanner.nextLine().split(",")));
        }
        scanner.close();

        return records;
    }
}
