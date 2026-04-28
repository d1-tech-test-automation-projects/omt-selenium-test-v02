import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonnelManagementTest extends BaseStep {

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
    @DisplayName("Çalışan Profilini Düzenleme Testi")
    public void TestEditEmployeeProfile() {
        BaseStep.waitSeconds(3);
        LogTest.info("Personel Yönetimi dropdown aranıyor");
        WebElement PersonnelManagementDropdown = BaseStep.findElementXpathWithWait("//span[@class='ant-menu-title-content']/a[@href='/employee']", TimeOut.SHORT.value);
        BaseStep.clickElement(PersonnelManagementDropdown, "Personel Yönetimi butonuna tıklandı");

        BaseStep.waitSeconds(3);
        LogTest.info("Çalışan arama alanına 'Ayşe' yazılıyor");
        WebElement employeeSearchInput = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div/div[1]/div[1]/div[1]/span/input", TimeOut.SHORT.value);
        BaseStep.clearAndType(employeeSearchInput, "Ayşe", "Çalışan arama alanına 'Ayşe' değeri yazıldı");
        BaseStep.waitSeconds(3);

        LogTest.info("Ayşe'nin çalışan kartına tıklanıyor...");
        WebElement ayseCard = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div/div[2]/div/div/div", TimeOut.SHORT.value);
        BaseStep.clickElement(ayseCard, "Ayşe'nin kartına tıklandı");
        BaseStep.waitSeconds(2);

        LogTest.info("Çalışanı Düzenle butonuna tıklanıyor...");
        WebElement editBtn = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div[1]/div/div/div/div[2]/div/div[2]/div/div[2]/button", TimeOut.SHORT.value);
        BaseStep.clickElement(editBtn, "Çalışanı Düzenle butonuna tıklandı");
        BaseStep.waitSeconds(2);

        LogTest.info("Personel numarasına '0' ekleniyor...");
        WebElement empNumInput = BaseStep.findElementXpathWithWait("//*[@id='employeeNumber']", TimeOut.SHORT.value);
        String currentNumber = empNumInput.getAttribute("value");
        BaseStep.clearAndType(empNumInput, currentNumber + "0", "Personel numarasının sonuna 0 eklendi");
        BaseStep.waitSeconds(1);

        LogTest.info("Güncelle butonuna basılıyor...");
        WebElement updateBtn = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div[3]/button[2]", TimeOut.MEDIUM.value);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateBtn);
        BaseStep.waitSeconds(1);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", updateBtn);
        BaseStep.waitSeconds(3);

        LogTest.info("Personel Yönetimi modülüne geri dönülüyor...");
        driver.get("https://test.d1-omt.com/employee");
        BaseStep.waitSeconds(3);
    }

    @Test
    @Order(3)
    @DisplayName("Çalışan Durumu Filtreleri Testi")
    public void TestPersonnelStatusFilters() {
        String dropdownTrigger = "//*[@id=\"root\"]/div/div/div/main/div/div/div/div[1]/div[1]/div[2]/div/div/span/span[2]";
        
        LogTest.info("Çalışan Durumu dropdown açılıyor");
        WebElement trigger1 = BaseStep.findElementXpathWithWait(dropdownTrigger, TimeOut.SHORT.value);
        BaseStep.clickElement(trigger1, "Dropdown açıldı");
        LogTest.info("'Aktif ve Pasif' seçiliyor");
        WebElement optionAktifPasif = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Aktif ve Pasif']", TimeOut.SHORT.value);
        BaseStep.clickElement(optionAktifPasif, "Aktif ve Pasif seçildi");
        BaseStep.waitSeconds(3);

        WebElement trigger2 = BaseStep.findElementXpathWithWait(dropdownTrigger, TimeOut.SHORT.value);
        BaseStep.clickElement(trigger2, "Dropdown açıldı");
        LogTest.info("'Aktif Çalışanlar' seçiliyor");
        WebElement optionAktif = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Aktif Çalışanlar']", TimeOut.SHORT.value);
        BaseStep.clickElement(optionAktif, "Aktif Çalışanlar seçildi");
        BaseStep.waitSeconds(3);

        WebElement trigger3 = BaseStep.findElementXpathWithWait(dropdownTrigger, TimeOut.SHORT.value);
        BaseStep.clickElement(trigger3, "Dropdown açıldı");
        LogTest.info("'Pasif Çalışanlar' seçiliyor");
        WebElement optionPasif = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Pasif Çalışanlar']", TimeOut.SHORT.value);
        BaseStep.clickElement(optionPasif, "Pasif Çalışanlar seçildi");
        BaseStep.waitSeconds(3);

        WebElement trigger4 = BaseStep.findElementXpathWithWait(dropdownTrigger, TimeOut.SHORT.value);
        BaseStep.clickElement(trigger4, "Dropdown açıldı");
        LogTest.info("'İşten Çıkarılanlar' seçiliyor");
        WebElement optionIstenCikarilan = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='İşten Çıkarılanlar']", TimeOut.SHORT.value);
        BaseStep.clickElement(optionIstenCikarilan, "İşten Çıkarılanlar seçildi");
        BaseStep.waitSeconds(3);

        WebElement trigger5 = BaseStep.findElementXpathWithWait(dropdownTrigger, TimeOut.SHORT.value);
        BaseStep.clickElement(trigger5, "Dropdown açıldı");
        LogTest.info("'Tüm Çalışanlar' seçiliyor");
        WebElement optionTum = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Tüm Çalışanlar']", TimeOut.SHORT.value);
        BaseStep.clickElement(optionTum, "Tüm Çalışanlar seçildi");
        BaseStep.waitSeconds(3);
    }

    @Test
    @Order(4)
    @DisplayName("Şirket Filtreleri Testi")
    public void TestCompanyFilters() {
        String[] sirketler = {"Tüm Şirketler", "Ana Şirket (Ana Şirket)", "Aracı Şirket 1 (Aracı Şirket)"};
        String wrapperXpath = "//div[contains(@class, 'employee-company-filter')]//div[contains(@class, 'ant-select-selector')]";
        String inputXpath = "//div[contains(@class, 'employee-company-filter')]//input";

        for (String sirket : sirketler) {
            LogTest.info(">> Sıradaki şirket için işlem: " + sirket);
            WebElement companyWrapper = BaseStep.findElementXpathWithWait(wrapperXpath, TimeOut.SHORT.value);
            BaseStep.clickElement(companyWrapper, "Dropdown kutusu aktif edildi");
            BaseStep.waitSeconds(1);

            WebElement companyInput = BaseStep.findElementXpathWithWait(inputXpath, TimeOut.SHORT.value);
            companyInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
            companyInput.sendKeys(org.openqa.selenium.Keys.DELETE);
            companyInput.sendKeys(sirket);
            BaseStep.waitSeconds(1);
            companyInput.sendKeys(org.openqa.selenium.Keys.ENTER);
            LogTest.info(sirket + " ENTER tuşu ile başarılı bir şekilde seçildi!");
            BaseStep.waitSeconds(2);
        }
    }

    @Test
    @Order(5)
    @DisplayName("İşten Çıkarma Talebi Oluşturma Testi")
    public void TestDismissalProcess() {
        LogTest.info("İşten Çıkarma İşlemleri butonuna tıklanıyor...");
        WebElement dismissalOpsBtn = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div/div[1]/div[2]/div[1]/button", TimeOut.SHORT.value);
        BaseStep.clickElement(dismissalOpsBtn, "İşten Çıkarma İşlemleri butonuna tıklandı");
        BaseStep.waitSeconds(2);

        LogTest.info("Yeni Talep Oluştur butonuna tıklanıyor...");
        WebElement newRequestBtn = BaseStep.findElementXpathWithWait("//div[contains(@id, 'panel-requests')]//button[contains(., 'Yeni Talep')] | //*[@id='rc-tabs-1-panel-requests']//button", TimeOut.MEDIUM.value);
        BaseStep.clickElement(newRequestBtn, "Yeni Talep Oluştur butonuna tıklandı");
        BaseStep.waitSeconds(2);

        LogTest.info("Çalışan seçiliyor: Ayşe");
        WebElement employeeInput = BaseStep.findElementXpathWithWait("//input[@id='employeeId']", TimeOut.SHORT.value);
        BaseStep.clickElement(employeeInput, "Çalışan kutusu tıklandı");
        employeeInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        employeeInput.sendKeys(org.openqa.selenium.Keys.DELETE);
        employeeInput.sendKeys("Ayşe");
        BaseStep.waitSeconds(2);

        try {
            WebElement employeeOption = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and contains(., 'Ayşe')]", TimeOut.SHORT.value);
            BaseStep.clickElement(employeeOption, "Ayşe seçeneğine tıklandı");
        } catch (Exception e) {
            LogTest.info("Tıklama başarısız oldu, klavye ile seçmeyi deniyoruz...");
            employeeInput.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN);
            BaseStep.waitSeconds(1);
            employeeInput.sendKeys(org.openqa.selenium.Keys.ENTER);
        }
        
        BaseStep.waitSeconds(1);

        LogTest.info("İşten Çıkarma Tarihi seçiliyor: Bugün");
        WebElement terminationDateInput = BaseStep.findElementXpathWithWait("//input[@id='terminationDate']", TimeOut.SHORT.value);
        BaseStep.clickElement(terminationDateInput, "Tarih kutusu tıklandı");
        BaseStep.waitSeconds(1);

        WebElement todayBtn = BaseStep.findElementXpathWithWait("//a[contains(@class, 'ant-picker-today-btn')] | //a[text()='Bugün']", TimeOut.SHORT.value);
        BaseStep.clickElement(todayBtn, "Takvimden 'Bugün' butonuna tıklandı");
        BaseStep.waitSeconds(1);

        LogTest.info("İşten Çıkarma Sebebi seçiliyor: İşten Çıkarma");
        WebElement terminationReasonBox = BaseStep.findElementXpathWithWait("//*[@id='terminationReason']/ancestor::div[contains(@class, 'ant-select-selector')]", TimeOut.SHORT.value);
        BaseStep.clickElement(terminationReasonBox, "İşten Çıkarma Sebebi kutusu açıldı");
        BaseStep.waitSeconds(1);

        WebElement reasonOption = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='İşten Çıkarma']", TimeOut.SHORT.value);
        BaseStep.clickElement(reasonOption, "Sebep: İşten Çıkarma seçildi");
        BaseStep.waitSeconds(1);

        LogTest.info("Onay butonuna tıklanıyor...");
        WebElement submitBtn = BaseStep.findElementXpathWithWait("/html/body/div[2]/div[2]/div/div[1]/div/div[3]/button[2]", TimeOut.SHORT.value);
        BaseStep.clickElement(submitBtn, "Gönder butonu tıklandı");
        BaseStep.waitSeconds(3);

        LogTest.info("Personel Yönetimi modülüne geri dönülüyor...");
        driver.get("https://test.d1-omt.com/employee");
        BaseStep.waitSeconds(3);
    }

    @Test
    @Order(6)
    @DisplayName("Yeni Çalışan Ekleme Testi")
    public void TestAddNewEmployee() {
        LogTest.info("Çalışan Ekle butonuna tıklanıyor...");
        WebElement addEmployeeBtn = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div/div[1]/div[2]/div[2]/button", TimeOut.SHORT.value);
        BaseStep.clickElement(addEmployeeBtn, "Çalışan Ekle butonuna tıklandı");
        BaseStep.waitSeconds(2);

        LogTest.info("Temel bilgiler dolduruluyor...");
        WebElement employeeNumberInput = BaseStep.findElementXpathWithWait("//*[@id='employeeNumber']", TimeOut.SHORT.value);
        BaseStep.clearAndType(employeeNumberInput, "EMP-576766545653440", "Çalışan Numarası");

        WebElement firstNameInput = BaseStep.findElementXpathWithWait("//*[@id='firstName']", TimeOut.SHORT.value);
        BaseStep.clearAndType(firstNameInput, "Arda", "Ad alanı");

        WebElement lastNameInput = BaseStep.findElementXpathWithWait("//*[@id='lastName']", TimeOut.SHORT.value);
        BaseStep.clearAndType(lastNameInput, "Kocaoğlu", "Soyad alanı");

        WebElement emailInput = BaseStep.findElementXpathWithWait("//*[@id='email']", TimeOut.SHORT.value);
        BaseStep.clearAndType(emailInput, "arda.test@d1tech.com", "E-posta alanı");

        WebElement phoneInput = BaseStep.findElementXpathWithWait("//*[@id='phoneNumber']", TimeOut.SHORT.value);
        BaseStep.clearAndType(phoneInput, "5551234567", "Telefon numarası");

        WebElement genderBox = BaseStep.findElementXpathWithWait("//input[@id='gender']/ancestor::div[contains(@class, 'ant-select-selector')]", TimeOut.SHORT.value);
        BaseStep.clickElement(genderBox, "Cinsiyet kutusu açıldı");
        BaseStep.waitSeconds(1);
        WebElement genderOption = BaseStep.findElementXpathWithWait("(//div[contains(@class, 'ant-select-dropdown') and not(contains(@style, 'display: none'))])[last()]//div[contains(@class, 'ant-select-item-option') and @title='Erkek']", TimeOut.SHORT.value);
        BaseStep.clickElement(genderOption, "Cinsiyet: Erkek seçildi");

        WebElement companyTypeBox = BaseStep.findElementXpathWithWait("//input[@id='companyType']/ancestor::div[contains(@class, 'ant-select-selector')]", TimeOut.SHORT.value);
        BaseStep.clickElement(companyTypeBox, "Şirket Tipi kutusu açıldı");
        BaseStep.waitSeconds(1);
        WebElement companyTypeOption = BaseStep.findElementXpathWithWait("(//div[contains(@class, 'ant-select-dropdown') and not(contains(@style, 'display: none'))])[last()]//div[contains(@class, 'ant-select-item-option') and @title='Ana Şirket']", TimeOut.SHORT.value);
        BaseStep.clickElement(companyTypeOption, "Şirket Tipi: Ana Şirket seçildi");

        WebElement companyIdBox = BaseStep.findElementXpathWithWait("//input[@id='companyId']/ancestor::div[contains(@class, 'ant-select-selector')]", TimeOut.SHORT.value);
        BaseStep.clickElement(companyIdBox, "Şirket ID kutusu açıldı");
        BaseStep.waitSeconds(1);
        WebElement companyIdOption = BaseStep.findElementXpathWithWait("(//div[contains(@class, 'ant-select-dropdown') and not(contains(@style, 'display: none'))])[last()]//div[contains(@class, 'ant-select-item-option') and @title='Ana Şirket']", TimeOut.SHORT.value);
        BaseStep.clickElement(companyIdOption, "Şirket: Ana Şirket seçildi");
        BaseStep.waitSeconds(1);

        LogTest.info("Yasal Bilgiler sekmesine geçiliyor...");
        WebElement legalTab = BaseStep.findElementXpathWithWait("//div[@role='tab' and contains(., 'Yasal Bilgiler')]", TimeOut.SHORT.value);
        BaseStep.clickElement(legalTab, "Yasal Bilgiler sekmesine tıklandı");
        BaseStep.waitSeconds(2);
        WebElement identityNumberInput = BaseStep.findElementXpathWithWait("//input[@id='identityNumber']", TimeOut.SHORT.value);
        BaseStep.clearAndType(identityNumberInput, "12345678901", "TC Kimlik Numarası");

        LogTest.info("İş Bilgileri sekmesine geçiliyor...");
        WebElement workTab = BaseStep.findElementXpathWithWait("//div[@role='tab' and contains(., 'İş Bilgileri')]", TimeOut.SHORT.value);
        BaseStep.clickElement(workTab, "İş Bilgileri sekmesine tıklandı");
        BaseStep.waitSeconds(1);
        WebElement startDateInput = BaseStep.findElementXpathWithWait("//*[@id='startDate']", TimeOut.SHORT.value);
        BaseStep.clearAndType(startDateInput, "07.04.2026", "İşe Başlama Tarihi girildi");
        startDateInput.sendKeys(org.openqa.selenium.Keys.ENTER);

        BaseStep.waitSeconds(1);
        BaseStep.waitSeconds(4);
        LogTest.info("Kaydet butonuna basılıyor...");
        WebElement saveButton = BaseStep.findElementXpathWithWait("//button[contains(., 'Kaydet')]", TimeOut.SHORT.value);
        BaseStep.clickElement(saveButton, "Kaydet butonuna tıklandı");
        LogTest.info("Çalışan Kaydı tamamlandı!");
    }
}
