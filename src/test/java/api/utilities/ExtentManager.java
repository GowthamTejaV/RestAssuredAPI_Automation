package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	public static ExtentReports createInstance() {
			String workingDir = System.getProperty("user.dir");
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
					workingDir + "/Reports/extentReport/reportExtent.html");

			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setReportName("Functional Report");
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setDocumentTitle("AutoMation Report");

			htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("OS", "Windows");
			extent.setSystemInfo("AUT", "QA");

			return extent;
	
	}



}
