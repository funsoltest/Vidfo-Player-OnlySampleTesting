package base;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import utils.DriverUtils;

import java.net.URL;
import java.time.Duration;

public class Vidfo {

    public AndroidDriver driver;
    protected Dimension dim;

    @BeforeClass
    public void setup() throws Exception {
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

    public AndroidDriver getDriver() {
        return driver;
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

