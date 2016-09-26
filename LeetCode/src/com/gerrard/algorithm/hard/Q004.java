package com.gerrard.algorithm.hard;

import java.util.Arrays;

public class Q004 {

	public static void main(String[] args) {
		System.out.println("<==========第一组测试==========>");
		int[] array1Test1 = new int[] { 1, 2, 5, 8, 9 };
		int[] array2Test1 = new int[] { 3, 4, 6, 7, 10, 11, 12, 13, 14 };
		System.out.println(method1(array1Test1, array2Test1));
		System.out.println(method2(array1Test1, array2Test1));
		System.out.println(method3(array1Test1, array2Test1));

		System.out.println("<==========第二组测试==========>");
		int[] array1Test2 = new int[] { 1 };
		int[] array2Test2 = new int[] { 3 };
		System.out.println(method1(array1Test2, array2Test2));
		System.out.println(method2(array1Test2, array2Test2));
		System.out.println(method3(array1Test2, array2Test2));

		System.out.println("<==========第三组测试==========>");
		int[] array1Test3 = new int[] { 1, 2, 3, 4, 5 };
		int[] array2Test3 = new int[] { 6 };
		System.out.println(method1(array1Test3, array2Test3));
		System.out.println(method2(array1Test3, array2Test3));
		System.out.println(method3(array1Test3, array2Test3));

		System.out.println("<==========第四组测试==========>");
		int[] array1Test4 = new int[] { 1, 2, 3, 4, 5 };
		int[] array2Test4 = new int[] { 3 };
		System.out.println(method1(array1Test4, array2Test4));
		System.out.println(method2(array1Test4, array2Test4));
		System.out.println(method3(array1Test4, array2Test4));

		System.out.println("<==========第五组测试==========>");
		int[] array1Test5 = new int[] {};
		int[] array2Test5 = new int[] { 5, 7, 9, 10 };
		System.out.println(method1(array1Test5, array2Test5));
		System.out.println(method2(array1Test5, array2Test5));
		System.out.println(method3(array1Test5, array2Test5));

		System.out.println("<==========第六组测试==========>");
		int[] array1Test6 = new int[] { 8 };
		int[] array2Test6 = new int[] { 6, 7, 9 };
		System.out.println(method1(array1Test6, array2Test6));
		System.out.println(method2(array1Test6, array2Test6));
		System.out.println(method3(array1Test6, array2Test6));

		System.out.println("<==========第七组测试==========>");
		int[] array1Test7 = new int[] { 7 };
		int[] array2Test7 = new int[] { 6, 9, 10 };
		System.out.println(method1(array1Test7, array2Test7));
		System.out.println(method2(array1Test7, array2Test7));
		System.out.println(method3(array1Test7, array2Test7));

		System.out.println("<==========第八组测试==========>");
		int[] array1Test8 = new int[] { 1, 2, 2 };
		int[] array2Test8 = new int[] { 3, 4, 5 };
		System.out.println(method1(array1Test8, array2Test8));
		System.out.println(method2(array1Test8, array2Test8));
		System.out.println(method3(array1Test8, array2Test8));
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
					} else {
						tempArray = array1;
						tempIndex = index1;
					}
					// 当一个数组用完，index这正好处于中位数运算时，需要累加当前值
					if (LENGTH % 2 == 0) {
						if (i == LENGTH / 2 - 1 || i == LENGTH / 2) {
							result += tempArray[tempIndex];
						}
					} else {
						if (i == LENGTH / 2) {
							result += 2 * array2[index2];
						}
					}
					tempIndex++;
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
		int medianIndex1 = (array1.length - 1) / 2;
		int medianIndex2 = (array2.length - 1) / 2;
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
		// 要去掉的数组赋值
		int[] arrayToReduceFromBegin = Arrays.copyOf(tempMedianMinArray, minIndex);
		int[] arrayToReduceToEnd = Arrays.copyOfRange(tempMedianMaxArray, tempMedianMaxArray.length - maxIndex,
				tempMedianMaxArray.length);
		// 要去掉的数组长度不为0
		if (!(arrayToReduceFromBegin.length == 0 || arrayToReduceToEnd.length == 0)) {
			// 两数组每次减去的数组长度必须相同
			int reduceLength = Math.min(arrayToReduceFromBegin.length, arrayToReduceToEnd.length);
			int[] nextArray1 = Arrays.copyOfRange(tempMedianMinArray, reduceLength, tempMedianMinArray.length);
			int[] nextArray2 = Arrays.copyOf(tempMedianMaxArray, tempMedianMaxArray.length - reduceLength);
			return method3(nextArray1, nextArray2);
		} else {
			// 存在2种情况：情况1.存在长度为1的数组；情况2.存在长度为2且中位数比较较小的那一方
			// 先考虑情况2，包括部分情况1也适用
			if (tempMedianMinArray.length == 2) {
				// 小数组去最前的1位，大数组去最后的1位
				int[] nextArray1 = Arrays.copyOfRange(tempMedianMinArray, 1, 2);
				int[] nextArray2 = Arrays.copyOf(tempMedianMaxArray, tempMedianMaxArray.length - 1);
				return method3(nextArray1, nextArray2);
			} else {
				// 情况1
				// 两数组长度都为1，特殊考虑
				if (tempMedianMinArray.length == 1 && tempMedianMaxArray.length == 1) {
					return (tempMedianMinArray[0] + tempMedianMaxArray[0]) / 2.0;
				}
				// 统一操作赋值
				int[] tempArray1;
				int[] tempArray2;
				if (tempMedianMinArray.length == 1) {
					tempArray1 = tempMedianMinArray;
					tempArray2 = tempMedianMaxArray;
				} else {
					tempArray1 = tempMedianMaxArray;
					tempArray2 = tempMedianMinArray;
				}
				// 之后有4中情况
				if (tempArray1[0] < tempArray2[tempArray2.length / 2]) {
					if (tempArray1[0] < tempArray2[0]) {
						// 形如[1]和[6,7,8]
						tempArray1 = new int[] {};
						// 去尾
						tempArray2 = Arrays.copyOf(tempArray2, tempArray2.length - 1);
					} else {
						// 形如[7]和[6,8,9]
						tempArray2 = Arrays.copyOfRange(tempArray2, 1, tempArray2.length - 1);
					}
				} else {
					if (tempArray1[0] > tempArray2[tempArray2.length - 1]) {
						// 形如[9]和[6,7,8]
						tempArray1 = new int[] {};
						// 去头
						tempArray2 = Arrays.copyOfRange(tempArray2, 1, tempArray2.length);
					} else {
						// 形如[8]和[6,7,9]
						tempArray2 = Arrays.copyOfRange(tempArray2, 1, tempArray2.length - 1);
					}
				}
				return method3(tempArray1, tempArray2);
			}
		}
	}
}