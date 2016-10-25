package sample.data.jpa.service;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;

import sample.data.jpa.domain.FeatureCollection;

@Component
public class CoordinatesBean implements InitializingBean {
	private static Logger LOG = Logger.getLogger(CoordinatesBean.class);
	private String message = null;
	Map<String, List<BigDecimal[]>> coordinates = null;
	Map<String, List<BigDecimal[]>> zipCoordinates = null;
	Map<String, List<BigDecimal[]>> fayetteZipCoordinates = null;
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("intializing coordinates on startup");
		message = "Hello me";
		Type REVIEW_TYPE = new TypeToken<FeatureCollection>() {}.getType();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("CountiesAtlantaRegion.json").getFile());
			System.out.println("file path" + file);
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new FileReader(file));
			FeatureCollection fColl = gson.fromJson(reader, REVIEW_TYPE);
			LOG.info("size " + fColl);
			if (fColl != null && fColl.getFeatures() != null && fColl.getFeatures().size() > 0) {
				coordinates = fColl.getFeatures().stream().collect(Collectors.toMap(
						x -> x.getProperties().getName().toLowerCase(), x -> x.getGeometry().getCoordinates().get(0)));
			}
			
//			file = new File(classLoader.getResource("ZipcodesFultonRegion.json").getFile());
//			System.out.println("file path" + file);
//			reader = new JsonReader(new FileReader(file));
//			fColl = gson.fromJson(reader, REVIEW_TYPE);
//			LOG.info("size " + fColl);
//			if (fColl != null && fColl.getFeatures() != null && fColl.getFeatures().size() > 0) {
//				zipCoordinates = fColl.getFeatures().stream().collect(Collectors.toMap(
//						x -> x.getProperties().getName().toLowerCase(), x -> x.getGeometry().getCoordinates().get(0)));
//			}
			
			file = new File(classLoader.getResource("ZipcodesAtlantaRegion.json").getFile());
			System.out.println("file path" + file);
			reader = new JsonReader(new FileReader(file));
			fColl = gson.fromJson(reader, REVIEW_TYPE);
			LOG.info("size " + fColl);
			if (fColl != null && fColl.getFeatures() != null && fColl.getFeatures().size() > 0) {
				zipCoordinates = fColl.getFeatures().stream().collect(Collectors.toMap(
						x -> x.getProperties().getName().toLowerCase(), x -> x.getGeometry().getCoordinates().get(0)));
			}
			
		} catch (Exception fe) {
			LOG.error("Error while fetching coordinates : " + fe.getMessage());
			fe.printStackTrace();
		}

	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, List<BigDecimal[]>> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Map<String, List<BigDecimal[]>> coordinates) {
		this.coordinates = coordinates;
	}
	public Map<String, List<BigDecimal[]>> getZipCoordinates() {
		return zipCoordinates;
	}
	public void setZipCoordinates(Map<String, List<BigDecimal[]>> zipCoordinates) {
		this.zipCoordinates = zipCoordinates;
	}
	public Map<String, List<BigDecimal[]>> getFayetteZipCoordinates() {
		return fayetteZipCoordinates;
	}
	public void setFayetteZipCoordinates(Map<String, List<BigDecimal[]>> fayetteZipCoordinates) {
		this.fayetteZipCoordinates = fayetteZipCoordinates;
	}
	
	
}
