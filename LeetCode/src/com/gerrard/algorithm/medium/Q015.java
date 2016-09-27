package com.gerrard.algorithm.medium;

import java.util.Arrays;
import java.util.Set;

public class Q015 {

	public static void main(String[] args) {
		int[] array1 = new int[] { -1, 0, 1, 2, -1, -4 };
	}

	private static Set<int[]> method(int[] array) {
		// 先给数组排序
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length - i; j++) {
				// 定下两个，其实下一个也就决定了
				int numberToFind = -(array[i] + array[j]);
				// Arrays.binarySearch()方法只能在有序数组中使用
				Arrays.binarySearch(array, numberToFind);
			}
		}
		return null;
	}
}