package G_MultiThread.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {

    // Tạo logger
    private final Logger logger = Logger.getLogger(Producer.class.getName());

    // Khai báo BlockingQueue
    private final BlockingQueue<Student> queue;

    public Producer(BlockingQueue<Student> queue) {
        this.queue = queue;
    }


    // Thêm 100 sinh viên vào queue sau mỗi 1 giây
    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            Student student = new Student("STUDENT" + i, "A" + i, "SUBJECT" + i);
            try {
                // Thêm sinh viên vào queue
                queue.put(student);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.log(Level.WARNING, "Error when put student into queue: " + e);
            }
        }
    }
}
