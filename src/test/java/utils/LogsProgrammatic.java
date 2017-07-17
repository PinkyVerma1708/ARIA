package utils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.WebDriver;
/**
 * @author Jrani
 */
public class LogsProgrammatic {
	
	public PatternLayout layout;
	public static Logger logger ;
	static WebDriver driver;
	static FileAppender fileAppender;
	static ConsoleAppender consoleAppender;
	
	public LogsProgrammatic(){
		logger = Logger.getLogger(LogsProgrammatic.class);
		fileAppender = new FileAppender();
		consoleAppender = new ConsoleAppender();
	}
	
	public static void main(String[] args) {
		// creates pattern layout
		new LogsProgrammatic()._initLog4j();
		//logger = Logger.getLogger(LogsProgrammatic.class);
		logger.debug("this is a debug log message");
		logger.info("this is a information log message");
		logger.warn("this is a warning log message");
		logger.error("this is a warning log message");
	}
	
	private void _initLog4j(){
		layout = new PatternLayout();
		String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
		layout.setConversionPattern(conversionPattern);

		// creates console appender
		consoleAppender.setLayout(layout);
		consoleAppender.activateOptions();

		// creates file appender
		fileAppender = new FileAppender();
		fileAppender.setFile("applog.txt");
		fileAppender.setLayout(layout);
		fileAppender.activateOptions();

	}
	
	public void logMessage(String message){
		logger.info(message);
	}
}