package ajeetmurty.reference.java.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileParse {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());
	private final String inputLogFilePath = "src/main/resources/regex.log";
	private final String regex = "^((\\d){1,3}\\.(\\d){1,3}\\.(\\d){1,3}\\.(\\d){1,3})(.*)$";

	public static void main(String[] args) {
		new FileParse();
	}

	private FileParse() {
		logp.info("start");
		parseFile();
		logp.info("stop");
	}

	private void parseFile() {
		try {
			logp.info("input log file path: " + inputLogFilePath);
			File inputFile = new File(inputLogFilePath);
			if (inputFile.isFile() && inputFile.canRead()) {
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher("");

				BufferedReader br = new BufferedReader(new FileReader(inputFile));
				String line = null;
				while ((line = br.readLine()) != null) {
					if (matcher.reset(line).matches()) {
						logp.info("line matched & host extracted: " + matcher.group(1));
					} else {
						logp.error("line not matched: " + line);
					}
				}
				br.close();
			} else {
				logp.error("input log file not found / not readable");
			}
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
	}
}
