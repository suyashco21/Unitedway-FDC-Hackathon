package sample.data.jpa.service;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import sample.data.jpa.domain.FeatureCollection;
import sample.data.jpa.response.County;
import sample.data.jpa.response.ZipCode;

public class ZipCodeServiceImpl {
	private static Logger LOG = Logger.getLogger(ZipCodeServiceImpl.class);
	//public static Map<String, List<BigDecimal[]>> coordinates = null;
	@Autowired
	CoordinatesBean coordinateBean;
	
	ZipCodeServiceImpl(){
//		Type REVIEW_TYPE = new TypeToken<FeatureCollection>() {}.getType();
//		try {
//			ClassLoader classLoader = getClass().getClassLoader();
//			File file = new File(classLoader.getResource("ZipcodesFultonRegion.json").getFile());
//			System.out.println("file path" + file);
//			Gson gson = new Gson();
//			JsonReader reader = new JsonReader(new FileReader(file));
//			FeatureCollection fColl = gson.fromJson(reader, REVIEW_TYPE);
//			LOG.info("size " + fColl);
//			if (fColl != null && fColl.getFeatures() != null && fColl.getFeatures().size() > 0) {
//				coordinates = fColl.getFeatures().stream().collect(Collectors.toMap(
//						x -> x.getProperties().getName().toLowerCase(), x -> x.getGeometry().getCoordinates().get(0)));
//			}
//		} catch (Exception fe) {
//			LOG.error("Error while fetching coordinates : " + fe.getMessage());
//			fe.printStackTrace();
//		}
	}
	
	public List<ZipCode> getZipCodes(String name){
		
		List<ZipCode> zipCodeList = new ArrayList<ZipCode>();
		for (Map.Entry<String, List<BigDecimal[]>> entry : coordinateBean.getZipCoordinates().entrySet()) {
		    ZipCode zipCode = new ZipCode();
		    zipCode.setZipCode(entry.getKey());
			zipCode.setCoordinates(entry.getValue());
			zipCodeList.add(zipCode);
		}
		
		return zipCodeList;
	}

}
