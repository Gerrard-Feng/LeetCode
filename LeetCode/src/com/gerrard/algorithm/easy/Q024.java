package com.gerrard.algorithm.easy;

import com.gerrard.structure.DoublyLinkedList;
import com.gerrard.structure.SinglyLinkedList;

public class Q024 {

	public static void main(String[] args) {
		System.out.println("<==========第一组测试==========>");
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		list1.add(5);
		method_1(list1);
		for (int i = 0; i < list1.size(); i++) {
			System.out.print(list1.get(i));
			if (i != list1.size() - 1) {
				System.out.print(" -> ");
			}
		}
		System.out.println();
		System.out.println("<==========第二组测试==========>");
		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		list2.add(1);
		list2.add(2);
		list2.add(3);
		list2.add(4);
		method_2(list2);
		for (int i = 0; i < list2.size(); i++) {
			System.out.print(list2.get(i));
			if (i != list2.size() - 1) {
				System.out.print(" -> ");
			}
		}
		System.out.println();
		System.out.println("<==========第三组测试==========>");
		DoublyLinkedList<Integer> list3 = new DoublyLinkedList<>();
		list3.add(3);
		list3.add(4);
		list3.add(5);
		list3.add(6);
		list3.add(7);
		list3.add(8);
		method_3(list3);
		for (int i = 0; i < list3.size(); i++) {
			System.out.print(list3.get(i));
			if (i != list3.size() - 1) {
				System.out.print(" -> ");
			}
		}
		System.out.println();
	}

	// 交换结点存放的数据
	private static void method_1(DoublyLinkedList<Integer> list) {
		for (int i = 0; i < list.size() - 1; i += 2) {
			Integer i1 = list.get(i);
			Integer i2 = list.get(i + 1);
			list.set(i, i2);
			list.set(i + 1, i1);
		}
	}

	// 交换单向链表的结点
	private static void method_2(SinglyLinkedList<Integer> list) {
		for (int i = 0; i < list.size() - 1; i += 2) {
			list.swapNode(i, i + 1);
		}
	}

	// 交换双向链表的结点
	private static void method_3(DoublyLinkedList<Integer> list) {
		for (int i = 0; i < list.size() - 1; i += 2) {
			list.swapNode(i, i + 1);
		}
	}
}