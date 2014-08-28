package com.kariqu.uc.web;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * 图片验证码生成器
 */
public class ImageController extends AbstractController {

    private String registerView;

    private static int WIDTH = 100;
    private static int HEIGHT = 40;
    private static int NUM = 4;

    private static int RED = 240;
    private static int GREEN = 238;
    private static int BLUE = 229;


    /**
     * 生成图片验证码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedImage image = randomImage(response, request);
        ServletOutputStream out = response.getOutputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPG", bos);
        byte[] buf = bos.toByteArray();
        response.setContentLength(buf.length);
        out.write(buf);
        bos.close();
        out.close();
        return null;
    }

    private BufferedImage randomImage(HttpServletResponse response, HttpServletRequest request) {
        //Random r = new Random();
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(new Color(RED, GREEN, BLUE));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        String code = RandomStringUtils.randomNumeric(NUM);
        for (int i = 0; i < NUM; i++) {
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font(Integer.valueOf(Font.ITALIC).toString(), Font.ITALIC, HEIGHT + 10));
            g.drawString(code.substring(i, i + 1), (((i * WIDTH) / NUM) * 90) / 100, HEIGHT);
        }
        request.getSession().setAttribute("imageCode", code);
        return image;
    }

    public String getRegisterView() {
        return registerView;
    }

    public void setRegisterView(String registerView) {
        this.registerView = registerView;
    }

}
