package N_DesignPattern.CreationalPattern.BuilderPattern;

public class Main {

    public static void main(String[] args) {
        Order order = new DoAnNhanhBuilder()
                .loaiDatHang(LoaiDatHang.ONLINE)
                .loaiBanhMy(LoaiBanhMy.TRUNG)
                .loaiHaiSan(LoaiHaiSan.CA)
                .loaiRau(LoaiRau.RAUMUONG)
                .build();
        System.out.println(order);
    }
}
