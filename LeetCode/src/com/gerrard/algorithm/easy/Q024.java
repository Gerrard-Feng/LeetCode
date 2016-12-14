package com.gerrard.algorithm.easy;

public class Q024 {

	public static void main(String[] args) {

		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		ListNode head = swapPairs(l1);
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}

	// 交换结点
	public static ListNode swapPairs(ListNode head) {
		// 第一个节点会变
		ListNode first = head == null || head.next == null ? head : head.next;
		// 上一个节点
		ListNode last = new ListNode(0);
		while (head != null && head.next != null) {
			// 下一个节点
			ListNode after = head.next;
			// 交换节点
			last.next = after;
			head.next = after.next;
			after.next = head;
			// 下一次交换赋值
			last = head;
			head = head.next;
		}
		return first;
	}

	// 交换结点存放的数据
	public static ListNode swapPairsVal(ListNode head) {
		ListNode first = head;
		while (head != null && head.next != null) {
			// 交换数据，一步到位，但有溢出的风险
			head.val = head.val + head.next.val - (head.next.val = head.val);
			head = head.next.next;
		}
		return first;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}