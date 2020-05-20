/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */
package io.namoo.prologue.ddd;

import io.namoo.prologue.util.json.JsonSerializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public abstract class DomainEntityDoc implements JsonSerializable {
    //
    protected String id;

    protected long entityVersion;

    protected DomainEntityDoc(String id) {
        //
        this.id = id;
        this.entityVersion = 0L;
    }

    protected DomainEntityDoc(DomainEntity domainEntity) {
        //
        this.id = domainEntity.getId();
        this.entityVersion = domainEntity.getEntityVersion();
    }

    @Override
    public boolean equals(Object target) {
        //
        if (this == target) {
            return true;
        } else if (target != null && this.getClass() == target.getClass()) {
            DomainEntityDoc entity = (DomainEntityDoc)target;
            return Objects.equals(this.id, entity.id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        //
        return Objects.hash(id);
    }

    public String toString(){
        //
        return toJson();
    }
}
