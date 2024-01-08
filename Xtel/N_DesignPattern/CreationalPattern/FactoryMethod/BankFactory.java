package N_DesignPattern.CreationalPattern.FactoryMethod;

public class BankFactory {

    private BankFactory() {}

    public static Bank getBank(BankType bankType) throws IllegalAccessException {
        switch (bankType) {
            case MBBANK -> {
                return new MBBank();
            }
            case VIETCOMBANK -> {
                return new VietComBank();
            }
            default -> throw new IllegalAccessException("Khong co ngan hang nao");
        }
    }
}
