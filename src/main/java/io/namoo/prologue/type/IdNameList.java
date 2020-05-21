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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Setter
public class IdNameList implements JsonSerializable, Serializable {
	//
	private static final long serialVersionUID = -7495186026035848142L;
	private List<IdName> idNames;

	public IdNameList(int capacity) {
		//
		this.idNames = new ArrayList<IdName>(capacity);
	}

	public IdNameList() {
		//
		this.idNames = new ArrayList<>();
	}

	public IdNameList(IdName idName) {
		//
		this();
		this.idNames.add(idName);
	}

	public IdNameList(String id, String name) {
		//
		this();
		this.idNames.add(new IdName(id, name));
	}

	public IdNameList(List<IdName> idNames) {
		//
		this.idNames = idNames;
	}

	public static IdNameList newInstance(String id, String name) {
		//
		return new IdNameList(id, name);
	}

	public static IdNameList newInstance(IdName idName) {
		//
		return new IdNameList(idName);
	}

	@Override
	public String toString() {
		//
		return toJson();
	}

	public static IdNameList fromJson(String json) {
		//
		return JsonUtil.fromJson(json, IdNameList.class);
	}

	public static IdNameList sample() {
	    //
        return new IdNameList(IdName.sample());
    }

	public IdNameList add(IdName idName) {
		//
		idNames.add(idName);
		return this;
	}

	public IdNameList add(String id, String name) {
		//
		idNames.add(new IdName(id, name));
		return this;
	}

	public void addAll(List<IdName> idNames) {
		//
		this.idNames.addAll(idNames);
	}

	public List<IdName> list() {
		//
		return idNames;
	}

	public void removeById(String id) {
		//
		for(IdName idName : getById(id)) {
			idNames.remove(idName);
		}
	}

	public List<IdName> getById(String id) {
		//
		List<IdName> foundIdNames = new ArrayList<>();
		for(IdName idName : idNames) {
			if(idName.getId().equals(id)) {
				foundIdNames.add(idName);
			}
		}

		return foundIdNames;
	}

	public IdName getByNameFirst(String name) {
		//
		for(IdName idName : idNames) {
			if(idName.getName().equals(name)) {
				return idName;
			}
		}

		throw new NoSuchElementException("Name: " + name);
	}

	public boolean containsName(String name) {
		//
		for(IdName idName : this.idNames) {
			if (name.equals(idName.getName())) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return idNames.size();
	}

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}
