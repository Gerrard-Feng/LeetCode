package com.gerrard.algorithm.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Q037 {

	public static void main(String[] args) throws SupposeFailException {

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
	public static void solveSudoku(char[][] board) throws SupposeFailException {
		show(board);
		// 记录某个位置的可选字符集合，键值为：第一维*10+第二维
		// System.out.println("穷举法");
		Map<Integer, List<Character>> map = exhaustion(board);
		// show(board);

		
		
		if (map == null || isValidSudoku(map, board) == 1) {
			throw new SupposeFailException();
		}
//		System.out.println(check(map, board));
		
		
		// System.out.println("排除法");
		map = exclusive(map, board);
		// show(board);

//		System.out.println(check(map, board));
		
		// 对于穷举法和排除法不能解决的问题，使用假设法
		int isValid = isValidSudoku(map, board);
		if (isValid == 2) {
			System.out.println("假设法");
			while (isValidSudoku(map, board) != 0) {
				String result = supposeSituation(map, board);
				
//				System.out.println(check(map, board));

				System.out.println(result);
				show(board);
				char[][] copySudoku = suppose(board, result);
				
				
//				System.out.println(check(map, board));
				
				show(copySudoku);
				System.out.println();
				try {
					solveSudoku(copySudoku);
				} catch (SupposeFailException e) {
					System.out.println(result + ":假设失败，回退");
					// 这个假设失败
					int separatorIndex = result.indexOf("#");
					int key = Integer.valueOf(result.substring(0, separatorIndex));
					int cInt = Integer.valueOf(result.substring(separatorIndex + 1));
					Character c = (char) (cInt + '0');
					List<Character> list = map.get(key);
					list.remove(c);
					map.put(key, list);
					// 排除假设之后，只有一个可能性
					if (list.size() == 1) {
						int i = key / 10;
						int j = key % 10;
						board[i][j] = list.get(0);
						map = exhaustion(board);
						// 如果这个假设本身就是基于一个错误的假设
						if (map == null) {
							throw new SupposeFailException();
						}
					}
				}
			}
		} else if (isValid == 0) {
			show(board);
			// 数独解析成功
			return;
		} else {
			// 数独解析失败
			throw new SupposeFailException();
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
							show(board);
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
	private static Map<Integer, List<Character>> exclusive(Map<Integer, List<Character>> map, char[][] board) {
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
					map = exhaustion(board);
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
					map = exhaustion(board);
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
					map = exhaustion(board);
					break;
				}
			}
		}
		return map;
	}

	// 假设法
	private static char[][] suppose(char[][] board, String result) {
		// 将概率最高的数字放入数独中
		int separatorIndex = result.indexOf("#");
		int key = Integer.valueOf(result.substring(0, separatorIndex));
		int cInt = Integer.valueOf(result.substring(separatorIndex + 1));
		char c = (char) (cInt + '0');
		int i = key / 10;
		int j = key % 10;
		// 复制数独的二维数组
		char[][] copySudoku = copyArraySudoku(board);
		copySudoku[i][j] = c;
		return copySudoku;
	}

	// 定位假设法使用的场景
	private static String supposeSituation(Map<Integer, List<Character>> map, char[][] board) {
		// 找到最大可能性的位置+字符
		String result = "";
		double maxProb = 0;
		for (Integer key : map.keySet()) {
			List<Character> list = map.get(key);
			if (list == null) {
				continue;
			}
			int i = key / 10;
			int j = key % 10;
			String charAndProb = calcRelativeProbability(map, i, j);
			int separatorIndex = charAndProb.indexOf("#");
			String probNum = charAndProb.substring(separatorIndex + 1);
			double prob = Double.valueOf(probNum);
			if (prob > maxProb) {
				maxProb = prob;
				result = key + "#" + charAndProb.substring(0, separatorIndex);
			}
		}
		return result;
	}

	// 使用穷举法，找出当前位置可以选择的字符集合
	private static List<Character> chosenCharacters(char[][] board, int i, int j) {
		if (board[i][j] != '.') {
			return null;
		}
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

	// 计算某一格，数字出现的相对概率
	// 如果最大相对概率的对应字符是9，概率是0.4，返回字符串为 9#0.4
	private static String calcRelativeProbability(Map<Integer, List<Character>> map, int i, int j) {
		int key = i * 10 + j;
		List<Character> cList = map.get(key);
		// 最高概率
		double maxProb = 0;
		Character maxCharacter = '0';
		// 开始计算每个字符的相对概率
		// 相对概率 = 九宫格出现的概率 × 行出现的概率 × 列出现的概率
		// 九宫格出现的概率：如果九宫格中有2个格可能出现1，目标格可能的数字为1、2、3，另一个格可能出现的数字为1、4，
		// 那么：目标格中的1在九宫格出现的概率 = 目标格中出现1的概率 × (1 - 另一个格中出现1的概率)，得1/3 × (1-1/2) =
		// 1/6
		// 行出现的概率和列出现的概率与九宫格出现的概率的算法原理相同
		for (Character c : cList) {
			// 1.同行
			int count1 = 0;
			double factor1 = 1;
			for (int k = i * 10; k < i * 10 + 9; k++) {
				List<Character> list1 = map.get(k);
				if (list1 != null && list1.contains(c)) {
					count1++;
					factor1 *= 1 - 1.0 / list1.size();
				}
			}
			double prob1 = 1.0 / count1 * factor1;
			// 2.同列
			int count2 = 0;
			double factor2 = 1;
			for (int k = j; k < j + 90; k += 10) {
				List<Character> list2 = map.get(k);
				if (list2 != null && list2.contains(c)) {
					count2++;
					factor2 *= 1 - 1.0 / list2.size();
				}
			}
			double prob2 = 1.0 / count2 * factor2;
			// 3.同九宫
			int count3 = 0;
			int iStart = 3 * (i / 3);
			int jStart = 3 * (j / 3);
			double factor3 = 1;
			for (int k = iStart; k < iStart + 3; k++) {
				for (int l = jStart; l < jStart + 3; l++) {
					int n = k * 10 + j;
					List<Character> list3 = map.get(n);
					if (list3 != null && list3.contains(c)) {
						count3++;
						factor3 *= 1 - 1.0 / list3.size();
					}
				}
			}
			double prob3 = 1.0 / count3 * factor3;
			// 计算该字符的相对概率
			double probability = prob1 * prob2 * prob3;
			if (probability > maxProb) {
				maxProb = probability;
				maxCharacter = c;
			}
		}
		return maxCharacter + "#" + maxProb;
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
		return 0;
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

	// 复制数独对应的二维数组
	private static char[][] copyArraySudoku(char[][] board) {
		char[][] copy = new char[9][9];
		for (int i = 0; i < 9; i++) {
			char[] child = board[i];
			char[] iChild = Arrays.copyOf(child, 9);
			copy[i] = iChild;
		}
		return copy;
	}

	// 假设失败异常
	private static class SupposeFailException extends Exception {

		private static final long serialVersionUID = 1L;

	}

	private static boolean check(Map<Integer, List<Character>> map, char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char c = board[i][j];
				int key = 10 * i + j;
				List<Character> list = map.get(key);
				if (c != '.') {
					if (list == null) {
						continue;
					} else {
						return false;
					}
				} else {
					List<Character> list2 = chosenCharacters(board, i, j);
					if (list.size() != list2.size()) {
						return false;
					}
					for (int k = 0; k < list.size(); k++) {
						if (!list.get(k).equals(list2.get(k))) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}