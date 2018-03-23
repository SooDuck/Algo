import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static StringBuilder sb = new StringBuilder();


    static void quadTree(List<List<Integer>> arr, int n) {

        int temp = arr.get(0).get(0);
        boolean flag = false;

        for (List<Integer> arrY: arr) {
            for (int x: arrY) {
                if(temp == x) {
                    temp = x;
                } else {
                    flag = true;
                    break;
                }
            }
        }

        if(!flag) {
            sb.append(temp);
            return;
        }

        int quad = n/2;

        List<List<Integer>> leftTop = new ArrayList<>();
        List<List<Integer>> rightTop = new ArrayList<>();
        List<List<Integer>> leftBottom = new ArrayList<>();
        List<List<Integer>> rightBottom = new ArrayList<>();

        List<List<Integer>> top = arr.subList(0, quad);
        List<List<Integer>> bottom = arr.subList(quad, n);

        for(int i=0; i<top.size(); i++) {
            leftTop.add(top.get(i).subList(0, quad));
            rightTop.add(top.get(i).subList(quad, n));

            leftBottom.add(bottom.get(i).subList(0, quad));
            rightBottom.add(bottom.get(i).subList(quad, n));
        }

//        ArrayList 참조와 복사로 인한 문제 발생
//        for(int i=0; i<n; i++) {
//
//            for(int j=0; j<n; j++) {
//                if(j < quad) {
//                    left.add(arr.get(i).get(j));
//                } else {
//                    right.add(arr.get(i).get(j));
//                }
//            }
//
//            if(i < quad) {
//                leftTop.add(left);
//                rightTop.add(right);
//            } else {
//                leftBottom.add(left);
//                rightBottom.add(right);
//            }
//            left.clear();
//            right.clear();
//        }

//        for(int i=0; i<n; i++) {
//
//            List<Integer> left = new ArrayList<>();
//            List<Integer> right = new ArrayList<>();
//
//            for(int j=0; j<n; j++) {
//                if(j < quad) {
//                    left.add(arr.get(i).get(j));
//                } else {
//                    right.add(arr.get(i).get(j));
//                }
//            }
//
//            if(i < quad) {
//                leftTop.add(left);
//                rightTop.add(right);
//            } else {
//                leftBottom.add(left);
//                rightBottom.add(right);
//            }
//        }

        sb.append("(");
        quadTree(leftTop, quad);
        quadTree(rightTop, quad);
        quadTree(leftBottom, quad);
        quadTree(rightBottom, quad);
        sb.append(")");

    }

    public static void main(String[] args) throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.valueOf(br.readLine());

        List<List<Integer>> video = new ArrayList<>();

        for(int i=0; i<n; i++) {
            video.add(Arrays.stream(br.readLine().split("")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList()));
        }

        quadTree(video, n);

        System.out.println(sb);

    }
}
