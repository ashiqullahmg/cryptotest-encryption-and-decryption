package tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.auideas.configuration.LoadProperties;
import com.auideas.pages.DecryptionPage;
import com.auideas.pages.EncryptionPage;
import com.auideas.pages.HomePage;

public class EncDecTest extends TestBase{
    HomePage homePage;
    EncryptionPage encryptionPage;
    DecryptionPage decryptionPage;
    public static String outputText;
    public static List<String> outputs = new ArrayList<>();

    @Test(priority = 0)
    public void checkThatEncryptionnWorks(){
        //System.out.println("Plain text before encryption: "+outputText);
        homePage = new HomePage(webDriver);
        encryptionPage = new EncryptionPage(webDriver);
        homePage.login(LoadProperties.env.getProperty("user_email"), LoadProperties.env.getProperty("user_password"));
        homePage.openService("encryption");
        assertIsEqual(encryptionPage.encryptionPageId, "File & Text Encryption");
        outputs = encryptionPage.encryption(LoadProperties.env.getProperty("plainText"), LoadProperties.env.getProperty("encryptType"));
        // outputText = encryptionPage.getOuputText();
        //System.out.println("Key: "+outputs.get(0)+"\n Key Save Toast MSG: "+outputs.get(1)+"\n Encryption Toast MSG: "+outputs.get(2)+"\n Output Text: "+outputs.get(3));
        assertIsEqualByStringOnly(outputs.get(1), "Key is Saved Successfully");
        assertIsEqualByStringOnly(outputs.get(2), "data encrypted successfully");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void checkThatDecryptionnWorks(){

       // System.out.println(outputs);
        //System.out.println("Key: "+outputs.get(0)+"\n Key Save Toast MSG: "+outputs.get(1)+"\n Encryption Toast MSG: "+outputs.get(2)+"\n Output Text: "+outputs.get(3));
        homePage = new HomePage(webDriver);
        decryptionPage = new DecryptionPage(webDriver);
        homePage.openService("decryption");
        assertIsEqual(decryptionPage.decryptionPageId, "File & Text Decryption");
        outputText = decryptionPage.decryption(outputs.get(3), LoadProperties.env.getProperty("encryptType"), outputs.get(0));
        //System.out.println("Plain text after decryption: "+outputText);
        assertIsEqualByStringOnly(outputText, LoadProperties.env.getProperty("plainText"));
        softAssert.assertAll();
    }


}
