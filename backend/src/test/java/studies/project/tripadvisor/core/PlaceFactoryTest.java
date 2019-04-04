package studies.project.tripadvisor.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studies.project.tripadvisor.core.place.Commerce;
import studies.project.tripadvisor.core.place.Place;
import studies.project.tripadvisor.core.place.Public;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceFactoryTest {

    @Test
    public void creatingExistingCommercePlace(){
        Place place = PlaceFactory.getPlace("commerce");
        assert place.getClass() == Commerce.class;
    }

    @Test
    public void creatingExistingPublicPlace(){
        Place place = PlaceFactory.getPlace("public");
        assert place.getClass() == Public.class;
    }

    @Test
    public void creatingNonExistingPlace(){
        try {
            Place place = PlaceFactory.getPlace("nonexisitng");
        } catch (AssertionError e){
            assert false;
        }
    }

}
