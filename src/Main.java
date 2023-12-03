import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1. Напишите программу-калькулятор арифметических выражений " +
                "\nзаписанных в обратной польской нотации (RPN-калькулятор).");
        String classic_string = "63-3+(45+6)/7*5+9";//выражение в польском калькуляторе
        String polish_String="63 3 - 45 6 + 7 / 5 * + 9 +";
        Polish_calc test=new Polish_calc();
        test.expression=classic_string;
        Polish_calc test1=new Polish_calc();
        test1.expression=polish_String;
        System.out.printf("Результат=%d", test.calculate(test.expression));
        System.out.printf("\nРезультат=%d", test.calculate(test1.expression));
    }
}
