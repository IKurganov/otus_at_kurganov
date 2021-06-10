package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.Classpath("test.properties")
public class ConfigForTests {
    private Logger log = LogManager.getLogger(ConfigForTests.class);
    private static ConfigForTests instance;


    private ConfigForTests(){
        PropertyLoader.newInstance().populate(this);
    }

    public static ConfigForTests getInstance() {
        if (instance == null) {
                    instance = new ConfigForTests();
                }
        return instance;
    }


    @Property("baseUrl")
    private static String baseUrl;

    @Property("browser")
    private static String browser;

    @Property("user.login")
    private static String userLogin;

    @Property("user.password")
    private static String userPassword;

    @Property("waiting")
    private static int waiting;

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        ConfigForTests.baseUrl = baseUrl;
    }

    public static String getBrowser() {
        return browser;
    }

    public static void setBrowser(String browser) {
        ConfigForTests.browser = browser;
    }

    public static String getUserLogin() {
        return userLogin;
    }

    public static void setUserLogin(String userLogin) {
        ConfigForTests.userLogin = userLogin;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static void setUserPassword(String userPassword) {
        ConfigForTests.userPassword = userPassword;
    }

    public static int getWaiting() {
        return waiting;
    }

    public static void setWaiting(int waiting) {
        ConfigForTests.waiting = waiting;
    }
}
