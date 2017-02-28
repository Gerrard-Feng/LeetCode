package com.gerrard.algorithm.easy;

import java.util.Arrays;

public class Q035 {

	public static void main(String[] args) {

		int[] nums = { 1, 3, 5, 6 };
		System.out.println(searchInsert(nums, 5));
	}

	public static int searchInsert(int[] nums, int target) {
		int index = Arrays.binarySearch(nums, target);
		if (index < 0) {
			return -index - 1;
		}
		int i = index - 1;
		for (; i > -1; i--) {
			if (nums[i] != target) {
				break;
			}
		}
		return i + 1;
	}
}