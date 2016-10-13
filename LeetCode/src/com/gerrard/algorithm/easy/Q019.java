package com.gerrard.algorithm.easy;

public class Q019 {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		method(list, 2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private static void method(LinkedList<Integer> list, int nFromEnd) {
		int index = list.size() - nFromEnd;
		list.remove(index);
	}
}

// 单向链表
class LinkedList<E> {
	// 节点定义
	private static class Node<E> {
		// 节点存的值
		E element;
		// 指向下一个节点的指针
		Node<E> next;

		Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	// 链表长度
	private int size = 0;
	// 第一个节点和最后一个节点
	Node<E> last;
	Node<E> first;

	public void add(E e) {
		// 待增加的节点
		Node<E> newNode = new Node<>(e, null);
		// 上一个节点
		Node<E> preNode = last;
		// 第一次增加节点的情况（不设计头节点的概念）
		if (last == null) {
			// 将当前节点作为第一个节点
			first = newNode;
		} else {
			// 把上一个节点的next指针指向当前节点
			preNode.next = newNode;
		}
		// 更新最后一个节点
		last = newNode;
		size++;
	}

	// 移除指定index的节点
	public void remove(int index) {
		// 忽略入参检查
		// 是第一个节点的情况
		if (index == 0) {
			if (size == 1) {
				// 只有一个节点的情况
				first = null;
				last = null;
			} else {
				// 不影响last
				first = first.next;
			}
		} else {
			// 寻找上一个节点
			Node<E> preNode = null;
			Node<E> tempNode = first;
			// 已经排除是第一个节点的情况，不存在index=0
			for (int i = 0; i < size; i++) {
				if (i == index - 1) {
					preNode = tempNode;
					// 找到就退出
					break;
				} else {
					tempNode = tempNode.next;
				}
			}
			// 当前节点
			Node<E> currentNode = preNode.next;
			// 是最后一个节点的情况，不用考虑单节点的情况
			if (index == size - 1) {
				preNode.next = null;
				currentNode = null;
			} else {
				// 中间情况，将上一个节点的指针指向下一个节点
				preNode.next = currentNode.next;
				currentNode = null;
			}
		}
		// remove结束之后记得削减size
		size--;
	}

	// 获取指定index的Node
	public E get(int index) {
		// 不做入参检查了
		// 一开始赋值first
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

	// 获取链表大小
	public int size() {
		return size;
	}
}