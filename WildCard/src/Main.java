import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static List<String> regularExpression = null;

    static List<String> word = null;
//    static int[] cache = null;

    static List<String> result = new ArrayList<>();

    static boolean wildCard(List<String> r, List<String> w) {

        boolean flag = false;

//        System.out.println(r.toString() + " - " + w.toString());

        //기저조건
        if( (r.size() == 1 && r.get(0).equals("*")) || (r.size() <= 0 && w.size() <= 0) ) {
            result.add(word.stream().collect(Collectors.joining("")));
//            System.out.println("YES");
            return true;
        }

        //dp

        //dfs

        while(!flag) {

            if(r.size() <= 0 || w.size() <= 0) {
                flag = true;
                break;
            }

            if(r.get(0).equals(w.get(0)) || r.get(0).equals("?")) {
//                r.remove(0);
//                w.remove(0);
                flag = wildCard(r.subList(1, r.size()), w.subList(1, w.size()));
            } else if(r.get(0).equals("*")) {
                if(r.size() <= 1) {
                    flag = true;
                    break;
                } else {
                    if(w.indexOf(r.get(1)) < 0) {
                        flag = true;
                        break;
                    }
                    flag = wildCard(r.subList(1, r.size()), w.subList(w.indexOf(r.get(1)), w.size()));
                }
            }
        }

        return flag;
    }



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.valueOf(br.readLine());

        for(int i=0; i<c; i++) {

            regularExpression = Arrays.stream(br.readLine().split("")).collect(Collectors.toList());
            int n = Integer.valueOf(br.readLine());

            for (int j=0; j<n; j++) {

                word = Arrays.stream(br.readLine().split("")).collect(Collectors.toList());
//                cache = new int[word.length-1];
//                Arrays.fill(cache, -1);
//                if(wildCard(regularExpression, word)) result.add(word.stream().collect(Collectors.joining("")));

                wildCard(regularExpression, word);
            }

        }

        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        result.stream().forEach(s -> System.out.println(s));

    }
}
