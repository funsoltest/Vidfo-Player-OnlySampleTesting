
import ParentLauncher.VIDFO;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;


@Listeners(ResultListener.class)
public class PermissionTestCase extends VIDFO {

    @Test(priority = 1)
    public void genralPermissions1() throws InterruptedException, IOException {
        Thread.sleep(2000);
        System.out.println("Test Case 1 -> All Permissions With Allow");
        isFirstMethodExecuted = true;
        if(!isFirstTime()){
            System.out.println("This Is Not First Session, So Am Going To Close App And Clear Permission");
            terminateApp(driver,"vidfo.video.player.videoplayer");
            Thread.sleep(1000);
            resetAppData("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
            StartApp();
            Thread.sleep(2000);
        }
        else {
            System.out.println("Already First Session");
        }
        // 3 sec baad start, 25 sec tak check
        clickIfExists(By.xpath("//android.widget.Button"), 3, 25);
        System.out.println("User Over Language Onboarding");
        // Check karo nextBtn hai ya continueBtn
        if (clickIfExists(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/nextBtn\"]"), 1, 5)) {
            // NextBtn click ho gaya
            System.out.println("NextBtn clicked");
        } else {
            // Agar nextBtn nahi mila to continueBtn try karo
            clickIfExists(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/continueBtn\"]"), 1, 5);
            System.out.println("ContinueBtn clicked");
        }
        System.out.println("User Now Over Onboarding Intro Screen");
        clickWithWait(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]"));
        clickWithWait(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]"));
        System.out.println("User Now Over Home Screen ");
        // 3 sec baad start, 25 sec tak check
        clickIfExists(By.xpath("//android.widget.Button"), 3, 25);
        Thread.sleep(3000);
        clickWithWait(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]"));
        System.out.println("Music Permissions Allow Through System Dialogue ");
        clickWithWait(By.id("vidfo.video.player.videoplayer:id/musicMenuItemBottomNavigation"));
        // 3 sec baad start, 25 sec tak check
        clickIfExists(By.xpath("//android.widget.Button"), 3, 25);
        clickWithWait(By.xpath("//android.view.ViewGroup[@resource-id=\"vidfo.video.player.videoplayer:id/grantPermissionBtn\"]"));
        clickWithWait(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]"));
        System.out.println("All Allow Scenario Pass");
        //  Terminate Method Call
        Thread.sleep(3000);
        terminateApp(driver, "vidfo.video.player.videoplayer");
        System.out.println("Test Case 1 ->> Terminate ");

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @Test(priority = 2)
    public void GenralPermissions2() throws InterruptedException{
        System.out.println("Test Case 2 ->> All Permissions With Deny ");


        if (!isFirstMethodExecuted) {
            // Check if the first method was not executed
            System.out.println("First method has not been executed...");
            //All Permissions Remove
            revokeAllPermissions("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
        }
        else {
            System.out.println("Else Condition Working");
            revokeAllPermissions("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
            try {
            Thread.sleep(2000);
            StartApp();
            System.out.println("Test Case 2 -> App Open");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }

//
//        //Checking Wheather genralPermission1 Run Or Not
////        PermissionCheck permissionCheck = new PermissionCheck();
////        permissionCheck.startApp();
//
//        try {
//            Thread.sleep(2000);
//            StartApp();
//            System.out.println("Test Case 2 -> App Open");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        Thread.sleep(2000);
        System.out.println("Click Start");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("User Now Over Home Screen ");
        Thread.sleep(3000);
        System.out.println("Deny 1st Permission");
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(510, 2111)).perform(); // Replace with actual coordinates
        // driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Deny 2nd Permission");
        action.tap(PointOption.point(510, 2111)).perform();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        if (!isFirstMethodExecuted) {
            System.out.println("Currently User In If Condition...");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button2\"]")).click();
            System.out.println("All Deny Scenario Pass");
            //  Terminate Method Call
            Thread.sleep(2000);
            String appPackage = "vidfo.video.player.videoplayer";
            terminateApp(driver, appPackage);
            System.out.println("Test Case 2 ->> Terminate ");
        }
        else {
            System.out.println("Else Condition Working");
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/grantPermission\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_and_dont_ask_again_button\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_and_dont_ask_again_button\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button2\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/navigation_bar_item_small_label_view\" and @text=\"Music\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/navigation_bar_item_small_label_view\" and @text=\"Videos\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button2\"]")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            System.out.println("All Deny Scenario Pass");
            //  Terminate Method Call
            Thread.sleep(2000);
            String appPackage = "vidfo.video.player.videoplayer";
            terminateApp(driver, appPackage);
            System.out.println("Test Case 2 ->> Terminate ");
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @Test(priority = 3)
    public void genralPermission3() throws InterruptedException {

        System.out.println("Test Case 3 -> Allow video And Deny Audio ");
        if (!isFirstMethodExecuted) {
            // Check if the first method was not executed
            System.out.println("First method has not been executed...");
            //All Permissions Remove
            revokeAllPermissions("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
        }
        else {
            System.out.println("Else Condition Working");
            revokeAllPermissions("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
            try {
                Thread.sleep(2000);
                StartApp();
                System.out.println("Test Case 2 -> App Open");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.sleep(2000);
        System.out.println("Click Start");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("User Now Over Home Screen ");
        Thread.sleep(3000);
        System.out.println("Deny 1st Audio Permission");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(510, 2111)).perform();
        System.out.println("Allow 2nd Video Permission");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(550, 1950)).perform();
        System.out.println("Videos Data Loading.....");
        Thread.sleep(1000);
        System.out.println("Videos Data Loading Done");
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/navigation_bar_item_small_label_view\" and @text=\"Music\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/grantPermissionBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Audio Permission Allowing");
        action.tap(PointOption.point(550, 1950)).perform();
        Thread.sleep(1000);
        System.out.println("Audio Data Loaded");
        System.out.println("All Scenario Pass");
        //  Terminate Method Call
        Thread.sleep(2000);
        String appPackage = "vidfo.video.player.videoplayer";
        terminateApp(driver, appPackage);
        System.out.println("Test Case 3 ->> Terminate ");
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test(priority = 4)
    public void genralPermission4() throws InterruptedException {
        System.out.println("Test Case 4 -> Allow Audio And Deny Video ");
        if (!isFirstMethodExecuted) {
            // Check if the first method was not executed
            System.out.println("First method has not been executed...");
            //All Permissions Remove
            revokeAllPermissions("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
        }
        else {
            System.out.println("Else Condition Working");
            revokeAllPermissions("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
            try {
                Thread.sleep(2000);
                StartApp();
                System.out.println("Test Case 2 -> App Open");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.sleep(2000);
        System.out.println("Click Start Over Onboarding Screen");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("User Now Over Home Screen ");
        Thread.sleep(3000);
        System.out.println("Allow 1st Audio Permission");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(550, 1950)).perform();
        System.out.println("Deny 2nd Video Permission");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/grantPermission\"]")).click();
        Thread.sleep(1000);
        System.out.println("Allowing System Video Permission.....");
        action.tap(PointOption.point(550, 1950)).perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/navigation_bar_item_small_label_view\" and @text=\"Music\"]")).click();
        Thread.sleep(3000);
        System.out.println("All Scenario Pass");
        //  Terminate Method Call
        Thread.sleep(2000);
        String appPackage = "vidfo.video.player.videoplayer";
        terminateApp(driver, appPackage);
        System.out.println("Test Case 4 ->> Terminate ");

    }
/// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(priority = 5)
    public void genralPermission5() throws InterruptedException {
        System.out.println("Test Case 5 -> Deny Both Permissions");
        if (!isFirstMethodExecuted) {
            // Check if the first method was not executed
            System.out.println("First method has not been executed...");
            //All Permissions Remove
            revokeAllPermissions("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
        }
        else {
            System.out.println("Else Condition Working");
            revokeAllPermissions("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
            try {
                Thread.sleep(3000);
                StartApp();

                System.out.println("Test Case 2 -> App Open");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.sleep(2000);
        System.out.println("Click Start Over Onboarding Screen");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("User Now Over Home Screen ");
        Thread.sleep(3000);
        TouchAction action = new TouchAction(driver);
        System.out.println("Deny Both System Permission.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        System.out.println("Allow Rational Dialogue.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/grantPermission\"]")).click();
        Thread.sleep(3000);
        System.out.println("Allow Both System Permission.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(550, 1950)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(550, 1950)).perform();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/navigation_bar_item_small_label_view\" and @text=\"Music\"]")).click();
        Thread.sleep(4000);
        System.out.println("All Scenario Pass");
        //  Terminate Method Call
        Thread.sleep(2000);
        String appPackage = "vidfo.video.player.videoplayer";
        terminateApp(driver, appPackage);
        System.out.println("Test Case 5 ->> Terminate ");
    }
/// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(priority = 6)
    public void genralPermission6() throws InterruptedException {
        System.out.println("Test Case 6 -> Deny Both Permissions And Then Allow Video And Deny Audio Again");
        TouchAction action = new TouchAction(driver);
        if (!isFirstMethodExecuted) {
            // Check if the first method was not executed
            System.out.println("First method has not been executed...");
            //All Permissions Remove
            revokeAllPermissions("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
        }
        else {
            System.out.println("Else Condition Working");
            revokeAllPermissions("vidfo.video.player.videoplayer");
            Thread.sleep(2000);
            try {
                Thread.sleep(2000);
                StartApp();
                System.out.println("Test Case 2 -> App Open");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.sleep(2000);
        System.out.println("Click Start Over Onboarding Screen");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
//        try {
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//            driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn"]")).click();
//        } catch (RuntimeException e) {
//            StartApp();
//            Thread.sleep(2000);
//            throw new RuntimeException(e);
//        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("User Now Over Home Screen ");
//        Thread.sleep(3000);
        System.out.println("Deny Both System Permission.....");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(1500);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        System.out.println("Allow Rational Dialogue.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/grantPermission\"]")).click();
        Thread.sleep(3000);
        System.out.println("Allow Video And Deny Audio System Permission.....");
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(550, 1950)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(4000);
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/navigation_bar_item_small_label_view\" and @text=\"Music\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Deny Both And then Allow Video And Deny Audio System Permission Done.....");
        Thread.sleep(1000);
        String appPackage = "vidfo.video.player.videoplayer";
        terminateApp(driver, appPackage);
        System.out.println("Test Case 6 ->> Terminate ");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test(priority = 7)
    public void genralPermission7() throws InterruptedException {
        System.out.println("Test Case 7 -> Deny Both Permissions And Then Allow Audio And Deny Video Again");
        TouchAction action = new TouchAction(driver);
        if (!isFirstMethodExecuted) {
            // Check if the first method was not executed
            System.out.println("First method has not been executed...");
            //All Permissions Remove
            revokeAllPermissions("vidfo.video.player.videoplayer");
        }
        else {
            System.out.println("Else Condition Working");
            revokeAllPermissions("vidfo.video.player.videoplayer");
            try {
                Thread.sleep(2000);
                StartApp();
                System.out.println("Test Case 2 -> App Open");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.sleep(2000);
        System.out.println("Click Start Over Onboarding Screen");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("User Now Over Home Screen ");
        Thread.sleep(3000);
        System.out.println("Deny Both System Permission.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        System.out.println("Allow Rational Dialogue.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/grantPermission\"]")).click();
        Thread.sleep(3000);
        System.out.println("Allow Audio And Deny Video System Permission.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(550, 1950)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Rational Allow Click ");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/grantPermission\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button2\"]")).click();
        Thread.sleep(1000);
        System.out.println("User Over Home Screen ");
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/navigation_bar_item_small_label_view\" and @text=\"Music\"]")).click();
        Thread.sleep(3000);
        System.out.println("Deny Both And then Allow Audio And Deny Video System Permission Done.....");
        String appPackage = "vidfo.video.player.videoplayer";
        terminateApp(driver, appPackage);
        System.out.println("Test Case 7 ->> Terminate ");
    }
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(priority = 8)
    public void genralPermission8() throws InterruptedException {
        System.out.println("Test Case 8 -> Deny Both System Permissions And Then Again Deny Both");
        TouchAction action = new TouchAction(driver);
        if (!isFirstMethodExecuted) {
            // Check if the first method was not executed
            System.out.println("First method has not been executed...");
            //All Permissions Remove
            revokeAllPermissions("vidfo.video.player.videoplayer");
        }
        else {
            System.out.println("Else Condition Working");
            revokeAllPermissions("vidfo.video.player.videoplayer");
            try {
                Thread.sleep(2000);
                StartApp();
                System.out.println("Test Case 2 -> App Open");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Click Start Over Onboarding Screen");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("User Now Over Home Screen ");
        Thread.sleep(3000);
        System.out.println("Deny Both System Permission.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        System.out.println("Allow Rational Dialogue.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/grantPermission\"]")).click();
        Thread.sleep(3000);
        System.out.println("Again Deny Both Permissions.....");
        System.out.println("Deny Both System Permission.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button2\"]")).click();
        Thread.sleep(1000);
        System.out.println("User Over Home Screen ");
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/navigation_bar_item_small_label_view\" and @text=\"Music\"]")).click();
        System.out.println("User Over Music Screen ");
        Thread.sleep(2000);
        System.out.println("Deny Both And then Allow Audio And Deny Video System Permission Done.....");
        String appPackage = "vidfo.video.player.videoplayer";
        terminateApp(driver, appPackage);
        System.out.println("Test Case 8 ->> Terminate ");

    }
    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(priority = 9)
    public void genralPermission9() throws InterruptedException {
        System.out.println("Test Case 9 -> Deny All System And Then Allow All From Setting");
        TouchAction action = new TouchAction(driver);
        if (!isFirstMethodExecuted) {
            // Check if the first method was not executed
            System.out.println("First method has not been executed...");
            //All Permissions Remove
            revokeAllPermissions("vidfo.video.player.videoplayer");
        }
        else {
            System.out.println("Else Condition Working");
            revokeAllPermissions("vidfo.video.player.videoplayer");
            try {
                Thread.sleep(2000);
                StartApp();
                System.out.println("Test Case 9 -> App Open");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Click Start Over Onboarding Screen");
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/onBoardingNextBtn\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("User Now Over Home Screen ");
        Thread.sleep(3000);
        System.out.println("Deny Both System Permission.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        System.out.println("Allow Rational Dialogue.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"vidfo.video.player.videoplayer:id/grantPermission\"]")).click();
        Thread.sleep(3000);
        System.out.println("Again Deny Both Permissions.....");
        System.out.println("Deny Both System Permission.....");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        action.tap(PointOption.point(510, 2111)).perform();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.android.settings:id/recycler_view\"]/android.view.ViewGroup[2]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.android.permissioncontroller:id/recycler_view\"]/android.widget.LinearLayout[5]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.android.permissioncontroller:id/allow_radio_button\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.android.permissioncontroller:id/recycler_view\"]/android.widget.LinearLayout[6]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"com.android.permissioncontroller:id/allow_radio_button_frame\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().back();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"vidfo.video.player.videoplayer:id/navigation_bar_item_small_label_view\" and @text=\"Music\"]")).click();
        Thread.sleep(3000);
        System.out.println("Deny Both And then Allow All From Setting Done.....");
        String appPackage = "vidfo.video.player.videoplayer";
        terminateApp(driver, appPackage);
        System.out.println("Test Case 9 ->> Terminate ");
    }
//    @Test(priority = 2)
//    public void testonly(){
//        System.out.println("Test Case 2 -> All Permissions With Deny");
//    }

    @AfterClass
    public void afterAllTests() {
        takeScreenshot("ALL_TESTS_FINISHED");
        System.out.println("SS Function Run");
    }
}

