// 1. Реализовать алгоритм сортировки слиянием (НЕОБЯЗАТЕЛЬНОЕ)

import java.util.Arrays;
import java.util.Random;

public class T1 {
    public static void main(String[] args) {
        Random rnd = new Random();
        int N = rnd.nextInt(4, 20);
        int[] intArray = GetArray(N);
        System.out.print("До сортировки: ");
        PrintArray(intArray);
        System.out.println();
        System.out.print("После сортировки: ");
        int[] sortArray = SortArray(intArray);
        PrintArray(sortArray);

    }

    public static int[] GetArray(int N) {
        Random rnd = new Random();
        int[] intArray = new int[N];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = rnd.nextInt(20);
        }
        return intArray;
    }

    public static void PrintArray(int[] intArray) {
        for (int i = 0; i < intArray.length; i++) {
            System.out.printf("%d ", intArray[i]);
        }
    }

    public static int[] SortArray(int[] intArray) {

        int[] buffer1 = Arrays.copyOf(intArray, intArray.length);

        int[] buffer2 = new int[intArray.length];
        int[] sortArray = SortInner(buffer1, buffer2, 0, intArray.length);
        return sortArray;
    }

    public static int[] SortInner(int[] buffer1, int[] buffer2, int start, int end) {

        if (start >= end - 1) {
            return buffer1;
        }

        int middle = start + (end - start) / 2;

        int[] firstSorted = SortInner(buffer1, buffer2, start, middle);
        int[] secondSorted = SortInner(buffer1, buffer2, middle, end);

        int firstIndex = start;
        int secondIndex = middle;
        int destIndex = start;
        int[] result = new int[1];
        if (firstSorted == buffer1) {
            result = buffer2;
        } else {
            result = buffer1;
        }

        while (firstIndex < middle && secondIndex < end) {
            if (firstSorted[firstIndex] < secondSorted[secondIndex]) {
                result[destIndex++] = firstSorted[firstIndex++];
            } else {
                result[destIndex++] = secondSorted[secondIndex++];
            }

        }
        while (firstIndex < middle) {
            result[destIndex++] = firstSorted[firstIndex++];
        }
        while (secondIndex < end) {
            result[destIndex++] = secondSorted[secondIndex++];
        }
        return result;
    }
}