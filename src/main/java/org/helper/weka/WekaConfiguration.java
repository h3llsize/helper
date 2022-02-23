package org.helper.weka;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class WekaConfiguration {
    private final String dataBasePath;

    public WekaConfiguration(String path)
    {
        this.dataBasePath = path;
    }

    public Instances loadARFF() throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(dataBasePath);
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);

        return data;
    }
}
