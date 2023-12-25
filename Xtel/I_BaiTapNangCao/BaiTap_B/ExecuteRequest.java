package I_BaiTapNangCao.BaiTap_B;

import java.util.concurrent.ThreadLocalRandom;

public class ExecuteRequest implements Runnable{

    @Override
    public void run() {
        // Lấy ra số nguyên ngẫu nhiên trong khoảng 1 - 1000
        int randomNumber = ThreadLocalRandom.current().nextInt(1, 1000);
        System.out.println(randomNumber);
    }
}
