package web;

import com.dianping.hui.web.BasicController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * @author: lanxinyu@meituan.com  2018-10-12 下午2:08
 * @Description:
 */


public class HomeControllerTest {

    @Test
    public void testHomePage1() {
        BasicController hc = new BasicController();
        Assert.assertEquals("home", hc.home());
    }

    @Test
    public void testHomePage() throws Exception {
        BasicController hc = new BasicController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(hc).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/homePage")).andExpect(MockMvcResultMatchers.view().name("home"));
    }
}
