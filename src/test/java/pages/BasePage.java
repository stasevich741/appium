package pages;

import static com.codeborne.selenide.Selenide.$;

import java.io.File;

import io.appium.java_client.AppiumBy;

/**
 * Базовый тестовый класс для скриншотов
 */
public class BasePage {

    /**
     * Делает скриншот экрана без статусбара
     * @return файл скриншота
     */
    public File fullPageScreenshot(){
        return $(AppiumBy.id("composerRootCl")).screenshot();
    }
}
