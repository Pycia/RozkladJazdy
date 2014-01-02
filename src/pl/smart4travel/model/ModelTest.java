package pl.smart4travel.model;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {

    private Model model;

    @Before
    public void setUp() throws Exception {
       model = new Model();
       model.read();
    }

    @Test
    public void testFindRouteOk() throws Exception {
        Route route = model.findRoute("Wzgórza Krzesławickie", "Biprostal", 7*60*60*1000);
        Assert.assertEquals(route.getLineId(),"1");
        Assert.assertEquals(route.getTimeList().size(),2);
        Assert.assertEquals(route.getTimeList().get(0).getHour(),7);
        Assert.assertEquals(route.getTimeList().get(0).getMinute(),0);
        Assert.assertEquals(route.getTimeList().get(1).getHour(),8);
        Assert.assertEquals(route.getTimeList().get(1).getMinute(),0);
    }

    @Test
    public void testFindRouteNoLine() throws Exception {
        Route route = model.findRoute("Biprostal", "Biprostal", 7*60*60*1000);
        Assert.assertNull(route.getLineId());
    }

    @Test
    public void testFindRouteNoTime() throws Exception {
        Route route = model.findRoute("Wzgórza Krzesławickie", "Biprostal", 17*60*60*1000);
        Assert.assertEquals(route.getLineId(),"1");
        Assert.assertEquals(route.getTimeList().size(),0);
    }

    @After
    public void tearDown() throws Exception {

    }
}
