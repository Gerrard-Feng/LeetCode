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
		System.out.println(method(a));
		System.out.println(method(b));
		System.out.println(method(c));
		System.out.println(method(d));
		System.out.println(method(e));
		System.out.println(method(f));
		System.out.println(method(g));
	}

	private static boolean method(String str) {
		// 0长度特殊处理
		if (str.length() == 0) {
			return true;
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
					Character last = leftList.get(leftList.size() - 1);
					// 只有Level相等，Direction相反，才是正确的
					// 匹配之后，去掉leftList的最后一项
					if (getDirection(last) == -getDirection(array[i]) && getLevel(last) == getLevel(array[i])) {
						leftList.remove(leftList.size() - 1);
					}else{
						return false;
					}
				}
			} else {
				// 不接收非括号
				return false;
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