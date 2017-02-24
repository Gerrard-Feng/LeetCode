package com.gerrard.algorithm.hard;

import java.util.HashMap;
import java.util.Map;

public class Q076 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		String s1 = "ADOBECODEBANC";
		String t1 = "ABC";
		System.out.println(minWindow(s1, t1));

		System.out.println("<==========第二组测试==========>");
		String s2 = "cabwefgewcwaefgcf";
		String t2 = "cae";
		System.out.println(minWindow(s2, t2));
	}

	// 逻辑与 method_1 相同，不使用对象，全部都是基本类型
	// 用一个 int[] 代替 Map 的作用
	// int[] 数组的大小，需要超过所有输入字符串的 ASCⅡ 码的值
	public static String minWindow(String s, String t) {
		// 使用基本类型 char 的数组
		char[] sArray = s.toCharArray();
		char[] tArray = t.toCharArray();
		// 代替 Map 的数组，容量需要足够大
		int[] map = new int[256];
		// 在子串对应的 ASCⅡ 码的位置上，计算出现的次数
		for (int i = 0; i < tArray.length; i++) {
			map[tArray[i]]++;
		}
		int count = tArray.length, minStart = 0, minLength = Integer.MAX_VALUE;
		// count 的计数规则与 method_1 相反，最初是目标长度，等于 0 时表示匹配
		for (int start = 0, end = 0; end < sArray.length; end++) {
			if (map[sArray[end]] > 0) {
				count--;
			}
			// 保证无关项的值始终小于等于 0
			map[sArray[end]]--;
			while (count == 0) {
				int len = end - start + 1;
				if (len < minLength) {
					minStart = start;
					minLength = len;
				}
				map[sArray[start]]++;
				// 表示遇到了相关项
				if (map[sArray[start]] > 0) {
					count++;
				}
				start++;
			}
		}
		return minStart + minLength > sArray.length ? "" : s.substring(minStart, minStart + minLength);
	}

	// 过于频繁的自动装箱和自动拆箱，对算法的效率造成了一定的影响
	public static String method_1(String s, String t) {
		// 将子串 t 放入 Map
		Map<Character, Integer> map = new HashMap<>();
		for (char c : t.toCharArray()) {
			Integer i = map.get(c);
			if (i == null) {
				map.put(c, 1);
			} else {
				map.put(c, i + 1);
			}
		}
		// 长度计数，最小长度开始位置，最小长度
		int count = 0, minStart = 0, minLength = Integer.MAX_VALUE;
		for (int start = 0, end = 0; end < s.length(); end++) {
			Character cur = s.charAt(end);
			if (!map.containsKey(cur)) {
				continue;
			}
			int i = map.get(cur);
			map.put(cur, i - 1);
			if (i > 0) {
				count++;
			}
			// 匹配成功，开启内循环，处理两件事情
			// 第一，去除字符串开头的冗余部分
			// 第二，找到最简字符串之后，去掉第一个 t 中的字符
			while (count == t.length()) {
				// 这里注意+1，因为下标的差值不等于子串的长度
				int len = end - start + 1;
				if (len < minLength) {
					minStart = start;
					minLength = len;
				}
				// 处理 start，注意开始部分出现类似 AA 这种重复现象
				char c = s.charAt(start);
				if (map.containsKey(c)) {
					int j = map.get(c);
					map.put(c, j + 1);
					if (j >= 0) {
						count--;
					}
				}
				start++;
			}
		}
		return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
	}
}