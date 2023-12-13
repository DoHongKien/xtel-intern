package OOP;

public class Main {

    public static void main(String[] args) {
        Meo meo = new Meo();

        meo.eat();
        meo.choi("Leo treo");
        meo.choi("Leo treo", "Hung du");

        NhanVien nhanVien = new NhanVien();
        nhanVien.nghe();
        nhanVien.doc();
        nhanVien.viet();
    }
}
