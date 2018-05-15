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

    public SpriteSheetHandler(String path) {
        try {
            image = ImageIO.read(new File(path));
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

    }

    public BufferedImage crop(int col, int row, int w, int h) {
        return image.getSubimage(col * 16, row * 16, w, h);
    }
}
