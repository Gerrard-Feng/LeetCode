package com.gerrard.algorithm.medium;

import java.util.Arrays;

public class Q034 {

	public static void main(String[] args) {

		int[] nums = { 5, 7, 7, 8, 8, 10 };
		int[] result = searchRange(nums, 8);
		for (int a : result) {
			System.out.print(a + " ");
		}
	}

	// 二分查找法定位数字
	public static int[] searchRange(int[] nums, int target) {
		int[] result = { -1, -1 };
		if (nums == null || nums.length == 0) {
			return result;
		}
		int index = Arrays.binarySearch(nums, target);
		if (index < 0) {
			return result;
		}
		int i = index - 1;
		// 从定位的位置向两侧寻找
		for (; i > -1; i--) {
			if (nums[i] != target) {
				result[0] = i + 1;
				break;
			}
		}
		int j = index + 1;
		for (; j < nums.length; j++) {
			if (nums[j] != target) {
				result[1] = j - 1;
				break;
			}
		}
		// 防止边界出现在两侧
		if (i == -1) {
			result[0] = 0;
		}
		if (j == nums.length) {
			result[1] = nums.length - 1;
		}
		return result;
	}

	// 顺序查找法
	public static int[] searchRange2(int[] nums, int target) {
		int[] result = { -1, -1 };
		boolean flag = false;
		for (int k = 0; k < nums.length; k++) {
			if (nums[k] == target) {
				if (!flag) {
					result[0] = result[1] = k;
					flag = true;
				} else {
					result[1] = k;
				}
			} else if (nums[k] > target) {
				break;
			}
		}
		return result;
	}
}