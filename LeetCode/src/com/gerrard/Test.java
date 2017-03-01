package com.gerrard;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		char[][] cArray = new char[9][9];
		System.out.println(cArray);
		map.put("Result", cArray);
		System.out.println(map.get("Result"));
	}
}
