package com.vinnotech.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins="*")
@RestController
public class IndexController {
	private static final String CLASSNAME = "IndexController";
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = { "/", "/{x:[\\w\\-]+}", "/{x:^(?!api$).*$}/**/{y:[\\w\\-]+}" })
	public ModelAndView indexPortal() {

		String methodName = "indexPortal";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return new ModelAndView("/index.html");

	}
}
