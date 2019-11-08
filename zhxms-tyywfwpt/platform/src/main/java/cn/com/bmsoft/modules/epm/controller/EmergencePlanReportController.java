package cn.com.bmsoft.modules.epm.controller;

import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntityExtend;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanReviewEntity;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanTaskEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanAssessService;
import cn.com.bmsoft.modules.epm.service.EmergencePlanReviewService;
import cn.com.bmsoft.modules.epm.service.EmergencePlanService;
import cn.com.bmsoft.modules.epm.service.EmergencePlanTaskService;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.pdf.PdfExportService;
import cn.com.bmsoft.pdf.PdfView;
import cn.com.bmsoft.utils.*;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;


import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgl on 2019/9/19.
 */
@Slf4j
@RestController
@RequestMapping("/epm/emergencePlanReport")
@Api("应急预案报告 API")
public class EmergencePlanReportController{
    @Autowired
    private EmergencePlanReviewService emergencePlanReviewService;

    @Autowired
    private EmergencePlanAssessService emergencePlanAssessService;

    @Autowired
    private EmergencePlanService emergencePlanService;

    @Autowired
    private EmergencePlanTaskService emergencePlanTaskService;




    /**
     * 应急预案报告分页列表（已完结状态）
     */
    @GetMapping("/reportList")
    public R unPublishedQueryList(@RequestParam Map<String, Object> params){
        params.put("sfypg", DictionaryConstant.EMERGENCE_PLAN_STATE_ASSESS);//是否已评估（已评估)
        params.put("sfyps",DictionaryConstant.EMERGENCE_PLAN_STATE_EXAMINED); //是否已评审（已评审）
        params.put("yazt",DictionaryConstant.EMERGENCE_PLAN_STATE_END); //预案状态（已完结)
        params.put("status",DictionaryConstant.STATUS_VALID);//筛选有效的（已删除的不显示）
        PageUtils result = emergencePlanService.emergencePlanQueryList(params);
        return R.ok().put("page", result);

    }




    /**
     * 查看报告(预案信息)
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("epm:emergenceplan:info")
    @ApiOperation(value = "获取emergenceplan对象详情", notes = "emergenceplan对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
        EmergencePlanEntityExtend emergencePlan=emergencePlanService.findEmergencePlanById(id);
        return R.ok().put("data", emergencePlan);
    }



    /**
     * 应急预案分页列表
     */
    @GetMapping("/emergencePlanReviewQueryList")
    public R emergencePlanReviewQueryList(@RequestParam Map<String, Object> params){
        PageUtils result = emergencePlanReviewService.emergencePlanReviewQueryList(params);
        return R.ok().put("page", result);

    }





    /**
     * 应急预案查看评估记录分页列表
     */
    @GetMapping("/getEmergencePlanAssessList")
    public R emergencePlanAssessQueryList(@RequestParam Map<String, Object> params){
        PageUtils result = emergencePlanAssessService.emergencePlanAssessQueryList(params);
        return R.ok().put("page", result);

    }




//    下载报告pdf(评审信息)
    @GetMapping("/exportPdf")
    public ModelAndView exportPdf(@RequestParam("epid") String epid){
        Map<String,Object> params=new HashMap<>();
        params.put("status",DictionaryConstant.STATUS_INVALID);//筛选有效的（已删除的不显示）
        params.put("epid",epid);

        List<EmergencePlanReviewEntity> reviewList= emergencePlanReviewService.emergencePlanReviewList(params);

        Map<String,Object> taskMapParams=new HashMap<>();
        taskMapParams.put("yjyaid",epid);
        taskMapParams.put("rwmc","review");
        EmergencePlanTaskEntity emergencePlanTaskEntity=null;
        List<EmergencePlanTaskEntity> emergencePlanTaskEntityList= emergencePlanTaskService.getEmergencePlanTaskList(taskMapParams);
        if (emergencePlanTaskEntityList.size()>0){
            emergencePlanTaskEntity=emergencePlanTaskEntityList.get(0);
        }
//        return R.ok().put("page", result).put("emergencePlanTaskEntity",emergencePlanTaskEntity);

//        Map<String, Object> params=new HashMap<>();
//        params.put("status", DictionaryConstant.STATUS_VALID);//筛选有效的（已删除的不显示）
//        params.put("epid",epid);
//        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal(); //获取当前登录的用户
//        PageUtils result = emergencePlanReviewService.emergencePlanReviewQueryList(params);
//        List<EmergencePlanReviewEntity> reviewList= (List<EmergencePlanReviewEntity>) result.getList();
//        Map<String,Object> taskMapParams=new HashMap<>();
//        taskMapParams.put("yjyaid",epid);
//        taskMapParams.put("rwmc","review");
//        EmergencePlanTaskEntity emergencePlanTaskEntity=null;
//        List<EmergencePlanTaskEntity> emergencePlanTaskEntityList= emergencePlanTaskService.getEmergencePlanTaskList(taskMapParams);
//        if (emergencePlanTaskEntityList.size()>0){
//            emergencePlanTaskEntity=emergencePlanTaskEntityList.get(0);
//        }
//        return R.ok().put("page", result).put("emergencePlanTaskEntity",emergencePlanTaskEntity);


        //定义pdf视图
        View view=new PdfView(exportService());
        ModelAndView mv=new ModelAndView();
        mv.setView(view);
        //加入数据模型
        mv.addObject("reviewList",reviewList);
        mv.addObject("emergencePlanTaskEntity",emergencePlanTaskEntity);
        return mv;
    }

    @SuppressWarnings("unchecked")
    private PdfExportService exportService(){
        return ((model, document, writer, request, response) -> {
            try{
                //A4纸
                document.setPageSize(PageSize.A4);
                //标题
                document.addTitle("评审信息");
                //换行
                document.add(new Chunk(("\n")));
                //表格 4列
                PdfPTable table = new PdfPTable(4);
                // 设置表格默认为无边框
                table.getDefaultCell().setBorder(0);
                //单元格
                PdfPCell cell = null;
                //字体
               Font f8 = new Font();
                //新增改进代码 -------------------------------
                BaseFont bfChinese = null;//1 不能省略
                try {
                    bfChinese = BaseFont.createFont("C:\\Windows\\Fonts\\simsun.ttc,1",  BaseFont.IDENTITY_H, 	BaseFont.NOT_EMBEDDED);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Font fontChinese = new Font(bfChinese, 10, Font.NORMAL);  //中文字体，解决中文导出不显示的bug
//                fontChinese.setColor(Color.BLUE);
                fontChinese.setStyle(Font.BOLD);
//                f8.setColor(Color.BLUE);
//                f8.setStyle(Font.BOLD);
                EmergencePlanTaskEntity emergencePlanTaskEntity= (EmergencePlanTaskEntity) model.get("emergencePlanTaskEntity");
                //派发人员姓名
                cell = new PdfPCell(new Paragraph("派发人员",fontChinese));
                // 设置无边框
                cell.setBorder(Rectangle.NO_BORDER);
                //居中对齐
                cell.setHorizontalAlignment(1);
                //将单元加入表格
                table.addCell(cell);
                //派发人员
                cell = new PdfPCell(new Paragraph(emergencePlanTaskEntity.getName(),fontChinese));  //运维人员姓名
                //居中对齐
                cell.setHorizontalAlignment(1);
                //将单元加入表格
                table.addCell(cell);
                //派发时间
                cell = new PdfPCell(new Paragraph("派发时间",fontChinese));
                //居中对齐
                cell.setHorizontalAlignment(1);
                cell.setBorder(Rectangle.NO_BORDER);
                //将单元加入表格
                table.addCell(cell);
                //派发时间
                String createTime=DateUtils.format(emergencePlanTaskEntity.getCreateTime(),DateUtils.DATE_TIME_PATTERN);
                cell = new PdfPCell(new Paragraph(createTime,fontChinese));  //运维人员姓名
                //居中对齐
                cell.setHorizontalAlignment(1);
                //将单元加入表格
                table.addCell(cell);

                //运维人员姓名
                cell = new PdfPCell(new Paragraph("运维人员姓名",fontChinese));
                //居中对齐
                cell.setHorizontalAlignment(1);
                cell.setBorder(Rectangle.NO_BORDER);
                //将单元加入表格
                table.addCell(cell);
//                //所属单位名称
//                cell = new PdfPCell(new Paragraph("所属单位名称",fontChinese));
//                //居中对齐
//                cell.setHorizontalAlignment(1);
//                //将单元加入表格
//                table.addCell(cell);
                //评估时间
                cell = new PdfPCell(new Paragraph("评审时间",fontChinese));
                //居中对齐
                cell.setHorizontalAlignment(1);
                cell.setBorder(Rectangle.NO_BORDER);
                //将单元加入表格
                table.addCell(cell);
                //评估评价
                cell = new PdfPCell(new Paragraph("评审评价",fontChinese));
                //居中对齐
                cell.setHorizontalAlignment(1);
                cell.setBorder(Rectangle.NO_BORDER);
                //将单元加入表格
                table.addCell(cell);
                //评估意见
                cell = new PdfPCell(new Paragraph("评审意见",fontChinese));
                cell.setBorder(Rectangle.NO_BORDER);
                //居中对齐
                cell.setHorizontalAlignment(1);
                //将单元加入表格
                table.addCell(cell);
                //获取数据模型中的评审列表
                List<EmergencePlanReviewEntity> reviewList= (List<EmergencePlanReviewEntity>)model.get("reviewList");
                for(EmergencePlanReviewEntity emergencePlanReviewQueryParam:reviewList){
                    document.add(new Chunk(("\n")));
                    cell = new PdfPCell(new Paragraph(emergencePlanReviewQueryParam.getName(),fontChinese));  //运维人员姓名
                    table.addCell(cell);
//                    cell = new PdfPCell(new Paragraph(emergencePlanReviewQueryParam.getProviderName(),fontChinese));  //所属单位名称
//                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(DateUtils.format(emergencePlanReviewQueryParam.getCreateTime(),DateUtils.DATE_TIME_PATTERN)));  //评审时间
                    cell.setBorder(Rectangle.NO_BORDER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(emergencePlanReviewQueryParam.getPjName(),fontChinese)); //评价
                    cell.setBorder(Rectangle.NO_BORDER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(emergencePlanReviewQueryParam.getYjjy(),fontChinese)); //意见
                    cell.setBorder(Rectangle.NO_BORDER);
                    table.addCell(cell);
                }

                //文档中加入表格
             document.add(table);
            }catch (DocumentException e){
                e.printStackTrace();
            }
        });
    }



}
