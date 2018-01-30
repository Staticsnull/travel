package cn.kane.ttms.attachment.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.kane.ttms.attachment.entity.Attachment;
import cn.kane.ttms.attachment.service.AttachmentService;
import cn.kane.ttms.common.web.JsonResult;
@RequestMapping("/attachment/")
@Controller
public class AttachmentController {//Attachment
	@Resource
	private AttachmentService attach;
	@RequestMapping("attachmentUI")
	public String attachmentUI(){
		return "attachment/attachment";
	}
	@RequestMapping("doUpload")
	@ResponseBody
	public JsonResult doUpload(String title,MultipartFile mFile){
		System.out.println("doUpload");
		attach.uploadAndSaveObject(title, mFile);
//		System.out.println("title="+title);
//		String fileName = mFile.getOriginalFilename();
//		System.out.println("filename="+fileName);
//		String contentType = mFile.getContentType();
//		System.out.println("contentType="+contentType);
//		//获得文件扩展名
//		String ext = FilenameUtils.getExtension(fileName);
		//实现文件上传
//		File dest = new File("/home/soft01","copy_"+fileName);
//		File parent = dest.getParentFile();
//		if(!parent.exists()){
//			dest.mkdirs();
//		}
//		try {
//			mFile.transferTo(dest);//上传
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return new JsonResult();
	}
	@RequestMapping("doDownload")
	@ResponseBody
	public byte[] doDownload(Integer id,HttpServletResponse response) throws IOException{
		//根据id执行查找操作
		Attachment a = attach.findObjectById(id);
		//2.设置下载内容类型，以及响应头（固定格式）
		response.setContentType("appliction/octet-stream");
		response.setHeader("Content-disposition","attachment;filename="+a.getFileName());
		//3.获得指定文件的路径对象
		Path path = Paths.get(a.getFilePath());
		//4.读取path路径对应的文件，并返回字节数组
		return Files.readAllBytes(path);
	}
	/**获得所有的附件信息*/
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		List<Attachment> list = attach.findObjects();
		return new JsonResult(list);
	}
	/**根据id查找附件信息*/
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Attachment attachment = attach.findObjectById(id);
		return new JsonResult(attachment);
	}

}
