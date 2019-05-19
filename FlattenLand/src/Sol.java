import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sooduck, Park
 * @since 2019-05-12 [2019.May.12]
 */

public class Sol {

    public long calc(Map<Object, List<Long>> cal, long value, int P, int Q) {

        long result = 0;

//        cal.forEach((k, v) -> {
//            System.out.println(k + " -- " + v.size());
//        });

        Iterator i = cal.values().iterator();

//        System.out.println(value+ "]------------------");
        while (i.hasNext()) {
            List<Long> l = (List<Long>) i.next();

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

        Map<Object, List<Long>> cal =
                Arrays.stream(land)
                        .flatMapToLong(array -> Arrays.stream(array).asLongStream())
                        .boxed()
                        .collect(Collectors.groupingBy(i -> i));

//        System.out.println(calc(cal, 0, P, Q));;
        List<Long> searchRange = cal.keySet()
                .stream()
                .map(String::valueOf)
                .mapToLong(Long::valueOf)
                .boxed()
                .collect(Collectors.toList());

//        System.out.println(searchRange);

//        int first = 0;
//        int last = searchRange.size();

        int search[] = new int[4];
        Arrays.fill(search, -1);

        search[0] = 0;
        search[3] = searchRange.size()-1;

//        System.out.println(first + " - " + last);

        Map<Long, Long> cache = new HashMap<>();

        while (search[0] <= search[3]) {

            if(!cache.containsKey(searchRange.get(search[0]))) cache.put(searchRange.get(search[0]), calc(cal, searchRange.get(search[0]), P, Q));
            if(!cache.containsKey(searchRange.get(search[3]))) cache.put(searchRange.get(search[3]), calc(cal, searchRange.get(search[3]), P, Q));

            if((searchRange.subList(searchRange.indexOf(searchRange.get(search[0])), searchRange.indexOf(searchRange.get(search[3]))).size()) < 3) {

                int mid = Math.round(search[0] + (search[3] - search[0]));

                if(!cache.containsKey(searchRange.get(mid))) cache.put(searchRange.get(mid), calc(cal, searchRange.get(mid), P, Q));

                return Math.min(cache.get(searchRange.get(search[0])), Math.min(cache.get(searchRange.get(search[3])), cache.get(searchRange.get(mid))));
            }
            search[1] = (int) (Math.ceil((search[3] - search[0]) / 3) * 1);
            search[2] = (int) (Math.ceil((search[3] - search[0]) / 3) * 2);

//            System.out.println(search[0] + " - " + search[1] + " - " + search[2] + " - " + search[3]);

            if(!cache.containsKey(searchRange.get(search[1]))) cache.put(searchRange.get(search[1]), calc(cal, searchRange.get(search[1]), P, Q));
            if(!cache.containsKey(searchRange.get(search[2]))) cache.put(searchRange.get(search[2]), calc(cal, searchRange.get(search[2]), P, Q));

            List<Long> val = new ArrayList<>();
            for (int i : search) {
                val.add(cache.get(searchRange.get(i)));
            }
            Collections.sort(val);

            search[0] = val.indexOf(val.get(0));
            search[3] = val.indexOf(val.get(1));

//            System.out.println(val.indexOf(val.get(0)) + " ~ " + val.indexOf(val.get(1)));
        }


        return answer;
    }
}
