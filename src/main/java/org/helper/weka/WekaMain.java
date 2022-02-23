package org.helper.weka;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class WekaMain {
    public static void main(String[] args) throws Exception {
        WekaConfiguration wekaConfiguration = new WekaConfiguration("src/main/resources/data.arff");
        Instances data = wekaConfiguration.loadARFF();

        WekaTrainer wekaTrainer = new WekaTrainer(data);
        wekaTrainer.trainModel(new RandomForest());

        Evaluation eTest = new Evaluation(data);
        eTest.evaluateModel(wekaTrainer.getClassifier(), data);
        String strSummary = eTest.toSummaryString();
        System.out.println(strSummary);

        System.out.println(data.numAttributes());

        WekaCSGO weka = new WekaCSGO(wekaTrainer.getClassifier());

        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("csgo.ai"));
            oos.writeObject(weka);
            oos.close();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }
}
