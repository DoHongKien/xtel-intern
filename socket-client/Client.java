import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        // Tham gia vào socket có port 6789
        Socket socket = new Socket("localhost", 6789);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String message = "", messageCopy = "";
        while(!message.equals("stop")) {
            // Đọc dữ liệu từ client
            message = bufferedReader.readLine();

            // Gửi dữ liệu của client đến server
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();

            // Nhận lại và in ra console
            messageCopy = dataInputStream.readUTF();
            System.out.println("Client: " + messageCopy);
        }

        if(message.equals("stop")) {
            System.out.println("Bạn đã thoát khỏi cuộc trò chuyện");
        }

        dataOutputStream.close();
        socket.close();
    }
}
