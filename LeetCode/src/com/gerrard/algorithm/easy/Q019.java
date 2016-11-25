package com.gerrard.algorithm.easy;

public class Q019 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		ListNode head1 = removeNthFromEnd(create(), 1);
		show(head1);

		System.out.println("<==========第二组测试==========>");
		ListNode head2 = removeNthFromEnd(create(), 5);
		show(head2);

		System.out.println("<==========第三组测试==========>");
		ListNode head3 = removeNthFromEnd(create(), 1);
		show(head3);
	}

	private static ListNode create() {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		return l1;
	}

	private static void show(ListNode node) {
		while (node != null) {
			System.out.print(node.val);
			if (node.next != null) {
				System.out.print("->");
			}
			node = node.next;
		}
		System.out.println("\n");
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null) {
			throw new IllegalArgumentException("Input error");
		}
		ListNode current = head;
		ListNode[] array = new ListNode[] {};
		int length = 1;
		while (current != null) {
			array = extendArray(array, current);
			current = current.next;
			length++;
		}
		if (n > length) {
			throw new IllegalArgumentException("Input error");
		}
		// 记录删除节点，以及上一个节点
		int index = length - n;
		int lastIndex = length - n - 1;
		// 删除第一个节点的情况
		if (lastIndex == 0) {
			head = head.next;
		} else {
			ListNode deleteNode = array[index - 1];
			ListNode lastNode = array[lastIndex - 1];
			lastNode.next = deleteNode.next;
			deleteNode = null;
		}
		return head;
	}

	private static ListNode[] extendArray(ListNode[] array, ListNode node) {
		ListNode[] listArray = new ListNode[array.length + 1];
		System.arraycopy(array, 0, listArray, 0, array.length);
		listArray[array.length] = node;
		return listArray;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}