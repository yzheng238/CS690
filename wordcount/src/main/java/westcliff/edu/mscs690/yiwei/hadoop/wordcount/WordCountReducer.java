/**
 * 
 */
package westcliff.edu.mscs690.yiwei.hadoop.wordcount;

import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.stream.StreamSupport;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * @author yiweizheng
 *
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> valueIterable,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException{
		int sum = StreamSupport.stream(valueIterable.spliterator(),false).mapToInt(value -> value.get()).sum();
		context.write(key,new IntWritable(sum));
	}
}
