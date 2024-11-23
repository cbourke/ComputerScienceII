package unl.soc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ConvertMapTests {

	public static final double TOLERANCE = 0.001;

	private static boolean isClose(double a[][], double b[][]) {
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[i].length; j++) {
				if(Math.abs(a[i][j] - b[i][j]) > TOLERANCE) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Test
	public void testConvertMap001() {
		double map[][] = {
				{ 9.50, 8.75, 7.25, 8.25, 8.25 },
				{ 8.50, 9.35, 6.45, 6.50, 7.25 },
				{ 7.50, 8.60, 4.50, 5.50, 5.75 }
		};
		
		double expected[][] = {
				{ 31.167, 28.707, 23.786, 27.067, 27.066 },
				{ 27.887, 30.675, 21.161, 21.325, 23.786 },
				{ 24.606, 28.215, 14.763, 18.044, 18.864 }
		};
		double actual[][] = RunningUtils.convertMap(map);

		assertTrue( isClose(expected, actual) );

	}
	
	@Test
	public void testConvertMap002() {
		double map[][] = {
				{ 1, 2 },
				{ 3, 4 },
				{ 5, 6 }
		};
		
		double expected[][] = {
				{ 3.28084, 6.56168  },
				{ 9.84252, 13.12336 },
				{ 16.4042, 19.68504 }
		};
		double actual[][] = RunningUtils.convertMap(map);

		assertTrue( isClose(expected, actual) );

	}
	
	@Test
	public void testConvertMap003() {
		double map[][] = {
				{ 2, 3, 5, 7, 11, 13}
		};
		
		double expected[][] = {
				{ 6.56168, 9.84252, 16.4042, 22.96588, 36.08924, 42.65092 }
		};
		double actual[][] = RunningUtils.convertMap(map);

		assertTrue( isClose(expected, actual) );

	}

}
