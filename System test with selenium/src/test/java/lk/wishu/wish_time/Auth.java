package lk.wishu.wish_time;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Auth {
    public ChromeDriver driver = new ChromeDriver();

    @Test
    public void t1(){
        driver.get("http://localhost:3000/authentication");
    }
}
