package N_DesignPattern.CreationalPattern.FactoryMethod;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Bank mbBank = BankFactory.getBank(BankType.MBBANK);
        System.out.println(mbBank.getBankName());
    }
}
