package com.gerrard.algorithm.medium;

public class Q011 {

	public static void main(String[] args) {
		int[] height = new int[15000];
		for (int i = 15000; i > 0; i--) {
			height[15000 - i] = i;
		}
		System.out.println(method_1(height));
		System.out.println(method_2(height));
		System.out.println(maxArea(height));
	}

	public static int maxArea(int[] height) {
		if (height == null || height.length < 2) {
			throw new IllegalArgumentException("Input error");
		}
		// 初始面积
		int left = 0, right = height.length - 1;
		int area = (right - left) * Math.min(height[left], height[right]);
		// 左右侧开始的最长高度
		int leftMax = height[left];
		int rightMax = height[right];
		// 从两侧向中间夹逼，即底在不断变小
		while (left < right) {
			if (height[left] < height[right]) {
				left++;
				// 更小的高没有比较价值
				if (height[left] <= leftMax) {
					continue;
				} else {
					leftMax = height[left];
				}
				area = Math.max(area, (right - left) * Math.min(height[left], height[right]));
			} else {
				right--;
				if (height[right] <= rightMax) {
					continue;
				} else {
					rightMax = height[right];
				}
				area = Math.max(area, (right - left) * Math.min(height[left], height[right]));
			}
		}
		return area;
	}

	private static int method_1(int[] height) {
		int max = 0;
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = i + 1; j < height.length; j++) {
				int area = Math.min(height[i], height[j]) * (j - i);
				if (max < area) {
					max = area;
				}
			}
		}
		return max;
	}

	private static int method_2(int[] height) {
		int max = 0;
		for (int i = 0; i < height.length; i++) {
			int maxHeight = 0;
			// 内循环反向遍历
			for (int j = height.length - 1; j > i; j--) {
				int h = Math.min(height[i], height[j]);
				// 因为底越来越小，所以只有高度大于最高高度，才有比较面积的意义
				if (h > maxHeight) {
					// 不考虑面积比较结果，高先赋值
					maxHeight = h;
					if (h * (j - i) > max) {
						max = h * (j - i);
					}
				}
			}
		}
		return max;
	}
}