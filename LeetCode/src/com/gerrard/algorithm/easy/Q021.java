package com.gerrard.algorithm.easy;

public class Q021 {

	public static void main(String[] args) {

		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(4);
		ListNode l2 = new ListNode(2);
		l2.next = new ListNode(3);
		ListNode first = mergeTwoLists(l1, l2);
		show(first);
	}

	private static void show(ListNode first) {
		StringBuffer output = new StringBuffer();
		while (first != null) {
			output.append(first.val + "->");
			first = first.next;
		}
		System.out.println(output.toString().substring(0, output.toString().length() - 2));
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		// 返回的第一个节点
		ListNode first = l1.val > l2.val ? l2 : l1;
		// 上一个节点
		ListNode last = new ListNode(0);
		while (true) {
			if (l1.val > l2.val) {
				// 交换l1节点和l2节点，保证下一次循环仍然以l1节点为基准
				ListNode swapNode = l2;
				l2 = l1;
				l1 = swapNode;
			}
			// 将last节点的next指针指向l1，同时更新last
			last.next = l1;
			last = l1;
			// 带排序的链表遍历完成，剩余链表自然有序
			if (l1.next == null) {
				l1.next = l2;
				break;
			}
			l1 = l1.next;
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