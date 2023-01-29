import com.auto.net.StarterApplication;
import com.auto.net.config.AppProps;
import com.auto.net.config.GuiProps;
import com.auto.net.helpers.CustomLogFilter;
import com.auto.net.stepdefinations.common.StepData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@Slf4j
@SpringBootTest(classes = StarterApplication.class)
@CucumberContextConfiguration
public class CucumberSpringContext {
    @Autowired
    private StepData stepData;

    @Autowired
    private AppProps appProps;

    @Autowired
    private GuiProps guiProps;

    @Before
    public void setUp(Scenario scenario) {
        stepData.setScenario(scenario);
        stepData.setCustomLogFilter(new CustomLogFilter());

        if (scenario.getSourceTagNames().contains("@GUI") && stepData.getDriver() == null) {
            log.info("------------ Rest Assured setup for executing GUI tests scenarios ------------");
            ChromeOptions options = new ChromeOptions();
            options.setBrowserVersion("99.0.4844.51-1");
            options.addArguments("no-sandbox", "--incognito", "--window-size=1920,1080",
                    "--ignore-certificate-errors", "--start-maximized", "--disable-gpu",
                    "--allow-running-insecure-content", "--disable-extensions", "--disable-dev-shm-usage", "--headless");
            WebDriverManager.chromedriver().setup();
            stepData.setDriver(new ChromeDriver(options));
            stepData.getDriver().manage().deleteAllCookies();
            stepData.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(guiProps.getImplicitWait()));
            stepData.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(guiProps.getPageLoadTimeout()));
            stepData.getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(guiProps.getScriptTimeout()));
            stepData.getDriver().navigate().to(appProps.getAppUrl());
        } else {
            log.info("------------ Rest Assured setup for executing API tests scenarios ------------");
            RestAssured.useRelaxedHTTPSValidation();
            stepData.setRequestSpecification(RestAssured.with());
            stepData.getRequestSpecification().baseUri(appProps.getBaseUri());
        }
    }

    @After
    public void tearDown() {
        stepData.logRequestAndResponse();
        if (stepData.getDriver() != null) {
            stepData.getDriver().quit();
            stepData.setDriver(null);
        }
    }
}
//    @PreDestroy
//    public static void uploadResultToS3() {
//        try {
//            String bucketName = "autonet-cicd-reports";
//            String fileName = "Cucumber.json";
//            String filePath = System.getProperty("user.dir") + "/target/cucumber-reports/Cucumber.json";
//            AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
//                    System.getenv("ACCESS_KEY"),
//                    System.getenv("SECRET_KEY"));
//
//            Region region = Region.EU_WEST_2;
//            S3Client client = S3Client.builder()
//                    .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
//                    .region(region)
//                    .build();
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//
//            PutObjectRequest request = PutObjectRequest.builder()
//                    .bucket(bucketName)
//                    .key((System.getenv("BUILD_NUMBER") != null ? System.getenv("BUILD_NUMBER") : "BUILD" + dateFormat.format(new Date()))
//                            + "/"
//                            + (System.getenv("TEST_NAME") != null ? System.getenv("TEST_NAME") : "TEST" + dateFormat.format(new Date()))
//                            + "/"
//                            + fileName)
//                    .build();
//
//            client.putObject(request, RequestBody.fromFile(new File(filePath)));
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}
