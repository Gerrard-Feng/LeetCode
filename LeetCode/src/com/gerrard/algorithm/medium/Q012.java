package com.gerrard.algorithm.medium;

public class Q012 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		System.out.println(intToRoman(2094));
		System.out.println(method(2094));
		System.out.println();

		System.out.println("<==========第二组测试==========>");
		System.out.println(intToRoman(1234));
		System.out.println(method(1234));
		System.out.println();

		System.out.println("<==========第三组测试==========>");
		System.out.println(intToRoman(3000));
		System.out.println(method(3000));
		System.out.println();

		System.out.println("<==========第四组测试==========>");
		System.out.println(intToRoman(39));
		System.out.println(method(39));
		System.out.println();

		System.out.println("<==========第五组测试==========>");
		System.out.println(intToRoman(654));
		System.out.println(method(654));
	}

	public static String intToRoman(int num) {
		if (num > 3999 || num < 1) {
			throw new IllegalArgumentException("Input error");
		}
		StringBuffer result = new StringBuffer();
		// 确定最高位数
		int length = String.valueOf(num).length();
		while (length-- > 0) {
			// 罗马数字从左往右是最高位的
			int current = (int) ((num / Math.pow(10, length)) % 10);
			// 4、9特殊处理
			if (current == 4) {
				result.append("" + romanDict(length, 0) + romanDict(length, 1));
			} else if (current == 9) {
				result.append("" + romanDict(length, 0) + romanDict(length + 1, 0));
			} else {
				// 大于等于5处理
				if (current / 5 == 1) {
					result.append(romanDict(length, 1));
				}
				// 加1
				for (int i = 0; i < current % 5; i++) {
					result.append(romanDict(length, 0));
				}
			}
		}
		return result.toString();
	}

	// 罗马数字字典
	private static char romanDict(int x, int y) {
		char[] roman = new char[] { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
		return roman[x * 2 + y];
	}

	// 使用二维数组，存储罗马字符集
	private static String method(int num) {
		StringBuffer result = new StringBuffer();
		// 罗马字符集
		char[][] roman = new char[][] { { 'I', 'V' }, { 'X', 'L' }, { 'C', 'D' }, { 'M' } };
		// 确定最高位数
		int length = String.valueOf(num).length();
		while (length-- > 0) {
			// 罗马数字从左往右是最高位的
			int current = (int) ((num / Math.pow(10, length)) % 10);
			// 4、9特殊处理
			if (current == 4) {
				result.append("" + roman[length][0] + roman[length][1]);
			} else if (current == 9) {
				result.append("" + roman[length][0] + roman[length + 1][0]);
			} else {
				// 大于等于5处理
				if (current / 5 == 1) {
					result.append(roman[length][1]);
				}
				// 加1
				for (int i = 0; i < current % 5; i++) {
					result.append(roman[length][0]);
				}
			}
		}
		return result.toString();
	}
}