package com.gerrard.algorithm.medium;

import java.util.Arrays;

public class Q033 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		int[] nums1 = { 9, 0, 2, 7, 8 };
		System.out.println(binarySearch(nums1, 1, 4, 3));
		System.out.println(search(nums1, 3));

		System.out.println("<==========第二组测试==========>");
		int[] nums2 = { 3, 5, 1 };
		System.out.println(search(nums2, 0));

		System.out.println("<==========第三组测试==========>");
		int[] nums3 = { 1 };
		System.out.println(search(nums3, 1));
	}

	public static int search(int[] nums, int target) {
		// 数组长度短时，直接顺序查找速度快
		if (nums.length < 3) {
			for (int k = 0; k < nums.length; k++) {
				if (nums[k] == target) {
					return k;
				}
			}
			return -1;
		}
		// 没有旋转的情形
		if (nums[0] < nums[nums.length - 1]) {
			int index = Arrays.binarySearch(nums, target);
			return index < 0 ? -1 : index;
		}
		// 定位旋转中心 mid，即数组当前值小于上一个值的位置
		int from = 0, to = nums.length - 1, mid = nums.length - 1;
		do {
			// 防止 mid==from
			mid = (from + to) >>> 1;
			mid = mid == from ? from + 1 : mid;
			// 先判断 mid 位置
			if (nums[mid] < nums[mid - 1]) {
				break;
			}
			int midVal = nums[mid];
			if (nums[from] > midVal) {
				// 中心出现在 from-(mid-1)
				to = mid - 1;
			} else {
				// 中心出现在 (mid+1)-to
				from = mid;
			}
		} while (from < to);
		// 重置 from 和 to
		from = 0;
		to = nums.length - 1;
		int index1 = binarySearch(nums, from, mid - 1, target);
		if (index1 < 0) {
			int index2 = binarySearch(nums, mid, to, target);
			if (index2 >= 0) {
				return index2;
			}
		} else {
			return index1;
		}
		// 查找失败
		return -1;
	}

	public static int search2(int[] nums, int target) {
		if (nums.length == 0) {
			return -1;
		}
		if (nums.length == 1) {
			return nums[0] == target ? 0 : -1;
		}
		if (nums[0] < nums[nums.length - 1]) {
			int index = Arrays.binarySearch(nums, target);
			return index < 0 ? -1 : index;
		}
		int i = 0;
		for (; i < nums.length - 1; i++) {
			if (nums[i] == target) {
				return i;
			}
			if (nums[i] > nums[i + 1]) {
				break;
			}
		}
		if (nums[i] == target) {
			return i;
		}
		int[] copy = Arrays.copyOfRange(nums, i + 1, nums.length);
		int index = Arrays.binarySearch(copy, target);
		if (index >= 0) {
			return i + 1 + index;
		}
		return -1;
	}

	// 给定一个界限，部分数组是有序的，对有序的数组直接二分查找
	public static int search3(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		if (nums.length == 1) {
			return nums[0] == target ? 0 : -1;
		}
		// 第一项小于最后一项，表示未旋转
		if (nums[0] < nums[nums.length - 1]) {
			int index = Arrays.binarySearch(nums, target);
			return index < 0 ? -1 : index;
		}
		// 将带有循环的数组，缩减到一个可以接受的大小，暂定64长度
		int start = 0, end = nums.length - 1;
		while (end - start > 64) {
			int middle = (end + start) >>> 1;
			if (nums[middle] > nums[start]) {
				// 表示前半部分有序，旋转部分在后半部分
				int index = Arrays.binarySearch(Arrays.copyOfRange(nums, start, middle), target);
				// 在有序部分二分查找
				if (index >= 0) {
					return start + index;
				}
				start = middle;
			} else {
				// 后半部分有序
				int index = Arrays.binarySearch(Arrays.copyOfRange(nums, middle, end), target);
				if (index >= 0) {
					return middle + index;
				}
				end = middle;
			}
		}
		int i = start;
		// 找到旋转的位置
		for (; i < end; i++) {
			if (nums[i] == target) {
				return i;
			}
			if (nums[i] > nums[i + 1]) {
				break;
			}
		}
		if (nums[i] == target) {
			return i;
		}
		// i 是旋转的第一个值的下标
		int[] copy = Arrays.copyOfRange(nums, i + 1, nums.length);
		int index = Arrays.binarySearch(copy, target);
		if (index >= 0) {
			return i + 1 + index;
		}
		return -1;
	}

	private static int binarySearch(int[] nums, int from, int to, int target) {
		if (nums[from] > target || nums[to] < target) {
			return -1;
		}
		while (to >= from) {
			int mid = (from + to) >>> 1;
			int midVal = nums[mid];
			if (midVal > target) {
				to = mid - 1;
			} else if (midVal < target) {
				from = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
}