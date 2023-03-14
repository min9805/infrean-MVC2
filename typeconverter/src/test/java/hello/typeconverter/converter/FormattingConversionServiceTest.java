package hello.typeconverter.converter;

import hello.typeconverter.formatter.MyNumberFormatter;
import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

public class FormattingConversionServiceTest {

	@Test
	void formattingConversionService(){
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		//컨버터 등록
		conversionService.addConverter(new StringToIpPortConverter());
		conversionService.addConverter(new IpPortToStringConverter());
		//포맷터 등록
		conversionService.addFormatter(new MyNumberFormatter());

		//컨버터 사용
		Assertions.assertThat(conversionService.convert("127.0.0.1:8080", IpPort.class)).isEqualTo(new IpPort("127.0.0.1", 8080));
		Assertions.assertThat(conversionService.convert(new IpPort("127.0.0.1", 8080), String.class)).isEqualTo("127.0.0.1:8080");

		//포맷터 사용
		Assertions.assertThat(conversionService.convert(1000, String.class)).isEqualTo("1,000");

	}
}