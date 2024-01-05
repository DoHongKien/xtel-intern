package M_BaiTapLuong;

public class Account {

    private Integer id;
    private Double tongTien;

    public Account(Integer id, Double tongTien) {
        this.id = id;
        this.tongTien = tongTien;
    }

    public synchronized void guiTien(Double soTienGui) {
        tongTien += soTienGui;
        System.out.println("Số tiền gửi là " + soTienGui + " trong tài khoản " + id + " là: " + tongTien);
    }

    public synchronized void rutTien(Double soTienRut) {
        if(soTienRut > tongTien) {
            System.out.println("Số tiền không đủ để rút");
        } else {
            tongTien -= soTienRut;
            System.out.println("Số tiền rút là " + soTienRut + " trong tài khoản " + id + " là: " + tongTien);
        }
    }

    public Integer getId() {
        return id;
    }

    public synchronized Double getTongTien() {
        return tongTien;
    }
}
