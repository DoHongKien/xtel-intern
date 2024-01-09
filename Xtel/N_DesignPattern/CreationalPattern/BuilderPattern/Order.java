package N_DesignPattern.CreationalPattern.BuilderPattern;

public class Order {

    private final LoaiBanhMy banhMy;
    private final LoaiDatHang datHang;
    private final LoaiHaiSan haiSan;
    private final LoaiRau rau;

    public Order(LoaiDatHang datHang, LoaiBanhMy banhMy, LoaiHaiSan haiSan, LoaiRau rau) {
        super();
        this.banhMy = banhMy;
        this.datHang = datHang;
        this.haiSan = haiSan;
        this.rau = rau;
    }

    @Override
    public String toString() {
        return "Order{" +
               "banhMy=" + banhMy +
               ", datHang=" + datHang +
               ", haiSan=" + haiSan +
               ", rau=" + rau +
               '}';
    }


}

