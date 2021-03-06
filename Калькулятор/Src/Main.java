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
        String[] rom = {"0","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        return rom[numArab];
    }

    private static int romanToNumber (String roman) {
        try {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
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
                throw new IllegalArgumentException("Неверный знак операции");
        }
        return result;
    }
    public static String calc(String input){
        //вводим-считываем пример, который необходимо решить
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
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
        } // проверка на соответствие условия количества операторов
        String[] arrOperand = str.split("[-+*/]"); //массив чисел из строки
        arrOperand[0] = arrOperand[0].trim();
        arrOperand[1] = arrOperand[1].trim();
        if (arrOperand[0].contains(" ") || arrOperand[1].contains(" ")) {
            throw new InputMismatchException("Неверный формат данных");
        }
        int check2 = 0; //для проверки однотипности данных
        for (int i = 0; i<2; i++) {
            if (!isNumeric(arrOperand[i])) {
                check2++;
            }
        }
        if (check2 == 1) {
            throw new InputMismatchException("т.к. используются одновременно разные системы счисления");
        }
        String numb0 = arrOperand[0];
        String numb2 = arrOperand[1];
        String numb1 = numb2.trim();
        int nom1 = romanToNumber(numb0);
        int nom2 = romanToNumber(numb1);
        int result = 0;
        if (nom1 < 0 && nom2 < 0) {
            for (int i = 0; i<2; i++){
                int ch = Integer.parseInt (arrOperand[i].trim ());
                if (ch> 10) {
                    throw new InputMismatchException("Неверный формат данных");
                } //проверка на величину входных чисел (не больше 10)
            }
            nom1 = Integer.parseInt(numb0);
            nom2 = Integer.parseInt(numb1);
            result = calculated(nom1, nom2, operation);
            return String.valueOf(result);
        } else {
            if (min > 0) {
                if (nom2 < nom1) {
                    result = calculated(nom1, nom2, operation);
                    String resultRoman = roman(result);
                    return (resultRoman);
                } else throw new InputMismatchException("т.к. в римской системе нет отрицательных чисел и 0");
            } else { result = calculated(nom1, nom2, operation);
                if (result == 0) {
                    throw new InputMismatchException("т.к. в римской системе нет отрицательных чисел и 0");
                }
                String resultRoman = roman(result);
                return (resultRoman); }
        }
    }
}
