package com.scalable.c2kclient.handlers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdel.util.helper.JacksonUtil;
import com.scalable.c2kclient.excephandler.ExceptionHandler;

public abstract class BeansMessageHandler<T> extends SafelyMessageHandler {

	protected static Logger log = LoggerFactory.getLogger(BeansMessageHandler.class);
	private Class<T> clazz;

	public BeansMessageHandler(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	public BeansMessageHandler(Class<T> clazz, ExceptionHandler excepHandler) {
		super(excepHandler);
		this.clazz = clazz;
	}

	public BeansMessageHandler(Class<T> clazz, List<ExceptionHandler> excepHandlers) {
		super(excepHandlers);
		this.clazz = clazz;
	}

	protected void doExecute(String message) throws Exception {
		List<T> beans = JacksonUtil.jsonToGenericContainer(message, List.class, clazz);
		doExecuteBeans(beans);
	}

	protected abstract void doExecuteBeans(List<T> bean);

}