package dataFrameScala



import FactoryPattern.{CSVFrame, IDataFrame}

import java.util
import java.util.function.Predicate


class DataFrameScala(val name: String, val separator: String) extends IDataFrameScala {
  private val df:IDataFrame = new CSVFrame(name)

   def at(fil: Int, col: String): String = df.at(fil, col)

   def columns: Int = df.columns

   def size: Int = df.size

   override def query(valuePredicate: Predicate[String]): util.List[String] = df.query(valuePredicate): util.List[String]
}
