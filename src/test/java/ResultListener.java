import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ResultListener implements ITestListener {
    private int passCount = 0;
    private int failCount = 0;
    private int skipCount = 0;

    @Override
    public void onTestStart(ITestResult result) {
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("\n[" + timestamp + "] ▶ Starting: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        passCount++;
        long duration = result.getEndMillis() - result.getStartMillis();
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());

        System.out.println("[" + timestamp + "] ✅ PASSED: " + result.getName());
        System.out.println("   ⏱ Duration: " + formatDuration(duration));
        System.out.println("   📊 Pass Count: " + passCount);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failCount++;
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());

        System.out.println("[" + timestamp + "] ❌ FAILED: " + result.getName());
        System.out.println("   ⚠ Error: " + result.getThrowable().getMessage());
        System.out.println("   📊 Fail Count: " + failCount);

        // Stack trace print karna (optional)
        if (result.getThrowable() != null) {
            System.out.println("   📋 Stack Trace:");
            result.getThrowable().printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skipCount++;
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("[" + timestamp + "] ⊘ SKIPPED: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 TEST SUITE STARTED");
        System.out.println("   Suite: " + context.getName());
        System.out.println("   Time: " + timestamp);
        System.out.println("   Total Tests: " + context.getAllTestMethods().length);
        System.out.println("=".repeat(60));
    }

    @Override
    public void onFinish(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int total = context.getAllTestMethods().length;

        System.out.println("\n" + "=".repeat(60));
        System.out.println("🏁 TEST SUITE FINISHED");
        System.out.println("   Suite: " + context.getName());
        System.out.println("   Time: " + timestamp);
        System.out.println("   " + "-".repeat(56));
        System.out.println("   📊 RESULTS:");
        System.out.println("      Total Tests: " + total);
        System.out.println("      ✅ Passed: " + passCount);
        System.out.println("      ❌ Failed: " + failCount);
        System.out.println("      ⊘ Skipped: " + skipCount);

        if (total > 0) {
            double successRate = (passCount * 100.0) / total;
            System.out.println("      📈 Success Rate: " + String.format("%.2f", successRate) + "%");
        }
        System.out.println("=".repeat(60) + "\n");
    }

    private String formatDuration(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;

        if (minutes > 0) {
            return minutes + "m " + seconds + "s";
        } else {
            return millis + "ms";
        }
    }

    //For Basic Detils Use Below Functios

//    @Override
//    public void onTestStart(ITestResult result) {
//        System.out.println("\n▶ Test Started: " + result.getName());
//        System.out.println("   Class: " + result.getTestClass().getName());
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        long duration = result.getEndMillis() - result.getStartMillis();
//        System.out.println("✅ Test PASSED: " + result.getName());
//        System.out.println("   Duration: " + duration + "ms");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        System.out.println("❌ Test FAILED: " + result.getName());
//        System.out.println("   Error: " + result.getThrowable().getMessage());
//        result.getThrowable().printStackTrace();
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        System.out.println("⊘ Test SKIPPED: " + result.getName());
//    }
//
//    @Override
//    public void onStart(ITestContext context) {
//        System.out.println("\n╔════════════════════════════════════════╗");
//        System.out.println("  Test Suite Started: " + context.getName());
//        System.out.println("  Total Tests: " + context.getAllTestMethods().length);
//        System.out.println("╚════════════════════════════════════════╝");
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        int total = context.getAllTestMethods().length;
//        int passed = context.getPassedTests().size();
//        int failed = context.getFailedTests().size();
//        int skipped = context.getSkippedTests().size();
//
//        System.out.println("\n╔════════════════════════════════════════╗");
//        System.out.println("  Test Suite Finished: " + context.getName());
//        System.out.println("  ─────────────────────────────────────");
//        System.out.println("  Total Tests: " + total);
//        System.out.println("  ✅ Passed: " + passed);
//        System.out.println("  ❌ Failed: " + failed);
//        System.out.println("  ⊘ Skipped: " + skipped);
//        System.out.println("  Success Rate: " + (passed * 100 / total) + "%");
//        System.out.println("╚════════════════════════════════════════╝\n");
//    }
}