package com.gerrard.algorithm.easy;

public class Q006 {

	public static void main(String[] args) {
		String str1 = "PAYPALISHIRING";
		System.out.println(method(str1, 3));
		String str2 = "abcdefghijklmnopqrstuvwxyz";
		System.out.println(method(str2, 5));
		System.out.println(method(str2, 4));
	}

	private static String method(String text, int nRows) {
		char[] array = text.toCharArray();
		StringBuffer sb = new StringBuffer();
		// 从上至下/从下至上的标志位
		int flag = 1;
		// 按行遍历
		for (int i = 0; i < nRows; i++) {
			// 每一行的数据
			for (int j = 0; i + j < array.length;) {
				sb.append(array[i + j]);
				// 极点有特殊的处理方式
				if (i == 0 || i == nRows - 1) {
					j += 2 * (nRows - 1);
				} else {
					if (flag == 1) {
						// 从上至下处理
						j += 2 * (nRows - i - 1);
					} else {
						// 从下至上处理
						j += 2 * i;
					}
					// 下次改变方向
					flag *= -1;
				}
			}
			// 结束一行遍历，方向标志位还原
			flag = 1;
		}
		return sb.toString();
	}
}