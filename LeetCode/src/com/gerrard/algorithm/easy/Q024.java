package com.gerrard.algorithm.easy;

import com.gerrard.structure.DoublyLinkedList;

public class Q024 {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		method(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private static void method(DoublyLinkedList<Integer> list) {
		for (int i = 0; i < list.size() - 1; i += 2) {
			Integer i1 = list.get(i);
			Integer i2 = list.get(i + 1);
			list.set(i, i2);
			list.set(i + 1, i1);
		}
	}
}