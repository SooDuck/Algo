
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static List<Integer> input = new ArrayList<>();

    // 책 알고리즘, 난수 발생후 정답 비교를 위해 output1 리스트에 저장
    static int fence1(int left, int right) {
        if (left == right) return input.get(left);
        int mid = (left + right) / 2;
        int ret = Math.max(fence1(left, mid), fence1(mid + 1, right));
        int lo = mid, hi = mid + 1;
        int height = Math.min(input.get(lo), input.get(hi));
        ret = Math.max(ret, height * 2);
        while (left < lo || hi < right) {
            if (hi < right && (lo == left || input.get(lo - 1) < input.get(hi + 1))) {
//                ++hi;
                height = Math.min(height, input.get(++hi));
            } else {
                height = Math.min(height, input.get(--lo));
            }
            ret = Math.max(ret, height * (hi - lo + 1));
//            System.out.println(height + " * " + (hi-lo+1) + " = " + ret + "(" + hi + "-" + lo + "+1)");

        }
        return ret;
    }

    static int fence(List<Integer> arr) {

        int size = arr.size();

        if(size == 1) return arr.get(0);

        // 작은 단위로 분해, 이분화된 문제중 답에 근접한 값 저장
        int result = Math.max(fence(arr.subList(0, size/2)), fence(arr.subList(size/2, size)));

//        분해된 문제에 대한 처리
//        기존의 가로 * 세로의 크기와
//        현재 배열의 가장 높은 울타리부터 가장 낮은 울타리까지, 연속수 증가
//        2 3 4
//        6 2 3
//        4 3 2

        // 전수조사, 타임아웃
//        for(int low=0; low < size; low++) {
//            int value = arr.get(low);
//            boolean flag = true;
//            int cntns = 0;
//            ;
//            for(int i=0; i<size; i++) {
//                // 비교처리
//                if(value <= arr.get(i)) {
//                    flag = true;
//                    cntns = cntns + 1;
//                } else {
//                    flag = false;
//                    cntns = 0;
//                }
//                if(flag) result = Math.max(result, value*cntns);
//            }
//        }

        int start = size/2 - 1;
        int end = size/2;
        int height = Math.min(arr.get(start), arr.get(end));

        // 집합의 원소가 2개인경우 아래 while문에서 start == 0, end == size-1이므로 조회 불가, 기본값으로 넓이가 2일떄 확인
        result = Math.max(result, height*2);

        System.out.println(Arrays.toString(arr.toArray()));

        // 0 ~ size-1 전체를 조사하는 경우도 있기떄문에 or연산 사용, 최초 and 사용으로 인한 다수 문제 발견
        while (start > 0 || end < size-1) {

            // ex) 7 1 5 9 6 7 | 3
            // | 이전까지는 뒤를 우선해서 정가시켜야함, 3일경우 index = size - 1 이므로 size번쨰 원소는 존재 x 비교 불가, 아웃 오브 바운스 에러 표출
            // 7 | 1 5 9 6 7 3
            // 마찬가지로 뒤를 우선 조회한다음엔 앞으로 와야하는데 start가 0이되면 ||문 이후에서 미래의 start, end를 조회할때 start가 -1일때 조회-> 아웃 오브 바운스, 이를 막아줘야함
            if( start <= 0 || ( end < size-1 && arr.get(start-1) <= arr.get(end+1)) ) {
                height = Math.min(height, arr.get(++end));
//                ++end;
            } else {
                height = Math.min(height, arr.get(--start));
//                --start;
            }

//            해당 stat ~ end 내 가장 작은 수를 찾기 위함 이였으나 타임 오버로 인해 조건문에서 동시 확인
//            for (int i=start; i<end; i++) height = Math.min(height, arr.get(i));

            result = Math.max(result, height*(end-start+1));

//            테스트코드
            System.out.println(height + " * " + (end-start+1) + " = " + result + "(" + end + "-" + start + "+1)");

        }

        return result; //most lowest num;
    }


//1
//3
//7 9 6

//1
//9
//7 9 3 1 2 2 4 6 9

//1
//10
//3060 9411 5969 4326 3454 1698 9994 7840 3938 8416  1

//1
//10
//3 9 5 4 3 1 9 7 3 8

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        int n = Integer.valueOf(br.readLine());
        int n = 1;

        List<Integer> output = new ArrayList<>();
        List<Integer> output1 = new ArrayList<>();

        for (int i=0; i<10; i++) {
            input.add((int)(Math.random() * 10000) + 1);
            System.out.print(input.get(i) + " ");
        }
        System.out.println("\n----------------");

        for(int i=0; i<n; i++) {
//            br.readLine();
//            List<Integer> input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
            output1.add(fence1(0, input.size()-1));
            output.add(fence(input));
        }

        System.out.println("---------------");
        System.out.print(output1.stream().map(String::valueOf).collect(Collectors.joining("\n")));
        System.out.println("\n---------------");
        System.out.print(output.stream().map(String::valueOf).collect(Collectors.joining("\n")));
    }
}
