package validation.impl;

import validation.Validation;

public class ValidationImpl implements Validation {

    @Override
    public boolean validateMenuOption(Integer option) {
        if (option == null) return false;
        return option == 1 || option == 2 || option == 3;
    }

    @Override
    public boolean validateQuantityOrInterval(Integer number) {
        if (number == null) return false;
        return number > 0;
    }

    @Override
    public boolean validateEmailAddress(String email) {
        if (email == null || email.length() < 5 || !email.contains("@")) {
            return false;
        }
        int apeCounter = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                ++apeCounter;
            }
        }
        return apeCounter <= 1;
    }
}
