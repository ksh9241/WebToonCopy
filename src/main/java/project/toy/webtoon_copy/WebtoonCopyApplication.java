package project.toy.webtoon_copy;

import com.google.gson.*;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableJpaAuditing
@SpringBootApplication
public class WebtoonCopyApplication {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
				.setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection)) // LazyInitializationException: could not initialize proxy 발생 시
				.setSkipNullEnabled(true)
		;
		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebtoonCopyApplication.class, args);
	}

}
