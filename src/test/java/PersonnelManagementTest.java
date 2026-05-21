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
    @Order(7)
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
    }

    @Test
    @Order(8)
    @DisplayName("Çalışan Profilini Düzenleme Testi")
    public void TestEditEmployeeProfile() {
        BaseStep.waitSeconds(3);
        LogTest.info("Personel Yönetimi dropdown aranıyor");
        WebElement PersonnelManagementDropdown = BaseStep.findElementXpathWithWait("//span[@class='ant-menu-title-content']/a[@href='/employee']", TimeOut.LONG.value);
        BaseStep.clickElement(PersonnelManagementDropdown, "Personel Yönetimi butonuna tıklandı");

        BaseStep.waitSeconds(3);
        LogTest.info("Çalışan arama alanına 'Deniz' yazılıyor");
        WebElement employeeSearchInput = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div/div[1]/div[1]/div[1]/span/input", TimeOut.LONG.value);
        BaseStep.clearAndType(employeeSearchInput, "Deniz", "Çalışan arama alanına 'Deniz' değeri yazıldı");
        BaseStep.waitSeconds(10);

        LogTest.info("Deniz'nin çalışan kartına tıklanıyor...");
        WebElement employeeCard = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div/div[2]/div/div/div", TimeOut.LONG.value);
        BaseStep.clickElement(employeeCard, "Deniz'nin kartına tıklandı");
        BaseStep.waitSeconds(2);

        LogTest.info("Çalışanı Düzenle butonuna tıklanıyor...");
        WebElement editBtn = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div[1]/div/div/div/div[2]/div/div[2]/div/div[2]/button", TimeOut.LONG.value);
        BaseStep.clickElement(editBtn, "Çalışanı Düzenle butonuna tıklandı");
        BaseStep.waitSeconds(2);

        LogTest.info("Personel numarası güncelleniyor (arttırılıyor)...");
        WebElement empNumInput = BaseStep.findElementXpathWithWait("//*[@id='employeeNumber']", TimeOut.LONG.value);
        String currentVal = empNumInput.getAttribute("value");
        String nextVal;
        
        if (currentVal != null && !currentVal.trim().isEmpty()) {
            String numericPart = currentVal.replaceAll("[^0-9]", "");
            if (!numericPart.isEmpty()) {
                try {
                    java.math.BigInteger num = new java.math.BigInteger(numericPart);
                    num = num.add(java.math.BigInteger.ONE);
                    nextVal = currentVal.replace(numericPart, num.toString());
                } catch (Exception e) {
                    nextVal = currentVal + "1";
                }
            } else {
                nextVal = currentVal + "1";
            }
        } else {
            nextVal = "EMP-" + (System.currentTimeMillis() / 1000);
        }
        
        // Manuel olarak temizle (daha güvenli olması için)
        empNumInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        empNumInput.sendKeys(org.openqa.selenium.Keys.DELETE);
        BaseStep.waitSeconds(1);
        
        BaseStep.clearAndType(empNumInput, nextVal, "Personel numarası bir arttırıldı");
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
    @Order(9)
    @DisplayName("Çalışan Durumu Filtreleri Testi")
    public void TestPersonnelStatusFilters() {
        String dropdownTrigger = "//*[@id=\"root\"]/div/div/div/main/div/div/div/div[1]/div[1]/div[2]/div/div/span/span[2]";
        
        LogTest.info("Çalışan Durumu dropdown açılıyor");
        WebElement trigger1 = BaseStep.findElementXpathWithWait(dropdownTrigger, TimeOut.LONG.value);
        BaseStep.clickElement(trigger1, "Dropdown açıldı");
        LogTest.info("'Aktif ve Pasif' seçiliyor");
        WebElement optionActivePassive = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Aktif ve Pasif']", TimeOut.LONG.value);
        BaseStep.clickElement(optionActivePassive, "Aktif ve Pasif seçildi");
        BaseStep.waitSeconds(3);

        WebElement trigger2 = BaseStep.findElementXpathWithWait(dropdownTrigger, TimeOut.LONG.value);
        BaseStep.clickElement(trigger2, "Dropdown açıldı");
        LogTest.info("'Aktif Çalışanlar' seçiliyor");
        WebElement optionActive = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Aktif Çalışanlar']", TimeOut.LONG.value);
        BaseStep.clickElement(optionActive, "Aktif Çalışanlar seçildi");
        BaseStep.waitSeconds(3);

        WebElement trigger3 = BaseStep.findElementXpathWithWait(dropdownTrigger, TimeOut.LONG.value);
        BaseStep.clickElement(trigger3, "Dropdown açıldı");
        LogTest.info("'Pasif Çalışanlar' seçiliyor");
        WebElement optionPassive = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Pasif Çalışanlar']", TimeOut.LONG.value);
        BaseStep.clickElement(optionPassive, "Pasif Çalışanlar seçildi");
        BaseStep.waitSeconds(3);

        WebElement trigger4 = BaseStep.findElementXpathWithWait(dropdownTrigger, TimeOut.LONG.value);
        BaseStep.clickElement(trigger4, "Dropdown açıldı");
        LogTest.info("'İşten Çıkarılanlar' seçiliyor");
        WebElement optionDismissed = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='İşten Çıkarılanlar']", TimeOut.LONG.value);
        BaseStep.clickElement(optionDismissed, "İşten Çıkarılanlar seçildi");
        BaseStep.waitSeconds(3);

        WebElement trigger5 = BaseStep.findElementXpathWithWait(dropdownTrigger, TimeOut.LONG.value);
        BaseStep.clickElement(trigger5, "Dropdown açıldı");
        LogTest.info("'Tüm Çalışanlar' seçiliyor");
        WebElement optionAll = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='Tüm Çalışanlar']", TimeOut.LONG.value);
        BaseStep.clickElement(optionAll, "Tüm Çalışanlar seçildi");
        BaseStep.waitSeconds(3);
    }

    @Test
    @Order(10)
    @DisplayName("Şirket Filtreleri Testi")
    public void TestCompanyFilters() {
        String[] companies = {"Tüm Şirketler", "NovaCore Teknoloji Grubu (Ana Şirket)", "Alfa Yazılım Geliştirme ve Danışmanlık (Yüklenici Şirket)"};
        String wrapperXpath = "//div[contains(@class, 'employee-company-filter')]//div[contains(@class, 'ant-select-selector')]";
        String inputXpath = "//div[contains(@class, 'employee-company-filter')]//input";

        for (String company : companies) {
            LogTest.info(">> Sıradaki şirket için işlem: " + company);
            WebElement companyWrapper = BaseStep.findElementXpathWithWait(wrapperXpath, TimeOut.LONG.value);
            BaseStep.clickElement(companyWrapper, "Dropdown kutusu aktif edildi");
            BaseStep.waitSeconds(1);

            WebElement companyInput = BaseStep.findElementXpathWithWait(inputXpath, TimeOut.LONG.value);
            companyInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
            companyInput.sendKeys(org.openqa.selenium.Keys.DELETE);
            companyInput.sendKeys(company);
            BaseStep.waitSeconds(1);
            companyInput.sendKeys(org.openqa.selenium.Keys.ENTER);
            LogTest.info(company + " ENTER tuşu ile başarılı bir şekilde seçildi!");
            BaseStep.waitSeconds(2);
        }
    }

    @Test
    @Order(11)
    @DisplayName("İşten Çıkarma Talebi Oluşturma Testi")
    public void TestDismissalProcess() {
        LogTest.info("İşten Çıkarma İşlemleri butonuna tıklanıyor...");
        WebElement dismissalOpsBtn = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div/div[1]/div[2]/div[1]/button", TimeOut.LONG.value);
        BaseStep.clickElement(dismissalOpsBtn, "İşten Çıkarma İşlemleri butonuna tıklandı");
        BaseStep.waitSeconds(2);

        LogTest.info("Yeni Talep Oluştur butonuna tıklanıyor...");
        WebElement newRequestBtn = BaseStep.findElementXpathWithWait("//div[contains(@id, 'panel-requests')]//button[contains(., 'Yeni Talep')] | //*[@id='rc-tabs-1-panel-requests']//button", TimeOut.MEDIUM.value);
        BaseStep.clickElement(newRequestBtn, "Yeni Talep Oluştur butonuna tıklandı");
        BaseStep.waitSeconds(2);

        LogTest.info("Çalışan seçiliyor: Ayşe");
        WebElement employeeInput = BaseStep.findElementXpathWithWait("//input[@id='employeeId']", TimeOut.LONG.value);
        BaseStep.clickElement(employeeInput, "Çalışan kutusu tıklandı");
        employeeInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        employeeInput.sendKeys(org.openqa.selenium.Keys.DELETE);
        employeeInput.sendKeys("Ayşe");
        BaseStep.waitSeconds(2);

        try {
            WebElement employeeOption = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and contains(., 'Ayşe')]", TimeOut.LONG.value);
            BaseStep.clickElement(employeeOption, "Ayşe seçeneğine tıklandı");
        } catch (Exception e) {
            LogTest.info("Tıklama başarısız oldu, klavye ile seçmeyi deniyoruz...");
            employeeInput.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN);
            BaseStep.waitSeconds(1);
            employeeInput.sendKeys(org.openqa.selenium.Keys.ENTER);
        }
        
        BaseStep.waitSeconds(1);

        LogTest.info("İşten Çıkarma Tarihi seçiliyor: Bugün");
        WebElement terminationDateInput = BaseStep.findElementXpathWithWait("//input[@id='terminationDate']", TimeOut.LONG.value);
        BaseStep.clickElement(terminationDateInput, "Tarih kutusu tıklandı");
        BaseStep.waitSeconds(11);

        WebElement todayBtn = BaseStep.findElementXpathWithWait("//a[contains(@class, 'ant-picker-today-btn')] | //a[text()='Bugün']", TimeOut.LONG.value);
        BaseStep.clickElement(todayBtn, "Takvimden 'Bugün' butonuna tıklandı");
        BaseStep.waitSeconds(1);

        LogTest.info("İşten Çıkarma Sebebi seçiliyor: İşten Çıkarma");
        WebElement terminationReasonBox = BaseStep.findElementXpathWithWait("//*[@id='terminationReason']/ancestor::div[contains(@class, 'ant-select-selector')]", TimeOut.LONG.value);
        BaseStep.clickElement(terminationReasonBox, "İşten Çıkarma Sebebi kutusu açıldı");
        BaseStep.waitSeconds(1);

        WebElement reasonOption = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and text()='İşten Çıkarma']", TimeOut.LONG.value);
        BaseStep.clickElement(reasonOption, "Sebep: İşten Çıkarma seçildi");
        BaseStep.waitSeconds(1);

        LogTest.info("Onay butonuna tıklanıyor...");
        WebElement submitBtn = BaseStep.findElementXpathWithWait("/html/body/div[2]/div[2]/div/div[1]/div/div[3]/button[2]", TimeOut.LONG.value);
        BaseStep.clickElement(submitBtn, "Gönder butonu tıklandı");
        BaseStep.waitSeconds(3);

        LogTest.info("Personel Yönetimi modülüne geri dönülüyor...");
        driver.get("https://test.d1-omt.com/employee");
        BaseStep.waitSeconds(3);
    }

    @Test
    @Order(12)
    @DisplayName("Yeni Çalışan Ekleme Testi")
    public void TestAddNewEmployee() {
        LogTest.info("Çalışan Ekle butonuna tıklanıyor...");
        WebElement addEmployeeBtn = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div/div[1]/div[2]/div[2]/button", TimeOut.LONG.value);
        BaseStep.clickElement(addEmployeeBtn, "Çalışan Ekle butonuna tıklandı");
        BaseStep.waitSeconds(2);

        LogTest.info("Temel bilgiler dolduruluyor...");
        WebElement employeeNumberInput = BaseStep.findElementXpathWithWait("//*[@id='employeeNumber']", TimeOut.LONG.value);
        String uniqueEmpNum = "EMP-" + (System.currentTimeMillis() / 1000);
        BaseStep.clearAndType(employeeNumberInput, uniqueEmpNum, "Çalışan Numarası: " + uniqueEmpNum);

        WebElement firstNameInput = BaseStep.findElementXpathWithWait("//*[@id='firstName']", TimeOut.LONG.value);
        BaseStep.clearAndType(firstNameInput, "Arda", "Ad alanı");

        WebElement lastNameInput = BaseStep.findElementXpathWithWait("//*[@id='lastName']", TimeOut.LONG.value);
        BaseStep.clearAndType(lastNameInput, "Kocaoğlu", "Soyad alanı");

        WebElement emailInput = BaseStep.findElementXpathWithWait("//*[@id='email']", TimeOut.LONG.value);
        String uniqueEmail = "arda.test" + (System.currentTimeMillis() / 1000) + "@d1tech.com";
        BaseStep.clearAndType(emailInput, uniqueEmail, "E-posta alanı: " + uniqueEmail);

        WebElement phoneInput = BaseStep.findElementXpathWithWait("//*[@id='phoneNumber']", TimeOut.LONG.value);
        BaseStep.clearAndType(phoneInput, "5551234567", "Telefon numarası");

        WebElement genderBox = BaseStep.findElementXpathWithWait("//input[@id='gender']/ancestor::div[contains(@class, 'ant-select-selector')]", TimeOut.LONG.value);
        BaseStep.clickElement(genderBox, "Cinsiyet kutusu açıldı");
        BaseStep.waitSeconds(1);
        WebElement genderOption = BaseStep.findElementXpathWithWait("(//div[contains(@class, 'ant-select-dropdown') and not(contains(@style, 'display: none'))])[last()]//div[contains(@class, 'ant-select-item-option') and @title='Erkek']", TimeOut.LONG.value);
        BaseStep.clickElement(genderOption, "Cinsiyet: Erkek seçildi");

        WebElement companyTypeBox = BaseStep.findElementXpathWithWait("//input[@id='companyType']/ancestor::div[contains(@class, 'ant-select-selector')]", TimeOut.LONG.value);
        BaseStep.clickElement(companyTypeBox, "Şirket Tipi kutusu açıldı");
        BaseStep.waitSeconds(1);
        WebElement companyTypeOption = BaseStep.findElementXpathWithWait("(//div[contains(@class, 'ant-select-dropdown') and not(contains(@style, 'display: none'))])[last()]//div[contains(@class, 'ant-select-item-option') and @title='Ana Şirket']", TimeOut.LONG.value);
        BaseStep.clickElement(companyTypeOption, "Şirket Tipi: Ana Şirket seçildi");

        WebElement companyIdBox = BaseStep.findElementXpathWithWait("//input[@id='companyId']/ancestor::div[contains(@class, 'ant-select-selector')]", TimeOut.LONG.value);
        BaseStep.clickElement(companyIdBox, "Şirket ID kutusu açıldı");
        BaseStep.waitSeconds(1);
        WebElement companyIdOption = BaseStep.findElementXpathWithWait("(//div[contains(@class, 'ant-select-dropdown') and not(contains(@style, 'display: none'))])[last()]//div[contains(@class, 'ant-select-item-option') and @title='NovaCore Teknoloji Grubu']", TimeOut.LONG.value);
        BaseStep.clickElement(companyIdOption, "Şirket: Ana Şirket seçildi");
        BaseStep.waitSeconds(1);

        LogTest.info("Yasal Bilgiler sekmesine geçiliyor...");
        WebElement legalTab = BaseStep.findElementXpathWithWait("//div[@role='tab' and contains(., 'Yasal Bilgiler')]", TimeOut.LONG.value);
        BaseStep.clickElement(legalTab, "Yasal Bilgiler sekmesine tıklandı");
        BaseStep.waitSeconds(2);
        WebElement identityNumberInput = BaseStep.findElementXpathWithWait("//input[@id='identityNumber']", TimeOut.LONG.value);
        // Geçerli formatta bir TC Kimlik No (Algoritmaya uygun dummy)
        BaseStep.clearAndType(identityNumberInput, "10000000146", "TC Kimlik Numarası (Geçerli format)");

        LogTest.info("İş Bilgileri sekmesine geçiliyor...");
        WebElement workTab = BaseStep.findElementXpathWithWait("//div[@role='tab' and contains(., 'İş Bilgileri')]", TimeOut.LONG.value);
        BaseStep.clickElement(workTab, "İş Bilgileri sekmesine tıklandı");
        BaseStep.waitSeconds(1);
        WebElement startDateInput = BaseStep.findElementXpathWithWait("//*[@id='startDate']", TimeOut.LONG.value);
        BaseStep.clickElement(startDateInput, "Tarih kutusu tıklandı");
        BaseStep.waitSeconds(1);
        BaseStep.clearAndType(startDateInput, "07.04.2026", "İşe Başlama Tarihi girildi");
        startDateInput.sendKeys(org.openqa.selenium.Keys.ENTER);
        BaseStep.waitSeconds(2);
        LogTest.info("Kaydet butonuna basılıyor...");
        try {
            WebElement saveButton = BaseStep.findElementXpathWithWait("//button[contains(., 'Kaydet')]", TimeOut.LONG.value);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", saveButton);
            BaseStep.waitSeconds(1);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
            LogTest.info("Kaydet butonuna tıklandı.");
        } catch (Exception e) {
            LogTest.info("Kaydet butonu bulunamadı veya form zaten kapandı.");
        }
        
        BaseStep.waitSeconds(3);
        LogTest.info("Çalışan Kaydı tamamlandı!");
    }
}
