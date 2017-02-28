package com.gerrard.algorithm.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Q037 {

	public static void main(String[] args) {

		char[][] board = { { '.', '.', '9', '7', '4', '8', '.', '.', '.' },
				{ '7', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '2', '.', '1', '.', '9', '.', '.', '.' },
				{ '.', '.', '7', '.', '.', '.', '2', '4', '.' }, { '.', '6', '4', '.', '1', '.', '5', '9', '.' },
				{ '.', '9', '8', '.', '.', '.', '3', '.', '.' }, { '.', '.', '.', '8', '.', '3', '.', '2', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '6' }, { '.', '.', '.', '2', '7', '5', '9', '.', '.' } };
		solveSudoku(board);
	}

	private static void show(char[][] board) {
		System.out.println("=====================");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 输入是一个能够解决的数独
	public static void solveSudoku(char[][] board) {
		// 记录某个位置的可选字符集合，键值为：第一维*10+第二维
		Map<Integer, List<Character>> map = exhaustion(board);
		System.out.println("穷举法");
		show(board);
		System.out.println("排除法");
		exclusive(map, board);
		show(board);
		Map<Integer, List<Character>> map2 = exhaustion(board);
		System.out.println("排除法");
		exclusive(map2, board);
		show(board);
		
		for (int i = 0; i < 9; i++) {
			List<List<Character>> list = new LinkedList<>();
			for (int j = 0; j < 9; j++) {
				Integer key = 10 * i + j;
				List<Character> cList = map.get(key);
				list.add(cList);
			}
			System.out.println();
		}
	}

	// 穷举法
	private static Map<Integer, List<Character>> exhaustion(char[][] board) {
		// 记录某个位置的可选字符集合，键值为：第一维*10+第二维
		Map<Integer, List<Character>> map = new HashMap<>();
		int remain = 81;
		int last = 0;
		// 先用穷举法，找出所有可能性
		while (true) {
			// 每次进来重置 remain
			remain = 81;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[i][j] == '.') {
						List<Character> cList = chosenCharacters(board, i, j);
						map.put(i * 10 + j, cList);
						if (cList.size() == 1) {
							// 只有一种可能
							board[i][j] = cList.get(0);
							remain--;
						} else if (cList.size() == 0) {
							// 数独有问题
							return null;
						}
					} else {
						// 填写完毕的位置为 null
						map.put(i * 10 + j, null);
						remain--;
					}
				}
			}
			if (last == remain) {
				// 当次遍历，不能够再填写任何数独的数字，退出穷举法的循环
				break;
			}
			last = remain;
		}
		return map;
	}

	// 排除法
	private static void exclusive(Map<Integer, List<Character>> map, char[][] board) {
		for (int key : map.keySet()) {
			List<Character> cList = map.get(key);
			if (cList == null) {
				continue;
			}
			int i = key / 10;
			int j = key % 10;
			for (Character c : cList) {
				// 1.和该位置同行
				// 依次判断这个位置可能出现的数字，在这行其他位置是否可能出现
				// 若都不可能，这个位置的数字就定下了
				int count1 = 0;
				for (int k = i * 10; k < i * 10 + 9; k++) {
					if (k == key) {
						continue;
					}
					List<Character> list1 = map.get(k);
					if (list1 == null || !list1.contains(c)) {
						count1++;
					} else {
						break;
					}
				}
				if (count1 == 8) {
					board[i][j] = c;
					break;
				}
				// 2.和该位置同列
				int count2 = 0;
				for (int k = j; k < j + 90; k += 10) {
					if (k == key) {
						continue;
					}
					List<Character> list2 = map.get(k);
					if (list2 == null || !list2.contains(c)) {
						count2++;
					} else {
						break;
					}
				}
				if (count2 == 8) {
					board[i][j] = c;
					break;
				}
				// 3.和该位置同方格
				int count3 = 0;
				int iStart = 3 * (i / 3);
				int jStart = 3 * (j / 3);
				Label1: for (int k = iStart; k < iStart + 3; k += 10) {
					for (int l = jStart; l < jStart + 3; l++) {
						int index = k * 10 + l;
						if (index == key) {
							continue;
						}
						List<Character> list3 = map.get(index);
						if (list3 == null || !list3.contains(c)) {
							count3++;
						} else {
							break Label1;
						}
					}
				}
				if (count3 == 8) {
					board[i][j] = c;
					break;
				}
			}
		}
	}

	// 假设法
	private static char[][] suppose(Map<Integer, List<Character>> map, char[][] board) {
		for (Integer key : map.keySet()) {
			List<Character> list = map.get(key);
			if (list == null) {
				continue;
			}
			// 找到最小可能性的 List
			if (list.size() == 2) {
				int i = key / 10;
				int j = key % 10;
				int[] result = leastPossiblity(board, i, j);
				if (result[0] != 2) {
					continue;
				}
				char c = list.get(1);
				board[i][j] = c;
				map = exhaustion(board);
				show(board);
				if (isValidSudoku(map, board) == 0) {
					return board;
				} else {
					suppose(map, board);
				}
			}
		}
		return board;
	}

	// 使用穷举法，找出当前位置可以选择的字符集合
	private static List<Character> chosenCharacters(char[][] board, int i, int j) {
		Character[] cArray = new Character[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		List<Character> cList = new LinkedList<>(Arrays.asList(cArray));
		// 处理横向和纵向
		for (int k = 0; k < 9; k++) {
			// 集合不存在该 Character，使用 remove() 方法不会报错
			Character c1 = board[i][k];
			cList.remove(c1);
			Character c2 = board[k][j];
			cList.remove(c2);
		}
		// 定位小方格的位置
		int iStart = 3 * (i / 3);
		int jStart = 3 * (j / 3);
		// 处理小方格
		for (int k = iStart; k < iStart + 3; k++) {
			for (int l = jStart; l < jStart + 3; l++) {
				Character c = board[k][l];
				cList.remove(c);
			}
		}
		return cList;
	}

	// 验证一个填写完毕的数独是否合理
	// 返回值：0-成功；1-失败；2-数独未填写完成
	private static int isValidSudoku(Map<Integer, List<Character>> map, char[][] board) {
		for (Integer key : map.keySet()) {
			List<Character> list = map.get(key);
			if (list != null) {
				return 2;
			}
		}
		int[] line = new int[9];
		int[] row = new int[9];
		int[] cube = new int[9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int check = 1 << (board[i][j] - '0');
					// 与需要校验的三个数组的对应位置，做与运算，如果大于0，表示某个位置上的数字重复了
					if ((line[j] & check) > 0 || (row[i] & check) > 0 || (cube[(i / 3) * 3 + j / 3] & check) > 0) {
						return 1;
					}
					// 做或运算，“累加”次位置的数字，计入下次的校验中
					line[j] |= check;
					row[i] |= check;
					cube[(i / 3) * 3 + j / 3] |= check;
				}
			}
		}
		return 2;
	}

	// 找到某一个未填位置上，与之同行、同列、同方格中，最小的空格数
	// 返回值是个数组，长度为2，第一项是个数，第二项是：1-同行；2-同列；3-同方格
	private static int[] leastPossiblity(char[][] board, int i, int j) {
		int[] result = new int[2];
		int count1 = 0, count2 = 0, count3 = 0;
		for (int k = 0; k < 9; k++) {
			if (board[i][k] == '.') {
				count1++;
			}
		}
		for (int k = 0; k < 9; k++) {
			if (board[k][j] == '.') {
				count2++;
			}
		}
		int iStart = 3 * (i / 3);
		int jStart = 3 * (j / 3);
		for (int k = iStart; k < iStart + 3; k++) {
			for (int l = jStart; l < jStart + 3; l++) {
				if (board[k][l] == '.') {
					count3++;
				}
			}
		}
		if (count1 <= count2 && count1 <= count3) {
			result[0] = count1;
			result[1] = 1;
		} else if (count2 <= count1 && count1 <= count3) {
			result[0] = count2;
			result[1] = 2;
		} else {
			result[0] = count3;
			result[1] = 3;
		}
		return result;
	}
}