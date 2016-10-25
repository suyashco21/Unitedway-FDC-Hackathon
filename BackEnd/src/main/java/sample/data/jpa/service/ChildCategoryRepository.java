package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import sample.data.jpa.domain.ChidCategoryEntity;
import sample.data.jpa.domain.ChidCategoryZipCodeEntity;
import sample.data.jpa.domain.CountyMasterEntity;
import sample.data.jpa.domain.ZipCodeMasterEntity;

public interface ChildCategoryRepository extends Repository<ChidCategoryEntity, Long>{

/*	Hotel findByCityAndName(City city, String name);

	@Query("select h.city as city, h.name as name, avg(r.rating) as averageRating "
			+ "from Hotel h left outer join h.reviews r where h.city = ?1 group by h")
	Page<HotelSummary> findByCity(City city, Pageable pageable);

	@Query("select r.rating as rating, count(r) as count "
			+ "from Review r where r.hotel = ?1 group by r.rating order by r.rating DESC")
	List<RatingCount> findRatingCounts(Hotel hotel);*/
	
	List<ChidCategoryEntity> findByCountyMasterEntity(CountyMasterEntity countyMasterEntity);
	List<ChidCategoryEntity> findById(long Id);
}
