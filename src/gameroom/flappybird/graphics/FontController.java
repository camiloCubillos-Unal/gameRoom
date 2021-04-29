package gameroom.flappybird.graphics;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class FontController {
    
    private static Font ttfBase = null;
    private static Font telegraficoFont = null;
    private static InputStream myStream = null;
    private Font font = null;
    
    public Font createFont(String fontFilePath) {
            try {
                InputStream myStream = new BufferedInputStream(new FileInputStream(fontFilePath));
                ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
                telegraficoFont = ttfBase.deriveFont(Font.PLAIN, 30);               
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Font not loaded.");
            }
            return telegraficoFont;
    }
}
