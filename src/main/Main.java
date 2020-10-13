package main;

import application.Application;

import java.io.IOException;

//1.7.4
// 	Адресність	Коментар
//	1-адреса	1-й операнд завжди в акумуляторі, результат команди заноситься в акумулятор
//  Бітність регістрів/стеку (слово процесора) та операндів команд:
//  24-бітні
//Кожний варіант має спільні команди(у) (мнемоніку команд виберіть самостійно):
//      занесення в регістр (акумулятор, стек) даного, яке може бути
//          чи літералом у команді, чи адресою в пам’яті чи регістром;
//      Арифметичний зсув вліво та вправо.
public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        try {
            application.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
