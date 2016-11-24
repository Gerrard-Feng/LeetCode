package com.gerrard.algorithm.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q018 {

	public static void main(String[] args) {
		int[] array = new int[] { -1, 0, -2, -2, };
		List<List<Integer>> list = fourSum(array, -5);
		for (List<Integer> a : list) {
			System.out.print("[");
			for (int i = 0; i < a.size(); i++) {
				System.out.print(a.get(i));
				if (i != a.size() - 1) {
					System.out.print(",");
				}
			}
			System.out.println("]");
		}
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		if (nums == null || nums.length < 4) {
			throw new IllegalArgumentException("Input error");
		}
		Arrays.sort(nums);
		return kSum(nums, 0, target, 4);
	}

	private static List<List<Integer>> kSum(int[] nums, int startIndex, int target, int kSum) {
		List<List<Integer>> result = new LinkedList<>();
		// 递归终点是2Sum问题
		if (kSum == 2) {
			int left = startIndex, right = nums.length - 1;
			int preLeft = Integer.MIN_VALUE, preRight = Integer.MIN_VALUE;
			while (left < right) {
				if (nums[left] > target && nums[left] > 0) {
					return result;
				}
				if (2 * nums[left] > target || 2 * nums[right] < target) {
					return result;
				}
				if (nums[left] == preLeft) {
					left++;
					continue;
				}
				if (nums[right] == preRight) {
					right--;
					continue;
				}
				int sum = nums[left] + nums[right];
				if (sum == target) {
					List<Integer> list = new LinkedList<>();
					list.add(nums[left]);
					list.add(nums[right]);
					result.add(list);
				}
				if (sum < target) {
					preLeft = nums[left];
					left++;
				} else {
					preRight = nums[right];
					right--;
				}
			}
		} else {
			// 大于2Sum问题，使用递归
			int previous = Integer.MAX_VALUE;
			for (int i = startIndex; i < nums.length - 1; i++) {
				if (nums[i] > target && nums[i] > 0) {
					return result;
				}
				// target值的范围超过k个极值
				if (kSum * nums[i] > target || kSum * nums[nums.length - 1] < target) {
					return result;
				}
				if (nums[i] == previous) {
					continue;
				}
				int tempTarget = target - nums[i];
				// 开启递归
				List<List<Integer>> tempResult = kSum(nums, i + 1, tempTarget, kSum - 1);
				for (List<Integer> a : tempResult) {
					a.add(nums[i]);
					result.add(a);
				}
				previous = nums[i];
			}
		}
		return result;
	}
}