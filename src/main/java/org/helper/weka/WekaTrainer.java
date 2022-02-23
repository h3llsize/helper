package org.helper.weka;

import weka.classifiers.Classifier;
import weka.core.Instances;

public class WekaTrainer {
    private final Instances instances;
    private Classifier classifier;

    public WekaTrainer(Instances instances) throws Exception {
        this.instances = instances;
    }

    public void trainModel(Classifier classifier) throws Exception {
        classifier.buildClassifier(instances);
        this.classifier = classifier;
    }

    public Classifier getClassifier() {
        return classifier;
    }
}
