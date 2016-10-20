package com.gerrard.algorithm.easy;

public class Q019 {

	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
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

	private static void method(SinglyLinkedList<Integer> list, int nFromEnd) {
		int index = list.size() - nFromEnd;
		list.removeIndexOf(index);
	}

	private static class SinglyLinkedList<E> {

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

		public int size() {
			return size;
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
}