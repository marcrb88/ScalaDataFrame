package dataFrameScala



import java.util
import java.util.function.Predicate

/**
 * @author Angel Gascon & Marc Roige
 *
 *         Defines the dataFrame functions
 *
 */
trait IDataFrameScala {
  /**
   * return the value of a single item (row) and column label (name).
   *
   * @param fil defines the row we want to look at (0,1,2,3,etc.)
   * @param col defines the column we want to look at (A,B,C,D,etc.)
   * @return String that contains the information at the position (fil, col)
   */
  def at(fil: Int, col: String): String

  /**
   * return number of labels
   *
   * @return int number of columns
   */
  def columns: Int

  /**
   * return number of items (rows)
   *
   * @return int number of rows
   */
  def size: Int

  def query(valuePredicate: Predicate[String]):  util.List[String]

}