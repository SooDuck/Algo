import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Sooduck, Park
 * @since 2018-06-05 [2018.Jun.05]
 */

public class Main {

    static int size;
    static int[][] input;

    static int trianglePath(int y, int x) {

        if(y >= size-1) {
            return input[y][x];
        }

        int left = trianglePath(y+1, x);
        int right = trianglePath(y+1, x+1);

        return input[y][x] + Math.max(left, right);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int c = Integer.valueOf(br.readLine());

        for (int i=0; i<c; i++) {
            size = Integer.valueOf(br.readLine());
            input = new int[size][size];


            for(int k=0; k<size; k++) {
                String[] row = br.readLine().split(" ");
                for(int l=0; l<=k; l++) {
                    input[k][l] = Integer.valueOf(row[l]);
                }
            }

            sb.append(trianglePath(0,0) + "\n");

        }

        System.out.println(sb);
    }
}
