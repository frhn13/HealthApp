package Constants;

import java.awt.*;

public class Constants {
    public static class Fonts {
        public static final Font LOGIN_FONT = new Font("MV Boli", Font.PLAIN, 25);
        public static final Font MENU_FONT = new Font("Mv Boli", Font.ITALIC, 25);
        public static final Font BMI_FONT = new Font("Mv Boli", Font.BOLD, 25);
        public static final Font TITLE_FONT = new Font("Mv Boli", Font.BOLD, 40);
    }
    public static class FrameSizes {
        public static final int DEFAULT_FRAME_WIDTH = 800;
        public static final int DEFAULT_FRAME_HEIGHT = 600;
        public static final Dimension LOGIN_SIZE = new Dimension(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        public static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        public static final Dimension LOGIN_PANEL_SIZE = new Dimension(DEFAULT_FRAME_WIDTH, (int) DEFAULT_FRAME_HEIGHT/3);
        public static final Dimension SIGNUP_PANEL_SIZE = new Dimension(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT/4);
    }
}
