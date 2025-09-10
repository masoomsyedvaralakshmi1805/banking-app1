package com.vmworld;

import com.vmworld.controller.BankingController;

/**
 * Main application class that launches the banking system.<br>
 * Entry point of the program.
 *
 * @author Varalakshmi
 * @author Masoom
 *
 * @version 1.0
 */
public class BankingApp {

    /**
     * Main method: starts the banking application.<br>
     * It creates an instance of the controller to begin the user interaction loop.
     * @param args command-line arguments (not used)
     */
    public static void main (String[] args) {
        new BankingController().startBankingApp();
    }

}
