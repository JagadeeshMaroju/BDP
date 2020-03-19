/**
 * Illustrates flatMap + countByValue for wordcount.
 */


import java.io.File
import java.nio.file.{Paths, Files}
import java.nio.file.{Paths, Files}
import org.apache.spark._
import org.apache.spark.sql.functions._

object TestSecondarySort {
    def main(args: Array[String]) {

      System.setProperty("hadoop.home.dir","C:\\spark-3.0.0-preview2-bin-hadoop2.7")
      val conf = new SparkConf().setAppName("Spark - Secondary Sort").setMaster("local[*]")
      val sc = new SparkContext(conf)

      val personRDD = sc.textFile("C:\\Users\\jagad\\Desktop\\temparature.txt")
      val pairsRDD = personRDD.map(_.split(",")).map { k => ((k(0) + "-" + k(1)),k(3)) }

      val numReducers = 2;

     // val listRDD = pairsRDD.groupByKey(1).mapValues(iter => iter.toList.sortBy(r => r))
     val listRDD = pairsRDD.groupByKey(1)
       .mapValues(iter => "[" + iter.toArray.sortBy(r => r).reverse.mkString(",") + "]")
      listRDD.foreach {
        println
      }

      val x = listRDD.partitionBy(new HashPartitioner(2))

      x.saveAsTextFile("secondarysortoutput")
      //x.foreach(println)
    }
}
