package com.ms.junz.domain;

import java.util.HashSet;
import java.util.Set;
import com.ms.junz.enums.ErrorType;

public class DatabaseError {
	private int id;
	private ErrorType type;
	private String name;
	private String suggestion;
	private Set<ErrorCondition> conditions = new HashSet<ErrorCondition>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ErrorType getType() {
		return type;
	}
	public void setType(ErrorType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public Set<ErrorCondition> getConditions() {
		return conditions;
	}
	public void setConditions(Set<ErrorCondition> conditions) {
		this.conditions = conditions;
	}
	@Override
	public String toString() {
		return "DatabaseError [id=" + id + ", type=" + type + ", name=" + name
				+ ", suggestion=" + suggestion + ", conditions=" + conditions
				+ "]";
	}
	
}
