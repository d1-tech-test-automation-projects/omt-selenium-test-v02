import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SystemSettingsTest extends BaseStep {

    @Test
    @Order(1)
    @DisplayName("Tarayıcıyı Aç ve Giriş Yap")
    public void OpenDriverAndLogin() {
        LogTest.info("Tarayıcı açılıyor");
        BaseStep.openChromeDriver();
        LogTest.info("Tarayıcı açıldı");
        LogTest.info("Kullanıcı adı Input aranıyor");
        WebElement usernameInput = BaseStep.findElementXpathWithWait("//*[@id=\"login_email\"]", TimeOut.LONG.value);
        BaseStep.clearAndType(usernameInput, "ardakocaoglu44@gmail.com", "Kullanıcı Adı");
        LogTest.info("Kullanıcı adı gönderildi");
        WebElement passwordInput = BaseStep.findElementXpathWithWait("//*[@id=\"login_password\"]", TimeOut.LONG.value);
        LogTest.info("Parola Inputu bulunuyor");
        BaseStep.clearAndType(passwordInput, "Arda.241144007", "Şifre");
        LogTest.info("Parola gönderildi");
        LogTest.info("Giriş Yap butonu bulunuyor");
        WebElement loginClickButton = BaseStep.findElementXpathWithWait("(//button[@type='submit'])[1]", TimeOut.LONG.value);
        BaseStep.clickElement(loginClickButton, "Giriş yap butonuna tıklandı");
    }

    @Test
    @Order(2)
    @DisplayName("Sistem Ayarları Sayfası Test Ediliyor")
    public void PageSystemSettings() {
        BaseStep.waitSeconds(3);
        LogTest.info("Sistem Ayarları dropdown aranıyor");
        WebElement SystemSettingsDropdown = BaseStep.findElementXpathWithWait("//span[@class='ant-menu-title-content']/a[@href='/settings']", TimeOut.LONG.value);
        LogTest.info("Sistem Ayarları butonuna tıklanıyor");
        BaseStep.clickElement(SystemSettingsDropdown, "Sistem Ayarları butonuna tıklandı");
    }
}
