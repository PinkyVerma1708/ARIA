package setup;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CustomizedReporting {

	protected static int totalScriptCount = 0;
	protected static int passedScriptCount = 0;
	protected static int failedScriptCount = 0;
	protected static int skippedScriptCount = 0;

	protected Map<String, String> listOfPassedTestScripts = new HashMap<String, String>();
	protected Map<String, String> listOfFailedTestScripts = new HashMap<String, String>();
	protected Map<String, String> listOfSkippedTestScripts = new HashMap<String, String>();
	
	protected Document htmlFile = null;
	protected static String testngReportFilePath = "test-output"+File.separator+"emailable-report.html";
	protected static String customizedReportFilePath = "CustomReport"+File.separator+"customReport.html";
	
	public void readTestngReportFile(String filePath) {
		try {
			Element table=null;
			htmlFile = Jsoup.parse(new File(filePath), "ISO-8859-1");
			if(htmlFile.getElementById("summary")!=null){
			 table = htmlFile.getElementById("summary");
			}else{
				 table = htmlFile.getElementById("t0");
			}
			Elements failedClass = table.getElementsByAttributeValueContaining("class", "failed");
			Elements passedClass = table.getElementsByAttributeValueContaining("class", "passed");
			Elements skippedClass = table.getElementsByAttributeValueContaining("class", "skipped");

			for (Element skipRow : skippedClass.tagName("tr")) {
				if (!skipRow.select("td").get(0).getElementsByAttribute("rowspan").text().isEmpty()) {
					listOfSkippedTestScripts.put(skipRow.select("td").get(0).getElementsByAttribute("rowspan").text(),skipRow.select("td").get(0).getElementsByAttribute("rowspan").text());
				}
			}
			
			for (Element FailRow : failedClass.tagName("tr")) {
				if (!FailRow.select("td").get(0).getElementsByAttribute("rowspan").text().isEmpty()&& !listOfSkippedTestScripts.containsKey(FailRow.select("td").get(0).getElementsByAttribute("rowspan").text())) {
					listOfFailedTestScripts.put(FailRow.select("td").get(0).getElementsByAttribute("rowspan").text(),FailRow.select("td").get(0).getElementsByAttribute("rowspan").text());
				}
			}

			for (Element PassRow : passedClass.tagName("tr")) {
				if (!PassRow.select("td").get(0).getElementsByAttribute("rowspan").text().isEmpty()&& !listOfFailedTestScripts.containsKey(PassRow.select("td").get(0).getElementsByAttribute("rowspan").text()) && !listOfSkippedTestScripts.containsKey(PassRow.select("td").get(0).getElementsByAttribute("rowspan").text())) {
					listOfPassedTestScripts.put(PassRow.select("td").get(0).text(), PassRow.select("td").get(0).text());
				}
			}
			
			passedScriptCount = listOfPassedTestScripts.size();
			failedScriptCount = listOfFailedTestScripts.size();
			skippedScriptCount = listOfSkippedTestScripts.size();
			totalScriptCount = passedScriptCount + failedScriptCount + skippedScriptCount;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void createTestReport(String sourceFilePath, String targetFilePath){
		StringBuffer html = new StringBuffer();
		String heading = "ARIA Automation Test Script Execution Report";
		String subHeading = "High Level View";

		html.append("<html><h2 style='text-align: center;'><span style='text-decoration: underline;'><span style='color: #333399; text-decoration: underline;'>"+heading+"</span></span></h2><h3 style='text-align: center;'><span style='text-decoration: underline;'><span style='color: #333399; text-decoration: underline;'>"+subHeading+"</span></span></h3>");
		html.append("<table style='height: 121px; background-color: #; margin-left: auto; margin-right: auto;' border='3' width='593'><tbody>");
		html.append("<tr><td style='text-align: center;'><h3>Total Script Executed</h3></td><td style='text-align: center;'><h3><strong>Passed&nbsp;</strong></h3></td><td style='text-align: center;'><h3><strong>Failed&nbsp;</strong></h3></td><td style='text-align: center;'><h3><strong>Skipped</strong></h3></td></tr>");
		html.append("<tr><td style='text-align: center;'><h2><strong>"+totalScriptCount+"</strong></h2></td><td style='text-align: center;'><h2><span style='color: #008000;'><strong>"+passedScriptCount+"</strong></span></h2></td><td style='text-align: center;'><h2><span style='color: #ff0000;'><span style='color: #ff0000;'><strong>"+failedScriptCount+"</strong></span></span></h2></td><td style='text-align: center;'><h2><span style='color: #808;'><strong>"+skippedScriptCount+"</strong></span></h2></td></tr>");
		html.append("</tbody></table><h3 style='text-align: center;'><span style='text-decoration: underline;'><span style='color: #333399; text-decoration: underline;'>Detailed View</span></span></h3></html>");

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(targetFilePath)));
			bw.write(html.toString());
			bw.write(getHtmlFileIntoString(sourceFilePath));
			bw.close();
			System.out.println("Custom Report Generated Successfully!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getHtmlFileIntoString(String filePath){
		StringBuilder contentBuilder = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filePath));
		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
		}
		return contentBuilder.toString();
	}
	
	

	public static void createCustomTestngReport(){
		new CustomizedReporting().readTestngReportFile(testngReportFilePath);
		new CustomizedReporting().createTestReport(testngReportFilePath, customizedReportFilePath);
	}
	
	public static void main(String...s){
		createCustomTestngReport();
	}
	
}
