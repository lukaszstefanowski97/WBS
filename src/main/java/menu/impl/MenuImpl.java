package menu.impl;

import emailSender.EmailSender;
import emailSender.impl.EmailSenderImpl;
import menu.Menu;
import messages.Messages;
import validation.Validation;
import validation.impl.ValidationImpl;

import java.util.Scanner;

public class MenuImpl implements Menu {

    private Validation validation = new ValidationImpl();

    private EmailSender emailSender = new EmailSenderImpl();

    private Integer scanInteger() {
        try {
            Scanner enterInteger = new Scanner(System.in);
            Integer value = enterInteger.nextInt();
            return value;
        } catch (Exception type) {
            System.out.println("That was not an integer. Please type again:\n");
            return scanInteger();
        }
    }

    private String scanString() {
        Scanner enterString = new Scanner(System.in);
        String result = enterString.nextLine();
        if (result == null) scanString();
        return result;
    }

    private String scanEmail() {
        String email = scanString();
        if (!validation.validateEmailAddress(email)) {
            System.out.println("Email Address was incorrect. Please type again: ");
            scanEmail();
        }
        return email;
    }

    private Integer chooseYourPath() {
        Integer menuOption = scanInteger();
        if (!validation.validateMenuOption(menuOption)) chooseYourPath();
        return menuOption;
    }

    private Integer chooseQuantity() {
        Integer quantity = scanInteger();
        if (!validation.validateQuantityOrInterval(quantity)) chooseQuantity();
        return quantity;
    }

    private Integer chooseInterval() {
        Integer interval = scanInteger();
        if (!validation.validateQuantityOrInterval(interval)) chooseInterval();
        return interval;
    }

    @Override
    public void displayMenu() throws InterruptedException {
        System.out.println(Messages.MENU_MESSAGE);
        Integer path = chooseYourPath();
        if (path == 1) sendQuantityOfMails();
        if (path == 2) sendInfiniteMails();
        if (path == 3) System.out.println(Messages.GOODBYE_MESSAGE);
    }

    @Override
    public void sendQuantityOfMails() throws InterruptedException {
        System.out.println("Choose quantity: ");
        Integer quantity = chooseQuantity();
        System.out.println("Choose interval: ");
        Integer interval = chooseInterval();
        System.out.println("Type sender email: ");
        String senderEmail = scanEmail();
        System.out.println("Type sender password: ");
        String senderPassword = scanString();
        System.out.println("Type reciever email: ");
        String recieverEmail = scanEmail();
        System.out.println("Type your message");
        String message = scanString();

        emailSender.sendEmailWithQuantity(quantity, interval, senderEmail, senderPassword, recieverEmail, message);
    }

    @Override
    public void sendInfiniteMails() throws InterruptedException {
        System.out.println("Choose interval: ");
        Integer interval = chooseInterval();
        System.out.println("Type sender email: ");
        String senderEmail = scanEmail();
        System.out.println("Type sender password: ");
        String senderPassword = scanString();
        System.out.println("Type reciever email: ");
        String recieverEmail = scanEmail();
        System.out.println("Type your message");
        String message = scanString();

        emailSender.sendEmailsUntilYouDie(interval, senderEmail, senderPassword, recieverEmail, message);
    }
}
