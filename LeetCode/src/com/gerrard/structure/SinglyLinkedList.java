package com.gerrard.structure;

// 带头结点的单向链表
public class SinglyLinkedList<E> {
	// 结点定义
	private static class Node<E> {
		// 结点存的值
		E element;
		// 指向下一个结点的指针
		Node<E> next;

		Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	// 链表长度
	private int size = 0;
	// 第一个结点和最后一个结点
	Node<E> last;
	Node<E> first;
	// 头结点定义
	Node<E> head = new Node<>(null, null);

	// 向链表最后，增加一个包含元素e的结点
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

	// 移除指定index的结点
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
			// 寻找上一个结点
			Node<E> preNode = null;
			Node<E> tempNode = first;
			// 已经排除是第一个结点的情况，不存在index=0
			for (int i = 0; i < size; i++) {
				if (i == index - 1) {
					preNode = tempNode;
					// 找到就退出
					break;
				} else {
					tempNode = tempNode.next;
				}
			}
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

	// 获取指定index的Node存放的element
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

	// 设置指定index的element为给定值
	public void set(int index, E e) {
		checkIndex(index);
		Node<E> node = getNode(index);
		node.element = e;
	}

	// 获取链表大小
	public int size() {
		return size;
	}

	// 交换2个结点
	public void swapNode(int index1, int index2) {
		// 检查入参
		checkIndex(index1);
		checkIndex(index2);

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