package com.github.ravikanth720.algorithm;
//package algorithms;

import java.util.*;

public class Mooshak {
	
	static int removeObstacle(int numRows, int numCols, List<List<Integer>> lot) {
		int minDist = traverse(lot, 0, 0, 0, Integer.MAX_VALUE);
		return minDist == Integer.MAX_VALUE ? -1 : minDist;
	}

	static int traverse(List<List<Integer>> lot, int i, int j, int dist, int minDist) {
		if (lot.get(i).get(j) == 9) {
			minDist = Math.min(dist, minDist);
			return minDist;
		}

		if (lot.get(i).get(j) == 0) {
			return Integer.MAX_VALUE;
		}

		lot.get(i).set(j, 0);

		if (i < (lot.size() -1)) minDist = Math.min(minDist, traverse(lot, i+1, j, dist + 1, minDist));
		if (i > 0) minDist = Math.min(minDist, traverse(lot, i-1, j, dist + 1, minDist));
		if (j < (lot.get(0).size() -1)) minDist = Math.min(minDist, traverse(lot, i, j+1, dist + 1, minDist));
		if (j > 0) minDist = Math.min(minDist, traverse(lot, i, j-1, dist + 1, minDist));

		lot.get(i).set(j, 1);

		return minDist;
	}
	
	public static void main(String[] args) {
		System.out.println(removeObstacle(1, 1, Arrays.asList(Arrays.asList(1))));
	}
}

/**
 * 
 * 1 0 0
 * 1 1 0
 * 0 1 9
 * 
 */