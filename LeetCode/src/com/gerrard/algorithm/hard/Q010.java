package com.gerrard.algorithm.hard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q010 {

	// 只考虑正则表达式有"."和"*"的情况，其他符号不考虑
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
		System.out.println(check("bbabcaccacbcacaa", ".*.c*a*.c"));
	}

	// 校验匹配的准确性
	private static boolean check(String s, String p) {
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches() == isMatch(s, p);
	}

	public static boolean isMatch(String s, String p) {
		// 递归方法不适用入参检查
		if (s == null || p == null) {
			throw new IllegalArgumentException("Input error");
		}
		// 最后一位匹配失败，直接匹配失败
		if (p.length() > 0 && s.length() > 0 && !p.substring(p.length() - 1).equals("*")
				&& !singleMatch(s.charAt(s.length() - 1), p.charAt(p.length() - 1))) {
			return false;
		}
		return isMatchTrue(s, p);
	}

	private static boolean isMatchTrue(String s, String p) {
		// 待处理的正则
		String pDeal;
		// 消去的字符串长度
		int sReduce;
		// 以字符串为主体，匹配正则
		while (s.length() > 0) {
			// 正则长度为0
			if (p.length() == 0) {
				return false;
			}
			if (p.length() > 1 && p.charAt(1) == '*') {
				// 第二个字符：*
				pDeal = p.substring(0, 2);
				sReduce = starMatch(s, p);
				// 在内部方法中，已经通过递归得出结果
				if (sReduce == -1) {
					return true;
				} else if (sReduce == -2) {
					return false;
				}
			} else {
				// 单字符匹配
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
		// 字符串解析完成，但正则还有剩余
		if (!regularEqualsNull(p)) {
			return false;
		}
		return true;
	}

	// 普通字符+*，返回*匹配的长度
	private static int starMatchNormal(String s, String p) {
		char pBeforeStar = p.charAt(0);
		// 正则长度：2
		if (p.length() == 2) {
			return getLength(s, pBeforeStar);
		}
		char pAfterStar = p.charAt(2);
		// 正则长度：3
		if (p.length() == 3) {
			int l = getLength(s, pBeforeStar);
			// 字符串s的最后一个字符，会影响*匹配长度
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
		// 正则第四个字符：*
		if (p.charAt(3) == '*') {
			// 如(aaabcd,a*.*d)
			// a*是否存在，不影响整体的匹配结果
			// 但是可以尽可能消去字符串s中，a起始的个数，减小.*匹配的负担
			if (pAfterStar == '.') {
				return getLength(s, pBeforeStar);
			}
			// 如a*a*，与a*等价
			if (pAfterStar == pBeforeStar) {
				return isMatchTrue(s, p.substring(2)) ? -1 : -2;
			}
			// 余下情况，如a*b*，考虑b*匹配长度
			if (pAfterStar != notXFromStart(s, pBeforeStar)) {
				// b*匹配长度：0
				return isMatchTrue(s, p.substring(0, 2) + p.substring(4)) ? -1 : -2;
			}
			// b*匹配长度大于1；或字符串全部由a组成
			return getLength(s, pBeforeStar);
		} else {
			// 如a*.
			// 无法确定*匹配的具体长度
			if (p.charAt(2) == '.') {
				// a*之后，所有的正则
				String pAfterPoint = p.substring(2);
				// 匹配字符串中，*所有可能性
				int posibility = getLength(s, pBeforeStar) + 1;
				for (int i = 0; i < posibility; i++) {
					if (isMatchTrue(s, pAfterPoint)) {
						return -1;
					}
					if (s.length() == 0) {
						return -2;
					}
					s = s.substring(1);
				}
				return -2;
			}
			// 如a*a
			if (p.charAt(2) == pBeforeStar) {
				// a*之后a的个数
				int count = 1;
				for (int i = 3; i < p.length(); i++) {
					if (p.charAt(i) == pBeforeStar) {
						count++;
					} else {
						break;
					}
				}
				// 如a*aaaab的b
				Character after = count == p.length() - 2 ? null : p.charAt(count + 2);
				int l = getLength(s, pBeforeStar);
				if (after == null || !(after.equals('.') || after.equals('*'))) {
					// 类似a*aaab一定不匹配aab
					if (count > l) {
						return -2;
					}
					return l - count;
				}
				// 如(a*aaa.b,aaaacb)，count=3
				if (after.equals('.')) {
					if (count > l) {
						return -2;
					}
					// 等价(a*.b,acb)
					s = s.substring(count);
					p = p.substring(0, 2) + p.substring(2 + count);
					if (isMatchTrue(s, p)) {
						return -1;
					}
					return -2;
				}
				// 如(a*aaa*b,aaaacb)，count=2
				// 等价(a*b,aacb)
				if (after.equals('*')) {
					count--;
					if (count > l) {
						return -2;
					}
					s = s.substring(count);
					p = p.substring(2 + count);
					if (isMatchTrue(s, p)) {
						return -1;
					}
					return -2;
				}
			}
			// 余下情况，如a*ba
			return getLength(s, pBeforeStar);
		}
	}

	// 返回*匹配长度
	private static int starMatch(String s, String p) {
		if (p.charAt(0) == '.') {
			// .*
			p = p.substring(2);
			// .*之后的正则，如果可以匹配空字符串，直接匹配成功
			if (regularEqualsNull(p)) {
				return -1;
			}
			// 用余下的正则，循环递归
			for (int i = 0; i < s.length(); i++) {
				String sAfter = s.substring(i);
				if (isMatchTrue(sAfter, p)) {
					return -1;
				}
			}
			// 余下的都不成功，表示整个不匹配
			return -2;
		} else {
			return starMatchNormal(s, p);
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
	private static char notXFromStart(String s, char x) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != x) {
				return s.charAt(i);
			}
		}
		// s全部由x组成
		return x;
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