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
            System.out.println(Messages.INVALID_INPUT_MESSAGE);
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
            System.out.println(Messages.INVALID_INPUT_MESSAGE);
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

    private String[] getAuthorizationData() {
        String[] data = new String[4];
        System.out.println(Messages.GET_SENDER_EMAIL);
        data[0] = scanEmail();
        System.out.println(Messages.GET_PASSWORD);
        data[1] = scanString();
        System.out.println(Messages.GET_RECIEVER_EMAIL);
        data[2] = scanEmail();
        System.out.println(Messages.GET_MESSAGE);
        data[3] = scanString();
        return data;
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
        System.out.println(Messages.GET_QUANTITY);
        Integer quantity = chooseQuantity();
        System.out.println(Messages.GET_INTERVAL);
        Integer interval = chooseInterval();
        String[] data = getAuthorizationData();

        emailSender.sendEmailWithQuantity(quantity, interval, data[0], data[1], data[2], data[3]);
    }

    @Override
    public void sendInfiniteMails() throws InterruptedException {
        System.out.println(Messages.GET_INTERVAL);
        Integer interval = chooseInterval();
        String[] data = getAuthorizationData();

        emailSender.sendEmailsUntilYouDie(interval, data[0], data[1], data[2], data[3]);
    }
}
