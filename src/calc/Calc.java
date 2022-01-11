package calc;

import java.util.Scanner;

public class Calc {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Input: ");
        String line = scan.nextLine();

        try {
            String[] action = line.split(" ");
            if (action.length < 3) throw new Exception("строка не является математической операцией");
            if (action.length > 3) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            Number num1 = Operation.Action.parseAndValidate(action[0]);
            Number num2 = Operation.Action.parseAndValidate(action[2], num1.getType());
            String result = Operation.calculate(num1, num2, action[1]);
            System.out.println("Output: \n" + result);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        scan.close();
    }

}

