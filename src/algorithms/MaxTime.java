import java.util.*;

class MaxTime {
  int minimumTime(int numOfSubFiles, List<Integer> files) {
    Queue<Integer> pq = new PriorityQueue<Integer>(files);
    int minimumTime = 0;

    if (numOfSubFiles == 1) return files.get(0);

    while(pq.size() > 1) {
      int timeForMinTwoFiles = pq.poll() + pq.poll();
      minimumTime += timeForMinTwoFiles;
      pq.add(timeForMinTwoFiles);
    }

    return minimumTime;
  }

  public static void main(String[] args) {
    MaxTime m = new MaxTime();
    System.out.println(m.minimumTime(2, Arrays.asList(6, 10)));
  }
}