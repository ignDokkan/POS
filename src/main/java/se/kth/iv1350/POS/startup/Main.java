package se.kth.iv1350.POS.startup;

import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.view.View;

/**
 * Starts the entire application, contains the main method used to start the application.
 */
public class Main {
    private Controller contr = new Controller();
    private View view = new View(contr);

    /**
     * The main method used to start the entire application.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.view.startSale();
        main.view.addItem("abc123", 2);
        main.view.addItem("def456", 1);
        main.view.endSale(100); 
    }
}
