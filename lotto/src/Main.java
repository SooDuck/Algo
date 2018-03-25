import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    static List<Integer> s = new ArrayList<>();

    static void pick(int k, List<Integer> result, int toPick) {

        // 기저조건
        if(toPick == 0) {
            String resultString = "";

            for(int i=0; i<6; i++) {
                resultString += String.valueOf(s.get(result.get(i))) + " ";
            }

            System.out.println(resultString);

//            System.out.println(
//                    result.stream().map(String::valueOf).collect(Collectors.joining(" "))
//
//            );

            return;
        }

        int smallest = (result.isEmpty()) ? 0 : result.get(result.size() - 1) + 1;

        for(int i = smallest; i < k; i++) {
            //선택
            result.add(i);
            //다음회차
            pick(k, result, toPick-1);
            //삭제
            result.remove(result.size() - 1);

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
//            if(k >= 6 || k <= 13) {
//                break;
//            }

            for(int i=0; i<k; i++) {
                s.add(Integer.valueOf(stringTokenizer.nextToken()));
            }

            pick(k, result, 6);

            s.clear();
            result.clear();
            System.out.println();

        }

    }
}
