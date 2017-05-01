package adaboost;

import java.util.ArrayList;



//@author Michael Haertling

public class Data {
    
    ArrayList<double[]> instances;
    int index =0;
    
    public double[] getNextAttributeSet(){
        double[] n = {index++};
        return n;
    }
    
    public void resetIteration(){
        index = 0;
    }
    
    public boolean iterationComplete(){
        return index == instances.size();
    }
    
    public int size(){
        return instances.size();
    }
    
    public int getIndex(){
        return index;
    }
    
}
