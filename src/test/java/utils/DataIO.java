package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author JRani
 *
 */

public class DataIO {
		//public static String propertyFilePath = setTestDataFilePath();
	public static String propertyFilePath = "\\resources\\testData\\%22%.properties";
	//MAC
			//private static String propertyFilePath = "/resources/testData/%22%.properties";
		
		//public static String setTestDataFilePath(){
		//	return File.separator+"resources"+File.separator+"testData"+File.separator+"%22%.properties";
			 
		//}
		

	/**
	 * To Fetch the cell value from xls file
	 * @param objectName - Name of locator mentioned in the file
	 * @param fileName - Name of the file in which you need some data
	 * @return - list of related values correspond to the given locator
	 * @throws IOException
	 */
	public static ArrayList<String> getValueFromExcelFile(String objectName, String fileName) throws IOException{
		//String filePath = System.getProperty("user.dir")+"\\resources\\ObjectRepository\\"+fileName+".xls";
		String filePath = System.getProperty("user.dir")+File.separator+"resources"+File.separator+"ObjectRepository"+File.separator+fileName+".xls";

		FileInputStream fis = new FileInputStream(filePath);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = workbook.getSheetAt(0);
		ArrayList<String> elements = new ArrayList<String>();
		for(int i=0; i<= sheet.getLastRowNum(); i++){
			HSSFRow row = sheet.getRow(i);
			if(row.getCell(0).toString().equalsIgnoreCase(objectName)){
				elements.add(row.getCell(0).toString());
				elements.add(row.getCell(1).toString());
				elements.add(row.getCell(2).toString());
				break;
			}
		}
		workbook.close();
		return elements;
	}
	
	
	/**
     * readData From properties file
     * @param property
     * @return text
     */
    public static String getConfig(String property) {
        try {
            Properties prop = ResourceLoader.loadProperties(System.getProperty("user.dir") +File.separator+ "Config.properties");
            return prop.getProperty(property).trim();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
	
	/**
	 * To Overwrite existing value in properties file.
	 * @param property
	 * @param data
	 * @throws Exception
	 */
	public static void overwritePropertiesFile(String property, String data, String fileName) {
		try {
			FileInputStream in = new FileInputStream(System.getProperty("user.dir") + propertyFilePath.replaceAll("%22%", fileName));
		  	Properties props = new Properties();
		  	props.load(in);
		  	in.close();
		  	FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + propertyFilePath.replaceAll("%22%", fileName));
		  	props.setProperty(property, data);
		  	props.store(out, null);
		  	out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
      } 
    /**
     * readData From properties file
     * @param property
     * @return text
     */
    public static String get(String property, String fileName) {
        try {
        	if(fileName.trim().equalsIgnoreCase("Config")){
        		return getConfig(property);
        	}
        	Properties prop = ResourceLoader.loadProperties(System.getProperty("user.dir") + propertyFilePath.replaceAll("%22%", fileName));
            return prop.getProperty(property).trim();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static String updateDataInPropertiesFile(String key, String value,String filename){
		try {
			overwritePropertiesFile(key, value,filename);
		} catch (Exception e) {
			System.out.println(e);
		}
		return value;
	}
    
    
    
    public static void createTxtFileInDownloadFolder(String fileName){ 
    	try{  
    		// create new file	         
    		PrintWriter writer = new PrintWriter(System.getProperty("user.home")+File.separator+"Downloads" +File.separator + fileName + ".txt", "UTF-8");
    		writer.println("The first line");
    		writer.println("The second line");
    		writer.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}

    }
    
   
}
