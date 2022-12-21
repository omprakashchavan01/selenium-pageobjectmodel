package org.selenium.pom.tests;

import io.restassured.http.Cookies;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.AccountPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws Exception {
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(new Cookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        checkoutPage.
                clickHereToLoginLink().
                login(user);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }

    @Test()
    public void shouldNotLoginWithAnInvalidPassword(){
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User(username, "demopwd", username + "@askomdch.com");
        new SignUpApi().register(user);

        AccountPage accountPage = new AccountPage(getDriver()).load();
        accountPage.login(user.getUsername(), "invalidPassword");
        Assert.assertEquals(accountPage.getErrorTxt(), "Error: The password you entered for the username "
                + user.getUsername() + " is incorrect. Lost your password?");
    }

    @Test()
    public void shouldNotLoginWithANonExistingUser(){
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User(username, "demopwd", username + "@askomdch.com");

        AccountPage accountPage = new AccountPage(getDriver()).load();
        accountPage.login(user.getUsername(), "demopwd");
        Assert.assertEquals(accountPage.getErrorTxt(), "Error: The username " + user.getUsername() +
                " is not registered on this site." +
                " If you are unsure of your username, try your email address instead.");
    }
}
