package MapandReduce

import HelperUtils.Parameters.getParam
import HelperUtils.{CreateLogger, ObtainConfigReference}
import java.lang.Iterable
import java.util.StringTokenizer
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.{Job, Mapper, Reducer}
import scala.collection.JavaConverters.*
import org.slf4j.Logger

object J4 {
  //Mapper Class - Converts into key value pairs
  class TokenizerMapper extends Mapper[Object, Text, Text, IntWritable] {
    val one = new IntWritable(1)
    val word = new Text()
    val logger: Logger = CreateLogger(classOf[TokenizerMapper])

    override def map(key: Object, value: Text, context: Mapper[Object, Text, Text, IntWritable]#Context): Unit = {

      val typeofmsg = value.toString().split(" ")(2)
      logger.info("Type of message")
      val str = value.toString()
      val Pattern = "(.*?)".r
      Pattern.findFirstMatchIn(str) match {
        case Some(pattern) => {
          word.set(typeofmsg)
          typeofmsg match {
            case "INFO" => {
              val s = value.toString().split(" ")(6)
              context.write(word, new IntWritable(str.length()))
            }
            case "WARN" => {
              val s = value.toString().split(" ")(6)
              logger.info(s"${s}")
              context.write(word, new IntWritable(str.length()))
            }
            case "ERROR" => {
              val s = value.toString().split(" ")(5)
              logger.info(s"${s}")
              context.write(word, new IntWritable(str.length()))
            }
            case "DEBUG" => {
              val s = value.toString().split(" ")(5)
              logger.info(s"${s}")
              context.write(word, new IntWritable(str.length()))
            }
            case _ =>
          }
        }
        case None =>
      }


    }
  }

  //Reducer Class --> Calcuating each word count based on key value pairs from mapper
  class IntSumReader extends Reducer[Text, IntWritable, Text, IntWritable] {
    val logger: Logger = CreateLogger(classOf[IntSumReader])

    override def reduce(key: Text, values: java.lang.Iterable[IntWritable], context: Reducer[Text, IntWritable, Text, IntWritable]#Context): Unit = {
      val sum = values.asScala.foldLeft(0) { (t, i) => t max i.get() }
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
    System.exit(if (job.waitForCompletion(true)) 0 else 1)
  }
}




