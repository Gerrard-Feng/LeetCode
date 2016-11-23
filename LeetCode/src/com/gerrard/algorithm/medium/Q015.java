package com.gerrard.algorithm.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q015 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		int[] array1 = new int[] { -1, 0, 1, 2, -1, -4 };
		show(threeSum(array1));
		show(method(array1));

		System.out.println("<==========第二组测试==========>");
		int[] array2 = new int[] { -1, 0, 1, 2, -1, -4, -2, -2, -7, 3, 0, 0, 0, 0, -100, 55, 6, 8, 9, 23, 12, 33, -9,
				-8, -6, 11, 23 };
		show(threeSum(array2));
		show(method(array2));

		System.out.println("<==========第三组测试==========>");
		int[] array3 = new int[] { 1, 2, -2, -1 };
		show(threeSum(array3));
		show(method(array3));

		System.out.println("<==========第四组测试==========>");
		int[] array4 = new int[] { -1, 0, 1, 2, -1, -4 };
		show(threeSum(array4));
		show(method(array4));

		System.out.println("<==========第五组测试==========>");
		int[] array5 = new int[] { 0, 0, 0, 0 };
		show(threeSum(array5));
		show(method(array5));
	}

	private static void show(List<List<Integer>> result) {
		System.out.print("{");
		for (List<Integer> list : result) {
			System.out.print("  " + "[");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i));
				if (i != list.size() - 1) {
					System.out.print(",");
				}
			}
			System.out.print("]");
		}
		System.out.println("  " + "}");
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		if (nums == null || nums.length < 3) {
			throw new IllegalArgumentException("Input error");
		}
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		int previous = 1;
		for (int i = 0; i < nums.length - 2; i++) {
			if (nums[i] > 0) {
				return result;
			}
			if (nums[i] == previous) {
				continue;
			} else {
				previous = nums[i];
			}
			int left = i + 1;
			int right = nums.length - 1;
			int numberToFind = -nums[i];
			// 维护上一个左值和右值
			int preLeft = Integer.MIN_VALUE, preRight = Integer.MIN_VALUE;
			// 夹逼剩余两数之和
			while (left < right) {
				if (nums[left] == preLeft) {
					left++;
					continue;
				}
				if (nums[right] == preRight) {
					right--;
					continue;
				}
				int sum = nums[left] + nums[right];
				// 找到不能退出循环，还有其他可能性
				if (sum == numberToFind) {
					List<Integer> list = new ArrayList<>();
					list.add(nums[i]);
					list.add(nums[left]);
					list.add(nums[right]);
					result.add(list);
				}
				if (sum < numberToFind) {
					preLeft = nums[left];
					left++;
				} else {
					preRight = nums[right];
					right--;
				}
			}
		}
		return result;
	}

	private static List<List<Integer>> method(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		// 先给数组排序
		Arrays.sort(nums);
		int previous = 1;
		for (int i = 0; i < nums.length - 2; i++) {
			// 第一个数大于0，之后更加没机会了
			if (nums[i] > 0) {
				return result;
			}
			// 如果和上个值相同，直接跳过
			if (nums[i] == previous) {
				continue;
			}
			// 下次探寻终点
			int lengthEnd = nums.length;
			int previous2 = Integer.MIN_VALUE;
			for (int j = i + 1; j < lengthEnd - 1; j++) {
				// 内循环第一个数，要大于0-array[i]
				if (nums[j] > -nums[i]) {
					break;
				}
				if (nums[j] == previous2) {
					continue;
				} else {
					previous2 = nums[j];
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
			previous = nums[i];
		}
		return result;
	}
}