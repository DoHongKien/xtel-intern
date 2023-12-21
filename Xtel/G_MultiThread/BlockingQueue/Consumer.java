package G_MultiThread.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread{

    // Tạo logger
    private final Logger logger = Logger.getLogger(Consumer.class.getName());

    // Khởi tạo BlockingQueue
    private BlockingQueue<Student> queue;

    public Consumer(BlockingQueue<Student> queue) {
        this.queue = queue;
    }


    // Sau mỗi 5 giây lấy ra sinh viên đứng đầu trong queue
    @Override
    public void run() {
        try {
            while (true) {
                // Trả về và xóa sinh viên ra khỏi queue
                Student student = queue.take();
                System.out.println(student.toString());
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Error when consumer get data in queue");
        }
    }
}
