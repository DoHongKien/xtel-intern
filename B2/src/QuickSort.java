import java.sql.SQLOutput;
import java.util.Scanner;

public class QuickSort {

    public static int[] quickSort(int[] numbers, int l, int r) {
        int p = numbers[(l + r) / 2];
        int i = l, j = r;

        while (i < j) {

            while (numbers[i] < p) {
                i++;
            }

            while (numbers[j] > p) {
                j--;
            }

            if (i <= j) {
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                i++;
                j--;
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
