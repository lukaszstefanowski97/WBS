package emailSender.impl;

import emailSender.EmailSender;
import messages.Messages;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderImpl implements EmailSender {

    @Override
    public void callApi(String senderAddress, String senderPassword, String receiverAddress, String message) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.user", senderAddress);
        props.put("mail.password", senderPassword);

        Session session = Session.getDefaultInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderAddress, senderPassword);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(senderAddress));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiverAddress));
            msg.setSubject(Messages.SUBJECT);
            msg.setText(message);
            Transport.send(msg);
            System.out.println(Messages.EMAIL_SENT);
        } catch (AddressException e) {
            System.out.println(Messages.ADDRESS_EXCEPTION);
        } catch (MessagingException e) {
            System.out.println(Messages.MESSAGE_EXCEPTION);
        }
    }

    @Override
    public void sendEmailWithQuantity(Integer quantity, Integer interval, String senderAddress, String senderPassword,
                                      String receiverAddress, String message) throws InterruptedException {

        for (int i = 0; i < quantity; i++) {
                callApi(senderAddress, senderPassword, receiverAddress, message);
                TimeUnit.SECONDS.sleep(interval);
        }
        System.out.println(Messages.GOODBYE_MESSAGE);
    }

    @Override
    public void sendEmailsUntilYouDie(Integer interval, String senderAddress, String senderPassword,
                                      String receiverAddress, String message) throws InterruptedException {

        while(true) {
                callApi(senderAddress, senderPassword, receiverAddress, message);
                TimeUnit.SECONDS.sleep(interval);
        }
    }
}
