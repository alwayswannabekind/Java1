public class ThirdLessonTasks {
    public static void main(String[] args) {
// Первое задание
        System.out.println("Первое задание: ");
        //Вводим массив
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) arr[i] = 1;
            else arr[i] = 0;
            System.out.print(arr[i]);
        }
// Второе задание
        System.out.println();
        System.out.println("Второе задание: ");
        int[] arr2 = new int[100];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i + 1;
            System.out.print(arr2[i]);
        }
// Третье задание
        System.out.println();
        System.out.println("Третье задание: ");
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) arr3[i] = arr3[i] * 2;
            System.out.print(arr3[i]);
        }
// Четвёртое задание
        System.out.println();
        System.out.println("Четвёртое задание: ");
        int[][] arr4 = new int[4][4];
        for (int i = 0; i < arr4.length; i++) {
            for (int j = 0; j < arr4.length; j++) {
                if (i == j) arr4[i][j] = 1;
                if (j == arr4.length - i - 1) arr4[i][j] = 1;
                System.out.print(arr4[i][j]);
                if (j == arr4.length - 1) System.out.println();
            }
            System.out.println();
        }
// Пятое задание
        System.out.println("Пятое задание: ");
        fifthTask(3, 88);
        System.out.println();
// Шестое задание
        System.out.println("Шестое задание: ");
        int[] arr6 = {1, 15, 2, 8, 99};
        int min = arr6[0], max = arr6[0];
        for (int i = 0; i < arr6.length; i++) {
            if (min > arr6[i]) min = arr6[i];
            if (max < arr6[i]) max = arr6[i];
        }
        System.out.println(min + " " + max);
// Седьмое задание
        System.out.println("Седьмое задание: ");
        int[] arr7 = {1, 2, 3, 0};
        System.out.print(seventhTask(arr7));
// Восьмое задание
        System.out.println();
        System.out.println("Восьмое задание: ");
        int[] arr8 = {0, 1, 2, 3, 4};
        eighthTask(-1, arr8);

    }

    public static int[] fifthTask(int length, int initialValue) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = initialValue;
            System.out.print(arr[i]);
        }
        return arr;
    }

    public static boolean seventhTask(int[] arr7) {
        boolean balance = false;
        int leftSum = 0, rightSum = 0, sum = 0;
        for (int i = 0; i < arr7.length; i++) {
            sum += arr7[i];
        }

        for (int i = 0; i < arr7.length; i++) {
            leftSum += arr7[i];
            rightSum = sum - leftSum;
            if (leftSum == rightSum) balance = true;
        }
        return balance;
    }

    public static void eighthTask(int n, int[] arr8) {
        //Чтобы немного оптимизировать:
        n = n % arr8.length;

        //Чтобы нагляднее продемонстрировать работу метода
        System.out.println("Изначальный массив ");
        for (int i = 0; i < arr8.length; i++) {
            System.out.print(arr8[i]);
        }

        //Code
        int max = arr8.length - 1;

        //Очевидно, что при n =0, массив не должен сдвигаться
        if (n == 0) {
            System.out.println();
            System.out.println("Конечный массив ");
            for (int i = 0; i < arr8.length; i++) {
                System.out.print(arr8[i]);
            }
        }
        //Что здесь происходит?
        //Изначально я написал цикл, который работает только при n = 1, что означает, что метод сдвигает массив на 1.
        //Причём, на тот момент, только вправо.
        //Далее, я создал цикл, который вызывает этот самый цикл n раз.
        //Получилось сдвигать массив, на любое n.
        //Далее, чтобы метод мог осуществлять сдвиг массива влево, я скопировал цикл для сдвига вправо,
        //  немного его поменял. И всё получилось, теперь всё работает)

        if (n > 0) {
            for (int i = 0; i < n; i++) {
                int temp = arr8[max];
                for (int j = max; j > 0; j--) {
                    arr8[j] = arr8[j - 1];
                }
                arr8[0] = temp;
            }
            System.out.println();
            System.out.println("Конечный массив ");
            for (int i = 0; i < arr8.length; i++) {
                System.out.print(arr8[i]);
            }
        }

        if (n < 0) {
            for (int i = 0; i < (-n); i++) {
                int temp = arr8[0];
                for (int j = 0; j < max; j++) {
                    arr8[j] = arr8[j + 1];
                }
                arr8[max] = temp;
            }
            System.out.println();
            System.out.println("Конечный массив ");
            for (int i = 0; i < arr8.length; i++) {
                System.out.print(arr8[i]);
            }
        }
    }
}
