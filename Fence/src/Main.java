
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static int fence(List<Integer> arr) {

        int size = arr.size();

        if(size == 1) {
            return arr.get(0);
        }

        // 작은 단위로 분해, 이분화된 문제중 답에 근접한 값 저장
        int result = Math.max(fence(arr.subList(0, size/2)), fence(arr.subList(size/2, size)));

        // 분해된 문제에 대한 처리
        // 기존의 가로 * 세로의 크기와
        // 현재 배열의 가장 높은 울타리부터 가장 낮은 울타리까지, 연속수 증가
        // 2 3 4
        // 6 2 3
        // 4 3 2

        for(int low=0; low < size; low++) {
            int value = arr.get(low);
            boolean flag = true;
            int cntns = 0;
            ;
            for(int i=0; i<size; i++) {
                // 비교처리
                if(value <= arr.get(i)) {
                    flag = true;
                    cntns = cntns + 1;
                } else {
                    flag = false;
                    cntns = 0;

                }
                if(flag) {
                    result = Math.max(result, value*cntns);
                }
            }
        }

        // 제일 큰걸찾고 기존에서 넘겨받은 결과와 비교

        return result; //most lowest num;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(br.readLine());

        List<Integer> output = new ArrayList<>();

        for(int i=0; i<n; i++) {
            br.readLine();
            List<Integer> input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
            output.add(fence(input));

        }
        System.out.print(output.stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }
}
