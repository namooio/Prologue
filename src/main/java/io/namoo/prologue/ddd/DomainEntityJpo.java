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

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class DomainEntityJpo implements JsonSerializable {
    //
    @Id
    protected String id;

    @Version
    protected long entityVersion;

    protected DomainEntityJpo(String id) {
        //
        this.id = id;
        this.entityVersion = 0L;
    }

    protected DomainEntityJpo(DomainEntity domainEntity) {
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
            DomainEntityJpo entity = (DomainEntityJpo)target;
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
