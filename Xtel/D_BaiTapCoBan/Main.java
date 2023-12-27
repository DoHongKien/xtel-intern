package D_BaiTapCoBan;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    // Nhập số đến khi đúng số được chỉ định
    public static void baiTapA() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chọn số cần nhập");
        int numberA = scanner.nextInt();

        while(true) {
            System.out.println("Vui lòng nhập đúng số vừa nhập!");
            int numberB = scanner.nextInt();
            if(numberA == numberB) {
                System.out.println("Chúc mừng bạn đã chọn đúng!!!");
                return;
            }
        }
    }

    // Tính tiền điện
    public static void baiTapB() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vui lòng nhập số điện đã dùng: ");
        int soDienDaDung = scanner.nextInt();
        if(soDienDaDung < 0) {
            System.out.print("Vui lòng nhập lại số điện đã dùng: ");
            soDienDaDung = scanner.nextInt();
        }

        int soTienCanTra;
        if(soDienDaDung <= 100) {
            soTienCanTra = soDienDaDung * 1000;
        } else if(soDienDaDung < 150) {
            soTienCanTra = (100 * 1000) + ((soDienDaDung - 100) * 1500);
        } else {
            soTienCanTra = (100 * 1000) + (49 * 1500) + ((soDienDaDung - 149) * 2000);
        }
        System.out.println("Số tiền cân trả là: " + soTienCanTra + "đ");
    }

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        sortInFile();
//        long end = System.currentTimeMillis();
//        System.out.println();
//        System.out.println("Time execute: " + (end - start));

        insertNumberInFile();
    }

    // Sắp xếp mảng 1 chiều từ file input.txt
    public static void sortInFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            String readLine = bufferedReader.readLine();
            int[] array = Arrays.stream(readLine.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            quickSort(array, 0, array.length - 1);
            Arrays.stream(array).forEach(n -> System.out.print(n + " "));

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Insert number into input.txt file
    public static void insertNumberInFile() {
        try {
            OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream("input.txt"));
            Random random = new Random();

            int i = 0;
            while(i < 1_000_000) {
                outputStream.write(random.nextInt(1, 99));
                i++;
            }
            outputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void quickSort(int[] numbers, int l, int r) {
        int p = numbers[(l + r) / 2];
        int i = l, j = r;

        while(i < j) {
            while(numbers[i] < p) {
                i++;
            }
            while(numbers[j] > p) {
                j--;
            }
            if(i <= j) {
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                i++;
                j--;
            }

            if(i < r) {
                quickSort(numbers, i, r);
            }
            if(j > l) {
                quickSort(numbers, l, j);
            }
        }
    }
}
