package com.gerrard.algorithm.easy;

public class Q024 {

	// 交换结点存放的数据
	public static ListNode swapPairs(ListNode head) {
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

		@SuppressWarnings("unused")
		ListNode(int x) {
			val = x;
		}
	}
}