package com.gerrard.structure;

// 带头结点的双向链表
public class DoublyLinkedList<E> {
	// 结点定义
	private static class Node<E> {
		// 结点存的值
		E element;
		// 指向下一个结点的指针
		Node<E> next;
		// 指向上一个结点的指针
		Node<E> previous;

		Node(E element, Node<E> previous, Node<E> next) {
			this.element = element;
			this.previous = previous;
			this.next = next;
		}
	}

	// 链表长度
	private int size = 0;
	// 第一个结点和最后一个结点
	Node<E> last;
	Node<E> first;
	// 头结点定义
	Node<E> head = new Node<>(null, null, null);

	// 向链表最后，增加一个包含元素e的结点
	public void add(E e) {
		// 新结点
		Node<E> newNode = new Node<>(e, null, null);
		// 第一次增加结点的情况
		if (size == 0) {
			head.next = newNode;
			newNode.previous = head;
			first = newNode;

		} else {
			last.next = newNode;
			newNode.previous = last;
		}
		// 更新最后一个结点
		last = newNode;
		size++;
	}

	// 移除指定index的结点
	public void removeIndexOf(int index) {

	}

	// 获取指定index的Node存放的element
	public E get(int index) {
		// 先检查index
		checkIndex(index);
		// 当前结点值，一开始赋值first
		Node<E> currentNode = first;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				break;
			} else {
				currentNode = currentNode.next;
			}
		}
		return currentNode.element;
	}

	// 获取链表大小
	public int size() {
		return size;
	}

	// 检查下标，超出下标抛异常
	private void checkIndex(int index) {
		if (index > size - 1 || index < 0) {
			String errorMessage = "size=" + size + ";index=" + "index.";
			throw new IndexOutOfBoundsException(errorMessage);
		}
	}
}