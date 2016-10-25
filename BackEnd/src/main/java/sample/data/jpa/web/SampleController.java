/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.jpa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.HeatMapTransportParams;
import sample.data.jpa.response.ChildMeasureResponseByCity;
import sample.data.jpa.response.ChildMeasureResponseByCounty;
import sample.data.jpa.response.ErrorHandler;
import sample.data.jpa.service.CountyService;
import sample.data.jpa.service.StateService;

@Controller
@RequestMapping("/")

public class SampleController {

	@Autowired
	private StateService stateService;

	@Autowired
	private CountyService countyService;

	/**
	 * @param state
	 * @param city
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@Transactional
	@RequestMapping(value = "/{state}/{city}", method = RequestMethod.GET)
	public ChildMeasureResponseByCity findAllByStatesandCity(@PathVariable("state") String state,
			@PathVariable("city") String city, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("State is  " + state + "  City is " + city);
		ChildMeasureResponseByCity childMeasureResponse = stateService.findAll(state, city);
		populateHeaders(request, response);
		return childMeasureResponse;
	}

	@ResponseBody
	@RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
	public ErrorHandler showError() {
		ErrorHandler handler = new ErrorHandler();
		handler.setErrorCode("00004");
		handler.setErrorMessage("No Request Mapping Found.");
		return handler;
	}

	@ResponseBody
	@Transactional
	@RequestMapping(value = "/{state}/{city}/{county}", method = RequestMethod.GET)
	public ChildMeasureResponseByCounty findAllByStatesCityAndCounty(@PathVariable("state") String state,
			@PathVariable("city") String city, @PathVariable("county") String county, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("State is  " + state + "  City is " + city + "  county is " + county);
		ChildMeasureResponseByCounty childMeasureResponse = countyService.findAll(state, city, county);
		populateHeaders(request, response);
		return childMeasureResponse;
	}

	public static void populateHeaders(final HttpServletRequest request, final HttpServletResponse response) {
		response.setHeader(HeatMapTransportParams.SECURITY_HEADER_STRICT_TRANSPORT,
				"max-age=16070400; includeSubDomains");
		response.setHeader(HeatMapTransportParams.SECURITY_HEADER_CONTENT_SECURITY_POLICY, "default-src 'self'");
		response.setHeader(HeatMapTransportParams.SECURITY_HEADER_CONTENT_TYPE_OPTIONS, "nosniff");
		response.setHeader(HeatMapTransportParams.SECURITY_HEADER_XSS_PROTECTION, "1; mode=block");
		response.setHeader("Access-Control-Allow-Origin", "*");
	}
}
