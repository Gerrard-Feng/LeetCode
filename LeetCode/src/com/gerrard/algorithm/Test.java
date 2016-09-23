package com.gerrard.algorithm;

import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {

		List<Boolean> list = new LinkedList<>();
		list.add(false);
		System.out.println(list.lastIndexOf(true));
	}
}