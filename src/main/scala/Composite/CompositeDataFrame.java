package Composite;

import FactoryPattern.IDataFrame;
import Visitor.IVisitor;
import java.util.*;
import java.util.function.Predicate;

/**
 * This class defines a directory and leaf
 * @author Angel Gascon and Marc Roigé
 * @version 8/1/2022
 */
public class CompositeDataFrame implements IDataFrame, ICompositeDataFrame {

    private final List <IDataFrame> children;

    /**
     * Constructor that initializes the children linked list
     */
    public CompositeDataFrame(){
        children = new LinkedList<>();
    }

    public void addChildren(IDataFrame child){

        children.add(child);
    }

    @Override
    public String at(int fil, String col) {
        StringBuilder result = new StringBuilder();
        for (IDataFrame child:children)
            result.append(child.at(fil, col)).append(";");
        return result.toString();
    }

    @Override
    public String iat(int fil, int col) {
        StringBuilder result = new StringBuilder();
        for (IDataFrame child:children)
            result.append(child.iat(fil, col)).append(";");
        return result.toString();
    }

    @Override
    public int columns() {
        int result = 0;
        for (IDataFrame child:children)
            result = result + child.columns();
        return result;
    }

    @Override
    public int size() {
        int result = 0;
        for (IDataFrame child:children)
            result = result + child.size();
        return result;
    }

    @Override
    public List<String> sort(String column, Comparator<String> comp) {
        List<String> result = new LinkedList<>();
        for (IDataFrame child : children) {
            result.addAll(child.sort(column, comp));
        }
        result.sort(comp);
        return result;
    }

    @Override
    public List<String> query(Predicate<String> valuePredicate) {
        return null;
    }


    @Override
    public Object accept(IVisitor v, String label) {
        return  v.visit(this, label);
    }
}
