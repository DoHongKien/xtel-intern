package G_MultiThread.Synchronize_Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DemoLock {

    // Khởi tạo lock
    private final Lock lock = new ReentrantLock();

    // Khai báo biến đếm
    public int count = 0;

    public void demoLock() {

        // Kiểm tra xem lock đã đươc bật chưa, nếu chưa thì bật lock
        if(!lock.tryLock()) {
            // Bắt đầu đồng bộ hóa
            lock.lock();
        }
        try {
            //Tăng biến đếm lên 1
            ++count;
            System.out.println(Thread.currentThread().getName() + " - counter: " + count);
        } finally {
            // Kết thúc đồng bộ hóa
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        DemoLock classLock = new DemoLock();
        Thread thread1 = new Thread(() -> {
            // Lặp hàm demoLock() trong for để tăng biến count
            for(int i = 0; i < 5; i++) {
                classLock.demoLock();
            }
        });
        Thread thread2 = new Thread(() -> {
            // Lặp hàm demoLock() trong for để tăng biến count
            for(int i = 0; i < 5; i++) {
                classLock.demoLock();
            }
        });

        // Bắt đầu thread
        thread1.start();
        thread2.start();
    }
}
