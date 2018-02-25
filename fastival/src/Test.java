import java.io.*;

public class Test {

    public static void main(String[] args) {

//        final int BUFFER_SIZE = 1024;
//        final char[] buffer = new char[BUFFER_SIZE];
//
//        final  StringBuilder out = new StringBuilder();
//        InputStreamReader isr = new InputStreamReader(System.in);
//
//        try {
//
//            while (true) {
//                int rsz = isr.read(buffer, 0, buffer.length);
//                if(rsz < 0) {
//                    break;
//                }
//                out.append(buffer,0,rsz);
//
//
//                System.out.println(out.toString());
//                out.delete(0, out.length());
//
//            }
//
//
//
////            System.out.println(out.toString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);

        try { // 입력
            String line = br.readLine();

            // 출력
            bw.write(line);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
