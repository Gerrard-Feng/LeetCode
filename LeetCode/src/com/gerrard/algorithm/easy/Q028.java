package com.gerrard.algorithm.easy;

import java.util.ArrayList;
import java.util.List;

public class Q028 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		String haystack1 = "BBC ABCDAB ABCDABCDABDE";
		String needle1 = "ABCDABD";
		int index = strStr(haystack1, needle1);
		System.out.println(index + "\n");

		System.out.println("<==========第二组测试==========>");
		String haystack2 = "mississippi";
		String needle2 = "issipi";
		int index2 = strStr(haystack2, needle2);
		System.out.println(index2 + "\n");

		System.out.println("<==========第三组测试==========>");
		String haystack3 = "";
		String needle3 = "";
		int index3 = strStr(haystack3, needle3);
		System.out.println(index3 + "\n");
	}

	public static int strStr(String haystack, String needle) {
		// 入参检查
		if (haystack == null || needle == null) {
			return -1;
		}
		int sourceLength = haystack.length();
		int targetLength = needle.length();
		// 父串长度为0
		if (sourceLength == 0) {
			return targetLength == 0 ? 0 : -1;
		}
		// 子串长度为0
		if (targetLength == 0) {
			return 0;
		}
		char[] source = haystack.toCharArray();
		char[] target = needle.toCharArray();
		// 遍历的终点
		int max = sourceLength - targetLength;
		// 第一个字符
		char first = target[0];
		for (int i = 0; i <= max; i++) {
			// 先找到第一个匹配情况
			if (source[i] != first) {
				while (++i <= max && source[i] != first)
					;
			}
			if (i <= max) {
				int j = i + 1;
				int end = j + targetLength - 1;
				for (int k = 1; j < end && source[j] == target[k]; j++, k++)
					;
				// 完全匹配的情况
				if (j == end) {
					return i;
				}
			}
		}
		return -1;
	}

	// JDK 自带方法
	public static int jdkSource(String haystack, String needle) {
		return haystack.indexOf(needle);
	}

	// 判断 needle 子串在 haystack 第一次出现的位置，没有返回-1
	public static int KMP(String haystack, String needle) {
		if (haystack == null || needle == null) {
			return -1;
		}
		if (haystack.length() == 0 && needle.length() == 0) {
			return 0;
		}
		char[] parent = haystack.toCharArray();
		char[] child = needle.toCharArray();
		// 部分匹配表
		int[] next = partMatch(needle);
		// 遍历父串
		for (int i = 0; i < parent.length;) {
			// 父串剩余长度不及子串
			if (parent.length - i < child.length) {
				return -1;
			}
			int k = i;
			int move = 0;
			for (int j = 0; j < child.length; j++) {
				if (parent[k] == child[j]) {
					k++;
				} else {
					// 移动长度为：已匹配个数-部分匹配个数
					move = j == 0 ? 1 : j - next[j - 1];
					break;
				}
			}
			// 匹配成功情况
			if (move == 0) {
				return i;
			}
			i += move;
		}
		return -1;
	}

	// 计算子串的部分匹配值数组 next
	private static int[] partMatch(String needle) {
		int[] next = new int[needle.length()];
		for (int i = 0; i < needle.length(); i++) {
			// 当前字符串
			String cur = needle.substring(0, i + 1);
			// 前缀/后缀 数组
			List<String> prefix = new ArrayList<>();
			List<String> suffix = new ArrayList<>();
			for (int j = 1; j < cur.length(); j++) {
				prefix.add(cur.substring(0, cur.length() - j));
				suffix.add(cur.substring(cur.length() - j));
			}
			// 前缀/后缀 的共有元素
			String common = "";
			for (String str : prefix) {
				if (suffix.contains(str)) {
					common = str;
					break;
				}
			}
			next[i] = common.length();
		}
		return next;
	}
}