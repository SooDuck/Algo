import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static void pickOnBook(int n, List<Integer> picked, int toPick) {
        if(toPick == 0) {
            System.out.println(picked.stream().map(String::valueOf).collect(Collectors.joining(", ")));
            return;
        }

        int smallest = picked.isEmpty() ? 0 : picked.get(picked.size()-1) + 1;


        for (int next = smallest; next < n; ++next) {
            temp.add(next);
            pick(n, picked, toPick-1);
            temp.remove(picked.size()-1);
        }
    }

    static List<Integer> temp = new ArrayList<>();
    static List<Integer> sample = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

    static void pick(int n, List<Integer> a, int count) {

        if(count == 0) {
            System.out.println(a.stream().map(String::valueOf).collect(Collectors.joining(", ")));
            return;
        }

        int tempNum = 0;

        if(a.size() == 0) {
            tempNum = 0;
        } else {
            tempNum = a.size();
        }
        //size가 문제가 되서 셈플 인덱스가 한칸 빠졋을때 다음회차에서 채우면서 문제가 발생.

//        int tempNum = a.isEmpty() ? 0 : a.get(a.size()-1) + 1;

        for (int next = tempNum; next < n; ++next) {

            a.add(sample.get(next));
            pick(n, temp, count-1);
            a.remove(temp.size()-1);
        }

    }

    static int sum(int n) {

        if(n == 1) return 1;
        return n + sum(n-1);
    }


    public static void main(String[] args) {

        System.out.println("result = " + String.valueOf(sum(10)));

        // 재귀호출로 4개씩 (1, 2, 3, 4), (1, 2, 3, 5) 같은 부분 집합구하기
        int a[] = new int[] {0, 1, 2, 3, 4, 5, 6};
        List<Integer> b = new ArrayList<>();
        List<Integer> c = Arrays.stream(a).boxed().collect(Collectors.toList());

        pick(7, temp, 4);

    }

}
