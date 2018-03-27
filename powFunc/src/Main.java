import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Main {

    static BigDecimal powFunc(BigDecimal a, int b) {

        BigDecimal result = new BigDecimal(1.0);

        if(b == 1) {
            return a;
        }

        result = result.multiply(powFunc(a, b / 2));

        if (b % 2 == 0) {
            result = result.multiply(powFunc(a, b / 2));
        } else {
            result = result.multiply(powFunc(a, (b / 2) + 1));
        }

        return result;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] item = br.readLine().split(" ");

        String result = String.valueOf(powFunc(BigDecimal.valueOf(Double.valueOf(item[0])), Integer.valueOf(item[1])));

        if(result.matches("^[0-9]*(\\.)*[0-9]*(E)(\\-|\\+)*[0-9]+$")) {
            String[] temp = result.split("E");

            String zero = "";
            Integer index = Integer.valueOf(temp[1].substring(1));
            for(int i=0; i<index-1; i++) {
                zero = zero + "0";
            }

            if(temp[1].substring(0, 1).equals("-")) {
                if(temp[0].split("\\.").length > 1)
                    temp[0] = temp[0].split("\\.")[0] + temp[0].split("\\.")[1];
                result = "0." + zero + temp[0];
            } else {
                result = temp[0] + zero + "0";
            }
        }

        if(result.matches("^[0-9]+(\\.)[0]*$")) {
            result = result.split("\\.")[0];
        }

        System.out.println(result);

    }

}
