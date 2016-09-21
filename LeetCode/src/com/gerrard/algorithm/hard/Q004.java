package com.gerrard.algorithm.hard;

public class Q004 {

	public static void main(String[] args) {
		int[] array1 = new int[] { 1, 2 };
		int[] array2 = new int[] { 3, 4 };
		System.out.println(method(array1, array2));
	}

	private static double method(int[] array1, int[] array2) {
		final int LENGTH = array1.length + array2.length;
		int[] resultArray = new int[LENGTH];
		// 数组当前位置索引
		int index1 = 0;
		int index2 = 0;
		int[] tempArray = null;
		int tempIndex = 0;
		// 合并数组赋值
		for (int i = 0; i < array1.length + array2.length; i++) {
			if (tempArray == null) {
				// 优先判断其中一个数组已经被掏空的情况
				// 特别注意，掏空的时候index会比最后的index+1
				if (index1 == array1.length || index2 == array2.length) {
					// temp赋值+本次赋值
					if (index1 == array1.length) {
						tempArray = array2;
						tempIndex = index2;
						resultArray[i] = tempArray[tempIndex++];
					} else {
						tempArray = array1;
						tempIndex = index1;
						resultArray[i] = tempArray[tempIndex++];
					}
					// 这个continue是必要的，赋值之后，直接下一次循环
					continue;
				}
				// 两个数组都有剩余的情况
				if (array1[index1] < array2[index2]) {
					resultArray[i] = array1[index1++];
				} else {
					resultArray[i] = array2[index2++];
				}
			} else {
				// 操作tempArray
				resultArray[i] = tempArray[tempIndex++];
			}
		}
		// 分奇偶确定中位数，除数要特别标注2.0，不然以整数规则计算
		if (LENGTH % 2 == 0) {
			return (resultArray[LENGTH / 2] + resultArray[LENGTH / 2 - 1]) / 2.0;
		}
		return resultArray[(LENGTH - 1) / 2];
	}
}