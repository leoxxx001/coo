package coo.mvc.config;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Component;

import coo.base.util.DateUtils;
import coo.mvc.converter.IEnumToString;
import coo.mvc.converter.ParamsToString;
import coo.mvc.converter.StringToIEnum;
import coo.mvc.converter.StringToParams;
import coo.mvc.converter.StringToUuidEntity;
import coo.mvc.converter.UuidEntityToString;

/**
 * 转换器配置组件。
 */
@Component("coo.mvc.converter.ConversionConfigurer")
public class ConversionConfigurer extends AbstractConversionConfigurer {
	@Override
	public void config(FormattingConversionService conversionService) {
		conversionService.removeConvertible(String.class, Enum.class);
		conversionService.addConverterFactory(new StringToIEnum());
		conversionService.addConverter(new IEnumToString());
		conversionService.addConverter(new StringToParams());
		conversionService.addConverter(new ParamsToString());
		conversionService.addConverterFactory(new StringToUuidEntity());
		conversionService.addConverter(new UuidEntityToString());

		conversionService
				.addFormatter(new DateFormatter(DateUtils.MILLISECOND));
		conversionService.addFormatter(new DateFormatter(DateUtils.SECOND));
		conversionService.addFormatter(new DateFormatter(DateUtils.MINUTE));
		conversionService.addFormatter(new DateFormatter(DateUtils.DAY));
	}
}
