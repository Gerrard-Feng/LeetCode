package com.gerrard.structure;

/**
 * 带头结点的双向链表
 * 
 * @author Administrator
 *
 * @param <E>
 */
public class DoublyLinkedList<E> {

	/**
	 * 双向链表结点定义
	 * 
	 * @author Administrator
	 *
	 * @param <E>
	 */
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

	/** 链表长度 */
	private int size = 0;

	/** 第一个结点和最后一个结点 */
	Node<E> last;
	Node<E> first;

	/** 头结点定义 */
	Node<E> head = new Node<>(null, null, null);

	/**
	 * 向链表最后，增加一个包含元素e的结点
	 * 
	 * @param e
	 */
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

	/**
	 * 移除指定index的结点
	 * 
	 * @param index
	 */
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

	/**
	 * 获取指定index的Node存放的element
	 * 
	 * @param index
	 * @return
	 */
	public E get(int index) {
		// 先检查index
		checkIndex(index);
		Node<E> currentNode = null;
		if (index < size / 2) {
			// 当前结点值，一开始赋值first
			currentNode = first;
			for (int i = 0; i < size; i++) {
				if (i == index) {
					break;
				} else {
					currentNode = currentNode.next;
				}
			}
		} else {
			// index在靠后的位置，从last向前遍历
			currentNode = last;
			for (int i = 0; i < size; i++) {
				if (i == size - 1 - index) {
					break;
				} else {
					currentNode = currentNode.previous;
				}
			}
		}
		return currentNode.element;
	}

	/**
	 * 设置指定index的element为给定值
	 * 
	 * @param index
	 * @param e
	 */
	public void set(int index, E e) {
		checkIndex(index);
		Node<E> node = getNode(index);
		node.element = e;
	}

	/**
	 * 获取链表大小
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 交换链表两个结点的位置
	 * 
	 * @param index1
	 * @param index2
	 */
	public void swapNode(int index1, int index2) {
		// 先检查index
		checkIndex(index1);
		checkIndex(index2);
		// 无需交换的情况
		if (index1 == index2) {
			return;
		}
		// 保证index1<index2
		if (index1 > index2) {
			int temp = index1;
			index1 = index2;
			index2 = temp;
		}
		// 确定2个结点
		Node<E> node1 = getNode(index1);
		// node2用getNode()方法从头找，浪费效率
		Node<E> node2 = node1;
		for (int i = 0; i < index2 - index1; i++) {
			node2 = node2.next;
		}
		// 考虑first和last的变化，因为后续操作不需要first和last，可以先赋值
		if (index1 == 0) {
			first = node2;
		}
		if (index2 == size - 1) {
			last = node1;
		}
		// 交换结点，相邻和不相邻断的指针个数是不一样的
		if (index2 - index1 == 1) {
			// 重新分配6次指针
			Node<E> preNode = node1.previous;
			Node<E> nextNode = node2.next;
			// next指针
			preNode.next = node2;
			node2.next = node1;
			node1.next = nextNode;
			// previous指针
			if (nextNode != null) {
				// 考虑原来node2是最后一个结点的情况
				nextNode.previous = node1;
			}
			node1.previous = node2;
			node2.previous = preNode;
		} else {
			// 重新分配8次指针
			Node<E> preNode1 = node1.previous;
			Node<E> preNode2 = node2.previous;
			Node<E> nextNode1 = node1.next;
			Node<E> nextNode2 = node2.next;
			// next指针
			preNode1.next = node2;
			node2.next = nextNode1;
			preNode2.next = node1;
			node1.next = nextNode2;
			// previous指针
			nextNode2.previous = node1;
			node2.previous = preNode1;
			nextNode1.previous = node2;
			node1.previous = preNode2;
		}
	}

	// 检查下标，超出下标抛异常
	private void checkIndex(int index) {
		if (index > size - 1 || index < 0) {
			String errorMessage = "size=" + size + ";index=" + index;
			throw new IndexOutOfBoundsException(errorMessage);
		}
	}

	// 获取指定index的结点
	private Node<E> getNode(int index) {
		// 没有必要检查index，因为是private方法，调用的地方会优先检查
		Node<E> currentNode = null;
		if (index < size / 2) {
			currentNode = first;
			for (int i = 0; i < size; i++) {
				if (i == index) {
					break;
				} else {
					currentNode = currentNode.next;
				}
			}
		} else {
			currentNode = last;
			for (int i = 0; i < size; i++) {
				if (i == size - 1 - index) {
					break;
				} else {
					currentNode = currentNode.previous;
				}
			}
		}
		return currentNode;
	}
}