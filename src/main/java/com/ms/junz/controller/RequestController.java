package com.ms.junz.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ms.junz.dao.RequestDao;
import com.ms.junz.domain.Request;


@RequestMapping("/alcazar/requests")
@Controller
public class RequestController {
	@Inject
	RequestDao requestDao;
	
	@RequestMapping(value = "/create/name/{name}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Boolean createRequest(@PathVariable String name) {
		Request req = new Request();
		req.setName(name);
		requestDao.save(req);
        return true;
    }
	
	@RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Request getRequestByName(@PathVariable String name) {
		List<Request> requests = requestDao.findByName(name);
		if(requests.isEmpty())
			return null;
        return requests.get(0);
    }
	
	@RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Request> getRequestAll() {
		List<Request> requests = new ArrayList<Request>(); 
		for(Request req : requestDao.findAll()){
			requests.add(req);
		}
		//add comment
        return requests;
    }
	
	@RequestMapping(value = "/get/file", method = RequestMethod.GET, produces = "application/vnd.ms-excel")
    public @ResponseBody FileSystemResource getFile(HttpServletResponse response) {
		//response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=Database.xls");
		return new FileSystemResource(new File("D:\\Documents and Settings\\Owner\\My Documents\\Downloads\\yhachina_hotels.xls"));
    }
	
	@RequestMapping(value = "/get/file2", method = RequestMethod.GET, produces = "application/vnd.ms-excel")
    public @ResponseBody FileSystemResource getFile2(HttpServletResponse response) {
		//response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=Database.xls");
		return new FileSystemResource(new File("D:\\Documents and Settings\\Owner\\My Documents\\Downloads\\yhachina_hotels.xls"));
    }
}
