package Utils;

public enum WebDriverBrowser {
    FIREFOX("firefox"),
    CHROME("chrome");

    private String browserName;

    WebDriverBrowser(String browserName){
        this.browserName = browserName;
    }

    @Override
    public String toString() {
        return this.browserName;
    }

    public static WebDriverBrowser fromString(String browserName){
        if (browserName.equalsIgnoreCase(CHROME.toString())){
            return CHROME;
        }
        else if (browserName.equalsIgnoreCase(FIREFOX.toString())){
            return FIREFOX;
        }
        else throw new RuntimeException("Неизвестный браузер " + browserName);
    }


}
