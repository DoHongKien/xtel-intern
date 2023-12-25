package I_BaiTapNangCao.BaiTap_A;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BaiTapA {

    public static void main(String[] args) {
        // Tạo luồng chạy bằng ScheduledExecutorService
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // Thiết lập các thông số cho luồng
        executor.scheduleAtFixedRate(new ExecuteRequest(), 1, 1, TimeUnit.SECONDS);

        // Nhấn enter để dừng chương trình
        System.out.println("Press Enter to stop the program.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Kết thúc luồng sau khi enter
        executor.shutdown();
    }
}
