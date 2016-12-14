package com.gerrard.algorithm.easy;

import java.util.Arrays;

public class Q026 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		int[] array1 = new int[] { 1, 1, 3, 4, 4, 4, 5, 7, 9, 9 };
		int newLength1 = removeDuplicates(array1);
		for (int i = 0; i < newLength1; i++) {
			System.out.print(array1[i] + " ");
		}
		System.out.println("\n");

		System.out.println("<==========第二组测试==========>");
		int[] array2 = new int[] { 0, 1, 1, 2, 2, 3, 3, 3, 4, 5, 6, 7, 7, 8, 8, 9, 9, 9, 9 };
		int newLength2 = removeDuplicates(array2);
		for (int i = 0; i < newLength2; i++) {
			System.out.print(array2[i] + " ");
		}
		System.out.println("\n");

		System.out.println("<==========第三组测试==========>");
		int[] array3 = new int[] { 1, 2, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 8, 9 };
		int newLength3 = removeDuplicates(array3);
		for (int i = 0; i < newLength3; i++) {
			System.out.print(array3[i] + " ");
		}
	}

	public static int removeDuplicates(int[] array) {
		if (array == null) {
			throw new IllegalArgumentException("Input error");
		}
		if (array.length < 2) {
			return array.length;
		}
		// 慢指针
		int i = 0;
		for (int j = 1; j < array.length; j++) {
			// 快指针不断前进，遇到与慢指针数字相同的情况，跳过
			if (array[j] != array[i]) {
				i++;
				array[i] = array[j];
			}
		}
		// 返回新数组的长度，不关心之后的内容
		return i + 1;
	}
}