package kien.exercise;

import java.util.Scanner;

public class Main {

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
}
