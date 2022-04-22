package VisitorScala

import compositeDataFrameScala.CompositeDataFrameScala

import java.util
import java.util.function.Predicate
import scala.jdk.CollectionConverters._

class FilterVisitorScala (valuePredicate:Predicate [String]) extends IVisitorScala {

  /**
   * visit method for CompositeDataframe
   *
   * @return Object visited
   */
  override def visit(cDataFrame: CompositeDataFrameScala): Any = try{

    val returning: util.List[String] = cDataFrame.query(valuePredicate)
    var aux:String = ""
    for(s <- returning.asScala){
        aux+= s+","
    }
    aux
  }


}