package ajeetmurty.reference.mapreduce.logcount;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	private Log log = LogFactory.getLog(Map.class);
	private static final IntWritable one = new IntWritable(1);
	private Text word = new Text();
	private final String logRegex = "^(\\S+)\\s(.*)$";

	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		String line = value.toString();
		log.info("map input line: " + line);
		Pattern pattern = Pattern.compile(logRegex);
		Matcher matcher = pattern.matcher("");
		if (matcher.reset(line).matches()) {
			word.set(matcher.group(1));
		} else {
			word.set("unknown");
		}
		log.info(String.format("map output: %1$s|%2$s", word.toString(), one.get()));
		output.collect(word, one);
	}
}
