// потратил кучу времени, чтобы разобраться с проверкой второй главной диагонали (и диагоналей ниже)
// но так и не смог, поэтому реализация выигрыша компа и блокировка выигрышного хода человека не работают в данном диапозоне
// так же надо было добавить логику для блока выигрышного хода человека (и чтобы комп мог видеть эту ситуацию, как выигрушную для себя), когда пропущена ячейка в ряду
    // например: x * x
// при игре 3 на 3 всё работает, так как нет побочных диагоналей, однако не успел добавить логику, как написал выше
import java.util.Random;
import java.util.Scanner;

public class FourthLessonTasks {

    private static char[][] map;
    private final static int MAP_SIZE = 3;
    private final static int DOTS_COUNT_TO_WIN = 3;
    private final static int DOTS_COUNT_TO_BLOCK = 2;
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
            if (checkHumanWin(DOT_X)) {
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
        int x, y;
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
        int x = -1, y = -1;

        int dotsInARowCount = 0;
        int dotsInAColumnCount = 0;
        int dotsInADiagonal1Count = 0;
        int dotsInADiagonal2Count = 0;
        boolean isTurn = false;

// Начало кода для реализации проверки выигрышного хода компа
        // Здесь вместо (DOTS_COUNT_TO_WIN - 1) можно было указать DOTS_COUNT_TO_BLOCK
        // Однако, если бы DOTS_COUNT_TO_WIN - 1 > DOTS_COUNT_TO_BLOCK, были бы проблемы
        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                dotsInARowCount = 0;
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == DOT_O) {
                        dotsInARowCount++;
                        if (dotsInARowCount == (DOTS_COUNT_TO_WIN - 1) ) {
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
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                dotsInARowCount = 0;
                for (int j = map.length - 1; j > 0; j--)
                    if (map[i][j] == DOT_O) {
                        dotsInARowCount++;
                        if (dotsInARowCount == (DOTS_COUNT_TO_WIN - 1) ) {
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
                dotsInAColumnCount = 0;
                for (int j = 0; j < map.length; j++) {
                    if (map[j][i] == DOT_O) {
                        dotsInAColumnCount++;
                        if (dotsInAColumnCount == (DOTS_COUNT_TO_WIN - 1) ) {
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
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                dotsInAColumnCount = 0;
                for (int j = map.length - 1; j > 0; j--)
                    if (map[j][i] == DOT_O) {
                        dotsInAColumnCount++;
                        if (dotsInAColumnCount == (DOTS_COUNT_TO_WIN - 1) ) {
                            if (j - 1 >= 0) {
                                if (map[j - 1][i] == DOT_EMPTY) {
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

// Проверка первой диагонали на возможный выигрыш компа
        // Проверка первой главной диагонали (и диагоналей выше) на возможный выигрыш компа
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = 0; i < map.length; i++) {
                    int k = i + j;
                    if (k < map.length) {
                        if (map[i][k] == DOT_O) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN - 1) {
                                if ((i + 1 <= map.length - 1) & (k + 1 <= map.length - 1) & (map[i + 1][k + 1] == DOT_EMPTY)) {
                                    x = i + 1;
                                    y = k + 1;
                                }
                            }
                        } else dotsInADiagonal1Count = 0;
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

        // Проверка первой главной диагонали (и диагоналей выше) на возможный выигрыш компа в обратную сторону
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - (i + j);
                    if (k > -1) {
                        if (map[i][map.length - 1 - k] == DOT_O) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN - 1) {
                                if ((i >= 1) & (k <= map.length - 2)) {
                                    if (map[i - 1][map.length - 2 - k] == DOT_EMPTY) {
                                        x = i - 1;
                                        y = map.length - 2 - k;
                                    }
                                }
                            }
                        } else dotsInADiagonal1Count = 0;
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }

        // Проверка первой главной диагонали (и диагоналей ниже) на возможный выигрыш компа
        if (isTurn == false) {
            for (int j = 1; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = 0; i < map.length; i++) {
                    int k = i + j;
                    if (k < map.length) {
                        if (map[k][i] == DOT_O) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN - 1) {
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

        // Проверка первой главной диагонали (и диагоналей ниже) на возможный выигрыш компа в обратную сторону
        if (isTurn == false) {
            for (int j = 1; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - (i + j);
                    if (k > -1) {
                        if (map[map.length - 1 - k][i] == DOT_O) {
                            dotsInADiagonal1Count++;
                            if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN - 1) {
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

        // Проверка второй главной диагонали (и диагоналей выше) на возможный выигрыш компа
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - i - j;
                    if (k > -1) {
                        if (map[i][k] == DOT_O) {
                            dotsInADiagonal2Count++;
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN - 1) {
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

        // Проверка второй главной диагонали (и диагоналей выше) на возможный выигрыш компа в обратную сторону
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = 0; i < map.length; i++) {
                    int k = map.length - 1 - i - j;
                    if (k > -1) {
                        if (map[i][k] == DOT_O) {
                            dotsInADiagonal2Count++;
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN -1) {
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
        // Проверка второй главной диагонали (и диагоналей ниже) на возможный выигрыш компа
        // Проверка второй главной диагонали (и диагоналей ниже) на возможный выигрыш компа в обратную сторону

// Конец кода для реализации проверки выигрышного хода компа
// Начало кода для реализации блокировки выигрышного хода человека
        // Проверка рядов для реализации блокировки выигрышного хода человека
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
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
        // Проверка рядов для реализации блокировки выигрышного хода человека в обратную сторону
        if (isTurn == false) {
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
        // Проверка столбцов для реализации блокировки выигрышного хода человека
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
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
        // Проверка столбцов для реализации блокировки выигрышного хода человека в обратную сторону
        if (isTurn == false) {
            for (int i = 0; i < map.length; i++) {
                dotsInAColumnCount = 0;
                for (int j = map.length - 1; j > 0; j--)
                    if (map[j][i] == DOT_X) {
                        dotsInAColumnCount++;
                        if (dotsInAColumnCount == DOTS_COUNT_TO_BLOCK) {
                            if (j - 1 >= 0) {
                                if (map[j - 1][i] == DOT_EMPTY) {
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
// Проверка первой главной диагонали (и диагоналей выше) для реализации блокировки выигрышного хода человека
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
                        } else dotsInADiagonal1Count = 0;
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
// Проверка первой главной диагонали (и диагоналей выше) для реализации блокировки выигрышного хода человека в обратную сторону
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - (i + j);
                    if (k > -1) {
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
                        } else dotsInADiagonal1Count = 0;
                    }
                }
            }
        }
        if (x != -1 & y != -1) {
            map[x][y] = DOT_O;
            isTurn = true;
        }
// Проверка первой главной диагонали (и диагоналей ниже) для реализации блокировки выигрышного хода человека
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
//Проверка первой главной диагонали (и диагоналей ниже)для реализации блокировки выигрышного хода человека в обратную сторону
        if (isTurn == false) {
            for (int j = 1; j < map.length; j++) {
                dotsInADiagonal1Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - (i + j);
                    if (k > -1) {
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
//  Проверка второй главной диагонали (и диагоналей выше) для реализации блокировки выигрышного хода человека
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - i - j;
                    if (k > -1) {
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
//  Проверка второй главной диагонали (и диагоналей выше) для реализации блокировки выигрышного хода человека в обратную сторону
        if (isTurn == false) {
            for (int j = 0; j < map.length; j++) {
                dotsInADiagonal2Count = 0;
                for (int i = 0; i < map.length; i++) {
                    int k = map.length - 1 - i - j;
                    if (k > -1) {
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
/*
//  Проверка второй главной диагонали (и диагоналей ниже) для реализации блокировки выигрышного хода человека
        if (isTurn == false) {
            for (int j = 1; j < map.length; j++) {
                for (int i = map.length - 1; i > 0; i--) {
                    int k = map.length - 1 - (i + j);
                    if (k < map.length - 1) {
                        if (map[i][map.length - 1 - k] == DOT_X) {
                            dotsInADiagonal2Count++;
                            System.out.println("dotsInADiagonal2Count: " + dotsInADiagonal2Count);
                            if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN - 1) {
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
        }*/

//Последняя проверка, если до сих пор isTurn = false, компьютер ставит 0 в рандомную ячейку
        if (isTurn == false) {
            do {
                x = random.nextInt(MAP_SIZE);
                y = random.nextInt(MAP_SIZE);
            } while (!cellValidation(x + 1, y + 1, DOT_O));
            map[x][y] = DOT_O;
        }
    }

    private static boolean checkHumanWin(char dot) {

        int dotsInARowCount = 0;
        int dotsInAColumnCount = 0;
        int dotsInADiagonal1Count = 0;
        int dotsInADiagonal2Count = 0;

//Проверка столбцов

        for (int i = 0; i < map.length; i++) {
            dotsInAColumnCount = 0;
            for (int j = 0; j < map.length; j++) {
                if (map[j][i] == DOT_X) {
                    dotsInAColumnCount++;
                    if (dotsInAColumnCount == DOTS_COUNT_TO_WIN) return true;
                } else dotsInAColumnCount = 0;
            }
        }

//Проверка рядов

        for (int i = 0; i < map.length; i++) {
            dotsInARowCount = 0;
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == DOT_X) {
                    dotsInARowCount++;
                    if (dotsInARowCount == DOTS_COUNT_TO_WIN) return true;
                } else dotsInARowCount = 0;
            }
        }

//Проверка диагоналей

// Проверка первой главной диагонали (и диагоналей выше)
        for (int j = 0; j < map.length; j++) {
            dotsInADiagonal1Count = 0;
            for (int i = 0; i < map.length; i++) {
                int k = i + j;
                if (k < map.length) {
                    if (map[i][k] == DOT_X) {
                        dotsInADiagonal1Count++;
                        if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN) return true;
                    } else dotsInADiagonal1Count = 0;
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
                    } else dotsInADiagonal1Count = 0;
                }
            }
        }

//  Проверка второй главной диагонали (и диагоналей выше)
        for (int j = 0; j < map.length; j++) {
            dotsInADiagonal2Count = 0;
            for (int i = map.length - 1; i > 0; i--) {
                int k = map.length - 1 - i - j;
                if (k > -1) {
                    if (map[i][k] == DOT_X) {
                        dotsInADiagonal2Count++;
                        if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                    } else dotsInADiagonal2Count = 0;
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
                    } else dotsInADiagonal2Count = 0;
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

//Проверка столбцов

        for (int i = 0; i < map.length; i++) {
            dotsInAColumnCount = 0;
            for (int j = 0; j < map.length; j++) {
                if (map[j][i] == DOT_O) {
                    dotsInAColumnCount++;
                    if (dotsInAColumnCount == DOTS_COUNT_TO_WIN) return true;
                } else dotsInAColumnCount = 0;
            }
        }

//Проверка рядов

        for (int i = 0; i < map.length; i++) {
            dotsInARowCount = 0;
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == DOT_O) {
                    dotsInARowCount++;
                    if (dotsInARowCount == DOTS_COUNT_TO_WIN) return true;
                } else dotsInARowCount = 0;
            }
        }

//Проверка диагоналей

// Проверка первой главной диагонали (и диагоналей выше)
        for (int j = 0; j < map.length; j++) {
            dotsInADiagonal1Count = 0;
            for (int i = 0; i < map.length; i++) {
                int k = i + j;
                if (k < map.length) {
                    if (map[i][k] == DOT_O) {
                        dotsInADiagonal1Count++;
                        if (dotsInADiagonal1Count == DOTS_COUNT_TO_WIN) return true;
                    } dotsInADiagonal1Count = 0;
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
                    } dotsInADiagonal1Count = 0;
                }
            }
        }

//  Проверка второй главной диагонали (и диагоналей выше)
        for (int j = 0; j < map.length; j++) {
            dotsInADiagonal2Count = 0;
            for (int i = map.length - 1; i > 0; i--) {
                int k = map.length - 1 - i - j;
                if (k > -1) {
                    if (map[i][k] == DOT_O) {
                        dotsInADiagonal2Count++;
                        if (dotsInADiagonal2Count == DOTS_COUNT_TO_WIN) return true;
                    } dotsInADiagonal2Count = 0;
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
                    } dotsInADiagonal2Count = 0;
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
}
