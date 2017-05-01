package adaboost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



//@author Michael Haertling

public class AdaBooster {
    
    double[] dt;
    double dtSum;
    HashMap<String,WeakLearner> learners;
    int time = 10;
    
    //The learables
    ArrayList<Double> at;
    ArrayList<WeakLearner> learnerList;
    
    public AdaBooster(){
        learners = new HashMap<>();
        at = new ArrayList<>();
        learnerList = new ArrayList<>();
    }
    
    public void addLearner(WeakLearner l){
        learners.put(l.getID(),l);
    }
    
    public void boost(LabeledData data){
        //Set up the inital distribution
        dt = new double[data.size()];
        Arrays.fill(dt, ((double)1/data.size()));
        calculateDtSum();
        
        while(time > 0){
            time--;
            //Find the learner with the lowest error over dt
            //Find the error of each learner over dt
            WeakLearner bestLearner = null;
            double et = 2;
            for(WeakLearner wl:learners.values()){
                double e = findError(data,wl);
                if(e<et){
                    et = e;
                    bestLearner = wl;
                }
            }
            
            //Find zt
            double zt = 2*Math.sqrt(et*(1-et));
            
            //Find at
            double a = 0.5*Math.log(((1-et)/et));
            
            //Save the learner usage and at
            learnerList.add(bestLearner);
            at.add(a);
            
            //Update the distribution (dt)
            for(int i=0;i<dt.length;i++){
                double[] instance = data.getNextAttributeSet();
                dt[i] = (dt[i]/zt)*Math.exp(-a*data.getClassValue(instance)
                        *bestLearner.getClassification(instance));
            }
            data.resetIteration();
            calculateDtSum();
        }
        System.out.println("At: "+at);
        System.out.println("Learner Order: "+learnerList);
    }
    
    public void calculateDtSum(){
        dtSum = 0;
        for(double weight:dt){
            dtSum += weight;
        }
    }
    
    public double findError(LabeledData data, WeakLearner learner){
        double error = 0;
        
        while(!data.iterationComplete()){
            double[] instance = data.getNextAttributeSet();
            if(data.getClassValue(instance) != learner.getClassification(instance)){
                int index = data.getIndex();
                error += dt[index];
            }
        }
        
        error /= dtSum;
        
        data.resetIteration();
        
        return error;
    }
    
    public double classify(double[] instance){
        double sum = 0;
        for(int i=0;i<learnerList.size();i++){
            sum += learnerList.get(i).getClassification(instance)*at.get(i);
        }
        return Math.copySign(1, sum);
    }
    
}
