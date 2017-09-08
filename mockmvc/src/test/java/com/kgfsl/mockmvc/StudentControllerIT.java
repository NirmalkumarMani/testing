package com.kgfsl.mockmvc;
import org.json.JSONException;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.context.embedded.AbstractConfigurableEmbeddedServletContainer;
import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.kgfsl.mockmvc.Course;
import com.kgfsl.mockmvc.StudentService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {MockmvcApplication.class} , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIT {
@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

public void testRetrieveStudentCourse() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/students/Student1/courses/Course1"),
				HttpMethod.GET, entity, String.class);

		String expected = "{id:Course1,name:Spring,description:10 Steps}";

;

}


	@Test
	public void addCourse() {

		Course course = new Course("Course1", "Spring", "10 Steps",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));

		HttpEntity<Course> entity = new HttpEntity<Course>(course, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/students/Student1/courses"),
				HttpMethod.POST, entity, String.class);
					String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
//	assertTrue(actual.contains("/students/Student1/courses/"));



	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}