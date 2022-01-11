package calc;

import java.util.Map;
import java.util.TreeMap;

public class Operation {

    public static String calculate(Number num1, Number num2, String action) throws Exception {

        int result = switch (action) {
            case "+" -> num1.getValue() + num2.getValue();
            case "-" -> num1.getValue() - num2.getValue();
            case "*" -> num1.getValue() * num2.getValue();
            case "/" -> num1.getValue() / num2.getValue();
            default -> throw new Exception("Не правильно введен символ операции, используйте только +, -, *, /");
        };

        if (num1.getType() == Number.NumType.ROMAN) {
            if (result < 1) throw new Exception("В римской системе нет 0 и отрицательных чисел");
            return Action.toRomanNumber(result);
        } else return String.valueOf(result);

    }

    class Action {

        private final static TreeMap<Integer, String> romanString = new TreeMap<>();

        static {
            romanString.put(1, "I");
            romanString.put(4, "IV");
            romanString.put(5, "V");
            romanString.put(9, "IX");
            romanString.put(10, "X");
        }

        static Number parseAndValidate(String symbol) throws Exception {

            int value;
            Number.NumType type;

            try {
                value = Integer.parseInt(symbol);
                type = Number.NumType.ARABIC;

            } catch (NumberFormatException e) {
                value = toArabicNumber(symbol);
                type = Number.NumType.ROMAN;
            }

            if (value < 1 || value > 10) {
                throw new Exception("Неподходящее значение числа(ел), используйте числа от 1 до 10 включительно");
            }

            return new Number(value, type);

        }

        static Number parseAndValidate(String symbol, Number.NumType type) throws Exception {

            Number number = parseAndValidate(symbol);
            if (number.getType() != type) {
                throw new Exception("используются одновременно разные системы счисления, используйте один тип вводных значений");
            }

            return number;

        }

        private static int letterToNumber(char letter) {

            int result = -1;

            for (Map.Entry<Integer, String> entry : romanString.entrySet()) {
                if (entry.getValue().equals(String.valueOf(letter))) result = entry.getKey();
            }
            return result;

        }

        static String toRomanNumber(int number) {

            int i = romanString.floorKey(number);

            if (number == i) {
                return romanString.get(number);
            }

            return romanString.get(i) + toRomanNumber(number - i);

        }

        static int toArabicNumber(String roman) throws Exception {
            int result = 0;

            int i = 0;
            while (i < roman.length()) {
                char letter = roman.charAt(i);
                int num = letterToNumber(letter);

                if (num < 1) throw new Exception("Неверный римский символ");

                i++;

                if (i == roman.length()) {
                    result += num;

                } else {
                    int nextNum = letterToNumber(roman.charAt(i));
                    if (nextNum > num) {
                        result += (nextNum - num);
                        i++;
                    } else result += num;
                }

            }
            return result;

        }


    }
}

