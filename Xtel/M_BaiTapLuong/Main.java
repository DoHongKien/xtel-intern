package M_BaiTapLuong;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Account account1 = new Account(1, 10000.0);
        Account account2 = new Account(2, 5000.0);
        bank.addAccount(account1);
        bank.addAccount(account2);

        Runnable runnable = () -> {
            for(int i = 0; i < 10; i++) {
                bank.guiTien(1, 5000.0);
            }
        };

        Runnable runnable1 = () -> {
            for(int i = 0; i < 10; i++) {
                bank.rutTien(1, 2000.0);
            }
        };

        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);

        thread.start();
        thread1.start();

        // Chờ 2 luồng kết thúc sau khi hiển thị tổng tiền trong tài khoản
        thread.join();
        thread1.join();

        System.out.println(bank.getTongTien(1));
    }
}
