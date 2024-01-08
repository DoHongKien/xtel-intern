package N_DesignPattern.CreationalPattern.BuilderPattern;

public interface DatHangBuilder {

    DatHangBuilder loaiDatHang(LoaiDatHang loaiDatHang);

    DatHangBuilder loaiBanhMy(LoaiBanhMy loaiBanhMy);

    DatHangBuilder loaiHaiSan(LoaiHaiSan loaiHaiSan);

    DatHangBuilder loaiRau(LoaiRau loaiRau);

    Order build();
}
