import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Sooduck, Park
 * @since 2018-05-01 [2018.May.01]
 */

public class Main {

//    static int[][] result = {
//            {1, 2, 3}
//            , {4, 5, 6}
//            , {7, 8, 0}
//    };

    static int[][] cube = {
            {1, 0, 3}
            , {4, 2, 5}
            , {7, 8, 6}
    };

    static int[] dx = {1, 0, -1,  0};
    static int[] dy = {0, 1,  0, -1};


    static int[] g = {1, 3, -1, -3};

    public static boolean puzzle(int y, int x) {

        boolean check = false;

        String result = Arrays.stream(cube).flatMap(arr -> Arrays.stream(arr).boxed()).map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(Arrays.toString(cube[0]));
        System.out.println(Arrays.toString(cube[1]));
        System.out.println(Arrays.toString(cube[2]));
        System.out.println(" -> " + cube[y][x]);

        if(result.equals("1 2 3 4 5 6 7 8 0")) {
            System.out.println("gd");
            return true;
        }

        for (int xy = 0; xy < dy.length; xy++) {
            int moveX = x + dx[xy];
            int moveY = y + dy[xy];

            if(0 <= moveX && moveX < cube.length && 0 <= moveY && moveY < cube.length) {
                int temp = cube[moveY][moveX];
                cube[moveY][moveX] = cube[y][x];
                cube[y][x] = temp;

                check = check || puzzle(moveY, moveX);
            }
        }


        return check;

    }


    public static void main(String[] args) {

//        List<Integer> cube = Arrays.asList(1, 0, 3, 4, 2, 5, 7, 8, 6);


//        List<Integer> temp = Arrays.asList(1, 0, 3);
//        pu.add(temp);
//        List<Integer> temp1 = Arrays.asList(4, 2, 5);
//        pu.add(temp1);
//        List<Integer> temp2 = Arrays.asList(7, 8, 6);
//        pu.add(temp2);

        puzzle(0, 1);

    }
}
