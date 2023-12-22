package G_MultiThread.Synchronize_Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Synchnorize {

    // Tạo logger nhằm log thông tin
    private static final Logger logger = Logger.getLogger(Synchnorize.class.getName());

    // Dùng từ khóa synchnorized để đồng bộ hóa tiến trình hàm khi có nhiều hơn 1 thao tác gọi đến hàm này
    public synchronized static void print(int number) {
        for (int i = 0; i < number; i++) {
            System.out.println("Number-" + (i * number));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.warning("Error: " + e.getMessage());
            }
        }
    }

    public static void print(String name) {
        // Dùng synchnorized đồng bộ 1 khối code, khi có thao tác gọi tới khối code nằm trong synchnorized sẽ được đồng bộ
        synchronized (Synchnorize.class) {
            for (int i = 0; i < 5; i++) {
                System.out.println(name + "-1-" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    logger.warning("Error: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> print("5"));
        Thread thread1 = new Thread(() -> print("10"));

        thread.start();
        thread1.start();
    }
}
