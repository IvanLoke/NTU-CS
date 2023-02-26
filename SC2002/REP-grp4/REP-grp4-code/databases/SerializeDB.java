package databases;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represents the abstract DB class for users to read and write from DBs.
 * 
 * @author Aaron Chua
 * @version 1.0
 * @since 2022-11-11
 */
public abstract class SerializeDB<T> {
    /**
     * The filepath to the .dat file.
     */
    protected String filename;

    /**
     * Reads the file.
     * 
     * @return the data contained in the file.
     */
    public T read() {
        T data = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            T readData = (T) in.readObject();
            data = readData;
            // The method readObject is used to read an object from the stream. Java's safe
            // casting should be used to get the desired type.
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    /**
     * Writes to the file.
     * 
     * @param data the data to be stored in the file.
     */
    public void write(T data) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(data);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
