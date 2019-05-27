package messages;

import java.util.Date;

public class Messages {

    public static final String MENU_MESSAGE = "Please select option:\n[1] Send defined quantity of emails\n[2] Send " +
            "emails until Google Mail API will discover that you are not human\n[3] Exit";

    public static final String GOODBYE_MESSAGE = "Thanks, bye!";

    public static final String INVALID_INPUT_MESSAGE = "Invalid input... Please type again:";

    public static final String IS_NOT_GMAIL = "Google Mali API does not allow email addresses which are not from " +
            "gmail. Please use gmail address:";

    public static final String GET_SENDER_EMAIL = "Type sender email: ";

    public static final String GET_PASSWORD = "Type sender password: ";

    public static final String GET_RECIEVER_EMAIL = "Type reciever email: ";

    public static final String GET_MESSAGE = "Type your message";

    public static final String GET_QUANTITY = "Choose quantity: ";

    public static final String GET_INTERVAL = "Choose interval: ";

    public static final String ADDRESS_EXCEPTION = "\nAddress Exception";

    public static final String MESSAGE_EXCEPTION = "\nMessage Exception";

    public static final String SUBJECT = "WojtylaBattleScript";

    public static String EMAIL_SENT = "\nEmail sent: " + new Date().toString();
}