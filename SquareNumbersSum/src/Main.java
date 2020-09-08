import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Sooduck, Park
 * @since 2018-06-06 [2018.Jun.06]
 */

public class Main {

    static int[] cache;

    static int squareNumbersSum(int num, int count) {

        if(num <= 0) {
            return count;
        }

//        if(cache[num] )





        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.valueOf(br.readLine());
        cache = new int[num];
        Arrays.fill(cache, 0);

        System.out.println(squareNumbersSum(num, 0));
    }
}
