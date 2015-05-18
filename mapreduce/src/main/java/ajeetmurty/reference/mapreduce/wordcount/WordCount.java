package ajeetmurty.reference.mapreduce.wordcount;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCount {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		new WordCount(args[0], args[1]);
	}

	public WordCount(String inputPath, String outputPath) throws IOException, ClassNotFoundException, InterruptedException {
		doMapReduce(inputPath, outputPath);
	}

	private void doMapReduce(String inputPath, String outputPath) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();

		Job job = Job.getInstance(conf, "WordCount");
		job.setJarByClass(WordCount.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(WordCountMap.class);
		job.setReducerClass(WordCountReduce.class);
		job.setCombinerClass(WordCountReduce.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		job.waitForCompletion(true);
	}
}
