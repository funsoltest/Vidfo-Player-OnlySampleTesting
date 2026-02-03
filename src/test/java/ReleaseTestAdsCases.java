import ParentLauncher.VIDFO;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ResultListener.class)
public class ReleaseTestAdsCases extends VIDFO {
    @Test(priority = 1)
    public void SplashToHomeRelease() throws Exception {
        System.out.println("=== Test Case: Release Test Ad Load on Splash Screen ===");

        // App start using VIDFOBase method
//

        System.out.println("Checking if ad is displayed on Splash Screen...");
        driver.findElement(By.xpath("//android.widget.Button")).click();

        if (isFirstTime()) {
            System.out.println("First time detected! 1st Session");
            // Handle first time logic
            System.out.println("Onboarding Started");
        } else {
            System.out.println("Not first time! 2nd Session");
            // Handle subsequent logic
            System.out.println("User Land Over Home Screen");

        }

        System.out.println("Clicked");
        Thread.sleep(25000);
        System.out.println("Done");
    }
}
