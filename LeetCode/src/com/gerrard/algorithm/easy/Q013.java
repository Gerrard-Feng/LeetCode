package com.gerrard.algorithm.easy;

public class Q013 {

	public static void main(String[] args) {
		
		System.out.println(romanToInt("MMXCIV"));
		System.out.println(romanToInt("MCCXXXIV"));
		System.out.println(romanToInt("MMM"));
		System.out.println(romanToInt("XXXIX"));
		System.out.println(romanToInt("DCLIV"));
	}

	// 不考虑非法的罗马字符串形式
	public static int romanToInt(String roman) {
		char[] array = roman.toCharArray();
		int sum = 0;
		// 上一个字符串代表的值，赋初始值不要影响第一次计算
		int previous = -1;
		int current;
		for (int i = 0; i < array.length; i++) {
			current = romanDict(array[i]);
			// 特殊的4、9处理
			if (current / previous == 5 || current / previous == 10) {
				sum -= 2 * previous;
			}
			sum += current;
			previous = current;
		}
		return sum;
	}

	// 罗马数字转化字典
	private static int romanDict(char str) {
		switch (str) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}
}