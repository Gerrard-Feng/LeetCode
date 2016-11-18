package com.gerrard.algorithm.medium;

import java.util.ArrayList;
import java.util.List;

public class Q003 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		String s1 = "abcabcbb";
		System.out.println(lengthOfLongestSubstring(s1));

		System.out.println("<==========第二组测试==========>");
		String s2 = "dvdf";
		System.out.println(lengthOfLongestSubstring(s2));

		System.out.println("<==========第三组测试==========>");
		String s3 = "bbbbb";
		System.out.println(lengthOfLongestSubstring(s3));

		System.out.println("<==========第四组测试==========>");
		String s4 = "pwwkew";
		System.out.println(lengthOfLongestSubstring(s4));
	}

	public static int lengthOfLongestSubstring(String s) {
		if (s == null) {
			throw new IllegalArgumentException("Input error");
		}
		char[] charArray = s.toCharArray();
		int maxLength = 0;
		List<Character> list = new ArrayList<>();
		for (int i = 0; i < charArray.length; i++) {
			Character c = charArray[i];
			// 判断当前字符是否重复
			if (list.contains(c)) {
				// 更新最大长度
				maxLength = Math.max(maxLength, list.size());
				// 删除重复之前的字符
				int lastIndex = list.indexOf(c);
				// lastIndex+1 代表删除次数
				while (lastIndex + 1 > 0) {
					list.remove(0);
					lastIndex--;
				}

			}
			// 当前字符是一定要加的
			list.add(c);
		}
		// 特殊情况考虑：最长字符串在原字符串尾部
		maxLength = Math.max(maxLength, list.size());
		return maxLength;
	}
}