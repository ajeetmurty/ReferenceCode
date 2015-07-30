package ajeetmurty.reference.java.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnit {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new HtmlUnit();
	}

	private HtmlUnit() {
		logp.info("start");
		doSearch();
		logp.info("stop");
	}

	private void doSearch() {
		try {
			final String configFile = "src\\main\\resources\\htmlunit.properties";
			SearchInput searchInput = getSearchParams(configFile);
			String searchResult = performSearch(searchInput);
			if (verifySearchResult(searchResult, searchInput.outputVerificationString)) {
				logp.info("search was successful!");
			} else {
				logp.error("search failed!");
			}
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
	}

	private String performSearch(SearchInput input) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		webClient.getOptions().setTimeout((int) input.timeout);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage(input.url);
		HtmlForm loginForm = page.getFormByName("x");
		loginForm.getInputByName("q").setValueAttribute(input.inputSearchString);
		HtmlPage pageResults = loginForm.getInputByName("b").click();
		String strResults = pageResults.asText().replaceAll("(\\r|\\n|\\t)", "");
		logp.debug("search result: " + strResults);
		return strResults;
	}

	private boolean verifySearchResult(String searchResults, String verificationString) {
		String regex = String.format("^(.*)(%s)+(.*)$", verificationString);
		logp.debug("search regex: " + regex);
		Pattern searchPattern = Pattern.compile(regex);
		Matcher searchMatcher = searchPattern.matcher("");
		if (searchMatcher.reset(searchResults).matches()) {
			return true;
		}
		return false;
	}

	private SearchInput getSearchParams(String configFile) throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream(configFile);
		prop.load(input);
		return new SearchInput(prop.getProperty("url"), prop.getProperty("search"), prop.getProperty("result"), Long.parseLong(prop.getProperty("timeout")));
	}

	private class SearchInput {
		private String url, inputSearchString, outputVerificationString;
		private long timeout;

		private SearchInput(String url, String inputSearchString, String outputVerificationString, long timeout) throws IOException {
			if (!url.isEmpty() && !inputSearchString.isEmpty() && !outputVerificationString.isEmpty()) {
				this.url = url;
				this.inputSearchString = inputSearchString;
				this.outputVerificationString = outputVerificationString;
				this.timeout = timeout;
				logp.debug(String.format("search input params: | url->%1$s | string->%2$s | result->%3$s | timeout->%4$s | ", this.url, this.inputSearchString, this.outputVerificationString, this.timeout));
			} else
				throw new IOException("input search parameters are emtpy!");
		}
	}
}
