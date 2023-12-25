package I_BaiTapNangCao.BaiTap_B;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaiTapB {

    // Khởi tạo Logger ghi thông tin log
    private static final Logger logger = Logger.getLogger(BaiTapB.class.getName());

    public static void main(String[] args) {
        // Nhập số giây để kết thúc chương trình
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số giây bạn muốn chương trình kết thúc");
        int time = scanner.nextInt();
        // Gọi hàm thực thi chương trình
        runRequest(time);
    }

    private static void runRequest(int time) {
        System.out.println("Start!!!");
        // Khởi tạo ScheduledExecutorService tạo 1 luồng theo lịch trình
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // Thiết lập các tham số cho luồng chạy
        executor.scheduleAtFixedRate(new ExecuteRequest(), 1, 1, TimeUnit.SECONDS);

        try {
            // Cho luồng ngủ sau n giây
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "The InterruptedException error can occur while the thread sleep");
        }
        // Kết thúc chương trình sau khi chạy hết số giây ở trên
        executor.shutdown();

        // Kiểm tra nếu chương trình đã kết thúc thì in ra thông báo
        if(executor.isShutdown()) {
            System.out.println("Process is shutdown");
        }
    }
}
