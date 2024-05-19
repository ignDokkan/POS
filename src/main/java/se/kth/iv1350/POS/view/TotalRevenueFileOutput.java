package se.kth.iv1350.POS.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private static final String FILE_PATH = "total_revenue.txt";

    @Override
    public void updateTotalRevenue(double newRevenue) {
        double totalRevenue = readTotalRevenueFromFile();
        totalRevenue += newRevenue;
        writeTotalRevenueToFile(totalRevenue);
    }

    private double readTotalRevenueFromFile() {
        double totalRevenue = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                totalRevenue = Double.parseDouble(line);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading total revenue from file: " + e.getMessage());
        }
        return totalRevenue;
    }

    private void writeTotalRevenueToFile(double totalRevenue) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(totalRevenue));
        } catch (IOException e) {
            System.err.println("Error writing total revenue to file: " + e.getMessage());
        }
    }
}
