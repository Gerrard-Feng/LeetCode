package com.gerrard.algorithm.easy;

public class Q014 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		String[] array1 = new String[] { "abcds", "abdee", "abcww", "ab" };
		System.out.println(longestCommonPrefix(array1));
		System.out.println(method(array1));
		System.out.println();

		System.out.println("<==========第二组测试==========>");
		String[] array2 = new String[] { "aaa", "aa", "aaa" };
		System.out.println(longestCommonPrefix(array2));
		System.out.println(method(array2));
		System.out.println();

		System.out.println("<==========第三组测试==========>");
		String[] array3 = new String[] { "", "b" };
		System.out.println(longestCommonPrefix(array3));
		System.out.println(method(array3));
		System.out.println();

		System.out.println("<==========第四组测试==========>");
		String[] array4 = new String[] { "aa", "a" };
		System.out.println(longestCommonPrefix(array4));
		System.out.println(method(array4));
	}

	public static String longestCommonPrefix(String[] strs) {
		if (strs == null) {
			throw new IllegalArgumentException("Input error");
		}
		String prefix = strs.length == 0 ? "" : strs[0];
		for (String compare : strs) {
			while (!compare.startsWith(prefix)) {
				// 不匹配，消去前缀最后一项
				prefix = prefix.substring(0, prefix.length() - 1);
			}
			if (prefix.length() == 0) {
				break;
			}
		}
		return prefix;
	}

	private static String method(String[] strs) {
		String prefix = strs.length == 0 ? "" : strs[0];
		// 从数组第二项开始遍历
		for (String compare : strs) {
			// 整理长度
			String s1 = prefix.length() > compare.length() ? compare : prefix;
			String s2 = prefix.length() > compare.length() ? prefix : compare;
			boolean flag = false;
			for (int j = 0; j < s1.length(); j++) {
				if (s1.charAt(j) != s2.charAt(j)) {
					prefix = prefix.substring(0, j);
					flag = true;
					break;
				}
			}
			// 防止完全匹配的情况
			if (!flag) {
				prefix = s1;
			}
			if (prefix.length() == 0) {
				break;
			}
		}
		return prefix;
	}
}