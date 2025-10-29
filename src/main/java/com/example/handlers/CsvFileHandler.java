package com.example.handlers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.example.interfaces.CrudOperations;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CsvFileHandler implements CrudOperations {
    private static final Logger LOG = Logger.getLogger(CsvFileHandler.class.getName());

    private static final String FILE_PATH = "data.csv";

    @Override
    public void create(String name, String email) throws Exception {
        boolean fileExists = new File(FILE_PATH).exists();

        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH, true))) {
            if (!fileExists) writer.writeNext(new String[]{"Name", "Email"});
            writer.writeNext(new String[]{name, email});
        }
        LOG.info("Record added successfully.");
        //SFTPHandler sftp = new SFTPHandler();
        //sftp.uploadFile(FILE_PATH);
    }

    @Override
    public List<String[]> read() throws Exception {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            LOG.info("CSV file not found!");
            return new ArrayList<>();
        }

        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            List<String[]> allRecords = reader.readAll();
            allRecords.forEach(record -> LOG.info(String.join(", ", record)));
            return allRecords;
        }
    }


    @Override
    public boolean update(String oldName, String newName, String newEmail) throws Exception {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            LOG.info("CSV file not found!");
            return false;
        }

        List<String[]> allRecords;
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            allRecords = reader.readAll();
        }

        boolean updated = false;
        for (int i = 1; i < allRecords.size(); i++) {
            if (allRecords.get(i)[0].equalsIgnoreCase(oldName)) {
                allRecords.set(i, new String[]{newName, newEmail});
                updated = true;
                break;
            }
        }

        if (updated) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
                writer.writeAll(allRecords);
            }
            LOG.info("Record updated successfully!");
        } else {
            LOG.info("Name not found!");
        }
        return updated;
    }


    @Override
    public boolean delete(String nameToDelete) throws Exception {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            LOG.info("CSV file not found!");
            return false;
        }

        List<String[]> allRecords;
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            allRecords = reader.readAll();
        }

        boolean deleted = allRecords.removeIf(record -> record[0].equalsIgnoreCase(nameToDelete) && !record[0].equals("Name"));

        if (deleted) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
                writer.writeAll(allRecords);
            }
            LOG.info("Record deleted successfully!");
        } else {
            LOG.info("Name not found!");
        }
        return deleted;
    }

  
    public void create() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        create(name, email);
        sc.close();
    }

    public void update() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name to Update: ");
        String oldName = sc.nextLine();
        System.out.print("Enter new Name: ");
        String newName = sc.nextLine();
        System.out.print("Enter new Email: ");
        String newEmail = sc.nextLine();
        update(oldName, newName, newEmail);
        sc.close();
    }

    public void delete() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name to Delete: ");
        String nameToDelete = sc.nextLine();
        delete(nameToDelete);
        sc.close();
    }
}

