package com.gerrard.algorithm.easy;

public class Q014 {

	public static void main(String[] args) {
		String[] array1 = new String[]{"abcds","abdee","abcww","ab"};
		String[] array2 = new String[]{"abcds","abdee","","ab"};
		String[] array3 = new String[]{"abcds","abdee","d","ab"};
		String[] array4 = new String[]{"abcds","abdee","a","ar"};
		System.out.println(method(array1));
		System.out.println(method(array2));
		System.out.println(method(array3));
		System.out.println(method(array4));
	}

	private static String method(String[] array) {
		// 特殊处理
		if (array == null || array.length == 0) {
			return "";
		} else if (array.length == 1) {
			return array[0];
		} else {
			String currentPrefix = array[0];
			// 从数组第二项开始遍历
			for (int i = 1; i < array.length; i++) {
				// 比较长度取较小者
				int length = Math.min(currentPrefix.length(), array[i].length());
				// 出现长度为0的空字符串，不作处理，直接返回
				if(length==0){
					return "";
				}
				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < length; j++) {
					// 记得用String.equals()方法
					if (currentPrefix.substring(j, j + 1).equals(array[i].substring(j, j + 1))) {
						sb.append(currentPrefix.substring(j, j + 1));
					} else {
						// 遇到不一样，赋值退出
						currentPrefix = sb.toString();
						// 匹配长度为0，直接返回
						if (currentPrefix.length() == 0) {
							return "";
						}
						break;
					}
				}
			}
			return currentPrefix;
		}
	}
}