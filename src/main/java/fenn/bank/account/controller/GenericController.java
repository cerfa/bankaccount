package fenn.bank.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.cors.CorsUtils;

public abstract class GenericController extends CorsUtils {
	private static final Logger LOG = LoggerFactory.getLogger(GenericController.class.toString());

	protected void crossOriginComplianceSetting(HttpServletRequest request, HttpServletResponse response) {
		if(CorsUtils.isCorsRequest(request)) {
			LOG.info("origin specified request full {} ",request);
			LOG.info("origin specified caller entry {} ",request.getHeader("Origin"));
		}else {
			LOG.info("origin specified request  noCors {} ",request);
			LOG.info("origin specified caller header {} ",request.getHeader("Origin"));
			LOG.info("origin specified caller  method {} ",request.getMethod());
		}
	}

}
