import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputOutputStream {

    public static void writeFile()  throws IOException {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("E:\\file.txt");
            String content = "Hello guys";
            byte[] write = content.getBytes();
            fileOutputStream.write(write);
            fileOutputStream.close();
            System.out.println("Successfully");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileOutputStream.close();
        }
    }

    public static void readFile() throws IOException{
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("E:\\file.txt");
            int i = 0;
            while((i = fileInputStream.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
        }
    }
}
