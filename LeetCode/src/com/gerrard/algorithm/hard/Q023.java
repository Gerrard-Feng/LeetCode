package com.gerrard.algorithm.hard;

public class Q023 {

	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null) {
			throw new IllegalArgumentException("Input error");
		}
		if (lists.length == 0) {
			return null;
		}
		return mergeListsArray(lists);
	}

	// 合并 k 个有序链表
	public static ListNode mergeListsArray(ListNode[] lists) {
		// 递归终点
		if (lists.length == 1) {
			return lists[0];
		}
		// 下一次递归的头结点数组
		ListNode[] next = new ListNode[(lists.length + 1) >>> 1];
		// 二路归并
		if (lists.length % 2 == 0) {
			for (int i = 0; i < lists.length; i += 2) {
				next[i >>> 1] = merge2Lists(lists[i], lists[i + 1]);
			}
		} else {
			for (int i = 0; i < lists.length - 1; i += 2) {
				next[i >>> 1] = merge2Lists(lists[i], lists[i + 1]);
			}
			next[next.length - 1] = lists[lists.length - 1];
		}
		return mergeListsArray(next);
	}

	// 合并2个有序链表
	private static ListNode merge2Lists(ListNode l1, ListNode l2) {
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