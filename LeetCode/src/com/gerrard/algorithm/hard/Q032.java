package com.gerrard.algorithm.hard;

public class Q032 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		String s1 = "()(()";
		System.out.println(longestValidParentheses(s1));

		System.out.println("<==========第二组测试==========>");
		String s2 = ")()())";
		System.out.println(longestValidParentheses(s2));

		System.out.println("<==========第三组测试==========>");
		String s3 = "(()(((()";
		System.out.println(longestValidParentheses(s3));

		System.out.println("<==========第四组测试==========>");
		String s4 = ")(";
		System.out.println(longestValidParentheses(s4));
	}

	public static int longestValidParentheses(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		// 去掉开头所有")"的部分，同时避免字符串全部由一种括号组成的情况
		int index1 = s.indexOf("(");
		if ((index1 + 1) * (s.indexOf(")") + 1) == 0) {
			return 0;
		}
		char[] array = s.toCharArray();
		// 之后的数组，都是从第一个 "(" 开始计算
		// 第一次遍历，将数组中，能组成括号的部分，变成0
		for (int i = index1; i < array.length; i++) {
			if (array[i] == ')') {
				for (int j = i - 1; j > -1; j--) {
					if (array[j] == '(') {
						array[i] = array[j] = 0;
						break;
					}
				}
			}
		}
		// 第二次遍历，计算最大长度
		int max = 0;
		int start = -1 + index1;
		for (int i = index1; i < array.length - 1; i++) {
			char a = array[i];
			char b = array[i + 1];
			if (a != 0) {
				start = i;
			} else if (b != 0) {
				max = Math.max(i - start, max);
			}
		}
		if (array[array.length - 1] == 0) {
			max = Math.max(array.length - 1 - start, max);
		}
		return max;
	}
}