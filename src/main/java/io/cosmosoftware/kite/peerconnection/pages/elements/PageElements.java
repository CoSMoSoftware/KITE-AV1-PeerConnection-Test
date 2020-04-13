package io.cosmosoftware.kite.peerconnection.pages.elements;

import java.util.HashMap;

public abstract class PageElements {

    // MAIN PAGE ITEMS
    public static final String MAIN_PAGE_ADDRESS_INPUT = "MAIN_PAGE_ADDRESS_INPUT";
    public static final String MAIN_PAGE_PORT_INPUT = "MAIN_PAGE_PORT_INPUT";
    public static final String MAIN_PAGE_CONNECT_BTN = "MAIN_PAGE_CONNECT_BTN";
    public static final String MAIN_PAGE_PARTICIPANT_BTN = "MAIN_PAGE_PARTICIPANT_BTN";
    public static final String MAIN_PAGE_LIST_LABEL = "MAIN_PAGE_LIST_LABEL";
    public static final String MAIN_PAGE_CLOSE_BTN = "MAIN_PAGE_CLOSE_BTN";

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

    abstract protected void getWindowsElements(HashMap<String, String> elements);
    abstract protected void getMacElements(HashMap<String, String> elements);
    abstract protected void getLinuxElements(HashMap<String, String> elements);
}
