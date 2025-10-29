package com.example.main;

import java.util.Scanner;
import java.util.logging.Logger;

import com.example.handlers.CsvFileHandler;
//import com.example.handlers.SFTPHandler;
import com.example.interfaces.CrudOperations;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        CrudOperations handler = new CsvFileHandler();

        while (true) {
            System.out.println("\nCSV File CRUD Menu");
            System.out.println("1. Create CSV Record");
            System.out.println("2. Read CSV Records");
            System.out.println("3. Update CSV Record");
            System.out.println("4. Delete CSV Record");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> handler.create();
                    case 2 -> handler.read();
                    case 3 -> handler.update();
                    case 4 -> handler.delete();
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option! Try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
