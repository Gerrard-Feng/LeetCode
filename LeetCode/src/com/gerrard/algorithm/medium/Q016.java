package com.gerrard.algorithm.medium;

import java.util.Arrays;

public class Q016 {

	public static void main(String[] args) {

	}

	private static int[] method(int[] array, int target) {
		int closest = Integer.MAX_VALUE;
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 2) {
				break;
			}
			if (i != 0 && array[i] == array[i - 1]) {
				continue;
			}
			for (int j = i + 1; j < array.length; j++) {


			}
		}
		return array;
	}
}