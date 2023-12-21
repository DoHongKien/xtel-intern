package A_KyThuatLapTrinhCanBan;

import java.util.Scanner;

public class Array {

    // Hàm nhận vào 1 mảng int và sau đó tiến hành sắp xếp tăng dần và trả về mảng int đã sắp xếp tăng dần
    public static int[] sortAsc(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    int swap = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = swap;
                }
            }
        }
        return numbers;
    }

    public static int[] selectionSort(int[] numbers) {
        int min;
        for (int i = 0; i < numbers.length; i++) {
            min = i;
            for (int j = i; j < numbers.length; j++) {
                if(numbers[min] > numbers[j]) {
                    min = j;
                }
            }

            if(min != i) {
                int temp = numbers[i];
                numbers[i] = numbers[min];
                numbers[min] = temp;
            }
        }

        return numbers;
    }

    //Thêm phần tử vào mảng
    public static void addArray() {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[5];
        System.out.println("Vui long nhap gia tri");
        for (int i = 0; i < numbers.length; i++) {
            int number = scanner.nextInt();
            numbers[i] = number;
        }

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }

    // Xóa phần tử khỏi mảng
    public static void deleteElement() {
        Scanner scanner = new Scanner(System.in);
        // Tạo 1 mảng có 5 phần tử
        int[] numbers = {1, 2, 3, 4, 5};

        //Nhập giá trị muốn xóa
        System.out.println("Mời bạn nhập số muốn xóa");
        int num = scanner.nextInt();

        // Tìm kiếm giá trị muốn xóa trong mảng
        int element = search(numbers, num);

        if (element == -1) {
            System.out.println("Không có phần tử nào trong mảng có giá trị " + element);
        }

        int length = numbers.length;
        for (int i = element; i < length; i++) {
            //Thay thế giá trị của phần tử muốn xóa bằng phần tử đứng trên nó và giảm kích thước mảng
            numbers[i] = numbers[i + 1];
            length -= 1;
        }

        for (int i = 0; i < length; i++) {
            System.out.println(numbers[i]);
        }
    }

    // Tìm kiếm phần tử trong mảng
    public static int search(int[] numbers, int number) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == number) {
                return i;
            }
        }
        return -1;
    }
}
