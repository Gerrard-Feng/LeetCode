package com.gerrard.algorithm.medium;

import java.util.Arrays;

public class Q016 {

	public static void main(String[] args) {
		int[] array1 = { -12, -7, 0, 4, 22, -8, 2, 55, 32, 11 };
		int target1 = -7;
		int[] result1 = method(array1, target1);
		for (int a : result1) {
			System.out.print(a + " ");
		}
	}

	private static int[] method(int[] array, int target) {
		// 入参检查
		if (array == null || array.length < 3) {
			return null;
		}
		// 结果数组
		int[] result = new int[3];
		// 先排序
		Arrays.sort(array);
		// 维护的最小值，第一次赋值数组的第1，2和最后一项与target的差值
		int closest = Math.abs(target - (array[0] + array[1] + array[array.length - 1]));
		// 将3Sum Closest的问题，转成n个2Sum Closest的问题
		// i是最后两项不用跑，因为数字不够了
		for (int i = 0; i < array.length - 2; i++) {
			// 剩余两个的下标，取头尾
			int j = i + 1;
			int k = array.length - 1;
			// 2Sum Closest问题的target
			int tempTarget = target - array[i];
			int sum;
			while (j != k) {
				sum = array[j] + array[k];
				// 因为解唯一，等于0直接赋值后退出
				if (tempTarget - sum == 0) {
					result[0] = array[i];
					result[1] = array[j];
					result[2] = array[k];
					return result;
				}
				// 最小值替换
				if (Math.abs(tempTarget - sum) < closest) {
					result[0] = array[i];
					result[1] = array[j];
					result[2] = array[k];
					closest = Math.abs(tempTarget - sum);
				}
				if (tempTarget - sum > 0) {
					// 和过小，升底
					j++;
				} else {
					// 和过大，降层
					k--;
				}
			}
		}
		return result;
	}
}