import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringJoiner;

public class Polish_calc {
    static String expression;

    static boolean isDelim(char c) {
        return c == ' ';
    }

    static boolean isOperator(char c) { // возвращяем тру если один из символов ниже
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    static int priority(char op) {
        switch (op) { // определение приоритета операций
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            default:
                return -1;
        }
    }

    static void processOperator(LinkedList<Integer> st, char op) {//выполняет операции
        int r = st.removeLast(); //из упорядоченного листа берем последние элементы
        int l = st.removeLast();
        switch (op) { // выполняем действие между левым и правым в зависимости от оператора в кейсе и результат записываем в st
            case '+':
                st.add(l + r);
                break;
            case '-':
                st.add(l - r);
                break;
            case '*':
                st.add(l * r);
                break;
            case '/':
                st.add(l / r);
                break;
            case '%':
                st.add(l % r);
                break;
        }
    }

    public static Integer calculate(String s) {
        LinkedList<Integer> st = new LinkedList<Integer>(); // сюда записываем числа
        LinkedList<Character> op = new LinkedList<Character>(); // сюда опрераторы и st и op в порядке поступления
        for (int i = 0; i < s.length(); i++) { // парсим строку с выражением и вычисляем
            char c = s.charAt(i);
            if (isDelim(c))//если элемент пробле, то скипаем
                continue;
            if (c == '(')//если элемент скобочка, добавим её в op
                op.add('(');
            else if (c == ')') {//если элемент другая скобочка, пока последним элементом не станет первая скобочка, производим соответствующие операции
                while (op.getLast() != '(')
                    processOperator(st, op.removeLast());
                op.removeLast();//после выполнении операции, удалим её
            } else if (isOperator(c)) {//если элемент-оператор?
                while (!op.isEmpty() && priority(op.getLast()) >= priority(c))//если операторы не пустые и приоритет соотвествует
                    processOperator(st, op.removeLast());//выполним данную операцию
                op.add(c);
            } else {
                String operand = "";
                while (i < s.length() && Character.isDigit(s.charAt(i)))
                    operand += s.charAt(i++);
                --i;
                st.add(Integer.parseInt(operand));
            }
        }
        while (!op.isEmpty())//пока операторы не пустые, выполним их
            processOperator(st, op.removeLast());
        return st.get(0);  // возврат результата
    }
}

