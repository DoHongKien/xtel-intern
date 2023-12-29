package K_BaiTapKhac.AlgorithmSearch;

public class BinarySearch {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 4, 7, 9, 10, 13, 15, 16, 17, 18};

        System.out.println(binarySearch(numbers, 11));
    }

    // Hàm tìm kiếm vị trí phần tử bằng thuật toán Binary Search
    private static int binarySearch(int[] numbers, int numberToFind) {
        // Gán vị trí đầu và cuối mảng cho low và high
        int low = 0;
        int high = numbers.length - 1;

        while(low <= high) {
            // Lấy vị trí ở giữa trong mảng
            int elementNumberPivot = (low + high) / 2;
            // Lấy giá trị phần tử ở giữa của mảng
            int valueNumberPivot = numbers[elementNumberPivot];
            if(valueNumberPivot == numberToFind) {
                return elementNumberPivot;
            }

            if(valueNumberPivot < numberToFind) {
                low = elementNumberPivot + 1;
            } else {
                high = elementNumberPivot - 1;
            }
        }
        return -1;
    }
}
