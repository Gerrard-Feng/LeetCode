package com.gerrard.algorithm;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {

		Set<int[]> set = new LinkedHashSet<>();
		int[] a = new int[] { 1 };
		int[] b = new int[] { 1 };
		set.add(a);
		set.add(b);
		System.out.println(set.size());
		
		List<Integer> list = new LinkedList<>();
		list.add(1);
		list.set(0, 2);
		System.out.println(list.get(0));
	}
}