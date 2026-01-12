import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class DebugAdsTestCase extends VIDFO {

    @Test(priority = 1)
    public void SplashToHome() throws Exception {
        System.out.println("=== Test Case: Debug Test Ad Load Splash Screen ===");
        Thread.sleep(500);
        System.out.println("Checking if ad is displayed on Splash Screen...");

        // 3 sec baad start, 25 sec tak check
        clickIfExists(By.xpath("//android.widget.Button"), 3, 25);
//        boolean clicked = clickIfPresent(By.xpath("//android.widget.Button"));
//
//

//        driver.findElement(By.xpath("//android.widget.Button")).click();
        System.out.println("Splash inter Dismiss");

        Thread.sleep(2000);

        if (isFirstTime()) {
            System.out.println("First time detected! 1st Session");
            // Handle first time logic
            System.out.println("Onboarding Started");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/nextBtn\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.findElement(By.xpath("//android.widget.Button")).click();
            System.out.println("User Land over Home Screen");
        } else {
            System.out.println("Not first time! 2nd Session");
            // Handle subsequent logic
            System.out.println("User Land Over Home Screen");

        }
        System.out.println("=== Splash to Home Function End ===");
        Thread.sleep(500);
    }
    @AfterClass
    public void CloseApp(){
        if (driver != null) {
            System.out.println("Closing Application");
            driver.quit();
        }
    }
}

        // Example: click on ad if exists (replace with your actual locator)
//        if (!driver.findElement(By.xpath("")).click()) {
//            driver.findElementById("vidfo.video.player.videoplayer:id/homeAd").click();
//            System.out.println("Ad clicked successfully!");
//        } else {
//            System.out.println("No ad found on home screen.");
//        }
//

        // Terminate app after test
//        terminateApp(driver, "vidfo.video.player.videoplayer");
//        System.out.println("App terminated after ad test.");
//    }

//    @Test(priority = 2)
//    public void testInterstitialAdOnExit() throws Exception {
//        System.out.println("=== Test Case: Interstitial Ad on Exit ===");
//
//        // Start app
//        StartApp();
//
//        // Navigate to exit action (replace with your app's navigation)
//        System.out.println("Navigating to exit screen...");
//
//        // Check if interstitial ad is displayed
////        if (!driver.findElementsById("vidfo.video.player.videoplayer:id/exitAd").isEmpty()) {
////            System.out.println("Interstitial ad displayed on exit.");
////        } else {
////            System.out.println("No interstitial ad found on exit.");
////        }
//
//        // Terminate app
//        terminateApp(driver, "vidfo.video.player.videoplayer");
//    }
//}
