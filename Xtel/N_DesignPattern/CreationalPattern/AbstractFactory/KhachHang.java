package N_DesignPattern.CreationalPattern.AbstractFactory;

public class KhachHang {

    public static void main(String[] args) {
        NhaMayNoiThatAbstract nhaMayNoiThat = NhaMayNoiThat.nhaMayNoiThat(ChatLieu.NHUA);

        Ban ban = nhaMayNoiThat.lamBan();
        Ghe ghe = nhaMayNoiThat.lamGhe();
        ban.cheTao();
        ghe.cheTao();
    }
}
