package com.andrebarroso.backend.resources;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.andrebarroso.backend.entities.ChamadasPlayList;
import com.andrebarroso.backend.resources.requesParameters.PostMock;
import com.andrebarroso.backend.resources.requesParameters.ServiceMockPostResponse;
import com.andrebarroso.backend.responses.playListResponse;
import com.andrebarroso.backend.services.ChamadasPlayListService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChamadasPlayListResources {
    
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@MockBean
	private ChamadasPlayListService service;
	
	@Test
	public void testPostRequisitionSuccess() throws Exception{
		ChamadasPlayList objBrowserMock = new PostMock();
		playListResponse responseOkService = new ServiceMockPostResponse();
		
		when(this.service.insert(objBrowserMock))
			.thenReturn(responseOkService);
		
		String url = "/chamadas";
		this.mvc.perform(post(url)
				.content("{\"solicitante\": \"André Barroso\" ,\"cidade\": \"Pato Branco\"}")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk());
	}
}
