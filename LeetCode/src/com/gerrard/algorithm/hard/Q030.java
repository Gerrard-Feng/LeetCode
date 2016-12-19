package com.gerrard.algorithm.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
		List<String> wordList = new LinkedList<>(Arrays.asList(words));
		int len = words[0].length();
		int total = len * wordList.size();
		int[] last = new int[len];
		for (int a : last) {
			a = -1;
		}
		
		// 上一组的匹配结果
		List<String>[] lastMatch = new List<String>[len];
		// 单独计算第一组的结果
		for (int i = 0; i < len && i < s.length() + 1 - total; i++) {
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
			if (index == i + total) {
				result.add(i);
			}
		}

		// 之后的判断，全部基于第一组的结果

		for (int i = 0; i < s.length() + 1 - total; i++) {

			// 有成功匹配之后，优先校验当前与上一次匹配的第一个字符串
			if (last[i % len] > 0 && i == last[i % len] + total
					&& s.substring(i, i + len).equals(s.substring(last[i % len], last[i % len] + len))) {
				result.add(i);
				last[i % len] = i;
				break;
			}
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
			// 全匹配成功
			if (index == i + total) {
				result.add(i);
				last[i % len] = i;
			}
		}
		return result;
	}

	// 每次前移一位，效率极低
	public static List<Integer> method(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
		if (words == null || s == null || words.length == 0) {
			return result;
		}
		List<String> wordList = new ArrayList<>(Arrays.asList(words));
		int len = words[0].length();
		for (int i = 0; i < s.length(); i++) {
			List<String> copy = new LinkedList<>(wordList);
			int index = i;
			while (copy.size() != 0 && index + len < s.length() + 1) {
				String cur = s.substring(index, index + len);
				if (!copy.contains(cur)) {
					break;
				}
				copy.remove(cur);
				index += len;
			}
			if (index == i + len * wordList.size()) {
				result.add(i);
			}
		}
		return result;
	}
}