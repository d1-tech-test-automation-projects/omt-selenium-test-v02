import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyManagementTest extends BaseStep {

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
    @DisplayName("Şirket Yönetimi Sayfası Test Ediliyor")
    public void PageCompanyManagement() {
        BaseStep.waitSeconds(3);
        LogTest.info("Şirket Yönetimi dropdown aranıyor");
        WebElement CompanyManagementDropdown = BaseStep.findElementXpathWithWait("//span[@class='ant-menu-title-content']/a[@href='/company']", TimeOut.SHORT.value);
        LogTest.info("Şirket Yönetimi butonuna tıklanıyor");
        BaseStep.clickElement(CompanyManagementDropdown, "Şirket Yönetimi butonuna tıklandı");
    }

    @Test
    @Order(3)
    @DisplayName("Şirket Detayları ve Düzenleme Akışı Test Ediliyor")
    public void TestCompanyDetailsFlow() {
        BaseStep.waitSeconds(1);
        LogTest.info("Ana şirket için Detaylar butonuna tıklanıyor...");
        WebElement detailsBtn = BaseStep.findElementXpathWithWait("//tr[contains(., 'Ana Şirket')]//button[contains(., 'Detaylar')] | //button[span[text()='Detaylar']] | //*[@id='rc-tabs-12-panel-owner']//button[contains(., 'Detaylar')]", TimeOut.MEDIUM.value);
        
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", detailsBtn);
        BaseStep.waitSeconds(1);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", detailsBtn);

        BaseStep.waitSeconds(2);
        String[] tabs = {"Temel Bilgiler", "İletişim Bilgileri", "Teknopark Bilgileri", "Banka Bilgileri", "Dökümanlar"};
        
        for (String tabName : tabs) {
            LogTest.info(tabName + " sekmesine geçiliyor...");
            WebElement tab = BaseStep.findElementXpathWithWait("//div[@role='tab' and contains(., '" + tabName + "')]", TimeOut.MEDIUM.value);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
            BaseStep.waitSeconds(1);
        }

        LogTest.info("Düzenle butonuna tıklanıyor...");
        WebElement editBtn = BaseStep.findElementXpathWithWait("//button[contains(., 'Düzenle')] | /html/body/div[3]/div[2]/div/div[1]/div/div[3]/button[1]", TimeOut.MEDIUM.value);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", editBtn);
        BaseStep.waitSeconds(2);

        LogTest.info("Kuruluş yılı güncelleniyor...");
        WebElement foundingYearInput = BaseStep.findElementXpathWithWait("//*[@id='foundingYear']", TimeOut.SHORT.value);
        String currentYearStr = foundingYearInput.getAttribute("value");
        int currentYear = Integer.parseInt(currentYearStr);
        String newYear = String.valueOf(currentYear - 1);
        
        BaseStep.clearAndType(foundingYearInput, "", "Temizlik denemesi"); // Önce standart temizlik
        foundingYearInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        foundingYearInput.sendKeys(org.openqa.selenium.Keys.DELETE);
        foundingYearInput.sendKeys(newYear);
        LogTest.info("Kuruluş yılı 1 yıl azaltıldı ve yazıldı: " + newYear);
        BaseStep.waitSeconds(1);

        LogTest.info("Değişikliği Kaydet butonuna tıklanıyor...");
        WebElement saveChangesBtn = BaseStep.findElementXpathWithWait("/html/body/div[3]/div[2]/div/div[1]/div/div/div/div/div/div[2]/form/div[2]/div/div/div/div/div/div[2]/button", TimeOut.SHORT.value);
        BaseStep.clickElement(saveChangesBtn, "Değişikliği Kaydet butonuna tıklandı");
        BaseStep.waitSeconds(2);
    }

    @Test
    @Order(4)
    @DisplayName("Şirket Türleri Sekmelerinde Gezinme Testi")
    public void TestCompanyTypeTabs() {
        BaseStep.waitSeconds(1);
        String[] companyTypes = {"Ana Şirketler", "Aracı Şirketler", "Yüklenici Şirketler"};

        for (String typeName : companyTypes) {
            LogTest.info(typeName + " sekmesine geçiliyor...");
            WebElement tab = BaseStep.findElementXpathWithWait("//div[@role='tab' and contains(., '" + typeName + "')]", TimeOut.MEDIUM.value);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
            BaseStep.waitSeconds(2);
        }
    }

    @Test
    @Order(5)
    @DisplayName("Şirket Durum Filtresi Testi (Aktif/Pasif)")
    public void TestCompanyStatusFilter() {
        BaseStep.waitSeconds(1);
        String filterXpath = "//*[@id='root']/div/div/div/main/div/div/div/div[1]/div[2]/div/div[1]/div/div";
        
        LogTest.info("Durum filtresi açılıyor...");
        WebElement filter = BaseStep.findElementXpathWithWait(filterXpath, TimeOut.SHORT.value);
        BaseStep.clickElement(filter, "Filtre dropdown açıldı");
        BaseStep.waitSeconds(1);

        LogTest.info("'Pasif' seçiliyor...");
        WebElement optionPasif = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Pasif']", TimeOut.SHORT.value);
        BaseStep.clickElement(optionPasif, "Pasif seçildi");
        BaseStep.waitSeconds(3);

        LogTest.info("Durum filtresi tekrar açılıyor...");
        WebElement filterAgain = BaseStep.findElementXpathWithWait(filterXpath, TimeOut.SHORT.value);
        BaseStep.clickElement(filterAgain, "Filtre dropdown tekrar açıldı");
        BaseStep.waitSeconds(1);

        LogTest.info("'Aktif' seçiliyor...");
        WebElement optionAktif = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Aktif']", TimeOut.SHORT.value);
        BaseStep.clickElement(optionAktif, "Aktif seçildi");
        BaseStep.waitSeconds(3);
    }

    @Test
    @Order(6)
    @DisplayName("Teknopark Şubesi Filtresi Testi (Var/Yok)")
    public void TestTeknoparkFilter() {
        BaseStep.waitSeconds(1);
        String filterXpath = "//*[@id='root']/div/div/div/main/div/div/div/div[1]/div[2]/div/div[2]/div/div";
        
        LogTest.info("Teknopark filtresi açılıyor...");
        WebElement filter = BaseStep.findElementXpathWithWait(filterXpath, TimeOut.SHORT.value);
        BaseStep.clickElement(filter, "Teknopark filtresi açıldı");
        BaseStep.waitSeconds(1);

        LogTest.info("'Teknopark Şubesi Yok' seçiliyor...");
        WebElement optionYok = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Teknopark Şubesi Yok']", TimeOut.SHORT.value);
        BaseStep.clickElement(optionYok, "Yok seçildi");
        BaseStep.waitSeconds(3);

        LogTest.info("Teknopark filtresi tekrar açılıyor...");
        WebElement filterAgain = BaseStep.findElementXpathWithWait(filterXpath, TimeOut.SHORT.value);
        BaseStep.clickElement(filterAgain, "Teknopark filtresi tekrar açıldı");
        BaseStep.waitSeconds(1);

        LogTest.info("'Teknopark Şubesi Var' seçiliyor...");
        WebElement optionVar = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Teknopark Şubesi Var']", TimeOut.SHORT.value);
        BaseStep.clickElement(optionVar, "Var seçildi");
        BaseStep.waitSeconds(3);
    }

    @Test
    @Order(7)
    @DisplayName("Filtreleri Temizleme Testi")
    public void TestClearFilters() {
        BaseStep.waitSeconds(2);
        LogTest.info("Filtreleri Temizle butonuna tıklanıyor...");
        WebElement clearBtn = BaseStep.findElementXpathWithWait("//*[@id='root']/div/div/div/main/div/div/div/div[1]/div[2]/div/div[3]/button", TimeOut.SHORT.value);
        BaseStep.clickElement(clearBtn, "Filtreleri Temizle butonuna tıklandı");
        BaseStep.waitSeconds(1);
        LogTest.info("Filtreler temizlendi!");

    }

    @Test
    @Order(8)
    @DisplayName("Yeni Şirket Ekleme ve Yüklenici Şirket Seçimi")
    public void AddContractorCompany() {
        BaseStep.waitSeconds(2);
        LogTest.info("Yeni Şirket Ekle butonuna tıklanıyor...");
        WebElement addCompanyBtn = BaseStep.findElementXpathWithWait("//button[span[contains(text(), 'Yeni Şirket Ekle')]] | //*[@id='root']//button[contains(., 'Yeni Şirket Ekle')]", TimeOut.SHORT.value);
        BaseStep.clickElement(addCompanyBtn, "Yeni Şirket Ekle butonuna tıklandı");

        BaseStep.waitSeconds(2);
        LogTest.info("Yüklenici Şirket kartı seçiliyor...");
        WebElement contractorCard = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-card')]//div[contains(text(), 'Yüklenici Şirket')] | //div[text()='Yüklenici Şirket']/ancestor::div[contains(@class, 'ant-card')] | /html/body/div[2]/div[2]/div/div[1]/div/div[2]/div/div/div[3]/div/div", TimeOut.SHORT.value);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", contractorCard);
        BaseStep.waitSeconds(1);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", contractorCard);
        LogTest.info("Yüklenici Şirket kartına tıklandı");
        BaseStep.waitSeconds(2);
    }
}
