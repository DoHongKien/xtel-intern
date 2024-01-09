package N_DesignPattern.StructuralPattern.Adapter;

public class TrinhPhienDich implements CongCuGui {

    private final NguoiPhienDich nguoiPhienDich;

    public TrinhPhienDich(NguoiPhienDich nguoiPhienDich) {
        this.nguoiPhienDich = nguoiPhienDich;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Người Việt nhắn: " + message);
        String translated = translate(message);
        nguoiPhienDich.recieveMessage(translated);
    }

    private String translate(String message) {
        System.out.println("Dịch tiếng Việt: " + message + " => sang tiếng Anh: Hello world" );
        return "Hello world";
    }
}
