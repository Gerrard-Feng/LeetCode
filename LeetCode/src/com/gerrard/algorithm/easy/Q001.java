package com.gerrard.algorithm.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q001 {

	public static void main(String[] args) {
		int[] array = new int[] { 2, 7, 2, 15 };
		int sum = 4;
		int[] result = method(array, sum);
		for (int a : result) {
			System.out.println(a);
		}
	}

	private static int[] method(int[] array, int sum) {
		// 结果数组
		int[] result = new int[2];
		// 保护无结果的标志位
		boolean flag = false;
		// 先将int[]转成Integer[]
		Integer[] collectionArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			collectionArray[i] = array[i];
		}
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(collectionArray);
		for (Integer a : list) {
			if (list.contains(sum - a)) {
				// 防止原数组有重复数字的情况
				result[0] = list.indexOf(a);
				result[1] = list.lastIndexOf(sum - a);
				flag = true;
				break;
			}
		}
		if (flag) {
			return result;
		}
		return null;
	}
}