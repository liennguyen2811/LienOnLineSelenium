package utils.config;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.UUID;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import utils.common.Common;
import utils.helper.Logger;
import utils.helper.PropertiesHelper;
import utils.integration.Bug;
//import utils.integration.jira.JiraIssueReporterHandler;

import com.logigear.driver.DriverUtils;

public class TestListener implements ITestListener {
//	private JiraIssueReporterHandler jiraHandler;
	private boolean autoReportBug;

	public void onTestFailure(ITestResult result) {
		// Get screenshot
		String path = DriverUtils.captureScreenshot(UUID.randomUUID().toString(), "screenshots");

		String script = Common.screenshotURI(path);
		Reporter.log(script);

		// Get Description
		List<String> logs = Logger.getCurrentLogs();
		String description = "";
		for (String log : logs) {
			description += log + "\n";
		}

		if (autoReportBug) {
			boolean hasBug = result.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(Bug.class);
			if (hasBug) {
				System.out.println("Updating the bug status as Reopened");
				Annotation[] annotations = result.getMethod().getConstructorOrMethod().getMethod()
						.getDeclaredAnnotations();
				String bugID = "";
				for (Annotation annotation : annotations) {
					if (annotation.annotationType() == Bug.class) {
						Bug bug = (Bug) annotation;
						bugID = bug.value();
//						jiraHandler.maskBugStatus(bug.value(), "Reopened");
					}
				}
//				jiraHandler.updateIssue(bugID, description, path);
			} else {
//				jiraHandler.createNewBug(result.getMethod().getDescription(), description, path,
//						result.getMethod().getTestClass().getName(), result.getMethod().getMethodName());
			}
		}
		new File(path).delete();
	}

	public void onTestStart(ITestResult result) {
		Object value = result.getTestContext().getAttribute("autoLogBug");
//		autoReportBug = value == null ? false : (boolean) value;
		Logger.info(String.format("TEST CASE: %s.%s", result.getTestClass().getName(), result.getName()));
	}

	public void onTestSuccess(ITestResult result) {
		if (!autoReportBug)
			return;

		boolean hasBug = result.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(Bug.class);
		if (hasBug) {
			System.out.println("Updating the bug status as Done");
			Annotation[] annotations = result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation.annotationType() == Bug.class) {
					Bug bug = (Bug) annotation;
//					jiraHandler.maskBugStatus(bug.value(), "Done");
				}
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		if (!autoReportBug)
			return;
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		if (!autoReportBug)
			return;
	}

	public void onStart(ITestContext context) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		// Setting for JIRA
		String jiraURL = PropertiesHelper.getPropValue("jira.url");
		String jiraUS = PropertiesHelper.getPropValue("jira.user.name");
		String jiraPWD = PropertiesHelper.getPropValue("jira.user.password");
		String jiraPrjKey = PropertiesHelper.getPropValue("jira.project.key");
//		jiraHandler = new JiraIssueReporterHandler(jiraURL, jiraUS, jiraPWD, jiraPrjKey);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}
