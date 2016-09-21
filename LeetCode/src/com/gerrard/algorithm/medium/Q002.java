package com.gerrard.algorithm.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Q002 {

	public static void main(String[] args) {

		List<Integer> list1 = new LinkedList<>();
		list1.add(2);
		list1.add(4);
		list1.add(3);
		List<Integer> list2 = new LinkedList<>();
		list2.add(5);
		list2.add(6);
		list2.add(4);
		List<Integer> list = method(list1, list2);
		for (int a : list) {
			System.out.print(a + " ");
		}
	}

	private static List<Integer> method(List<Integer> list1, List<Integer> list2) {
		int sum1 = 0;
		int sum2 = 0;
		int size1 = list1.size();
		int size2 = list2.size();
		// 声明2个ListIterator，可以逆向迭代，指针位置先给到最后一位
		ListIterator<Integer> iter1 = list1.listIterator(size1);
		ListIterator<Integer> iter2 = list2.listIterator(size2);
		// 逆序遍历+累加
		while (iter1.hasPrevious()) {
			sum1 += iter1.previous() * Math.pow(10, --size1);
		}
		while (iter2.hasPrevious()) {
			sum2 += iter2.previous() * Math.pow(10, --size2);
		}
		int sum = sum1 + sum2;
		List<Integer> resultList = new LinkedList<>();
		while (sum > 0) {
			// 取末尾
			resultList.add(sum % 10);
			// 去末尾
			sum /= 10;
		}
		return resultList;
	}
}