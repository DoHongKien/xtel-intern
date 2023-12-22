package G_MultiThread.ThreadPool;

import G_MultiThread.BlockingQueue.Student;

public class RequestHandle implements Runnable{

    private final Student student;

    public RequestHandle(Student student) {
        this.student = student;
    }

    //Chạy task mỗi sau 1 giây
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " start task " + student.getCode());
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " finish task " + student.getCode());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
