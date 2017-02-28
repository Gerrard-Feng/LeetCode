package com.gerrard.algorithm.medium;

public class Q036 {

	public static void main(String[] args) {

		// 以下这个例子，显然不是一个合理的数独，但是返回应该是 true，因为只考虑已经填充的数字
		System.out.println("<==========第一组测试==========>");
		char[][] board1 = { { '.', '8', '7', '6', '5', '4', '3', '2', '1' },
				{ '2', '.', '.', '.', '.', '.', '.', '.', '.' }, { '3', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '4', '.', '.', '.', '.', '.', '.', '.', '.' }, { '5', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '6', '.', '.', '.', '.', '.', '.', '.', '.' }, { '7', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '8', '.', '.', '.', '.', '.', '.', '.', '.' }, { '9', '.', '.', '.', '.', '.', '.', '.', '.' } };
		System.out.println(isValidSudoku(board1));

		System.out.println("<==========第二组测试==========>");
		char[][] board2 = { { '.', '.', '4', '0', '.', '.', '6', '3', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '5', '.', '.', '.', '.', '.', '.', '9', '.' },
				{ '.', '.', '.', '5', '6', '.', '.', '.', '.' }, { '4', '.', '3', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '7', '.', '.', '.', '.', '.' }, { '.', '.', '.', '5', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
		System.out.println(isValidSudoku(board2));
	}

	// 只需要验证，已经填充的数字是否符合数独条件
	// 即横向、纵向、小方格三个地方满足没有重复元素
	public static boolean isValidSudoku(char[][] board) {
		// 数独的每一个数字都要校验3处
		int[] line = new int[9];
		int[] row = new int[9];
		int[] cube = new int[9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					// 左移此处数字的位数
					int check = 1 << (board[i][j] - '0');
					// 与需要校验的三个数组的对应位置，做与运算，如果大于0，表示某个位置上的数字重复了
					if ((line[j] & check) > 0 || (row[i] & check) > 0 || (cube[(i / 3) * 3 + j / 3] & check) > 0) {
						return false;
					}
					// 做或运算，“累加”次位置的数字，计入下次的校验中
					line[j] |= check;
					row[i] |= check;
					cube[(i / 3) * 3 + j / 3] |= check;
				}
			}
		}
		return true;
	}

	// 使用一个 int[][] 转存，根据这个二维数组，在赋值的同时依次校验
	public static boolean isValidSudoku2(char[][] board) {
		int[][] check = new int[9][9];
		for (int i = 0; i < 9; i++) {
			// 第 i 行的数组
			for (int j = 0; j < 9; j++) {
				char c = board[i][j];
				if (c != '.') {
					// 转换成 int 类型的对应数字，'.'作为0处理
					check[i][j] = c - '0';
				}
			}
			// 检验第 i 行
			if (isRepeat(check[i])) {
				return false;
			}
			// 检验小方格
			if (i % 3 == 2) {
				for (int l = 0; l < 9; l += 3) {
					int[] childCheck = new int[9];
					int index = 0;
					for (int k = l; k < l + 3; k++) {
						for (int j = i - 2; j < i + 1; j++) {
							childCheck[index++] = check[j][k];
						}
					}
					if (isRepeat(childCheck)) {
						return false;
					}
				}
				// 检验所有的列
				if (i == 8) {
					for (int k = 0; k < 9; k++) {
						int[] childCheck = new int[9];
						int index = 0;
						for (int j = 0; j < 9; j++) {
							childCheck[index++] = check[j][k];
						}
						if (isRepeat(childCheck)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	// true 表示有重复元素
	// 由于数组内部只会出现0-9，而且大小固定为9，使1-1,2-10,3-100，依次类推
	// 最终的和，只要某一位大于1，就表示数组存在重复的数字
	private static boolean isRepeat(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += Math.pow(10, nums[i] - 1);
		}
		while (sum > 0) {
			if (sum % 10 > 1) {
				return true;
			}
			sum /= 10;
		}
		return false;
	}
}