package Pages;

import io.appium.java_client.AndroidDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class DebugAdsPage {

    private AndroidDriver driver;

    public DebugAdsPage(AndroidDriver driver) {
        this.driver = driver;
    }

    // ========= Locators =========
    By anyButton = By.xpath("//android.widget.Button");
    By nextBtn = By.xpath("//android.widget.TextView[@resource-id='vidfo.video.player.videoplayer:id/nextBtn']");
    By continueBtn = By.xpath("//android.widget.TextView[@resource-id='vidfo.video.player.videoplayer:id/continueBtn']");
    By onboardingNextBtn = By.xpath("//android.widget.Button[@resource-id='vidfo.video.player.videoplayer:id/onBoardingNextBtn']");

    // ========= Page Actions =========

    /** Splash inter dismiss (if exists) */
    public void dismissSplashAdIfAny() {
        if (!driver.findElements(anyButton).isEmpty()) {
            driver.findElement(anyButton).click();
        }
    }

    /** Handle onboarding for first-time user */
    public void completeOnboardingIfVisible() {

        if (!driver.findElements(nextBtn).isEmpty()) {
            driver.findElement(nextBtn).click();
        } else if (!driver.findElements(continueBtn).isEmpty()) {
            driver.findElement(continueBtn).click();
        }

        driver.findElement(onboardingNextBtn).click();
        driver.findElement(onboardingNextBtn).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    /** Final landing click (home) */
    public void landOnHomeScreen() {
        if (!driver.findElements(anyButton).isEmpty()) {
            driver.findElement(anyButton).click();
        }
    }
}

