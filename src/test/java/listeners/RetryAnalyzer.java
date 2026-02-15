package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int maxRetryCount = 1; // retry once

    @Override
    public boolean retry(ITestResult result) {

        if (count < maxRetryCount) {
            count++;
            return true;
        }

        return false;
    }
}
