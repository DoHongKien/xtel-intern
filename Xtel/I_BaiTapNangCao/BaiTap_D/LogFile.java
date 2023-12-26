package I_BaiTapNangCao.BaiTap_D;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFile {

    private static final Logger loggerRoot = Logger.getLogger(LogFile.class.getName());

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
            loggerRoot.warning("The IOExeption error can occur while write FileHandler");
        }
    }
}
