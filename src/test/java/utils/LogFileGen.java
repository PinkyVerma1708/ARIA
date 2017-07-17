package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.testng.annotations.Test;

public class LogFileGen {

	@Test
	public void rename(){
		String usr = System.getProperty("user.dir");
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy-hh-mm-ss");
		String nm = formatter.format(currentDate.getTime());
		System.out.println(nm);
		String sfile = usr+File.separator+"logs"+File.separator+"LogFile.log";
		String dfile = usr+File.separator+"logs"+File.separator+nm+".log";
		File fl = new File(sfile);
		File fl2 = new File(dfile);
		if(fl.renameTo(fl2)){
			System.out.println("file is renated");
		}
	}
	
}
