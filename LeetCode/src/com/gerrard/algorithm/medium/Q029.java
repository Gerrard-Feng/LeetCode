package com.gerrard.algorithm.medium;

public class Q029 {

	public static void main(String[] args) {

		System.out.println(divide(-36, 5));
	}

	// 不使用乘法、除法、余数，实现除法逻辑
	public static int divide(int dividend, int divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException("Input error");
		}
		// 只有一种越界情况
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		// 除数是 MIN_VALUE
		if (divisor == Integer.MIN_VALUE) {
			return dividend == Integer.MIN_VALUE ? 1 : 0;
		}
		// 除数是1或-1
		if (divisor == 1) {
			return dividend;
		}
		if (divisor == -1) {
			return -dividend;
		}
		// 商数的符号位
		boolean sign = dividend > 0 ^ divisor > 0;
		// 考虑 MIN_VALUE 的问题，转换成绝对值的负数计算
		dividend = dividend > 0 ? -dividend : dividend;
		divisor = divisor > 0 ? -divisor : divisor;
		int quotient = negativeDivide(dividend, divisor);
		return sign ? -quotient : quotient;
	}

	// dividend 负数，divisor 负数
	private static int negativeDivide(int dividend, int divisor) {
		if (divisor < dividend) {
			return 0;
		}
		int high = 0;
		// 确定商的最高位 high（二进制的最高位）
		while ((divisor << high) >= dividend && divisor << high < 0 && high < 31) {
			high++;
		}
		// 最高位退一位，累加部分商
		int rest = dividend - (divisor << (high - 1));
		// 当前的部分商
		int quotient = 1 << (high - 1);
		if (rest > divisor) {
			return quotient;
		}
		return quotient + negativeDivide(rest, divisor);
	}
}