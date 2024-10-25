package com.automation.utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;

public class SomeTest {
    public static void main(String[] args) {

        User[] arr;
        PersonGenerator generator = new PersonGenerator();

        arr = new User[6];

        arr[0] = new User("standard_user", "secret_sauce");
        arr[1] = new User("locked_out_user", "secret_sauce");
        arr[2] = new User("problem_user", "secret_sauce");
        arr[3] = new User("performance_glitch_user", "secret_sauce");
        arr[4] = new User("error_user", "secret_sauce");
        arr[5] = new User("visual_user", "secret_sauce");

        WebDriver driver = new ChromeDriver();

        for (User user : arr) {
            System.out.println("Try loggin with user name: " + user.userName + " "
                           + "and user password: "
                           + user.password);
            driver.get("https://www.saucedemo.com/");
            WebElement userName = driver.findElement(By.cssSelector("#user-name"));
            WebElement password = driver.findElement(By.cssSelector("#password"));
            WebElement submitButton = driver.findElement(By.cssSelector(".submit-button"));
            userName.sendKeys(user.userName);
            password.sendKeys(user.password);
            submitButton.click();
            try {
            WebElement error = driver.findElement(By.cssSelector(".error-message-container h3"));
            System.out.println(error.getText());
            continue;
            } catch (NoSuchElementException e) {
                System.out.println("Login successful!");
            }
            List<WebElement> itemButtons = driver.findElements(By.cssSelector(".btn_primary"));
            for (WebElement button : itemButtons) {
                button.click();
            }
            WebElement shoppingCart = driver.findElement(By.cssSelector(".shopping_cart_link"));
            shoppingCart.click();
            WebElement checkoutButton = driver.findElement(By.cssSelector("#checkout"));
            checkoutButton.click();
            WebElement firstNameInput = driver.findElement(By.cssSelector("#first-name"));
            WebElement lastNameInput = driver.findElement(By.cssSelector("#last-name"));
            WebElement postalCodeInput = driver.findElement(By.cssSelector("#postal-code"));
            String firstName = generator.getRandomFirstName();
            String lastName = generator.getRandomLastName();
            System.out.println(firstName);
            System.out.println(lastName);
            firstNameInput.sendKeys(firstName);
            lastNameInput.sendKeys(lastName);
            postalCodeInput.sendKeys("60700"); 
            WebElement continueButton = driver.findElement(By.cssSelector("#continue"));
            continueButton.click();
            WebElement finishButton = driver.findElement(By.cssSelector("#finish"));
            finishButton.click();
            WebElement backToProductsButton = driver.findElement(By.cssSelector("#back-to-products"));
            backToProductsButton.click();
            WebElement reactBurgerMenuButton = driver.findElement(By.cssSelector("#react-burger-menu-btn"));
            reactBurgerMenuButton.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement logoutSidebarLinkButton = driver.findElement(By.cssSelector("#logout_sidebar_link"));
            logoutSidebarLinkButton.click();
        }
        
    }
}

class User {

    public String userName;
    public String password;

    User(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public String replaceRandomCharInUserName() 
    {
        if (userName == null || userName.isEmpty()) {
            return userName;  // Если строка пустая или null, возвращаем ее как есть
        }

        Random random = new Random();

        int randomIndex = random.nextInt(userName.length());

        char randomLetter = (char) ('a' + random.nextInt(26));

        StringBuilder result = new StringBuilder(userName);
        result.setCharAt(randomIndex, randomLetter);

        return result.toString();
    }

    public String replaceRandomCharInPassword() 
    {
        if (password == null || password.isEmpty()) {
            return password;  // Если строка пустая или null, возвращаем ее как есть
        }

        Random random = new Random();

        int randomIndex = random.nextInt(password.length());

        char randomLetter = (char) ('a' + random.nextInt(26));

        StringBuilder result = new StringBuilder(password);
        result.setCharAt(randomIndex, randomLetter);

        return result.toString();
    }
    
}

class PersonGenerator {
    private final String[] firstNames = {
        "John", "Jane", "Alex", "Emily", "Chris", "Sarah", "David", "Sophia", "Daniel", "Olivia"
    };

    private final String[] lastNames = {
        "Smith", "Johnson", "Brown", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin"
    };

    private final Random random = new Random();

    public String getRandomFirstName() {
        return firstNames[random.nextInt(firstNames.length)];
    }

    public String getRandomLastName() {
        return lastNames[random.nextInt(lastNames.length)];
    }
}