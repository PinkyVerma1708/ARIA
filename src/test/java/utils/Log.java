package utils;

import org.apache.log4j.Logger;

public class Log {

	// Initialize Log4j logs
	private static Logger Log = Logger.getLogger(Log.class.getName());

	// This is to print log for the beginning of the test case
	// as we usually run so many test cases as a test suite
	public static void startTestCase(String sTestCaseName) {
		Log.info("****************************************************************************************");
		Log.info("****************************************************************************************");
		Log.info("$$$$$$$$$$$$$$$$$$$$$         " + sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		Log.info("****************************************************************************************");
		Log.info("****************************************************************************************");
	}

	// This is to print log for the ending of the test case
	public static void endTestCase(String sTestCaseName) {
		Log.info("XXXXXXXXXXXXXXXXXXXXXXX         " + "-E---N---D-"+ "          XXXXXXXXXXXXXXXXXXXXXX");
		Log.info("X");
		Log.info("X");
		Log.info("X");
		Log.info("X");
	}

	//Log method
	public static void info(String message) {
		Log.info(message);
	}
	
	//Log method
	public static void info(String message,Throwable t) {
		Log.info(message,t);
	}

	public static void warn(String message) {
		Log.warn(message);
	}
	
	public static void warn(String message, Throwable t) {
		Log.warn(message,t);
	}

	public static void error(String message) {
		Log.error(message);
	}
	
	public static void error(String message,Throwable t) {
		Log.error(message,t);
	}

	public static void fatal(String message) {
		Log.fatal(message);
	}
	
	public static void fatal(String message,Throwable t) {
		Log.fatal(message,t);
	}

	public static void debug(String message) {
		Log.debug(message);
	}
	
	public static void debug(String message,Throwable t) {
		Log.debug(message,t);
	}
	
}
