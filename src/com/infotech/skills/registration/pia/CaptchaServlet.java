package com.infotech.skills.registration.pia;

/**
 * Created By : Laxman Singh
 * Description: Servlet responsible for generating captcha
 * 
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.captcha.Captcha;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.DefaultTextProducer;

/*class RahulRender implements GimpyRenderer{
	@Override
	public void gimp(BufferedImage arg0) {	}
}*/
@SuppressWarnings("serial")
public class CaptchaServlet extends HttpServlet {

	protected final Log log = LogFactory.getLog(getClass());

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("At CaptchaServlet");
		try {
			char[] srcChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
			//DropShadowGimpyRenderer dsgr  = null;
			DefaultTextProducer dtp = new DefaultTextProducer(7, srcChars);
			//dsgr = new DropShadowGimpyRenderer();
			Captcha.Builder builder = new Captcha.Builder(200, 50);
			builder.addText(dtp);
			//builder.gimp(dsgr);
			//builder.gimp(new RahulRender());BlockGimpyRenderer
			builder.gimp();
			Captcha captcha = builder.build();
			// 200, 50 is the size, 6 is the number of letters to use, we choose
			// the shadow renderer.
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			// don't cache it , don't store it, expire after 0 second, so with
			// refresh new image loaded
			response.setContentType("image/jpeg");
			// image type
			CaptchaServletUtil.writeImage(response, captcha.getImage());
			// write the image
			request.getSession().setAttribute("CorrectAnswer", captcha.getAnswer());
			log.info("captcha generated == \t" + captcha.getAnswer());
			// store the answer for this in session
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

}
