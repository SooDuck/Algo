import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    static List<Integer> s = new ArrayList<>();

    static void pick(int k, List<Integer> index, List<Integer> result, int toPick) {

        // 기저조건
        if(toPick == 0) {
            System.out.println(
                    result.stream().map(String::valueOf).collect(Collectors.joining(" "))
            );
            return;
        }

        int smallest = (result.isEmpty()) ? 0 : index.get(index.size() - 1) + 1;

        for(int i = smallest; i < k; i++) {
            //선택
            index.add(i);
            result.add(s.get(i));
            //다음회차
            pick(k, index, result, toPick-1);
            //삭제
            result.remove(index.size() - 1);
            index.remove(index.size() - 1);

        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 : 원소수 K (6 < k < 13), 집합 S
        // 처리 : 6개의 원소로 이루어진 모든 집합 조합
        // 출력 : 가능한 모든 조합 출력

        List<Integer> result = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        while(true) {


            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

            int k = Integer.valueOf(stringTokenizer.nextToken());

            if(k == 0) {
                break;
            }
            if(k >= 6 || k <= 13) {
                break;
            }

            for(int i=0; i<k; i++) {
                s.add(Integer.valueOf(stringTokenizer.nextToken()));
            }

            pick(k, index, result, 6);


            s.clear();
            result.clear();
            System.out.println();

        }

    }
}
