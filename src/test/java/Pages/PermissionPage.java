package Pages;

import io.appium.java_client.AndroidDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

public class PermissionPage {

    AndroidDriver driver;
    TouchAction action;

    public PermissionPage(AndroidDriver driver) {
        this.driver = driver;
        this.action = new TouchAction(driver);
    }

    // ===== Locators =====
    By onboardingNextBtn = By.id("vidfo.video.player.videoplayer:id/onBoardingNextBtn");
    By grantPermissionBtn = By.id("vidfo.video.player.videoplayer:id/grantPermission");
    By musicTab = By.xpath("//android.widget.TextView[@text='Music']");
    By videoTab = By.xpath("//android.widget.TextView[@text='Videos']");

    // ===== Onboarding =====
    public void completeOnboarding() {
        driver.findElement(onboardingNextBtn).click();
        driver.findElement(onboardingNextBtn).click();
    }

    // ===== System Permission Actions =====
    public void allowPermission() {
        action.tap(PointOption.point(550, 1950)).perform();
    }

    public void denyPermission() {
        action.tap(PointOption.point(510, 2111)).perform();
    }

    // ===== App Permission Flow =====
    public void openPermissionRationale() {
        driver.findElement(grantPermissionBtn).click();
    }

    public void openMusicTab() {
        driver.findElement(musicTab).click();
    }

    public void openVideoTab() {
        driver.findElement(videoTab).click();
    }

    // ===== Combined reusable flows =====
    public void denyBothPermissions() {
        denyPermission();
        denyPermission();
    }

    public void allowBothPermissions() {
        allowPermission();
        allowPermission();
    }
}

