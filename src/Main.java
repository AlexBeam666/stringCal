import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String exp = scn.nextLine();

        char action;
        String[] data;
        if (exp.contains(" + ")) {
            data = exp.split(" \\+ ");
            action = '+';
        } else if (exp.contains(" - ")) {
            data = exp.split(" - ");
            action = '-';
        } else if (exp.contains(" * ")) {
            data = exp.split(" \\* ");
            action = '*';
        } else if (exp.contains(" / ")) {
            data = exp.split(" / ");
            action = '/';
        } else {
            throw new Exception("Некорректный знак действия");
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "").trim();
        }

        // Проверка длины строк
        for (String str : data) {
            if (str.length() > 10) {
                throw new Exception("Строка не должна превышать 10 символов");
            }
        }

        if (action == '*' || action == '/') {
            // Проверка, что число находится в пределах от 1 до 10
            int number = Integer.parseInt(data[1]);
            if (number < 1 || number > 10) {
                throw new Exception("Число должно быть от 1 до 10 включительно");
            }
        }

        if (action == '+') {
            printInQuotes(data[0] + data[1]);
        } else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]);
            String result = "";
            for (int i = 0; i < multiplier; i++) {
                result += data[0];
                if (result.length() > 40) {
                    result = result.substring(0, 40) + "...";
                }
            }
            printInQuotes(result);
        } else if (action == '-') {
            int index = data[0].indexOf(data[1]);
            if (index == -1) {
                printInQuotes(data[0]);
            } else {
                String result = data[0].substring(0, index);
                result += data[0].substring(index + data[1].length());
                printInQuotes(result);
            }
        } else {
            int divisor = Integer.parseInt(data[1]);
            int newLen = data[0].length() / divisor;
            printInQuotes(data[0].substring(0, Math.min(newLen, data[0].length())));
        }
    }

    static void printInQuotes(String text) {
        System.out.println("Ответ: " + "\n" + "\"" + text + "\"");
    }
}
