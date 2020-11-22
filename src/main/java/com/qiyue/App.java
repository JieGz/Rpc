package com.qiyue;

import java.util.Arrays;

public class App {

	public static void main(String[] args) {
		//在 VM options 增加-Dluke=111,也可以用过java -jar -Dluke=111 app.jar的方式
		System.out.println(System.getProperty("luke"));


		String[] arr = new String[]{"a", "b", "c"};
		System.out.println(Arrays.toString(arr));
	}
}
