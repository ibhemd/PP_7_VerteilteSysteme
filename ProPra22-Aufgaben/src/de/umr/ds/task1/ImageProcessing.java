package de.umr.ds.task1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {

	/**
	 * Loads an image from the given file path
	 *
	 * @param path The path to load the image from
	 * @return BufferedImage
	 */
	private static BufferedImage loadImage(String path) throws IOException {
		// TODO Task 1a)
		return ImageIO.read(new File(path));
	}

	/**
	 * Converts the input RGB image to a single-channel gray scale array.
	 * 
	 * @param img The input RGB image
	 * @return A 2-D array with intensities
	 */
	private static int[][] convertToGrayScaleArray(BufferedImage img) {
		// TODO Task 1b)
		int[][] res = new int[img.getWidth()][img.getHeight()];
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color color = new Color(img.getRGB(x,y));
				res[x][y] = (int) (0.299*color.getRed() + 0.587*color.getGreen() + 0.114*color.getBlue());
			}
		}
		return res;
	}

	/**
	 * Converts a single-channel (gray scale) array to an RGB image.
	 * 
	 * @param img The input image array
	 * @return BufferedImage
	 */
	private static BufferedImage convertToBufferedImage(int[][] img) {
		// TODO Task 1c)
		int w = img.length; // 600px
		int h = img[0].length; // 400px
		BufferedImage res = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int i = img[x][y];
				Color color = new Color(i,i,i);
				int c = color.getRGB();
				res.setRGB(x,y,c);
			}
		}
		return res;
	}

	/**
	 * Saves an image to the given file path
	 *
	 * @param img The RGB image
	 * @param path The path to save the image to
	 */
	private static void saveImage(BufferedImage img, String path) throws IOException {
		// TODO Task 1c)
		ImageIO.write(img, "jpg", new File(path));
	}

	/**
	 * Converts input image to gray scale and applies the kernel.
	 * 
	 * @param img The RGB input image
	 * @param kernel The kernel to apply
	 * @return The convolved gray-scale image
	 */
	private static BufferedImage filter(BufferedImage img, Kernel kernel) {
		// TODO Task 1f)
		int[][] oldArray = convertToGrayScaleArray(img);
		int[][] newArray = kernel.convolve(oldArray);
		BufferedImage res = convertToBufferedImage(newArray);
		return resizeImage(res, img.getWidth(),img.getHeight());
	}

	// TODO Task 1g)
	private static BufferedImage resizeImage(BufferedImage img, int w, int h) {
		BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = res.createGraphics();
		g2d.drawImage(img, 0,0,w, h, null);
		g2d.dispose();
		return res;
	}


	public static void main(String[] args) throws IOException {
		// TODO
		BufferedImage image = loadImage("example.jpg");
		int[][] grayArr = convertToGrayScaleArray(image);
		BufferedImage newImage = convertToBufferedImage(grayArr);
		saveImage(newImage, "exampleGRAY.jpg");

		Kernel GaussianBlur = Kernels.GaussianBlur5x5();
		BufferedImage GaussianBlurImage = filter(image, GaussianBlur);
		saveImage(GaussianBlurImage, "GaussianBlurImage.jpg");

		Kernel BoxBlur = Kernels.BoxBlur3x3();
		BufferedImage BoxBlurImage = filter(image, BoxBlur);
		saveImage(BoxBlurImage, "BoxBlurImage.jpg");

		Kernel MotionBlur = Kernels.MotionBlur();
		BufferedImage MotionBlurImage = filter(image, MotionBlur);
		saveImage(MotionBlurImage, "MotionBlurImage.jpg");

		Kernel Sharpen = Kernels.Sharpen();
		BufferedImage SharpenImage = filter(image, Sharpen);
		saveImage(SharpenImage, "SharpenImage.jpg");

		Kernel self1 = Kernels.self1();
		BufferedImage Self1Image = filter(image, self1);
		saveImage(Self1Image, "Self1Image.jpg");

		Kernel self2 = Kernels.self2();
		BufferedImage Self2Image = filter(image, self2);
		saveImage(Self2Image, "Self2Image.jpg");

	}

}
