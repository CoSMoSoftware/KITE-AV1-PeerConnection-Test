package io.cosmosoftware.kite.peerconnection.pages;

import static io.cosmosoftware.kite.entities.Timeouts.FIVE_SECOND_INTERVAL;
import static io.cosmosoftware.kite.entities.Timeouts.ONE_SECOND_INTERVAL;
import static io.cosmosoftware.kite.util.TestUtils.waitAround;
import io.cosmosoftware.kite.peerconnection.pages.elements.MainPageElements;
import io.cosmosoftware.kite.exception.KiteInteractionException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.peerconnection.pages.elements.PageElements;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

public class MainPage extends Page {

    private final MainPageElements mainPageElements = new MainPageElements();
    private boolean streaming = false;
    private String windowHandle;

    public MainPage(Runner runner) {
        super(runner);
        this.os = ((RemoteWebDriver) runner.getWebDriver()).getCapabilities().getPlatform().toString();
        this.elements = this.mainPageElements.populateElement(this.os);
    }

    public void setAddress(String address) throws KiteInteractionException {
        this.windowHandle = this.webDriver.getWindowHandle();
        sendKeys(getElement(PageElements.MAIN_PAGE_ADDRESS_INPUT), address);
    }

    public void setPort(String port) throws KiteInteractionException {
        sendKeys(getElement(PageElements.MAIN_PAGE_PORT_INPUT), port);
    }

    public void connectServer() throws KiteInteractionException {
        click(getElement(PageElements.MAIN_PAGE_CONNECT_BTN));
    }

    public void joinRoom() throws  KiteInteractionException {
        ((RemoteWebDriver) this.webDriver).getMouse().doubleClick(((RemoteWebElement) (getElement(PageElements.MAIN_PAGE_PARTICIPANT_BTN))).getCoordinates());
        waitAround(ONE_SECOND_INTERVAL);
        this.webDriver.switchTo().window(windowHandle);
    }

    public void quitApplication() throws KiteInteractionException {
        click(getElement(PageElements.MAIN_PAGE_CLOSE_BTN));
    }

    public void isOnParticipantList() throws KiteInteractionException {
        changeWindow();
        waitUntilVisibilityOf(getElement(PageElements.MAIN_PAGE_LIST_LABEL), FIVE_SECOND_INTERVAL);
    }

    public void changeWindow() {
        this.webDriver.switchTo().window(windowHandle);
    }
}
