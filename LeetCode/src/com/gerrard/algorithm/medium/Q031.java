package com.gerrard.algorithm.medium;

public class Q031 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		int[] nums1 = { 5, 4, 7, 5, 3, 2 };
		nextPermutation(nums1);
		for (int a : nums1) {
			System.out.print(a);
		}
		System.out.println();

		System.out.println("<==========第二组测试==========>");
		int[] nums2 = { 3, 2, 1 };
		nextPermutation(nums2);
		for (int a : nums2) {
			System.out.print(a);
		}
		System.out.println();

		System.out.println("<==========第三组测试==========>");
		int[] nums3 = { 2, 3, 1 };
		nextPermutation(nums3);
		for (int a : nums3) {
			System.out.print(a);
		}
		System.out.println();
	}

	public static void nextPermutation(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}
		// 自后向前，找到第一个逆序排列的数字，如1239877621中的3
		for (int i = nums.length - 1; i > 0; i--) {
			if (nums[i] > nums[i - 1]) {
				// 自那个数字3向后找，第一个比3小的数字的前一个数字，与3交换，如1239877621->1269877321
				int j = i + 1;
				for (; j < nums.length; j++) {
					if (nums[i - 1] >= nums[j]) {
						swap(nums, i - 1, j - 1);
						break;
					}
				}
				// 如果没有以上情况，和最后一个数字交换，如12398776->12698773
				if (j == nums.length) {
					swap(nums, i - 1, nums.length - 1);
				}
				// 第 i 项之后，整体倒序，如1239877621->1269877321->1261237789
				// 倒序时长度分奇偶，注意两点：结束的位置、交换的下标
				int sum = i + nums.length - 1;
				for (int k = i; k < (sum + 1) / 2; k++) {
					swap(nums, k, sum - k);
				}
				return;
			}
		}
		// 数组整体已经最大，将数组倒序，如：987654321->123456789
		for (int i = 0; i < nums.length / 2; i++) {
			swap(nums, i, nums.length - 1 - i);
		}
	}

	private static void swap(int[] nums, int i, int j) {
		nums[i] = nums[i] + nums[j] - (nums[j] = nums[i]);
	}
}