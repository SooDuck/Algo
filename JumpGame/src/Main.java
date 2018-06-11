import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] dx = {1,  0};
    static int[] dy = {0,  1};

    static int k;
    static int[][] cache = null;
    static int[][] map = null;

    static StringBuilder sb = new StringBuilder();

    static boolean jumpGame(int y, int x) {

        boolean flag = false;

        // 기저사례 0일때
        if(map[y][x] == 0) {
            Arrays.stream(cache).forEach(row -> {
                Arrays.fill(row, 0);
            });
            return true;
        }

        // cache has result
        if(cache[y][x] != -1) {
            return false;
        }

        cache[y][x] = 1;

        int farFrom = map[y][x];

        for(int i=0; i<2; i++) {

            int nextX = x + (dx[i] * farFrom);
            int nextY = y + (dy[i] * farFrom);

            if(0 <= nextX && nextX < k && 0 <= nextY && nextY < k) {
                flag = jumpGame(nextY, nextX) || flag ? true : false;
            }
        }

        return flag;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(br.readLine());

        for (int i=0; i<n; i++) {

            k = Integer.valueOf(br.readLine());

            cache = new int[k][k];
            map = new int[k][k];

            Arrays.stream(cache).forEach(row -> {
                Arrays.fill(row, -1);
            });

            for (int j=0; j<k; j++) {
                map[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            }

            sb.append(jumpGame(0, 0) ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
    }
}
