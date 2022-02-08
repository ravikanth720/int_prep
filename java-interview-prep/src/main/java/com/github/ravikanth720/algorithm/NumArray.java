package com.github.ravikanth720.algorithm;
import java.util.*;
public class NumArray {
    int[][] nums;
    public NumArray(int[] nums) {
        int length = nums.length;
        this.nums = new int[length][length];
        for(int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum +=  nums[j];
                this.nums[i][j] = sum;
            }
        }
    }

    public int sumRange(int i, int j) {
        return this.nums[i][j];
    }
    
    public static void main(String[] args) {
        NumArray na = new NumArray(new int[]{-2, 0, 3, 5, -1, 6});
        System.out.println(na.sumRange(0, 2));
    }
}