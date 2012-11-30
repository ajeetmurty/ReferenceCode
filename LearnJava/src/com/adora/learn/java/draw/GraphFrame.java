package com.adora.learn.java.draw;

import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.Rotation;

public class GraphFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public GraphFrame(String applicationTitle, String chartTitle) {
		super(applicationTitle);

		JFreeChart chart = ChartFactory.createXYLineChart("Line Chart Demo", "X", "Y", createDataset(), PlotOrientation.HORIZONTAL , true, false, false);
		XYPlot plot = (XYPlot) chart.getPlot();

		// draw a horizontal line across the chart at y == 0

		ChartPanel chartPanel = new ChartPanel(null);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
		// add it to our application
		setContentPane(chartPanel);
	}

	// Creates a sample dataset
	private XYSeriesCollection createDataset() {
		XYSeries series1 = new XYSeries("function");
		series1.add(1.0, 1.0);
		series1.add(2.0, 4.0);
		series1.add(3.0, -3.0);
		series1.add(4.0, 5.0);
		series1.add(5.0, 5.0);
		series1.add(6.0, 7.0);
		series1.add(7.0, 7.0);
		series1.add(8.0, 8.0);
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		return dataset;
	}

	// Creates a chart
	private JFreeChart createChart(PieDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}
}
