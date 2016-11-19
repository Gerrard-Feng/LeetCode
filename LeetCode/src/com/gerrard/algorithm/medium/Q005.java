package com.gerrard.algorithm.medium;

public class Q005 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		String str1 = "cabcbaabcaaaaaaa";
		System.out.println(longestPalindrome(str1));

		System.out.println("<==========第二组测试==========>");
		String str2 = "cbaabc";
		System.out.println(longestPalindrome(str2));
	}

	public static String longestPalindrome(String s) {
		if (s == null || s.length() > 1000) {
			throw new IllegalArgumentException("Input error");
		}
		char[] array = s.toCharArray();
		// 最大回文子串长度和其开始位置索引
		int maxLength = 0;
		int startIndex = 0;
		for (int i = 0; i < array.length; i++) {
			// 超过字符串一半之后，理论上可能达到的最大长度小于当前的最大长度，直接退出循环
			if (i > (array.length - 1) / 2 && 2 * (array.length - 1 - i) + 1 < maxLength) {
				break;
			}
			// 分奇偶计算回文长度
			int count1 = extend(array, i, i);
			int count2 = extend(array, i, i + 1);
			int count = Math.max(count1, count2);
			if (count > maxLength) {
				maxLength = count;
				// 奇偶的startIndex可以统一成一个表达式
				startIndex = i - (count - 1) / 2;
			}
		}
		return s.substring(startIndex, startIndex + maxLength);
	}

	// 奇数回文和偶数回文统一处理
	private static int extend(char[] array, int left, int right) {
		// 回文长度和外扩次数
		int count = 0;
		int extendTime = 0;
		// 外扩至超出数组范围
		while (left - extendTime >= 0 && right + extendTime < array.length) {
			// 不对称，直接跳出
			if (array[left - extendTime] != array[right + extendTime]) {
				break;
			}
			extendTime++;
			count += 2;
		}
		// 奇数回文，最终长度减一
		if (left == right) {
			count--;
		}
		return count;
	}
}