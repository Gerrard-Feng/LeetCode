package com.gerrard.algorithm.medium;

public class Q003 {

	public static void main(String[] args) {
		String str = "abbaabc";
		System.out.println(method(str));
	}

	private static String method(String str) {
		int maxLength = 0;
		// 结果集
		StringBuffer result = new StringBuffer();
		StringBuffer temp = new StringBuffer();
		String tempStr;
		for (int i = 0; i < str.length(); i++) {
			tempStr = str.substring(i, i + 1);
			// 不存在重复
			if (!isExist(temp, tempStr)) {
				temp.append(tempStr);
			} else {
				// 遇到重复，比较最大长度
				if (temp.length() > maxLength) {
					// temp超过最大长度，保存结果
					maxLength = temp.length();
					result = temp;
				}
				// 无论超过与否，都要重置temp，temp的值是当前的判断到重复的字符串
				temp = new StringBuffer(tempStr);
				continue;
			}
			// 特殊情况考虑：最长字符串在原字符串尾部
			if (i == str.length() - 1) {
				if (temp.length() > maxLength) {
					// 最后一个，没必要给maxLength赋值了，虽然这个值错了
					result = temp;
				}
			}
		}
		return result.toString();
	}

	// 判断check字符，是否存在于sb字段中
	private static boolean isExist(StringBuffer sb, String check) {
		for (int i = 0; i < sb.length(); i++) {
			if (sb.substring(i, i + 1).equals(check)) {
				// true表示存在重复
				return true;
			}
		}
		return false;
	}
}