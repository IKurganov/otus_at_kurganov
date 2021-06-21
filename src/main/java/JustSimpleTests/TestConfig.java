package JustSimpleTests;

import org.aeonbits.owner.Config;

// можем вообще не указывать файл пропертис, использовать только этот интерфейс

@Config.Sources({"classpath:TestConfig.properties"})
public interface TestConfig extends Config {
    @DefaultValue("80")
        //указываем на тот случай, если мы не укажем port в файле конфига Tests.TestConfig
    int port();

    String baseUrl();

    String yandexMarketUrl();

    @DefaultValue("42")
        //указываем на тот случай, если мы не укажем maxThreads в файле конфига Tests.TestConfig
    int maxThreads();
}
