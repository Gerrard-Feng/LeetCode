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
		checkIndex(index);
		// 最后一项的特殊情况
		if (index == size - 1) {
			last = last.previous;
			last.next = null;
		} else {
			// 正常情况
			Node<E> currentNode = getNode(index);
			currentNode.previous.next = currentNode.next;
			currentNode.next.previous = currentNode.previous;
			// 第一项的情况，会多改变一个first的值
			if (index == 0) {
				first = currentNode.next;
			}
			currentNode = null;
		}
		size--;
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

	// 获取指定index的结点
	private Node<E> getNode(int index) {
		// 没有必要检查index，因为是private方法，调用的地方会优先检查
		Node<E> currentNode = first;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				break;
			} else {
				currentNode = currentNode.next;
			}
		}
		return currentNode;
	}
}