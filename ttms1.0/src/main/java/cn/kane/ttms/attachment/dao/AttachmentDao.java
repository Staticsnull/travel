package cn.kane.ttms.attachment.dao;

import java.util.List;

import cn.kane.ttms.attachment.entity.Attachment;
import cn.kane.ttms.common.dao.BaseDao;

public interface AttachmentDao extends BaseDao<Attachment> {
//	public int insertObject(Attachment attachment);
	/**根据摘要信息 获取记录数*/
	public int getRowCountByDigest(String fileDisgest);
	//获得所有上传的文件信息
	public List<Attachment> findObjects();
	//根据id查找某个对象
//	public Attachment findObjectById(Integer id);
}
