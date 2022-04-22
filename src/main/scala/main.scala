import FactoryPattern.CSVFactory
import compositeDataFrameScala.CompositeDataFrameScala
import dataFrameScala.{DataFrameScala, IDataFrameScala}
import VisitorScala.FilterVisitorScala
import VisitorScala.CounterVisitorScala


object main extends scala.App {
  val fac2 = new CSVFactory
  val df2: IDataFrameScala = new DataFrameScala("cities.csv", ",")

  println("DataFrame at -> "+df2.at(3, "LatD"))
  println("DataFrame query String -> "+df2.query(entry => entry.equals("Youngstown")))
  println("DataFrame query Int -> "+df2.query(entry => entry.toInt > 40))

  val comp: CompositeDataFrameScala = new CompositeDataFrameScala
  val comp1: CompositeDataFrameScala = new CompositeDataFrameScala
  //add children to composite
  comp.addChildren(comp1)
  comp1.addChildren(df2)
  comp.addChildren(df2)

  comp.at(3, "LatD")
  println("Composite at -> "+comp.at(3, "LatD"))
  println("Composite query String -> "+comp.query(x => x.equals("Youngstown")))
  println("DataFrame query Int -> "+comp.query(x => x.toInt > 60))

  val v1 = new FilterVisitorScala(x => x.equals("Youngstown"))
  val v2 = new FilterVisitorScala(x => x.toInt > 49)
  val v3= new CounterVisitorScala()
  println("FilterVisitor String-> "+comp.accept(v1))
  println("FilterVisitor Int-> "+comp.accept(v2))
  println("CounterVisitor comp-> "+comp.accept(v3))
}
