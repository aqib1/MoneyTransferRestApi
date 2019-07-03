package com.initdata.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.moneytranfer.utility.DataUtils;

public class InitDataServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6465557296885950233L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataUtils.getInstance().buildData();
	}
}
