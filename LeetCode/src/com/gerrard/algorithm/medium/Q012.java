package com.gerrard.algorithm.medium;

public class Q012 {

	public static void main(String[] args) {
		System.out.println(method(2094));
		System.out.println(method(1234));
		System.out.println(method(3000));
		System.out.println(method(39));
		System.out.println(method(654));
	}

	private static String method(int number) {
		// 入参保护
		if (number > 3999 || number < 1) {
			return null;
		}
		// 结果集
		StringBuffer result = new StringBuffer();
		// 罗马字符集
		char[][] romanArray = new char[][] { { 'I', 'V' }, { 'X', 'L' }, { 'C', 'D' }, { 'M' } };
		// 创建副本确定最高位数
		int maxLevel = 0;
		int copyNumber = number;
		while (copyNumber > 0) {
			copyNumber /= 10;
			maxLevel++;
		}
		while (--maxLevel >= 0) {
			// 罗马数字从左往右是最高位的
			int currentNumber = (int) ((number / Math.pow(10, maxLevel)) % 10);
			// 4,9特别处理
			if (currentNumber == 4) {
				result.append("" + romanArray[maxLevel][0] + romanArray[maxLevel][1]);
			} else if (currentNumber == 9) {
				result.append("" + romanArray[maxLevel][0] + romanArray[maxLevel + 1][0]);
			} else {
				// 大于等于5处理
				if (currentNumber / 5 == 1) {
					result.append(romanArray[maxLevel][1]);
				}
				// 加1
				for (int i = 0; i < currentNumber % 5; i++) {
					result.append(romanArray[maxLevel][0]);
				}
			}
		}
		return result.toString();
	}
}