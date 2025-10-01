package edu.au.cpsc.module7.data;

import java.io.*;

public class TransactionDatabaseIO {

    public static void save(TransactionDatabase ad, OutputStream strm) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(strm);
        oos.writeObject(ad);
    }

    public static TransactionDatabase load(InputStream strm) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(strm);
        return (TransactionDatabase) ois.readObject();

    }
}
