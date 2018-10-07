package com.dujegue.betsoccer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@TestPropertySource(locations = "classpath:user.properties")
@SpringBootTest
public class BetSoccerApplicationTests {

	private WebDriver driver;

	// private Gson gson;

	@Autowired
	private Environment env;

	@Before
	public void init() {

		driver = new ChromeDriver();

		driver.get("https://www.resultados.com/");
		
		tempo(10000);
		
		WebElement menuAovivo = driver.findElement(By.id("fscon"));
		WebElement ul = menuAovivo.findElement(By.tagName("ul"));
		List<WebElement> li = ul.findElements(By.tagName("li"));
		
		li.get(1).click();
		
		WebElement fs = driver.findElement(By.id("fs"));
		List<WebElement> tables = fs.findElements(By.tagName("table"));
		
		WebElement tabela1 = tables.get(0);
		List<WebElement> trs = tabela1.findElements(By.tagName("tr"));
		
		driver.get("https://www.resultados.com/jogo/tKRRAaVa/#resumo-de-jogo");
		
		tempo(5000);
		
		WebElement detailBookmarks = driver.findElement(By.id("detail-bookmarks"));
		WebElement detailSubMenuBookmarks = detailBookmarks.findElement(By.id("detail-submenu-bookmark"));
		WebElement ulSub = detailSubMenuBookmarks.findElement(By.tagName("ul"));
		List<WebElement> liSub = ulSub.findElements(By.tagName("li"));
		
		liSub.get(1).click();
		
		tempo(5000);
		
		WebElement mathStats = driver.findElement(By.id("tab-match-statistics"));
		WebElement stat0 = mathStats.findElement(By.id("tab-statistics-0-statistic"));
		List<WebElement> divSub = stat0.findElements(By.className("statTextGroup"));
		
		for (WebElement divS : divSub) {
			System.out.println(divS.getText());
		}
	}

	@Test
	public void testAccess() {
		assertThat("success", containsString("success"));
	}
	
	private void tempo(long tmp) {
		try {
			Thread.sleep(tmp);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * @Test public void testAccess() {
	 * 
	 * List<WebElement> links = driver.findElements(By.className("link-race"));
	 * List<String> listRaceId = new ArrayList<String>();
	 * 
	 * String siteRace = env.getProperty("app.site.race");
	 * 
	 * for (WebElement webElement : links) {
	 * listRaceId.add(webElement.getAttribute("data-race-id")); }
	 * 
	 * for (String raceId : listRaceId) {
	 * 
	 * driver.get(siteRace + raceId); }
	 * 
	 * assertThat("success", containsString("success")); }
	 */
}