package driver;

import com.codeborne.selenide.WebDriverProvider;

import config.ConfigReader;
import helper.ApkInfoHelper;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс для инициализации AndroidDriver
 */
public class EmulatorDriver implements WebDriverProvider {
    protected static AndroidDriver driver;
    //чтение пропертей
    private static final String DEVICE_NAME = ConfigReader.emulatorConfig.deviceName();
    private static final String PLATFORM_NAME = ConfigReader.emulatorConfig.platformName();
    private static String APP_PACKAGE = ConfigReader.emulatorConfig.appPackage();
    private static String APP_ACTIVITY = ConfigReader.emulatorConfig.appActivity();
    private static final String APP = ConfigReader.emulatorConfig.app();
    private static final String URL = ConfigReader.emulatorConfig.remoteURL();

    /**
     * Валидация URL ссылки из пропертей
     * @return
     */
    public static URL getUrl() {
        try {
            return new URL(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получаем абсолютный путь от рутового путя
     * @param filePath путь к файлу из корня прокта
     * @return
     */
    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");//проверяем что файл существует

        return file.getAbsolutePath();
    }

    /**
     * Получаем AppPackage и AppActivity из чтения apk файла
     */
    private void initPackageAndActivity() {
//        ApkInfoHelper helper = new ApkInfoHelper();
//        //тернарное условие, если app_package не задано в пропертях, достаем из из apk
//        APP_PACKAGE = APP_PACKAGE.isEmpty() ? helper.getAppPackageFromApk() : APP_PACKAGE;
//        APP_ACTIVITY = APP_ACTIVITY.isEmpty() ? helper.getAppMainActivity() : APP_ACTIVITY;
    }


    /**
     * Создает appium сессиюю AndroidDriver
     * @param capabilities настройки для создания сесии
     * @return сессия AndroidDriver
     */
    @Nonnull
    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        initPackageAndActivity();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutoGrantPermissions(true);
        options.setDeviceName(DEVICE_NAME);
        options.setPlatformName(PLATFORM_NAME);
        options.setAppPackage(APP_PACKAGE);
        options.setAppActivity(APP_ACTIVITY);
        options.setApp(getAbsolutePath(APP));

        options.merge(capabilities);

        driver = new AndroidDriver(getUrl(), options);
        return driver;
    }
}
