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
		for (int a : findSubstring(s1, words1)) {
			System.out.print(a + " ");
		}
		System.out.println("\n");

		System.out.println("<==========第二组测试==========>");
		String s2 = "wordgoodgoodgoodbestword";
		String[] words2 = { "word", "good", "best", "good" };
		for (int a : findSubstring(s2, words2)) {
			System.out.print(a + " ");
		}
		System.out.println("\n");

		System.out.println("<==========第三组测试==========>");
		String s3 = "a";
		String[] words3 = { "a" };
		for (int a : findSubstring(s3, words3)) {
			System.out.print(a + " ");
		}
		System.out.println("\n");

		System.out.println("<==========第四组测试==========>");
		String s4 = "abababababababababab";
		String[] words4 = { "ab", "ab", "ba", "ba" };
		for (int a : findSubstring(s4, words4)) {
			System.out.print(a + " ");
		}
	}

	public static List<Integer> findSubstring(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
		if (words == null || s == null || words.length == 0) {
			return result;
		}
		// 匹配数组转成 Map 键值对存储：字符串-出现次数
		Map<String, Integer> initMap = new HashMap<>();
		Map<String, Integer> initScanned = new HashMap<>();
		for (String word : words) {
			if (initMap.containsKey(word)) {
				initMap.put(word, initMap.get(word) + 1);
			} else {
				initMap.put(word, 1);
				initScanned.put(word, 0);
			}
		}
		// 单次和总体匹配长度
		int len = words[0].length();
		int total = len * words.length;
		// 第一层循环，分为 len 组
		for (int i = 0; i < len; i++) {
			Map<String, Integer> copy = new HashMap<>(initMap);
			Map<String, Integer> scannedMap = new HashMap<>(initScanned);
			// 第二层循环，遍历当前组，每次累加 的长度由第三层循环决定
			for (int j = i; j < s.length() + 1 - total;) {
				// 成功情况，分三种
				int successSituation = 0;
				int scanned = calcScanned(scannedMap) * len;
				// 第三层循环，判断当前其实位置是否符合
				for (int k = j + scanned; k < j + total; k += len) {
					String cur = s.substring(k, k + len);
					if (!containsMap(copy, cur)) {
						if (scannedMap.containsKey(cur) && scannedMap.get(cur) > 0) {
							// 下一次遍历，已经扫描的字符串数组，去掉一个当前字符串
							successSituation = 1;
						} else {
							// 下一次遍历，从错误位置的下一个字符开始，扫描的字符串集合清空
							successSituation = 2;
						}
						break;
					} else {
						// 当前字符串存在
						scannedMap.put(cur, scannedMap.get(cur) + 1);
						copy.put(cur, copy.get(cur) - 1);
						scanned += len;
					}
				}
				// 维护2个属性：已经扫描的List，和剩余字符串的Map
				if (successSituation == 2) {
					// 下一次直接跳到当前字符串的下一个位置，List和Map重置
					j += scanned + len;
					scannedMap = new HashMap<>(initScanned);
					copy = new HashMap<>(initMap);
				} else {
					// 下一次前进一格 len 的长度，无论成功与否
					String first = s.substring(j, j + len);
					scannedMap.put(first, scannedMap.get(first) - 1);
					copy.put(first, copy.get(first) + 1);
					if (successSituation == 0) {
						// 结果集
						result.add(j);
					}
					j += len;
				}
			}
		}
		return result;
	}
	
	private static int calcScanned(Map<String, Integer> scannedMap) {
		int result = 0;
		for (String key : scannedMap.keySet()) {
			result += scannedMap.get(key);
		}
		return result;
	}
	
	public static List<Integer> method_3(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
		if (words == null || s == null || words.length == 0) {
			return result;
		}
		// 匹配数组转成 Map 键值对存储：字符串-出现次数
		Map<String, Integer> initMap = new HashMap<>();
		for (String word : words) {
			if (initMap.containsKey(word)) {
				initMap.put(word, initMap.get(word) + 1);
			} else {
				initMap.put(word, 1);
			}
		}
		// 单次和总体匹配长度
		int len = words[0].length();
		int total = len * words.length;
		// 第一层循环，分为 len 组
		for (int i = 0; i < len; i++) {
			Map<String, Integer> copy = new HashMap<>(initMap);
			List<String> scannedList = new ArrayList<>();
			// 第二层循环，遍历当前组，每次累加 的长度由第三层循环决定
			for (int j = i; j < s.length() + 1 - total;) {
				// 成功情况，分三种
				int successSituation = 0;
				// 第三层循环，判断当前其实位置是否符合
				for (int k = j + scannedList.size() * len; k < j + total; k += len) {
					String cur = s.substring(k, k + len);
					if (!containsMap(copy, cur)) {
						if (scannedList.contains(cur)) {
							// 下一次遍历，已经扫描的字符串数组，去掉一个当前字符串
							successSituation = 1;
						} else {
							// 下一次遍历，从错误位置的下一个字符开始，扫描的字符串集合清空
							successSituation = 2;
						}
						break;
					} else {
						// 当前字符串存在
						scannedList.add(cur);
						copy.put(cur, copy.get(cur) - 1);
					}
				}
				// 维护2个属性：已经扫描的List，和剩余字符串的Map
				if (successSituation == 2) {
					// 下一次直接跳到当前字符串的下一个位置，List和Map重置
					j += (scannedList.size() + 1) * len;
					scannedList = new ArrayList<>();
					copy = new HashMap<>(initMap);
				} else {
					// 下一次前进一格 len 的长度，无论成功与否
					String first = scannedList.get(0);
					scannedList.remove(first);
					copy.put(first, copy.get(first) + 1);
					if (successSituation == 0) {
						// 结果集
						result.add(j);
					}
					j += len;
				}
			}
		}
		return result;
	}
	
	private static boolean containsMap(Map<String, Integer> map, String str) {
		if (!map.containsKey(str) || map.get(str) == 0) {
			return false;
		}
		return true;
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