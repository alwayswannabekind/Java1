// При игре больше, чем 3 на 3:
        // Не блокирует возможный победный ход человека, при
            //проверке второй главной диагонали (и диагоналей ниже) в обратную сторону
        // Соответсвенно победа у человека и компьютера в этой ситуации тоже не считается
// При игре 3 на 3 всё ок

import java.util.Random;
import java.util.Scanner;

public class FourthLessonTasksForCheck {

    private static char[][] map;
    private final static int MAP_SIZE = 5;
    private final static int DOTS_COUNT_TO_WIN = 4;
    private final static int DOTS_COUNT_TO_BLOCK = 3;
    private final static char DOT_X = 'X';
    private final static char DOT_O = 'O';
    private final static char DOT_EMPTY = '•';
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        play();
    }

    private static void play() {
        init();
        print();
        while (true) {
            humanTurn();
            print();
            if (checkWin(DOT_X)) {
                System.out.println("YOU WON");
                break;
            }
            if (checkDraw()) {
                System.out.println("DRAW");
                break;
            }
            computerTurn();
            print();
            if (checkComputerWin(DOT_O)) {
                System.out.println("COMPUTER WON");
                break;
            }
            if (checkDraw()) {
                System.out.println("DRAW");
                break;
            }
        }
    }

    private static void humanTurn() {
        int x,y;
        do {
            while (true) {
                System.out.println("Please input dots coordinate in format 'x y'");
                if (scanner.hasNextInt()) {
                    x = scanner.nextInt();
                } else {
                    System.out.println("You input wrong X coordinate format");
                    scanner.nextLine();
                    continue;
                }
                if (scanner.hasNextInt()) {
                    y = scanner.nextInt();
                    break;
                } else {
                    System.out.println("You input wrong Y coordinate format");
                    scanner.nextLine();
                }
            }
        } while (!cellValidation(x, y, DOT_X));
        map[x - 1][y - 1] = DOT_X;
    }

    private static void computerTurn() {

        System.out.println("Computer turn");
        int x = -1,y = -1;

        int dotsInARowCount = 0;
        int dotsInAColumnCount = 0;
        int dotsInADiagonal1Count = 0;
        int dotsInADiagonal2Count = 0;
        boolean isTurn = false;

// Такого задания не было, но я добавил логику, чтобы компьютер выигрывал, если у него есть выигрышный ход.
        // Я просто скопировал код, реализующий блокировку выигрышного хода человека, и поменял в условии dot_x на dot_o.
// Ещё я добавлял обнуление переменных(счётчиков), насколько я знаю, так правильно делать,
        //  но в нашем случае необязательно.
// В процессе понял, что у меня не была реализована блокировка, в случае КОГДА X * X
        // Эту проблему можно было просто убрать, сделав первый ход компа в середину
        // Но это было бы неверно в случае, если для победы надо больше, чем 3 в ряд
// Соответсвенно, выигрыш, в случае КОГДА 0 * 0, тоже не был реализован

// Начало кода для реализации проверки выигрышного хода компа

        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                dotsInARowCount = 0;
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == DOT_O) {
                        dotsInARowCount++;
                        if (dotsInARowCount == DOTS_COUNT_TO_BLOCK) {
                            if (j <= map.length - 2) {
                                if (map[i][j + 1] == DOT_EMPTY) {
                                    x = i;
                                    y = j + 1;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (x != -1 & y !=-1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
        dotsInARowCount = 0;

        if (isTurn == false){
            for (int i = 0; i < map.length; i++) {
                dotsInARowCount = 0;
                for (int j = map.length - 1; j > 0; j--)
                    if (map[i][j] == DOT_O) {
                        dotsInARowCount++;
                        if (dotsInARowCount == DOTS_COUNT_TO_BLOCK) {
                            if (map[i][j - 1] == DOT_EMPTY) {
                                x = i;
                                y = j - 1;
                            }
                        }
                    }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
        dotsInARowCount = 0;

        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == DOT_O) {
                        if (j + 2 <= map.length - 1){
                            if (map[i][j+1] == DOT_EMPTY) {
                                if (map[i][j+2] == DOT_O) {
                                    x = i;
                                    y = j + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

// Теперь надо проверить столбцы
        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                dotsInAColumnCount = 0;
                for (int j = 0; j < map.length; j++) {
                    if (map[j][i] == DOT_O) {
                        dotsInAColumnCount++;
                        if (dotsInAColumnCount == DOTS_COUNT_TO_BLOCK) {
                            if (j <= map.length - 2) {
                                if (map[j + 1][i] == DOT_EMPTY) {
                                    x = j + 1;
                                    y = i;
                                }
                            }
                        }
                    } else dotsInAColumnCount = 0;
                }
            }
        }
        if (x != -1 & y !=-1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
        dotsInAColumnCount = 0;

        if ((isTurn == false) & (x == -1 & y == -1)) {
            for (int i = 0; i < map.length; i++) {
                dotsInAColumnCount = 0;
                for (int j = map.length - 1; j > 0; j--)
                    if (map[j][i] == DOT_O) {
                        dotsInAColumnCount++;
                        if (dotsInAColumnCount == DOTS_COUNT_TO_BLOCK) {
                            if (j - 1 >= 0) {
                                if (map[j-1][i] == DOT_EMPTY) {
                                    x = j - 1;
                                    y = i;
                                }
                            }
                        }
                    } else dotsInAColumnCount = 0;
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
        dotsInAColumnCount = 0;

        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == DOT_O) {
                        if (i + 2 <= map.length - 1){
                            if (map[i+1][j] == DOT_EMPTY) {
                                if (map[i+2][j] == DOT_O) {
                                    x = i + 1;
                                    y = j;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

// Теперь надо проверить первую диагональ
        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][i] == DOT_O) {
                        dotsInADiagonal1Count++;
                        if (dotsInADiagonal1Count == DOTS_COUNT_TO_BLOCK) {
                            if ((i <= map.length - 2) & (j <= map.length - 2)) {
                                if (map[i + 1][i + 1] == DOT_EMPTY) {
                                    x = i + 1;
                                    y = i + 1;
                                }
                            }
                        }
                        break;
                    } else dotsInADiagonal1Count = 0;
                }
            }
            dotsInADiagonal1Count = 0;
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

        if (isTurn == false) {
            for (int i = map.length - 1; i > 0; i--) {
                for (int j = map.length - 1; j > 0; j--) {
                    if (map[i][i] == DOT_O) {
                        dotsInADiagonal1Count++;
                        if (dotsInADiagonal1Count == DOTS_COUNT_TO_BLOCK) {
                            if ((i -1 >= 0) & (j >= 1)) {
                                if (map[i - 1][i - 1] == DOT_EMPTY) {
                                    x = i - 1;
                                    y = i - 1;
                                }
                            }
                        }
                        break;
                    }
                }
            }
            dotsInADiagonal1Count = 0;
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == DOT_O) {
                        if ( (i + 2 <= map.length - 1) & (j + 2 <= map.length - 1) ){
                            if (map[i+1][j+1] == DOT_EMPTY) {
                                if (map[i+2][j+2] == DOT_O) {
                                    x = i + 1;
                                    y = j + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
// Теперь надо проверить вторую диагональ
        if (isTurn == false) {
            dotsInADiagonal2Count = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][map.length - i - 1] == DOT_O) {
                        dotsInADiagonal2Count++;
                        if (dotsInADiagonal2Count == DOTS_COUNT_TO_BLOCK) {
                            if ((i <= map.length - 2) & (j <= map.length - 2)) {
                                if (map[i + 1][map.length - i - 2] == DOT_EMPTY) {
                                    x = i + 1;
                                    y = map.length - i - 2;
                                }
                            }
                        }
                        break;
                    } else dotsInADiagonal2Count = 0;
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

        if (isTurn == false) {
            dotsInADiagonal2Count = 0;
            for (int i = map.length - 1; i > 0; i--) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][map.length - i - 1] == DOT_O) {
                        dotsInADiagonal2Count++;
                        if (dotsInADiagonal2Count == DOTS_COUNT_TO_BLOCK) {
                            if (map[i - 1][map.length - i -1] == DOT_EMPTY) {
                                x = i - 1;
                                y = map.length - i ;
                            }
                        }
                        break;
                    } else dotsInADiagonal2Count = 0;
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
        dotsInADiagonal2Count = 0;

        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][map.length - i - 1] == DOT_O) {
                        if (i + 2 <= map.length - 1) {
                            if (map[i + 1][map.length - i - 2] == DOT_EMPTY) {
                                if (map[i+2][map.length - i - 3] == DOT_O) {
                                    x = i + 1;
                                    y = map.length - i - 2;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

// Конец кода для реализации проверки выигрышного хода компа

// Начало кода для реализации блокировки выигрышного хода человека

//Проверяем ряды, для блокировки возможной победы человека

// 1 - Если победа возможна, то прописываем дополнительную логику,
//      чтобы комп ставил 0, тем самым блокируя возможную победу
// 2 - Здесь так же надо прописать проверку- свободна ли ячейка для блокировки
//          и добавить изменение x & y, для хода, если ячейка свободна
// 3 - Также добавляем дополнительную логику для проверки в обратном направлении
// 4 - прописываем дополнительную логику, чтобы комп ходил для блокировки возможной победы человека
// 5 - Также надо позаботиться о том, чтобы комп не ходил больше 1 раза
// 6 - Надо добавить переменную типа boolean, которая будет передавать true,
//      если ход был совершён. Если же нет, то false
// 7 - Понял, что у меня не были реализованы блокировки, в случае, когда для победы надо 3 в ряд и,
        // КОГДА X * X(и подобных вариантов), добавил их

        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                dotsInARowCount = 0;
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == DOT_X) {
                        dotsInARowCount++;
                        if (dotsInARowCount == DOTS_COUNT_TO_BLOCK) {
                            if (j <= map.length - 2) {
                                if (map[i][j + 1] == DOT_EMPTY) {
                                    x = i;
                                    y = j + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
// Если значения x & y поменялись, компьютер должен сделать ход
// isTurn становится true
// Если значения не поменялись, идёт проверка в обратную сторону
// Такая же проверка - Если значения x & y поменялись, компьютер должен сделать ход
// Если значения опять не поменялись, переходим к проверке ситуации, когда X * X
        if (x != -1 & y !=-1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
        if (isTurn == false){
            for (int i = 0; i < map.length; i++) {
                dotsInARowCount = 0;
                for (int j = map.length - 1; j > 0; j--)
                    if (map[i][j] == DOT_X) {
                        dotsInARowCount++;
                        if (dotsInARowCount == DOTS_COUNT_TO_BLOCK) {
                            if (map[i][j - 1] == DOT_EMPTY) {
                                x = i;
                                y = j - 1;
                            }
                        }
                    }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == DOT_X) {
                        if (j + 2 <= map.length - 1){
                            if (map[i][j+1] == DOT_EMPTY) {
                                if (map[i][j+2] == DOT_X) {
                                    x = i;
                                    y = j + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
// Если значения опять не поменялись, переходим к проверке столбцов

// Теперь надо проверить столбцы
        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                dotsInAColumnCount = 0;
                for (int j = 0; j < map.length; j++) {
                    if (map[j][i] == DOT_X) {
                        dotsInAColumnCount++;
                        if (dotsInAColumnCount == DOTS_COUNT_TO_BLOCK) {
                            if (j <= map.length - 2) {
                                if (map[j + 1][i] == DOT_EMPTY) {
                                    x = j + 1;
                                    y = i;
                                }
                            }
                        }
                    } else dotsInAColumnCount = 0;
                }
            }
        }
        if (x != -1 & y !=-1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

// Проверка столбцов в обратную сторону
        if (isTurn == false){
            for (int i = 0; i < map.length; i++) {
                dotsInAColumnCount = 0;
                for (int j = map.length - 1; j > 0; j--)
                    if (map[j][i] == DOT_X) {
                        dotsInAColumnCount++;
                        if (dotsInAColumnCount == DOTS_COUNT_TO_BLOCK) {
                            if (j - 1 >= 0) {
                                if (map[j-1][i] == DOT_EMPTY) {
                                    x = j - 1;
                                    y = i;
                                }
                            }
                        }
                    } else dotsInAColumnCount = 0;
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

// Проверка ситуации, когда X
//                          *
//                          X
        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == DOT_X) {
                        if (i + 2 <= map.length - 1){
                            if (map[i+1][j] == DOT_EMPTY) {
                                if (map[i+2][j] == DOT_X) {
                                    x = i + 1;
                                    y = j;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

// Проверка первой главной диагонали (и диагоналей выше)
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = 0; i < map.length; i++) {
                    int k = i + j;
                    if (k < map.length) {
                        if (map[i][k] == DOT_X) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_BLOCK) {
                                if ((i <= map.length - 2) & (k <= map.length - 2)) {
                                    if (map[i + 1][k + 1] == DOT_EMPTY) {
                                        x = i + 1;
                                        y = k + 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

//Проверка первой главной диагонали (и диагоналей выше) в обратную сторону
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - (i + j);
                    if (k > - 1) {
                        if (map[i][map.length - 1 - k] == DOT_X) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_BLOCK) {
                                if ((i >= 1) & (k <= map.length - 2)) {
                                    if (map[i - 1][map.length - 2 - k] == DOT_EMPTY) {
                                        x = i - 1;
                                        y = map.length - 2 - k;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

// Проверка первой главной диагонали (и диагоналей ниже)
        if (isTurn == false) {
            for (int j = 1; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = 0; i < map.length; i++) {
                    int k = i + j;
                    if (k < map.length) {
                        if (map[k][i] == DOT_X) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_BLOCK) {
                                if ((i <= map.length - 2) & (k <= map.length - 2)) {
                                    if (map[k + 1][i + 1] == DOT_EMPTY) {
                                        x = k + 1;
                                        y = i + 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

//Проверка первой главной диагонали (и диагоналей ниже) в обратную сторону
        if (isTurn == false) {
            for (int j = 1; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - (i + j);
                    if (k > - 1) {
                        if (map[map.length - 1 - k][i] == DOT_X) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_BLOCK) {
                                if ((i >= 1) & (k <= map.length - 2)) {
                                    if (map[map.length - 2 - k][i - 1] == DOT_EMPTY) {
                                        x = map.length - 2 - k;
                                        y = i - 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
//(ТОЛЬКО ДЛЯ ИГР, КОГДА ДЛЯ ПОБЕДЫ НАДО 3 В РЯД)
// Проверка ситуации, когда
// x
//  *
//   x
        if (DOTS_COUNT_TO_WIN == 3) {
            if (isTurn == false) {
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map.length; j++) {
                        if (map[i][j] == DOT_X) {
                            if ((i + 2 <= map.length - 1) & (j + 2 <= map.length - 1)) {
                                if (map[i + 1][j + 1] == DOT_EMPTY) {
                                    if (map[i + 2][j + 2] == DOT_X) {
                                        x = i + 1;
                                        y = j + 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (x != -1 & y != -1) {
                map[x][y] = DOT_O;
                isTurn = true;
            }
        }
//(ТОЛЬКО ДЛЯ ИГР, КОГДА ДЛЯ ПОБЕДЫ НАДО 3 В РЯД)
// Проверка ситуации, когда
//   x
//  *
// x
        if (DOTS_COUNT_TO_WIN == 3) {
            if (isTurn == false) {
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map.length; j++) {
                        if (map[i][j] == DOT_X) {
                            if ((i - 2 >= 0) & (j + 2 <= map.length - 1)) {
                                if (map[i - 1][j + 1] == DOT_EMPTY) {
                                    if (map[i - 2][j + 2] == DOT_X) {
                                        x = i - 1;
                                        y = j + 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (x != -1 & y != -1) {
                map[x][y] = DOT_O;
                isTurn = true;
            }
        }

//  Проверка второй главной диагонали (и диагоналей выше)
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - i - j;
                    if (k > - 1){
                        if (map[i][k] == DOT_X) {
                            dotsInADiagonal2Count++;
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_BLOCK) {
                                if ((i >= 1) & (k <= map.length - 2)) {
                                    if (map[i - 1][k + 1] == DOT_EMPTY) {
                                        x = i - 1;
                                        y = k + 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

//  Проверка второй главной диагонали (и диагоналей выше) в обратную сторону
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = 0; i < map.length; i++) {
                    int k = map.length - 1 - i - j;
                    if (k > - 1){
                        if (map[i][k] == DOT_X) {
                            dotsInADiagonal2Count++;
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_BLOCK) {
                                if ((i <= map.length - 2) & (k >= 1)) {
                                    if (map[i + 1][k - 1] == DOT_EMPTY) {
                                        x = i + 1;
                                        y = k - 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

//  Проверка второй главной диагонали (и диагоналей ниже)
        if (isTurn == false)  {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - i + j;
                    if (k < map.length) {
                        if (map[i][k] == DOT_X) {
                            dotsInADiagonal2Count++;
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_BLOCK) {
                                if (map[i - 1][k+1] == DOT_EMPTY) {
                                    x = i - 1;
                                    y = k + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
/*
//  Проверка второй главной диагонали (и диагоналей ниже) в обратную сторону - Пока не работает
        if (isTurn == false)  {
            for (int j = map.length - 1; j > 0; j--) {
                dotsInADiagonal2Count = 0;
                for (int i = 0; i < map.length; j++) {
                    int k = j - i;
                    if (k < map.length) {
                        if (map[k][i] == DOT_X) {
                            dotsInADiagonal2Count++;
                            System.out.println("dotsInADiagonal2Count: " + dotsInADiagonal2Count);
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_BLOCK) {
                                if ((i <= map.length - 2) & (k <= map.length - 2)) {
                                    if (map[k + 1][i + 1] == DOT_EMPTY) {
                                        x = k + 1;
                                        y = i + 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
*/

//Последняя проверка, если до сих пор isTurn = false, компьютер ставит 0 в рандомную ячейку
        if (isTurn == false) {
            do {
                x = random.nextInt(MAP_SIZE);
                y = random.nextInt(MAP_SIZE);
            } while (!cellValidation(x + 1, y + 1, DOT_O));
            map[x][y] = DOT_O;
        }
    }

        private static boolean checkWin(char dot) {

        int dotsInARowCount = 0;
        int dotsInAColumnCount = 0;
        int dotsInADiagonal1Count = 0;
        int dotsInADiagonal2Count = 0;

//Проверяем столбцы

        for (int i = 0; i < map.length; i++) {
            dotsInAColumnCount =0;
            for (int j = 0; j < map.length; j++) {
                if (map[j][i] == DOT_X) {
                    dotsInAColumnCount++;
                    if (dotsInAColumnCount == DOTS_COUNT_TO_WIN) return true;
                } else dotsInAColumnCount =0;
            }
        }

//Проверяем ряды

        for (int i = 0; i < map.length; i++) {
            dotsInARowCount =0;
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == DOT_X) {
                    dotsInARowCount++;
                    if (dotsInARowCount == DOTS_COUNT_TO_WIN) return true;
                } else dotsInARowCount =0;
            }
        }

//Проверяем диагонали

// Проверка первой главной диагонали (и диагоналей выше)
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = 0; i < map.length; i++) {
                    int k = i + j;
                    if (k < map.length) {
                        if (map[i][k] == DOT_X) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN) return true;
                        }
                    }
                }
            }

//Проверка первой главной диагонали (и диагоналей выше) в обратную сторону
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - (i + j);
                    if (k > -1) {
                        if (map[i][map.length - 1 - k] == DOT_X) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN) return true;
                        }
                    }
                }
            }
// Проверка первой главной диагонали (и диагоналей ниже)
            for (int j = 1; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = 0; i < map.length; i++) {
                    int k = i + j;
                    if (k < map.length) {
                        if (map[k][i] == DOT_X) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN) return true;
                        }
                    }
                }
            }

//Проверка первой главной диагонали (и диагоналей ниже) в обратную сторону
            for (int j = 1; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - (i + j);
                    if (k > - 1) {
                        if (map[map.length - 1 - k][i] == DOT_X) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN) return true;
                        }
                    }
                }
            }

//  Проверка второй главной диагонали (и диагоналей выше)
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - i - j;
                    if (k > - 1){
                        if (map[i][k] == DOT_X) {
                            dotsInADiagonal2Count++;
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                        }
                    }
                }
            }
//  Проверка второй главной диагонали (и диагоналей выше) в обратную сторону
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = 0; i < map.length; i++) {
                    int k = map.length - 1 - i - j;
                    if (k > - 1){
                        if (map[i][k] == DOT_X) {
                            dotsInADiagonal2Count++;
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                        }
                    }
                }
            }
//  Проверка второй главной диагонали (и диагоналей ниже)
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - i + j;
                    if (k < map.length) {
                        if (map[i][k] == DOT_X) {
                            dotsInADiagonal2Count++;
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                        }
                    }
                }
            }
//  Проверка второй главной диагонали (и диагоналей ниже)
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - i + j;
                    if (k < map.length) {
                        if (map[i][k] == DOT_X) {
                            dotsInADiagonal2Count++;
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                        }
                    }
                }
            }
//  Проверка второй главной диагонали (и диагоналей ниже) в обратную сторону - НЕ РАБОТАЕТ
            for (int j = map.length - 1; j > 0; j--) {
                dotsInADiagonal2Count = 0;
                for (int i = 0; i < map.length; j++) {
                    int k = j - i;
                    if (k < map.length) {
                        if (map[k][i] == DOT_X) {
                            dotsInADiagonal2Count++;
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                        }
                    }
                }
            }
    return false;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkComputerWin(char dot) {

        int dotsInARowCount = 0;
        int dotsInAColumnCount = 0;
        int dotsInADiagonal1Count = 0;
        int dotsInADiagonal2Count = 0;

//Проверяем столбцы

        for (int i = 0; i < map.length; i++) {
            dotsInAColumnCount =0;
            for (int j = 0; j < map.length; j++) {
                if (map[j][i] == DOT_O) {
                    dotsInAColumnCount++;
                    if (dotsInAColumnCount == DOTS_COUNT_TO_WIN) return true;
                } else dotsInAColumnCount =0;
            }
        }

//Проверяем ряды

        for (int i = 0; i < map.length; i++) {
            dotsInARowCount =0;
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == DOT_O) {
                    dotsInARowCount++;
                    if (dotsInARowCount == DOTS_COUNT_TO_WIN) return true;
                } else dotsInARowCount =0;
            }
        }

//Проверяем диагонали

// Проверка первой главной диагонали (и диагоналей выше)
        for (int j = 0; j < map.length; j++) {
            dotsInADiagonal1Count = 0;
            for (int i = 0; i < map.length; i++) {
                int k = i + j;
                if (k < map.length) {
                    if (map[i][k] == DOT_O) {
                        dotsInADiagonal1Count++;
                        if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN) return true;
                    }
                }
            }
        }

//Проверка первой главной диагонали (и диагоналей выше) в обратную сторону
        for (int j = 0; j < map.length; j++) {
            dotsInADiagonal1Count = 0;
            for (int i = map.length - 1; i > 0; i--) {
                int k = map.length - 1 - (i + j);
                if (k > -1) {
                    if (map[i][map.length - 1 - k] == DOT_O) {
                        dotsInADiagonal1Count++;
                        if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN) return true;
                    }
                }
            }
        }
// Проверка первой главной диагонали (и диагоналей ниже)
        for (int j = 1; j < map.length; j++) {
            dotsInADiagonal1Count = 0;
            for (int i = 0; i < map.length; i++) {
                int k = i + j;
                if (k < map.length) {
                    if (map[k][i] == DOT_O) {
                        dotsInADiagonal1Count++;
                        if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN) return true;
                    }
                }
            }
        }

//Проверка первой главной диагонали (и диагоналей ниже) в обратную сторону
        for (int j = 1; j < map.length; j++) {
            dotsInADiagonal1Count = 0;
            for (int i = map.length - 1; i > 0; i--) {
                int k = map.length - 1 - (i + j);
                if (k > - 1) {
                    if (map[map.length - 1 - k][i] == DOT_O) {
                        dotsInADiagonal1Count++;
                        if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN) return true;
                    }
                }
            }
        }

//  Проверка второй главной диагонали (и диагоналей выше)
        for (int j = 0; j < map.length; j++) {
            dotsInADiagonal2Count = 0;
            for (int i = map.length - 1; i > 0; i--) {
                int k = map.length - 1 - i - j;
                if (k > - 1){
                    if (map[i][k] == DOT_O) {
                        dotsInADiagonal2Count++;
                        if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                    }
                }
            }
        }
//  Проверка второй главной диагонали (и диагоналей выше) в обратную сторону
        for (int j = 0; j < map.length; j++) {
            dotsInADiagonal2Count = 0;
            for (int i = 0; i < map.length; i++) {
                int k = map.length - 1 - i - j;
                if (k > - 1){
                    if (map[i][k] == DOT_O) {
                        dotsInADiagonal2Count++;
                        if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                    }
                }
            }
        }
//  Проверка второй главной диагонали (и диагоналей ниже)
        for (int j = 0; j < map.length; j++) {
            dotsInADiagonal2Count = 0;
            for (int i = map.length - 1; i > 0; i--) {
                int k = map.length - 1 - i + j;
                if (k < map.length) {
                    if (map[i][k] == DOT_O) {
                        dotsInADiagonal2Count++;
                        if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                    }
                }
            }
        }
//  Проверка второй главной диагонали (и диагоналей ниже)
        for (int j = 0; j < map.length; j++) {
            dotsInADiagonal2Count = 0;
            for (int i = map.length - 1; i > 0; i--) {
                int k = map.length - 1 - i + j;
                if (k < map.length) {
                    if (map[i][k] == DOT_O) {
                        dotsInADiagonal2Count++;
                        if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                    }
                }
            }
        }
//  Проверка второй главной диагонали (и диагоналей ниже) в обратную сторону - НЕ РАБОТАЕТ
        for (int j = map.length - 1; j > 0; j--) {
            dotsInADiagonal2Count = 0;
            for (int i = 0; i < map.length; j++) {
                int k = j - i;
                if (k < map.length) {
                    if (map[k][i] == DOT_O) {
                        dotsInADiagonal2Count++;
                        if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean cellValidation(int x, int y, char dot) {
        if (x < 1 || x > MAP_SIZE || y < 1 || y > MAP_SIZE) {
            System.out.println("Exit map sizes");
            return false;
        }
        boolean check = map[x - 1][y - 1] == DOT_EMPTY;

        if (check) {
            return check;
        } else {
            if (dot == DOT_X) {
                System.out.println("Cell is Busy");
            }
            return false;
        }
    }

    private static void print() {
        for (int i = 0; i < map.length + 1; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print((i) + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void init() {
        map = new char[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
