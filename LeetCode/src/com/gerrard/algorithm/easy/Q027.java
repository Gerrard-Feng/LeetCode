package com.gerrard.algorithm.easy;

public class Q027 {

	public static void main(String[] args) {

		int[] nums = { 3, 2, 2, 3, 4 };
		int l = removeElement(nums, 3);
		for (int i = 0; i < l; i++) {
			System.out.print(nums[i] + " ");
		}
	}

	public static int removeElement(int[] nums, int val) {
		if (nums == null) {
			throw new IllegalArgumentException("Input error");
		}
		if (nums.length == 0) {
			return 0;
		}
		// 慢指针，出现匹配时移动
		int i = nums.length - 1;
		// 倒序遍历
		for (int j = nums.length - 1; j > -1; j--) {
			if (nums[j] == val) {
				nums[j] = nums[i];
				i--;
			}
		}
		return i + 1;
	}
}