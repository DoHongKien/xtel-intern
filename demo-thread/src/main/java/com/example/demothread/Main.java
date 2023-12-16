package com.example.demothread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    // Tạo đối tượng khóa để xử lý đồng bộ
    private static final Object lock = new Object();
    private static int count = 1;

    public static void main(String[] args) {
        // Tạo schedule để thực hiện lên lịch theo khoảng thời gian cố định
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);

        // Tạo 1 luồng bằng interface runnable
        Runnable t1 = () -> System.out.println("Hello World");
        // Dùng schedule để thực hiện liên tục runnable sau 3 giây
        scheduled.scheduleAtFixedRate(t1, 0, 3, TimeUnit.SECONDS);

        // Khởi tại timer
        Timer timer = new Timer();

        // Khởi tạo time task thực hiện in "Hello"
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };

        // Set sau mỗi 3s in "Hello" 1 lần
        timer.schedule(timerTask, 0,3000);

        // Gọi hàm in chẵn, lẻ để thực hiện in ra số từ 1 -> 11 xen kẽ nhau
        Thread evenThread = new Thread(Main::printEven);
        Thread oddThread = new Thread(Main::printOdd);

        // Chạy thread
        evenThread.start();
        oddThread.start();
    }


    //Hàm in ra số chẵn
    public static void printEven() {
        while(count <= 10) {
            //Chặn nhiều luồng xử lý khối mã đồng thời
            synchronized (lock){
                if(count % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                }
            }
        }
    }

    // Hàm in ra số lẻ
    public static void printOdd() {
        while(count <= 10) {
            //Chặn nhiều luồng xử lý khối mã đồng thời
            synchronized (lock) {
                if(count % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                }
            }
        }
    }
}
