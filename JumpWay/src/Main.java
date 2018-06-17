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
    static long[][] cache = null;

    static long way = 0;

    static StringBuilder sb = new StringBuilder();

    static long jumpGame(int y, int x, String path) {

//        boolean flag = false;
//        int[][] newCache = new int[k][k];
//        for(int i=0; i<k; i++) {
//            newCache[i] = Arrays.copyOf(cache[i], cache[i].length);
//        }

//        path = path + map[y][x] + "(" + y +", " + x + ")" + " ";

        // 기저사례 0일때
        if(y >= map.length-1 && x >= map.length-1 && map[y][x] == 0) {
//            System.out.println(path);
//            way++;
            return 1;
        }

        // cache has result
        if(cache[y][x] != 0) {
//            System.out.println(path);
//            cache[y][x]++;
//            way += cache[y][x];
            return cache[y][x];
        }

        if(map[y][x] == 0) {
            return 0;
        }

//        newCache[y][x] = 1;

        int farFrom = map[y][x];

        for(int i=0; i<2; i++) {

            int nextX = x + (dx[i] * farFrom);
            int nextY = y + (dy[i] * farFrom);

            if(0 <= nextX && nextX < k && 0 <= nextY && nextY < k) {
//                flag = jumpGame(nextY, nextX, path);
//                if(flag) cache[y][x]++;
                cache[y][x] += jumpGame(nextY, nextX, path);
            }
        }

        return cache[y][x];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.valueOf(br.readLine());

        map = new int[k][k];

        cache = new long[k][k];

        Arrays.stream(cache).forEach(row -> {
            Arrays.fill(row, 0);
        });

        for (int j=0; j<k; j++) {
            map[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }

        jumpGame(0, 0, "");

//        sb.append(way);
        sb.append(cache[0][0]);

        System.out.println(sb);

//        Arrays.stream(cache).forEach(row -> {
//            System.out.println(Arrays.toString(row));
//        });

        Arrays.stream(cache).forEach(row -> {
            Arrays.stream(row).forEach(col -> {
                System.out.print(col + " ");
            });
            System.out.println();
        });
    }
}
