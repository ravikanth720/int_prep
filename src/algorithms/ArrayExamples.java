

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class ArrayExamples {
	public int[] cellCompetition(int[] arr, int days){
		while(days > 0){
			int n = arr.length;
			int[] res = new int[n];
			
			for(int i=1; i<n-1; i++){
				res[i] = arr[i-1]^arr[i+1];
			}
			
			res[0] = 0^arr[1];
			res[n-1] = arr[n-2]^0;
			
			arr = res;
			System.out.println(Arrays.toString(arr));
			days--;
		}
		return arr;
	}
	
	public int kMin(int[] arr, int k) {
	  Queue<Integer> pq = new PriorityQueue<Integer> (new Comparator<Integer>(){
	    public int compare(Integer a, Integer b) {
	      return b - a;
	    }
	  });
	  
	  for (int i=0; i<arr.length; i++) {
	    if(k > i) {
	      pq.add(arr[i]);
	    } else {
	      if(pq.peek() > arr[i]) {
	        pq.poll();
	        pq.add(arr[i]);
	      }
	    }
	  }
	  return pq.peek();
	}

	// Searches Sorted Rotated array 
	public int searchRotated(int[] arr, int start, int end, int target) {
		if (arr == null || arr.length == 0) return -1;

		int mid = (start + end) / 2;

		if (target == arr[mid]) return target;

		else if (target > arr[mid] && arr[start] < arr[mid]) {
			// go right
		}

		else if (target > arr[mid] && arr[start] > target) {}

		return -1;
	}
	
	public static void main(String[] args) {
		ArrayExamples obj =  new ArrayExamples();
		int[] arr = {1,1,1,0,1,1,1,1};
		//obj.cellCompetition(arr, 2);
		System.out.println(obj.kMin(new int[]{2,8,3,5,10,9,12,11},5));
		System.out.println("lot".compareTo("lot"));
	}
}
