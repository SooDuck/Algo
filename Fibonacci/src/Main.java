
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Sooduck, Park
 * @since 2018-06-07 [2018.Jun.07]
 */

public class Main {

    static int[] cache;

    static int fibonacci(int num) {

        if(num == 0) {
            return 0;
        } else if(num == 1) {
            return 1;
        }

        if(cache[num] == -1) {
            cache[num] = fibonacci(num-1) + fibonacci(num-2);
        }


        return cache[num];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.valueOf(br.readLine());
        cache = new int[num+1];
        Arrays.fill(cache, -1);

        System.out.println(fibonacci(num));
    }
}
