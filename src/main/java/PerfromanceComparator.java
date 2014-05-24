import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerfromanceComparator extends ApplicationFrame {

    public PerfromanceComparator(String title, int maxNumberOfTasks) throws ExecutionException, InterruptedException {
        super(title);
        XYDataset dataset = createDataset(maxNumberOfTasks);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static XYDataset createDataset(int maxNumberOfTasks) throws ExecutionException, InterruptedException {
        int screenWidth = 1500;
        int screenHeight = 1000;
        XYSeries series1 = new XYSeries("");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=1;i<=maxNumberOfTasks;i++){
            MandelBrotGenerator generator = new MandelBrotGenerator(screenWidth,screenHeight,1000,i,executorService);
            series1.add(i, generator.getComputationTime());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "MandelBrot Performance Comparison", // chart title
                "Number of Tasks", // x axis label
                "ComputationTime in MilliSeconds", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                true, // tooltips
                false // urls
        );
        return chart;
    }


    public static void main(String[] args) {
        PerfromanceComparator comparator = null;
        try {
            comparator = new PerfromanceComparator("",100);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        comparator.pack();
        RefineryUtilities.centerFrameOnScreen(comparator);
        comparator.setSize(800,600);
        comparator.setVisible(true);
    }
}