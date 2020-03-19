/**
 * Illustrates flatMap + countByValue for wordcount.
 */


import org.apache.spark._

object SecondarySort {
    def main(args: Array[String]) {

      System.setProperty("hadoop.home.dir","C:\\spark-3.0.0-preview2-bin-hadoop2.7")
      //val inputFile = args(0)
      //val outputFile = args(1)
      val conf = new SparkConf().setAppName("secondarysort").setMaster("local[*]")
      // Create a Scala Spark Context.
      val sc = new SparkContext(conf)
      // Load our input data.
      //val input =  sc.textFile(inputFile)
      val input = sc.textFile("C:\\Users\\jagad\\Desktop\\temparature.txt")
      // Split up into words.
      val words = input.flatMap(line => line.split(","))
      // words.collect().foreach(println)
     // println(words[0])

    }
}
