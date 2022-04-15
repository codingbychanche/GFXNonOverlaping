package test;

import gfxNonOverlapping.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class MainTest {

	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;

	static public void main(String args[]) {

		RectArea r1 = new RectArea(0, 0, 50, 50);
		RectArea r2 = new RectArea(20, 10, 100, 280);
		RectArea r3 = new RectArea(80, 10, 380, 80);
		// RectArea r4 = new RectArea(200, 100, 15, 150);

		Rectangles l = new Rectangles(WIDTH, HEIGHT);

		RectArea r;
		r = l.add(r1);
		System.out.println("r1 added. Did overlap?=>" + r.didOverlap() + "     Should be shifted, but was not possible=>"
				+ r.didOverlapCouldNotBeShifted() + " x=" + r.getX() + "  y=" + r.getY());

		r = l.add(r2);
		System.out.println("r1 added.  Did overlap?=>" + r.didOverlap() + "     Should be shifted, but was not possible=>"
				+ r.didOverlapCouldNotBeShifted() + " x=" + r.getX() + "  y=" + r.getY());

		r = l.add(r3);
		System.out.println("r1 added.  Did overlap?=>" + r.didOverlap() + "     Should be shifted, but was not possible=>"
				+ r.didOverlapCouldNotBeShifted() + " x=" + r.getX() + "  y=" + r.getY());
		// l.add(r4);

		draw(l);
		show(l);

	}

	public static void draw(Rectangles l) {
		// Create an in memory Image

		BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

		// Grab the graphics object off the image
		Graphics2D graphics = img.createGraphics();

		graphics.setBackground(Color.WHITE);

		Stroke stroke = new BasicStroke(1f);
		graphics.setStroke(stroke);
		graphics.setColor(Color.BLACK);

		// Draw
		List<RectArea> rectangles = l.getObjectList();
		for (RectArea r : rectangles)
			graphics.drawRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());

		// Save to file.
		try {
			File outputfile = new File("Pic.png");
			ImageIO.write(img, "png", outputfile);
		} catch (Exception e) {
		}

	}

	private static void show(Rectangles l) {
		List<RectArea> rList = l.getObjectList();

		for (RectArea r : rList) {
			System.out.println("x=" + r.getX() + "  y=" + r.getY());
		}
	}
}
