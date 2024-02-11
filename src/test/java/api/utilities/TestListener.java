package api.utilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class TestListener implements ITestListener {
	private ExtentTestManager extentTestManager;
	
	public TestListener() {
		
		extentTestManager = new ExtentTestManager();
	}

	public void onStart(ITestContext context) {

		System.out.println("*** Test Suite " + context.getName() + " started ***");

	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		extentTestManager.endTest();
		context.getCurrentXmlTest();
		
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {

		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		extentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		String msg="*** Executed " + result.getMethod().getMethodName() + " test successfully...";
		System.out.println(msg);
		extentTestManager.getTest().info(MarkupHelper.createLabel(msg, ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		String msg="*** Test execution " + result.getMethod().getMethodName() + " failed...";
		System.out.println(msg);
		extentTestManager.getTest().info(MarkupHelper.createLabel(msg, ExtentColor.RED));

		String str = result.getThrowable().fillInStackTrace().getMessage();
		System.out.println(str);

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		extentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());

	}

       
}