import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static StringBuilder sb = new StringBuilder();


    static String quadTreeReverse(List<String> arr) {

        String result = "";
        for(int i=0; i < arr.size(); i++) {
            if(arr.get(i).equals("x")) {

                List<String> temp = arr.subList(i+1, arr.size());
                String ttt = quadTreeReverse(temp);

                arr.set(i, ttt);

            }
        }

        if(arr.size() >= 4) {
            result = "x" + arr.get(2) + arr.get(3) + arr.get(0) + arr.get(1);
            arr.remove(3);
            arr.remove(2);
            arr.remove(1);
            arr.remove(0);
        }



        return result;

    }

    public static void main(String[] args) throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int n = Integer.valueOf(br.readLine());


        List<String> result = new ArrayList<>();

        for(int i=0; i<n; i++) {
            List<String> video = Arrays.stream(br.readLine().split("")).collect(Collectors.toList());
            quadTreeReverse(video);
            result.add(video.stream().collect(Collectors.joining("")));
        }

        sb.append(result.stream().collect(Collectors.joining("\n")));

        System.out.println(sb);

    }
}
