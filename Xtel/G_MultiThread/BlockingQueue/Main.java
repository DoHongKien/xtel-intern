package G_MultiThread.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    //Ví dụ này được lấy ý tưởng từ việc sinh viên đăng ký tín chỉ.
    //Do nếu sinh viên đồng loạt đăng ký sẽ có nguy cơ bị sập server nên ví dụ này tạo ra để nhằm khắc
    //phục việc đó bằng cách cho 5 sinh viên đăng ký cùng lúc.
    //Sinh viên nào vào trước đăng ký xong trước sẽ đi ra cho sinh viên sau vô và cứ như thế đến 100

    public static void main(String[] args) {
        // Tạo ArrayBlockingQueue có kích thước bằng 5
        BlockingQueue<Student> queue = new ArrayBlockingQueue<>(5);

        // Khai báo producer và consumer
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        // Khai báo thread cho producer và consumer
        Thread thread = new Thread(producer);
        Thread thread1 = new Thread(consumer);

        // Bắt đầu chạy luồng
        thread.start();
        thread1.start();
    }
}
