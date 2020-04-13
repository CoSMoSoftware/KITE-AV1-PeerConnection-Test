package io.cosmosoftware.kite.peerconnection.checks;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.ReportUtils;
import static io.cosmosoftware.kite.util.TestUtils.videoCheckByBytes;
import static io.cosmosoftware.kite.util.TestUtils.waitAround;

public class LocalVideoDisplayCheck extends TestStep {
    private final Coordinator coordinator;
    private final Runner runner;
    private final MainPage mainPage;
    public LocalVideoDisplayCheck(MainPage mainPage, Runner runner, Coordinator coordinator) {
        super(runner);
        this.runner = runner;
        this.mainPage = mainPage;
        this.coordinator = coordinator;
    }

    @Override
    protected void step() throws KiteTestException {
        waitAround(3000);
        String videoCheck = videoCheckByBytes(webDriver);
        this.reporter.screenshotAttachment(this.report, this.getClassName(), ReportUtils
                .saveScreenshotPNG(this.webDriver));
        this.reporter.textAttachment(this.report, "Video Check", videoCheck, "plain");
    }

    @Override
    public String stepDescription() {
        return "Video checking";
    }
}

