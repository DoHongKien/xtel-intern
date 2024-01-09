package N_DesignPattern.StructuralPattern.Facade;

public class Main {

    public static void main(String[] args) {
        FacadeService.getInstance().paymentByVNPayAndNotificationByEmail("dohongkien2003@gmail.com", "0987654321");
        FacadeService.getInstance().paymentByCashAndNotificationByPhone("dohongkien2003@gmail.com");
    }
}
