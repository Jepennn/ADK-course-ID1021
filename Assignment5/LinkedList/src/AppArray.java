// Purpose: AppArray class to append two arrays

public class AppArray {

    public static void main(String[] args) {

        int[] a = { 1, 2, 3, 4, 5 };
        int[] b = { 6, 7, 8, 9, 10 };

        int[] aq = append(a, b);

        for (int x : aq) {
            System.out.println(x);
        }
    }

    public static int[] append(int[] a, int[] b) {

        int totLength = a.length + b.length;

        int[] aquired = new int[totLength];

        for (int i = 0; i < a.length; i++) {
            aquired[i] = a[i];
        }

        int j = 0;
        for (int i = (totLength - a.length); i < totLength; i++) {
            aquired[i] = b[j];
            j++;
        }

        return aquired;
    }

}