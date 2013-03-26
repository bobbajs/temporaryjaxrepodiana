package com.crawljax.examples;

import com.crawljax.browser.EmbeddedBrowser.BrowserType;
import com.crawljax.core.CrawljaxController;
import com.crawljax.core.configuration.CrawlSpecification;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.configuration.InputSpecification;

public final class SimpleExample{
	
	private static final String URL = "http://www.bing.com";
	
	private SimpleExample(){
		
	}
	
	private static CrawljaxConfiguration getCrawljaxConfiguration(){
		CrawljaxConfiguration config = new CrawljaxConfiguration();
		config.setBrowser(BrowserType.firefox);
		config.setCrawlSpecification(getCrawlSpecification());
		
		// add other URL that cannot be reachable from seedURL
		config.alsoCrawl("http://www.google.com");
		//config.alsoCrawl("http://www.yahoo.com");
		
		return config;
	}
	
	private static CrawlSpecification getCrawlSpecification(){
		CrawlSpecification crawler = new CrawlSpecification(URL);
		
		crawler.click("a");
		crawler.click("input").withAttribute("type", "submit");
		crawler.dontClick("a").withText("Language Tools");
		crawler.dontClick("a").underXPath("//DIV[@id='guser']");
		crawler.setInputSpecification(getInputSpecification());
		
		//set maximum states
		crawler.setMaximumStates(5);
		//crawler.setDepth(2);
		
		return crawler;
	}
	
	private static InputSpecification getInputSpecification(){
		InputSpecification input = new InputSpecification();
		input.field("sb_form_q").setValue("Crawljax");
		return input;
	}
	
	public static void main(String[] args){
		CrawljaxController crawljax = new CrawljaxController(getCrawljaxConfiguration());
		crawljax.run();
	}
	
}