import java.lang.reflect.Method;

import telran.test.Tests;

public class TestsApp {

	public static void main(String[] args) throws Exception{
		if (args.length > 1 && args.length % 2 == 0) {
			Tests tests = new Tests();
			for(int i = 0; i < args.length; i+=2) {
				runTest(args[i], args[i + 1], tests) ;
			}
			
		} else {
			System.out.println("Usage pairs of test method name and display name");
		}

	}

	private static void runTest(String testName, String testArg, Tests tests) throws Exception{
		Method method = Tests.class.getDeclaredMethod(testName, String.class);
		method.setAccessible(true); //for calling the private methods
		method.invoke(tests, testArg);
		
	}

}
