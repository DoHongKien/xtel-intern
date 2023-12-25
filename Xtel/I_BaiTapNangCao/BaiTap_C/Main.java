package I_BaiTapNangCao.BaiTap_C;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        // Tạo BlockingQueue với kích thước là 5
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>(5);

        // Tạo luồng cho producer
        Thread producer = new Thread(new Producer(blockingQueue));

        // Tạo luồng cho consumer
        Thread consumer = new Thread(new Consumer(blockingQueue));

        // Bắt đầu có thể chạy luồng producer và consumer
        producer.start();
        consumer.start();
    }
}
