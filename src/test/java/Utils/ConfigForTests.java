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

    @Property("nameRu")
    private static String nameRu;

    @Property("secondnameRu")
    private static String secondnameRu;

    @Property("nameLatin")
    private static String nameLatin;

    @Property("secondnameLatin")
    private static String secondnameLatin;

    @Property("blogName")
    private static String blogName;

    @Property("birthDate")
    private static String birthDate;

    @Property("country")
    private static String country;

    @Property("city")
    private static String city;

    @Property("relocate")
    private static boolean relocate;


    public WebDriverBrowser getWebDriverBrowser(){
        if (System.getProperty("browser") != null){
            browser = System.getProperty("browser");
        }
        return WebDriverBrowser.fromString(browser);
    }

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

    public static String getNameRu() {
        return nameRu;
    }

    public static void setNameRu(String nameRu) {
        ConfigForTests.nameRu = nameRu;
    }

    public static String getSecondnameRu() {
        return secondnameRu;
    }

    public static void setSecondnameRu(String secondnameRu) {
        ConfigForTests.secondnameRu = secondnameRu;
    }

    public static String getNameLatin() {
        return nameLatin;
    }

    public static void setNameLatin(String nameLatin) {
        ConfigForTests.nameLatin = nameLatin;
    }

    public static String getSecondnameLatin() {
        return secondnameLatin;
    }

    public static void setSecondnameLatin(String secondnameLatin) {
        ConfigForTests.secondnameLatin = secondnameLatin;
    }

    public static String getBlogName() {
        return blogName;
    }

    public static void setBlogName(String blogName) {
        ConfigForTests.blogName = blogName;
    }

    public static String getBirthDate() {
        return birthDate;
    }

    public static void setBirthDate(String birthDate) {
        ConfigForTests.birthDate = birthDate;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        ConfigForTests.country = country;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        ConfigForTests.city = city;
    }

    public static boolean isRelocate() {
        return relocate;
    }

    public static void setRelocate(boolean relocate) {
        ConfigForTests.relocate = relocate;
    }
}
