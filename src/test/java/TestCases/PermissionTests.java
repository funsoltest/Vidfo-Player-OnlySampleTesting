package TestCases;

import base.VIDFO;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.PermissionPage;

@Listeners(ResultListener.class)
public class PermissionTests extends VIDFO {

    @Test(priority = 1)
    public void allPermissionsAllow() throws Exception {

        PermissionPage permission = new PermissionPage(driver);

        resetAppIfNotFirstRun();
        permission.completeOnboarding();

        permission.allowBothPermissions();
        permission.openMusicTab();

        terminateApp(driver, "vidfo.video.player.videoplayer");
    }

    @Test(priority = 2)
    public void allPermissionsDeny() throws Exception {

        PermissionPage permission = new PermissionPage(driver);

        revokeAllPermissions("vidfo.video.player.videoplayer");
        StartApp();

        permission.completeOnboarding();
        permission.denyBothPermissions();

        terminateApp(driver, "vidfo.video.player.videoplayer");
    }

    @Test(priority = 3)
    public void allowVideoDenyAudio() throws Exception {

        PermissionPage permission = new PermissionPage(driver);

        revokeAllPermissions("vidfo.video.player.videoplayer");
        StartApp();

        permission.completeOnboarding();
        permission.denyPermission();   // Audio
        permission.allowPermission();  // Video

        permission.openMusicTab();
        permission.openPermissionRationale();
        permission.allowPermission();

        terminateApp(driver, "vidfo.video.player.videoplayer");
    }
}
