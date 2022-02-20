public class HomeWorkApp2 {

    public static void main(String[] args){

        System.out.println( from10to20(7,10) );
        evenOrOdd(-20);
        System.out.println( evenOrOdd2(-15) );
        printLine("Привет, хорошего тебе дня!)", 5);
        System.out.println( isLeapYear(400) );

    }

    static boolean from10to20(int x, int y){
        if ((x +  y >= 10) && (x +  y <= 20)) {
            return true;
        }
        else {
            return false;
        }
    }

    static void evenOrOdd(int x) {
        if (x >= 0) {
            System.out.println("Число положительное");
        }
        else {
            System.out.println("Число отрицательное");
        }
    }

    static String evenOrOdd2(int x) {
        if (x >= 0) {
            return "false";
        }
        else {
            return "true";
        }
    }

    public static void printLine(String line, int x  ) {
        int i;
        for (i = 0; i < (x-1); i++) {
            System.out.println(line);
        }
    }

    public static boolean isLeapYear(int x) {
        //Любой год, который делится на 4 без остатка
        //Год, который делится без остатка на 100 является високосным годом только в том случае, если он также без остатка делится на 400
        if ( (x % 4 == 0) && (x % 100 != 0) ) {
            return true;
        } else if ( (x % 4 == 0) && (x % 100 == 0) ) {
            if (x % 400 == 0){
                return true;
            }
            else return false;
        } else {
            return false;
        }
    }
}


