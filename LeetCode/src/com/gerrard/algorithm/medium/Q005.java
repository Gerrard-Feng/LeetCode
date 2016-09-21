package com.gerrard.algorithm.medium;

public class Q005 {

	public static void main(String[] args) {

		String str = "cabcba";
		int startIndex = method(str)[0];
		int length = method(str)[1];
		System.out.println(str.substring(startIndex, startIndex + length));
	}

	// 返回一个长度为2的数组，第一位是startIndex，第二位是length
	private static int[] method(String str) {
		// str.split("")方法生成的字符串，长度+1，第一个是空字符串
		char[] array = str.toCharArray();
		int maxLength = 0;
		int startIndex = 0;
		// 循环中使用，先声明
		int count1;
		int count2;
		for (int i = 0; i < array.length; i++) {
			count1 = singleCore(i, array);
			count2 = doubleCore(i, array);
			// 存在超过最大长度的情况
			if (count1 > maxLength || count2 > maxLength) {
				// 不存在相等情况
				if (count1 > count2) {
					// 单核
					maxLength = count1;
					startIndex = i - (count1 - 1) / 2;
				} else {
					// 双核
					maxLength = count2;
					startIndex = i + 1 - (count2 / 2);
				}
			}
		}
		// 返回结果
		int[] result = new int[2];
		result[0] = startIndex;
		result[1] = maxLength;
		return result;
	}

	// 单核处理
	private static int singleCore(int index, char[] array) {
		// 长度计数器
		int count = 1;
		// 扩展次数
		int extendTime = 0;
		// 直到外扩超过数组范围，一直循环
		while (index - extendTime > 0 && index + extendTime < array.length - 1) {
			// 不对称，直接跳出
			if (array[index - extendTime] != array[index + extendTime]) {
				break;
			}
			extendTime++;
			count += 2;
		}
		return count;
	}

	// 双核处理，没有必要考虑左和右的问题，因为这一次的右就是下一次的左
	private static int doubleCore(int index, char[] array) {
		// 返回长度
		int count = 0;
		// 右双核的情况，最后一位排除
		if (index != array.length - 1) {
			int extendTime = 0;
			while (index - extendTime > 0 && index + extendTime < array.length - 1) {
				if (array[index - extendTime] != array[index + 1 + extendTime]) {
					break;
				}
				extendTime++;
				count += 2;
			}
		}
		return count;
	}
}