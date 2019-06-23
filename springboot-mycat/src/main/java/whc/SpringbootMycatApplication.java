package whc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mycat分库分表测试
 */
@MapperScan("whc.dao")
@SpringBootApplication
public class SpringbootMycatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMycatApplication.class, args);
    }

}
