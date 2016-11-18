package com.gerrard.algorithm.hard;

import java.util.Arrays;

public class Q004 {

	public static void main(String[] args) {
		System.out.println("<==========第一组测试==========>");
		int[] array1Test1 = new int[] { 1, 2, 5, 8, 9 };
		int[] array2Test1 = new int[] { 3, 4, 6, 7, 10, 11, 12, 13, 14 };
		System.out.println(method1(array1Test1, array2Test1));
		System.out.println(method2(array1Test1, array2Test1));
		System.out.println(findMedianSortedArrays(array1Test1, array2Test1));

		System.out.println("<==========第二组测试==========>");
		int[] array1Test2 = new int[] { 1, 3 };
		int[] array2Test2 = new int[] { 2 };
		System.out.println(method1(array1Test2, array2Test2));
		System.out.println(method2(array1Test2, array2Test2));
		System.out.println(findMedianSortedArrays(array1Test2, array2Test2));

		System.out.println("<==========第三组测试==========>");
		int[] array1Test3 = new int[] { 1, 2, 3, 4, 5 };
		int[] array2Test3 = new int[] { 6 };
		System.out.println(method1(array1Test3, array2Test3));
		System.out.println(method2(array1Test3, array2Test3));
		System.out.println(findMedianSortedArrays(array1Test3, array2Test3));

		System.out.println("<==========第四组测试==========>");
		int[] array1Test4 = new int[] { 2 };
		int[] array2Test4 = new int[] {};
		System.out.println(method1(array1Test4, array2Test4));
		System.out.println(method2(array1Test4, array2Test4));
		System.out.println(findMedianSortedArrays(array1Test4, array2Test4));

		System.out.println("<==========第五组测试==========>");
		int[] array1Test5 = new int[] {};
		int[] array2Test5 = new int[] { 5, 7, 9, 10 };
		System.out.println(method1(array1Test5, array2Test5));
		System.out.println(method2(array1Test5, array2Test5));
		System.out.println(findMedianSortedArrays(array1Test5, array2Test5));

		System.out.println("<==========第六组测试==========>");
		int[] array1Test6 = new int[] { 1, 5, 6, 8 };
		int[] array2Test6 = new int[] { 2, 3, 4, 7, 9, 10 };
		System.out.println(method1(array1Test6, array2Test6));
		System.out.println(method2(array1Test6, array2Test6));
		System.out.println(findMedianSortedArrays(array1Test6, array2Test6));

		System.out.println("<==========第七组测试==========>");
		int[] array1Test7 = new int[] { 3, 4 };
		int[] array2Test7 = new int[] { 1, 2, 5, 6 };
		System.out.println(method1(array1Test7, array2Test7));
		System.out.println(method2(array1Test7, array2Test7));
		System.out.println(findMedianSortedArrays(array1Test7, array2Test7));

		System.out.println("<==========第八组测试==========>");
		int[] array1Test8 = new int[] { 1, 1 };
		int[] array2Test8 = new int[] { 1, 2 };
		System.out.println(method1(array1Test8, array2Test8));
		System.out.println(method2(array1Test8, array2Test8));
		System.out.println(findMedianSortedArrays(array1Test8, array2Test8));

		System.out.println("<==========第九组测试==========>");
		int[] array1Test9 = new int[] { 1, 4 };
		int[] array2Test9 = new int[] { 2, 3 };
		System.out.println(method1(array1Test9, array2Test9));
		System.out.println(method2(array1Test9, array2Test9));
		System.out.println(findMedianSortedArrays(array1Test9, array2Test9));

		System.out.println("<==========第十组测试==========>");
		int[] array1Test10 = new int[] { 2, 3 };
		int[] array2Test10 = new int[] { 1 };
		System.out.println(method1(array1Test10, array2Test10));
		System.out.println(method2(array1Test10, array2Test10));
		System.out.println(findMedianSortedArrays(array1Test10, array2Test10));
	}

	// 因为两数组分别有序，用类二分查找法寻找中位数，时间复杂度O(log(m+n))
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		// 入参校验
		if (nums1 == null || nums2 == null || nums1.length + nums2.length == 0) {
			throw new IllegalArgumentException("Input error");
		}
		// 特殊情况：一个数组长度为0
		if (nums1.length == 0 || nums2.length == 0) {
			int[] array = nums1.length == 0 ? nums2 : nums1;
			return (array[array.length / 2] + array[(array.length - 1) / 2]) / 2.0;
		}
		// 中位数
		int median1 = nums1[(nums1.length - 1) / 2];
		int median2 = nums2[(nums2.length - 1) / 2];
		// 特殊情况：两个数组的中位数相同
		if (median1 == median2) {
			// 两数组长度为奇数
			if (nums1.length % 2 == 1 && nums2.length % 2 == 1) {
				return median1;
			}
			// 两数组长度为偶数
			if (nums1.length % 2 == 0 && nums2.length % 2 == 0) {
				return (median1 + Math.min(nums1[nums1.length / 2], nums2[nums2.length / 2])) / 2.0;
			}
			// 一奇一偶，就是偶数长度数组的中位数
			int[] array = nums1.length % 2 == 0 ? nums1 : nums2;
			return (median1 + array[array.length / 2]) / 2.0;
		}
		// 特殊情况：一个数组长度为1
		if (nums1.length == 1 || nums2.length == 1) {
			int[] array1, array2;
			if (nums1.length == 1) {
				array1 = nums1;
				array2 = nums2;
			} else {
				array1 = nums2;
				array2 = nums1;
			}
			// 特殊情况：另一个数组的长度也为1
			if (array2.length == 1) {
				return (array1[0] + array2[0]) / 2.0;
			}
			// 特殊情况：两个数组的中位数计算，包含长度为1的数组的值
			if (array2.length % 2 == 0) {
				if (array1[0] >= array2[(array2.length - 1) / 2] && array1[0] <= array2[array2.length / 2]) {
					return array1[0];
				}
			} else {
				if (array1[0] < array2[array2.length / 2] && array1[0] > array2[array2.length / 2 - 1]) {
					return (array1[0] + array2[array2.length / 2]) / 2.0;
				}
				if (array1[0] > array2[array2.length / 2] && array1[0] < array2[array2.length / 2 + 1]) {
					return (array1[0] + array2[array2.length / 2]) / 2.0;
				}
			}
			// 将array1长度降为0，进入下一次递归
			if (array1[0] > array2[array2.length / 2]) {
				return findMedianSortedArrays(new int[0], Arrays.copyOfRange(array2, 1, array2.length));
			} else {
				return findMedianSortedArrays(new int[0], Arrays.copyOf(array2, array2.length - 1));
			}
		}
		// 中位数大的数组是array1
		int[] array1, array2;
		if (median1 > median2) {
			array1 = nums1;
			array2 = nums2;
		} else {
			array1 = nums2;
			array2 = nums1;
		}
		// 特殊情况：两个数组的中位数，就是array1的中位数
		// 只有在两个偶数长度的数组的情况下，才能实现
		if (array1.length % 2 == 0 && array2.length % 2 == 0) {
			if (array1[array1.length / 2] <= array2[array2.length / 2]) {
				return (array1[(array1.length - 1) / 2] + array1[array1.length / 2]) / 2.0;
			}
		}
		int reduce1 = array1.length / 2;
		int reduce2 = (array2.length - 1) / 2;
		// 特殊情况：array2的长度为2
		if (reduce2 == 0) {
			return findMedianSortedArrays(Arrays.copyOf(array1, array1.length - 1), new int[] { array2[1] });
		}
		int reduce = Math.min(reduce1, reduce2);
		// 削去数组递归
		return findMedianSortedArrays(Arrays.copyOf(array1, array1.length - reduce),
				Arrays.copyOfRange(array2, reduce, array2.length));
	}

	// 将两个数组合并成新数组取中位数，时间复杂度是O(m+n)
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
		// 中位数的索引
		int targetIndex1 = (LENGTH - 1) / 2;
		int targetIndex2 = LENGTH / 2;
		// 结果
		int result = 0;
		boolean flag = false;
		// 数组当前位置索引
		int index1 = 0;
		int index2 = 0;
		// 当前操作数组及索引
		int[] currentArray;
		int currentIndex;
		// 开启循环
		for (int i = 0; i < LENGTH; i++) {
			// 某一数组遍历结束
			if (index1 == array1.length || index2 == array2.length) {
				int[] remain, exhaust;
				if (index1 == array1.length) {
					remain = array2;
					exhaust = array1;
				} else {
					remain = array1;
					exhaust = array2;
				}
				if (!flag) {
					// 中位数在之后，可以直接计算
					return (remain[(LENGTH - 1) / 2 - exhaust.length] + remain[LENGTH / 2 - exhaust.length]) / 2.0;
				} else {
					// 偶数位的总长度，且result已经加过一次
					return (result + remain[i - exhaust.length]) / 2.0;
				}
			}
			// 索引自增，当前操作数组及索引赋值
			if (array1[index1] < array2[index2]) {
				currentArray = array1;
				currentIndex = index1;
				index1++;
			} else {
				currentArray = array2;
				currentIndex = index2;
				index2++;
			}
			// 结果赋值，奇偶长度统一
			if (i == targetIndex1) {
				flag = true;
				result += currentArray[currentIndex];
			}
			if (i == targetIndex2) {
				result += currentArray[currentIndex];
				break;
			}
		}
		return result / 2.0;
	}
}