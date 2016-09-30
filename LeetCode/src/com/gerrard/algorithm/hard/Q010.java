package com.gerrard.algorithm.hard;

public class Q010 {

	// 只考虑正则表达式有"."和"*"的情况，其他符号不考虑，包括括号
	public static void main(String[] args) {
		System.out.println(method("aa", "a"));
		System.out.println(method("aa", "aa"));
		System.out.println(method("aa", "aaaa"));
		System.out.println(method("aa", "a*"));
		System.out.println(method("aa", ".*"));
		System.out.println(method("ab", ".*"));
		System.out.println(method("aab", "c*a*b*"));
		System.out.println(method("aab", ".*a"));
		System.out.println(method("aab", ".*ab"));
	}

	private static boolean method(String str, String regularStr) {
		String strWithoutHead = str;
		String regularStrWithoutHead = regularStr;
		int alreadyMatchedLength = 0;
		// 待处理的正则字符串
		String regularStrToDeal = null;
		int strLengthToReduce;
		while (alreadyMatchedLength < str.length()) {
			// 因为退出条件是解析完成字符串长度=原长度，
			// 所以一次循环完成时，要判断一下，正则的长度够不够
			if (regularStrWithoutHead.length() == 0) {
				return false;
			}
			if (regularStrWithoutHead.length() > 1 && regularStrWithoutHead.substring(1, 2).equals("*")) {
				// 第二个数是"*"情况
				regularStrToDeal = regularStrWithoutHead.substring(0, 2);
				// 考虑到".*"的情况，把剩余整个正则和待处理字符串传进去
				strLengthToReduce = matchStarLength(strWithoutHead, regularStrWithoutHead);
				// ".*"的特殊处理，因为有递归，这里就是一个出口
				if (strLengthToReduce == -1) {
					return true;
				} else if (strLengthToReduce == -2) {
					return false;
				}
			} else {
				// 单个匹配情况
				regularStrToDeal = regularStrWithoutHead.substring(0, 1);
				if (!singleStringMatch(strWithoutHead.substring(0, 1), regularStrToDeal)) {
					return false;
				}
				strLengthToReduce = 1;
			}
			// 增加已处理的字符串长度
			alreadyMatchedLength += strLengthToReduce;
			// 去头
			strWithoutHead = str.substring(alreadyMatchedLength);
			regularStrWithoutHead = regularStrWithoutHead.substring(regularStrToDeal.length());
		}
		// 待解析完成，但正则还有
		if (regularStrWithoutHead.length() > 0) {
			return false;
		}
		return true;
	}

	// 单个字符匹配问题
	private static boolean singleStringMatch(String str, String regularStr) {
		// 特殊符号"."处理
		if (regularStr.equals(".")) {
			return true;
		} else if (str.equals(regularStr)) {
			return true;
		}
		return false;
	}

	// 由于"*"一定会匹配成功，返回原字符串的匹配长度
	// str不是原字符串，是"*"开始匹配的第一个位置
	private static int matchStarLength(String str, String regularString) {
		int length = 0;
		if (regularString.substring(0, 1).equals(".")) {
			// 最最最烦的一点：".*"处理
			// 先把对应的正则字符串去掉".*"
			String regularRemain = regularString.substring(2);
			// ".*"之后不跟，匹配一切
			if (regularRemain.equals("")) {
				// 返回剩下的字符串长度
				return str.length();
			}
			// 用余下的东西递归
			for (int i = 0; i < str.length(); i++) {
				String remain = str.substring(i);
				// 开始递归
				if (method(remain, regularRemain)) {
					// 只要出现true，直接整个都可以匹配
					return -1;
				}
			}
			// 余下的都不成功，表示整个不匹配
			return -2;
		} else {
			// 正常字符+"*"
			String regularInUse = regularString.substring(0, 1);
			for (int i = 0; i < str.length(); i++) {
				if (regularInUse.equals(str.substring(i, i + 1))) {
					length++;
				} else {
					break;
				}
			}
		}
		return length;
	}
}