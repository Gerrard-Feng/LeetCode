package com.gerrard.algorithm.medium;

import java.util.ArrayList;
import java.util.List;

public class Q017 {

	public static void main(String[] args) {
		List<String> list1 = method("78");
		for (String str : list1) {
			System.out.println(str);
		}
	}

	private static List<String> method(String digit) {
		// 入参检查省略
		// source一开始没有值
		List<String> source = new ArrayList<>();
		// 初始size需要等于1，不然之后不能循环
		source.add("");
		return format(source, digit);
	}

	private static List<String> format(List<String> source, String targetDigit) {
		// 剩余数字不存在，返回source
		if (targetDigit == null || targetDigit.length() < 1) {
			return source;
		}
		List<String> result = new ArrayList<>();
		// 截取剩余未处理字符串的第一个值
		int firstNumber;
		try {
			firstNumber = Integer.valueOf(targetDigit.substring(0, 1));
		} catch (NumberFormatException e) {
			return null;
		}
		// 获取对应数字的字典
		List<Character> cList = dict(firstNumber);
		if (cList == null) {
			return null;
		}
		// source集合和targetDigit第一个数字对应字典的全排列
		for (String str : source) {
			StringBuffer sb = new StringBuffer(str);
			for (Character c : cList) {
				sb.append(c);
				result.add(sb.toString());
				// 注意还原StringBuffer
				sb = new StringBuffer(str);
			}
		}
		// 将target转成source，继续递归
		return format(result, targetDigit.substring(1));
	}

	// 数字-字母转译字典
	private static List<Character> dict(int number) {
		// 不接收2-9以外的数字
		if (number < 2 || number > 9) {
			return null;
		}
		List<Character> list = new ArrayList<>();
		// 注意7和9代表4个字母
		char start;
		if (number < 8) {
			start = (char) ('a' + (number - 2) * 3);
		} else {
			start = (char) ('a' + (number - 2) * 3 + 1);
		}
		list.add(start);
		list.add((char) (start + 1));
		list.add((char) (start + 2));
		if (number == 9 || number == 7) {
			list.add((char) (start + 3));
		}
		return list;
	}
}