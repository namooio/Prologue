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
public class IdNameMap implements JsonSerializable {
	//
	private Map<String, String> idNameMap;

	public IdNameMap(int capacity) {
		//
		this.idNameMap = new LinkedHashMap<>(capacity);
	}

	public IdNameMap() {
		//
		this.idNameMap = new LinkedHashMap<>();
	}

	public IdNameMap(IdName idName) {
		//
		this();
		this.idNameMap.put(idName.getId(), idName.getName());
	}

	public IdNameMap(String id, String name) {
		//
		this();
		this.idNameMap.put(id, name);
	}

	public String toString() {
		//
		return toJson();
	}

	public static IdNameMap fromJson(String json) {
		//
		return JsonUtil.fromJson(json, IdNameMap.class);
	}

	public static IdNameMap sample() {
	    //
        return new IdNameMap(IdName.sample());
    }

	public IdNameMap put(IdName idName) {
		//
		idNameMap.put(idName.getId(), idName.getName());
		return this;
	}

	public IdNameMap put(String id, String name) {
		//
		idNameMap.put(id, name);
		return this;
	}

	public void addAll(List<IdName> idNames) {
		//
		for(IdName idName : idNames) {
			this.idNameMap.put(idName.getId(), idName.getName());
		}
	}

	public List<IdName> list() {
		//
		List<IdName> idNames = new ArrayList<>(idNameMap.size());
		Iterator<String> keyIterator = idNameMap.keySet().iterator();
		while(keyIterator.hasNext()) {
			String key = keyIterator.next();
			idNames.add(new IdName(key, idNameMap.get(key)));
		}

		return idNames;
	}

	public void removeById(String id) {
		//
		idNameMap.remove(id);
	}

	public int size() {
		return idNameMap.size();
	}

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}