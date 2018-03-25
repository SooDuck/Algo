import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n = 0;

    static int[][] g;
    static int[][] result;

    static int index = 0;

    static void findWay(int site, int deep) {

        // 돌아서 처음 스타트만 아니라 돌아서 탐색했던데 다시 가면 아웃;
        if(result[index][site] == 1 && deep != 0) {
            result[index][site] = 1;
            return;
        }

        System.out.print(site + " -> ");
        if(deep != 0) result[index][site] = 1;

        for(int i = 0; i < n; i++) {
            if(g[site][i] == 1 && i != site) {
                findWay(i, deep+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        n = Integer.valueOf(br.readLine());

        if(n < 0 || n > 100) return;

        g = new int[n][n];
        result = new int[n][n];

        StringTokenizer stringTokenizer;

        for(int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                g[i][j] = Integer.valueOf(stringTokenizer.nextToken());
                result[i][j] = 0;
            }
        }

        for(index = 0; index < n; index++) {
            int deep = 0;
            findWay(index, deep);
            System.out.println();
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(result[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
