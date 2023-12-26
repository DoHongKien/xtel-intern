package I_BaiTapNangCao.BaiTap_D;

import java.io.*;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    // Khởi tạo Logger lấy ra các thông tin
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        Socket socket = null;
        try {
            // Kết nối vào socket có port 1234 và ip 127.0.0.1
            socket = new Socket("127.0.0.1", 1234);

            // Khai báo InputStream để đọc socket
            InputStream is = socket.getInputStream();
            DataInputStream inputStream = new DataInputStream(is);

            // Khai báo OutputStream để ghi socket
            OutputStream os = socket.getOutputStream();
            DataOutputStream outputStream = new DataOutputStream(os);

            while(true) {
                // Dùng UUID để sinh chuỗi ngẫu nhiên
                String message = UUID.randomUUID().toString();

                // dùng DataInputStream để ghi chuỗi gửi sang server
                outputStream.writeUTF(message);

                // Lấy giá trị vừa ghi và in ra màn hình của client
                String read = inputStream.readUTF();
                System.out.println(read);

                // Ngủ 3 giây mỗi khi sinh số ngẫu nhiên
                Thread.sleep(3000);
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "The IOException error can occur: " + e);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "The InterruptedException error can occur when thread sleep: " + e);
        } finally {
            // Kiểm tra nếu socket còn đang hoạt động thì đóng socket
            if(socket != null) {
                try {
                    // Đóng socket
                    socket.close();
                } catch (IOException e) {
                    logger.log(Level.WARNING, "The IOException error can occur when close socket: " + e);
                }
            }
        }
    }
}
