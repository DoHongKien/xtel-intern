package G_MultiThread.Wait_Notify;

import java.util.logging.Logger;

public class DemoWaitNotify {

    // Tạo logger để log thông tin
    private static final Logger LOGGER = Logger.getLogger(DemoWaitNotify.class.getName());

    // Tạo đối tượng để lock trong synchnorized
    private final Object object = new Object();

    // Tạo mảng dữ liệu
    private final boolean[] datas = {false, false, false, false, true};

    // Biến kiểm tra đã có dữ liệu hay chưa
    private boolean isDataReady = false;

    public void producer() {
        synchronized (object) {
            System.out.println("Đang tìm kiếm dữ liệu");
            for (boolean data : datas) {
                try {
                    Thread.sleep(1000);
                    if (data) {
                        isDataReady = true;
                        break;
                    }
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
            // Thông báo cho các luồng đang đợi biết và thực thi khi có dữ liệu
            object.notify();
        }
    }

    public void consumer() {
        synchronized (object) {
            try {
                System.out.println("Đang đợi dữ liệu");

                // Nếu dữ liệu chưa có thì gọi wait()
                while (!isDataReady) {
                    object.wait();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println("Đã lấy được dữ liệu");
        }
    }

    public static void main(String[] args) {
        DemoWaitNotify waitNotify = new DemoWaitNotify();

        Thread thread = new Thread(waitNotify::producer);

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            waitNotify.consumer();
        });

        thread.start();
        thread1.start();
    }
}
