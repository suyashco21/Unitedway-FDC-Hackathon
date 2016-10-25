package sample.data.jpa.service;

import sample.data.jpa.response.ChildMeasureResponseByCounty;

public interface CountyService {

	public ChildMeasureResponseByCounty findAll(String state, String city, String county);
}
