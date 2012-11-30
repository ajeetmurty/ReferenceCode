package com.adora.learn.java.draw;

import javax.swing.JFrame;

import com.adora.learn.java.util.Logger;

public class GraphMain {
	private Logger logp = Logger.getInstance();

	public static void main(String[] args) {
		new GraphMain();
	}

	public GraphMain() {
		logp.log("start");
		drawGraph();
		logp.log("end");
	}

	private void drawGraph() {
		logp.log("start drawing...");
		GraphFrame demo = new GraphFrame("Comparison", "Which operating system are you using?");
		demo.pack();
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		demo.setLocation(200, 200);
		demo.setVisible(true);
		
		
		// JFrame f = new JFrame();
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// f.add(new GraphPanel());
		// f.setSize(800, 800);
		// f.setLocation(100, 100);
		// f.setVisible(true);
	}
}
