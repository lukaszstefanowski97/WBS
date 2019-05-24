package emailSender;

public interface EmailSender {

    void callApi(String senderAddress, String senderPassword, String receiverAddress, String message);

    void sendEmailWithQuantity(Integer quantity, Integer interval, String senderAddress, String senderPassword,
                               String receiverAddress, String message) throws InterruptedException;

    void sendEmailsUntilYouDie(Integer interval, String senderAddress, String senderPassword, String receiverAddress,
                               String message) throws InterruptedException;
}
