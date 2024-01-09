package N_DesignPattern.StructuralPattern.Facade;

public class NotificationService {

    public void sendEmail(String email) {
        System.out.println("Send to email with: " + email);
    }

    public void sendPhoneNumber(String phoneNumber) {
        System.out.println("Send to phone number with phone: " + phoneNumber);
    }
}
