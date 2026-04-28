import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InvoiceManagementTest extends BaseStep {

    @BeforeAll
    public static void setup() {
        openChromeDriver();
    }

    @AfterAll
    public static void tearDownAll() {
        BaseStep.driverQuit();
    }

    @Test
    @Order(1)
    @DisplayName("Tarayıcıyı Aç ve Giriş Yap")
    public void OpenDriverAndLogin() {
        LogTest.info("Kullanıcı adı Input aranıyor");
        WebElement usernameInput = BaseStep.findElementXpathWithWait("//*[@id=\"login_email\"]", TimeOut.SHORT.value);
        BaseStep.clearAndType(usernameInput, "ardakocaoglu44@gmail.com", "Kullanıcı Adı");
        LogTest.info("Kullanıcı adı gönderildi");
        WebElement passwordInput = BaseStep.findElementXpathWithWait("//*[@id=\"login_password\"]", TimeOut.SHORT.value);
        LogTest.info("Parola Inputu bulunuyor");
        BaseStep.clearAndType(passwordInput, "Arda.241144007", "Şifre");
        LogTest.info("Parola gönderildi");
        LogTest.info("Giriş Yap butonu bulunuyor");
        WebElement loginClickButton = BaseStep.findElementXpathWithWait("(//button[@type='submit'])[1]", TimeOut.SHORT.value);
        BaseStep.clickElement(loginClickButton, "Giriş yap butonuna tıklandı");
    }

    @Test
    @Order(2)
    @DisplayName("Fatura Yönetimi Sayfası Test Ediliyor")
    public void PageInvoiceManagement() {
        BaseStep.waitSeconds(5);
        LogTest.info("Fatura Yönetimi dropdown aranıyor");
        WebElement InvoiceManagementDropdown = BaseStep.findElementXpathWithWait("//span[@class='ant-menu-title-content']/a[@href='/invoice']", TimeOut.SHORT.value);
        LogTest.info("Fatura Yönetimi butonuna tıklanıyor");
        BaseStep.clickElement(InvoiceManagementDropdown, "Fatura Yönetimi butonuna tıklandı");
    }

    @Test
    @Order(3)
    @DisplayName("Fatura Ekleme Butonuna Tıklanıyor")
    public void ClickAddInvoiceButton() {
        BaseStep.waitSeconds(3);
        LogTest.info("Fatura ekleme butonu aranıyor");
        WebElement addInvoiceButton = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div/div[2]/div/div[1]/div[3]/button", TimeOut.SHORT.value);
        LogTest.info("Fatura ekleme butonuna tıklanıyor");
        BaseStep.clickElement(addInvoiceButton, "Fatura ekleme butonuna tıklandı");
    }

    @Test
    @Order(4)
    @DisplayName("Fatura Dökümanı Yükleniyor")
    public void UploadInvoiceFile() {
        BaseStep.waitSeconds(3);
        LogTest.info("Dosya yükleme alanı aranıyor");
        BaseStep.uploadFileFromProject("/html/body/div[2]/div[2]/div/div[1]/div/div[2]/form/div/div[2]/div[2]/span/div[1]/span/input", "DÖKÜMAN.pdf", "Fatura dökümanı yüklendi");
        BaseStep.waitSeconds(5);
    }
}
