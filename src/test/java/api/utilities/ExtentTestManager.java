package api.utilities;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentTestManager {

	private ExtentReports extent;
	public static ExtentTest test;

	private Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

	public synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public synchronized void endTest() {

		extent.flush();
	}

	public synchronized ExtentTest startTest(String testName) {

		extent = ExtentManager.getInstance();
		test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;

	}
	
	
	
	public static void addJsonResponseToReport(String  json) {
		test.log(test.getStatus(),MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
	}

}