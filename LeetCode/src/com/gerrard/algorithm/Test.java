package com.gerrard.algorithm;

import java.util.LinkedHashSet;
import java.util.Set;

public class Test {

	public static void main(String[] args) {

		Set<int[]> set = new LinkedHashSet<>();
		int[] a = new int[] { 1 };
		int[] b = new int[] { 1 };
		set.add(a);
		set.add(b);
		System.out.println(set.size());

		Set<String> set2 = new LinkedHashSet<>();
		String s1 = "()";
		String s2 = new StringBuffer("").append("()").toString();
		set2.add(s1);
		set2.add(s2);
		System.out.println(set2.size());

	}
}