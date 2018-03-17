import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int m;
    static int n;
    static int count = 1;
    static List<Integer> sizeList = new ArrayList<>();
    static List<List<Boolean>> colored = new ArrayList<List<Boolean>>();
    static List<Boolean> coloredChild = new ArrayList<>();
    static int size = 0;

    //직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값
    static void coloring(int lx, int ly, int rx, int ry) {

        for (int i = ly; i < ry; i++) {

            coloredChild = colored.get(i);

            for(int j = lx; j < rx; j++) {
                coloredChild.set(j, true);
            }
            colored.set(i, coloredChild);
        }
    }

    static void findArea(int x, int y) {

        if(colored.get(y).get(x)) {
//            if(x == (n-1) && y == (m-1)) {
//                n = n-1;
//            }
            return;
        }
        // 18.03.15 자기 컬러 체크
        //          지가 컬러 true 일떄 -> 끝내고 다음, 다음은 어떻게??? -> 어짜피 컬러링 되니까 전수조사

        System.out.println(" (" + x + ", " + y + ") was search");
        colored.get(y).set(x, true);
        size = size + 1;

        if(x < (n-1)) findArea(x+1, y);
        if(y < (m-1)) findArea(x, y+1);
        if(y == (m-1) || y > 0) findArea(x, y-1);
        if(x == (n-1) || x > 0) findArea(x-1, y);


        // 18.03.15 0, 0에서부터 전수조사하니까 위 우 만 조사하면됨
        //          x좌표 맨끝에서 y좌표만 증가 -> x좌표 원래 값으로 처리해줘야댐

        // 18.03.17 x나 y가 배열의 마지막에 도달했을 경우 문제 발생
        //          상, 우 로만 조사x 상하좌우 다 조사?


        // 18.03.15 지가 컬러 false -> 다음 좌표지정 -> size + 1, findArea()
        //          상하좌우가 열린건 좌표지정만 상하좌우로 해주면 다음번 딥에서 체크 후 아니면 반환, 기면 더 딥하게 들어감
        //          조사한데는 컬러링

    }


    public static void main(String[] args) throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        m = Integer.valueOf(stringTokenizer.nextToken());
        n = Integer.valueOf(stringTokenizer.nextToken());

        for (int i = 0; i < m; i++) {

            coloredChild = new ArrayList<>();

            for(int j = 0; j < 7; j++) {
                coloredChild.add(false);
            }
            colored.add(coloredChild);
        }

        int k = Integer.valueOf(stringTokenizer.nextToken());

        for(int i=0; i<k; i++) {
            int[] crd = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            coloring(crd[0], crd[1], crd[2], crd[3]);
        }

        for(int y=0; y<m; y++) {
            for(int x=0; x<n; x++) {
                if(!colored.get(y).get(x)) {
                    count++;
                    System.out.println("----- At (" + x + ", " + y + ") search start-----");
                    findArea(x, y);
                    sizeList.add(size);
                    System.out.println("----Deep " + size + " search finish-----");
                    size = 0;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count);
        sb.append("\n");
        Collections.sort(sizeList);
        sb.append(sizeList.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        System.out.println(sb);
    }


}
