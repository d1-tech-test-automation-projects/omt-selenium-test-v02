import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcurementDashboardTest extends BaseStep {

    @BeforeAll
    public static void setup() {
        openChromeDriver();
    }

    @AfterAll
    public static void tearDownAll() {
        BaseStep.driverQuit();
    }

    @Test
    @Order(5)
    @DisplayName("Tarayıcıyı Aç ve Giriş Yap")
    public void OpenDriverAndLogin() {
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
        BaseStep.waitSeconds(3);
    }

    @Test
    @Order(6)
    @DisplayName("Yatırım Yönetimi Paneli - Sayfaya Git ve Sekmeleri Gez")
    public void TestProcurementDashboard() {
        BaseStep.waitSeconds(1);
        LogTest.info("Yatırım Yönetimi Paneli açılıyor...");
        WebElement procurementLink = BaseStep.findElementXpathWithWait(
            "//span[@class='ant-menu-title-content']/a[@href='/procurement-dashboard']",
            TimeOut.LONG.value);
        BaseStep.clickElement(procurementLink, "Yatırım Yönetimi Paneli linkine tıklandı");
        BaseStep.waitSeconds(3);

        LogTest.info("İstatistik kartları kontrol ediliyor...");
        BaseStep.findElementXpathWithWait(
            "//div[contains(@class,'ant-statistic') or contains(@class,'ant-card')]",
            TimeOut.LONG.value);
        LogTest.info("İstatistik kartları görünüyor");

        String[] tabs = {
            "Personel Listesi", "Mesai Ödemeleri", "Sözleşme Onayları",
            "Faturalar", "Turnover Analizi", "Dış Kaynak Özeti"
        };

        for (String tabName : tabs) {
            LogTest.info(tabName + " sekmesine geçiliyor...");
            WebElement tab = BaseStep.findElementXpathWithWait(
                "//div[@role='tab' and contains(., '" + tabName + "')] | " +
                "//div[contains(@class,'ant-tabs-tab-btn') and contains(text(),'" + tabName + "')]",
                TimeOut.MEDIUM.value);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", tab);
            BaseStep.waitSeconds(1);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", tab);
            BaseStep.waitSeconds(2);
            LogTest.info(tabName + " sekmesi açıldı");
        }
    }
}
