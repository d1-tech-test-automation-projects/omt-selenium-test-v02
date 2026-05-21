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
    @Order(21)
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
    @Order(22)
    @DisplayName("Fatura Yönetimi Sayfası Test Ediliyor")
    public void PageInvoiceManagement() {
        BaseStep.waitSeconds(5);
        LogTest.info("Fatura Yönetimi dropdown aranıyor");
        WebElement InvoiceManagementDropdown = BaseStep.findElementXpathWithWait("//span[@class='ant-menu-title-content']/a[@href='/invoice']", TimeOut.LONG.value);
        LogTest.info("Fatura Yönetimi butonuna tıklanıyor");
        BaseStep.clickElement(InvoiceManagementDropdown, "Fatura Yönetimi butonuna tıklandı");
    }

    @Test
    @Order(23)
    @DisplayName("Fatura Ekleme Butonuna Tıklanıyor")
    public void ClickAddInvoiceButton() {
        BaseStep.waitSeconds(3);
        LogTest.info("Fatura ekleme butonu aranıyor");
        WebElement addInvoiceButton = BaseStep.findElementXpathWithWait("//*[@id=\"root\"]/div/div/div/main/div/div/div/div[2]/div/div[1]/div[3]/button", TimeOut.LONG.value);
        LogTest.info("Fatura ekleme butonuna tıklanıyor");
        BaseStep.clickElement(addInvoiceButton, "Fatura ekleme butonuna tıklandı");
    }

    @Test
    @Order(24)
    @DisplayName("Fatura Dökümanı Yükleniyor")
    public void UploadInvoiceFile() {
        BaseStep.waitSeconds(3);
        LogTest.info("Dosya yükleme alanı aranıyor");
        BaseStep.uploadFileFromProject("/html/body/div[2]/div[2]/div/div[1]/div/div[2]/form/div/div[2]/div[2]/span/div[1]/span/input", "DÖKÜMAN.pdf", "Fatura dökümanı yüklendi");
        BaseStep.waitSeconds(5);
    }

    @Test
    @Order(25)
    @DisplayName("Sonraki Adıma Geçiliyor")
    public void ClickNextStepButton() {
        LogTest.info("Sonraki adım butonu aranıyor");
        WebElement nextStepButton = BaseStep.findElementXpathWithWait("/html/body/div[2]/div[2]/div/div[1]/div/div[2]/form/div/div[2]/div[2]/div/button", TimeOut.LONG.value);
        LogTest.info("Sonraki adım butonuna tıklanıyor");
        BaseStep.clickElement(nextStepButton, "Sonraki adım butonuna tıklandı");
        BaseStep.waitSeconds(3);
    }

    @Test
    @Order(26)
    @DisplayName("Fatura Bilgileri Dolduruluyor")
    public void FillInvoiceDetails() {
        LogTest.info("Fatura bilgileri formu dolduruluyor");

        // Tedarikçi Şirketi Seçimi (Arayarak)
        WebElement supplierCompany = BaseStep.findElementXpathWithWait("//*[@id='vendorCompanyId']", TimeOut.LONG.value);
        BaseStep.clickElement(supplierCompany, "Tedarikçi dropdown tıklandı");
        BaseStep.clearAndType(supplierCompany, "Alfa Yazılım Geliştirme ve Danışmanlık", "Tedarikçi adı yazıldı");
        BaseStep.waitSeconds(2);
        WebElement supplierOption = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'Alfa Yazılım Geliştirme ve Danışmanlık')]", TimeOut.LONG.value);
        BaseStep.clickElement(supplierOption, "Tedarikçi seçildi");

        // Alıcı Şirketi Seçimi (Arayarak)
        WebElement receiverCompany = BaseStep.findElementXpathWithWait("//*[@id='buyerCompanyId']", TimeOut.LONG.value);
        BaseStep.clickElement(receiverCompany, "Alıcı dropdown tıklandı");
        BaseStep.clearAndType(receiverCompany, "NovaCore Teknoloji Grubu", "Alıcı adı yazıldı");
        BaseStep.waitSeconds(2);
        WebElement receiverOption = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'NovaCore Teknoloji Grubu')]", TimeOut.LONG.value);
        BaseStep.clickElement(receiverOption, "Alıcı seçildi");

        // Kontrat Seçimi
        WebElement contractDropdown = BaseStep.findElementXpathWithWait("//*[@id='contractId']/ancestor::div[contains(@class, 'ant-select-selector')]", TimeOut.LONG.value);
        BaseStep.clickElement(contractDropdown, "Kontrat dropdown tıklandı");
        BaseStep.waitSeconds(2);
        WebElement contractOption = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'MST-2026-0001')]", TimeOut.LONG.value);
        BaseStep.clickElement(contractOption, "Kontrat seçildi");

        // Satın Alma Numarası (PO)
        WebElement poNumberInput = BaseStep.findElementXpathWithWait("//*[@id='purchaseOrderNumber']", TimeOut.LONG.value);
        BaseStep.clearAndType(poNumberInput, "PO123456", "PO Numarası girildi");

        // İlgili Kişi Seçimi (Arayarak)
        WebElement relatedPersonDropdown = BaseStep.findElementXpathWithWait("//*[@id='relatedPersonId']", TimeOut.LONG.value);
        BaseStep.clickElement(relatedPersonDropdown, "İlgili Kişi dropdown tıklandı");
        BaseStep.clearAndType(relatedPersonDropdown, "Arda Kocaoğlu", "İlgili kişi aratıldı");
        BaseStep.waitSeconds(2);
        WebElement relatedPersonOption = BaseStep.findElementXpathWithWait("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'Arda Kocaoğlu')]", TimeOut.LONG.value);
        BaseStep.clickElement(relatedPersonOption, "İlgili kişi seçildi");

        // Açıklama
        WebElement descriptionTextarea = BaseStep.findElementXpathWithWait("//*[@id='description']", TimeOut.LONG.value);
        BaseStep.clearAndType(descriptionTextarea, "Test Faturası Açıklaması", "Açıklama girildi");

        // Fatura Kalemleri - Açıklama
        WebElement itemDescriptionInput = BaseStep.findElementXpathWithWait("/html/body/div[2]/div[2]/div/div[1]/div/div[2]/form/div/div[2]/div[2]/div[8]/div/div/div/div[1]/div/table/tbody/tr[2]/td[2]/input", TimeOut.LONG.value);
        BaseStep.clearAndType(itemDescriptionInput, "Test Ürünü", "Kalem açıklaması girildi");

        // Fatura Kalemleri - Miktar
        WebElement quantityInput = BaseStep.findElementXpathWithWait("/html/body/div[2]/div[2]/div/div[1]/div/div[2]/form/div/div[2]/div[2]/div[8]/div/div/div/div[1]/div/table/tbody/tr[2]/td[3]/div/div[2]/input", TimeOut.LONG.value);
        BaseStep.clearAndType(quantityInput, "10", "Miktar girildi");

        // Fatura Kalemleri - Birim Fiyat
        WebElement unitPriceInput = BaseStep.findElementXpathWithWait("/html/body/div[2]/div[2]/div/div[1]/div/div[2]/form/div/div[2]/div[2]/div[8]/div/div/div/div[1]/div/table/tbody/tr[2]/td[5]/div/div[2]/input", TimeOut.LONG.value);
        BaseStep.clearAndType(unitPriceInput, "100", "Birim fiyat girildi");

        // İleri: Onay Akışı Butonu
        WebElement nextApprovalButton = BaseStep.findElementXpathWithWait("/html/body/div[2]/div[2]/div/div[1]/div/div[2]/form/div/div[2]/div[2]/div[11]/div/div[2]/button", TimeOut.LONG.value);
        BaseStep.clickElement(nextApprovalButton, "İleri: Onay Akışı butonuna tıklandı");
        
        BaseStep.waitSeconds(3);
    }

    @Test
    @Order(27)
    @DisplayName("Fatura Onaya Gönderiliyor")
    public void SubmitForApproval() {
        LogTest.info("Onaya Gönder butonu aranıyor");
        WebElement submitButton = BaseStep.findElementXpathWithWait("/html/body/div[2]/div[2]/div/div[1]/div/div[2]/form/div/div[2]/div[2]/div[5]/div/div[3]/button", TimeOut.LONG.value);
        LogTest.info("Onaya Gönder butonuna tıklanıyor");
        BaseStep.clickElement(submitButton, "Onaya Gönder butonuna tıklandı");
        BaseStep.waitSeconds(5);
    }
}
