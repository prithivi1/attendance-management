package com.company;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Summary {
    ApplicationFrame applicationFrame;

    static int z = 0;
    static int o = 0;

    Summary(String title,int absent,int present) {

        this.z = absent;
        this.o = present;
        SummaryMethod(title);
    }

    public void SummaryMethod(String title) {

        applicationFrame = new ApplicationFrame(title);
        applicationFrame.setContentPane(createDemoPanel());
        applicationFrame.setSize(800, 600);
        RefineryUtilities.centerFrameOnScreen(applicationFrame);
        applicationFrame.setVisible(true);
    }

    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Absent", new Double(z));
        dataset.setValue("Present", new Double(o));
        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("ATTENDANCE", dataset, true, true, false);
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

}