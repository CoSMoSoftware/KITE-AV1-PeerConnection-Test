package io.cosmosoftware.kite.peerconnection.checks;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.ReportUtils;

import static io.cosmosoftware.kite.entities.Timeouts.DEFAULT_TIMEOUT;
import static io.cosmosoftware.kite.entities.Timeouts.ONE_SECOND_INTERVAL;
import static io.cosmosoftware.kite.util.TestUtils.waitAround;

public class ParticipantLeftCheck extends TestStep {
    private final Coordinator coordinator;
    private final Runner runner;
    private final MainPage mainPage;
    public ParticipantLeftCheck(MainPage mainPage, Runner runner, Coordinator coordinator) {
        super(runner);
        this.runner = runner;
        this.mainPage = mainPage;
        this.coordinator = coordinator;
    }

    @Override
    protected void step() throws KiteTestException {
        mainPage.changeWindow();
        for (int elapsed = 0; elapsed < DEFAULT_TIMEOUT; elapsed += ONE_SECOND_INTERVAL) {
            logger.info("PARTICIPANT NB: " + coordinator.getParticipantSize());
            if (coordinator.getParticipantSize() == 1) {
                this.mainPage.isOnParticipantList();
                this.reporter.screenshotAttachment(this.report, this.getClassName(), ReportUtils
                        .saveScreenshotPNG(this.webDriver));
                this.reporter.textAttachment(this.report, "Number of participant", Integer.toString(this.coordinator.getParticipantSize()), "plain");
                return;
            }
            waitAround(ONE_SECOND_INTERVAL);
        }
        throw new KiteTestException("Some participants failed to disconnect from the room before timeout", Status.FAILED);
    }

    @Override
    public String stepDescription() {
        return "Participant has left checking";
    }
}




