package com.gerrard.algorithm.easy;

import com.gerrard.structure.SinglyLinkedList;

public class Q019 {

	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		method(list, 2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private static void method(SinglyLinkedList<Integer> list, int nFromEnd) {
		int index = list.size() - nFromEnd;
		list.removeIndexOf(index);
	}
}