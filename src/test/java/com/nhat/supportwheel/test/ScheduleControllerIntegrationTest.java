package com.nhat.supportwheel.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nhat.supportwheel.Application;
import com.nhat.supportwheel.controller.ScheduleController;

/**
 * Integration test for {@link ScheduleController}
 * 
 * @author ngocnhat.dau
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
public class ScheduleControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void getSchedule_GetRequest_CorrectSchedule() {
	try {
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.WEEK_OF_YEAR, 1);
	    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    mvc.perform(get("/schedule")).andDo(print()).andExpect(status().isOk())
		    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		    .andExpect(jsonPath("$.items", hasSize(equalTo(10))))
		    .andExpect(jsonPath("$.items[0].date", is(dateFormatter.format(calendar.getTime()))));

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
