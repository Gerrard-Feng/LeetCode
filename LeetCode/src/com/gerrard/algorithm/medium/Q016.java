package com.gerrard.algorithm.medium;

import java.util.Arrays;

public class Q016 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		int[] array1 = { -12, -7, 0, 4, 22, -8, 2, 55, 32, 11 };
		int target1 = -7;
		System.out.println(threeSumClosest(array1, target1));
		System.out.println();

		System.out.println("<==========第二组测试==========>");
		int[] array2 = { 2, 1, 0 };
		int target2 = 3;
		System.out.println(threeSumClosest(array2, target2));
		System.out.println();

		System.out.println("<==========第三组测试==========>");
		int[] array3 = { 1, 1, -1, -1, 3 };
		int target3 = -1;
		System.out.println(threeSumClosest(array3, target3));
		System.out.println();
	}

	// 返回值是数组3个元素的和，不是差值
	public static int threeSumClosest(int[] nums, int target) {
		if (nums == null || nums.length < 3) {
			throw new IllegalArgumentException("Input error");
		}
		Arrays.sort(nums);
		// 初始差值和返回值
		int returnNo = Integer.MAX_VALUE;
		int closest = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length - 2; i++) {
			// 数组剩余部分夹逼
			int left = i + 1;
			int right = nums.length - 1;
			// 转化为2Sum Closest问题
			int remainTarget = target - nums[i];
			int sum;
			while (left < right) {
				sum = nums[left] + nums[right];
				// 解唯一确定，直接返回
				if (remainTarget - sum == 0) {
					return sum + nums[i];
				}
				// 最小值替换，返回值赋值
				int temp = Math.abs(remainTarget - sum);
				if (temp < closest) {
					returnNo = nums[i] + nums[left] + nums[right];
					closest = temp;
				}
				if (remainTarget - sum > 0) {
					left++;
				} else {
					right--;
				}
			}
		}
		return returnNo;
	}
}