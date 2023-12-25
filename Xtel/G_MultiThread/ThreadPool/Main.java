package G_MultiThread.ThreadPool;

import G_MultiThread.BlockingQueue.Student;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    //Như ví dụ ở package BlockingQueue trong MultiThread thì ta chỉ chạy BlockingQueue trên 1 luồng do đó sẽ khá chậm.
    //Trong ví dụ này ta sẽ thiết lập cho chạy tối đa trên 10 luồng giúp việc thực hiện sẽ nhanh hơn

    //Tạo logger log các thông tin
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // Tạo BlockingQueue lưu trữ task đang chờ với kích thước tối đa là 20
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(20);

        // Tạo RejectedExecutionHandler để xử lý thực thi nhiệm vụ nếu các thread đều đang thực hiện
        RejectedExecutionHandler executionHandler = new ThreadPoolExecutor.CallerRunsPolicy();

        // Tạo thread pool với các thông số
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(
                        5, //Số luồng tối thiểu để thực hiện tasks
                        10, //Số luồng tối đa để thực hiện các task
                        60, // Thời gian chờ xem nếu thread đang rảnh sẽ hủy thread đó
                        TimeUnit.SECONDS,
                        blockingQueue, // Nếu các luồng đều đang thực hiện công việc mà có task tới sẽ đẩy vô BlockingQueue
                        executionHandler);


        for (int i = 0; i < 100; i++) {
            Student student = new Student("STUDENT" + i, "A" + i, "SUBJECT" + i);
            threadPoolExecutor.execute(new RequestHandle(student));
        }

        if (!threadPoolExecutor.isShutdown()) {
            //Khi pool kết thúc thì không nhận yêu cầu mới
            threadPoolExecutor.shutdown();
        }

        try {
            // Lấy kết quả thông báo khi pool kết thúc
            boolean isSuccess = threadPoolExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            if (isSuccess) {
                System.out.println("Successfully");
            }
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Error: " + e.getMessage());
        }
    }
}
