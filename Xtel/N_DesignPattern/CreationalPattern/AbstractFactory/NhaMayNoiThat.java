package N_DesignPattern.CreationalPattern.AbstractFactory;

public class NhaMayNoiThat {

    // Chọn chất liệu muốn làm
    public static NhaMayNoiThatAbstract nhaMayNoiThat(ChatLieu chatLieu) {
        switch (chatLieu) {
            case GO -> {
                // Đến nhà máy gỗ
                return new NhaMayGo();
            }
            case NHUA -> {
                // Đến nhà máy nhựa
                return new NhaMayNhua();
            }
            default -> throw new IllegalStateException("Khong co chat lieu phu hop");
        }
    }
}

enum ChatLieu {
    GO, NHUA
}
