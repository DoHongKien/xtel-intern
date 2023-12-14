import java.util.Scanner;

public class QuickSort {

    public static int[] quickSort(int[] numbers, int l, int r) {
        int p = numbers[(l + r) / 2]; // Lấy giá trị của trục làm mốc
        int i = l, j = r;

        while (i < j) {// Nếu left lớn hơn hoặc bằng right thì dừng while

            while (numbers[i] < p) { // Nếu các giá trị nằm bên trái mà nhỏ hơn giá trị trục thì tăng i
                i++;
            }

            while (numbers[j] > p) { // Nếu các giá trị nằm bên phải mà lớn hơn giá trị trục thì trừ j
                j--;
            }

            if (i <= j) { //Nếu giá trị hiện tại của index i mà nhỏ hơn hoặc bằng giá trị hiện tại của index j thì đổi chỗ chúng cho nhau
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                i++;//Tăng index bên trái lên 1
                j--;//Giảm index bên phải đi 1
            }

            if (i < r) {
                quickSort(numbers, i, r);
            }

            if (j > l) {
                quickSort(numbers, l, j);
            }
        }
        return numbers;
    }

    public static void print(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số lượng phần tử của mảng");
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        System.out.println("Mời bạn nhập danh sách số nguyên");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        print(quickSort(numbers, 0, n - 1));
    }
}
