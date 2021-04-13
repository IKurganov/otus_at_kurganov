import org.aeonbits.owner.Config;

// можем вообще не указывать файл пропертис, использовать только этот интерфейс

@Config.Sources({"file:C:\\Users\\IKurganov\\IdeaProjects\\otus_at_kurganov\\src\\test\\java\\TestConfig.properties"})
public interface TestConfig extends Config {
    @DefaultValue("80")
        //указываем на тот случай, если мы не укажем port в файле конфига TestConfig
    int port();

    String baseUrl();

    @DefaultValue("42")
        //указываем на тот случай, если мы не укажем maxThreads в файле конфига TestConfig
    int maxThreads();
}