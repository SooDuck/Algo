import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sooduck, Park
 * @since 2019-04-09 [2019.Apr.09]
 */

public class Solution {


    public static long calc(int[][] land, long height, long P, long Q) {

//        LongSummaryStatistics overSummery = Arrays.stream(land).flatMapToLong(array -> Arrays.stream(array)).filter(i -> i > height).summaryStatistics();
//        LongSummaryStatistics underSummery = Arrays.stream(land).flatMapToLong(array -> Arrays.stream(array)).filter(i -> i < height).summaryStatistics();
//

//        System.out.println("[" + height + "]-------------------------------------");
        return  Arrays.stream(land).flatMapToLong(array -> Arrays.stream(array).asLongStream()).map(i -> {

            // height == 3
            if(i > height) {
//                    System.out.println("(" + i + " - " + height + ") * " + Q + " = " + ((i-height) * Q));
                return (i-height) * Q;
            } else if(i < height) {
//                    System.out.println("(" + height + " - " + i + ") * " + P + " = " + ((height-i) * P));
                return (height-i) * P;
            } else {
//                    System.out.println(i-height);
                return i-height;
            }

        }).sum();

//        System.out.println();
//        System.out.println(height + "-----------------" );
//        System.out.println("-" + (overSummery.getSum() - (height * overSummery.getCount())) + " * " + Q);
//        System.out.println("+" + ((height * underSummery.getCount()) - underSummery.getSum()) + " * " + P);
//        System.out.println("---------------------------");

//        System.out.println(
//
//                "(" + overSummery.getSum() + " - " + "(" + height + " * " + overSummery.getCount() + ") * " + Q
//
//
//        );
//
//        return (((long) (overSummery.getSum()) - (height * (long) overSummery.getCount())) * Q)
//                + (((height * (long) underSummery.getCount()) - (long) (underSummery.getSum())) * P);

    }

    public long solution(int[][] land, int P, int Q) {
        long answer = -1;

//        Arrays.stream(land).flatMapToInt(array -> Arrays.stream(array)).sorted().mapToObj(Integer::new).collect(Collectors.groupingBy(Integer::valueOf)).forEach((k, v) -> {
//            System.out.println(k + " - " + v.size() + " " + v.toString());
//        });

//        System.out.println();

        long max = Arrays.stream(land).flatMapToLong(array -> Arrays.stream(array).asLongStream()).summaryStatistics().getMax();


//        long bf = Long.MAX_VALUE;

//        for(int i = 0; i <= max ; i++) {
////            System.out.println(i + "] ---------------------------");
//            long temp = calc(land, i, P, Q);
//            bf = Math.min(bf, temp);
//
//            System.out.println( i + " --> " + temp);
//        }

        long first = 0;
        long last = max;
        long left;
        long right;

        long[] cache = new long[(int) max + 1];
        Arrays.fill(cache, -1);

        long firstVal = Long.MAX_VALUE;
//        long midVal = Long.MAX_VALUE;

        while (first <= last) {

            if((last - first) < 3) {
                firstVal = calc(land, first, P, Q);
                long midVal = calc(land, (last-first)+1, P, Q);
                long lastVal = calc(land, last, P, Q);

//                System.out.println(first + " - " + ((last-first)+1) + " - " + last);
//                System.out.println(firstVal + " - " + midVal + " - " + lastVal);

                return Math.min(firstVal, Math.min(lastVal, midVal));
            }

            left = (long) (Math.ceil((last - first) / 3) * 1);
            right = (long) (Math.ceil((last - first) / 3) * 2);

//            System.out.println(first + " - " + left + " - " + right + " - " + last);

//            midVal = calc(land, mid, P, Q);
//            firstVal = calc(land, first, P, Q);
//            long leftVal = calc(land, left, P, Q);
//            long rightVal = calc(land, right, P, Q);
//            long lastVal = calc(land, last, P, Q);


            firstVal = (cache[(int) first] != -1) ? cache[(int) first] : calc(land, first, P, Q);
            long leftVal = (cache[(int) left] != -1) ? cache[(int) left] : calc(land, left, P, Q);
            long rightVal = (cache[(int) right] != -1) ? cache[(int) right] : calc(land, right, P, Q);
            long lastVal = (cache[(int) last] != -1) ? cache[(int) last] : calc(land, last, P, Q);

            if(cache[(int) first] != -1) cache[(int) first] = firstVal;
            if(cache[(int) left] != -1) cache[(int) left] = leftVal;
            if(cache[(int) right] != -1) cache[(int) right] = rightVal;
            if(cache[(int) last] != -1) cache[(int) last] = lastVal;

//            System.out.println("------------------------");
//            System.out.println(first + " " + firstVal);
//            System.out.println(left + " " + leftVal);
//            System.out.println(right + " " + rightVal);
//            System.out.println(last + " " + lastVal);
//            System.out.println("------------------------");

            Map<Long, Long> lowestMap = new HashMap<>();
            lowestMap.put(first, firstVal);
            lowestMap.put(left, leftVal);
            lowestMap.put(right, rightVal);
            lowestMap.put(last, lastVal);

            List list = sortByValue(lowestMap);

            first = Math.min((long) list.get(0), (long) list.get(1));
            last = Math.max((long) list.get(0), (long) list.get(1));

//            System.out.println(first + " - " + last);
//            System.out.println("------------------------");

        }

            answer = firstVal;

        return answer;
    }

    private static List sortByValue(Map map) {

        List<Long> list = new ArrayList<>();


        list.addAll(map.keySet());

//        Collections.sort(list, new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                return ((Comparable) map.get(o2)).compareTo(map.get(o1));
//            }
//        });

        Collections.sort(list, (o1, o2) -> ( -1 * ((Comparable) map.get(o2)).compareTo(map.get(o1))));
//        Collections.reverse(list);

//        System.out.println("*****************************************");
//        for (Object a : map.entrySet()) {
//            String[] b = String.valueOf(a).split("=");
//            System.out.println(b[0] + " - " + b[1]);
//        }
//
//        for (long a : list) {
//            System.out.println(a);
//        }
//        System.out.println("*****************************************");


        return list;
    }

}
