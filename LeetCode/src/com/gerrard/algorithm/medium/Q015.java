package com.gerrard.algorithm.medium;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Q015 {

	public static void main(String[] args) {
		System.out.println("<==========第一组测试==========>");
		int[] array1 = new int[] { -1, 0, 1, 2, -1, -4 };
		show(method(array1));

		System.out.println("<==========第二组测试==========>");
		int[] array2 = new int[] { -1, 0, 1, 2, -1, -4, -2, -2, -7, 3, 0, 0, 0, 0, -100, 55, 6, 8, 9, 23, 12, 33, -9,
				-8, -6, 11, 23 };
		show(method(array2));
	}

	// 显示结果格式优化
	private static void show(Set<int[]> set) {
		System.out.println("[");
		for (int[] a : set) {
			System.out.print("  " + "[");
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i]);
				if (i != a.length - 1) {
					System.out.print(",");
				}
			}
			System.out.print("]");
			System.out.println();
		}
		System.out.println("]");
	}

	private static Set<int[]> method(int[] array) {
		// 返回的结果集
		Set<int[]> set = new LinkedHashSet<>();
		// 先给数组排序
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			// 第一个数大于0，之后更加没机会了
			if (array[i] > 0) {
				return set;
			}
			// 之后剩余的个数不够了
			if (i == array.length - 2) {
				break;
			}
			// 先判断一下，如果当前值和上个值相同，直接跳过，注意第一位是没有“上一位”的概念的
			if (i != 0 && array[i] == array[i - 1]) {
				// 注意是continue，而不是break
				continue;
			}
			// 下次探寻终点
			int lengthEnd = array.length - i;
			for (int j = i + 1; j < lengthEnd; j++) {
				// 同理，剩余个数不足
				if (j == lengthEnd - 1) {
					break;
				}
				// 同样的判断，第一次是没有必要的
				if (j != i + 1 && array[j] == array[j - 1]) {
					continue;
				}
				// 定下两个，其实下一个也就决定了
				int numberToFind = -(array[i] + array[j]);
				// Arrays.binarySearch()方法只能在有序数组中使用
				// 注意不是在整个数组上用这个方法，是在剩余数组上（夹逼过的）
				int index = Arrays.binarySearch(Arrays.copyOfRange(array, j, lengthEnd), numberToFind);
				// Arrays.binarySearch()查找失败时，会返回一个负值
				// JAVA_API：这保证了当且仅当此键被找到时，返回的值将 >= 0。
				if (index >= 0) {
					// 因为有序，下一个array[j]一定比现在的大或等于（有去重要求）
					// 而index之后的肯定比array[index]大，再之后的就没有必要遍历了
					// 注意+1，因为lengthEnd是指长度而不是下标
					// 再注意，这里的index，是去掉原数组前j项的index，加回去
					lengthEnd = index + 1 + j;
					// 这里可以保证array[i]<=array[j]<=array[index]，不必担心Set集合接收重复问题
					set.add(new int[] { array[i], array[j], array[index + j] });
				}
			}
		}
		return set;
	}
}