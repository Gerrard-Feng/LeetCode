package com.gerrard.algorithm.easy;

public class Q008 {

	public static void main(String[] args) {
		System.out.println(myAtoi(" 23.45"));
		System.out.println(myAtoi(" a23.45"));
		System.out.println(myAtoi(" 2a3.45"));
		System.out.println(myAtoi("   +23.45"));
		System.out.println(myAtoi("   -23.45"));
		System.out.println(myAtoi("   2345 "));
		System.out.println(myAtoi("123456789876"));
		System.out.println(myAtoi("-123456789876"));
	}

	public static int myAtoi(String str) {
		if (str == null) {
			throw new IllegalArgumentException("Input error");
		}
		// 先去空格
		char[] array = str.trim().toCharArray();
		// 长度为0的特殊处理
		if (array.length == 0) {
			return 0;
		}
		// 正负号标志位
		int sign = 1;
		long sum = 0;
		if (array[0] == '+' || array[0] == '-' || (array[0] >= '0' && array[0] <= '9')) {
			// 操作符置char型的'0'
			if (array[0] == '+') {
				array[0] = '0';
				sign = 1;
			} else if (array[0] == '-') {
				array[0] = '0';
				sign = -1;
			}
			// 循环确定长度
			int length = 0;
			for (int i = 0; i < array.length; i++) {
				if (array[i] >= '0' && array[i] <= '9') {
					length++;
				} else {
					// 遇到非数值退出循环
					break;
				}
			}
			// length值作为退出条件，但同时又要自减，准备一个副本
			int exit = length;
			for (int i = 0; i < exit; i++) {
				// char型的数值，转换成数值型处理
				sum += (array[i] - '0') * Math.pow(10, --length);
			}
			// 超出范围处理
			long num = sum * sign;
			if (num > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			if (num < Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
			return (int) num;
		} else {
			return 0;
		}
	}
}