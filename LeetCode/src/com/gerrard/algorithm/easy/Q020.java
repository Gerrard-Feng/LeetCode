package com.gerrard.algorithm.easy;

import java.util.ArrayList;
import java.util.List;

public class Q020 {

	public static void main(String[] args) {

		String a = "";
		String b = ")";
		String c = "{[()]";
		String d = "{([])}";
		String e = "(a)";
		String f = "{}[]()";
		String g = "{{[(())]}}[]";
		System.out.println(isValid(a));
		System.out.println(isValid(b));
		System.out.println(isValid(c));
		System.out.println(isValid(d));
		System.out.println(isValid(e));
		System.out.println(isValid(f));
		System.out.println(isValid(g));
	}

	public static boolean isValid(String str) {
		if (str == null) {
			throw new IllegalArgumentException("Input error");
		}
		// 0长度特殊处理
		if (str.length() == 0) {
			return true;
		}
		// 长度为奇数可以直接判断false
		if (str.length() % 2 == 1) {
			return false;
		}
		char[] array = str.toCharArray();
		// 维护左括号的集合
		List<Character> leftList = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			if (getDirection(array[i]) == 1) {
				// 左括号处理
				if (leftList.size() == 0) {
					leftList.add(array[i]);
				} else {
					// 判断上一个括号的Level，小于才可以加，否则return false
					Character last = leftList.get(leftList.size() - 1);
					if (getLevel(array[i]) <= getLevel(last)) {
						leftList.add(array[i]);
					} else {
						return false;
					}
				}
			} else if (getDirection(array[i]) == -1) {
				// 右括号处理
				if (leftList.size() == 0) {
					return false;
				} else {
					// 题目认为"({})"也是合理的形式
					leftList.remove(leftList.size() - 1);
				}
			} else {
				throw new IllegalArgumentException("Input error");
			}
		}
		// 循环结束之后判断leftList是否还有剩余
		if (leftList.size() != 0) {
			return false;
		}
		return true;
	}

	// 括号级别
	private static int getLevel(Character c) {
		switch (c) {
		case '{':
		case '}':
			return 3;
		case '[':
		case ']':
			return 2;
		case '(':
		case ')':
			return 1;
		default:
			return 0;
		}
	}

	// 括号方向
	private static int getDirection(Character c) {
		switch (c) {
		case '{':
		case '[':
		case '(':
			return 1;
		case '}':
		case ']':
		case ')':
			return -1;
		default:
			return 0;
		}
	}
}