package I_BaiTapNangCao.BaiTap_D;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Server {

    // Khởi tạo Logger lấy ra các thông tin
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // Khởi tạo server socket có port 1234
            serverSocket = new ServerSocket(1234);
            while (true) {
                Socket socket = null;
                DataOutputStream outputStream = null;
                try {
                    // Cho socket chấp nhận các client vào
                    socket = serverSocket.accept();

                    // Thiết lập timeout là 5 giây cho thời gian tối đa thực hiện 1 nhiệm vụ
                    socket.setSoTimeout(5000);

                    // Khởi tạo InputStream đọc dữ liệu trong socket
                    InputStream is = socket.getInputStream();
                    DataInputStream inputStream = new DataInputStream(is);

                    // Khai báo OutputStream để ghi socket
                    OutputStream os = socket.getOutputStream();
                    outputStream = new DataOutputStream(os);
                    String message;

                    while (true) {
                        // Đọc dữ liệu từ socket
                        message = inputStream.readUTF();
                        if (message.isEmpty()) {
                            break;
                        }
                        // In ra màn hình dữ liệu vừa đọc
                        System.out.println(message);

                        // Ghi dữ liệu từ socket
                        outputStream.writeUTF(message);
                    }

                    // Đóng socket khi kết thúc
                    socket.close();
                } catch (IOException e) {
                    // Kiểm tra nếu socket đang chạy và quá thời gian thực thi sẽ thông báo cho cả client và server quá thời gian
                    if (socket != null && socket.getSoTimeout() > 0) {
                        String mesageTimeOut = "Dữ liệu gửi quá hạn";
                        outputStream.writeUTF(mesageTimeOut);
                        System.out.println(mesageTimeOut);

                        // Gọi hàm viết lỗi vào file
                        writeErrorInFile(logger);
                    }
                    logger.log(Level.WARNING, "The IOException error can occur: " + e);
                }
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "The IOException error can occur: " + e);
        } finally {
            // Kiểm tra nếu socket còn đang hoạt động thì đóng socket
            if (serverSocket != null) {
                try {
                    // Đóng socket
                    serverSocket.close();
                } catch (IOException e) {
                    logger.log(Level.WARNING, "The IOException error can occur: " + e);
                }
            }
        }
    }

    // Hàm ghi lỗi vào file
    public static void writeErrorInFile(Logger logger) {
        try {
            // Khởi FileHandler ghi lỗi vào file có đường dẫn "I_BaiTapNangCao/BaiTap_D/log-error.txt"
            FileHandler fileHandler = new FileHandler("I_BaiTapNangCao/BaiTap_D/log-error.txt");

            // Thiết lập FileHandler cho logger
            logger.addHandler(fileHandler);

            // Dùng SimpleFormatter định dang file
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            logger.warning("The IOExeption error can occur while write FileHandler");
        }
    }
}
