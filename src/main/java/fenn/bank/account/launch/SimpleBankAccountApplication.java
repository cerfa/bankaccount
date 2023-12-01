package fenn.bank.account.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages ="fenn.bank")
@EntityScan("fenn.bank.account.entities")
@EnableJpaRepositories("fenn.bank.account.entities.repository")
@EnableSwagger2
public class SimpleBankAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleBankAccountApplication.class, args);
	}

}
