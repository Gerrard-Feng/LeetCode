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
		String s2 = "goodoooogoodwordbestbestgoodgoodgoodbestword";
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
		String[] words4 = { "aba", "bab" };
		for (int a : findSubstring(s4, words4)) {
			System.out.print(a + " ");
		}
	}

	public static List<Integer> findSubstring(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
		if (words == null || s == null || words.length == 0) {
			return result;
		}
		// 总长度，单条子串长度，遍历终点
		int total = s.length();
		int len = words[0].length();
		int last = total - len + 1;
		// 先判断一下可能直接退出的情况
		if (total < len * words.length) {
			return result;
		}
		// 后续用来比对的二维数组
		int[][] compare = new int[2][words.length];
		// 将子串数组做出转换，放入一个 Map 中，其中的数字代表一个子串，相同的子串对应的数字相同，长度不定
		Map<String, Integer> map = new HashMap<>();
		// 对已经匹配的进行计数
		int matched = 0;
		for (int i = 0; i < words.length; i++) {
			Integer j = map.get(words[i]);
			if (j == null) {
				map.put(words[i], matched);
				j = matched++;
			}
			// 第二维的下标，就是子串代表的数字，第二维的值，就是其出现的次数
			compare[0][j]++;
		}
		// 将父串转化成数组，其中 -1 代表不存在于子串中，其他数字代表子串你对应的值，长度确定
		int[] smap = new int[last];
		for (int i = 0; i < smap.length; i++) {
			String str = s.substring(i, i + len);
			Integer j = map.get(str);
			smap[i] = j == null ? -1 : j;
		}
		// 开始通过转化的 Map 和数组进行判断，将原字符串分成 len 组
		for (int i = 0; i < len; i++) {
			// 每一组开始时，先将比对数组清空
			Arrays.fill(compare[1], 0);
			// 单个子串字符匹配
			int currentFailures = matched;
			for (int start = i, end = i; end < last;) {
				while (currentFailures > 0 && end < last) {
					int target = smap[end];
					// 这里如果出现 target=-1，可以选择直接重置
					// 从局部上来说这么做会增强效率，但是一般都是只在共计 len 组中的，某一组得力，从整体上来讲，效率会降低
					if (target != -1 && compare[0][target] == ++compare[1][target]) {
						// 出现子串中有重复情况时，只有最后一个重复的子串匹配之后，才执行减一操作
						currentFailures--;
					}
					end += len;
				}
				// 父串已经遍历完了子串的所有内容，但是总长度不定，从 start 位置开始考虑
				while (currentFailures == 0 && start < end) {
					int target = smap[start];
					// 去掉开头第一个字符串，判断超过匹配次数的情况
					if (target != -1 && compare[0][target] - 1 == --compare[1][target]) {
						// 通过长度判断是否符合条件
						if (end - start == len * words.length) {
							result.add(start);
						}
						// 去掉开头的字符串之后，父串不能完全覆盖子串，要 currentFailures 加一，跳出循环
						currentFailures++;
					}
					start += len;
				}
			}
		}
		return result;
	}

	// 每次前移一位，效率极低
	public static List<Integer> method_1(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
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

	// 在 method_2 的基础上，对原字符串进行分组
	// 使用一个 List 记录已经遇到过的字符串，尽可能地减少下一次开始匹配的位置
	// 如果匹配失败时，当前字符串在 List 中不存在，下一次字符串开始位置可以直接定位到这个错误字符串之后
	// 如果匹配失败时，当前字符串在 List 中存在，对比 List 中的第一个字符串，若不相同，下一次匹配在这个位置时失败
	public static List<Integer> method_3(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
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

	// 在 method_3 的基础上，使用 Map 代替 List
	// 在预想中可能会提高速度，实际上却降低了效率
	public static List<Integer> method_4(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
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
				if (successSituation == 2) {
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

	private static boolean containsMap(Map<String, Integer> map, String str) {
		if (!map.containsKey(str) || map.get(str) == 0) {
			return false;
		}
		return true;
	}

	// 样例程序
	public static List<Integer> sample(String s, String[] words) {
		int N = s.length();
		List<Integer> indexes = new ArrayList<Integer>(s.length());
		if (words.length == 0) {
			return indexes;
		}
		int M = words[0].length();
		if (N < M * words.length) {
			return indexes;
		}
		int last = N - M + 1;
		// map each string in words array to some index and compute target
		// counters
		Map<String, Integer> mapping = new HashMap<String, Integer>(words.length);
		int[][] table = new int[2][words.length];
		int failures = 0, index = 0;
		for (int i = 0; i < words.length; ++i) {
			Integer mapped = mapping.get(words[i]);
			if (mapped == null) {
				++failures;
				mapping.put(words[i], index);
				mapped = index++;
			}
			++table[0][mapped];
		}
		// find all occurrences at string S and map them to their current
		// integer, -1 means no such string is in words array
		int[] smapping = new int[last];
		for (int i = 0; i < last; ++i) {
			String section = s.substring(i, i + M);
			Integer mapped = mapping.get(section);
			if (mapped == null) {
				smapping[i] = -1;
			} else {
				smapping[i] = mapped;
			}
		}
		// fix the number of linear scans
		for (int i = 0; i < M; ++i) {
			// reset scan variables
			int currentFailures = failures; // number of current mismatches
			int left = i, right = i;
			Arrays.fill(table[1], 0);
			// here, simple solve the minimum-window-substring problem
			while (right < last) {
				while (currentFailures > 0 && right < last) {
					int target = smapping[right];
					if (target != -1 && ++table[1][target] == table[0][target]) {
						--currentFailures;
					}
					right += M;
				}
				while (currentFailures == 0 && left < right) {
					int target = smapping[left];
					if (target != -1 && --table[1][target] == table[0][target] - 1) {
						int length = right - left;
						// instead of checking every window, we know exactly the
						// length we want
						if ((length / M) == words.length) {
							indexes.add(left);
						}
						++currentFailures;
					}
					left += M;
				}
			}
		}
		return indexes;
	}
}