package io.cosmosoftware.kite.peerconnection.pages.elements;


import java.util.HashMap;

public class MainPageElements extends PageElements {

    // WINDOWS============================================================
    private String addressInput = "/Window/Edit[1]";
    private String portInput = "/Window/Edit[2]";
    private String connectInput = "/Window/Button";
    private String participantBtn = "/Window/List/ListItem[2]";
    private String closeBtn = "/Window/TitleBar/Button[3]";
    private String listLabel = "/Window/List/ListItem[1]";

    // MAC================================================================
    private String addressInputMac = "/Window/Window[5]/Group/Button";
    private String portInputMac = "/Window/Window[5]/Group/Button";
    private String connectInputMac = "/Window/Window[5]/Group/Button";

    public MainPageElements() {

    }

    public HashMap<String, String> populateElement(String os) {
        HashMap<String, String> elements = new HashMap<>();

        if (os.equals("WINDOWS")) {
            getWindowsElements(elements);
        } else if (os.equals("MAC")) {
            getMacElements(elements);
        } else {
            getLinuxElements(elements);
        }
        return elements;
    }

    protected void getWindowsElements(HashMap<String, String> elements) {
        elements.put(PageElements.MAIN_PAGE_ADDRESS_INPUT, this.addressInput);
        elements.put(PageElements.MAIN_PAGE_PORT_INPUT, this.portInput);
        elements.put(PageElements.MAIN_PAGE_CONNECT_BTN, this.connectInput);
        elements.put(PageElements.MAIN_PAGE_PARTICIPANT_BTN, this.participantBtn);
        elements.put(PageElements.MAIN_PAGE_CLOSE_BTN, this.closeBtn);
        elements.put(PageElements.MAIN_PAGE_LIST_LABEL, this.listLabel);
    }

    protected void getMacElements(HashMap<String, String> elements) {
    }

    protected void getLinuxElements(HashMap<String, String> elements) {
    }

}
