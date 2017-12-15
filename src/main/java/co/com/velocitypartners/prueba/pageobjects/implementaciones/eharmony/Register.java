package co.com.velocitypartners.prueba.pageobjects.implementaciones.eharmony;

import co.com.velocitypartners.prueba.pageobjects.definiciones.eharmony.IRegisterPO;
import co.com.velocitypartners.prueba.util.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 *
 */
public class Register extends PageObject implements IRegisterPO {

    @FindBy(name = "firstName")
    private WebElement inputFirstName;

    @FindBy(name = "postalCode")
    private WebElement inputpostalCode;

    @FindBy(name = "country")
    private WebElement cbcountry;

    @FindBy(name = "gender")
    private List<WebElement> radioGender;

    @FindBy(name = "gender2")
    private List<WebElement> radioSeek;


    @FindBy(css="div#frame1 a")
    private WebElement submitButton;


    @FindBy(name="emailAddress")
    private WebElement inputEmailAddress;


    @FindBy(name="password")
    private WebElement inputPassword;

    @FindBy(id="findEH")
    private WebElement inputFindEH;




    @Override
    public boolean register(String name,int gender,int seek,String zipCode,String country,
                         String user,String pass,String hearAbout ) {

        try {
            basicData(name, gender, seek, zipCode, country);
            loginInfo(user, pass, hearAbout);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }





    }


    private void loginInfo(String user,String pass,String hearAbout){
        inputEmailAddress.sendKeys(user);
        inputPassword.sendKeys(pass);
        Select select=new Select(inputFindEH);

        select.selectByValue(hearAbout);
        //captcha.click();
    }


    private void basicData(String name, int gender, int seek, String zipCode, String country) {
        inputFirstName.sendKeys(name);
        inputpostalCode.sendKeys(zipCode);
        Select select=new Select(cbcountry);
        select.selectByVisibleText(country);

        if(gender==1){
            radioGender.get(0).click();
        }else{
            radioGender.get(1).click();
        }

        if(seek==1){
            radioSeek.get(0).click();
        }else{
            radioSeek.get(1).click();
        }

        submitButton.click();
    }





}
