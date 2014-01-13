package ajeetmurty.reference.java.os;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandExecutor {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new CommandExecutor();
	}

	public CommandExecutor() {
		logp.info("start");
		executeCommand();
		logp.info("stop");
	}

	private void executeCommand() {
		try {
			ArrayList<String> commandList = new ArrayList<String>();
			commandList.add("nslookup");
			commandList.add("-all");
			commandList.add("google.com");

			ProcessBuilder builder = new ProcessBuilder(commandList);
			logp.info("command: " + builder.command().toString());
			StringBuffer commmandOutput = new StringBuffer();
			Process p = builder.start();

			InputStream isStdout = p.getInputStream();
			InputStreamReader isrStdout = new InputStreamReader(isStdout);
			BufferedReader brStdout = new BufferedReader(isrStdout);
			String lineStdout;
			while ((lineStdout = brStdout.readLine()) != null) {
				commmandOutput.append("|" + lineStdout.trim());
			}
			InputStream isStderror = p.getErrorStream();
			InputStreamReader isrStderror = new InputStreamReader(isStderror);
			BufferedReader brStderror = new BufferedReader(isrStderror);
			String lineStderror;
			while ((lineStderror = brStderror.readLine()) != null) {
				commmandOutput.append("|" + lineStderror.trim());
			}

			commmandOutput.append("|");
			logp.info("command output: " + commmandOutput.toString());
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
	}
}
