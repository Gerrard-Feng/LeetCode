package com.gerrard.algorithm.hard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q010 {

	// 只考虑正则表达式有"."和"*"的情况，其他符号不考虑，包括括号
	public static void main(String[] args) {
		System.out.println(check("aa", "a"));
		System.out.println(check("aa", "aa"));
		System.out.println(check("aa", "aaaa"));
		System.out.println(check("aa", "a*"));
		System.out.println(check("aa", ".*"));
		System.out.println(check("ab", ".*"));
		System.out.println(check("aab", "c*a*b*"));
		System.out.println(check("aab", ".*a"));
		System.out.println(check("aab", ".*ab"));
		System.out.println(check("aaa", "a*a"));
		System.out.println(check("aa", "a*c*a"));
		System.out.println(check("aca", "a*c*a"));
		System.out.println(check("", "b*"));
		System.out.println(check("a", "c*c"));
		System.out.println(check("aa", "b*a"));
		System.out.println(check("aaaaaabaabcabac", ".*a*.*b*"));
		System.out.println(check("c", "c*."));
		System.out.println(check("bbbacbaacacaaaba", "b*c*b*.a.*a*.*.*b*"));
		System.out.println(check("a", "a*.a*"));
		System.out.println(check("aaaa", "a*b*.aaa"));
		System.out.println(check("bbbcd", "b*bbbcd"));
		System.out.println(check("aabcbcbcaccbcaabc", "a*aa*.*b*.c*.*a*"));
		System.out.println(check("cbbbaababbac", ".*c*."));
		System.out.println(check("a", "a*aa*"));
	}

	// 校验匹配的准确性
	private static boolean check(String s, String p) {
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches() == isMatch(s, p);
	}

	public static boolean isMatch(String s, String p) {
		// 递归方法时不适合使用入参检查的
		// 待处理的正则
		String pDeal;
		// 消去的字符串长度
		int sReduce;
		while (s.length() > 0) {
			// 正则长度为0
			if (p.length() == 0) {
				return false;
			}
			if (p.length() > 1 && p.charAt(1) == '*') {
				// 第二个数是"*"情况
				pDeal = p.substring(0, 2);
				sReduce = starMatch(s, p);
				// 在内部方法中，已经通过递归得出结果
				if (sReduce == -1) {
					return true;
				} else if (sReduce == -2) {
					return false;
				}
			} else {
				// 单个匹配情况
				pDeal = p.substring(0, 1);
				if (!singleMatch(s.charAt(0), p.charAt(0))) {
					return false;
				}
				sReduce = 1;
			}
			// 消去字符串
			s = s.substring(sReduce);
			p = p.substring(pDeal.length());
		}
		// 待解析完成，但正则还有
		if (!regularEqualsNull(p)) {
			return false;
		}
		return true;
	}

	// 普通字符+"*"，返回"*"匹配的长度
	private static int starMatchWithoutPoint(String s, String p) {
		// "*"之前的字符
		char pBeforeStar = p.charAt(0);
		// 正则长度是2的情况
		if (p.length() == 2) {
			return getLength(s, pBeforeStar);
		}
		char pAfterStar = p.charAt(2);
		// 正则长度是3的情况
		if (p.length() == 3) {
			int l = getLength(s, pBeforeStar);
			// 根据s的最后一个字符，改变返回长度
			if (singleMatch(s.charAt(s.length() - 1), pAfterStar)) {
				if (singleMatch(pBeforeStar, pAfterStar)) {
					l--;
				}
			} else {
				// 最后一个字符不匹配，整体不匹配
				return -2;
			}
			return l < 0 ? 0 : l;
		}
		// 正则第四个为值是*
		if (p.charAt(3) == '*') {
			// .*匹配所有情况，所以前两个正则可以尽可能多的消去
			if (pAfterStar == '.') {
				return getLength(s, pBeforeStar);
			}
			// 类似a*a*等价于a*，递归
			if (pAfterStar == pBeforeStar) {
				return starMatchWithoutPoint(s, p.substring(2));
			}
			// 余下情况，类似于a*b*，先考虑b*匹配长度
			// 顺序不能反，后者有null的风险
			// 触发自动装箱
			Character c = pAfterStar;
			if (!c.equals(notXFromStart(s, pBeforeStar))) {
				// 去掉b*，递归
				return isMatch(s, p.substring(0, 2) + p.substring(4)) ? -1 : -2;
			}
			// b*有匹配，可以确定a*的匹配长度
			return getLength(s, pBeforeStar);
		} else {
			// 类似a*.情况
			if (p.charAt(2) == '.') {
				// a*之后，所有的正则
				String pAfterPoint = p.substring(2);
				// -1代表成功，-2代表失败
				// a*匹配0个
				if (isMatch(s, pAfterPoint)) {
					return -1;
				}
				// a*匹配1个，至字符串s前面所有的a
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == pBeforeStar) {
						// 字符串去掉第一个a
						s = s.substring(1);
						if (isMatch(s, pAfterPoint)) {
							return -1;
						}
					} else {
						return -2;
					}
				}
			}
			// 类似a*a的情况
			if (p.charAt(2) == pBeforeStar) {
				// 计算a*之后a的个数，已经至少有1个
				int count = 1;
				for (int i = 3; i < p.length(); i++) {
					if (p.charAt(i) == pBeforeStar) {
						count++;
					} else {
						break;
					}
				}
				// 类似a*aaaab的b
				Character after = count == p.length() - 2 ? null : p.charAt(count + 2);
				int l = getLength(s, pBeforeStar);
				if (after == null || !(after.equals('.') || after.equals('*'))) {
					// 类似a*aaab一定不匹配aab
					if (count > l) {
						return -2;
					}
					return l - count;
				}
				// 类似a*aaa.b匹配aaaacb，count=3
				if (after.equals('.')) {
					if (count > l) {
						return -2;
					}
					// 等价于a*.b匹配acb
					s = s.substring(count);
					p = p.substring(0, 2) + p.substring(2 + count);
					if (isMatch(s, p)) {
						return -1;
					}
					return -2;
				}
				if (after.equals('*')) {
					// 最后一个a匹配*，不作数
					count--;
					if (count > l) {
						return -2;
					}
					s = s.substring(count);
					p = p.substring(0, 2) + p.substring(2 + count);
					if (isMatch(s, p)) {
						return -1;
					}
					return -2;
				}
			}
			// 剩余情况类似a*ba
			return getLength(s, pBeforeStar);
		}
	}

	// 返回*匹配长度
	private static int starMatch(String s, String p) {
		if (p.charAt(0) == '.') {
			// .*处理，先把对应的正则字符串去掉.*
			p = p.substring(2);
			// .*之后的正则，可以匹配空字符串，直接匹配成功
			if (regularEqualsNull(p)) {
				return -1;
			}
			// 用余下的正则，循环递归
			for (int i = 0; i < s.length(); i++) {
				// 开始递归
				String sWithoutHead = s.substring(i);
				if (isMatch(sWithoutHead, p)) {
					// 只要出现true，直接整个都可以匹配
					return -1;
				}
			}
			// 余下的都不成功，表示整个不匹配
			return -2;
		} else {
			return starMatchWithoutPoint(s, p);
		}
	}

	// 单个字符匹配
	private static boolean singleMatch(char s, char p) {
		if (p == '.' || p == s) {
			return true;
		}
		return false;
	}

	// 一个可以表示为空字符串的正则表达式
	private static boolean regularEqualsNull(String p) {
		if (p.length() % 2 == 1) {
			return false;
		}
		while (p.length() > 0) {
			if (p.charAt(1) != '*') {
				return false;
			}
			p = p.substring(2);
		}
		return true;
	}

	// 在字符串s中，第一个不是x的字符
	private static Character notXFromStart(String s, char x) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != x) {
				return s.charAt(i);
			}
		}
		// s全部由x组成
		return null;
	}

	// 字符串s中，以pBeforeStar开头的个数
	private static int getLength(String s, char pBeforeStar) {
		int l = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == pBeforeStar) {
				l++;
			} else {
				break;
			}
		}
		return l;
	}
}