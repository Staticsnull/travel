package cn.kane.ttms.common.web;
/**
 * 用来封装返回的数据
 * @author soft01
 * 借助此类封装Controller方法中的返回值 有@ResponseBody注解
 * 目的：统一返回值类型，便于在页面上进行统一的处理
 */
public class JsonResult {
	private static final int SUCCESS = 1;
	private static final int ERROR = 0;
	/**状态*/
	private Integer state;
	/**对应状态的消息*/
	private String message;
	/**具体的业务数据*/
	private Object data;
	/**此构造方法应用data为null的场景*/
	public JsonResult() {
		this.state = SUCCESS;
		this.message = "OK";
	}
	/**有具体业务返回时使用此构造方法*/
	public JsonResult(Object data){
		this();
		this.data = data;
	}
	/**出现异常时，要调用此方法来封装异常信息*/
	public JsonResult(Throwable t){
		this.state = ERROR;
		this.message = t.getMessage();
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static int getSuccess() {
		return SUCCESS;
	}
	public static int getError() {
		return ERROR;
	}
	
}
