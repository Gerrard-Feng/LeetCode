package com.gerrard.algorithm;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {

		int[] array = new int[] { 1, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 9 };
		array = Arrays.copyOfRange(array, 1, array.length);
		System.out.println();
	}
}