package cn.kane.ttms.attachment.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.kane.ttms.attachment.dao.AttachmentDao;
import cn.kane.ttms.attachment.entity.Attachment;
import cn.kane.ttms.attachment.service.AttachmentService;
import cn.kane.ttms.common.exception.ServiceException;
@Service
public class AttachmentServiceImpl implements AttachmentService {
	@Resource
	private AttachmentDao attachmentDao;
	@Override
	public void uploadAndSaveObject(String title, MultipartFile mFile) {
		//1.实现文件上传
		if(title == null || title.trim().length()==0){
//		if(title == null || title.equals("")){
			throw new ServiceException("上传标题不能为空");
		}
		if(mFile == null){
			throw new ServiceException("请选择上传的文件");
		}
		if(mFile.isEmpty()){
			throw new ServiceException("上传文件不能为空");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String dateDir = sdf.format(new Date());
		String baseDir = "/home/soft01/upload/";
		File uploadDir = new File(baseDir+dateDir);
		if(!uploadDir.exists()){
			uploadDir.mkdirs();
		}
		//1.3.2 构建新的文件名 UUID.randomUUID() 获得随机数
		String srcFileName = mFile.getOriginalFilename();
		String newFileName = 
				UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(srcFileName);
		//1.3.3 创建目标文件对象
		File dest = new File(uploadDir,newFileName);
		//1.3.4 实现文件上传
		byte[] buf = null;
		try {
			buf = mFile.getBytes();
			mFile.transferTo(dest);
		}  catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException("文件上传失败！");
		}
		String fileDisgest = null;
		//对文件内容进行md5加密，并转换为16进制显示 
		//对文件内容进行md5加密以后形成的字符串，称之为文件摘要
		//文件内容一样，构建的摘要字符串也是一样的
		fileDisgest = DigestUtils.md5DigestAsHex(buf);
		//1.2 判定文件是否已经上传过 根据摘要信息
		//查询并统计记录 
		int count = attachmentDao.getRowCountByDigest(fileDisgest);
		//加入统计结果大于0说明文件已经上传过
		if(count>0){
			throw new ServiceException("文件已上传");
		}
		//1.3 实现文件上传的操作
		//1.3.1 构建文件上传的路径 要求路径：/home/soft01/uploads/2017/08/15/xxx.png
		
		//2.将文件相关的信息写入数据库
//		String fileDisgest = null;
//		//对文件内容进行md5加密，并转换为16进制显示
//		fileDisgest = DigestUtils.md5DigestAsHex(buf);
		System.out.println(fileDisgest);
		Attachment attachment = new Attachment();
		attachment.setTitle(title);
		attachment.setFileName(mFile.getOriginalFilename());
		attachment.setContentType(mFile.getContentType());
		attachment.setFilePath(dest.getAbsolutePath());
//		String fileDisgest = DigestUtils.md5Digest(mFile.getBytes());
		attachment.setFileDisgest(fileDisgest);
		attachment.setAthType(1);//暂且没用到
		attachment.setBelongId(1);//暂且没用到
		System.out.println(attachment);
		int rows = attachmentDao.insertObject(attachment);
		System.out.println(rows);
		if(rows == -1){
			throw new ServiceException("插入信息失败");
		}
	}
	@Override
	public List<Attachment> findObjects() {
		return attachmentDao.findObjects();
	}
	@Override
	public Attachment findObjectById(Integer id) {
		if(id==null || id<1){
			throw new ServiceException("id值无效");
		}
		Attachment attachment = attachmentDao.findObjectById(id);
		if(attachment == null){
			throw new ServiceException("查找不到任何记录");
		}
		return attachment;
	}

}
