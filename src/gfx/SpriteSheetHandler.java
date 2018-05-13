package gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheetHandler {
    private String path;
    private int width;
    private int height;
    private int[] pixels;
    BufferedImage image;

    public SpriteSheetHandler() {

        try {
            image = ImageIO.read(new File("res/player16x16.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            return;
        }

        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = image.getRGB(0, 0, width, height, null, 0, width);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (
                    pixels[i] & 0xff / 64
            );
        }

        for (int i = 0; i < 20; i++) {
            System.out.println(pixels[i]);
        }
    }

    public BufferedImage crop(int col, int row, int w, int h) {
        return image.getSubimage(col * 16, row * 16, w, h);
    }
}
