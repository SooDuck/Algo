import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Sooduck, Park
 * @since 2018-06-08 [2018.Jun.08]
 */

public class Main {

    static int cache[];

    static int twoNTailing(int n) {

        if(n == 1) {
            return 1;
        } else if(n == 2) {
            return 2;
        }

        if(cache[n] < 0) {
            cache[n] = (twoNTailing(n-1) + twoNTailing(n-2)) % 10007;
        }

        return cache[n];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(br.readLine());
        cache = new int[n+1];
        Arrays.fill(cache, -1);
        cache[0] = 0;

        System.out.println(twoNTailing(n));

    }
}
