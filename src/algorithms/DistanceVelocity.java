package algorithms;

import java.util.Arrays;

public class DistanceVelocity {

    public static double solution1(double[][] readings, long endTime) {
        double dist = 0;
        if (readings.length == 0) return 0;
        double speed = 0, time = 0;

        Arrays.sort(readings, (r1, r2) -> (int)(r1[0] - r2[0]));
        for (double[] reading: readings) {
            if (endTime > reading[0]) {
                dist += speed * ((reading[0] - time)/3600);
                speed = reading[1];
                time = reading[0];
            } else {
                dist += speed * ((endTime - time)/3600);

                return dist;
            }
        }

        return dist + (speed * ((endTime-time))/3600);
    }

    public static double solution(double[][] readings, long endTime) {
        double Totaldistance=0;
        double speedAtStart=readings[0][1];
        double time=0;
        for(int i=1;i<readings.length;i++){

            if (endTime > readings[i][0]) {
                Totaldistance += speedAtStart * ((readings[i][0] - time) / 3600);
                time = readings[i][0];
                speedAtStart = readings[i][1];
            } else {
                Totaldistance += speedAtStart * ((endTime-time)/3600);

                return Totaldistance;
            }
        }

        Totaldistance += speedAtStart * ((endTime-time)/3600);

        return Totaldistance;
    }

    public static void main(String[] args) {
        DistanceVelocity d = new DistanceVelocity();

        System.out.println(d.solution1(new double[][] {new double[]{0, 90}, new double[]{60, 98}, new double[]{155, 85.5}}, 600));
    }
}
