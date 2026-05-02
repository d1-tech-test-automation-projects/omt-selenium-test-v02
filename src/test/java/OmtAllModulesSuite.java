import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * DHR projesinin tüm modül testlerini sıralı şekilde çalıştıran Suite sınıfı.
 *
 * Çalıştırma:
 *   mvn test -Dtest=DhrAllModulesSuite
 *
 * Yeni bir modül test sınıfı eklediğinizde:
 *   1. Yeni sınıfı yazın (örn: DhrLeaveModuleTest.java)
 *   2. Aşağıdaki @SelectClasses listesine ekleyin
 *   3. Suite'i çalıştırdığınızda otomatik olarak sıralı çalışır
 *
 * Sıralama kuralı:
 *   - Sınıflar @SelectClasses içindeki yazım sırasına göre çalışır
 *   - Her sınıfın içindeki @Test metodları @Order annotation'ına göre sıralı çalışır
 */
@Suite
@SuiteDisplayName("OMT All Modules Test Suite")
@SelectClasses({

        DashboardPanelsTest.class,
        HRDashboardTest.class,
        ProcurementDashboardTest.class,
        PersonnelManagementTest.class,
        CompanyManagementTest.class,
        InvoiceManagementTest.class,
        

})
@ConfigurationParameter(
        key = "junit.jupiter.testclass.order.default",
        value = "org.junit.jupiter.api.ClassOrderer$OrderAnnotation"
)
public class OmtAllModulesSuite {
    // Suite sınıfı boş kalır - sadece annotation'lar konfigürasyonu sağlar
}
