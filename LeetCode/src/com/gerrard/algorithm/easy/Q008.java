package com.gerrard.algorithm.easy;

public class Q008 {

	public static void main(String[] args) {
		System.out.println(method(" 23.45"));
		System.out.println(method(" a23.45"));
		System.out.println(method(" 2a3.45"));
		System.out.println(method("   +23.45"));
		System.out.println(method("   -23.45"));
		System.out.println(method("   2345"));
		System.out.println(method("123456789876"));
		System.out.println(method("-123456789876"));
	}

	private static int method(String str) {
		// 先去空格
		char[] array = str.trim().toCharArray();
		// 0特殊处理
		if (array.length == 0) {
			return 0;
		}
		// 正负号标志位
		int operatorFlag = 1;
		// 记录返回数字，超过long表示范围不考虑
		long sum = 0;
		if (array[0] == '+' || array[0] == '-' || (array[0] >= '0' && array[0] <= '9')) {
			// 操作符置0，便于统一处理
			if (array[0] == '+') {
				// 这里赋值，注意是char型的
				array[0] = '0';
				operatorFlag = 1;
			} else if (array[0] == '-') {
				array[0] = '0';
				operatorFlag = -1;
			}
			// 最高位
			int maxLevel = 0;
			// 循环确定最高位
			for (int i = 0; i < array.length; i++) {
				if (array[i] >= '0' && array[i] <= '9') {
					maxLevel++;
				} else {
					// 遇到非数字退出
					break;
				}
			}
			// 需要一个maxLevel的副本来赋值
			int copyMaxLevel = maxLevel;
			// 累加赋值，注意其实位置的下标
			for (int i = 0; i < maxLevel; i++) {
				// 注意char型的数字，转成数字的处理
				sum += Character.valueOf((char) (array[i] - '0')) * Math.pow(10, --copyMaxLevel);
			}
			// 超出范围处理
			long realNumber = sum * operatorFlag;
			if (realNumber > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			if (realNumber < Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
			return (int) realNumber;
		} else {
			return 0;
		}
	}
}