package setup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
public class DesignHTMLReport {

	public static void main(String[] args) {
	
		StringBuffer html = new StringBuffer();

		html.append("<html><h2 style='text-align: center;'><span style='text-decoration: underline;'><span style='color: #333399; text-decoration: underline;'>Workday Automation Test Execution Report</span></span></h2><h3 style='text-align: center;'>Tenant Management Test Case</h3>");
		html.append("<table style='height: 121px; background-color: #; margin-left: auto; margin-right: auto;' border='3' width='593'><tbody>");
		html.append("<tr><td style='text-align: center;'><h3>Totel Script Executed</h3></td><td style='text-align: center;'><h3><strong>Passed&nbsp;</strong></h3></td><td style='text-align: center;'><h3><strong>Failed&nbsp;</strong></h3></td><td style='text-align: center;'><h3><strong>Skipped</strong></h3></td></tr>");
		html.append("<tr><td style='text-align: center;'><h2><strong>100</strong></h2></td><td style='text-align: center;'><h2><span style='color: #008000;'><strong>60</strong></span></h2></td><td style='text-align: center;'><h2><span style='color: #ff0000;'><span style='color: #ff0000;'><strong>30</strong></span></span></h2></td><td style='text-align: center;'><h2><span style='color: #808;'><strong>10</strong></span></h2></td></tr>");
		html.append("</tbody></table></html>");

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"report1.html")));
			bw.write(html.toString());
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}