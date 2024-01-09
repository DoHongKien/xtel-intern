package N_DesignPattern.CreationalPattern.AbstractFactory;

public class NhaMayNhua extends NhaMayNoiThatAbstract {

    @Override
    public Ghe lamGhe() {
        return new GheNhua();
    }

    @Override
    public Ban lamBan() {
        return new BanNhua();
    }
}
