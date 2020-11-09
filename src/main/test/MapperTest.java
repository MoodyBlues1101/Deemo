import com.deemo.dao.UserMapper;
import com.deemo.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

/**
 *      测试dao层
 */

//@ContextConfiguration用来指定spring配置文件，用来创建-ioc容器,然后直接aotowired要使用的组件
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
public class MapperTest {
    @Autowired
    UserMapper userMapper;

    //批量的
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD(){

        //原生方法，推荐spring的项目使用Spring的单元测试

//        //1.创建springIOC容器
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //从容器中获取mapper
//        ioc.getBean(UserMapper.class);


        System.out.println(userMapper);
        List<User> users = userMapper.selectAll();
        for (User u :
                users) {
            System.out.println(u.getUsername());
        }
//        userMapper.insert(new User(3,"布莱克","123"));

//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        for (int i = 0; i < 100; i++) {
//            String uid = UUID.randomUUID().toString().substring(0, 3);
//            mapper.insert(new User(i+1,"MR."+uid,"123"));
//        }
        System.out.println("+++---终---+++");
    }
}
