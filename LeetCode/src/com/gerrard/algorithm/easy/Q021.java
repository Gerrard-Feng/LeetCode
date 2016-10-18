package com.gerrard.algorithm.easy;

public class Q021 {

	public static void main(String[] args) {
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
		list1.add(1);
		list1.add(3);
		list1.add(4);
		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
		list2.add(2);
		list2.add(5);
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

	public void add(E e) {
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

	public int get(int index) {
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
			} else {
				break;
			}
		}
		return currentNode;
	}

	private void checkIndex(int index) {
		if (index > size - 1 || index < 0) {
			String errorMessage = "size=" + size + ";index=" + "index.";
			throw new IndexOutOfBoundsException(errorMessage);
		}
	}

	public int size() {
		return size;
	}

	// 对两个已经排序的链表进行排序，不消耗多余的Node空间
	public SinglyLinkedList<E> sort(SinglyLinkedList<E> sortedList1, SinglyLinkedList<E> sortedList2) {
		// 以sortedList1为基准排序
		Node<E> lastNode = sortedList1.head;
		// 赋值size
		sortedList1.size = sortedList1.size + sortedList2.size;
		// 断开的链表，初始赋值为链表2
		SinglyLinkedList<E> tempList = sortedList2;
		// 开启循环
		for (int i = 0; i < sortedList1.size; i++) {
			if (tempList.size == 0) {
				// tempList读完，break
				// 之后的链表其实都是连接着的，已经有序
				break;
			}
			// 比较
			if (sortedList1.get(i) < tempList.get(0)) {
				// 不需要动tempList
				lastNode.next = sortedList1.getNode(i);
			} else {
				// 在断开链表的指针之前，优先赋值tempList，只要改变first就行了
				// 如果不优先赋值，后面的Node指针断了，就找不回来了
				tempList.first = lastNode.next;
				lastNode.next = tempList.getNode(0);
			}
		}
		return sortedList1;
	}
}