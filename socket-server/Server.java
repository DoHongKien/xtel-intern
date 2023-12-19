import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        // Tạo kết nối socket với port 6789
        ServerSocket serverSocket = new ServerSocket( 6789);

        // Lắng nghe khi có client tham gia vào và chấp nhận
        Socket socket = serverSocket.accept();

        // Khởi tạo DataInputStream đọc dữ liệu
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        // Khởi tạo DataOutputStream ghi dữ liệu
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        // Khởi tạo BufferedReader để đọc dữ liệu viết từ console
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String message = "", messageCopy = "";
        while(!message.equals("stop")) {

            // Nhận và in tin nhắn của client
            message = dataInputStream.readUTF();
            System.out.println("Server: " + message);

            // Đọc dữ liệu của server trong console
            messageCopy = bufferedReader.readLine();

            // Gửi dữ liệu của server đến client
            dataOutputStream.writeUTF(messageCopy);
            dataOutputStream.flush();
        }

        if(message.equals("stop")) {
            System.out.println("Bạn đã thoát khỏi cuộc trò chuyện");
        }

        dataInputStream.close();
        socket.close();
        serverSocket.close();
    }
}
