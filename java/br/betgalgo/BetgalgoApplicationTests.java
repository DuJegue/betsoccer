package br.betgalgo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.betgalgo.commons.pojo.Analysis;
import br.betgalgo.commons.pojo.Analysis2;
import br.betgalgo.commons.pojo.Race;
import br.betgalgo.commons.util.Analysis2Adapter;
import br.betgalgo.commons.util.AnalysisAdapter;
import br.betgalgo.commons.util.IntegerCustom;
import br.betgalgo.commons.util.IntegerCustomTypeAdapter;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:user.properties")
@SpringBootTest
public class BetgalgoApplicationTests {

	private WebDriver driver;

	private Gson gson;

	private Map<String, Race> mapRace;

	@Autowired
	private Environment env;

	@Before
	public void init() {

		mapRace = new HashMap<>();

		gson = new GsonBuilder().registerTypeAdapter(Analysis.class, new AnalysisAdapter())
		         				.registerTypeAdapter(Analysis2.class, new Analysis2Adapter())
		         				.registerTypeAdapter(IntegerCustom.class, new IntegerCustomTypeAdapter())
		         				.setPrettyPrinting()
		         				.create();

		driver = new ChromeDriver();

		driver.get(env.getProperty("app.site"));

		WebElement inputUser = driver.findElement(By.name("username"));

		inputUser.sendKeys(env.getProperty("app.user"));

		WebElement inputPass = driver.findElement(By.name("password"));

		inputPass.sendKeys(env.getProperty("app.pass"));

		WebElement button = driver.findElement(By.cssSelector("button[type='submit'"));

		button.click();
	}

	@Test
	public void testAccess() {

		List<WebElement> links = driver.findElements(By.className("link-race"));
		List<String> listRaceId = new ArrayList<String>();

		String siteRace = env.getProperty("app.site.race");

		for (WebElement webElement : links) {
			listRaceId.add(webElement.getAttribute("data-race-id"));
		}

		for (String raceId : listRaceId) {
            
			driver.get(siteRace + raceId);
			
			try {
			
				Thread.sleep(2000);
			
				String json = driver.findElement(By.tagName("body")).getText();

				Race race = gson.fromJson(json, Race.class);

				mapRace.put(raceId, race);
	        
	            Files.write(Paths.get(raceId + ".json"), json.getBytes());

			} catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
		assertThat("success", containsString("success"));
	}
}
