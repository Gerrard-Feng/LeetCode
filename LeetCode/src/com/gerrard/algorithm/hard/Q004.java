package com.gerrard.algorithm.hard;

import java.util.Arrays;

public class Q004 {

	public static void main(String[] args) {
		int[] array1 = new int[] { 1, 2, 5, 8, 9 };
		int[] array2 = new int[] { 3, 4, 6, 7, 10, 11, 12, 13, 14 };
		System.out.println(method1(array1, array2));
		System.out.println(method2(array1, array2));
	}

	// 这个的时间复杂度是O(m+n)
	private static double method1(int[] array1, int[] array2) {
		final int LENGTH = array1.length + array2.length;
		int[] resultArray = new int[LENGTH];
		// 数组当前位置索引
		int index1 = 0;
		int index2 = 0;
		int[] tempArray = null;
		int tempIndex = 0;
		// 合并数组赋值
		for (int i = 0; i < LENGTH; i++) {
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

	// 不需要重组整个数组，只需要重组一半，时间复杂度是O((m+n)/2)
	private static double method2(int[] array1, int[] array2) {
		final int LENGTH = array1.length + array2.length;
		// 数组当前位置索引
		int index1 = 0;
		int index2 = 0;
		int[] tempArray = null;
		int tempIndex = 0;
		int result = 0;
		// 遍历至一半即可
		for (int i = 0; i < LENGTH / 2 + 1; i++) {
			if (tempArray == null) {
				// 优先判断其中一个数组已经被掏空的情况
				// 特别注意，掏空的时候index会比最后的index+1
				if (index1 == array1.length || index2 == array2.length) {
					// 索引累加
					if (index1 == array1.length) {
						tempArray = array2;
						tempIndex = index2;
						tempIndex++;
					} else {
						tempArray = array1;
						tempIndex = index1;
						tempIndex++;
					}
					// 这个continue是必要的，赋值之后，直接下一次循环
					continue;
				}
				// 两个数组都有剩余的情况
				if (array1[index1] < array2[index2]) {
					// 偶数长度，i=LENGTH/2-1和i=LENGTH/2的平均数
					if (LENGTH % 2 == 0) {
						if (i == LENGTH / 2 - 1 || i == LENGTH / 2) {
							result += array1[index1];
						}
					} else {
						// 奇数长度，i=LENGTH/2
						if (i == LENGTH / 2) {
							result += 2 * array1[index1];
						}
					}
					index1++;
				} else {
					if (LENGTH % 2 == 0) {
						if (i == LENGTH / 2 - 1 || i == LENGTH / 2) {
							result += array2[index2];
						}
					} else {
						// 奇数长度，i=LENGTH/2
						if (i == LENGTH / 2) {
							result += 2 * array2[index2];
						}
					}
					index2++;
				}
			} else {
				// 操作tempArray
				if (LENGTH % 2 == 0) {
					if (i == LENGTH / 2 - 1 || i == LENGTH / 2) {
						result += tempArray[tempIndex];
					}
				} else {
					// 奇数长度，i=LENGTH/2
					if (i == LENGTH / 2) {
						result += 2 * tempArray[tempIndex];
					}
				}
				tempIndex++;
			}
		}
		return result / 2.0;
	}

	// 因为两数组分别有序，用类二分查找法寻找中位数，时间复杂度O(log(m+n))
	private static double method3(int[] array1, int[] array2) {
		// 优先判断，一个数组用完的情况
		if (array1.length == 0 || array2.length == 0) {
			int[] tempArray;
			if (array1.length == 0) {
				tempArray = array2;
			} else {
				tempArray = array2;
			}
			// 直接返回单个有序数组的中位数，奇偶情况可以用一个式子表达
			return (tempArray[tempArray.length / 2] + tempArray[(tempArray.length - 1) / 2]) / 2.0;
		}

		// 两个数组都存在的情况
		int medianIndex1 = array1.length / 2;
		int medianIndex2 = array2.length / 2;

		//
		int[] arrayToReduceFromBegin;
		int[] arrayToReduceToEnd;

		// 操作数组统一赋值
		int[] tempMedianMinArray;
		int[] tempMedianMaxArray;
		// 操作数组中位数索引标志
		int minIndex;
		int maxIndex;
		// 两数组统一赋值处理
		if (array1[medianIndex1] < array2[medianIndex2]) {
			tempMedianMinArray = array1;
			tempMedianMaxArray = array2;
			minIndex = medianIndex1;
			maxIndex = medianIndex2;
		} else {
			tempMedianMinArray = array2;
			tempMedianMaxArray = array1;
			minIndex = medianIndex2;
			maxIndex = medianIndex1;
		}
		// 要去掉的数组
		arrayToReduceFromBegin = Arrays.copyOf(tempMedianMinArray, medianIndex2);
		arrayToReduceToEnd = Arrays.copyOf(tempMedianMaxArray, medianIndex2);
		// 要去掉的数组长度不为0
		if (arrayToReduceFromBegin.length == 0 && arrayToReduceToEnd.length == 0) {
			// 两数组每次减去的数组长度必须相同
			int reduceLength = Math.abs(arrayToReduceFromBegin.length - arrayToReduceToEnd.length);
			int[] nextArray1 = Arrays.copyOfRange(tempMedianMaxArray, reduceLength, tempMedianMaxArray.length);
			int[] nextArray2 = Arrays.copyOf(tempMedianMaxArray, tempMedianMaxArray.length - reduceLength);
		}
		return 0;
	}

}