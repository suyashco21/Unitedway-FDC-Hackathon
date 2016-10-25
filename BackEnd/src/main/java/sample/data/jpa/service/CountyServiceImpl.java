package sample.data.jpa.service;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import sample.data.jpa.domain.ChidCategoryEntity;
import sample.data.jpa.domain.ChidCategoryZipCodeEntity;
import sample.data.jpa.domain.CityMasterEntity;
import sample.data.jpa.domain.CommunityCategoryEntity;
import sample.data.jpa.domain.CommunityCategoryZipCodeEntity;
import sample.data.jpa.domain.CountyMasterEntity;
import sample.data.jpa.domain.FamilyCategoryEntity;
import sample.data.jpa.domain.FamilyCategoryZipCodeEntity;
import sample.data.jpa.domain.FeatureCollection;
import sample.data.jpa.domain.StateMasterEntity;
import sample.data.jpa.domain.ZipCodeMasterEntity;
import sample.data.jpa.response.ChildMeasureResponseByCounty;
import sample.data.jpa.response.County;
import sample.data.jpa.response.ErrorHandler;
import sample.data.jpa.response.ZipCode;

@Component("countyService")
@Transactional
public class CountyServiceImpl implements CountyService {

	private static Logger LOG = Logger.getLogger(CountyServiceImpl.class);

	private StateRepository stateRepository;
	private CountyRepository countyRepository;
	private CityRepository cityRepository;
	private ZipCodeRepository zipCodeRepository;
	private ChildCategoryRepository childCategoryRepository;
	private ChildCategoryZipCodeRepository childCategoryZipCodeRepository;
	private FamilyCategoryRepository familyCategoryRepository;
	private FamilyCategoryZipCodeRepository familyCategoryZipCodeRepository;
	private CommunityCategoryRepository communityCategoryRepository;
	private CommunityCategoryZipCodeRepository communityCategoryZipCodeRepository;
	@Autowired
	CoordinatesBean coordinateBean;

	public CountyServiceImpl(StateRepository stateRepository, CountyRepository countyRepository,
			CityRepository cityRepository, ZipCodeRepository zipCodeRepository,ChildCategoryRepository childCategoryRepository,
			FamilyCategoryRepository familyCategoryRepository,
			CommunityCategoryRepository communityCategoryRepository, 
			ChildCategoryZipCodeRepository childCategoryZipCodeRepository,
			FamilyCategoryZipCodeRepository familyCategoryZipCodeRepository,
			CommunityCategoryZipCodeRepository communityCategoryZipCodeRepository) {
		this.stateRepository = stateRepository;
		this.countyRepository = countyRepository;
		this.cityRepository = cityRepository;
		this.zipCodeRepository = zipCodeRepository;
		this.childCategoryRepository = childCategoryRepository;
		this.childCategoryZipCodeRepository = childCategoryZipCodeRepository;
		this.familyCategoryRepository = familyCategoryRepository;
		this.familyCategoryZipCodeRepository = familyCategoryZipCodeRepository;
		this.communityCategoryRepository = communityCategoryRepository;
		this.communityCategoryZipCodeRepository = communityCategoryZipCodeRepository;
	}

	@Override
	public ChildMeasureResponseByCounty findAll(String state, String city, String county) {
		List<CityMasterEntity> cityMasterEntity = null;
		CountyMasterEntity countyMasterEntity = null;
		List<ChidCategoryEntity> childCategoryEntities = null;
		List<FamilyCategoryEntity> familyCategoryEntity = null;
		List<CommunityCategoryEntity> communityCategoryEntity = null;
		List<ZipCodeMasterEntity> zipCodeMasterEntity = null;
		double cityChildMeasure = 0.0;
		double cityFamilyMeasure = 0.0;
		double cityCommunityMeasure = 0.0;
		boolean statefound = false;
		boolean cityfound = false;

		ChildMeasureResponseByCounty childMeasureResponse = new ChildMeasureResponseByCounty();
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
					LOG.debug(cities.getCityName());
					if (cities.getCityName().equalsIgnoreCase(city)) {
						cityNameValue = cities.getCityName();
						cityfound = true;
						countyMasterEntity = countyRepository.findByCountyName(county.toLowerCase());
						break;
					}
				}

				if (!cityfound) {
					return errorHandling(childMeasureResponse, "00002", "City name not found in record.");
				}

				if (null != countyMasterEntity) {
					System.out.println(countyMasterEntity.getCountyName());
					
					
					double countyChildMeasure = 0;
					County countyObject = new County();
					if (coordinateBean.getCoordinates().containsKey(countyMasterEntity.getCountyName().toLowerCase())) {
						countyObject.setCoordinates(coordinateBean.getCoordinates().get(countyMasterEntity.getCountyName().toLowerCase()));
					}
					
					//get zip codes
					List<ZipCode> zipCodeList = new ArrayList<ZipCode>();
					zipCodeMasterEntity = zipCodeRepository.findByCountyMasterEntity(countyMasterEntity);
					if (zipCodeMasterEntity != null){
						LOG.info("ZipCode in this county: "+zipCodeMasterEntity.size());
					}
					for (ZipCodeMasterEntity z : zipCodeMasterEntity){
						List<ChidCategoryZipCodeEntity> childMeasure = childCategoryZipCodeRepository.findByZipCodeMasterEntity(z);
						List<FamilyCategoryZipCodeEntity> familyMeasure = familyCategoryZipCodeRepository.findByZipCodeMasterEntity(z);
						List<CommunityCategoryZipCodeEntity> communityMeasure = communityCategoryZipCodeRepository.findByZipCodeMasterEntity(z);
						ZipCode zipCode = new ZipCode();
						zipCode.setZipCode(z.getZipCode());
						if (childMeasure != null && childMeasure.size() > 0)
							zipCode.setCountyChildMeasure(Double.parseDouble(childMeasure.get(0).getPercentage()));
						if (familyMeasure != null && familyMeasure.size() > 0)
							zipCode.setCountyFamilyMeasure(Double.parseDouble(familyMeasure.get(0).getPercentage()));
						if (communityMeasure != null && communityMeasure.size() > 0)
							zipCode.setCountyCommunityMeasure(Double.parseDouble(communityMeasure.get(0).getPercentage()));
						if (coordinateBean.getZipCoordinates().containsKey(z.getZipCode().toLowerCase())) {
							zipCode.setCoordinates(coordinateBean.getZipCoordinates().get(z.getZipCode().toLowerCase()));
						}
						zipCodeList.add(zipCode);
					}
					countyObject.setZipCodeList(zipCodeList);
					
					childCategoryEntities = childCategoryRepository.findByCountyMasterEntity(countyMasterEntity);
					if (null != childCategoryEntities && childCategoryEntities.size() > 0) {
						for (ChidCategoryEntity category : childCategoryEntities) {
							countyChildMeasure = countyChildMeasure + Double.parseDouble(category.getPercentage());
						}
						countyChildMeasure = countyChildMeasure / childCategoryEntities.size();
						cityChildMeasure = (cityChildMeasure + countyChildMeasure);
						System.out.println("countyChildWellBeingScore is " + countyChildMeasure);
						countyObject.setCountyName(countyMasterEntity.getCountyName());
						countyObject.setCountyChildMeasure(countyChildMeasure);
					} else {
						return errorHandling(childMeasureResponse, "00007", "Child mesaure not found in record.");
					}
					
					familyCategoryEntity = familyCategoryRepository.findByCountyMasterEntity(countyMasterEntity);
					double countyFamilyMeasure = 0;
					if (null != familyCategoryEntity && familyCategoryEntity.size() > 0) {
						for (FamilyCategoryEntity category : familyCategoryEntity) {
							countyFamilyMeasure = countyFamilyMeasure + Double.parseDouble(category.getPercentage());
						}
						countyFamilyMeasure = countyFamilyMeasure / familyCategoryEntity.size();
						cityFamilyMeasure = (cityFamilyMeasure + countyFamilyMeasure);
						System.out.println("countyFamilyWellBeingScore is " + countyFamilyMeasure);
						countyObject.setCountyFamilyMeasure(countyFamilyMeasure);
					} else {
						return errorHandling(childMeasureResponse, "00006", "Family mesaure not found in record.");
					}

					communityCategoryEntity = communityCategoryRepository.findByCountyMasterEntity(countyMasterEntity);
					double countyCommunityMeasure = 0;
					if (null != communityCategoryEntity && communityCategoryEntity.size() > 0) {
						for (CommunityCategoryEntity category : communityCategoryEntity) {
							countyCommunityMeasure = countyCommunityMeasure
									+ Double.parseDouble(category.getPercentage());
						}
						countyCommunityMeasure = countyCommunityMeasure / communityCategoryEntity.size();
						cityCommunityMeasure = (cityCommunityMeasure + countyCommunityMeasure);
						System.out.println("countyCommunityWellBeingScore is " + countyCommunityMeasure);
						countyObject.setCountyCommunityMeasure(countyCommunityMeasure);
						countyList.add(countyObject);
					} else {
						return errorHandling(childMeasureResponse, "00005", "Community measure not found in record.");
					}

					childMeasureResponse.setCityName(cityNameValue);
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

	private ChildMeasureResponseByCounty errorHandling(ChildMeasureResponseByCounty childMeasureResponse,
			String errorCode, String ErrorMessage) {
		ErrorHandler handler = new ErrorHandler();
		handler.setErrorCode(errorCode);
		handler.setErrorMessage(ErrorMessage);
		childMeasureResponse.setErrorHandler(handler);
		return childMeasureResponse;
	}

}
