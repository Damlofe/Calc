import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        String input = null;
        System.out.print(calc(input));

    }
//для проверки на идентичность записи чисел
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private static String roman (int numArab) {
        String[] rom = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        final String s = rom[numArab];
        return s;
    }

    private static int romanToNumber (String roman) {
        try {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }

    public static int calculated (int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
    public static String calc(String input){
        Scanner eq = new Scanner(System.in); //вводим-считываем пример, который необходимо решить
        String str = eq.next();
        eq.close();
        str = str.trim(); //убрать пробелы
        char[] oper = new char[10]; //здесь будут введённые данные посимвольно
        char operation = ' ';
        int check = 0;
        int min = 0;
        for (int i = 0; i < str.length(); i++) { //ищем операцию
            oper[i] = str.charAt(i);
            if (oper[i] == '+') {
                operation = '+';
                check++;
            }
            if (oper[i] == '-') {
                operation = '-';
                check++;
                min++;
            }
            if (oper[i] == '*') {
                operation = '*';
                check++;
            }
            if (oper[i] == '/') {
                operation = '/';
                check++;
            }
        }
        if (check != 1) {
            if (check > 1) {
                throw new InputMismatchException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            } else throw new InputMismatchException("throws Exception //т.к. строка не является математической операцией");
        }  // проверка на соответствие условия количества операторов
        String operString = String.valueOf(oper);
        String[] arrOperand = str.split("[-+*/]"); //массив чисел из строки
        int check2 = 0;
        for (int i = 0; i<2; i++) {
            if (isNumeric(arrOperand[i]) == false) {
                check2++;
            }
        }
        if (check2 == 1) {
            throw new InputMismatchException("т.к. используются одновременно разные системы счисления");
        }
        String numb0 = arrOperand[0];
        String numb1 = arrOperand[1];
        int nom1 = romanToNumber(numb0);
        int nom2 = romanToNumber(numb1);
        int result = 0;
        if (nom1 > 10 || nom2 > 10) {
            throw new InputMismatchException("Неверный формат данных");
        }
        if (nom1 < 0 && nom2 < 0) {
            result = 0;
        } else {
            if (min > 0) {
                if (nom2 <= nom1) {
                    result = calculated(nom1, nom2, operation);
                    String resultRoman = roman(result);
                    System.out.print(resultRoman);
                } else throw new InputMismatchException("т.к. в римской системе нет отрицательных чисел");
            } else { result = calculated(nom1, nom2, operation);
                String resultRoman = roman(result);
                System.out.print(resultRoman); }
        }
        nom1 = Integer.parseInt(numb0);
        nom2 = Integer.parseInt(numb1);
        result = calculated(nom1, nom2, operation);
        String itog = String.valueOf(result);
        return itog;
    }
}
