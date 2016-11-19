package com.gerrard.algorithm.easy;

public class Q006 {

	public static void main(String[] args) {
		System.out.println("<==========第一组测试==========>");
		String str1 = "PAYPALISHIRING";
		System.out.println(convert(str1, 3));
		System.out.println();

		System.out.println("<==========第二组测试==========>");
		String str2 = "abcdefghijklmnopqrstuvwxyz";
		System.out.println(convert(str2, 4));
		System.out.println();

		System.out.println("<==========第三组测试==========>");
		System.out.println(convert(str2, 5));
		System.out.println();

		System.out.println("<==========第四组测试==========>");
		System.out.println(convert("A", 1));
		System.out.println();
	}

	public static String convert(String s, int numRows) {
		if (s == null || numRows <= 0) {
			throw new IllegalArgumentException("Input error");
		}
		if (numRows == 1) {
			return s;
		}
		char[] array = s.toCharArray();
		StringBuffer buffer = new StringBuffer();
		// 上行/下行标志位
		int flag = 1;
		// 间隔定值
		int interval = 2 * (numRows - 1);
		// 按行遍历
		for (int i = 0; i < numRows; i++) {
			// 每一行的数据
			int j = 0;
			while (i + j < array.length) {
				buffer.append(array[i + j]);
				// 第一行和最后一行
				if (i == 0 || i == numRows - 1) {
					j += interval;
				} else {
					if (flag == 1) {
						// 上行
						j += interval - 2 * i;
					} else {
						// 下行
						j += 2 * i;
					}
					// 改变下次的标志位
					flag *= -1;
				}
			}
			// 结束一行的遍历，标志位还原
			flag = 1;
		}
		return buffer.toString();
	}
}