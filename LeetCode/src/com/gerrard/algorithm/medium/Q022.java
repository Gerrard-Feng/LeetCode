package com.gerrard.algorithm.medium;

import java.util.LinkedHashSet;
import java.util.Set;

public class Q022 {

	public static void main(String[] args) {

	}

	private static Set<String> method(int number) {
		// 入参保护
		if (number < 1) {
			return null;
		}
		// 结果集
		Set<String> set = new LinkedHashSet<>();
		// 递归终点
		if (number == 1) {
			set.add("()");
			return set;
		}
		Set<String> lastSet = method(number - 1);
		// 一般情况
		for (String s : lastSet) {
			Set<String> tempSet = addLeft(s);
		}

		return set;
	}

	// 加左括号
	private static Set<String> addLeft(String str) {
		Set<String> set = new LinkedHashSet<>();
		// 先在第一个位置加"("（其实是第二个位置，但是等价的）
		insert(str, 0, "(");
		// 第一个不可能是右括号
		for (int i = 1; i < str.length(); i++) {
			// 在遇到的每一个")"后面加"("
			if (str.substring(i, i + 1).equals(")")) {
				set.add(insert(str, i, "("));
			}
		}
		return set;
	}

	// 加右括号
	private static Set<String> addRight(String str) {
		Set<String> set = new LinkedHashSet<>();
		// 左右括号的个数
		int leftNumber = 0;
		int rightNumber = 0;
		for (int i = 0; i < str.length(); i++) {
			String current = str.substring(i, i + 1);
			if (current.equals("(")) {
				leftNumber++;
			} else {
				rightNumber++;
			}
		}
		return set;
	}

	// 在index位置之后1位，增加insertTarget
	private static String insert(String str, int index, String insertTarget) {
		String part1 = str.substring(0, index + 1);
		String part2 = str.substring(index + 1, str.length());
		return new StringBuffer(part1).append(insertTarget).append(part2).toString();
	}
}