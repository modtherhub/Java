import java.io.FileOutputStream;
import java.io.IOException;

public class TestBinary {
    public static void main(String[] args) {
        try {
            FileOutputStream output = new FileOutputStream("test.bin");
            
            output.write(65);
            output.write(66);
            output.write(67);
            
            output.close();
            
            System.out.println("Done writing to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}