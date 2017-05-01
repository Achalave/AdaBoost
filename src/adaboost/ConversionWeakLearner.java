package adaboost;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



//@author Michael Haertling

public class ConversionWeakLearner implements WeakLearner{

    ArrayList<Double> classifications;
    String learnerID;
    
    public ConversionWeakLearner(String filename) throws FileNotFoundException{
        classifications = new ArrayList<>();
        learnerID = filename;
        Scanner in = new Scanner(new File(filename));
        while(in.hasNextLine()){
            classifications.add(Double.parseDouble(in.nextLine()));
        }
    }
        
    @Override
    public String getID() {
        return learnerID;
    }

    @Override
    public double getClassification(double[] aset) {
        double c = classifications.get((int)aset[0]);
        if(c==0){
            c = -1;
        }
        return c;
    }

    @Override
    public String toString(){
        return learnerID;
    }
}
