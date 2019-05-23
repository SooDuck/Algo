
/**
 * @author Sooduck, Park
 * @since 2019-05-22 [2019.May.22]
 * @ref. https://coloredrabbit.tistory.com/59
 *
 * Ternary search를 이용한 탐색
 *
 *  *  배열의 요소 높이의 통계값에 대하여 연산시 시간초과 나옴
 */

public class Solut {

    long n;

    public long calc(int[][] land, long value, int P, int Q) {

        long result = 0;

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                result += (land[i][j] - value) * (land[i][j] - value > 0 ? Q : -P);
            }
        }

        return result;
    }

    public long solution(int[][] land, int P, int Q) {

        long answer = -1;
        long i, j;
        n = (long)land.length;
        long l2h, lh2, s1, s2, l = 1, r = 0, mid;

        for(i = 0; i < n; i++) {
            for(j = 0; j < n; j++) {
                r = Math.max(r, land[(int)i][(int)j]);
            }
        }

//        r = Arrays.stream(land).flatMapToLong(arr -> Arrays.stream(arr).asLongStream()).max().orElse(0);

        for (i = 0; i < 64; i++) {
            l2h = (2 * l + r) / 3;
            lh2 = (l + 2 * r) / 3;
            s1 = calc(land, l2h, P, Q);
            s2 = calc(land, lh2, P, Q);

            if (s1 > s2) l = l2h;
            else r = lh2;
        }
        answer = Long.MAX_VALUE;
        mid = (l + r) / 2;

        for(i = (mid - 10); i <= mid + 10; i++) {
            answer = Math.min(answer, calc(land, i, P, Q));
        }

        return answer;
    }

}
