package A_KyThuatLapTrinhCanBan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputOutputStream {

    public static void writeFile()  throws IOException {
        //Khởi tạo FileOutputStream ghi dữ liệu vào file
        FileOutputStream fileOutputStream = null;

        try {
            // Chỉ định đường dẫn file ghi dữ liệu "E:\\file.txt"
            fileOutputStream = new FileOutputStream("E:\\file.txt");

            // Đọc nội dung ra mảng byte
            String content = "Hello guys";
            byte[] write = content.getBytes();

            // Ghi vào file
            fileOutputStream.write(write);

            // Đóng việc ghi file
            fileOutputStream.close();
            System.out.println("Successfully");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng việc ghi file
            fileOutputStream.close();
        }
    }

    public static void readFile() throws IOException{
        //Khởi tạo FileInputStream đọc dữ liệu trong file
        FileInputStream fileInputStream = null;

        try {
            // Chỉ định đọc dữ liệu từ đường dẫn "E:\\file.txt"
            fileInputStream = new FileInputStream("E:\\file.txt");
            int i = 0;
            // Đọc ký tự trong while đến khi không còn ký tự nào
            while((i = fileInputStream.read()) != -1) {
                // Convert sang kiểu char để in d liệu ra console
                System.out.print((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng việc đọc file
            fileInputStream.close();
        }
    }
}
