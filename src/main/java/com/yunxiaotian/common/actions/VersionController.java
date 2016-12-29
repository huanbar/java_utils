package com.yunxiaotian.common.actions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 版本说明控制器
 * 
 * @Description: 描述各版本更新提示
 * @author zhou
 * @date 2016-12-18 14:44:35
 *
 */
@Controller
public class VersionController {

	@RequestMapping({ "/" })
	public String index() {
		return "index";
	}

	/**
	 * 返回版本信息
	 * 
	 * @Description:
	 * @return Object 返回类型
	 * @author zhou
	 */
	@RequestMapping(value = "/version")
	@ResponseBody
	public Object version() {
		Map<String, Object> version = new HashMap<String, Object>();
		version.put("version", "1.0.1");
		return version;
	}
}