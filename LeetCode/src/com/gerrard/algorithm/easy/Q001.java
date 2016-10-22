package com.gerrard.algorithm.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q001 {

	public static void main(String[] args) {
		System.out.println("<==========第一组测试==========>");
		int[] nums1 = new int[] { 3, 2, 4 };
		int target1 = 6;
		int[] resultArray1Test1 = twoSum(nums1, target1);
		for (int a : resultArray1Test1) {
			System.out.print(a + " ");
		}
		System.out.println();
		int[] resultArray2Test1 = method_1(nums1, target1);
		for (int a : resultArray2Test1) {
			System.out.print(a + " ");
		}
		System.out.println();
		int[] resultArray3Test1 = method_2(nums1, target1);
		for (int a : resultArray3Test1) {
			System.out.print(a + " ");
		}
		System.out.println();
		int[] resultArray4Test1 = method_3(nums1, target1);
		for (int a : resultArray4Test1) {
			System.out.print(a + " ");
		}
		System.out.println();

		System.out.println("<==========第二组测试==========>");
		int[] nums2 = new int[] { 2, 7, 2, 15 };
		int target2 = 4;
		int[] resultArray1Test2 = twoSum(nums2, target2);
		for (int a : resultArray1Test2) {
			System.out.print(a + " ");
		}
		System.out.println();
		int[] resultArray2Test2 = method_1(nums2, target2);
		for (int a : resultArray2Test2) {
			System.out.print(a + " ");
		}
		System.out.println();
		int[] resultArray3Test2 = method_2(nums2, target2);
		for (int a : resultArray3Test2) {
			System.out.print(a + " ");
		}
		System.out.println();
		int[] resultArray4Test2 = method_3(nums2, target2);
		for (int a : resultArray4Test2) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	/**
	 * Map集合将数组元素作为key，下标作为value，利用Map.containsKey()方法判断已经遍历过的数字。
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			throw new IllegalArgumentException("Input error");
		}
		Map<Integer, Integer> map = new HashMap<>();
		map.put(nums[0], 0);
		for (int i = 1; i < nums.length; i++) {
			int numberToFind = target - nums[i];
			if (map.containsKey(numberToFind)) {
				return new int[] { map.get(numberToFind), i };
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	/**
	 * 二次循环，线性查找
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] method_1(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			throw new IllegalArgumentException("Input error");
		}
		for (int i = 0; i < nums.length - 1; i++) {
			int numberToFind = target - nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] == numberToFind) {
					return new int[] { i, j };
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	/**
	 * 用List集合的List.contains()方法
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] method_2(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			throw new IllegalArgumentException("Input error");
		}
		// 先将int[]转成Integer[]
		Integer[] collectionArray = new Integer[nums.length];
		for (int i = 0; i < nums.length; i++) {
			collectionArray[i] = nums[i];
		}
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(collectionArray);
		for (Integer a : list) {
			if (list.contains(target - a)) {
				// 防止原数组有重复数字的情况
				int index1 = list.indexOf(a);
				int index2 = list.lastIndexOf(target - a);
				if (index1 != index2) {
					return new int[] { index1, index2 };
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	/**
	 * 用一个List记录已经遇到的数字
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] method_3(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			throw new IllegalArgumentException("Input error");
		}
		List<Integer> list = new ArrayList<>();
		list.add(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			if (list.contains(target - nums[i])) {
				return new int[] { list.indexOf(target - nums[i]), i };
			}
			list.add(nums[i]);
		}
		throw new IllegalArgumentException("No two sum solution");
	}
}