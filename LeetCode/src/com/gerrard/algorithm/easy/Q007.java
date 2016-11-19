package com.gerrard.algorithm.easy;

public class Q007 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		System.out.println(reverse(0));
		System.out.println();

		System.out.println("<==========第二组测试==========>");
		System.out.println(reverse(123));
		System.out.println();

		System.out.println("<==========第三组测试==========>");
		System.out.println(reverse(-123));
		System.out.println();

		System.out.println("<==========第四组测试==========>");
		System.out.println(reverse(1534236469));
		System.out.println();

		System.out.println("<==========第五组测试==========>");
		System.out.println(reverse(Integer.MIN_VALUE));
	}

	public static int reverse(int x) {
		// 正负的标志位
		int sign = 1;
		// 考虑负数转化
		if (x < 0) {
			x = -x;
			sign = -1;
		}
		long sum = 0;
		// 先获取位数
		int length = String.valueOf(x).length();
		// 取余数，乘以因子累加
		while (x > 0) {
			// 要先减length
			sum += (x % 10) * Math.pow(10, --length);
			x /= 10;
		}
		return sum > Integer.MAX_VALUE ? 0 : (int) sum * sign;
	}
}