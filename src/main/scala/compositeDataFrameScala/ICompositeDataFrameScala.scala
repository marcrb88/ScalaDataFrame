package compositeDataFrameScala

import VisitorScala.IVisitorScala
import dataFrameScala.IDataFrameScala

import java.util

trait ICompositeDataFrameScala {

  def addChildren(child: IDataFrameScala): Unit

  def getChildren: util.List[IDataFrameScala]

  def accept(v: IVisitorScala): Any

  def getNdir: Int

  def getNfiles: Int
}
