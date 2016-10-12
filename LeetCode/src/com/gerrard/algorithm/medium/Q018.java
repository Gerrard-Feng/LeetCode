package com.gerrard.algorithm.medium;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Q018 {

	public static void main(String[] args) {
		int[] array = new int[] { 1, 0, -1, 0, -2, 2, 3, 4, -7, -7 };
		Set<int[]> set = method(array, 0, 4);
		for (int[] a : set) {
			System.out.print("[");
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i]);
				if (i != a.length - 1) {
					System.out.print(",");
				}
			}
			System.out.println("]");
		}
	}

	private static Set<int[]> method(int[] array, int target, int kSum) {
		// 入参保护
		if (array == null || array.length < kSum) {
			return null;
		}
		// 第一次进来做一次排序，之后自然有序，无需排序
		if (kSum == 4) {
			Arrays.sort(array);
		}
		// 结果集
		Set<int[]> result = new LinkedHashSet<>();
		// 递归终点是2Sum问题
		if (kSum == 2) {
			if (target < array[0]) {
				// 因为数组有序，target如果小于数组第一项，返回空集合
				return result;
			} else {
				int lastNumber = Integer.MAX_VALUE;
				for (int i = 0; i < array.length - 1; i++) {
					if (array[i] == lastNumber) {
						continue;
					}
					int tempTarget = target - array[i];
					// 秉承始终向后找的思想，下一项小于tempTarget，直接退出
					// 而且tempTarget会越来越小，array[i+1]越来越大，再往后更加不可能了，直接返回
					if (tempTarget < array[i + 1]) {
						return result;
					} else {
						int[] remainArray = Arrays.copyOfRange(array, i + 1, array.length);
						int index = Arrays.binarySearch(remainArray, tempTarget);
						if (index >= 0) {
							int[] newArray = new int[2];
							newArray[0] = array[i];
							newArray[1] = remainArray[index];
							result.add(newArray);
						}
					}
					lastNumber = array[i];
				}
			}
		} else {
			// 大于2Sum问题，使用递归
			// 记录上一个值，这是必须的，因为Set集合对于2个一模一样的数组无能为力
			int lastNumber = Integer.MAX_VALUE;
			for (int i = 0; i < array.length - (kSum - 1); i++) {
				if (array[i] == lastNumber) {
					continue;
				}
				int tempTarget = target - array[i];
				// 开启递归
				Set<int[]> tempResult = method(Arrays.copyOfRange(array, i + 1, array.length), tempTarget, kSum - 1);
				for (int[] a : tempResult) {
					int[] newArray = new int[a.length + 1];
					newArray[0] = array[i];
					for (int j = 0; j < a.length; j++) {
						newArray[j + 1] = a[j];
					}
					result.add(newArray);
				}
				// 下次循环前，变更lastNumber
				lastNumber = array[i];
			}
		}
		return result;
	}
}