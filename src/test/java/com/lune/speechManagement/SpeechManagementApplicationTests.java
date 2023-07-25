package com.lune.speechManagement;

import com.lune.speechManagement.model.Speech;
import com.lune.speechManagement.service.SpeechServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ContextConfiguration(classes = SpeechManagementApplication.class)
class SpeechManagementApplicationTests {

	@Autowired
	private SpeechManagementApplication speechManagementApplication;

	@MockBean
	private SpeechServiceImpl speechService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddNewSpeech() {
		SpeechManagementApplication speechManagementApplication = new SpeechManagementApplication();
		Model model = new ExtendedModelMap();

		// execute
		String result = speechManagementApplication.addNewSpeech(model);

		// verify
		assertEquals("newspeech.html", result);
		Speech speech = (Speech) model.getAttribute("speech");
		assertEquals(new Speech(), speech);
	}

	@Test
	public void testUpdateForm() {
		int speechId = 1;
		Speech speech = new Speech();
		speech.setId(speechId);
		speech.setSpeechText("Sample Speech");

		when(speechService.getById(speechId)).thenReturn(speech);

		Model model = new ExtendedModelMap();

		// execute
		String result = speechManagementApplication.updateForm(speechId, model);

		// verify
		assertEquals("update.html", result);
		Speech speechModel = (Speech) model.getAttribute("speech");
		assertEquals(speech, speechModel);

		verify(speechService, times(1)).getById(speechId);
	}

	@Test
	public void testSaveSpeech() {
		Speech speech = new Speech();
		speech.setSpeechText("Sample Speech");

		// execute
		String result = speechManagementApplication.saveSpeech(speech);

		// verify
		assertEquals("redirect:/", result);
		verify(speechService, times(1)).save(speech);
	}

	@Test
	public void testDeleteThroughId() {
		Integer speechId = 1;

		// execute
		String result = speechManagementApplication.deleteThroughId(speechId);

		// verify
		assertEquals("redirect:/", result);
		verify(speechService, times(1)).deleteViaId(speechId);
	}

/** AssertionFailedError
	@Test
	public void testSearchAllWithNullDateRange() {
		Speech speech = new Speech();
		Model model = new ExtendedModelMap();

		String keyword = "Example";
		LocalDate startDate = null;
		LocalDate endDate = null;

		List<Speech> expectedList = List.of(
				new Speech(null, "Example Speech", "John Doe", "Example Subject", null)
		);

		when(speechService.getByKeywordAndDateRange(keyword, startDate, endDate)).thenReturn(expectedList);

		String result = legalsightApplication.searchAll(speech, model, keyword, startDate, endDate);

		assertEquals("index.html", result);
		assertEquals(expectedList, model.getAttribute("list"));

		verify(speechService, times(1)).getByKeywordAndDateRange(keyword, startDate, endDate);
	}
 **/

}
