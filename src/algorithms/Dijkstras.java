package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import datastructures.AdjNode;
import datastructures.FibonacciHeap;
import datastructures.FibonacciHeap.Node;

public class Dijkstras {

	public HashMap<String, Integer> shortestPath(
			HashMap<String, ArrayList<AdjNode>> cityMap, String source,
			String dest, HashSet<String> knodes) {
		FibonacciHeap<Integer> fheap = new FibonacciHeap<Integer>();
		HashSet<String> visited = new HashSet<String>();
		HashMap<String, Integer> distArray = new HashMap<String, Integer>();
		HashMap<String, FibonacciHeap.Node<Integer>> nodeLookup = new HashMap<String, FibonacciHeap.Node<Integer>>();
		// initialize FibHeap
		for (String city : cityMap.keySet()) {
			FibonacciHeap.Node<Integer> fibcitynode = null;
			if (city.equals(source)) {
				fibcitynode = fheap.insert(new Integer(0), city);
			
			} else {
				fibcitynode = fheap.insert(Integer.MAX_VALUE, city);
			}
			nodeLookup.put(city, fibcitynode);
			for (AdjNode neighbors : cityMap.get(city)) {
		//		System.out.println("---" + city);
				if (!nodeLookup.containsKey(neighbors.getNode())) {
			//		System.out.println("-------" + neighbors.getNode());
					fibcitynode = neighbors.getNode() == source ? fheap.insert(
							new Integer(0), neighbors.getNode()) : fheap
							.insert(Integer.MAX_VALUE, neighbors.getNode());
					nodeLookup.put(neighbors.getNode(), fibcitynode);
				}
			}
		}

		

		while (!fheap.isEmpty()) {
			Node<Integer> currNode = fheap.extractMin();

			if (currNode.getLabel().equals(dest)) {
				distArray.put(dest, currNode.getKey());
				break;
			}
			if (!visited.contains(currNode.getLabel())) {
				// Add to visited list
				visited.add(currNode.getLabel());
				if (knodes.contains(currNode.getLabel())) {
					distArray.put(currNode.getLabel(), currNode.getKey());
				}
				ArrayList<AdjNode> neighbors = cityMap.get(currNode.getLabel());
				int neighborslen = neighbors==null?0:neighbors.size();
				for(int i=0; i<neighborslen; i++){
				//for (AdjNode neighbor : cityMap.get(currNode.getLabel())) {
					//System.out.println("in loop");
					
					int ndist = currNode.getKey() + neighbors.get(i).getDist();
					Node<Integer> currNeighbor = nodeLookup.get(neighbors.get(i)
							.getNode());
					if (currNeighbor.getKey() > ndist)
						fheap.decreaseKey(currNeighbor, ndist);
				}
			}

		}

		return distArray;
	}

}
