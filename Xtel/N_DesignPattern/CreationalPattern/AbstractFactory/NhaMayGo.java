package N_DesignPattern.CreationalPattern.AbstractFactory;

public class NhaMayGo extends NhaMayNoiThatAbstract {

    @Override
    public Ghe lamGhe() {
        return new GheGo();
    }

    @Override
    public Ban lamBan() {
        return new BanGo();
    }
}
