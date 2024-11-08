package rechard.learn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import rechard.learn.ejb.Counter;
import rechard.learn.ejb.CounterBeanHome;
import rechard.learn.ejb.api.FirstApi;
import rechard.learn.validate.MyNotNull;

@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<String> threadLocal = new ThreadLocal<String>();
//	@Autowired
//	HelloWorldApi helloWorldEJBBean;

    @PostConstruct
    public void  init(){
        System.out.println("PostConstruct");
    }

    @GetMapping("/call1/{name}")
	public String firstBeanCall(@PathVariable("name") String name , HttpServletRequest request) {
		System.out.println(request);
		InitialContext context;
		try {
			Properties props = new Properties();
	        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	        props.put(Context.PROVIDER_URL, "remote-http://localhost:8080");
			context = new InitialContext(props);
			
			FirstApi firstApi= (FirstApi)context.lookup("java:app/ejbweb/FirstApiBean!rechard.learn.ejb.api.FirstApi");
			return firstApi.sayHello(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
    public static class User {
        @MyNotNull
        private String name;

        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }

    }
    @PostMapping("/callvalidate")
	public String callvalidate(@Validated @RequestBody User user) {
		return user.getName();
	}
	
	@GetMapping("/test")
	public String test() {
        logger.info("/test");
		InitialContext context;
		try {
			Properties props = new Properties();
			context = new InitialContext(props);
            CounterBeanHome counterBeanHome= (CounterBeanHome)context.lookup("java:global/ejbweb/CounterService!rechard.learn.ejb.CounterBeanHome");
            Counter counter=counterBeanHome.create();
            counter.increment();
			return counter.getCount()+"";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
    @GetMapping("/testejb2")
	public String testejb2() {
        logger.info("/testejb2");
		InitialContext context;
		try {
			Properties props = new Properties();
			context = new InitialContext(props);
			CounterBeanHome counterBeanHome= (CounterBeanHome)context.lookup("java:app/ejbweb/CounterService!rechard.learn.ejb.CounterBeanHome");
			Counter counte=counterBeanHome.create();
            counte.increment();
			return "counter result="+counte.getCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

    @GetMapping("/testPutThreadlocalNotClear")
    public String testPutThreadlocalNotClear(){
      
        threadLocal.set("123");
        System.out.println(threadLocal.get());
        return "success";
    }

    @GetMapping("/testSaveDB4Tx") 
    @Transactional
	public String testSaveDB4Tx() throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        Context context = new InitialContext();
        DataSource ds = (DataSource) context.lookup("java:/jboss/jdbc/appdb");
        conn = ds.getConnection();
        String sql ="insert into Employees(name, age)values(?,?)";
        ps=conn.prepareStatement(sql);
        ps.setString(1, "tom");
        ps.setInt(2, 20);
        ps.executeUpdate();
        ps.close();
        conn.close();
        int i=1/0;
        return "success";
    }

    @GetMapping("/testSaveDB4Tx2") 
    @Transactional
	public String testSaveDB4Tx2() throws Exception{
        Context context = new InitialContext();
        DataSource ds = (DataSource) context.lookup("java:/jboss/jdbc/appdb");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        String sql ="insert into Employees(name, age)values(?,?)";
        jdbcTemplate.update(sql, "cat",10);
        int i=1/0;
        return "success";
    }

	@GetMapping("/testCallDB") 
	public String testCallDB(){
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/jboss/jdbc/appdb");

            // Get a connection from the data source
            conn = ds.getConnection();
            
            // 执行查询
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, age FROM Employees";
            rs = stmt.executeQuery(sql);
            
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.print(", Age: " + age);
                System.out.print("\n");
            }
        } catch(Exception e){
            // 处理 Class.forName 错误和 SQLException
            e.printStackTrace();
        } finally {
            // 这里只关闭了Statement和ResultSet，没有关闭Connection
            try{
                if(stmt != null)
                    stmt.close();
                if(rs != null)
                    rs.close();
            }catch(SQLException se2){
            }// 什么都不做
        }
		return "success";

	}
}
