package com.qa.radgate.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	// This Playwright reference can use anywhere in this calss
	Playwright playwright;
	Browser browser;

	// BrowserContexts provide a way to operate multiple independent browser
	BrowserContext browserContext;

	// Page provides methods to interact with a single tab in a Browser.
	// One Browserinstance might have multiple Page instances.
	Page page;
	Properties prop;

	public Page initBrowser(Properties prop) {

		String browserName = prop.getProperty("Browser").trim();
		String url = prop.getProperty("url").trim();
		// boolean headless=prop.getProperty("headless").trim() != null;

		System.out.println("Browse Name is: " + browserName);

		playwright = Playwright.create();
		switch (browserName.toLowerCase()) {
		case "chromium":
			// launch() method launch the browser in Headlesmode
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;

		case "firefox":
			// launch() method launch the browser in Headlesmode
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;

		case "safari":
			// launch() method launch the browser in Headlesmode
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;

		case "chrome":
			// launch() method launch the browser in Headlesmode
			browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			break;

		default:
			System.out.println("Please pass the correct browser name......");
			break;
		}
		// Create a new incognito browser context.
		browserContext = browser.newContext();

		page = browserContext.newPage();
		page.navigate(url);

		return page;
	}

	/**
	 * This method is use to initialize the properties properties from config file
	 * 
	 * @return
	 * 
	 * @throws FileNotFoundException
	 * 
	 */
	public Properties init_prop() throws FileNotFoundException {

		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public static void dicomModifier(String batchFileName) {
		try {
			// Specify the path to the batch file
			String batchFilePath = System.getProperty("user.dir") + "\\src\\main\\resource\\config\\" + batchFileName;
			System.out.println(batchFilePath + " batch file starts the process");

			// Create ProcessBuilder instance with the batch file path as argument
			ProcessBuilder processBuilder = new ProcessBuilder(batchFilePath);

			// Start the process
			Process process = processBuilder.start();

			// Wait for the process to finish
			int exitCode = process.waitFor();

			// Output the exit code
			System.out.println("Batch file executed, exit code: " + exitCode);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
