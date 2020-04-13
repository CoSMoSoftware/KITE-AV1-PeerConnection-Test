package io.cosmosoftware.kite.peerconnection.steps;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.steps.TestStep;
import org.webrtc.kite.tests.TestRunner;

public class QuitApplicationStep extends TestStep {

    private final MainPage mainPage;
    private final Coordinator coordinator;
    private final Runner runner;

    public QuitApplicationStep(MainPage mainPage, Runner runner, Coordinator coordinator) {
        super(runner);
        this.runner = runner;
        this.mainPage = mainPage;
        this.coordinator = coordinator;
    }

    @Override
    protected void step() throws KiteTestException {
        mainPage.quitApplication();
        this.coordinator.removeParticipant(this.runner, Integer.toString(((TestRunner)this.runner).getId()));
    }

    @Override
    public String stepDescription() {
        return "Quit application";
    }

}
