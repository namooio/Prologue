/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.type;

import io.namoo.prologue.util.json.JsonSerializable;
import io.namoo.prologue.util.json.JsonUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class CodeNameMap implements JsonSerializable {
	//
	private Map<String, String> codeNameMap;

	public CodeNameMap(int capacity) {
		//
		this.codeNameMap = new LinkedHashMap<>(capacity);
	}

	public CodeNameMap() {
		//
		this.codeNameMap = new LinkedHashMap<>();
	}

	public CodeNameMap(CodeName codeName) {
		//
		this();
		this.codeNameMap.put(codeName.getCode(), codeName.getName());
	}

	public CodeNameMap(String id, String name) {
		//
		this();
		this.codeNameMap.put(id, name);
	}

	public String toString() {
		//
		return toJson();
	}

	public static CodeNameMap fromJson(String json) {
		//
		return JsonUtil.fromJson(json, CodeNameMap.class);
	}

	public static CodeNameMap sample() {
	    //
        return new CodeNameMap(CodeName.sample());
    }

	public CodeNameMap add(CodeName codeName) {
		//
		codeNameMap.put(codeName.getCode(), codeName.getName());
		return this;
	}

	public CodeNameMap add(String code, String name) {
		//
		codeNameMap.put(code, name);
		return this;
	}

	public void addAll(List<CodeName> codeNames) {
		//
		for(CodeName codeName : codeNames) {
			this.codeNameMap.put(codeName.getCode(), codeName.getName());
		}
	}

	public List<CodeName> list() {
		//
		List<CodeName> codeNames = new ArrayList<>();
		Iterator<String> keyIterator = codeNameMap.keySet().iterator();
		while(keyIterator.hasNext()) {
			String key = keyIterator.next();
			codeNames.add(new CodeName(key, codeNameMap.get(key)));
		}
		
		return codeNames;
	}

	public void removeByCode(String code) {
		//
		codeNameMap.remove(code);
	}

	public int size() {
		return codeNameMap.size();
	}

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}