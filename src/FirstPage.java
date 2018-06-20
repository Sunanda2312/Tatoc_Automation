import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FirstPage {
	
	public static void main(String args[]){
		
		
		System.setProperty("webdriver.chrome.driver","Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc/");
		//driver.findElement(By.linkText("Basic Course")).click();
		driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();
		driver.findElement(By.className("greenbox")).click();
		
		//driverDriverWait wait = new driverDriverWait(driver, 10);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("main")));
		
		driver.switchTo().frame("main");
		String box1 = driver.findElement(By.id("answer")).getAttribute("class");
		
         
		while(true){
            
            driver.findElement(By.cssSelector("a")).click(); //click on repaint box 2
            driver.switchTo().frame("child"); //goto child frame for box2
            String box2 = driver.findElement(By.id("answer")).getAttribute("class");// get second box color
            driver.switchTo().parentFrame(); //go back to parent form for repainting box
            if(box1.equals(box2))
            {
                driver.findElements(By.cssSelector("a")).get(1).click(); //clicking on proceed
                break;
            }
        
        }
		
		WebElement From = driver.findElement(By.id("dragbox"));
		WebElement To = driver.findElement(By.id("dropbox"));
		Actions act = new Actions(driver);					

		//Dragged and dropped.		
	    act.dragAndDrop(From, To).build().perform();
	    
	    //popup
	     
	    
	    driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();
	   	    
	    String parentWindowHandler = driver.getWindowHandle(); // Store your parent window

	    driver.findElement(By.cssSelector("a")).click(); //click on popup window
	    String subWindowHandler = null;

	    Set<String> handles = driver.getWindowHandles(); // get all window handles
	    System.out.println(handles);
	    
	    Iterator<String> iterator = handles.iterator(); //creating iterator to get popup window
	    while (iterator.hasNext()){
	    subWindowHandler = iterator.next();
	    }
	    driver.switchTo().window(subWindowHandler); // switch to pop up window
	    driver.findElement(By.id("name")).sendKeys("sunanda");// writing in area
	    driver.findElement(By.id("submit")).click();// clicking on submit

	    driver.switchTo().window(parentWindowHandler);  // switch back to parent window
	    driver.findElements(By.cssSelector("a")).get(1).click(); //click on proceed      
	    
	    WebElement token = driver.findElement(By.xpath("//*[@id='token']"));
	    driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();
	    driver.manage().getCookies();
	    Cookie ck = new Cookie("Token",token.getText().split("Token: ")[1],"/");
	    driver.manage().addCookie(ck);
	    
	    driver.findElements(By.cssSelector("a")).get(1).click(); 
	    
	    
	    									
	}

}
