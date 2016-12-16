package com.gerrard.algorithm.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q030 {

	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> result = new LinkedList<>();
		if (words == null || s == null || words.length == 0) {
			return result;
		}
		List<String> wordList = Arrays.asList(words);
		int len = words[0].length();
		for (int i = 0; i < s.length() - len; i++) {
			String cur = s.substring(i, i + len);
			if (!wordList.contains(cur)) {
				continue;
			}
			List<String> copy = wordList;
		}
		return result;
	}
}