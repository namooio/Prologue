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

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CodeNameList implements JsonSerializable {
	//
	private List<CodeName> codeNames;

	public CodeNameList(int capacity) {
		//
		this.codeNames = new ArrayList<>(capacity);
	}

	public CodeNameList() {
		//
		this.codeNames = new ArrayList<>();
	}

	public CodeNameList(CodeName codeName) {
		//
		this();
		this.codeNames.add(codeName);
	}

	public CodeNameList(String code, String name) {
		//
		this();
		this.codeNames.add(new CodeName(code, name));
	}

	public CodeNameList(List<CodeName> codeNames) {
		//
		this();
		this.codeNames.addAll(codeNames);
	}

	@Override
	public String toString() {
		//
		return toJson();
	}

	public static CodeNameList newInstance(String code, String name) {
		//
		return new CodeNameList(code, name);
	}

	public static CodeNameList newInstance(CodeName codeName) {
		//
		return new CodeNameList(codeName);
	}

	public static CodeNameList fromJson(String json) {
		//
		return JsonUtil.fromJson(json, CodeNameList.class);
	}

	public static CodeNameList sample() {
	    //
        return new CodeNameList(CodeName.sample());
    }

	public CodeNameList add(CodeName codeName) {
		//
		codeNames.add(codeName);
		return this;
	}

	public CodeNameList add(String code, String name) {
		//
		codeNames.add(new CodeName(code, name));
		return this;
	}

	public void addAll(List<CodeName> codeNames) {
		//
		this.codeNames.addAll(codeNames);
	}

	public List<CodeName> list() {
		//
		return codeNames;
	}

	public void removeByCode(String targetCode) {
		//
		for(CodeName codeName : getByCode(targetCode)) {
			codeNames.remove(codeName);
		}
	}

	public List<CodeName> getByCode(String targetCode) {
		//
		List<CodeName> foundIdNames = new ArrayList<>();
		for(CodeName codeName : codeNames) {
			if(codeName.getCode().equals(targetCode)) {
				foundIdNames.add(codeName);
			}
		}

		return foundIdNames;
	}

	public int size() {
		return codeNames.size();
	}

    public static void main(String[] args) {
        //
        System.out.println(sample());
        System.out.println(sample().toPrettyJson());
    }
}