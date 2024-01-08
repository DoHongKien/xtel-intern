package N_DesignPattern.CreationalPattern.BuilderPattern;

public class DoAnNhanhBuilder implements DatHangBuilder{
    private LoaiBanhMy banhMy;
    private LoaiDatHang datHang;
    private LoaiHaiSan haiSan;
    private LoaiRau rau;

    @Override
    public DatHangBuilder loaiDatHang(LoaiDatHang loaiDatHang) {
        this.datHang = loaiDatHang;
        return this;
    }

    @Override
    public DatHangBuilder loaiBanhMy(LoaiBanhMy loaiBanhMy) {
        this.banhMy = loaiBanhMy;
        return this;
    }

    @Override
    public DatHangBuilder loaiHaiSan(LoaiHaiSan loaiHaiSan) {
        this.haiSan =loaiHaiSan;
        return this;
    }

    @Override
    public DatHangBuilder loaiRau(LoaiRau loaiRau) {
        this.rau = loaiRau;
        return this;
    }

    @Override
    public Order build() {
        return new Order(datHang, banhMy, haiSan, rau);
    }
}
