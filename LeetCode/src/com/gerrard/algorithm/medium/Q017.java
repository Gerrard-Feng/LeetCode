package com.gerrard.algorithm.medium;

import java.util.LinkedList;
import java.util.List;

public class Q017 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		List<String> list1 = letterCombinations("78");
		for (String str : list1) {
			System.out.print(str + " ");
		}
		System.out.println();
		List<String> list2 = letterCombinations2("78");
		for (String str : list2) {
			System.out.print(str + " ");
		}
		System.out.println();

		System.out.println("<==========第二组测试==========>");
		List<String> list3 = letterCombinations("2586");
		for (String str : list3) {
			System.out.print(str + " ");
		}
		System.out.println();
		List<String> list4 = letterCombinations2("2586");
		for (String str : list4) {
			System.out.print(str + " ");
		}
		System.out.println();
	}

	public static List<String> letterCombinations(String digits) {
		if (digits == null) {
			throw new IllegalArgumentException("Input error");
		}
		String copyDigits = digits;
		while (copyDigits.length() > 0) {
			int i = copyDigits.charAt(0) - '0';
			if (i < 2 || i > 9) {
				throw new IllegalArgumentException("Input error");
			}
			copyDigits = copyDigits.substring(1);
		}
		// 只接受2-9的数值字符串
		List<String> source = new LinkedList<>();
		if (digits.equals("")) {
			return source;
		}
		// 初始size需要等于1，不然之后不能循环
		source.add("");
		return format(source, digits);
	}

	// 递归方法
	private static List<String> format(List<String> source, String targetDigit) {
		if (targetDigit.length() == 0) {
			return source;
		}
		List<String> result = new LinkedList<>();
		// 第一个数
		int first = targetDigit.charAt(0) - '0';
		char[] cArray = dict(first);
		// source集合和targetDigit第一个数字对应字典的全排列
		for (String str : source) {
			for (char c : cArray) {
				result.add(str + c);
			}
		}
		// 将target转成source，继续递归
		return format(result, targetDigit.substring(1));
	}

	private static char[] dict(int num) {
		String[] mappping = new String[] { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		return mappping[num - 2].toCharArray();
	}

	public static List<String> letterCombinations2(String digits) {
		LinkedList<String> list = new LinkedList<String>();
		if (digits.length() == 0) {
			return list;
		}
		list.add("");
		for (int i = 0; i < digits.length(); i++) {
			int x = Character.getNumericValue(digits.charAt(i));
			while (list.peek().length() == i) {
				// 删除集合第一个元素并返回
				String s = list.remove();
				for (char c : dict2(x)) {
					list.add(s + c);
				}
			}
		}
		return list;
	}

	// 数字-字母转译字典
	private static char[] dict2(int num) {
		char[] result;
		if (num == 9 || num == 7) {
			result = new char[4];
		} else {
			result = new char[3];
		}
		// 注意7和9代表4个字母
		char start;
		if (num < 8) {
			start = (char) ('a' + (num - 2) * 3);
		} else {
			start = (char) ('a' + (num - 2) * 3 + 1);
		}
		result[0] = start;
		result[1] = (char) (start + 1);
		result[2] = (char) (start + 2);
		if (num == 9 || num == 7) {
			result[3] = (char) (start + 3);
		}
		return result;
	}
}