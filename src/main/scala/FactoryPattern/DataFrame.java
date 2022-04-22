package FactoryPattern;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * This class implements all those interface IDataFrame methods
 * @author Angel Gascon and Marc Roigé
 * @version 8/1/2022
 */
public class DataFrame implements IDataFrame {

    private int rows, labels;
    private Map<String, List<String>> frameInfo;
    private ArrayList<String> header;

    /**
     * Constructor that initialize attributes
     */
    public DataFrame(){
        frameInfo = new HashMap<>();
        rows = 0;
        labels = 0;
        header = null;
    }

    /**
     * Method that initializes attributes
     * @param frameInfo Hashmap that will contains csv/txt/json information
     * @param rows number of rows of the Hashmap
     * @param labels number of keys
     * @param header will contain the keys of the Hashmap
     */
    public void takeRef(Map<String, List<String>> frameInfo, int rows, int labels, ArrayList<String> header){
        this.frameInfo = frameInfo;
        this.rows = rows;
        this.labels = labels;
        this.header = header;
    }


    /**
     * return the value of a single item (row) and column label (name).
     * @param fil defines the row we want to look at (0,1,2,3,etc.)
     * @param col defines the column we want to look at (A,B,C,D,etc.)
     * @return String that contains the information at the position (fil, col)
     */
    @Override
    public String at(int fil, String col) {
        List<String> ref = frameInfo.get(col);
        if(fil < 0 || fil>rows-1 || ref ==null)
            return "ERROR: Non existent info";
        else
            return String.valueOf(ref.get(fil));
    }
    /**
     * access a single value for a row and column by integer position
     * @param fil defines the row we want to look at (0,1,2,3,etc.)
     * @param col defines the column we want to look at (0,1,2,3,etc.)
     * @return String that contains the information at the position (fil, col)
     */
    @Override
    public String iat(int fil, int col) {
        if(fil < 0 || fil>rows-1 || col < 0 || col>labels-1)
            return "ERROR: Non existent info";
        else
            return String.valueOf(frameInfo.get(header.get(col)).get(fil));
    }
    /**
     * return number of labels
     * @return int number of columns
     */
    @Override
    public int columns() {
        return labels;
    }
    /**
     * return number of items (rows)
     * @return int number of rows
     */
    @Override
    public int size() {
        return rows;
    }

    /**
     * return the values of a column in the DataFrame following a certain order
     * @param column index of a column
     * @param comp comparator
     * @return values of a collum in a certain order
     */
    @Override
    public List<String> sort(String column, Comparator<String> comp) {
        try{
            List<String> aux = frameInfo.get(column);
            aux.sort(comp);
            return aux;
        }catch (Exception e){
            return null;
        }
    }


    /**
     * return query of the dataframe
     * @param valuePredicate query expression
     * @return query
     */
    @Override
    public List<String> query(Predicate<String> valuePredicate){
        List<String> returning = new LinkedList<>();
        for (String s: header) {
            try{
                List<String> aux = frameInfo.get(s).stream().filter(valuePredicate).collect(Collectors.toList());
                returning.addAll(aux);
            }catch (NumberFormatException ignored){ }
        }
        return returning;
    }
}

