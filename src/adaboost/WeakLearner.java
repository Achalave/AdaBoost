package adaboost;

//@author Michael Haertling
public interface WeakLearner {

    public double getClassification(double[] aset);

    public String getID();

}
