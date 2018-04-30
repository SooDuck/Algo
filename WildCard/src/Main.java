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

        System.out.print("r = " + Arrays.toString(r.toArray()));
        System.out.println(" w = " + Arrays.toString(w.toArray()));

        boolean flag = false;

        if(r.equals(w) || (r.size() == 1 && r.get(r.size()-1).equals("*")))  {
            System.out.println("YES");
            return true;
        }

        int rIndex = 0;
        int wIndex = 0;

//        r.get(rIndex).equals(w.get(wIndex)) || r.get(rIndex).equals("?") ||

//        System.out.println((r.size() > 0) + "&&" + (rIndex < r.size()) + "&&" + (w.size() > 0) + "&&" + (wIndex < w.size()) + "&&" + r.get(rIndex).equals("*"));

//        while ((r.size() > 0 && rIndex < r.size() && w.size() > 0 && wIndex < w.size()) && r.get(rIndex).equals("*")) {
//            if(r.get(rIndex+1).equals(w.get(++wIndex))) {
//                ++rIndex;
//                System.out.print("\tr = " + r.get(rIndex));
//                System.out.println(" w = " + w.get(wIndex));
//                break;
//            } else {
//                System.out.print("\tr = " + r.get(rIndex));
//                System.out.println(" w = " + w.get(wIndex));
//            }
//        }
//
//        if((r.size() > 0 && w.size() > 0) && (r.get(rIndex).equals(w.get(wIndex)) || r.get(rIndex).equals("?")))
//            flag = wildCard(r.subList(rIndex+1, r.size()), w.subList(wIndex+1, w.size())) || flag ? true : false;


        System.out.println((r.size() > 0) + "&&" + (rIndex < r.size()) + "&&" + (w.size() > 0) + "&&" + (wIndex < w.size())
                + "&& (" + r.get(rIndex).equals("*") + " || " + r.get(rIndex).equals("?") + " || " + r.get(rIndex).equals(w.get(wIndex)) + ")");

        while ((r.size() > 0 && rIndex < r.size() && w.size() > 0 && wIndex < w.size())
//                && (r.get(rIndex).equals("*") || r.get(rIndex).equals("?") || r.get(rIndex).equals(w.get(wIndex)))
                ) {

            System.out.print("\twhile = " + r.get(rIndex));
            System.out.println(" ? " + w.get(wIndex));

            if (r.get(rIndex).equals("*")) {
                flag = wildCard(r.subList(++rIndex, r.size()), w.subList(++wIndex, w.size())) || flag;
            } else if(!r.get(rIndex).equals("*") || r.get(rIndex).equals("?") || r.get(rIndex).equals(w.get(wIndex))) {
                ++wIndex;
                ++rIndex;
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
                if(wildCard(regularExpression,word)) result.add(word.stream().collect(Collectors.joining("")));

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
