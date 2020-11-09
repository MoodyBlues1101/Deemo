import com.deemo.entity.User;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 使用spring测试模块提供的测试请求功能
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
@WebAppConfiguration
public class MvcTest {

    //传入springMvc的ioc
    @Autowired
    WebApplicationContext context;
    //虚拟mvc请求，获取结果
    MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user").param("page", "1")).andReturn();

        //请求成功以后，请求域中会有pageInfo
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("页码：" + pageInfo.getPageNum());
        System.out.println("总页码" + pageInfo.getPages());

        List<User> list = pageInfo.getList();
        for (User user:list) {
            System.out.println(user.getUsername());
        }
    }

}
