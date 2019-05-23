import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sooduck, Park
 * @since 2019-05-12 [2019.May.12]
 *
 * 구간을 3등분 하여 중간값(연산시, 배열의 요소 높이의 통계값에 대하여 연산)을 이용하여 탐색 -> 시간초과
 */

public class Solution2 {

    public long calc(Map<Object, List<Integer>> cal, long value, int P, int Q) {

        long result = 0;

//        cal.forEach((k, v) -> {
//            System.out.println(k + " -- " + v.size());
//        });

        Iterator i = cal.values().iterator();

//        System.out.println(value+ "]------------------");
        while (i.hasNext()) {
            List<Integer> l = (List<Integer>) i.next();

            if(l.get(0) > value) {
//                System.out.println("(" + l.get(0) + " - " + value + ") * " + l.size() * Q + " = " + ((l.get(0) - value) * l.size() * Q));
                result += ((l.get(0) - value) * l.size() * Q);
            } else if (l.get(0) < value) {
//                System.out.println("(" + value + " - " + l.get(0) + ") * " + l.size() * P + " = " + ((value - l.get(0)) * l.size() * P));
                result += ((value - l.get(0)) * l.size() * P);
            } else {
//                System.out.println(value + " = 0");
                result += 0;
            }
        }

        return result;
    }

    public long solution(int[][] land, int P, int Q) {
        long answer = -1;

        Map<Object, List<Integer>> cal =
                Arrays.stream(land)
//                        .flatMapToLong(array -> Arrays.stream(array).asLongStream())
                        .flatMapToInt(array -> Arrays.stream(array))
                        .boxed()
                        .collect(Collectors.groupingBy(i -> i));

//        List<Integer> searchRange = cal.keySet()
//                .stream()
//                .map(String::valueOf)
//                .mapToInt(Integer::valueOf)
//                .boxed()
//                .collect(Collectors.toList());

//        System.out.println(Arrays.toString(cal.keySet().toArray()));
//        System.out.println(Integer.valueOf(String.valueOf(cal.keySet().toArray()[cal.size()-1])));

//        List<Integer> searchRange = IntStream.rangeClosed(0, Integer.valueOf(String.valueOf(cal.keySet().toArray()[cal.size()-1])))
//                .boxed()
//                .collect(Collectors.toList());

//        System.out.println(searchRange);

//        int first = 0;
//        int last = searchRange.size();

        int search[] = new int[4];
        Arrays.fill(search, -1);

        search[0] = 0;
//        search[3] = searchRange.size() - 1;
        search[3] = (int) cal.keySet().toArray()[cal.size()-1];

        Map<Integer, Long> cache = new HashMap<>();

        while (search[0] <= search[3]) {

            if(!cache.containsKey(search[0])) cache.put(search[0], calc(cal, search[0], P, Q));
            if(!cache.containsKey(search[3])) cache.put(search[3], calc(cal, search[3], P, Q));

            if((search[3] - search[0]) < 3) {

                int mid = search[0] + (search[3] - search[0]);

                if(!cache.containsKey(mid)) cache.put(mid, calc(cal, mid, P, Q));

//                System.out.println(search[0] + " - " + mid + " - " + search[3]);
//
//                System.out.println(cache.get(search[0]) + " - "
//                        + cache.get(mid) + " - "
//                        + cache.get(search[3])
//                );

                return Math.min(cache.get(search[0]), Math.min(cache.get(search[3]), cache.get(mid)));
            }
            search[1] = (int) (Math.ceil((search[3] - search[0]) / 3) * 1);
            search[2] = (int) (Math.ceil((search[3] - search[0]) / 3) * 2);

//            System.out.println(search[0] + " - " + search[1] + " - " + search[2] + " - " + search[3]);

            if(!cache.containsKey(search[1])) cache.put(search[1], calc(cal, search[1], P, Q));
            if(!cache.containsKey(search[2])) cache.put(search[2], calc(cal, search[2], P, Q));

            List<Integer> key = new ArrayList<>(){{ for (int i : search) add(i); }};

//            System.out.println(cache.get(searchRange.get(search[0])) + " - "
//                    + cache.get(searchRange.get(search[1])) + " - "
//                    + cache.get(searchRange.get(search[2])) + " - "
//                    + cache.get(searchRange.get(search[3]))
//                    );

            Collections.sort(key, (o1, o2) -> ((cache.get(o1).compareTo(cache.get(o2)))));

//            System.out.println("ccccccccccc");
//            key.forEach(i -> {
//                System.out.print(i + " ");
//            });
//            System.out.println();
//            System.out.println("ccccccccccccc");

            search[0] = Math.min(key.get(0), key.get(1));
            search[3] = Math.max(key.get(0), key.get(1));

//            System.out.println(search[0] + " - " + search[3]);
//            System.out.println(val.indexOf(val.get(0)) + " ~ " + val.indexOf(val.get(1)));
        }


        return Long.valueOf(answer);
    }
}
