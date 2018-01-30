package cn.kane.ttms.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.ttms.common.web.JsonResult;
/**通过此注解声明此类为一个全局异常处理类型 牢记此类*/
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult handlerServiceException(ServiceException e){
		e.printStackTrace();
		//将异常封装到JsonResult
		return new JsonResult(e);
	}
}
