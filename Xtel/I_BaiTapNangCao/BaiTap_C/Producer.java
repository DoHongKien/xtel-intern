package I_BaiTapNangCao.BaiTap_C;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Producer implements Runnable {

    // Tạo Logger ghi thông tin log
    private final Logger logger = Logger.getLogger(Producer.class.getName());

    // Tạo BlockingQueue thực hiện thêm phần tử vào queue
    private final BlockingQueue<Message> blockingQueue;

    public Producer(BlockingQueue<Message> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        // Thiết lập biến bắt đầu bằng 1
        int i = 1;
        while(true) {
            // Khởi tạo và thêm dữ liệu vào message
            Message message = new Message("SENDER" + i, "MESSAGE" + i, "RECEIVER" + i);
            try {
                // Thêm 1 message vào trong queue
                blockingQueue.put(message);

                // Ngủ 3 giây sau mỗi lần thêm phần tử vào queue
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                logger.warning("The InterruptedException error can occur in the Producer class");
                break;
            }
            // Tăng biến đếm nên 1
            i++;
        }
    }
}