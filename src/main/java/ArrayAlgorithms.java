import java.util.Arrays;

public class ArrayAlgorithms {

    int[] array;
    int[] missing_arr;
    String palindrome_to_check;

    public ArrayAlgorithms() {
        array = new int[]{1, 0, 0, 2, 0, 3, 4, 5, 6, 5, 0, 0, 0, 0, 1, 3, 0, 1};
    }
    public static void main(String[] args) {

        ArrayAlgorithms arr = new ArrayAlgorithms();

        //Reversing algorithm
        arr.reverse(arr.array);
        //Minimum of an array
        arr.minimum(arr.array);
        //Second maximum of an array
        arr.second_max(arr.array);
        //Moving zeroes of an array to end
        arr.zeroes_to_end(arr.array);
        arr.reset_array();
        arr.zeroes_to_end_optimal(arr.array);
        arr.reset_array();
        //Resizing an array
        arr.resize_array(arr.array,24);
        //Finding missing number
        arr.find_missing_number(arr.missing_arr);
        //Check if palindrome
        arr.check_if_palindrome(arr.palindrome_to_check.toCharArray());
        arr.check_if_palindrome_alternate(arr.palindrome_to_check.toCharArray());

    }

    public void reset_array() {
        this.array = new int[]{1, 0, 0, 2, 0, 3, 4, 5, 6, 0, 0, 0, 0, 1, 3, 0, 1};
        this.missing_arr = new int[]{1,2,3,4,7,8,6};
        this.palindrome_to_check = new String("madam");
    }

    public void reverse(int[] arr) {

        System.out.print("Original array: " + Arrays.toString(arr) + "\n");

        for (int i=0; i < arr.length/2; i++) {
            int temp = arr[arr.length-i-1];
            arr[arr.length-i-1] = arr[i];
            arr[i] = temp;
        }
        System.out.print("Reversed array: " + Arrays.toString(arr)  + "\n");
    }

    public void minimum(int[] arr) {
        int min = arr[0];
        for (int i=0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.print("Array minimum is " + String.valueOf(min) + "\n");
    }

    //Single pass solution
    //Can be O(n) if you don't update second_max in the first conditional (double pass)
    //or can be O(1) if you sort the array and get the second element
    public void second_max(int[] arr) {
        int max = Integer.MIN_VALUE;
        int second_max = Integer.MIN_VALUE;

        for (int i = 0; i<arr.length; i++) {
            if (arr[i] > max) {
                second_max = max;
                max = arr[i];
            } 
            else if (arr[i] > second_max && arr[i] != max) {
                second_max = arr[i];
            }
        }
        System.out.print("Second max of array is " + String.valueOf(second_max) + "\n");
    }

    //First idea of a solution, making a copy of the array and adding non zero entries, then
    //adding zeros, complexity is O(n) (inlcuding the copying)
    public void zeroes_to_end(int[] arr) {

        int[] zeroes_array = new int[arr.length];

        int non_zero_count = 0;

        //Detecting zeroes and adding non zeroes to array
        for (int i=0; i<arr.length; i++) {
            if (arr[i] != 0) {
                zeroes_array[non_zero_count] = arr[i];
                non_zero_count += 1;
            } 
        }

        for (int i=non_zero_count; i<zeroes_array.length; i++) {
            zeroes_array[i] = 0;
        }


        System.out.print("The array with zeroes at the end is " + Arrays.toString(zeroes_array) + "\n");

    }

    //Optimal solution, still O(n) but less iterations and no copy necessary
    //by performing a swap with two pointers, one (j) that keeps track of the zeroes
    //and another (i) that keeps track of the non zeroes as we iterate, and swaps them as
    //necessary. j doesn't advance until a non zero has been swapped to its place.
    public void zeroes_to_end_optimal(int[] arr) {
        int j = 0;
        for (int i=0;i<arr.length;i++){
            if(arr[i]!=0 && arr[j] == 0) {
                int temp = arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
            if(arr[j] != 0) {
                j++;
            }
        }
        System.out.print("The array with zeroes at the end is " + Arrays.toString(arr) + "\n");
    }

    public void resize_array(int[] arr, int new_size) {

        System.out.print("Original array size " + String.valueOf(arr.length) + "\n");

        int[] temp = new int[new_size];

        for (int i=0;i<arr.length;i++) {
            temp[i] = arr[i];
        }

        arr = temp;
        System.out.print("New array size " + String.valueOf(arr.length) + "\n");
    }

    //My first idea for an algorithm that solves this
    public void find_missing_number(int[] arr) {
        int max_number = Arrays.stream(arr).max().orElseThrow();
        int array_sum = Arrays.stream(arr).sum();
        int max_sum = (max_number)*(max_number+1)/2;

        int missing_number = max_sum - array_sum;
        System.out.print("The missing number is " + String.valueOf(missing_number) + "\n");
    }
    
    public void check_if_palindrome(char[] str) {
        char[] reverse = new char[str.length];

        int j=0;
        for (int i = str.length-1;i>=0;i--) {
            reverse[j] = str[i];
            j+=1;
        }

        if (Arrays.equals(str, reverse)){
            System.out.print("The string is a palindrome" + "\n");
        }
        else {
            System.out.print("The string is not a palindrome" + "\n");
        }
    }

    //Alternatively, only check that the string is symmetric, when it is not,
    //you don't need to check any further
    public void check_if_palindrome_alternate(char[] str) {
        int start = 0;
        int end = str.length - 1;
        while (start<end) {
            if (str[start] != str[end]) {
                System.out.print("The string is not a palindrome" + "\n");
            } 
            start ++;
            end --;
        }
        System.out.print("The string is a palindrome" + "\n");
    }
}
