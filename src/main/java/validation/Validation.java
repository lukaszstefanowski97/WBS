package validation;

public interface Validation {

    boolean validateMenuOption(Integer option);

    boolean validateQuantityOrInterval(Integer number);

    boolean validateEmailAddress(String email);
}
