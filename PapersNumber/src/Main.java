import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static List<Integer> result = new ArrayList<>();

    static void papersNumber(List<List<Integer>> arr, int num) {

        int flagNum = arr.get(0).get(0);
        boolean flag = true;

        for (List<Integer>low : arr) {
            for (int col : low) {
                if(!Integer.valueOf(col).equals(Integer.valueOf(flagNum))) {
                    flag = false;
                    break;
                }
            }
        }

        if(flag) {
            result.add(flagNum);
            return;
        }

        int nineEqualPart = num/3;

        List<List<Integer>> arrTop = arr.subList(0, nineEqualPart);
        List<List<Integer>> arrMid = arr.subList(nineEqualPart, nineEqualPart*2);
        List<List<Integer>> arrBottom = arr.subList(nineEqualPart*2, num);

        List<List<Integer>> arrTopLeft = new ArrayList<>();
        List<List<Integer>> arrTopMid = new ArrayList<>();
        List<List<Integer>> arrTopRight = new ArrayList<>();

        arrTop.forEach(lists -> {
            arrTopLeft.add(lists.subList(0, nineEqualPart));
            arrTopMid.add(lists.subList(nineEqualPart, nineEqualPart*2));
            arrTopRight.add(lists.subList(nineEqualPart*2, num));
        });

        List<List<Integer>> arrMidLeft = new ArrayList<>();
        List<List<Integer>> arrMidMid = new ArrayList<>();
        List<List<Integer>> arrMidRight = new ArrayList<>();

        arrMid.forEach(lists -> {
            arrMidLeft.add(lists.subList(0, nineEqualPart));
            arrMidMid.add(lists.subList(nineEqualPart, nineEqualPart*2));
            arrMidRight.add(lists.subList(nineEqualPart*2, num));
        });

        List<List<Integer>> arrBottomLeft = new ArrayList<>();
        List<List<Integer>> arrBottomMid = new ArrayList<>();
        List<List<Integer>> arrBottomight = new ArrayList<>();

        arrBottom.forEach(lists -> {
            arrBottomLeft.add(lists.subList(0, nineEqualPart));
            arrBottomMid.add(lists.subList(nineEqualPart, nineEqualPart*2));
            arrBottomight.add(lists.subList(nineEqualPart*2, num));
        });

        papersNumber(arrTopLeft, nineEqualPart);
        papersNumber(arrTopMid, nineEqualPart);
        papersNumber(arrTopRight, nineEqualPart);

        papersNumber(arrMidLeft, nineEqualPart);
        papersNumber(arrMidMid, nineEqualPart);
        papersNumber(arrMidRight, nineEqualPart);

        papersNumber(arrBottomLeft, nineEqualPart);
        papersNumber(arrBottomMid, nineEqualPart);
        papersNumber(arrBottomight, nineEqualPart);

    }

    public static void main(String[] args) throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.valueOf(br.readLine());

        List<List<Integer>> input = new ArrayList<>();

        for(int i=0; i<n; i++) {
            input.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList()));
        }

        papersNumber(input, n);

        int result0 = 0;
        int result1 = 0;
        int resultM = 0;

        for (int r : result) {
            if(r == 0) result0++;
            if(r == 1) result1++;
            if(r == -1) resultM++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(resultM+"\n");
        sb.append(result0+"\n");
        sb.append(result1+"\n");

        System.out.println(sb);

    }
}
