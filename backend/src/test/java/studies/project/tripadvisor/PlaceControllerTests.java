package studies.project.tripadvisor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import studies.project.tripadvisor.controller.PlaceController;
import studies.project.tripadvisor.entity.Comment;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.entity.dto.response.PlaceResponseDTO;
import studies.project.tripadvisor.service.PlaceService;
import studies.project.tripadvisor.service.UserService;
import studies.project.tripadvisor.service.impl.SearchServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlaceController.class)
@AutoConfigureMockMvc
public class PlaceControllerTests {

    @TestConfiguration
    static class PlaceControllerContextConfiguration {
        @Bean
        public PlaceController placeController() {
            return new PlaceController();
        }
    }

    @Autowired
    private PlaceController placeController;

    @MockBean
    private PlaceService placeService;

    @MockBean
    private SearchServiceImpl searchService;

    @MockBean
    private UserService userService;

    @Test
    public void checkScoreIsCalculatedForPlace() throws Exception {
        Place place = new Place("Restauracja A", "Test commercial Place",
                "Klimeckiego", "Krakow", "123", "Polska", 100.00, "commercialPlace@gmail.com",
                "123456789", List.of("hotel", "parking"));
        Comment comment1 = new Comment(1L, "Text komentarza", place, 7, null, "2010.05.12");
        Comment comment2 = new Comment(1L, "Text komentarza", place, 5, null, "2010.05.12");

        Set<Comment> commentSet = new HashSet<>();
        commentSet.add(comment1);
        commentSet.add(comment2);
        place.setComments(commentSet);

        PlaceResponseDTO placeResponseDto = placeController.convertToDto(place);

        assertEquals(new Double(6.0), placeResponseDto.getScore());
    }

}

