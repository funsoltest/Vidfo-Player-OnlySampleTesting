import ParentLauncher.VIDFO;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenShotListener extends VIDFO implements ITestListener {

    // Test fail hote hi ye method call hoga
    @Override
    public void onTestFailure(ITestResult result) {
//        if(driver != null) {
            System.out.println("ScreenShotListener - onTestFailure" + driver);
            System.out.println("Test failed: " + result.getName());
            takeScreenshot(result.getName());
//        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
        takeScreenshot(result.getName() + "_SKIPPED");
    }
}


// Yeh Class Only TestNg.xml say use ho ge