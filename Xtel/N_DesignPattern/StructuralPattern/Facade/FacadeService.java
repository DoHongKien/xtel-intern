package N_DesignPattern.StructuralPattern.Facade;

public class FacadeService {

    private static final FacadeService INSTANCE = new FacadeService();

    private final AccountService accountService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    private FacadeService() {
        accountService = new AccountService();
        paymentService = new PaymentService();
        notificationService = new NotificationService();
    }

    public static FacadeService getInstance() {
        return INSTANCE;
    }

    public void paymentByCashAndNotificationByPhone(String email) {
        accountService.getAccount(email);
        paymentService.paymentByVnPay();
        notificationService.sendEmail(email);
    }

    public void paymentByVNPayAndNotificationByEmail(String email, String phone) {
        accountService.getAccount(email);
        paymentService.paymentByCash();
        notificationService.sendPhoneNumber(phone);
    }
}
