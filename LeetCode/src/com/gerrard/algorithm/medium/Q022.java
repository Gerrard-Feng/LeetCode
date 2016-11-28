package com.gerrard.algorithm.medium;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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

	private static void show(int n) {
		List<String> result1 = generateParenthesis(n);
		List<String> result2 = generateParenthesis2(n);
		for (String s : result1) {
			if (!result2.contains(s)) {
				System.out.println(s);
			}
		}
//		for (String s : result1) {
//			System.out.print(s + " ");
//		}
//		System.out.println("\n");
	}

	public static List<String> generateParenthesis2(int n) {
		List<String> result = new ArrayList<>();
		if (n == 1) {
			result.add("()");

		} else {
			List<String> source = generateParenthesis2(n - 1);
			for (String s : source) {
				List<String> list = new ArrayList<>();
				list.add("()" + s);
				list.add(s + "()");
				list.add("(" + s + ")");
				for (String str : list) {
					if (!result.contains(str)) {
						result.add(str);
					}
				}
			}
		}
		return result;
	}

	// 先遍历所有左括号情况，再遍历右括号情况是错误的
	public static List<String> generateParenthesis(int n) {
		// 入参保护
		if (n < 1) {
			throw new IllegalArgumentException("Input error");
		}
		List<String> result = new LinkedList<>();
		// 递归终点
		if (n == 1) {
			result.add("()");
			return result;
		}
		List<String> last = generateParenthesis(n - 1);
		// 一般情况
		for (String s1 : last) {
			result.addAll(addParentheses(s1));
		}
		List<String> list = new ArrayList<>();
		for (String s : result) {
			if (!list.contains(s)) {
				list.add(s);
			}
		}
		return list;
	}

	// 加括号
	private static Set<String> addParentheses(String str) {
		Set<String> set = new LinkedHashSet<>();
		// 先加左括号
		// 先在第一个位置加"("，其实是第二个位置，但是等价的
		String tempFirst = insert(str, 0, "(");
		set.addAll(addRight(tempFirst, 1));
		// 第一个不可能是右括号
		for (int i = 1; i < str.length(); i++) {
			// 在遇到的每一个")"后面加"("，防止重复情况
			if (str.substring(i, i + 1).equals(")")) {
				String temp = insert(str, i, "(");
				// 加完左括号之后，立即加右括号
				// 注意新加的"("在temp里的index是i+1
				set.addAll(addRight(temp, i + 1));
			}
		}
		return set;
	}

	// 加右括号，是基于已经加好的左括号，在这个左括号之后加右括号
	private static Set<String> addRight(String str, int leftIndex) {
		Set<String> set = new LinkedHashSet<>();
		// 左右括号的个数
		int leftNumber = 0;
		int rightNumber = 0;
		// 从i=leftIndex开始计数，之前的leftNumber=rightNumber，就不计入统计了
		for (int i = leftIndex; i < str.length(); i++) {
			String current = str.substring(i, i + 1);
			if (current.equals("(")) {
				leftNumber++;
			} else {
				rightNumber++;
			}
			// 累计当前之后的括号数，再判断是否可加右括号
			if (leftNumber > rightNumber) {
				// 这里是可能出现重复现象的
				// 如"(()"，i=1或2，之后加")"都是一样的
				set.add(insert(str, i, ")"));
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