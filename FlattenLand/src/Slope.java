import javafx.beans.binding.MapExpression;

/**
 * @author Sooduck, Park
 * @since 2019-05-21 [2019.May.21]
 * @ref. https://sangdo913.tistory.com/140
 *
 * 기울기를 이용한 탐색 -> 오답 및 시간초과
 */

public class Slope {


    int n;

    public long calc(int[][] land, long value, int P, int Q) {

        long result = 0;

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                long mul = land[i][j] - value > 0 ? Q : -P;
                result += (long)(land[i][j] - value) * mul;
            }
        }

        return result;
    }


    public long solution(int[][] land, int P, int Q) {
        long answer = Long.MAX_VALUE;

        n = land.length;

        int first = 0;
        int last = 300;
        int mid = (first + last) / 2;

        while (first <= last) {

            mid = (first + last) / 2;

            long[] res = {calc(land, mid, P, Q), calc(land, mid + 1, P, Q)};

            if(res[0] == res[1]) break;

            if(res[0] < res[1]) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }
        }

        for (int i = mid - 10; i <= mid + 10; i++) {
            long temp = calc(land, i, P, Q);
            answer = Math.min(answer, temp);
        }

        return answer;
    }


}
