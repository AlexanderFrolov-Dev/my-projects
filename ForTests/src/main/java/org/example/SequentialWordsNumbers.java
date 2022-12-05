package org.example;

public class SequentialWordsNumbers {

    public static String sequentialWordsNumbers(String text) {
        StringBuilder sb = new StringBuilder();
        String substr;
        int count = 1;
        int index = text.indexOf(' ');

        System.out.println("text.indexOf(' '): " + index);

        while (text.indexOf(' ') > 0) {
            substr = text.substring(0, text.indexOf(' '));
            sb.append("(").append(count).append(") ").append(substr).append(" ");
            text = text.substring(substr.length() + 1);
            count++;
        }

        if (!text.isBlank()) {
            substr = text.trim();
            sb.append("(").append(count).append(") ").append(substr);
        }

        return sb.toString();
    }
}
