import core.Log;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        Log.info("[Class] : Start Tests in: " + getClass().getSimpleName());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        Log.info("[Class] : End of Tests in: " + getClass().getSimpleName() + "\n");
    }

    @BeforeMethod(alwaysRun = true)
    public void handleTestMethodName(Method method) {
        Log.info("===> START [Test: " + method.getName() + "]");
    }

    @AfterMethod(alwaysRun = true)
    public void logTestResult(ITestResult result) {
        String testResult;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                testResult = "Passed";
                break;
            case ITestResult.FAILURE:
                testResult = "Failed";
                break;
            case ITestResult.SKIP:
                testResult = "Skipped";
                break;
            default:
                testResult = Integer.toString(result.getStatus());
                break;
        }
        Log.info("<===  END  [Test: " + result.getMethod().getMethodName() + "]: Result = " + testResult);
        Log.resetLastStepNumberMap();
    }
}
