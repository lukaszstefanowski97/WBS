import menu.Menu;
import menu.impl.MenuImpl;
import messages.Messages;

public class WojtylaBattleScript {

    public static void main(String[] args) {

        Menu menu = new MenuImpl();
        try {
            menu.displayMenu();
        } catch (InterruptedException e) {
            System.out.println(Messages.GOODBYE_MESSAGE);
        }
        System.out.println(Messages.GOODBYE_MESSAGE);
    }
}
