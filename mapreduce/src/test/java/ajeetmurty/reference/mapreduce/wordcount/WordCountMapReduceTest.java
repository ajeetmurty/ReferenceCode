package ajeetmurty.reference.mapreduce.wordcount;

import java.io.IOException;
import junit.framework.TestCase;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class WordCountMapReduceTest extends TestCase {
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

	@Before
	public void setUp() {
		WordCountMap mapper = new WordCountMap();
		WordCountReduce reducer = new WordCountReduce();
		mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
	}

	@Test
	public void testMapReduce() throws IOException {
		mapReduceDriver.addInput(new LongWritable(1), new Text("orange 001cv orange apple"));
		mapReduceDriver.addOutput(new Text("001cv"), new IntWritable(1));
		mapReduceDriver.addOutput(new Text("apple"), new IntWritable(1));
		mapReduceDriver.addOutput(new Text("orange"), new IntWritable(2));
		mapReduceDriver.runTest(true);
	}
}