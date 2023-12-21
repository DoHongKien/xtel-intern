package C_ExceptionHandling;

public class ExceptionHandling {

    public static void testThrows() throws ArithmeticException {// Khai báo ngoại lệ có thể xảy ra trong hàm testThrows()
        int result = 50 /0;
    }

    public static void main(String[] args) {

        int number = 50 / 0;// Xảy ra lỗi ArithmeticException
        System.out.println("Ngoài try-catch");// Không được chạy khi dòng trên lỗi

        try {
            int result = 50 / 0; // Xảy ra lỗi ArithmeticException
        } catch (ArithmeticException e) {
            e.printStackTrace();//Catch xử lý lỗi ArithmeticException
        }
        System.out.println("Ngoài try-catch");// Chạy được khi lỗi đã được catch xử lý

        try {
            int result = 50 / 0; // Xảy ra lỗi ArithmeticException
            System.out.println("Thành công không?"); // Không được chạy khi "50 /0" lỗi
        } catch (ArithmeticException e) {
            e.printStackTrace();//Catch xử lý lỗi ArithmeticException
        } finally {
            System.out.println("Thành công hay thất bại thì tôi vẫn chạy?");//Finally luôn được chạy dù có ném ra ngoại lệ hay không
        }
        System.out.println("Ngoài try-catch");// Chạy được khi lỗi đã được catch xử lý

        try {
            throw new ClassNotFoundException("Class khong ton tai");// Dùng throw ném ra ngoại lệ ClassNotFoundException
        } catch (ClassNotFoundException e) {
            System.out.println("Xu ly ClassNotFoundException");
            e.printStackTrace();
        }

        try{
            testThrows();//Cho hàm testThrows() vào try-catch để xỷ lý do hàm testThrows() cảnh báo ra ngoại lệ ArithmeticException
        }catch (ArithmeticException e) {
            System.out.println("Xử lý lỗi ArithmeticException từ hàm testThrows()");
            e.printStackTrace();
        }

    }
}
