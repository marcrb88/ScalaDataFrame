package VisitorScala

import compositeDataFrameScala.CompositeDataFrameScala

class CounterVisitorScala() extends IVisitorScala {
  /**
   * visit method for CompositeDataframe
   *
   * @return Object visited
   */
   def visit(cDataFrame: CompositeDataFrameScala): Any = try{

     val aux: String = "Dirs " + cDataFrame.getNdir.toString + ", Files " + cDataFrame.getNfiles.toString
     aux
  }
}
