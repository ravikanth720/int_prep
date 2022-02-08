package com.github.ravikanth720.datastructures;

public class AdjNode {
	private String nodeLabel;
	private int dist;
	
	public AdjNode(String node, int dist){
		this.nodeLabel = node;
		this.dist = dist;
	}
	
	public String getNode() {
		return nodeLabel;
	}
	public void setNode(String node) {
		this.nodeLabel = node;
	}
	public int getDist() {
		return dist;
	}
	public void setDist(int dist) {
		this.dist = dist;
	}
}
