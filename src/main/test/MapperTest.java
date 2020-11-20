import com.deemo.dao.DeptMapper;
import com.deemo.dao.EmpMapper;
import com.deemo.dao.UserMapper;
import com.deemo.entity.Dept;
import com.deemo.entity.Emp;
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
 * 测试dao层
 */

//@ContextConfiguration用来指定spring配置文件，用来创建-ioc容器,然后直接aotowired要使用的组件
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
public class MapperTest {
    @Autowired
    EmpMapper empMapper;
    @Autowired
    DeptMapper deptMapper;

    //批量的
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD() {

        //原生方法，推荐spring的项目使用Spring的单元测试

//        //1.创建springIOC容器
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //从容器中获取mapper
//        ioc.getBean(UserMapper.class);

        System.out.println(deptMapper);
        List<Dept> deptAll = deptMapper.findDeptAll2(1);
        System.out.println(deptAll.toString());


        System.out.println("————————+++---终---+++————————");
    }
}
