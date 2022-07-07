package com.company.shoparearent.app;

import io.jmix.core.annotation.MessageSourceBasenames;
import io.jmix.ui.component.ValidationException;
import io.jmix.ui.component.validation.Validator;
import io.jmix.ui.screen.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("ui_PhoneNumberValidator")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PhoneNumberValidator implements Validator<String> {

    @Autowired
    private MessageBundle messageBundle;

    private static final int CORRECT_PHONE_NUMBER_LENGTH = 16;
    private static final int[] spaceIndexes = {2, 6, 10, 13};
    private static final int[] digitIndexes = {1, 3, 4, 5, 7, 8, 9, 11, 12, 14, 15};

    public static boolean IsCorrectPhoneNumberFormat(String phoneNumber) {
        if (phoneNumber.length() != CORRECT_PHONE_NUMBER_LENGTH)
            return false;

        if (phoneNumber.charAt(0) != '+')
            return false;

        for (int spaceIndex : spaceIndexes) {
            if (phoneNumber.charAt(spaceIndex) != ' ')
                return false;
        }

        for (int digitIndex : digitIndexes) {
            if (!Character.isDigit(phoneNumber.charAt(digitIndex)))
                return false;
        }

        return true;
    }

    @Override
    public void accept(String value) throws ValidationException {
        if (!IsCorrectPhoneNumberFormat(value))
            throw new ValidationException(messageBundle.getMessage("ui_PhoneNumberValidator.exceptionMessage"));
    }
}