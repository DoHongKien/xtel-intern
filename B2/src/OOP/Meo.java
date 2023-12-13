package OOP;

public class Meo extends DongVat {

    private String mauSac;
    private Double canNang;

    public Meo() {
    }

    public Meo(Integer id, String name, String mauSac, Double canNang) {
        super(id, name);
        this.mauSac = mauSac;
        this.canNang = canNang;
    }

    // 3 hàm eat(), speak(), sleep() là overriding khi thân hàm đã bị thay đổi so với lớp OOP.DongVat
    @Override
    public void eat() {
        System.out.println("Ăn ngoạm ngoạm");
    }

    @Override
    public void speak() {
        System.out.println("Kêu meo meo");
    }

    @Override
    public void sleep() {
        System.out.println("Ngủ khò khò");
    }

    // Đây là overloading khi vẫn giữa nguyên tên hàm choi() nhưng khác tham số truyền vào
    public void choi(String hanhDong) {
        super.choi(hanhDong);
    }

    public void choi(String hanhDong, String tinhCach) {
        System.out.println(hanhDong + " " + tinhCach);
    }

    // Get, set
    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public Double getCanNang() {
        return canNang;
    }

    public void setCanNang(Double canNang) {
        this.canNang = canNang;
    }
}
