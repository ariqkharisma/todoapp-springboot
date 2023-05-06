package com.ariq.todo.utils;

public class PriorityValidation {

    public static boolean check(String priority) {
        String priorityLowerCase = priority.toLowerCase();
        
        if (priorityLowerCase.equals("low") || priorityLowerCase.equals("high") || priorityLowerCase.equals("medium")) {
            return true;
        } else return false;
    }
}
