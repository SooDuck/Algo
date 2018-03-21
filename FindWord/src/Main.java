import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    static int[] dx = {-1, 1, 0, -1, 1, 0, -1, 1};
    static int[] dy = {0, 0, 1, 1, 1, -1, -1, -1};

    static String[][] board;
    static String[][] word;

    static String[] result;

    static int index;
    static int wordCount;

    static int boardSize = 5;

    static void findWord(int x, int y, int deep) {

        if(x < 0 || y < 0 || x > (boardSize-1) || y > (boardSize-1)) {
            return;
        }

        if(!board[y][x].equals(word[index][deep])) {
            return;
        }

        if(word[index].length-1 <= deep) {
            result[index] = "YES";
            return;
        }

        for(int i=0; i<dx.length; i++) {
            findWord(x+dx[i], y+dy[i], deep+1);
        }
    }

    public static void main(String[] args) throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int c = Integer.valueOf(br.readLine());

        if(c  > 50) return;

        board = new String[boardSize][boardSize];

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < c; i++) {
            for(int j = 0; j < boardSize; j++) {
                board[j] = br.readLine().split("");
            }

            wordCount = Integer.valueOf(br.readLine());

            word = new String[wordCount][50];
            result = new String[wordCount];

            for(int j = 0; j < wordCount; j++) {
                word[j] = br.readLine().split("");
                result[j] = "NO";
            }

            for(index = 0; index < wordCount; index++) {
                for(int k = 0; k < boardSize; k++) {
                    for(int j = 0; j < boardSize; j++) {
                        findWord(j , k, 0);
                    }
                }
            }

            for(int k = 0; k < wordCount; k++) {
                sb.append(Arrays.stream(word[k]).collect(Collectors.joining()) + " " + result[k] + "\n");
            }

            sb.append("\n");
        }

        System.out.println(sb);

    }
}
