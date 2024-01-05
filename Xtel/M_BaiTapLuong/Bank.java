package M_BaiTapLuong;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public synchronized void guiTien(Integer accountId, Double soTienGui) {
        Account account = getTaiKhoan(accountId);
        if(account != null) {
            account.guiTien(soTienGui);
        }
    }

    public synchronized void rutTien(Integer accountId, Double soTienRut) {
        Account account = getTaiKhoan(accountId);
        if(account != null) {
            account.rutTien(soTienRut);
        }
    }

    public synchronized double getTongTien(int accountId) {
        Account account = getTaiKhoan(accountId);
        return (account != null) ? account.getTongTien() : -1;
    }

    private Account getTaiKhoan(int accountId) {
        for (Account account : accounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        return null;
    }
}
