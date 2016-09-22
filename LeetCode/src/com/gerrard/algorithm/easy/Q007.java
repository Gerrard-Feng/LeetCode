package com.gerrard.algorithm.easy;

public class Q007 {

	public static void main(String[] args) {
		System.out.println(method(0));
		System.out.println(method(123));
		System.out.println(method(-123));
	}

	private static int method(int number) {
		// 正负的标志位
		int flag = 1;
		// 考虑负数转化
		if (number <= 0) {
			number = -number;
			flag = -1;
		}
		int sum = 0;
		// 先获取最高位，准备一个副本
		int maxLevel = 0;
		int temp = number;
		while (temp > 0) {
			temp /= 10;
			maxLevel++;
		}
		// 取余数，乘以因子累加
		while (number > 0) {
			// 要先减maxLevel
			sum += (number % 10) * Math.pow(10, --maxLevel);
			number /= 10;
		}
		return sum * flag;
	}
}