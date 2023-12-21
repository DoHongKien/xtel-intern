package G_MultiThread;

import java.util.*;

public class DemoQueue {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        removeElementInQueue(queue);
        showElementInQueue(queue);
    }

    // Enter the number of element want to add to queue and enter element into queue
    public static void addElementToQueue(Queue<Integer> queue) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời bạn nhập số lượng phần tử trong queue: ");

        int sizeOfQueue = scanner.nextInt();

        // Enter element in queue
        for (int i = 0; i < sizeOfQueue; i++) {
            System.out.println("Mời bạn nhập phần tử " + (i + 1) + " trong queue: ");
            queue.add(scanner.nextInt());
        }
    }

    // Display the element in the queue on the console
    public static void showElementInQueue(Queue<Integer> queue) {
        System.out.println("Số phần tử trong queue: ");
        for (Integer qu : queue) {
            System.out.print(qu + " ");
        }
    }

    // Enter the number of elements want to delete
    public static void removeElementInQueue(Queue<Integer> queue) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời bạn nhập số lượng phần tử muốn xóa khỏi queue: ");
        int number;

        // Check the number of elements greater than 0
        do {
            number = scanner.nextInt();
        } while (number <= 0);

        // Delete elements in queue
        for (int i = 0; i < number; i++) {
            Integer poll = queue.poll();
            if (poll == null) {
                System.out.println("Không có phần tử nào trong queue!");
                return;
            }
        }
    }

}
