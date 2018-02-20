import java.util.ArrayList;
import java.util.Scanner;

public class Fastival {

    public static void main(String[] args) {

        ArrayList<Float> result = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        int testCaseCount = sc.nextInt();

        if(testCaseCount > 100) {
            return;
        }

        // 입력
        for(int i = 0; i < testCaseCount; i++) {

            int dayCount = sc.nextInt();
            if(dayCount < 0 || dayCount > 1000) {
                return;
            }

            int teams = sc.nextInt();
            if(teams < 0 || teams > 1000 || teams > dayCount) {
                return;
            }

            ArrayList<Integer> dayCosts = new ArrayList<>();

            for(int j = 0; j < dayCount; j++) {
                dayCosts.add(sc.nextInt());
                if(dayCosts.get(j) > 100) {
                    return;
                }
            }

//            System.out.println(dayCosts.toString());
            Float minCost = Float.valueOf(100);

            // 계산

            for(int k = teams; k < dayCosts.size()-1; k++) {
                Float tempCost = Float.valueOf(0);

//                System.out.println("if borrow " + k + " days");

                for (int l = 0; l <= dayCosts.size() - teams; l++) {

                    if(l + k > dayCosts.size()) {
                            continue;
                    }

//                    System.out.println(" * start index at " + l);

                    for(int n = 0, m = l; n < k; n++, m++) {

//                        System.out.print(dayCosts.get(m) + " + ");
                        tempCost = tempCost + dayCosts.get(m);

                    }
//                    System.out.println();

                    tempCost = tempCost/Float.valueOf(k);

                    if(minCost > tempCost) {
                        minCost = tempCost;
                    }
                }


            }

            result.add(minCost);

        }

        for(int i = 0; i < result.size(); i++) {
            System.out.println(String.format("%.11f", result.get(i)));
        }
    }
}
