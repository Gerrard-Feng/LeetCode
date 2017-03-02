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
				{ '.', '.', '7', '.', '.', '.', '.', '2', '4' }, { '.', '6', '4', '.', '1', '.', '5', '9', '.' },
				{ '.', '9', '8', '.', '.', '.', '3', '.', '.' }, { '.', '.', '.', '8', '.', '3', '.', '2', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '6' }, { '.', '.', '.', '2', '7', '5', '9', '.', '.' } };
		show(board);
		solveSudoku(board);
		show(board);
	}

	private static void show(char[][] board) {
		if (board == null) {
			return;
		}
		System.out.println("=================");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("=================");
	}

	// 输入是一个能够解决的数独
	public static void solveSudoku(char[][] board) {
		Map<String, Object> resultMap = sudoku(board);
		char[][] result = (char[][]) resultMap.get("Sudoku");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = result[i][j];
			}
		}
	}

	// 需要递归的方法，返回判断结果和数组
	private static Map<String, Object> sudoku(char[][] board) {
		// 先用穷举法
		Map<Integer, List<Character>> map = exhaustion(board);
		// 穷举法过程中发现错误，数独存在问题，无解
		if (map == null) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("Status", false);
			return resultMap;
		}
		// 再用排除法
		map = exclusive(map, board);
		if (isValidSudoku(map, board) == 2) {
			// 必须使用假设法的场景
			String result = supposeSituation(map, board);
			int separatorIndex = result.indexOf("#");
			int key = Integer.valueOf(result.substring(0, separatorIndex));
			int i = key / 10;
			int j = key % 10;
			// 选出相对概率最高的假设新数组，递归解决这个新数组
			char[][] copySudoku = suppose(board, result);
			Map<String, Object> rMap = sudoku(copySudoku);
			if ((boolean) rMap.get("Status")) {
				rMap.put("Status", true);
				rMap.put("Sudoku", rMap.get("Sudoku"));
				return rMap;
			} else {
				// 假设失败，回退
				int cInt = Integer.valueOf(result.substring(separatorIndex + 1));
				Character c = (char) (cInt + '0');
				copySudoku = copyArraySudoku(board);
				map = exhaustion(copySudoku);
				// 去除这种可能性，因为这种可能性衍生出来的变化也要删去（这很重要）
				List<Character> list = map.get(key);
				list.remove(c);
				map.put(key, list);
				if (list.size() == 0) {
					Map<String, Object> resultMap = new HashMap<>();
					resultMap.put("Status", false);
					return resultMap;
				} else if (list.size() == 1) {
					copySudoku[i][j] = list.get(0);
					Map<String, Object> resultMap = sudoku(copySudoku);
					if ((boolean) resultMap.get("Status")) {
						resultMap.put("Status", true);
						resultMap.put("Sudoku", resultMap.get("Sudoku"));
						return resultMap;
					} else {
						resultMap.put("Status", false);
						return resultMap;
					}
				} else {
					// 选择这个位置概率最高的的其他假设
					String probRemain = calcRelativeProbability(map, i, j);
					Character cRemain = probRemain.substring(0, probRemain.indexOf("#")).toCharArray()[0];
					copySudoku[i][j] = cRemain;
					// 其他假设的结果
					Map<String, Object> resultMap = sudoku(copySudoku);
					if ((boolean) resultMap.get("Status")) {
						resultMap.put("Status", true);
						resultMap.put("Sudoku", resultMap.get("Sudoku"));
						return resultMap;
					} else {
						resultMap.put("Status", false);
						return resultMap;
					}
				}
			}
		}
		// 不需要假设法直接成功
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("Status", true);
		resultMap.put("Sudoku", board);
		return resultMap;
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
	private static Map<Integer, List<Character>> exclusive(Map<Integer, List<Character>> map, char[][] board) {
		for (Integer key : map.keySet()) {
			// 内部的穷举法可能导致 map 变为 null
			if (map == null) {
				return null;
			}
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
					// 每次能够定位一个数字之后，重新调用穷举法，并更新对应的 Map
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
				// 3.和该位置同小九宫
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
	// 如果位置是2排3列，假设的字符是6，返回字符串为12#6
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
			// 计算相对概率，并解析返回字符串
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
			// 集合不存在该 Character，使用 remove() 方法不作任何处理
			// 输入需要指定 Character，不能触发自动装箱
			Character c1 = board[i][k];
			cList.remove(c1);
			Character c2 = board[k][j];
			cList.remove(c2);
		}
		// 定位小九宫的位置
		int iStart = 3 * (i / 3);
		int jStart = 3 * (j / 3);
		// 处理小九宫
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
		// 集合只有一个时，直接返回
		if (cList.size() == 1) {
			return cList.get(0) + "#" + 1;
		}
		// 最高概率
		double maxProb = 0;
		Character maxCharacter = '0';
		// 开始计算每个字符的相对概率
		// 相对概率 = 九宫格出现的概率×行出现的概率×列出现的概率
		// 九宫格出现的概率：如果九宫格中有2个格可能出现1，目标格可能的数字为1、2、3，另一个格可能出现的数字为1、4，
		// 那么：目标格中的1在九宫格出现的概率 = 目标格中出现1的概率 ×(1-另一个格中出现1的概率)，得1/3×(1-1/2)=1/6
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
			// 3.同小九宫
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
		int[] row = new int[9];
		int[] column = new int[9];
		int[] box = new int[9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int check = 1 << (board[i][j] - '0');
					// 与需要校验的三个数组的对应位置，做与运算，如果大于0，表示某个位置上的数字重复了
					if ((row[j] & check) > 0 || (column[i] & check) > 0 || (box[(i / 3) * 3 + j / 3] & check) > 0) {
						return 1;
					}
					// 做或运算，“累加”次位置的数字，计入下次的校验中
					row[j] |= check;
					column[i] |= check;
					box[(i / 3) * 3 + j / 3] |= check;
				}
			}
		}
		return 0;
	}

	// 复制数独对应的二维数组
	// API 自带的方法不能深度复制二维数组
	private static char[][] copyArraySudoku(char[][] board) {
		char[][] copy = new char[9][9];
		for (int i = 0; i < 9; i++) {
			char[] child = board[i];
			char[] iChild = Arrays.copyOf(child, 9);
			copy[i] = Arrays.copyOf(iChild, 9);
		}
		return copy;
	}

	// 用于检测数独对应的，二维数组与 Map 的匹配关系
	public static boolean check(Map<Integer, List<Character>> map, char[][] board) {
		if (map == null) {
			return false;
		}
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