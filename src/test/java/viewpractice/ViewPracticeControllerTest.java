package viewpractice;

import com.dianping.hui.web.ViewPracticeController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author: lanxinyu@meituan.com  2018-10-22 下午3:22
 * @Description:
 */
public class ViewPracticeControllerTest {

    @Test public void register() throws Exception {
        ViewPracticeController controller = new ViewPracticeController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/view/register"))
                .andExpect(MockMvcResultMatchers.view().name("jspractice/springform"))
                .andDo(MockMvcResultHandlers.print());

    }
}
