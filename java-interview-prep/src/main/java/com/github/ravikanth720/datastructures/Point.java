package com.github.ravikanth720.datastructures;

public class Point {

	public int x, y;
	public double dist;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, Point origin){
		this.x = x;
		this.y = y;
		this.dist = Math.sqrt(Math.pow(x-origin.x, 2) + Math.pow(y - origin.y, 2));
	}
	/*public int compareTo(Point ob) {
		return Double.compare(ob.dist, this.dist);
	}*/
	public String toString(){
		return "("+this.x+", "+this.y+")";
	}
	public void display(){
		System.out.println("Kth Closest Point in ("+this.x+", "+this.y+")");
		System.out.println("Distance from origin is "+this.dist);
	}

}
