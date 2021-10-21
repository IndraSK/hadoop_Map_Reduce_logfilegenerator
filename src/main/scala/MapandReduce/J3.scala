package MapandReduce



import HelperUtils.CreateLogger
import MapandReduce.J4.IntSumReader

import java.lang.Iterable
import java.util.StringTokenizer
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.{Job, Mapper, Reducer}
import org.slf4j.Logger

import scala.collection.JavaConverters.*


object J3 :
  //Mapper Class - Converts into key value pairs
  class TokenizerMapper extends Mapper[Object, Text, Text, IntWritable] {
    val logger: Logger = CreateLogger(classOf[TokenizerMapper])
    val one = new IntWritable(1)
    val word = new Text()

    override def map(key: Object, value: Text, context: Mapper[Object, Text, Text, IntWritable]#Context): Unit = {
      val typeofmsg = value.toString().split(" ")(2)
      logger.info("Type of message")
      val itr = new StringTokenizer(value.toString)
      word.set(typeofmsg)
      context.write(word, one)

    }
  }
  //Reducer Class --> Calcuating each word count based on key value pairs from mapper
  class IntSumReader extends Reducer[Text, IntWritable, Text, IntWritable] {
    val logger: Logger = CreateLogger(classOf[IntSumReader])
    override def reduce(key: Text, values: java.lang.Iterable[IntWritable], context: Reducer[Text, IntWritable, Text, IntWritable]#Context): Unit = {
      val sum = values.asScala.foldLeft(0)(_ + _.get)
      logger.info(s"${sum}")
      context.write(key, new IntWritable(sum))
    }
  }

  //Main Method - runs mapper and reducer
  def main(args: Array[String]): Unit = {
    val configuration = new Configuration
    val job = Job.getInstance(configuration, "word count")
    job.setJarByClass(this.getClass)
    job.setMapperClass(classOf[TokenizerMapper])
    job.setCombinerClass(classOf[IntSumReader])
    job.setReducerClass(classOf[IntSumReader])
    job.setOutputKeyClass(classOf[Text])
    job.setOutputKeyClass(classOf[Text]);
    job.setOutputValueClass(classOf[IntWritable]);
    FileInputFormat.addInputPath(job, new Path(args(0)))
    FileOutputFormat.setOutputPath(job, new Path(args(1)))
    job.waitForCompletion(true)
  }


