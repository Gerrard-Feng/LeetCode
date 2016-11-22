package com.gerrard.algorithm.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q015 {

	public static void main(String[] args) {
		System.out.println("<==========第一组测试==========>");
		int[] array1 = new int[] { -1, 0, 1, 2, -1, -4 };
		show(threeSum(array1));

		System.out.println("<==========第二组测试==========>");
		int[] array2 = new int[] { -1, 0, 1, 2, -1, -4, -2, -2, -7, 3, 0, 0, 0, 0, -100, 55, 6, 8, 9, 23, 12, 33, -9,
				-8, -6, 11, 23 };
		show(threeSum(array2));

		System.out.println("<==========第三组测试==========>");
		int[] array3 = new int[] { 1, 2, -2, -1 };
		show(threeSum(array3));

		System.out.println("<==========第四组测试==========>");
		int[] array4 = new int[] { -1, 0, 1, 2, -1, -4 };
		show(threeSum(array4));
	}

	private static void show(List<List<Integer>> result) {
		System.out.println("[");
		for (List<Integer> list : result) {
			System.out.print("  " + "[");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i));
				if (i != list.size() - 1) {
					System.out.print(",");
				}
			}
			System.out.print("]");
			System.out.println();
		}
		System.out.println("]");
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		if (nums == null || nums.length < 3) {
			throw new IllegalArgumentException("Input error");
		}
		List<List<Integer>> result = new ArrayList<>();
		// 先给数组排序
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			// 第一个数大于0，之后更加没机会了
			if (nums[i] > 0) {
				return result;
			}
			// 先判断一下，如果当前值和上个值相同，直接跳过，注意第一位是没有“上一位”的概念的
			if (i != 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			// 下次探寻终点
			int lengthEnd = nums.length;
			for (int j = i + 1; j < lengthEnd - 1; j++) {
				// 内循环第一个数，要大于0-array[i]
				if (nums[j] > -nums[i]) {
					break;
				}
				// 同样的判断，第一次是没有必要的
				if (j != i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				// 定下两个，其实下一个也就决定了
				int numberToFind = -(nums[i] + nums[j]);
				// Arrays.binarySearch()方法只能在有序数组中使用
				// 注意不是在整个数组上用这个方法，是在剩余数组上（夹逼过的）
				int[] remian = Arrays.copyOfRange(nums, j + 1, lengthEnd);
				int index = Arrays.binarySearch(remian, numberToFind);
				// Arrays.binarySearch()查找失败时，会返回一个负值
				// JAVA_API：这保证了当且仅当此键被找到时，返回的值将 >= 0。
				if (index >= 0) {
					// 因为有序，下一个array[j]一定比现在的大或等于（有去重要求）
					// 而index之后的肯定比array[index]大，再之后的就没有必要遍历了
					// 注意+1，因为lengthEnd是指长度而不是下标
					// 再注意，这里的index，是去掉原数组前j项的index，加回去
					lengthEnd = index + 1 + j;
					// 这里可以保证array[i]<=array[j]<=array[index]，不必担心Set集合接收重复问题
					List<Integer> list = new ArrayList<>();
					list.add(nums[i]);
					list.add(nums[j]);
					list.add(nums[index + j + 1]);
					result.add(list);
				}
			}
		}
		return result;
	}
}