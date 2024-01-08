package N_DesignPattern.CreationalPattern.BuilderPattern;

public class Order {

    private LoaiBanhMy banhMy;
    private LoaiDatHang datHang;
    private LoaiHaiSan haiSan;
    private LoaiRau rau;

    public Order(LoaiDatHang datHang, LoaiBanhMy banhMy, LoaiHaiSan haiSan, LoaiRau rau) {
        super();
        this.banhMy = banhMy;
        this.datHang = datHang;
        this.haiSan = haiSan;
        this.rau = rau;
    }

    public LoaiBanhMy getBanhMy() {
        return banhMy;
    }

    public LoaiDatHang getDatHang() {
        return datHang;
    }

    public LoaiHaiSan getHaiSan() {
        return haiSan;
    }

    public LoaiRau getRau() {
        return rau;
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

