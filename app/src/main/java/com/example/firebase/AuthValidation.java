package com.example.firebase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthValidation
{
    public boolean isValid(String phone, String email, String password, String repeatPswd)
    {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Pattern passPattern = Pattern.compile("[A-Za-z0-9*/<|>-]");
        Pattern phonePattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
        Pattern langPattern = Pattern.compile("[А-Яа-я]");

        Matcher emailCheck = emailPattern.matcher(email);
        Matcher passCheck = passPattern.matcher(password);
        Matcher phoneCheck = phonePattern.matcher(phone);
        Matcher langPassCheck = langPattern.matcher(password);
        Matcher langEmailCheck = langPattern.matcher(email);

        if (phoneCheck.find() == false)
            return false;

        if (emailCheck.find() == false)
            return false;

        if (passCheck.find() == false)
            return false;

        if (langPassCheck.find() ==  true || langEmailCheck.find() == true)
            return false;

        if (password.length() <  7)
            return false;

        if (password.equals(repeatPswd) ==  false)
            return false;

        return true;
    }
}
