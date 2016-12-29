package com.yunxiaotian.common.actions;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunxiaotian.common.util.ChengyuCaptchaHelper;

/**
 * 验证码控制器
 *  
 * @Description: 验证码
 * @author zhou
 * @date 2016-12-18
 *
 */
@Controller
@RequestMapping(value = "/captcha")
public class CaptchaController {
	private static Logger logger = LoggerFactory.getLogger(CaptchaController.class);

	/**
	 * 成语拆拆猜
	 *  
	 * @Description:四字成语,中间一个被挖空,猜吧
	 * @return Object 返回类型
	 * @author zhou
	 */
	@RequestMapping(value = "/cyccc")
	public Object cyccc(HttpServletRequest req, HttpServletResponse resp) {
		logger.debug("调用了成语拆拆猜");
		BufferedImage bufimg = ChengyuCaptchaHelper.getImageChallenge();
		resp.setHeader("Cache-Control", "no-store");
		resp.setHeader("Pragma", "no-chche");
		resp.setDateHeader("Expires", 0L);
		resp.setContentType("image/jpeg");
		try {
			ImageIO.write(bufimg, "jpeg", resp.getOutputStream());
		} catch (IOException e) {
			logger.error("输出验证码给用户时发生异常:" + e.getMessage());
		}
		return null;
	}
}