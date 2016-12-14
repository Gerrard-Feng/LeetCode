package com.gerrard.algorithm.medium;

public class Q029 {

	public static void main(String[] args) {
		System.out.println(1 >> 1 << 1);
		System.out.println(divide(15, 2));
	}

	// 不使用，乘法、除法、余数，实现除法逻辑
	public static int divide(int dividend, int divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException("Input error");
		}
		if (dividend == Integer.MIN_VALUE && divisor == 1) {
			return Integer.MAX_VALUE;
		}
		int dividend2 = 0;
		int count = 0;
		// 用二进制加法的原理逆向思维，累加除法的和
		int sum = dividend >= divisor ? 1 : 0;
		while (dividend > divisor) {
			if (dividend >>> 1 << 1 != dividend) {
				dividend2 += 1 << count;
			}
			count++;
			sum <<= 1;
			dividend >>>= 1;
		}
		if (dividend != divisor) {
			sum >>>= 1;
		}
		if (dividend2 != 0) {
			sum += divide(dividend2, divisor);
		}
		return sum;
	}
}