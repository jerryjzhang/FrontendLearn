package com.ms.junz.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ms.junz.domain.RequestHistory;


@Controller
@RequestMapping("/alcazar/refresh")
public class RefreshController {       
    private static List<RequestHistory> requestHistory = new ArrayList<RequestHistory>();

    static{
        RequestHistory req = new RequestHistory();
        req.setName("fakeRequest");
        req.setUpdateDatetime("2013-09-10");
        req.setSourceDbName("fakeSrcDb");
        req.setTargetDbName("fakeTgtDb");
        req.setStatus("");
        requestHistory.add(req);
        
    }
    
    @RequestMapping(value = "/get/refresh/history/request/{requestName}", method = RequestMethod.GET)
    public @ResponseBody List<RequestHistory> getRequestHistoryByName(@PathVariable String requestName) {
        return requestHistory;
    }
    
    @RequestMapping(value = "/get/refresh/history/source/server/{serverName}/dbname/{dbName}", method = RequestMethod.GET)
    public @ResponseBody List<RequestHistory> getRequestHistoryBySrcDbName(@PathVariable String serverName, @PathVariable String dbName) {
        return requestHistory;
    }
}