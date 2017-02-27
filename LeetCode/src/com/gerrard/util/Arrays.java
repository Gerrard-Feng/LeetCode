package com.gerrard.util;

public class Arrays {

	/**
	 * 在一个无序数组 nums 中，下标 from-to 的位置中数组有序（升序）
	 * 在这段有序的位置中寻找 target 的下标位置，如果没有，返回 -1
	 * 
	 * @param nums 数组
	 * @param from 开始下标
	 * @param to 结束下标
	 * @param target 目标数字
	 * @return
	 */
	public static int binarySearch(int[] nums, int from, int to, int target) {
		while(to > from) {
			int middle = (from + to) >>> 1;
			int midVal = nums[middle];
			if (midVal > target) {
				from = middle;
			} else if (midVal < target) {
				to = middle;
			} else {
				return middle;
			}
		}
		return -1;
	}
}