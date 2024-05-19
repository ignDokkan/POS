package se.kth.iv1350.POS.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TotalRevenueView implements TotalRevenueObserver {
    private static final String FILE_PATH = "total_revenue.txt";

    @Override
    public void updateTotalRevenue(double newRevenue) {
        double totalRevenue = readTotalRevenueFromFile();
        totalRevenue += newRevenue;
        System.out.println("Total revenue: " + totalRevenue + " SEK");
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
}
