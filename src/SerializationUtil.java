
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationUtil //implements Serializable
{

    public static Object deserialize(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        return obj;

    }

    public static void serialize(Object obj, String fileName) throws FileNotFoundException, IOException {
        FileOutputStream fos; //= new FileOutputStream(fileName);
        ObjectOutputStream oos; //= new ObjectOutputStream(fos);
        File file = new File(fileName);
        if (file.length() != 0) {
            System.out.println("mpiak stin append");
            fos = new FileOutputStream(fileName, true);
            oos = new ObjectOutputStream(fos) {
                protected void writeStreamHeader1() throws IOException {
                    reset();
                }
            };
            oos.writeObject(obj);

            oos.flush();
            oos.close();
        } else if (file.length() == 0) {
            System.out.println("den mpika stin append");
            //file.createNewFile();
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
        }
    }
}
