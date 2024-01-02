package L_Java8.Interface_Lambda;


public class LambdaExpressions {

    public static void main(String[] args) {
        // "() -> lambda..." expression lambda
        DemoInterface demoInterface = (message) -> System.out.println("I agree, " + message);
        demoInterface.sayYes("Kien");
    }
}
