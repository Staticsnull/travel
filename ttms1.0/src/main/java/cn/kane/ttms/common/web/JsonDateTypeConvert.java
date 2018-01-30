package cn.kane.ttms.common.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 此类用户转换日期格式 第二种转换日期的格式
 * @author soft01
 *springmvc在将日期对象转换为字符串是，一般默认会转换
 *为长整型，假如我们需要自己定义日期格式，通常会写一个
 *类继承JsonSerializer  假如在对象中需要将日期转换为我们需要的格式
 *可以在对应的实体对象get方法中使用
 *@JsonSerialize（using=JsonDateTypeConvert.class)
 */
public class JsonDateTypeConvert extends JsonSerializer<Date>{
	/**
	 * 泛型属于编译的一种类型，在编译之间就确定类型
	 * value是要转换的日期  gen 为一个json对象生成器
	 */
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider ser)
			throws IOException, JsonProcessingException {
		//定义日期字符串转换对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//将日期转换为指定格式的字符串
		String str = sdf.format(value);
		//将转换后的字符串写入到json对象中
		gen.writeString(str);
	}
	

}
