package ajeetmurty.reference.mapreduce.wordcount;

import java.io.IOException;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class WordCountReduceTest extends TestCase {
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;

	@Before
	public void setUp() {
		WordCountReduce reducer = new WordCountReduce();
		reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>();
		reduceDriver.setReducer(reducer);
	}

	@Test
	public void testReduce() throws IOException {
		ArrayList<IntWritable> appleValues = new ArrayList<IntWritable>();
		appleValues.add(new IntWritable(6));
		appleValues.add(new IntWritable(0));

		ArrayList<IntWritable> orangeValues = new ArrayList<IntWritable>();
		orangeValues.add(new IntWritable(1));
		orangeValues.add(new IntWritable(2));

		reduceDriver.withInput(new Text("apple"), appleValues);
		reduceDriver.withInput(new Text("orange"), orangeValues);
		reduceDriver.withOutput(new Text("apple"), new IntWritable(6));
		reduceDriver.withOutput(new Text("orange"), new IntWritable(3));
		
		reduceDriver.runTest(true);
	}
}