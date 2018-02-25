
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) {

//        ArrayList<Double> result = new ArrayList<>();

//        Scanner sc = new Scanner(System.in);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int testCaseCount = 0;
        String line = "";

        try { // 입력
             line = br.readLine();

            // 출력
//            bw.write(line);
            testCaseCount = Integer.valueOf(line);

            if(testCaseCount > 100) {
                return;
            }

            StringTokenizer stringTokenizer = null;
            // 입력
            for(int i = 0; i < testCaseCount; i++) {

                int dayCount = 0;
                int teams = 0;

                stringTokenizer = new StringTokenizer(br.readLine());

                dayCount = Integer.valueOf(stringTokenizer.nextToken());
                teams = Integer.valueOf(stringTokenizer.nextToken());

                if(dayCount < 0 || dayCount > 1000) {
                    return;
                }

                if(teams < 0 || teams > 1000 || teams > dayCount) {
                    return;
                }

                ArrayList<Integer> dayCosts = new ArrayList<>();

                stringTokenizer = new StringTokenizer(br.readLine());

                for(int j = 0; j < dayCount; j++) {
                    int temp = Integer.valueOf(stringTokenizer.nextToken());
                    dayCosts.add(temp);
                    if(temp > 100) {
                        return;
                    }
                }

//            System.out.println(dayCosts.toString());
                Double minCost = Double.valueOf(100);

                // 계산

                for(int k = teams; k < dayCosts.size()-1; k++) {
                    Double tempCost = Double.valueOf(0);

//                System.out.println("if borrow " + k + " days");

                    for (int l = 0; l <= dayCount - teams; l++) {

                        if(l + k > dayCount) {
                            continue;
                        }

//                    System.out.println(" * start index at " + l);

                        for(int n = 0, m = l; n < k; n++, m++) {

//                        System.out.print(dayCosts.get(m) + " + ");
                            tempCost = tempCost + dayCosts.get(m);

                        }
//                    System.out.println();

                        tempCost = tempCost/Double.valueOf(k);

                        if(minCost > tempCost) {
                            minCost = tempCost;
                        }
                    }


                }

//                result.add(minCost);
                System.out.println(String.format("%.11f", minCost));;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


//        for(int i = 0; i < result.size(); i++) {
//            System.out.println(String.format("%.11f", result.get(i)));
//        }
    }


}
