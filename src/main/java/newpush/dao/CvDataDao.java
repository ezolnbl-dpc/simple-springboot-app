package newpush.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import newpush.model.CvData;

public interface CvDataDao extends JpaRepository<CvData, Integer> {

	public CvData findOneByFirstNameAndLastNameAndCountryName(String firstName, String lastName, String CountryName);
}