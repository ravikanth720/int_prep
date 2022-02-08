package com.github.ravikanth720.algorithm;

public class MxMin {
	public int maxMin(int[][] arr){
		int max;
		if(arr == null){
			return 0;
		}
		int cl = arr.length;
		int rl = arr[0].length;
		arr[0][0] = Integer.MAX_VALUE;
		
		max = recurseMax(arr, 0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		return max;
	}

	private int recurseMax(int[][] arr, int i, int j, int minValue, int maxValue) {
		System.out.println("val = "+arr[i][j]+" i = "+i+" j = "+j);
		
		if(i==arr.length-1 && j==arr[0].length-1){
			
			if(maxValue<minValue){
				maxValue=minValue;
			}
		}
		
		if(minValue >arr[i][j] ){
			minValue =arr[i][j];
		}
		
		if(i+1<arr.length)
			maxValue = recurseMax(arr, i+1, j, minValue, maxValue);
		if(j+1<arr[0].length)
			maxValue = recurseMax(arr, i, j+1, minValue, maxValue);
		
		return maxValue;
	}
	
	public static void main(String[] args) {
		int[][] arr = {{1,2,3,4},{6,5,4,7},{9,7,8, 9}};
		MxMin obj = new MxMin();
		System.out.println(obj.maxMin(arr));
	}
}
