package com.gerrard.algorithm.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q022 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		show(1);

		System.out.println("<==========第二组测试==========>");
		show(2);

		System.out.println("<==========第三组测试==========>");
		show(3);

		System.out.println("<==========第四组测试==========>");
		show(4);
	}

	// 显示优化
	private static void show(int n) {
		List<String> result = generateParenthesis(n);
		for (String s : result) {
			System.out.print(s + " ");
		}
		System.out.println("\n");
	}

	public static List<String> generateParenthesis(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("Input error");
		}
		List<String> list = new ArrayList<>();
		generate(n, n, "", list);
		return list;
	}

	// 先加所有左括号，遍历所有可能性，然后回退一个左括号，依次类推
	private static void generate(int left, int right, String current, List<String> list) {
		// 先加左括号
		if (left > 0) {
			generate(left - 1, right, current + "(", list);
		}
		if (right > 0 && right > left) {
			generate(left, right - 1, current + ")", list);
		}
		if (left == 0 && right == 0) {
			list.add(current);
		}
	}

	// 根据n-1的情况增加括号
	public static List<String> method(int n) {
		List<String> list = new ArrayList<>(getPharentheses(n));
		return list;
	}

	private static Set<String> getPharentheses(int n) {
		Set<String> set = new HashSet<>();
		if (n == 1) {
			set.add("()");
		} else {
			Set<String> last = getPharentheses(n - 1);
			for (String s : last) {
				String s1 = "(" + s;
				int l1 = 0;
				int r1 = 0;
				for (int j = 1; j < s1.length(); j++) {
					if (s1.charAt(j) == '(') {
						l1++;
					} else {
						r1++;
					}
					// 这里有重复的可能性
					if (l1 > r1) {
						set.add(s1.substring(0, j + 1) + ")" + s1.substring(j + 1));
					}
				}
				// 先加左括号
				for (int i = 1; i < s.length(); i++) {
					if (s.charAt(i) == ')') {
						String str = s.substring(0, i + 1) + "(" + s.substring(i + 1);
						// 左右括号计数
						int l = 0;
						int r = 0;
						for (int j = i + 1; j < str.length(); j++) {
							if (str.charAt(j) == '(') {
								l++;
							} else {
								r++;
							}
							// 这里有重复的可能性
							if (l > r) {
								set.add(str.substring(0, j + 1) + ")" + str.substring(j + 1));
							}
						}
					}
				}
			}
		}
		return set;
	}
}