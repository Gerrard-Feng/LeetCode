package com.gerrard.algorithm.easy;

public class Q009 {

	public static void main(String[] args) {
		System.out.println(method(0));
		System.out.println(method(2));
		System.out.println(method(34));
		System.out.println(method(345543));
		System.out.println(method(1246421));
	}

	private static boolean method(int number) {
		// 负数不在讨论范围
		if (number < 0) {
			return false;
		}
		// 确定最高位（0的最高位数是0，等下特殊处理）
		int maxLevel = 0;
		int copyNumber = number;
		while (copyNumber > 0) {
			copyNumber /= 10;
			maxLevel++;
		}
		// 低高位
		int low;
		int high;
		// 无论最高位是奇偶，遍历至maxLevel/2就行了
		// maxLevel=0在这里直接跳过了
		for (int i = 1; i < maxLevel / 2 + 1; i++) {
			// 低位+高位的和是maxLevel+1
			low = getIndexNumber(i, number);
			high = getIndexNumber(maxLevel + 1 - i, number);
			if (!(low == high)) {
				return false;
			}
		}
		return true;
	}

	// 获取一个数字具体某一位的数字
	// index是自低位起的数字
	private static int getIndexNumber(int index, int number) {
		return (int) ((number / Math.pow(10, index - 1)) % 10);
	}
}