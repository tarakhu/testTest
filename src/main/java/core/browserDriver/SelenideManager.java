package core.browserDriver;

import com.codeborne.selenide.Configuration;
import core.Log;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SelenideManager {

    private DesiredCapabilities capabilities = new DesiredCapabilities();

    public void configure(String browserName) {
        BrowserType browserType = BrowserType.getBrowserType(browserName);

        Configuration.browser = browserType.getTypeString();
        Configuration.browserSize = "1920x1080";
        capabilities.setJavascriptEnabled(true);
        capabilities.setBrowserName(browserName);
        String tz = System.getProperty("user.timezone");
        capabilities.setCapability("timeZone", tz);
        Log.info("TimeZone " + tz);

        Log.debug("Setting " + browserName + " capabilities");
        switch (browserType) {
            case FIREFOX:
                setFireFox();
                break;
            case CHROME:
                setChrome();
                break;
            default:
                throw new IllegalArgumentException("Invalid browserType specified");
        }
        Configuration.browserCapabilities = capabilities;
    }

    private void setFireFox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("media.navigator.streams.fake", true);
        options.addPreference("media.navigator.permission.disabled", true);
        options.setAcceptInsecureCerts(true);
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        options.addPreference("pdfjs.disabled", true);
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
    }

    private void setChrome() {
        capabilities.setCapability("seleniumProtocol", "WebDriver");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.addArguments("--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream",
                "--enable-usermedia-screen-capturing", "--allow-real-media", "--test-type", "--disable-popup-blocking");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("prompt_for_download", false);
        prefs.put("directory_upgrade", true);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }
}
