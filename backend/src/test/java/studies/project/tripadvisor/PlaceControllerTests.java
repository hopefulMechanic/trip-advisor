package studies.project.tripadvisor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import studies.project.tripadvisor.controller.PlaceController;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.entity.dto.request.PlaceRequestDTO;
import studies.project.tripadvisor.service.PlaceService;
import studies.project.tripadvisor.service.impl.SearchServiceImpl;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlaceController.class)
@AutoConfigureMockMvc
public class PlaceControllerTests {

    @MockBean
    PlaceService placeService;

    @MockBean
    SearchServiceImpl searchService;

    @MockBean
    PlaceController placeController;

    @Test
    public void convertPlaceDtoToEntity() throws Exception {
        PlaceRequestDTO placeRequestDto = new PlaceRequestDTO(
                "test name", "test description", "adress",
                "Berlin", "123", "Niemcy", 1.32,
                "abc@gmail.com", "123456789", List.of("food", "5star")
        );

        Place place = new Place();
        place = placeController.convertToEntity(placeRequestDto);
    }

}

