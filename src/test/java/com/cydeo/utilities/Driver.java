package com.cydeo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {
    //create private constructor to remove access to this object
    private Driver() {
    }

    /*
    we make the web driver private because we want to  close access from outside  the class
    we're  making  it static because we will use it in a static method
     */
    //private static WebDriver driver;   //default value=null

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {
            /*
            we will read our browser type from Configuration.properties file
            this way control which browser is open from outside  our code
             */
            String browserType = ConfigurationReader.getProperties("browser");
        /*
         Depending on browserType returned from Configuration.properties
         switch statement will determine case and open the matching browser .
        */
            switch (browserType) {
                case "chrome":
                   // WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;

                case "firefox":
                    //WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
            }
        }
        return driverPool.get();
    }

    /*
    Create a new driver . classDriver();it will use .
     quite() method to quit browsers . and then set the driver value back to null .
     */
    public static void closeDriver(){
        if(driverPool.get()!=null){
          /*
          This line terminate the currently exiting driving completely .
           it will be not exist going forward
          */
          driverPool.get().quit();

          /*
          we assign value back to "null" so that my "singleton"  can create a newer one if needed
           */

            driverPool.remove();

        }
    }
}
