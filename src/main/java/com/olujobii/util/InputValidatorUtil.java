package com.olujobii.util;

public class InputValidatorUtil {

    public static boolean isAValidInteger(String input){
        try{
           Integer.parseInt(input);
           return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }

    public static boolean isUserOptionNotValid(int userOption, int listSize){
        return userOption < 1 || userOption > listSize;
    }

    public static boolean isAValidPrice(String input){
        try{
            Double.parseDouble(input);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }

}
