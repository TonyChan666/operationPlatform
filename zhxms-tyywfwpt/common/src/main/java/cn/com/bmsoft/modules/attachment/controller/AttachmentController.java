package cn.com.bmsoft.modules.attachment.controller;

import cn.com.bmsoft.modules.attachment.entity.AttachmentBusinessEntity;
import cn.com.bmsoft.modules.attachment.entity.AttachmentEntity;
import cn.com.bmsoft.modules.attachment.service.AttachmentService;
import cn.com.bmsoft.modules.attachment.service.FileSystemService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公共附件表 前端控制器
 * </p>
 *
 * @author diaohancai
 * @since 2019-08-26
 */
@Slf4j
@RestController
@RequestMapping("/api/attachment")
@Api("公共附件表 API")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private FileSystemService fileSystemService;

    /**
     * 获取公共附件表
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取Attachment对象详情", notes = "查看公共附件表", response = AttachmentEntity.class)
    public R getAttachment(@Valid @RequestParam int id) throws Exception {

        LambdaQueryWrapper<AttachmentEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AttachmentEntity::getId, id);
        AttachmentEntity attachmentEntity = attachmentService.getOne(queryWrapper);
        return R.ok().put("attachmentEntity", attachmentEntity);
    }

    /**
     * 公共附件表分页列表
     */
    @GetMapping("/page")
    @ApiOperation(value = "获取Attachment分页列表", notes = "公共附件表分页列表", response = AttachmentEntity.class)
    public R getAttachmentPageList(@Valid @RequestParam Map<String, Object> params) throws Exception {

        PageUtils page = attachmentService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 列出附件业务关联信息
     * @param businessTableName
     * @param businessTableId
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public R listAttachmentBusiness(@RequestParam(value="businessTableName", required=true) String businessTableName,
                                    @RequestParam(value="businessTableId", required=true) String businessTableId) throws Exception{

        AttachmentBusinessEntity attachmentBusinessEntity = new AttachmentBusinessEntity();
        attachmentBusinessEntity.setBusinessTableName(businessTableName);
        attachmentBusinessEntity.setBusinessTableId(businessTableId);
        List<AttachmentBusinessEntity> AttachmentBusinessList = attachmentService.listAttachmentBusiness(attachmentBusinessEntity);
        return R.ok().put("AttachmentBusinessList", AttachmentBusinessList);
    }

    /**
     * 上传一个附件
     * @param businessTableName
     * @param businessTableId
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传一个附件", notes = "上传一个附件", response = Integer.class)
    public R upload(@RequestParam(value="businessTableName", required=true) String businessTableName,
                    @RequestParam(value="businessTableId", required=true) String businessTableId,
                    @RequestParam(value="file", required=true) MultipartFile multipartFile) throws Exception {

        AttachmentEntity attachmentEntity = new AttachmentEntity();
        String id="";
        try {
            // 附件上传
            Map<String, Object> map = fileSystemService.uploadFile(multipartFile.getOriginalFilename(), multipartFile.getInputStream());
            // 记录附件日志
            attachmentEntity = buildAttachment(multipartFile, map);
            boolean flag = attachmentService.save(attachmentEntity);
            id=attachmentEntity.getId();
            if (!flag){
                return R.error(500, "附件上传失败，请联系管理员！");
            }

        } catch (Exception e) {
            log.error(e.toString(), e);
            throw e;
        }

        return R.ok().put("id", id);
    }

    /**
     * 下载一个附件
     */
    @GetMapping("/download/{id}")
    @ApiOperation(value = "下载一个附件", notes = "下载一个附件")
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @Valid @PathVariable String id) throws Exception {

        //查询附件信息
        LambdaQueryWrapper<AttachmentEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AttachmentEntity::getId, id);
        AttachmentEntity attachmentEntity = attachmentService.getOne(queryWrapper);

        if (attachmentEntity == null) {
            throw new Exception("找不到附件");
        } else {
            //初始化文件流、输入流、输出流
            File file = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                file = fileSystemService.downloadFile(attachmentEntity.getAbsolutePath());
                inputStream = new FileInputStream(file);

                outputStream = response.getOutputStream();
                //设置下载文件名
                String fileName = URLEncoder.encode(attachmentEntity.getOriginalFileName(), "UTF-8");
                //设置响应头
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("multipart/form-data");
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

                byte[] buff = new byte[1024];
                int len = -1;
                while ((len = inputStream.read(buff)) > 0) {
                    outputStream.write(buff, 0, len);
                }
            } catch (Exception e) {
                log.error(e.toString(), e);
                throw e;
            } finally {
                //关闭文件流、输入流、输出流
                outputStream.flush();
                outputStream.close();
                inputStream.close();
                file.delete();
            }
        }
    }

    /**
     * 删除一个附件
     */
    @GetMapping("/delete")
    @ApiOperation(value = "删除一个附件", notes = "删除一个附件", response = Integer.class)
    public R delete(@Valid @RequestParam int id) throws Exception {

        LambdaQueryWrapper<AttachmentEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AttachmentEntity::getId, id);
        AttachmentEntity attachmentEntity = attachmentService.getOne(queryWrapper);

        if (attachmentEntity == null) {
            throw new Exception("找不到附件");
        } else {
            try {
                fileSystemService.deleteFile(attachmentEntity.getAbsolutePath());

                attachmentService.deleteByIdLogical(id);
            } catch (Exception e) {
                log.error(e.toString(), e);
                throw e;
            }
        }
        return R.ok();
    }

    /**
     * 构建附件对象
     *
     * @param multipartFile
     * @param map
     * @return
     */
    private AttachmentEntity buildAttachment(MultipartFile multipartFile, Map<String, Object> map) {

        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setStorageFileName((String) map.get("storageFileName"));
        attachmentEntity.setOriginalFileName(multipartFile.getOriginalFilename());
        attachmentEntity.setFileType((String) map.get("fileType"));
        attachmentEntity.setFileSize((int) multipartFile.getSize()/1024);
        attachmentEntity.setRelativePath((String) map.get("relativePath"));
        attachmentEntity.setAbsolutePath((String) map.get("absolutePath"));
        attachmentEntity.setStatus("1");
        return attachmentEntity;
    }

}

