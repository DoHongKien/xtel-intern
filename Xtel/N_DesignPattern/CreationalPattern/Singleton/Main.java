package N_DesignPattern.CreationalPattern.Singleton;

public class Main {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        singleton.addString("Kien1");
        singleton.addString("Kien2");
        singleton.addString("Kien3");

        singleton.getStrings().forEach(System.out::println);
    }
}
