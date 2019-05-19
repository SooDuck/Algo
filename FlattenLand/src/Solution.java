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

//        long[] temp = Arrays.stream(land).flatMapToLong(array -> Arrays.stream(array).asLongStream()).toArray();
//        Arrays.parallelSort(temp);
//        long max = temp[temp.length-1];

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

//        long firstVal = Long.MAX_VALUE;
//        long midVal = Long.MAX_VALUE;

        Map<Long, Long> cache = new HashMap<>();

        while (first <= last) {

            if(!cache.containsKey(first)) cache.put(first, calc(land, first, P, Q));
            if(!cache.containsKey(last)) cache.put(last, calc(land, last, P, Q));

            if((last - first) < 3) {

                long mid = (last - first) + 1;

                if(!cache.containsKey(mid)) cache.put(mid, calc(land, mid, P, Q));

                System.out.println(first + " - " + ((last-first)+1) + " - " + last);
                System.out.println(cache.get(first) + " - " + cache.get(mid) + " - " + cache.get(last));

                return Math.min(cache.get(first), Math.min(cache.get(last), cache.get(mid)));
            }

            long left = (long) (Math.ceil((last - first) / 3) * 1);
            long right = (long) (Math.ceil((last - first) / 3) * 2);

            System.out.println(first + " - " + left + " - " + right + " - " + last);

            if(!cache.containsKey(left)) cache.put(left, calc(land, left, P, Q));
            if(!cache.containsKey(right)) cache.put(right, calc(land, right, P, Q));

//            long firstVal = cache.get(first);
//            long leftVal = cache.get(left);
//            long rightVal = cache.get(right);
//            long lastVal = cache.get(last);

//            System.out.println("------------------------");
//            System.out.println(first + " " + cache.get(first));
//            System.out.println(left + " " + cache.get(left));
//            System.out.println(right + " " + cache.get(right));
//            System.out.println(last + " " + cache.get(last));
//            System.out.println("------------------------");

            Map<Long, Long> lowestMap = new HashMap<>();
            lowestMap.put(first, cache.get(first));
            lowestMap.put(left, cache.get(left));
            lowestMap.put(right, cache.get(right));
            lowestMap.put(last, cache.get(last));

//            long[][] compa = new long[4][4];
//
//            compa[0][0] = first; compa[0][1] = cache.get(first);
//            compa[1][0] = left;  compa[1][1] = cache.get(left);
//            compa[2][0] = right; compa[2][1] = cache.get(right);
//            compa[3][0] = last;  compa[3][1] = cache.get(last);


            List list = sortByValue(lowestMap);

            first = Math.min((long) list.get(0), (long) list.get(1));
            last = Math.max((long) list.get(0), (long) list.get(1));

//            System.out.println(first + " - " + last);
//            System.out.println("------------------------");

        }

            answer = cache.get(first);

        return answer;
    }

    private static List sortByValue(Map map) {

        List<Long> list = new ArrayList<>();
        list.addAll(map.keySet());

//        list.forEach(i -> {
//            System.out.println(i + " - " + map.get(i));
//        });

        Collections.sort(list, (o1, o2) -> {

//            System.out.println(map.get(o2) + " - " + map.get(o1));

            return ( -1 * ((Comparable) map.get(o2)).compareTo(map.get(o1)));
        });
//        Collections.reverse(list);

        return list;
    }

}
