package compositeDataFrameScala

import VisitorScala.IVisitorScala
import dataFrameScala.IDataFrameScala

import java.util
import java.util.function.Predicate
import scala.jdk.CollectionConverters._

class CompositeDataFrameScala() extends IDataFrameScala with ICompositeDataFrameScala {
  private val children: util.List[IDataFrameScala] = new util.LinkedList[IDataFrameScala]
  private var nfiles: Int = 0
  private var ndirs:Int = 0
  override def addChildren(child: IDataFrameScala): Unit = {
    children.add(child)
  }

  override def at(fil: Int, col: String): String = {
    val result: StringBuilder = new StringBuilder
    for (child <- children.asScala) {
      result.append(child.at(fil, col)).append(";")

      if(child.isInstanceOf[CompositeDataFrameScala]) { ndirs = ndirs+1 }
      else{ nfiles = nfiles+1 }
    }
    result.toString
  }

  override def columns: Int = {
    var result: Int = 0
    for (child <- children.asScala) {
      result = result + child.columns

      if(child.isInstanceOf[CompositeDataFrameScala]) { ndirs = ndirs+1 }
      else{ nfiles = nfiles+1 }
    }
    result
  }

  override def size: Int = {
    var result: Int = 0
    for (child <- children.asScala) {
      result = result + child.size

      if(child.isInstanceOf[CompositeDataFrameScala]) { ndirs = ndirs+1 }
      else{ nfiles = nfiles+1 }
    }
    result
  }

  override def getChildren: util.List[IDataFrameScala] = {
    children
  }

  override def accept(v: IVisitorScala): Any = v.visit(this)

  override def query(valuePredicate: Predicate[String]): util.List[String] = {
    val returning: util.List[String] = new util.LinkedList[String]
    for (child <- children.asScala) {
      val aux: util.List[String] = child.query(valuePredicate)
      for (str <- aux.asScala){
        returning.add(str)
      }
      if(child.isInstanceOf[CompositeDataFrameScala]) { ndirs = ndirs+1 }
      else{ nfiles = nfiles+1 }
    }
    returning
  }

  override def getNdir: Int = ndirs

  override def getNfiles: Int = nfiles
}
