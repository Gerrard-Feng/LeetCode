package com.gerrard.algorithm.hard;

public class Q025 {

	public static void main(String[] args) {

		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		ListNode check = reverseKGroup(l1, 3);
		while (check != null) {
			System.out.print(check.val + " ");
			check = check.next;
		}
	}

	// 交换节点
	public static ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k < 2) {
			return head;
		}
		// 获取链表的长度
		ListNode check = head;
		int size = 0;
		while (check != null) {
			size++;
			check = check.next;
		}
		// 确定返回的头节点
		ListNode first = head;
		if (k <= size) {
			for (int i = 1; i < k; i++) {
				first = first.next;
			}
		}
		// 当前节点和上一个节点
		ListNode current = head;
		ListNode last = new ListNode(0);
		ListNode[] reverse = new ListNode[k];
		while (size > k - 1) {
			for (int i = 0; i < k; i++) {
				reverse[i] = current;
				current = current.next;
			}
			// 倒序赋值
			last.next = reverse[k - 1];
			for (int i = k - 1; i > 0; i--) {
				reverse[i].next = reverse[i - 1];
			}
			reverse[0].next = current;
			// 更新上一个节点
			last = reverse[0];
			size -= k;
		}
		return first;
	}

	// 交换数据
	public static ListNode reverseKGroupVal(ListNode head, int k) {
		if (head == null || k < 2) {
			return head;
		}
		// 获取链表的长度
		ListNode check = head;
		int size = 0;
		while (check != null) {
			size++;
			check = check.next;
		}
		ListNode current = head;
		ListNode[] reverse = new ListNode[k];
		while (size > k - 1) {
			// 倒序的节点数组，同时将 current 指向下一次倒序的节点开始位置
			for (int i = 0; i < k; i++) {
				reverse[i] = current;
				current = current.next;
			}
			// 交换至一半结束
			for (int i = 0; i < (k >>> 1); i++) {
				reverse[i].val = reverse[i].val + reverse[k - 1 - i].val - (reverse[k - 1 - i].val = reverse[i].val);
			}
			size -= k;
		}
		return head;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}