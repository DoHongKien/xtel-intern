package H_Logger;

import java.io.IOException;
import java.util.logging.*;

public class DemoLogger {

    public static void main(String[] args) {

        // Khởi tạo logger có tên giống class để log thông tin
        Logger logger = Logger.getLogger(DemoLogger.class.getName());

        try {
            // Gọi hàm ghi log vào file
            writeLog(logger);

            // Tạo ra ngoại lệ để ghi log vào file
            int i = 50 / 0;

        } catch (SecurityException e) {
            // Ném ra ngoại lệ của hàm addHandler()
            logger.warning("The SecurityException error can occur in the DemoLogger class");
        } catch (ArithmeticException e) {
            // Ném ra ngoại lệ của 50/0
            logger.warning("The ArithmeticException error can occur in the DemoLogger class");
        }

        logger.info("This is info log");
    }

    private static void writeLog(Logger logger) {
        try {
            // Khởi tạo FileHandler tiến hành lưu log vào file
            FileHandler fh = new FileHandler("H_Logger/MyLogFile.log");

            logger.addHandler(fh);

            // Dùng SimpleFormatter để định dạng file
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            // Ném ra ngoại lệ của FileHandler
            logger.warning("The IOException error can occur in the DemoLogger class");
        }

    }
}
