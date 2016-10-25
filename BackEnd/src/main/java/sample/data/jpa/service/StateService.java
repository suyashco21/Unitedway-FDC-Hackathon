package sample.data.jpa.service;

import sample.data.jpa.response.ChildMeasureResponseByCity;

public interface StateService {

	public ChildMeasureResponseByCity findAll(String state, String city);
}
