package repository;

import com.dianping.hui.entity.GF;
import com.dianping.hui.repository.LansingRepository;
import com.dianping.hui.web.LansingController;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lanxinyu@meituan.com  2018-10-12 下午6:32
 * @Description:
 */
public class LansingTest {

    @Test
    public void marryTest() throws Exception {
        LansingRepository mockRepository = Mockito.mock(LansingRepository.class);

        /**
         * mock 接口「当调用 mockRepository.getGirlFriend() 」 返回 「王🐳」
         */
        Mockito.when(mockRepository.getGirlFriend()).thenReturn("王🐳");


        LansingController controller = new LansingController(mockRepository);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/lansing.jsp")
                ).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/lansing"))
                .andExpect(MockMvcResultMatchers.view().name("lansing"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("gf"));

    }

    @Test public void viewTuiduanTest() throws Exception {

        List<String> mockReturn = new ArrayList() {
            {
                add("王🐳");
                add("朱超然");
                add("刘畅");
                add("金丽红");
            }
        };

        LansingRepository mockRepository = Mockito.mock(LansingRepository.class);
        Mockito.when(mockRepository.getGirlFriends()).thenReturn(mockReturn);
        LansingController lansingController = new LansingController(mockRepository);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(lansingController)
                .setSingleView(
                        new InternalResourceView("WEB-INF/views/viewTuiduan.jsp")
                ).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/viewTuiduan/兰鑫宇")
                .characterEncoding("utf-8"))
                .andExpect(MockMvcResultMatchers.view().name("viewTuiduan"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("gfNames"))
        .andDo(MockMvcResultHandlers.print());

    }

    /**
     * {@link org.springframework.web.bind.support.DefaultDataBinderFactory.createBinder()}
     * 中的参数 target 为空使
     * {@link org.springframework.web.bind.support.ConfigurableWebBindingInitializer.initBinder(WebDataBinder binder)}
     * 未绑定Validator 而使验证器验证失败
     * @throws Exception
     */
    @Test public void submitForm() throws Exception {

        LansingRepository mockRepository = Mockito.mock(LansingRepository.class);

        GF unsaved = new GF(1,"金丽红") ;
        GF saved = new GF(2,"朱超然") ;


        Mockito.when(mockRepository.save(unsaved)).thenReturn(saved);

        LansingController controller = new LansingController(mockRepository);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/submitForm")
                .characterEncoding("utf-8")
                .param("index","1")
                .param("name","金丽红")
        ).andExpect(MockMvcResultMatchers.redirectedUrl("/viewTuiduan/朱超然"))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(mockRepository, Mockito.atLeastOnce()).save(unsaved);

    }
}
