package OOP;

import OOP.HanhDong;

public class NhanVien extends AbstractPeople implements HanhDong {

    @Override
    public void doc() {
        System.out.println("Doc");
    }

    @Override
    public void viet() {
        System.out.println("Viet");
    }

    @Override
    public void noi() {
        System.out.println("Noi linh tinh");
    }

    @Override
    public void nghe() {
        super.nghe();
    }
}
