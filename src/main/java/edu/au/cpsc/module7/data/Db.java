package edu.au.cpsc.module7.data;

import java.io.*;

public class Db {

    public static final File DEFAULT_FILE = new File("transactions.dat");
    private static TransactionDatabase db = null;

    public static TransactionDatabase getDataBase() {
        if (db == null) {
            db = loadDatabase();
        }
        return db;
    }

    private static TransactionDatabase loadDatabase() {
        try (InputStream is = new FileInputStream(DEFAULT_FILE)) {
            return TransactionDatabaseIO.load(is);
        } catch (IOException | ClassNotFoundException e) {
            return new TransactionDatabase();
        }
    }

    public static void saveDatabase() {
        try (OutputStream os = new FileOutputStream(DEFAULT_FILE)) {
            TransactionDatabaseIO.save(getDataBase(), os);
        } catch (IOException e) {
            System.err.println("Error saving database: " + DEFAULT_FILE);
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
