package ajeetmurty.reference.mapreduce.wordcount;

import java.io.IOException;
import junit.framework.TestCase;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class WordCountMapTest extends TestCase {
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;

	@Before
	public void setUp() {
		WordCountMap mapper = new WordCountMap();
		mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
		mapDriver.setMapper(mapper);
	}

	@Test
	public void testMap() throws IOException {
		mapDriver.withInput(new LongWritable(1), new Text("orange 901 orange apple"));
		mapDriver.withOutput(new Text("orange"), new IntWritable(1));
		mapDriver.withOutput(new Text("901"), new IntWritable(1));
		mapDriver.withOutput(new Text("orange"), new IntWritable(1));
		mapDriver.withOutput(new Text("apple"), new IntWritable(1));
		mapDriver.runTest(true);
	}
}