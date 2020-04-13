package io.cosmosoftware.kite.peerconnection.steps;

import static io.cosmosoftware.kite.entities.Timeouts.*;
import static io.cosmosoftware.kite.util.TestUtils.waitAround;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestStep;

public class JoinRoomStep extends TestStep {

    private final boolean isHost;
    private final Coordinator coordinator;
    private final MainPage mainPage;

    /**
     * Instantiates a new Test step.
     *
     * @param runner the runner
     */
    public JoinRoomStep(MainPage mainPage, Runner runner, Coordinator coordinator, boolean isHost) {
        super(runner);
        this.coordinator = coordinator;
        this.isHost = isHost;
        this.mainPage = mainPage;
    }

    @Override
    protected void step() throws KiteTestException {
        if (isHost) {
            for (int elapsed = 0; elapsed < DEFAULT_TIMEOUT; elapsed += ONE_SECOND_INTERVAL) {
                if (coordinator.allParticipantsConnected()) {
                    mainPage.joinRoom();
                    this.coordinator.setRoomId("0");
                    return;
                }
                waitAround(ONE_SECOND_INTERVAL);
            }
            throw new KiteTestException("Some participants failed to connect to the room before timeout", Status.FAILED);
        } else {
             for (int elapsed = 0; elapsed < DEFAULT_TIMEOUT; elapsed += ONE_SECOND_INTERVAL) {
                if (coordinator.getRoomId().equals("0")) {
                    mainPage.changeWindow();
                    return;
            }
            waitAround(ONE_SECOND_INTERVAL);
        }
        throw new KiteTestException("Some participants failed to connect to the room before timeout", Status.FAILED);

        }
    }

    @Override
    public String stepDescription() {
        return "Join room";
    }
}
