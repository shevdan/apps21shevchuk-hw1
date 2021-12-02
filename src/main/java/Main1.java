
import ua.edu.ucu.tempseries.TemperatureSeriesAnalysis;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] arr = { 3, 4, 5, 6, 7, 8, 9};
//        for (int i = 0; i < 10; i++){
//            arr[i] = sc.nextInt();
//        }

        TemperatureSeriesAnalysis tmp = new TemperatureSeriesAnalysis(arr);
        System.out.println(tmp.getTempArray().length);
        System.out.println(tmp.toString());
        System.out.println(tmp.average());
        System.out.println(tmp.deviation());
        System.out.println(tmp.min());
        System.out.println(tmp.max());
        System.out.println(tmp.findTempClosestToValue(5));
        System.out.println(tmp.findTempClosestToZero());
        double[] res = tmp.findTempsGreaterThen(5);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++){
            sb.append(res[i]);
            sb.append(" ");
        }
        System.out.println(sb.toString());
        res = tmp.findTempsLessThen(5);
        sb.delete(0, sb.length());
        for (int i = 0; i < res.length; i++){
            sb.append(res[i]);
            sb.append(" ");
        }
        System.out.println(sb.toString());

    }
}
