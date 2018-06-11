import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Sooduck, Park
 * @since 2018-06-11 [2018.Jun.11]
 */

public class Main {

    static int[] dx = {1,  0};
    static int[] dy = {0,  1};

    static int k;
    static int[][] map = null;

    static long way = 0;

    static StringBuilder sb = new StringBuilder();

    static void jumpGame(int y, int x, int[][] cache, String path) {


        int[][] newCache = new int[k][k];
        for(int i=0; i<k; i++) {
            newCache[i] = Arrays.copyOf(cache[i], cache[i].length);
        }

        path = path + map[y][x] + "(" + y +", " + x + ")" + " ";

        // 기저사례 0일때
        if(y >= map.length-1 && x >= map.length-1 && map[y][x] == 0) {
            System.out.println(path);
            way++;
            return;
        }

        // cache has result
        if(newCache[y][x] != -1) {
            return;
        }

        newCache[y][x] = 1;

        int farFrom = map[y][x];

        for(int i=0; i<2; i++) {

            int nextX = x + (dx[i] * farFrom);
            int nextY = y + (dy[i] * farFrom);

            if(0 <= nextX && nextX < k && 0 <= nextY && nextY < k) {
                jumpGame(nextY, nextX, newCache, path);
            }
        }

        return;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.valueOf(br.readLine());

        map = new int[k][k];

        int[][] cache = new int[k][k];

        Arrays.stream(cache).forEach(row -> {
            Arrays.fill(row, -1);
        });

        for (int j=0; j<k; j++) {
            map[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }

        jumpGame(0, 0, cache, "");

        sb.append(way);

        System.out.print(sb);
    }
}
