package newpush.controller;

import static java.util.Arrays.asList;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import newpush.dao.CvDataDao;
import newpush.model.CvData;

@Controller
public class AppController {
	public static final List<Country> COUNTRIES = asList(new Country("Hungary"), new Country("Germany"),
			new Country("Ukraine"));

	@Autowired
	private CvDataDao cvDataDao;

	@RequestMapping({ "/", "/index" })
	public String index(Model model) {
		model.addAttribute("cvData", new CvData());
		model.addAttribute("allCountries", COUNTRIES);
		return "index";
	}

	@RequestMapping(value = "/cv", method = POST)
	public String cv(@ModelAttribute("cvData") CvData cvData, Model model) throws ParseException {
		CvData savedCvData = cvDataDao.findOneByFirstNameAndLastNameAndCountryName(cvData.getFirstName(),
				cvData.getLastName(), cvData.getCountryName());
		if (savedCvData != null) {
			GregorianCalendar selected = new GregorianCalendar();
			selected.setTime(cvData.getDate());
			GregorianCalendar saved = new GregorianCalendar();
			saved.setTime(savedCvData.getDate());
			if (selected.get(MONTH) == saved.get(MONTH) && selected.get(YEAR) == saved.get(YEAR)
					&& selected.get(DAY_OF_MONTH) == saved.get(DAY_OF_MONTH)) {
				model.addAttribute("savedCv", savedCvData.getCv());
			} else {
				model.addAttribute("savedCv", "Found CV in the database but it's date is not matching!");
			}
		} else {
			model.addAttribute("savedCv", "For these inputs there is no saved CV in the database!");
		}
		return "cv";
	}
}
