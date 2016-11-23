package com.gerrard.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8 };
		myAddAll(Arrays.copyOf(array, 2), Arrays.copyOfRange(array, 4, array.length));
		System.out.println();

		Set<List<Integer>> result = new HashSet<>();
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		List<Integer> list2 = new ArrayList<>();
		list1.add(1);
		result.add(list1);
		result.add(list1);
		result.add(list2);
		System.out.println(result.size());
	}

	private static int[] myAddAll(int[] a1, int[] a2) {
		int[] array = new int[a1.length + a2.length];
		System.arraycopy(a1, 0, array, 0, a1.length);
		System.arraycopy(a2, 0, array, a1.length, a2.length);
		return array;
	}
}