package io.cosmosoftware.kite.peerconnection;

import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.report.AllureTestReport;
import io.cosmosoftware.kite.report.Reporter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Coordinator {

    private final int participantCount;
    private String roomId = "none";
    private HashMap<Runner, String> participantMap = new HashMap<>();
    private List<String> participants = new ArrayList<>();

    public Coordinator(int participantCount) {
        this.participantCount = participantCount;
    }

    public synchronized String getRoomId() {
        return roomId;
    }

    public synchronized void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public synchronized boolean allParticipantsConnected() {
        return this.participants.size() == this.participantCount;
    }

    public synchronized int getParticipantSize() { return this.participants.size(); }

    public synchronized void addParticipant(Runner runner, String id) {
        this.participantMap.put(runner, id);
        this.participants.add(id);
    }
    public synchronized  void removeParticipant(Runner runner, String id) {
        this.participantMap.remove(runner, id);
        this.participants.remove(id);
    }

}
