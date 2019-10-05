package algorithms;

import java.util.HashMap;
import java.util.Map;

class SimilarTriangles {
  public static void main(String[] args) {
    SimilarTriangles s = new SimilarTriangles();
   // System.out.println(s.areSimilar(new int[]{0,0,0,1,1,0,0,0,0,-3,-3,0}));
   System.out.println(s.countPairsWithSum(8, new int[]{2,3,6,2,8}));
  }

  boolean areTrianglesSimilar(int[] coordinates) {
    double epsilon = 0.000001;
    // triangle 1
    int t1absq = squareLength(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
    int t1bcsq = squareLength(coordinates[2], coordinates[3], coordinates[4], coordinates[5]);
    int t1casq = squareLength(coordinates[4], coordinates[5], coordinates[0], coordinates[1]);

    float t1ab = (float)Math.sqrt(t1absq);
    float t1bc = (float)Math.sqrt(t1bcsq);
    float t1ca = (float)Math.sqrt(t1casq);
    //triangle 2
    int t2absq = squareLength(coordinates[6], coordinates[7], coordinates[8], coordinates[9]);
    int t2bcsq = squareLength(coordinates[8], coordinates[9], coordinates[10], coordinates[11]);
    int t2casq = squareLength(coordinates[10], coordinates[11], coordinates[6], coordinates[7]);

    float t2ab = (float)Math.sqrt(t2absq);
    float t2bc = (float)Math.sqrt(t2bcsq);
    float t2ca = (float)Math.sqrt(t2casq);

    return (Math.abs(Math.acos((t1bcsq + t1casq - t1absq)/(2*t1bc*t1ca)) - Math.acos((t2bcsq + t2casq - t2absq)/(2*t2bc*t2ca))) < epsilon) 
            && (Math.abs(Math.acos((t1absq + t1bcsq - t1casq)/(2*t1ab*t1bc)) - Math.acos((t2absq + t2bcsq - t2casq)/(2*t2ab*t2bc))) < epsilon)
            && (Math.abs(Math.acos((t1casq + t1absq - t1bcsq)/(2*t1ca*t1ab)) - Math.acos((t2casq + t2absq - t2bcsq)/(2*t2ca*t2ab))) < epsilon);
  }

  int squareLength(int x1, int y1, int x2, int y2) {
    int x = x1 - x2;
    int y = y1 - y2;

    return x*x + y*y;
  }

  int countPairsWithSum(int k, int[] a) {
    
    Map<Integer, Boolean> freq = new HashMap<>();
    int count = 0;      

    for(int i=0; i<a.length; i++) {
        int curr = a[i];
        int diff = k - a[i];

        if (!freq.getOrDefault(curr, false)) {
          if(freq.containsKey(diff)) {
            freq.put(diff, true);
            freq.put(curr, true);
            count++;
          } else {
              freq.put(curr, false);
          }
        }
    }

    return count;
}
}