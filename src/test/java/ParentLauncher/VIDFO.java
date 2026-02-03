package ParentLauncher;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

//import android.content.Context;
//import android.content.SharedPreferences;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;


@Listeners(ResultListener.class)
public class VIDFO {

    public AndroidDriver driver;
    protected Dimension dim;
    protected boolean isFirstMethodExecuted = false;

    @BeforeClass
    public void StartApp() throws IOException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:deviceName", "Android Device");
        caps.setCapability("appium:appPackage", "vidfo.video.player.videoplayer");
        caps.setCapability("appium:appActivity", "com.example.vidfo.ui.activity.MainActivity");
        caps.setCapability("appium:noReset", true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723/wd/hub"), caps);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        dim = driver.manage().window().getSize();

        System.out.println(">>>> App Started Successfully");
    }

    //Screen Shot Kay Liay
    public void takeScreenshot(String name) {
        try {
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshots/" + name + "_" + time + ".png");
            FileUtils.copyFile(src, dest);
            System.out.println("Screenshot taken successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Screenshot taken failure");
        }
    }

    // 🔹 New Function: Check First Launch
//    @BeforeMethod
    public boolean isFirstTime() {
        try {
            // 3 sec baad start, 25 sec tak check
            clickIfExists(By.xpath("//android.widget.Button"), 3, 25);

            // Check karo nextBtn hai ya continueBtn
            if (clickIfExists(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/nextBtn\"]"), 1, 5)) {
                // NextBtn click ho gaya
                System.out.println("NextBtn clicked");
            } else {
                // Agar nextBtn nahi mila to continueBtn try karo
                clickIfExists(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/continueBtn\"]"), 1, 5);
                System.out.println("ContinueBtn clicked");
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    public boolean checkFirstLaunch() {
//        try {
//            // SharedPreferences inside app context
//            Context appContext = driver.getContext(); // Appium driver context
//            // Note: If AndroidDriver context can't directly give Context, this can be managed via ParentLauncher.VIDFO app-side helper
//            SharedPreferences prefs = ((Context) appContext)
//                    .getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
//
//            boolean isFirstLaunch = prefs.getBoolean("isFirstLaunch", true);
//
//            if (isFirstLaunch) {
//                prefs.edit().putBoolean("isFirstLaunch", false).apply();
//            }
//
//            return isFirstLaunch;
//
//        } catch (Exception e) {
//            System.out.println("Failed to check first launch: " + e.getMessage());
//            return false;
//        }
//    }

    //Click Check karna mila hy ya ni
    public boolean clickIfExists(By locator, int delaySeconds, int maxWaitSeconds) {

        // Pehle 3 seconds wait karo (delay)
        try {
            Thread.sleep(delaySeconds * 1000);
            System.out.println("⏳ " + delaySeconds + " sec delay complete...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ab 25 seconds tak check karo
        FluentWait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(maxWaitSeconds))
                .pollingEvery(Duration.ofMillis(1000))  // Har 1 sec check karo
                .ignoring(NoSuchElementException.class);

        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("✅ Click ho gaya");
            return true;
        } catch (TimeoutException e) {
            System.out.println("⏭️ Element nahi mila, skip...");
            return false;
        }
    }

    // Reusable function
    public void clickWithWait(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }

    // Terminate App

    public void terminateApp(AppiumDriver driver, String appPackage) {
        try {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.terminateApp(appPackage);
            System.out.println("App terminated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to terminate app");
        }
    }

    public void revokeAllPermissions(String appPackage) {
        String[] permissions = {
                "android.permission.READ_MEDIA_VIDEO",
                "android.permission.READ_MEDIA_AUDIO",
                "android.permission.POST_NOTIFICATIONS"
        };

        for (String permission : permissions) {
            try {
                // Store result as String (or Object)
                Object result = driver.executeScript("mobile: shell", Map.of(
                        "command", "pm revoke " + appPackage + " " + permission
                ));

                System.out.println("✓ Revoked: " + permission);
                System.out.println("Result: " + result);

            } catch (Exception e) {
                System.out.println("✗ Failed to revoke " + permission + ": " + e.getMessage());
            }
        }
    }

    public void resetAppData(String appPackage) {
        try {
            driver.executeScript("mobile: shell", Map.of(
                    "command", "pm clear " + appPackage
            ));
            System.out.println("App data cleared - permissions reset");
        } catch (Exception e) {
            System.out.println("Failed to clear app data: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    // Revoke Permissions
//    public void revokeAllPermissions(String appPackage) {
//        try {
//            String[] permissions = {
//                    "android.permission.READ_MEDIA_VIDEO",
//                    "android.permission.READ_MEDIA_AUDIO",
//                    "android.permission.POST_NOTIFICATIONS"
//            };
//
//            for (String permission : permissions) {
//                driver.executeScript("mobile: shell", Map.of(
//                        "command", "pm revoke " + appPackage + " " + permission
//                ));
//            }
//
//            System.out.println("Permissions revoked");
//        } catch (Exception e) {
//            System.out.println("Permission revoke failed");
//        }
//    }

}