package com.ms.junz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ms.junz.domain.DatabaseError;
import com.ms.junz.domain.ErrorCondition;
import com.ms.junz.enums.ErrorConditionType;
import com.ms.junz.enums.ErrorType;

@Controller
@RequestMapping("/alcazar/runbook")
public class RunbookController {
	private static List<DatabaseError> errors = new ArrayList<DatabaseError>();
	
	static {
		DatabaseError err = new DatabaseError();
		err.setId(1);
		err.setName("Lingering Connection");
		err.setType(ErrorType.DB_ERROR);
		err.setSuggestion("File DBAU ticket");
		ErrorCondition ec = new ErrorCondition();
		ec.setId(1);
		ec.setType(ErrorConditionType.STRING_MATCH);
		ec.setValue("Condition");
		err.getConditions().add(ec);
		
		/*errors.add(err);*/
	}

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public @ResponseBody List<DatabaseError> getAll() {
        return errors;
    }
    
    @RequestMapping(value = "/post/error", method = RequestMethod.POST, consumes="application/json; charset=utf-8")
    public @ResponseBody String updateError(@RequestBody DatabaseError err) {
    	errors.add(err);
    	System.out.println(err);
        return "OK";
    }
    
    
}
