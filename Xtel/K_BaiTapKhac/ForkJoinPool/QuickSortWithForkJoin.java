package K_BaiTapKhac.ForkJoinPool;

import java.io.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuickSortWithForkJoin extends RecursiveAction {

    byte[] numbers;

    int left, right;

    public QuickSortWithForkJoin(byte[] array, int left, int right) {
        this.numbers = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        int i = left, j = right;

        // Đặt phần tử ở giữa mảng làm trục
        int pivot = numbers[(left + right) / 2];

        while (i <= j) {
            while (numbers[i] < pivot) {
                i++;
            }
            while (numbers[j] > pivot) {
                j--;
            }

            // Thay đổi giá trị 2 vị trí
            if (i <= j) {
                byte temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                i++;
                j--;
            }
        }

        QuickSortWithForkJoin leftTask = null;
        QuickSortWithForkJoin rightTask = null;

        if (i < right) {
            leftTask = new QuickSortWithForkJoin(numbers, i, right);
        }

        if (j > left) {
            rightTask = new QuickSortWithForkJoin(numbers, left, j);
        }

        // Kiểm tra nếu leftTask được khởi tạo trong điều kiện if (i < right) thì gọi lại lệnh compute
        if (leftTask != null) {
            leftTask.compute();
        }

        // Kiểm tra nếu rightTask được khởi tạo trong điều kiện if (i < right) thì gọi lại lệnh compute
        if (rightTask != null) {
            rightTask.compute();
        }
    }

    public static void main(String[] args) throws IOException {
        // Lấy dữ liệu trong file để sắp xếp
        byte[] bytes = readFile();

        long start = System.nanoTime();

        // Khởi tạo ForkJoinPool thực hiện chia nhỏ task để làm việc
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // Khởi tạo hàm sắp xếp
        QuickSortWithForkJoin forkJoin = new QuickSortWithForkJoin(bytes, 0, bytes.length - 1);

        // Thực hiện việc chia nhỏ task
        forkJoinPool.invoke(forkJoin);
        long end = System.nanoTime();

        // Thời gian trả về khi sắp xếp hoàn thành
        System.out.println("Time execute: " + ((end - start) / 1_000_000_000) + "s");

        // Thực hiện sắp xếp 1 tỷ số thời gian nhanh nhất đạt được là 70s trên laptop 8gb ram
    }

    private static byte[] readFile() {
        Logger logger = Logger.getLogger("read-file");
        FileInputStream fileInputStream = null;
        byte[] bytes = new byte[0];
        try {
            // Tạo FileInputStream đọc file từ input.txt
            fileInputStream = new FileInputStream("input.txt");

            // Lấy ra tất cả dữ liệu lưu vào mảng byte
            bytes = fileInputStream.readAllBytes();

        } catch (IOException e) {
            if(logger.isLoggable(Level.WARNING)) {
                logger.warning("The IOException error can occur when read file in the readFile function");
            }

        } finally {
            // Kiểm tra xem FileInputStream còn hoạt động hay không
            if(fileInputStream != null) {
                try {
                    // Đóng FileInputStream
                    fileInputStream.close();
                } catch (IOException e) {
                    if(logger.isLoggable(Level.WARNING)) {
                        logger.warning("The IOException error can occur when close FileInputStream");
                    }
                }
            }

        }
        return bytes;
    }
}
