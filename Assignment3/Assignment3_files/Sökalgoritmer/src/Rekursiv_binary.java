public class Rekursiv_binary {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int key = 1;
        int min = 0;
        int max = arr.length - 1;

        System.out.println(recursive(arr, key, min, max));
    }

    public static boolean recursive(int[] arr, int key, int first, int last) {

        int mid = first + ((last - first) / 2);

        if (arr[mid] == key) {
            return true;
        }
        if ((arr[mid] > key) && (first < mid)) {
            last = mid - 1;
            return recursive(arr, key, first, last);
        }
        if ((arr[mid] < key) && (mid < last)) {
            first = mid + 1;
            return recursive(arr, key, first, last);
        }
        return false;
    };
}