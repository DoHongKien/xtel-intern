package N_DesignPattern.StructuralPattern.Adapter;

public class NguoiViet {

    public static void main(String[] args) {
        CongCuGui congCuGui = new TrinhPhienDich(new NguoiPhienDich());

        congCuGui.sendMessage("Xin chào");
    }
}
