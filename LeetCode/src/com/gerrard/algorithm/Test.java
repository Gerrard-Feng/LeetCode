package com.gerrard.algorithm;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8 };
		myAddAll(Arrays.copyOf(array, 2), Arrays.copyOfRange(array, 4, array.length));
		System.out.println();
	}

	private static int[] myAddAll(int[] a1, int[] a2) {
		int[] array = new int[a1.length + a2.length];
		System.arraycopy(a1, 0, array, 0, a1.length);
		System.arraycopy(a2, 0, array, a1.length, a2.length);
		return array;
	}
}