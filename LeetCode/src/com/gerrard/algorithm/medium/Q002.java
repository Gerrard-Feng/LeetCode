package com.gerrard.algorithm.medium;

public class Q002 {

	public static void main(String[] args) {

		System.out.println("<==========第一组测试==========>");
		ListNode l1Test1 = new ListNode(2);
		l1Test1.next = new ListNode(4);
		l1Test1.next.next = new ListNode(3);
		ListNode l2Test1 = new ListNode(5);
		l2Test1.next = new ListNode(6);
		l2Test1.next.next = new ListNode(4);
		show(addTwoNumbers(l1Test1, l2Test1));

		System.out.println("<==========第二组测试==========>");
		ListNode l1Test2 = new ListNode(1);
		l1Test2.next = new ListNode(8);
		ListNode l2Test2 = new ListNode(0);
		show(addTwoNumbers(l1Test2, l2Test2));

		System.out.println("<==========第三组测试==========>");
		ListNode l1Test3 = new ListNode(5);
		ListNode l2Test3 = new ListNode(5);
		show(addTwoNumbers(l1Test3, l2Test3));
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// 头结点
		ListNode head = new ListNode(0), operNode = head;
		// 进位标志
		int carry = 0;
		while (l1 != null || l2 != null) {
			int num1 = l1 == null ? 0 : l1.val;
			int num2 = l2 == null ? 0 : l2.val;
			int sum = num1 + num2 + carry;
			operNode.next = new ListNode(sum % 10);
			carry = sum / 10;
			operNode = operNode.next;
			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}
		// 最后一次相加之后判断标志位
		if (carry == 1) {
			operNode.next = new ListNode(1);
		}
		return head.next;
	}

	/**
	 * 单向链表结点
	 * 
	 * @author Administrator
	 *
	 */
	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	private static void show(ListNode node) {
		while (node != null) {
			System.out.print(node.val);
			if (node.next != null) {
				System.out.print("->");
			}
			node = node.next;
		}
		System.out.println();
	}
}