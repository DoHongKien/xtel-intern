public class VongLap {

    public static void main(String[] args) {
        //If
        boolean isNotNull = true;
        if (isNotNull) { // Nếu điều kiện đúng sẽ thực hiện if
            System.out.println("Không phải là null");
        }

        // For
        for (int i = 0; i < 5; i++) { // Chạy for đến khi i = 5 sẽ dừng vì kiều kiện sai 5 < 5
            System.out.println("FOR");
        }

        // While
        int w = 0;
        while (w < 0) { // Điều kiện đúng sẽ thực hiện thân while
            System.out.println("WHILE");
            w++;
        }

        //Do - While
        int dw = 0;
        do {
            System.out.println("DO_WHILE");
            dw++;
        } while (dw < 0); // Điều kiện sai sẽ kết thúc do-while

        // Switch - case
        // Thực hiện hàm trong case nếu biến sw bằng với case còn không sẽ thực hiện default
        int sw = 3;
        switch (sw) {
            case 1:
                System.out.println("Case 1");
                break;
            case 2:
                System.out.println("Case 2");
                break;
            case 3:
                System.out.println("Case 3");
                break;
            default:
                System.out.println("Default");
                break;
        }

    }
}
