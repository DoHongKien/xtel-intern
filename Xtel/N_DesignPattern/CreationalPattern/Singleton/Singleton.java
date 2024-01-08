package N_DesignPattern.CreationalPattern.Singleton;


import java.util.ArrayList;
import java.util.List;

public class Singleton {

    private static Singleton singleton;
    private final List<String> strings;

    private Singleton() {
        strings = new ArrayList<>();
    }

    public static Singleton getSingleton() {
        if(singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void addString(String str) {
        strings.add(str);
    }
}
