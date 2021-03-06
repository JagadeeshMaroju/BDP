/**
 * Illustrates flatMap + countByValue for wordcount.
 */


import org.apache.spark._

object WordCount {
    def main(args: Array[String]) {

      System.setProperty("hadoop.home.dir","C:\\spark-3.0.0-preview2-bin-hadoop2.7")
      //val inputFile = args(0)
      //val outputFile = args(1)
      val conf = new SparkConf().setAppName("wordCount").setMaster("local[*]")
      // Create a Scala Spark Context.
      val sc = new SparkContext(conf)
      // Load our input data.
      //val input =  sc.textFile(inputFile)
      val input = sc.textFile("input.txt")
      // Split up into words.
      val words = input.flatMap(line => line.split(" "))
      // Transform into word and count.
      val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
      // Save the word count back out to a text file, causing evaluation.
      counts.saveAsTextFile("output")
      //split the file and use count action to get the count of word "good" in the input file
      val file = input.flatMap(l=>l.split(" ")).filter(value=>value=="good")
      println(file.count())
      file.top(2) .foreach(println)
    }
}
