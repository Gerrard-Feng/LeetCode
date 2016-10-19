package com.gerrard.structure;

/**
 * 带头结点的单向链表
 * 
 * @author Administrator
 *
 * @param <E>
 */
public class SinglyLinkedList<E> {

	/**
	 * 单向链表结点定义
	 * 
	 * @author Administrator
	 *
	 * @param <E>
	 */
	private static class Node<E> {
		// 结点存的数据
		E element;
		// 指向下一个结点的指针
		Node<E> next;

		Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	/** 链表长度 */
	private int size = 0;

	/** 第一个结点和最后一个结点 */
	Node<E> last;
	Node<E> first;

	/** 头结点定义 */
	Node<E> head = new Node<>(null, null);

	/**
	 * 在链表的最后一个结点之后，增加一个包含元素e的结点
	 * 
	 * @param e
	 */
	public void add(E e) {
		// 待增加的结点
		Node<E> newNode = new Node<>(e, null);
		// 上一个结点
		Node<E> preNode = last;
		// 第一次增加结点的情况
		if (last == null) {
			// 将当前结点作为第一个结点
			first = newNode;
			// 头结点指针
			head.next = first;
		} else {
			// 把上一个结点的next指针指向当前结点
			preNode.next = newNode;
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
		// 是第一个结点的情况
		if (index == 0) {
			if (size == 1) {
				// 只有一个结点的情况
				first = null;
				last = null;
				head.next = null;
			} else {
				// 不影响last，但是要重新改变头结点的指针和first的值
				head.next = first.next;
				first = first.next;
			}
		} else {
			// 上一个结点
			Node<E> preNode = getNode(index - 1);
			// 当前结点
			Node<E> currentNode = preNode.next;
			// 是最后一个结点的情况，不用考虑单结点的情况
			if (index == size - 1) {
				preNode.next = null;
				currentNode = null;
			} else {
				// 中间情况，将上一个结点的指针指向下一个结点
				preNode.next = currentNode.next;
				currentNode = null;
			}
		}
		// remove结束之后记得削减size
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
		// 当前结点值，一开始赋值first
		Node<E> currentNode = first;
		for (int i = 0; i < size; i++) {
			if (i < index) {
				// 取到index之前一路next
				currentNode = currentNode.next;
			} else {
				// 取到break
				break;
			}
		}
		return currentNode.element;
	}

	/**
	 * 设置指定index的element为给定值e
	 * 
	 * @param index
	 * @param e
	 */
	public void set(int index, E e) {
		// 先检查index
		checkIndex(index);
		Node<E> node = getNode(index);
		node.element = e;
	}

	/**
	 * @return 链表大小
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
		// 前一个结点，考虑头结点的特殊情况
		Node<E> preNode1;
		if (index1 == 0) {
			preNode1 = head;
		} else {
			preNode1 = getNode(index1 - 1);
		}
		Node<E> preNode2 = getNode(index2 - 1);
		// 当前结点（有preNode的情况下，不要用getNode()方法，影响效率）
		Node<E> node1 = preNode1.next;
		Node<E> node2 = preNode2.next;
		// 考虑first和last的变化，因为后续操作不需要first和last，可以先赋值
		if (index1 == 0) {
			first = node2;
		}
		if (index2 == size - 1) {
			last = node1;
		}
		if (index2 - index1 == 1) {
			// 相邻结点，只需要断3次指针
			node1.next = node2.next;
			preNode1.next = node2;
			node2.next = node1;
		} else {
			// 不相邻结点，需要断4次指针
			Node<E> nextTempNode1 = node1.next;
			// 交换node1
			node1.next = node2.next;
			preNode2.next = node1;
			// 交换node2
			preNode1.next = node2;
			node2.next = nextTempNode1;
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