package com.gerrard.algorithm.medium;

public class Q011 {

	public static void main(String[] args) {
		int[] array1 = new int[] { 2, 7, 3, 11, 8 };
		int[] result1 = method(array1);
		int index1 = result1[0];
		int index2 = result1[1];
		int height1 = Math.min(array1[index1], array1[index2]);
		int length1 = Math.abs(index1 - index2);
		System.out.println("索引值：" + index1 + "," + index2);
		System.out.println("面积：" + height1 * length1);
	}

	private static int[] method(int[] array) {
		int[] result = new int[2];
		int maxArea = 0;
		for (int i = 0; i < array.length; i++) {
			int maxHeight = 0;
			// 第二次反向遍历
			for (int j = array.length - 1; j > i; j--) {
				int height = Math.min(array[i], array[j]);
				// 因为底越来越小，所以只有高度大于最高高度，才有比较面积的意义
				if (height > maxHeight) {
					// 不考虑面积比较结果，高先赋值
					maxHeight = height;
					if (height * (j - i) > maxArea) {
						maxArea = height * (j - i);
						result[0] = i;
						result[1] = j;
					}
				}
			}
		}
		return result;
	}
}