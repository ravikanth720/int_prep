import java.util.*;

public class Distance {
  public class Point {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return "(" + x + ", " + y + ")";
		}
  }

	public Set<Point> findClosestPoints(Set<Point> allPoints, Point referencePoint, int k) {
		Queue<Point> pq = new PriorityQueue<Point>((p1, p2) -> {
			double d1 = Math.pow(p1.x - referencePoint.x, 2) + Math.pow(p1.y - referencePoint.y, 2);
			double d2 = Math.pow(p2.x - referencePoint.x, 2) + Math.pow(p2.y - referencePoint.y, 2);

			return (int)(d2 - d1);
		});

		for (Point p: allPoints) {
			pq.add(p);
			if (pq.size() > k) pq.poll();
		}

		return new HashSet(pq);
	}
	
	public void input(){
		Set<Point> points = new HashSet<Point>();
				Point origin = new Point(0,0);
				points.add(new Point(1,1));
				points.add(new Point(15,5));
				points.add(new Point(0,1));
        System.out.println(findClosestPoints(points, origin, 2));
	}

	public static void main(String[] args) {
		Distance d = new Distance();
		d.input();
	}	
}

