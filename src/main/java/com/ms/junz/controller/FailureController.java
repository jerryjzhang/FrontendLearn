package com.ms.junz.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ms.junz.domain.RefreshFailure;
import com.ms.junz.domain.RequestHistory;

@Controller
@RequestMapping("/alcazar/failure")
public class FailureController {       
    private static List<RefreshFailure> failures = new ArrayList<RefreshFailure>();

    static{
        RefreshFailure r = new RefreshFailure();
        r.setName("fake1");
        r.setErrorDatetime("2013-06-21");
        r.setSourceDbName("NYP_ALZ.QC_ETL");
        r.setTargetDbName("NYT_ALZ.QC_ETL");
        r.setStep("COPY_DATA");
        r.setReason("New Error");
        r.setRootCause("rootCause");
        r.setFixNumber("TDMS-3049");
        r.setId(1);
        failures.add(r);
        
        RefreshFailure r1 = new RefreshFailure();
        r1.setName("fake2");
        r1.setErrorDatetime("2013-06-23");
        r1.setSourceDbName("NYP_ALZ1.QC_ETL");
        r1.setTargetDbName("NYT_ALZ1.QC_ETL");
        r1.setStep("COPY_DATA");
        r1.setReason("New Error");
        r1.setErrorMessage("dddddddddddddddddddddddddddddddd");
        r1.setId(2);
        failures.add(r1);
        
        for(int i = 3; i < 9; ++i){
        	failures.add(fakeRefreshFailure(i));
        }
    }
    
    private static RefreshFailure fakeRefreshFailure(int id){
    	RefreshFailure r = new RefreshFailure();
        r.setName("fakedddddddddddddddddddd" + id);
        r.setErrorDatetime("2013-06-21");
        r.setSourceDbName("NYP_ALZ.QC_ETL" + id);
        r.setTargetDbName("NYT_ALZ.QC_ETL" + id);
        r.setStep("COPY_DATA");
        r.setReason("New Error");
        r.setRootCause("rootCause");
        r.setFixNumber("TDMS-3049");
        r.setId(id);
        
        return r;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public @ResponseBody List<RefreshFailure> getAll() {
        return failures;
    }
    
    @RequestMapping(value = "/get/startDate/{startDate}/endDate/{endDate}", method = RequestMethod.GET)
    public @ResponseBody List<RefreshFailure> getAllByRange(@PathVariable String startDate, @PathVariable String endDate) {
        //return errorHistoryDao.getAllByDateRange(startDate, endDate);
        return failures;
    }
    
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public @ResponseBody boolean updateFailures(@RequestBody List<String> failures)throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        for(String s : failures){
//            RefreshFailure f = mapper.readValue(s, RefreshFailure.class);
//            if(!errorHistoryDao.existed(f.getId())){
//                errorHistoryDao.insert(f);
//            }else{
//                errorHistoryDao.update(f);
//            }
//        }
//            
//        return true;
//    }
//    
//    @RequestMapping(value = "/get/file2", method = RequestMethod.GET, produces= "application/msword")
//    public @ResponseBody FileSystemResource getFile2(HttpServletResponse resp) {
//        resp.setHeader("Content-Disposition", "attachement; filename=foo.docx");
//        return new FileSystemResource(new File("U:\\DWS_QA.docx"));
//    }
//    
//    @RequestMapping(value = "/get/file/workOrderId/{workOrderId}/fileName/{fileName}", method = RequestMethod.GET)
//    public  void  getTransactionLogFile(HttpServletResponse resp, @PathVariable String workOrderId,
//            @PathVariable String fileName) throws Exception{
//        try {
//            String baseDir = errorHistoryDao.getJavaConfigValue("BASE_REPORT_PATH");
//            String filePath = baseDir + "/" + workOrderId + "/" + fileName;
//   
//            File f = new File(filePath);         
//            resp.setContentType("text/plain");
//            //resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//            FileCopyUtils.copy(new FileInputStream(f), resp.getOutputStream());          
//            resp.flushBuffer();
//          } catch (IOException ex) {
//              throw new RuntimeException(ex.getMessage());
//          }
//    }
//    
//    @RequestMapping(value = "/get/file/startDate/{startDate}/endDate/{endDate}", method = RequestMethod.GET)
//    public  void  getFile(HttpServletResponse resp, @PathVariable String startDate,
//            @PathVariable String endDate) throws Exception{
//        try {
//            
//            String fileName = "FailureList-" + startDate + "-" + endDate+ ".xls";
//            createFile("//tmp//" + fileName, errorHistoryDao.getAllByDateRange(startDate, endDate));
//            File f = new File("//tmp//" + fileName);         
//            resp.setContentType("application/xls");
//            resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//            FileCopyUtils.copy(new FileInputStream(f), resp.getOutputStream());          
//            resp.flushBuffer();
//          } catch (IOException ex) {
//              throw new RuntimeException(ex.getMessage());
//          }
//    }
//    
//    
//    
//    private static void createFile(String fileName, List<RefreshFailure> failures) throws Exception{
//        WritableWorkbook wb = Workbook.createWorkbook(new File(fileName));
//        WritableSheet sheet = wb.createSheet("Failure List", 0);
//        writeHeaderCells(sheet);
//        int x = 1;
//        WritableCellFormat cf = new WritableCellFormat();
//        cf.setWrap(true);
//        for(RefreshFailure rf : failures){
//            int y = 0;
//            sheet.addCell(new Label(y++, x, rf.getName(), cf));
//            sheet.addCell(new Label(y++, x, rf.getErrorDatetime(), cf));
//            sheet.addCell(new Label(y++, x, rf.getSourceDbName(), cf));
//            sheet.addCell(new Label(y++, x, rf.getTargetDbName(), cf));
//            sheet.addCell(new Label(y++, x, rf.getStep(), cf));
//            sheet.addCell(new Label(y++, x, rf.getReason(), cf));
//            sheet.addCell(new Label(y++, x, rf.getRootCause(), cf));
//            sheet.addCell(new Label(y++, x, rf.getFinalSolution(), cf));
//            sheet.addCell(new Label(y++, x, rf.getFixNumber(), cf));
//            sheet.addCell(new Label(y++, x, rf.getErrorMessage(), cf));            
//            x++;
//        }
//        
//        wb.write();
//        wb.close();        
//    }
//    
//    private static void writeHeaderCells(WritableSheet sheet)throws Exception{
//        String [] headers = new String [] {"Request Name", "Date Time",
//                "Source Database", "Target Database", "Failed Step",
//                "Why no Suggestion", "Root Cause", "Final Solution",
//                "#Bug/Enhancement", "Error Message"};
//        WritableCellFormat cf = new WritableCellFormat(
//                new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD));
//        
//        for(int i = 0; i < headers.length; ++i){
//            Label cell = new Label(i, 0, headers[i], cf);
//            sheet.addCell(cell);
//            CellView cv = sheet.getColumnView(i);
//            cv.setSize(5000);
//            sheet.setColumnView(i, cv);
//        }
//    }
//    
//    public static void main(String [] arg)throws Exception{
//        createFile("tmp//alcazar//temp.xls", failures);
//    }
}