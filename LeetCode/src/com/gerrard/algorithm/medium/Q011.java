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
		int l = 0, r = height.length - 1;
        int area = (r - l) * Math.min(height[l],height[r]);
        while (l < r) {
            if (height[l] < height[r]) {
                l++;
                area = Math.max(area, (r - l) * Math.min(height[l],height[r]));
            }
            else {
                r--;
                area = Math.max(area, (r - l) * Math.min(height[l],height[r]));
            }
        }
        return area;
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
}