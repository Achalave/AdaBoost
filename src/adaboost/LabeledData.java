package adaboost;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



//@author Michael Haertling

public class LabeledData extends Data{
    
    ArrayList<Double> classifications;
    
    public LabeledData(String filename) throws FileNotFoundException{
        classifications = new ArrayList<>();
        Scanner in = new Scanner(new File(filename));
        while(in.hasNextLine()){
            classifications.add(Double.parseDouble(in.nextLine()));
        }
    }
    
    public double getClassValue(double[] i) {
        double c = classifications.get((int)i[0]);
        if(c==0){
            c = -1;
        }
        return c;
    }

    
    
}
