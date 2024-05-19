package se.kth.iv1350.POS.startup;

import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.view.*;
import se.kth.iv1350.POS.exceptions.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Starts the entire application, contains the main method used to start the application.
 */
public class Main {
    private final Controller contr = new Controller();
    private final View view = new View(contr);

    /**
     * The main method used to start the entire application.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        Main main = new Main();
        TotalRevenueView totalRevenueView = new TotalRevenueView();
        TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput();
        int updateDatabase = 1;

        main.contr.addObserver(totalRevenueView);
        main.contr.addObserver(totalRevenueFileOutput);

        main.view.startSale();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("exceptions.txt", true))) {
            try {
                main.view.addItem("abc123", 2); // Valid item ID
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
                writer.write(e.getMessage());
                writer.newLine();
            } catch (FailedDatabaseException e) {
                System.out.println(e.getMessage());
                writer.write(e.getMessage());
                writer.newLine();
                updateDatabase--;
            }
            try {
                main.view.addItem("def456", 1); // Valid item ID
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
                writer.write(e.getMessage());
                writer.newLine();
            } catch (FailedDatabaseException e) {
                System.out.println(e.getMessage());
                writer.write(e.getMessage());
                writer.newLine();
                updateDatabase--;
            }
            try {
                main.view.addItem("def46", 1); // Invalid item ID
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
                writer.write(e.getMessage());
                writer.newLine();
            } catch (FailedDatabaseException e) {
                System.out.println(e.getMessage());
                writer.write(e.getMessage());
                writer.newLine();
                updateDatabase--;
            }
            try {
                main.view.addItem("databaseErrorID", 1); // Throws FailedDatabaseException
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
                writer.write(e.getMessage());
                writer.newLine();
            } catch (FailedDatabaseException e) {
                System.out.println(e.getMessage());
                writer.write(e.getMessage());
                writer.newLine();
                updateDatabase--;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the log file.");
        }

        main.view.endSale(100, updateDatabase);
    }
}
