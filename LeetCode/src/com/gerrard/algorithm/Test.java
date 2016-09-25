package com.gerrard.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {

		List<Boolean> list = new LinkedList<>();
		list.add(false);
		System.out.println(list.lastIndexOf(true));

		int[] array = new int[] { 1, 2, 3, 4 };
		int[] copy1 = Arrays.copyOfRange(array, 2, 4);
		for (int a : copy1) {
			System.out.print(a + " ");
		}
		System.out.println();
		int[] copy2 = Arrays.copyOf(array, 2);
		for (int a : copy2) {
			System.out.print(a + " ");
		}
	}
}