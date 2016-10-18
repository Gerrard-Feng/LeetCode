package com.gerrard.algorithm.easy;

public class Q021 {

	public static void main(String[] args) {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		list1.add(1);
		list1.add(3);
		list1.add(3);
		list1.add(6);
		list1.add(8);
		list1.add(9);
		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		list2.add(2);
		list2.add(5);
		list2.add(7);
		SinglyLinkedList<Integer> result = method(list1, list2);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}

	private static SinglyLinkedList<Integer> method(SinglyLinkedList<Integer> sortedList1,
			SinglyLinkedList<Integer> sortedList2) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		return list.sort(sortedList1, sortedList2);
	}
}

// 带头结点的单向链表
class SinglyLinkedList<E> {
	// 结点定义
	private static class Node<E> {
		E element;
		Node<E> next;

		Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	private int size = 0;
	Node<E> last;
	Node<E> first;
	Node<E> head = new Node<>(null, null);

	void add(E e) {
		Node<E> newNode = new Node<>(e, null);
		Node<E> preNode = last;
		if (last == null) {
			first = newNode;
			head.next = first;
		} else {
			preNode.next = newNode;
		}
		last = newNode;
		size++;
	}

	int get(int index) {
		checkIndex(index);
		Node<E> currentNode = first;
		for (int i = 0; i < size; i++) {
			if (i < index) {
				currentNode = currentNode.next;
			} else {
				break;
			}
		}
		return (int) currentNode.element;
	}

	private Node<E> getNode(int index) {
		checkIndex(index);
		Node<E> currentNode = first;
		for (int i = 0; i < size; i++) {
			if (i < index) {
				currentNode = currentNode.next;
			} else {
				break;
			}
		}
		return currentNode;
	}

	private void checkIndex(int index) {
		if (index > size - 1 || index < 0) {
			String errorMessage = "size=" + size + ";index=" + index;
			throw new IndexOutOfBoundsException(errorMessage);
		}
	}

	public int size() {
		return size;
	}

	// 对两个已经排序的链表进行排序，不消耗多余的Node空间
	SinglyLinkedList<E> sort(SinglyLinkedList<E> sortedList, SinglyLinkedList<E> tempList) {
		// 以sortedList为基准排序
		Node<E> lastNode = sortedList.head;
		// 开启循环
		for (int i = 0; i < sortedList.size; i++) {
			if (tempList.size == 0) {
				// tempList读完，break
				// 之后的链表其实都是连接着的，已经有序
				break;
			}
			// 比较
			if (sortedList.get(i) < tempList.get(0)) {
				// 不需要动tempList
				lastNode.next = sortedList.getNode(i);
			} else {
				// 先交换last
				tempList.last = sortedList.getNode(sortedList.size - 1);
				sortedList.last = tempList.getNode(tempList.size - 1);
				// 注意断开链表的指针时，如果只有一个指针指向下个结点，一旦断开就找不回来了
				// 重点在于tempList的第一个结点，有head.next，first，2个指针
				tempList.head.next = lastNode.next;
				lastNode.next = tempList.first;
				tempList.first = tempList.head.next;
				// 改变两个链表的size
				int temp = sortedList.size;
				sortedList.size = i + tempList.size;
				tempList.size = temp - i;
			}
			// 更新lastNode
			lastNode = sortedList.getNode(i);
			// 当已排序链表到底了，证明temp里的都比sorted大，把指针连上就结束了
			if (lastNode == sortedList.last) {
				lastNode.next = tempList.first;
				// 改变大小
				sortedList.size += tempList.size;
				// 注意退出
				break;
			}
		}
		return sortedList;
	}
}