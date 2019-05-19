

public class Main {

    public static void main(String[] args) {

        long result;

        System.out.println("Hello World!");

//        long result = new Solution().solution(new int[][]{{1, 2}
//                                                           , {2, 3}}, 3, 2);

//        long result = new Solution().solution(new int[][]{{4, 4, 3}
//                                                          , {3, 2, 2}
//                                                          , {2, 1, 0}}, 5, 3);


//        long result = new Solution().solution(new int[][]{{4, 4, 3, 2}
//                                                        , {3, 2, 2, 0}
//                                                        , {2, 1, 0, 3}
//                                                        , {6, 3, 4 ,1}}, 2, 3);


//        result = new Solution().solution(new int[][]{{0, 0, 0, 0}
//                , {0, 0, 0, 0}
//                , {0, 0, 0, 0}
//                , {6, 0, 0, 0}}, 2, 103);

//11 * 6 * 2 12 * 11
//        long result = new Solution().solution(new int[][]{{0, 1, 3, 2}
//                                                        , {2, 0, 2, 0}
//                                                        , {9, 1, 0, 3}
//                                                        , {7, 2, 3 ,1}}, 2, 3);

//          long result = new Solution().solution(new int[][]{{1, 5, 7}
//                                                          , {1, 5, 7}
//                                                          , {1, 5, 7}}, 3, 5);


//        long result = new Solution().solution(new int[][]{{3, 3, 6, 0}
//                , {5, 3, 5, 2}
//                , {0, 5, 3, 5}
//                , {5, 4, 2, 3}}, 2, 103);




        result = new Solution().solution(new int[][]{{0, 1, 3, 2}
                , {2, 0, 2, 0}
                , {9, 1, 0, 3}
                , {7, 2, 3 ,1}}, 2, 3);

        System.out.println("Solution result : " + result);


//        long result = new Sol().solution(new int[][]{{4, 4, 3}
//                , {3, 2, 2}
//                , {2, 1, 0}}, 5, 3);


        System.out.println("--------------------------------------------");
//        result = new Sol().solution(new int[][]{{0, 0, 0, 0}
//                , {0, 0, 0, 0}
//                , {0, 0, 0, 0}
//                , {6, 0, 0, 0}}, 2, 103);

        result = new Sol().solution(new int[][]{{0, 1, 3, 2}
                                                        , {2, 0, 2, 0}
                                                        , {9, 1, 0, 3}
                                                        , {7, 2, 3 ,1}}, 2, 3);
        System.out.println("Sol result : " + result);
    }
}
