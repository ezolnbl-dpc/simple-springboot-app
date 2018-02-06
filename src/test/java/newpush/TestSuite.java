package newpush;

import static newpush.controller.AppController.COUNTRIES;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import newpush.controller.AppController;
import newpush.dao.CvDataDao;
import newpush.model.CvData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class TestSuite {

	@Mock
	private CvDataDao cvDataDao;

	@InjectMocks
	private AppController appController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(appController)
				.setViewResolvers(new StandaloneMvcTestViewResolver()).build();
	}

	@Test
	public void testGetCorrectIndexPage() throws Exception {
		this.mockMvc.perform(get("/index")).andDo(print()).andExpect(status().isOk())
				.andExpect(model().attribute("cvData", any(CvData.class)))
				.andExpect(model().attribute("allCountries", containsInAnyOrder(COUNTRIES.toArray())));
	}

	@Test
	public void testGetCorrectCv() throws Exception {

		Date date = new Date();
		String savedCv = "thecv";

		CvData saved = new CvData();
		saved.setDate(date);
		saved.setCv(savedCv);

		when(cvDataDao.findOneByFirstNameAndLastNameAndCountryName(anyString(), anyString(), anyString()))
				.thenReturn(saved);

		CvData cvData = new CvData();
		cvData.setDate(date);

		this.mockMvc.perform(post("/cv").flashAttr("cvData", cvData)).andDo(print()).andExpect(status().isOk())
				.andExpect(model().attribute("savedCv", equalTo(savedCv)));
	}
}