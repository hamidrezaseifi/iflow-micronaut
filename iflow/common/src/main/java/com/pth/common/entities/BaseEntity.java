package com.pth.common.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(name = "id")
    protected UUID id;

    @Version
    @Column(name = "version")
    protected Integer version;

    public BaseEntity() {
        super();

        this.id = UUID.randomUUID();
        this.version = 0;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }

}