
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static List<Integer> input = new ArrayList<>();

    static int fence(int left, int right) {

        if(left == right) return input.get(left);

        int mid = (left + right) / 2;

        int ret = Math.max(fence(left, mid), fence(mid+1, right));

        int lo = mid, hi = mid+1;

        int height = Math.min(input.get(lo), input.get(hi));
        ret = Math.max(ret, height*2);

        while (left < lo || hi < right) {
            if(hi < right && (lo == left || input.get(lo-1) < input.get(hi+1))) {
                ++hi;
                height = Math.min(height, input.get(hi));
            } else {
                --lo;
                height = Math.min(height, input.get(lo));
            }

            ret = Math.max(ret, height * (hi - lo  + 1));
        }

        return ret;

    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++) {
            String low = br.readLine();
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
            int result = fence(0,input.size()-1);
            System.out.println(result);
        }
    }
}
