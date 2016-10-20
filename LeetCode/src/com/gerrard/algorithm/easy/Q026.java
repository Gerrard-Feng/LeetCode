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
		System.out.println();

		System.out.println("<==========第一组测试==========>");
		int[] array2 = new int[] { 0, 1, 1, 2, 2, 3, 3, 3, 4, 5, 6, 7, 7, 8, 8, 9, 9, 9, 9 };
		int newLength2 = removeDuplicates(array2);
		for (int i = 0; i < newLength2; i++) {
			System.out.print(array2[i] + " ");
		}
		System.out.println();

		System.out.println("<==========第三组测试==========>");
		int[] array3 = new int[] { 1, 2, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 8, 9 };
		array3 = removeDuplicates_3(array3);
		for (int i = 0; i < array3.length; i++) {
			System.out.print(array3[i] + " ");
		}
	}

	public static int removeDuplicates(int[] array) {
		if (array == null) {
			return -1;
		}
		if (array.length < 2) {
			return array.length;
		}
		// “新数组”的指针
		int i = 0;
		for (int j = 1; j < array.length; j++) {
			// “旧数组”的指针不断往前跑，没遇到重复的，就给“新数组”赋值
			if (array[j] != array[i]) {
				i++;
				array[i] = array[j];
			}
		}
		array = Arrays.copyOf(array, i + 1);
		return array.length;
	}

	// 在同一份数组空间中操作，返回新数组的长度
	// 比上面的效率差很多
	public static int removeDuplicates_2(int[] array) {
		// 入参保护
		if (array == null) {
			return -1;
		}
		// 肯定不存在重复的情况
		if (array.length < 2) {
			return array.length;
		}
		// 重复的个数
		int repeatNumber = 0;
		// 循环自第二项开启，至新数组长度结束
		for (int i = 1; i < array.length - repeatNumber; i++) {
			if (array[i] == array[i - 1]) {
				// 先加repeatNumber
				repeatNumber++;
				// 自下一项起，往前挪
				for (int j = i; j < array.length - repeatNumber; j++) {
					array[j] = array[j + 1];
				}
				// 向前挪完之后，下一次循环还是从当前项开始
				i--;
			}
		}
		// 去除数组多余项
		array = Arrays.copyOf(array, array.length - repeatNumber);
		return array.length;
	}

	// 多使用一份数组空间，但具有更高的效率
	// 因为操作不同的内存，所以要返回指定的新数组
	public static int[] removeDuplicates_3(int[] array) {
		// 入参保护
		if (array == null) {
			return null;
		}
		// 肯定不存在重复的情况
		if (array.length < 2) {
			return array;
		}
		// 重复的个数
		int repeatNumber = 0;
		int[] newArray = new int[array.length];
		// 第一项先赋值
		newArray[0] = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] != array[i - 1]) {
				newArray[i - repeatNumber] = array[i];
			} else {
				repeatNumber++;
			}
		}
		array = Arrays.copyOf(newArray, array.length - repeatNumber);
		return array;
	}
}