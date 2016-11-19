package com.gerrard.algorithm.easy;

public class Q009 {

	public static void main(String[] args) {

		System.out.println(isPalindrome(0));
		System.out.println(isPalindrome(2));
		System.out.println(isPalindrome(34));
		System.out.println(isPalindrome(345543));
		System.out.println(isPalindrome(1246421));
	}

	public static boolean isPalindrome(int x) {
		// 负数不在讨论范围内
		if (x < 0) {
			return false;
		}
		int length = String.valueOf(x).length();
		// 低位/高位
		int low;
		int high;
		// 循环至一半就可以退出
		for (int i = 1; i < length / 2 + 1; i++) {
			// low+high=length+1
			low = getNFromLow(i, x);
			high = getNFromLow(length + 1 - i, x);
			if (!(low == high)) {
				return false;
			}
		}
		return true;
	}

	// 获取数字x，自低位起的第n个数
	private static int getNFromLow(int n, int x) {
		return (int) ((x / Math.pow(10, n - 1)) % 10);
	}
}