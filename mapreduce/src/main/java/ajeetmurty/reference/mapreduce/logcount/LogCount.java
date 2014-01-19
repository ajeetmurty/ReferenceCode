package ajeetmurty.reference.mapreduce.logcount;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class LogCount {
	private Log log = LogFactory.getLog(LogCount.class);

	public static void main(String[] args) {
		new LogCount(args[0], args[1]);
	}

	public LogCount(String inputPath, String outputPath) {
		doMapReduce(inputPath, outputPath);
	}

	private void doMapReduce(String inputPath, String outputPath) {
		log.info("start");
		try {
			log.info("doing mapreduce...");
			JobConf conf = new JobConf(LogCount.class);
			conf.setJobName("mapreduce-template");
			conf.setOutputKeyClass(Text.class);
			conf.setOutputValueClass(IntWritable.class);
			conf.setMapperClass(Map.class);
			conf.setCombinerClass(Reduce.class);
			conf.setReducerClass(Reduce.class);
			conf.setInputFormat(TextInputFormat.class);
			conf.setOutputFormat(TextOutputFormat.class);

			FileInputFormat.setInputPaths(conf, new Path(inputPath));
			FileOutputFormat.setOutputPath(conf, new Path(outputPath));
			JobClient.runJob(conf);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		log.info("stop");
	}
}
