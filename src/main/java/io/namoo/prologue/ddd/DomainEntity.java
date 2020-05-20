/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.ddd;

import io.namoo.prologue.type.IdName;
import io.namoo.prologue.util.json.JsonSerializable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public abstract class DomainEntity implements JsonSerializable, Serializable {
	//
	private static final long serialVersionUID = 805201038388117274L;
	private String id;
	private long entityVersion;

	protected DomainEntity() {
	    //
		this.id = UUID.randomUUID().toString();
	}

	protected DomainEntity(String id) {
	    //
		this.id = id;
	}

	protected DomainEntity(DomainEntity domainEntity) {
	    //
        this.id = domainEntity.getId();
        this.entityVersion = domainEntity.getEntityVersion();
    }

	public IdName genEntityIdName() {
		//
		return new IdName(this.id, this.getClass().getSimpleName());
	}

	public boolean equals(Object target) {
		//
		if (this == target) {
			return true;
		}

		if (target == null || getClass() != target.getClass()) {
			return false;
		}

		DomainEntity entity = (DomainEntity) target;

		return Objects.equals(id, entity.id);
	}

	public int hashCode() {
		return Objects.hash(id);
	}

	public String toString() {
	    //
		return toJson();
	}

	public String toPrettyString() {
		//
		return toPrettyJson();
	}
}