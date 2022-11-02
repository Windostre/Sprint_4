package ru.yandex.qa_scooter.helpers;

public class Utils {
    public static int randomNumber() {
        int min = 0;
        int max = 6;
        int index = (int)(Math.random()*(max-min+1)+min);
        return index;
    }
}
