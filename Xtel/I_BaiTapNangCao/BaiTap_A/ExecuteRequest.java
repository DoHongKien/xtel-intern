package I_BaiTapNangCao.BaiTap_A;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class ExecuteRequest implements Runnable {

    private final Logger logger = Logger.getLogger(ExecuteRequest.class.getName());

    public ExecuteRequest() {
    }

    @Override
    public void run() {
        // Lấy đường dẫn của file log
        Path path = Paths.get("I_BaiTapNangCao/BaiTap_A/output.txt");
        BufferedWriter writer;

        // Tạo StringBuilder hỗ trợ thao tác với chuỗi
        StringBuilder builder = new StringBuilder();
        try {
            // Tạo số nguyên random
            int randomNumber = ThreadLocalRandom.current().nextInt(1, 1000);

            // Đọc dữ liệu từ file qua BufferedReader
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
                String message;
                while ((message = reader.readLine()) != null) {
                    // Nối dữ liệu cũ trong file với số random mới
                    builder.append(message).append(" ").append(randomNumber);
                }
            }

            // Ghi dữ liệu bằng BufferedWriter vào file
            writer = new BufferedWriter(new FileWriter(path.toFile()));
            System.out.println(randomNumber);
            writer.write(builder.toString());

            // Đóng cổng BufferedWriter
            writer.close();

        } catch (IOException e) {
            logger.warning("The IOException error can occur in the ExecuteRequest");
        }
    }
}
