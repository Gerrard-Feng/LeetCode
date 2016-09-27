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
		System.out.println();
		int[] arr1 = new int[] {};
		System.out.println(arr1.length == 0);
		int[] arr2 = new int[] { 1 };
		System.out.println(Arrays.copyOf(arr2, 0).length == 0);
		System.out.println(Arrays.copyOfRange(arr2, 1, 1).length == 0);

		StringBuffer sb = new StringBuffer();
		char a = 'A';
		char b = 'B';
		sb.append(a);
		sb.append(b);
		System.out.println(sb);

		int aa = 2;
		int bb = -3;
		System.out.println(-(aa + bb));
	}
}