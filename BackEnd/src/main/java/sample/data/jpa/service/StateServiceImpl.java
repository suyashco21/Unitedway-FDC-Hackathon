package sample.data.jpa.service;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.math.*;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import sample.data.jpa.domain.ChidCategoryEntity;
import sample.data.jpa.domain.CityMasterEntity;
import sample.data.jpa.domain.CommunityCategoryEntity;
import sample.data.jpa.domain.CountyMasterEntity;
import sample.data.jpa.domain.FamilyCategoryEntity;
import sample.data.jpa.domain.FeatureCollection;
import sample.data.jpa.domain.StateMasterEntity;
import sample.data.jpa.response.ChildMeasureResponseByCity;
import sample.data.jpa.response.County;
import sample.data.jpa.response.ErrorHandler;

@Component("stateService")
@Transactional
public class StateServiceImpl implements StateService {

	private static Logger LOG = Logger.getLogger(StateServiceImpl.class);

	//private static Type REVIEW_TYPE = new TypeToken<FeatureCollection>() {}.getType();
	private StateRepository stateRepository;
	private CountyRepository countyRepository;
	private CityRepository cityRepository;
	private ChildCategoryRepository childCategoryRepository;
	private FamilyCategoryRepository familyCategoryRepository;
	private CommunityCategoryRepository communityCategoryRepository;
	//public static Map<String, List<BigDecimal[]>> coordinates = null;
	
	@Autowired
	CoordinatesBean coordinateBean;

	public StateServiceImpl(StateRepository stateRepository, CountyRepository countyRepository,
			CityRepository cityRepository, ChildCategoryRepository childCategoryRepository,
			FamilyCategoryRepository familyCategoryRepository,
			CommunityCategoryRepository communityCategoryRepository) {
		this.stateRepository = stateRepository;
		this.countyRepository = countyRepository;
		this.cityRepository = cityRepository;
		this.childCategoryRepository = childCategoryRepository;
		this.familyCategoryRepository = familyCategoryRepository;
		this.communityCategoryRepository = communityCategoryRepository;
	}

	@Override
	public ChildMeasureResponseByCity findAll(String state, String city) {
		LOG.info("  Got from the coordiante bean "+coordinateBean.getMessage());
		List<CityMasterEntity> cityMasterEntity = null;
		List<CountyMasterEntity> countyMasterEntity = null;
		List<ChidCategoryEntity> childCategoryEntities = null;
		List<FamilyCategoryEntity> familyCategoryEntity = null;
		List<CommunityCategoryEntity> communityCategoryEntity = null;
		double cityChildMeasure = 0.0;
		double cityFamilyMeasure = 0.0;
		double cityCommunityMeasure = 0.0;
		boolean statefound = false;
		boolean cityfound = false;

		ChildMeasureResponseByCity childMeasureResponse = new ChildMeasureResponseByCity();
		List<County> countyList = new ArrayList<County>();
		String cityNameValue = "";

		List<StateMasterEntity> stateMasterEntity = stateRepository.findAll();

		if (null != stateMasterEntity && stateMasterEntity.size() > 0) {
			for (StateMasterEntity states : stateMasterEntity) {
				if (states.getStateName().equalsIgnoreCase(state)) {
					cityMasterEntity = cityRepository.findByStateMasterEntity(states);
					statefound = true;
					break;
				}
			}
			if (!statefound) {
				return errorHandling(childMeasureResponse, "00001", "State name not found in record.");
			}

			if (null != cityMasterEntity && cityMasterEntity.size() > 0) {
				for (CityMasterEntity cities : cityMasterEntity) {
					System.out.println("City Name" + cities.getCityName());
					if (cities.getCityName().equalsIgnoreCase(city)) {
						cityNameValue = cities.getCityName();
						cityfound = true;
						countyMasterEntity = countyRepository.findByCityMasterEntity(cities);
						break;
					}
				}

				if (!cityfound) {
					return errorHandling(childMeasureResponse, "00002", "City name not found in record.");
				}

				if (null != countyMasterEntity && countyMasterEntity.size() > 0) {
					for (CountyMasterEntity counties : countyMasterEntity) {
						System.out.println("County Name" + counties.getCountyName());
						childCategoryEntities = childCategoryRepository.findByCountyMasterEntity(counties);

						double countyChildMeasure = 0;
						County county = new County();
						if (coordinateBean.getCoordinates().containsKey(counties.getCountyName().toLowerCase())) {
							county.setCoordinates(coordinateBean.getCoordinates().get(counties.getCountyName().toLowerCase()));
						}
						if (null != childCategoryEntities && childCategoryEntities.size() > 0) {
							for (ChidCategoryEntity category : childCategoryEntities) {
								countyChildMeasure = countyChildMeasure + Double.parseDouble(category.getPercentage());
							}
							countyChildMeasure = countyChildMeasure / childCategoryEntities.size();
							cityChildMeasure = (cityChildMeasure + countyChildMeasure);
							System.out.println("countyChildWellBeingScore is " + countyChildMeasure);
							county.setCountyName(counties.getCountyName());
							county.setCountyChildMeasure(countyChildMeasure);
						} else {
							errorHandling(childMeasureResponse, "00007", "Child mesaure not found in record.");
						}

						familyCategoryEntity = familyCategoryRepository.findByCountyMasterEntity(counties);
						double countyFamilyMeasure = 0;
						if (null != familyCategoryEntity && familyCategoryEntity.size() > 0) {
							for (FamilyCategoryEntity category : familyCategoryEntity) {
								countyFamilyMeasure = countyFamilyMeasure
										+ Double.parseDouble(category.getPercentage());
							}
							countyFamilyMeasure = countyFamilyMeasure / familyCategoryEntity.size();
							cityFamilyMeasure = (cityFamilyMeasure + countyFamilyMeasure);
							System.out.println("countyFamilyWellBeingScore is " + countyFamilyMeasure);
							county.setCountyFamilyMeasure(countyFamilyMeasure);
						} else {
							errorHandling(childMeasureResponse, "00006", "Family mesaure not found in record.");
						}

						communityCategoryEntity = communityCategoryRepository.findByCountyMasterEntity(counties);
						double countyCommunityMeasure = 0;
						if (null != communityCategoryEntity && communityCategoryEntity.size() > 0) {
							for (CommunityCategoryEntity category : communityCategoryEntity) {
								countyCommunityMeasure = countyCommunityMeasure
										+ Double.parseDouble(category.getPercentage());
							}
							countyCommunityMeasure = countyCommunityMeasure / communityCategoryEntity.size();
							cityCommunityMeasure = (cityCommunityMeasure + countyCommunityMeasure);
							System.out.println("countyCommunityWellBeingScore is " + countyCommunityMeasure);
							county.setCountyCommunityMeasure(countyCommunityMeasure);
							countyList.add(county);
						} else {
							errorHandling(childMeasureResponse, "00005", "Community measure not found in record.");
						}

					}
					childMeasureResponse.setCityName(cityNameValue);
					childMeasureResponse.setCityChildMeasure(cityChildMeasure / countyMasterEntity.size());
					childMeasureResponse.setCityFamilyMeasure(cityFamilyMeasure / countyMasterEntity.size());
					childMeasureResponse.setCityCommunityMeasure(cityCommunityMeasure / countyMasterEntity.size());
				} else {
					errorHandling(childMeasureResponse, "00003", "County name not found in record.");
				}
			} else {
				errorHandling(childMeasureResponse, "00002", "City name not found in record.");
			}
		} else {
			errorHandling(childMeasureResponse, "00001", "State name not found in record.");
		}
		childMeasureResponse.setCountyList(countyList);
		return childMeasureResponse;
	}

	private ChildMeasureResponseByCity errorHandling(ChildMeasureResponseByCity childMeasureResponse, String errorCode,
			String ErrorMessage) {
		ErrorHandler handler = new ErrorHandler();
		handler.setErrorCode(errorCode);
		handler.setErrorMessage(ErrorMessage);
		childMeasureResponse.setErrorHandler(handler);
		return childMeasureResponse;
	}
}
