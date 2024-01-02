package L_Java8.Interface_Lambda;

public interface DemoInterface {

    // Use static method in interface
    static String sayHello() {
        return "Hello World!";
    }

    // Use default method in interface
    default String sayHi(String message) {
        return "Hi " + message;
    }

    void sayYes(String message);
}
