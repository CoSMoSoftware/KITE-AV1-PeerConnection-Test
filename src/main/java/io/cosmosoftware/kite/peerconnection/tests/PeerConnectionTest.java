package io.cosmosoftware.kite.peerconnection.tests;

import io.cosmosoftware.kite.peerconnection.Coordinator;
import io.cosmosoftware.kite.peerconnection.checks.ParticipantLeftCheck;
import io.cosmosoftware.kite.peerconnection.pages.MainPage;
import io.cosmosoftware.kite.peerconnection.steps.ConnectClientStep;
import io.cosmosoftware.kite.peerconnection.steps.JoinRoomStep;
import io.cosmosoftware.kite.peerconnection.checks.LocalVideoDisplayCheck;
import io.cosmosoftware.kite.peerconnection.steps.QuitApplicationStep;
import org.webrtc.kite.tests.KiteBaseTest;
import org.webrtc.kite.tests.TestRunner;

public class PeerConnectionTest extends KiteBaseTest {

    protected Coordinator coordinator;

    @Override
    protected void payloadHandling() {
        super.payloadHandling();
        this.coordinator = new Coordinator(this.tupleSize);
    }

    @Override
    protected void populateTestSteps(TestRunner testRunner) {
       MainPage mainPage = new MainPage(testRunner);
       testRunner.addStep(new ConnectClientStep(mainPage, testRunner, this.coordinator, this.url));
       testRunner.addStep(new JoinRoomStep(mainPage, testRunner, this.coordinator, testRunner.getId() == 0));
       testRunner.addStep(new LocalVideoDisplayCheck(mainPage, testRunner, this.coordinator));
       if(testRunner.getId() == 0) {
           testRunner.addStep(new QuitApplicationStep(mainPage, testRunner, this.coordinator));
       } else {
           testRunner.addStep(new ParticipantLeftCheck(mainPage, testRunner, this.coordinator));
           testRunner.addStep(new QuitApplicationStep(mainPage, testRunner, this.coordinator));
       }
    }
}
