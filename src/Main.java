import java.util.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Main {
    public static void main(String[] args) {

        String s = "Я ищу совпадения в 2024 году. И я их найду в 3 счёта. 198765 Ели[-ели]";
        String pattern;

        pattern = "[2024]"; // шаблон для поиска каждого из перечисленных в квадратных скобках символа по очереди
        pattern = "[0-9]"; // шаблон для поиска каждого из указанного в квадратных скобках диапазона символов
        pattern = "[0-9][0-9]"; // можно и такой шаблон задать
        pattern = "[0-9][0-9][0-9]"; // можно и такой шаблон задать
        pattern = "[12][0-9][0-9][0-9]"; // можно и такой шаблон задать
        pattern = "[A-яЁё.]"; // можно так [A-Яа-яё] а можно и так [A-яё]  а можно и так [а-яёA-Я] но только в русском алфавите в ангдийском алфавите только так [A-Za-z]
        pattern = "."; // как спецсимвол и выводит все символы
        pattern = "[.]"; // точка перестает быть спецсимволом и выводит только точки
        pattern = "[A-яЁё]\\."; // выводит у. и а.
        pattern = "[A-яЁё.\\[\\]-]";
        pattern = "[^0-9]"; // ^ ищет все символы кроме тех которые указаны после [^.....] т.е. все кроме цифр
        pattern = "[^A-яЁё]"; // ^ ищет все кроме букв
        pattern = "[а-д[к-н]]"; // обьединение двух диапазонов но это равносильно [а-дк-н]
        pattern = "[а-л && [к-н]]"; // пересечение диапазонов и выведет только букву 'л'
        pattern = "[а-л || [к-н]]"; // объединение диапазонов
        pattern = "[а-л && [^к-н]]"; // пересечение диапазонов за ичулючением 'л' и 'м'


        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(s);

        //System.out.println(matcher.find());
        while (matcher.find()) {
            //System.out.println("Шаблон совпадения найден с " + matcher.start()+ " индекса по " + (matcher.end()-1) + " индекс"); //поиск начального и конечного индексов вырвжения совпадения с шаблоном
            //System.out.println(s.substring(matcher.start(),matcher.end())); //поиск совпадения самого вырвжения с шаблоном
            System.out.println(matcher.group()); //тоже метод поиска самого выражения совпадения с шаблоном
        }

        String str = "ЁАЯаиёя";
        for (int i = 0; i < str.length(); i++) {
            System.out.println((str.charAt(i)) + ": " + str.codePointAt(i));
        }
        System.out.println("\n\n");

        //=== 1 ================================ поиск ошибок в шаблоне ==========================================
        try {
            String s1 = "Час в 24 часовом формате от 00 до 23. 2021-05-13T21:45. Минуты в диапазоне от 00 до 59. 2021-05-13T01:09";
            String pattern1;
            pattern1 = "[0-2][0-9]:[0-5][0-9]";

            Pattern regex1 = Pattern.compile(pattern1);
            Matcher matcher1 = regex1.matcher(s1);

            while (matcher1.find()) {
                System.out.println(matcher1.group());
            }
        } catch (PatternSyntaxException pse){
            System.err.println("Неправильное регулярное выражение: " + pse.getMessage());
            System.err.println("Описание ошибки: " + pse.getDescription());
            System.err.println("Позиция ошибки: " + pse.getIndex());
            System.err.println("Неправильный шаблон: " + pse.getPattern());
        }
        System.out.println("\n\n");

        //=== 2 =================================== шаблоны ===============================================
        //String s2 = "Я ищу совпадения в 2024 году. И я их найду в 3 счёта. 198765 Ели[-ели]. Hello_World";
        String s2 = "Цифры: 7, +17, -42, 0013, 0.3";
        String pattern2;
        //pattern2 = "\\d"; //ищет каждую цифру но все цифры аналог [0-9] но не аналог [0-5] (например)
        //pattern2 = "\\s";
        //pattern2 = "\\s\\d";
        pattern2 = "\\w";
        pattern2 = "\\D";
        pattern2 = "\\W";
        pattern2 = "\\AЯ ищу";
        pattern2 = "_World\\Z";
        pattern2 = "\\w+";
        pattern2 = "[+-]?[\\d.]+";

        // \\d  == [0-9] - одна цифра
        // \\s == [ ] - один пробельный символ
        // \\w == [A-Za-z0-9_ ] - буква латинского алфавита без учет регистра, любая цифра и символ подчеркивния
        // \\D  == [^0-9] - все символы кроме цифры
        // \\S  == [^ ] - все символы кроме кроме пробелов
        // \\W == [^A-Za-z0-9_ ] - все кроме символ латинского алфавита без учет регистра, любая цифра и символ подчеркивния
        // \\AЯ ищу  - поиск шаблона с начала строки
        // \\ZЯ ищу  - поиск шаблона с конца строки
        // "+" - повторений от 1 до бесконечности
        // "*" - повторений от 0 до бесконечности
        // "?" - повторений от 0 до 1

        Pattern regex2 = Pattern.compile(pattern2);
        Matcher matcher2 = regex2.matcher(s2);

        while (matcher2.find()) {
            //System.out.print(matcher2.group() + " ");
            //System.out.print(matcher2.group());
            System.out.print(matcher2.group() + "\t\t");
        }
        System.out.println("\n\n");

    //=== 3 ================================ поиск ошибок в шаблоне ==========================================
        String s3 = "autor=Пушкин А.С.; title = Евгений Онегин; price =200; year= 1830";
        //String pattern3 = "\\w+\\s*=\\s*[А-я0-9\\s.]+";
        String pattern3 = "\\w+\\s*=\\s*[^;]+"; // можно и так

        Pattern regex3 = Pattern.compile(pattern3);
        Matcher matcher3 = regex3.matcher(s3);

        while (matcher3.find()) {
            System.out.print(matcher3.group() + "\t\t");
        }
        System.out.println("\n\n");

    //=== 4 ================================ поиск ошибок в шаблоне ==========================================
        String s4 = "12 сентября 2024 года";
        //String pattern4 = "\\d{2}";
        //String pattern4 = "\\d{2,}";
        String pattern4 = "\\d{2,4}";

        // {2} - количество повторенй в цифрах  результат: 12     20     24
        // {3} - количество повторенй в цифрах  результат: 202
        // {4} - количество повторенй в цифрах  результат: 2024
        // {2,} - количество повторенй в цифрах от 2-х повторений и до бесконечности результат: 12     2024
        // {2,4} - количество повторенй в цифрах от 2-х повторений и до 4-повторений включительно  результат: 12     2024

        Pattern regex4 = Pattern.compile(pattern4);
        Matcher matcher4 = regex4.matcher(s4);

        while (matcher4.find()) {
            System.out.print(matcher4.group() + "\t\t");
        }
        System.out.println("\n\n");

        //=== 5 ======================== ПРОВЕРКА ШАБЛОНА ТЕЛЕФОНА ===============================
        String s5 = "+7 499 456 45 78, +74994564578, +7 (499) 456 45 78, 74994564578";
        System.out.print("Тест: "+ s5 + "\n");
        String pattern5 = "\\+?7\\d{10}";
        //String pattern5 = "\\+7\\s\\(\\d+\\)\\s\\d+\\s\\d+\\s\\d+";
        //String pattern5 = "\\+7\\s\\(\\d+\\)[^,]+";

        Pattern regex5 = Pattern.compile(pattern5);
        Matcher matcher5 = regex5.matcher(s5);

        while (matcher5.find()) {
            System.out.print(matcher5.group() + "\t\t");
        }
        System.out.println("\n\n");

        //=== 6 ============================= ПРОВЕРКА ШАБЛОНА  ==========================================
        String s6 = "I learning Java";
        System.out.print(s6 + "\n\n");
        //String pattern6 = "\\w+\\s\\w+";
        //String pattern6 = "^\\w+\\s\\w+"; // ^ - поиск совпадения от начала строки
        //String pattern6 = "\\w+\\s\\w+$"; // $ - поиск совпадения от конца строки
        String pattern6 = "^\\w+\\s\\w+\\s\\w+$";

        Pattern regex6 = Pattern.compile(pattern6);
        Matcher matcher6 = regex6.matcher(s6);

        while (matcher6.find()) {
            //System.out.print(matcher6.group() + "\t\t");
            System.out.print(matcher6.group() + "\n");
        }
        System.out.println("\n\n");

        //=== 7 ============================= ПРОВЕРКА ИМЕНИ ПОЛЬЗОВАТЕЛЯ ==========================================
        //String s7 = "Java_master";
        String s7 = "Java_master-009";
        System.out.print(s7 + "\n\n");
        String pattern7 = "^[\\w-]{3,16}$"; // только буквы латинского алфавита, цифры, символ '_' и '-', количество символов в логине  от 3 до 16

        Pattern regex7 = Pattern.compile(pattern7);
        Matcher matcher7 = regex7.matcher(s7);

        System.out.print(matcher7.find());

        System.out.println("\n\n");

        //=== 8 ============================= ФЛАГИ  ==========================================
        String s8 = "Я ищу совпадения в 2024 году. И я их найду в 3 счёта. Hello_World.";
        System.out.print("Флаги - " + s8 + "\n\n");
        //String pattern8 = "h";
        String pattern8 = "я";

        //Pattern regex8 = Pattern.compile(pattern8, Pattern.CASE_INSENSITIVE ); // Pattern.CASE_INSENSITIVE этот флаг позволяет искать 'h' без учета регистра и возвращает 'H' (работает только с латинским алфавитом)
        Pattern regex8 = Pattern.compile(pattern8, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE); // Pattern.UNICODE_CASE позволяет работать как с латинским так и слюбым другим алфавитом в том числе и с русским а "|"  позволяет включать сразу несколько флагов как логическое "И"
        Matcher matcher8 = regex8.matcher(s8);

        while (matcher8.find()) {
            System.out.print(matcher8.group() + "\t");
        }
        System.out.println("\n\n");

        //=== 9 ============================= ПОИСК С ПОМОЩЬЮ ШАБЛОНА  ==========================================
        Scanner input9 = new Scanner(System.in);
        System.out.print("Введите текст с цифрами: ");
        String s9 = input9.nextLine();
        String pattern9 = "\\d # поиск цифр";

        Pattern regex9 = Pattern.compile(pattern9, Pattern.COMMENTS); // Pattern.COMMENTS позволяет в шаблоне писать комментарий после символа '#' (который ни где не отображается но виден в коде)
        Matcher matcher9 = regex9.matcher(s9);

        int count9 = 0;
        while (matcher9.find()) {
           count9++;
        }
        //System.out.print("Количество цифр во введенном тексте: " + count9);
        //System.out.println("\n\n");
        System.out.println("\n\n");

    }   //================= скобка закрывающая метод  public static void main =====================

//=================================================================================================
//======================================= свои методы =============================================
//=================================================================================================


}  //============================= скобка закрывающая class Main ==================================
