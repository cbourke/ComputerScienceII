package unl.soc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

/**
 * Class used for in-class demonstrations.
 * 
 * Author: Chris Bourke Date: 2024-01-01
 * 
 */
public class Demo {
	
	public static <T extends Number> double sum(List<T> values) {
		double total = 0;
		for(T x : values) {
			total += x.doubleValue();
		}
		return total;
	}


	public static void main(String args[]) {
		
		BigInteger x = new BigInteger("23432984723984729387492387492387492387492387492387492387492387498237492837492837498237492837489723");

		Person p = new Person("Bourke", "Chris", null);

		List stuff = new ArrayList();
		stuff.add(42);
		stuff.add("hello");
		stuff.add(10.5);
		stuff.add(p);

		List<Integer> integersOnly = new ArrayList<>();
		integersOnly.add(42);
		integersOnly.add(101);
//		integersOnly.add("hello");
//		integersOnly.add(p);
		double total = 0;
		total = sum(integersOnly);
		System.out.println(total);
		
		List<Double> doublesOnly = new ArrayList<>();
		doublesOnly.add(10.5);
		doublesOnly.add(Math.PI);
		doublesOnly.add(10.0);
		total = sum(doublesOnly);
		System.out.println(total);


		List<Number> anyNumber = new ArrayList<>();
		anyNumber.add(10);
		anyNumber.add(10.5);
		anyNumber.add(Math.PI);
		double foo = sum(anyNumber);
		System.out.println(foo);
		
		//type of variable: int
		//name of the variable: x
		//value of the variable: 10
		//int x = 10;
		

		//type of variable: T (generic: it can be ANY type!)
		//name of the variable: y
		//value of the variable: null
		//T y;
		
	}

}
