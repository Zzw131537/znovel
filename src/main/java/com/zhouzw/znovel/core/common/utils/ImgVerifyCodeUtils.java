package com.zhouzw.znovel.core.common.utils;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

@UtilityClass
public class ImgVerifyCodeUtils {

    /**
     * 随机产生只有数字的字符串
     */
    private final String randNumber = "0123456789";

    /**
     * 图片宽
     */
    private final int width = 100;

    /**
     * 图片高
     */
    private final int height = 38;

    private final Random random = new Random();

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixed",Font.PLAIN,23);
    }

    /**
     * 生成校验码图片
     */
    public String genVerifyCodeImg(String verifyCode) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        g.fillRect(0, 0, width, height);

        g.setColor(new Color(204,204,204));
        //干扰线数量
        int lineSize = 40;

        for(int i=0;i<lineSize;i++){
            drawLine(g);
        }

        // 绘制随机字符串
        drawString(g,verifyCode);
        g.dispose();
        // 将图片转换成Base64字符串
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        ImageIO.write(image,"JPEG",stream);

        return Base64.getEncoder().encodeToString(stream.toByteArray());

    }

    /**
     * 绘制字符串
     */
    private void drawString(Graphics g,String verifyCode){
        for (int i = 1; i <= verifyCode.length(); i++) {
            g.setFont(getFont());
            g.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            g.translate(random.nextInt(3), random.nextInt(3));
            g.drawString(String.valueOf(verifyCode.charAt(i - 1)), 13 * i, 23);
        }
    }


    /**
     * 绘制干扰线
     */
    private void drawLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(23);
        int yl = random.nextInt(23);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机校验码
     */
    public String getRandVerifyCode(int num) {
        int randNumberSize = randNumber.length();
        StringBuilder verifyCode = new StringBuilder();
        for (int i = 0; i < num; i++) {
            String rand = String.valueOf(randNumber.charAt(random.nextInt(randNumberSize)));
            verifyCode.append(rand);
        }
        return verifyCode.toString();
    }
}
