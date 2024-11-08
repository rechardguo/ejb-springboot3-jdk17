package rechard.learn;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(Application.class);
	}

	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver(){
		StandardServletMultipartResolver resolver =new StandardServletMultipartResolver();
		//CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		// resolver.setDefaultEncoding("UTF-8");
		resolver.setResolveLazily(true);
		// resolver.setMaxInMemorySize(40960);
		// resolver.setMaxUploadSize(50*1024*1024);
		return resolver;
	}

}
