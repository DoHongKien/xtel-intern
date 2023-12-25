package I_BaiTapNangCao.BaiTap_C;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    // Tạo Logger ghi thông tin log
    private final Logger logger = Logger.getLogger(G_MultiThread.BlockingQueue.Consumer.class.getName());

    // Tạo BlockingQueue thực hiện lấy phần tử ra khỏi queue
    private final BlockingQueue<Message> blockingQueue;

    public Consumer(BlockingQueue<Message> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while(true) {
            try {
                // Lấy phần tử ra khỏi queue
                Message message = blockingQueue.take();
                // In message ra console
                System.out.println(message.toString());

                // Ngủ 1 giây sau mỗi lần lấy phần tử
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.warning("The InterruptedException error can occur in the Consumer class");
            }
        }
    }
}