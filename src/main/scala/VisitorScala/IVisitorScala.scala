package VisitorScala

import compositeDataFrameScala.CompositeDataFrameScala


/**
 * Interface for visitor
 */
trait IVisitorScala {
  /**
   * visit method for CompositeDataframe
   *
   * @param cDataFrame DataFrame ref
   * @return Object visited
   */
  def visit(cDataFrame: CompositeDataFrameScala): Int
}

