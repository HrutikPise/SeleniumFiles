package SeleniumDemo;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class Demo1 {
	WebDriver driver;
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\hrutik.prakash\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://10.82.181.42/Automation/PackAndGo_v2/");
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	@Test
	public void test() throws InterruptedException{
		
		List<WebElement> tag=driver.findElements(By.tagName("a"));
		System.out.println("No of Links:"+tag.size());
		driver.findElement(By.linkText("About Us")).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//*[@id=\"aboutUsModal\"]/div/div/div[1]/h4"));
		String str=driver.findElement(By.xpath("//*[@id=\"aboutUsModal\"]/div/div/div[2]/p")).getText();
		System.out.println("About Us:"+str);
		driver.findElement(By.id("closeAbout")).click();
		
		driver.findElement(By.linkText("Offers")).click();
		WebElement tblOffer=driver.findElement(By.id("offersTable"));
		List<WebElement> row=tblOffer.findElements(By.tagName("tr"));
		System.out.println("row size:"+row.size());
		List<WebElement> head=tblOffer.findElements(By.tagName("th"));
		//System.out.println(head.get(0).getText()+"\t"+head.get(1).getText()+"\t"+head.get(2).getText()+"\t"+head.get(3).getText());
		for(int i=0;i<head.size();i++) {
			System.out.print(head.get(i).getText());
		}
		System.out.println();
		for(int i=0;i<row.size();i++) {
			List<WebElement> cols=row.get(i).findElements(By.tagName("td"));
			System.out.println("length"+cols.size());
			Thread.sleep(2000);
			for(int j=0;j<cols.size();j++) {
				if(cols.get(0).getText().equals("22 Aug")) {
					String str1=cols.get(0).getText();
					assertEquals(str1,"22 Aug");
					System.out.print(cols.get(j).getText());
				}
			}
			System.out.println();
		}
		driver.findElement(By.id("closeTable")).click();
		
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("usernameLogin")).sendKeys("pgScarlet");
		driver.findElement(By.id("passwordLogin")).sendKeys("freezeray");
		driver.findElement(By.id("login")).click();
		String d=driver.getTitle();
		assertEquals("Dashboard",d);
		System.out.println("Login Successful: "+d);
		
		
		WebElement fromDD=driver.findElement(By.id("fromDD"));
		Select self=new Select(fromDD);
		self.selectByValue("Bengaluru");
		
		WebElement toDD=driver.findElement(By.id("toDD"));
		Select selt=new Select(toDD);
		selt.selectByValue("Hyderabad");
		
		driver.findElement(By.id("searchBus")).click();
		
		driver.findElement(By.id("radio2")).click();
		
		WebElement bp=driver.findElement(By.id("fromDD1"));
		Select sel=new Select(bp);
		sel.selectByIndex(1);
		
		WebElement tp=driver.findElement(By.id("toDD1"));
		Select se1=new Select(tp);
		se1.selectByIndex(1);
		
		driver.findElement(By.id("book")).click();
		
		String name=driver.findElement(By.xpath("//*[@id=\"rowB2\"]/td")).getText();
		System.out.println("Boarding Point is: "+name);
		
		driver.findElement(By.id("counter")).clear();
		driver.findElement(By.id("counter")).sendKeys("4");
		driver.findElement(By.xpath("//*[@id=\"rowB6\"]/td/p/input")).click();
		String amt=driver.findElement(By.xpath("//*[@id=\"tFare\"]")).getText();
		assertEquals("4800",amt);
		//Thread.sleep(2000);
		System.out.println("Amt is:"+amt);
		driver.findElement(By.id("confirmBooking")).click();
		String alert=driver.switchTo().alert().getText();
		System.out.println(alert);
		driver.switchTo().alert().accept();
		
		
		driver.findElement(By.linkText("Booking History")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("refresh")).click();
		WebElement bookHis=driver.findElement(By.id("bookingHistoryTable"));
		List<WebElement> rowws=bookHis.findElements(By.tagName("tr"));
		List<WebElement> headd=bookHis.findElements(By.tagName("th"));
		System.out.println(headd.get(0).getText()+"\t"+headd.get(1).getText()+"\t"+headd.get(2).getText()+"\t"+headd.get(3).getText());
		for(int i=0;i<rowws.size();i++) {
			List<WebElement> coll=rowws.get(i).findElements(By.tagName("td"));
			for(WebElement col:coll) {
				System.out.print(col.getText()+"\t");
			}
		} 
		System.out.println();
		
		driver.findElement(By.linkText("Edit Profile")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("editAccount")).click();
		driver.findElement(By.id("genderM")).click();
		WebElement Lan=driver.findElement(By.id("sel1"));
		Select sel2=new Select(Lan);
		sel2.selectByIndex(2);
		driver.findElement(By.id("contactNum")).sendKeys("1234567890");
		driver.findElement(By.id("clearAccount")).click();
		
		driver.findElement(By.xpath("//*[@id=\"topMenu2\"]/a")).click();
		
		
		driver.navigate().to("http://10.82.181.42/Automation/DemoApps/Drag'N'Drop.aspx");
		String d1=driver.getTitle();
		System.out.println("Title:"+d1);
		Actions action=new Actions(driver);
		WebElement drag=driver.findElement(By.id("draggable"));
		WebElement drop=driver.findElement(By.id("droppable"));
		action.dragAndDrop(drag, drop).click().build().perform();
		String dropped=driver.findElement(By.xpath("//*[@id=\"droppable\"]/p")).getText();
		SoftAssert ass=new SoftAssert();
		ass.assertEquals("Dropped!",dropped);
		
		driver.navigate().to("http://10.82.181.42/Automation/DemoApps/PopupBox.aspx");
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/input")).click();
		String alert1=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		System.out.println(alert1);
		
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/input")).click();
		String alert2=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		System.out.println(alert2);
		
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[3]/input")).click();
		driver.switchTo().alert().sendKeys("Hrutik");
		driver.switchTo().alert().accept();
		String alert3=driver.findElement(By.id("lblMessage")).getText();
		assertEquals("Hello Hrutik! How are you today?",alert3);
		
		driver.navigate().to("http://10.82.181.42/Automation/DemoApps/FrameExample.aspx");
		String title1=driver.getTitle();
		assertEquals("Popup box",title1);
		System.out.println("Assert True"+title1);
		Set<String> window=driver.getWindowHandles();
		driver.switchTo().frame(0);
		String fr1=driver.findElement(By.xpath("//*[@id=\"form1\"]/div[3]/div[2]/div/span")).getText();
		System.out.println(fr1);
		
		driver.switchTo().parentFrame();
		
		driver.switchTo().frame("right");
		String fr3=driver.findElement(By.xpath("//*[@id=\"form1\"]/div[3]/div[2]/div/span")).getText();
		System.out.println(fr3);
	}

}
