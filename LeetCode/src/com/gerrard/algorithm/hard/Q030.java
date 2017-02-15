package com.gerrard.algorithm.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Q030 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		String s1 = "barfoothefoobarman";
		String[] words1 = { "foo", "bar" };
		for (int a : method_1(s1, words1)) {
			System.out.print(a + " ");
		}
		System.out.println("\n");

		System.out.println("<==========第二组测试==========>");
		String s2 = "wordgoodgoodgoodbestword";
		String[] words2 = { "word", "good", "best", "good" };
		for (int a : method_1(s2, words2)) {
			System.out.print(a + " ");
		}
		System.out.println("\n");

		System.out.println("<==========第三组测试==========>");
		String s3 = "a";
		String[] words3 = { "a" };
		for (int a : method_1(s3, words3)) {
			System.out.print(a + " ");
		}
		System.out.println("\n");

		System.out.println("<==========第四组测试==========>");
		String s4 = "abababababababababab";
		String[] words4 = { "ab", "ab", "ba", "ba" };
		for (int a : method_1(s4, words4)) {
			System.out.print(a + " ");
		}
	}

	public static List<Integer> findSubstring(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
		if (words == null || s == null || words.length == 0) {
			return result;
		}
		// 匹配数组转成 Map 键值对存储：字符串-出现次数
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) {
			if (map.containsKey(word)) {
				map.put(word, map.get(word) + 1);
			} else {
				map.put(word, 1);
			}
		}
		// 单次和总体匹配长度
		int len = words[0].length();
		int total = len * words.length;
		// 第一层循环，分为 len 组
		for (int i = 0; i < len; i++) {
			Map<String, Integer> copy = new HashMap<>(map);
			List<String> scannedList = new ArrayList<>();
			// 第二层循环，遍历当前组，每次累加 len 的长度
			for (int j = 0; j < s.length() + 1 - total; j += len) {
				// 扫描长度
				int scanLen = 0;
				// 第三层循环，判断当前其实位置是否符合
				for (int k = j; k < j + total; k += len) {
					String cur = s.substring(k, k + len);
					if (!copy.containsKey(cur) || copy.get(cur) == 0) {
						if (scannedList.contains(cur)) {
							// 下一次遍历，已经扫描的字符串数组，去掉一个当前字符串
						} else {
							// 下一次遍历，从错误位置的下一个字符开始，扫描的字符串集合清空
						}
						break;
					} else {
						// 当前字符串存在
						scannedList.add(cur);
						copy.put(cur, copy.get(cur) - 1);
						scanLen += len;
					}
				}
			}
		}

		// 下一组匹配的 Map
		List<Map<String, Integer>> groupList = new ArrayList<>();
		// 上一次匹配的出错位置
		int[] wrongLocation = new int[len];
		for (int i = 0; i < len; i++) {
			wrongLocation[i] = -1;
		}
		// Map的剩余长度
		int[] mapLen = new int[len];
		for (int i = 0; i < len; i++) {
			mapLen[i] = words.length;
		}
		// 上一组的匹配结果
		// 单独计算第一组的结果
		for (int i = 0; i < len && i < s.length() + 1 - total; i++) {
			Map<String, Integer> deal = null;
			// 组号
			int groupId = i % len;
			// 先判断这一组，上次出错位置
			if (wrongLocation[groupId] == -1) {
				// 上一组成功，或者上次出错位置和开始位置匹配
				deal = groupList.get(groupId);
			} else {
				if (wrongLocation[groupId] < i) {
				} else {
					// 直接失败

				}
			}
			int index = i;
			int groupLen = mapLen[groupId];
			while (groupLen != 0) {
				String cur = s.substring(index, index + len);
				if (!deal.containsKey(cur)) {

					break;
				}
				deal.put(cur, deal.get(cur) - 1);
				groupLen--;
			}
			// 成功匹配
			if (index == i + total) {
				result.add(i);
			}
		}
		return result;
	}

	// 每次前移一位，效率极低
	public static List<Integer> method_1(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
		if (words == null || s == null || words.length == 0) {
			return result;
		}
		// 数组转集合
		List<String> wordList = Arrays.asList(words);
		// 单个字符串长度
		int len = words[0].length();
		// 匹配总长度
		int total = len * words.length;
		// 循环结束条件：剩余长度不够
		for (int i = 0; i < s.length() + 1 - total; i++) {
			// 准备具体操作的副本
			List<String> copy = new LinkedList<>(wordList);
			int index = i;
			while (copy.size() != 0) {
				String cur = s.substring(index, index + len);
				if (!copy.contains(cur)) {
					break;
				}
				copy.remove(cur);
				index += len;
			}
			// 对比预期长度
			if (index == i + total) {
				result.add(i);
			}
		}
		return result;
	}

	// 类似 method_1 逻辑，使用 Map作为容器
	// 效率较 method_1 高很多，尤其在某些极端情况下
	// 比如 words 长度很大，且全部由一个字符串组成
	public static List<Integer> method_2(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
		if (words == null || s == null || words.length == 0) {
			return result;
		}
		// 匹配数组转成 Map 键值对存储：字符串-出现次数
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) {
			if (map.containsKey(word)) {
				map.put(word, map.get(word) + 1);
			} else {
				map.put(word, 1);
			}
		}
		int len = words[0].length();
		int total = len * words.length;
		for (int i = 0; i < s.length() + 1 - total; i++) {
			Map<String, Integer> copy = new HashMap<>(map);
			int index = i;
			int mapLen = words.length;
			while (mapLen != 0) {
				String cur = s.substring(index, index + len);
				if (!copy.containsKey(cur) || copy.get(cur) == 0) {
					break;
				}
				copy.put(cur, copy.get(cur) - 1);
				index += len;
				mapLen--;
			}
			if (index == i + total) {
				result.add(i);
			}
		}
		return result;
	}
}