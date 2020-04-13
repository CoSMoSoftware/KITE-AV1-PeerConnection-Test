package io.cosmosoftware.kite.peerconnection.steps;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.ReportUtils;
import org.webrtc.kite.tests.TestRunner;

public class ConnectClientStep extends TestStep {

    private final MainPage mainPage;
    private final String url;
    private final Coordinator coordinator;
    private final Runner runner;

    public ConnectClientStep(MainPage mainPage, Runner runner, Coordinator coordinator, String url) {
        super(runner);
        this.runner = runner;
        this.mainPage = mainPage;
        this.coordinator = coordinator;
        this.url = url;
    }

    @Override
    protected void step() throws KiteTestException {
        mainPage.setAddress(this.url);
        mainPage.connectServer();
        this.coordinator.addParticipant(this.runner, Integer.toString(((TestRunner)this.runner).getId()));
        try {
         this.reporter.screenshotAttachment(this.report, this.getClassName(), ReportUtils
                    .saveScreenshotPNG(this.webDriver));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String stepDescription() {
        return "Connect client to server";
    }

}
