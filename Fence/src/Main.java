
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static final int HEIHSTNUM = 987654321;
    static int[] fence(List<Integer> arr, int deep) {

        int size = arr.size();

        int[] left = null;
        int[] right = null;

        int highestFence = 0;
        int cnsctNmbr = 1;

        // 작은 단위로 분해, 이분화된 문제중 답에 근접한 값 저장
        if(size > 1) {
            left = fence(arr.subList(0, size/2),deep+1);
            right = fence(arr.subList(size/2, size),deep+1);

            if ((left[0]*left[1]) > (right[0]*right[1])) {
                highestFence = left[0];
                cnsctNmbr = left[1];
            } else if((left[0]*left[1]) < (right[0]*right[1])) {
                highestFence = right[0];
                cnsctNmbr = right[1];
            }
        }

        System.out.println("split done");


        // 분해된 문제에 대한 처리
        // 기존의 가로 * 세로의 크기와
        // 현재 배열의 가장 높은 울타리부터 가장 낮은 울타리까지, 연속수 증가
        // 2 3 4
        // 6 2 3
        // 4 3 2


        int fence = arr.get(0);
        int low = 0;

        int resultFence = arr.get(0);
        int resultLow = 1;

        System.out.println("in for");
        for(int i=size; i>0; i--) {
            // 비교처리

        }

        // 9 >= 6 f6 l2 r12
        // 6 >= 7

        // 2 >= 3 2 1

        // 2 > 3 || 2 < 6

        // 제일 큰걸찾고 기존에서 넘겨받은 결과와 비교
        int[] result = new int[2];

        result[0] = resultFence;
        result[1] = resultLow;

        if((highestFence * cnsctNmbr) > (resultFence * resultLow) ) {
            result[0] = highestFence;
            result[1] = cnsctNmbr;
        }

        for(int i=0; i < deep; i++) {
            System.out.print("   ");
        }
        System.out.println(Arrays.toString(arr.toArray()) + " fence : " + result[0] + " cnsct : " + result[1] + " largest : " + (result[0] * result[1]));


//        int result = highestFence * cnsctNmbr;

        return result; //most lowest num;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++) {
            String low = br.readLine();
            List<Integer> input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
            int[] result = fence(input,0);
            System.out.println(result[0] * result[1]);
        }
    }
}
