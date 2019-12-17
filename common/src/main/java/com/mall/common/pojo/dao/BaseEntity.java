package com.mall.common.pojo.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * DESCRIPTION: Base Entity
 *
 * @author <a href="mailto:amos.wang@xiaoi.com">amos.wang</a>
 * @date 2019/12/17
 */
@Getter
@Setter
@MappedSuperclass
@Accessors(chain = true)
@Where(clause = "DELETE_FLAG=0")
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "base-uuid")
    @GenericGenerator(name = "base-uuid", strategy = "uuid")
    private String id;

    @CreatedDate
    private LocalDateTime createTime;

    @CreatedBy
    private String createUser;

    @LastModifiedDate
    private LocalDateTime modifyTime;

    @LastModifiedBy
    private String modifyUser;

    @Column(name = "DELETE_FLAG", nullable = false)
    private Boolean deleteFlag;

    @PrePersist
    public void prePersist() {
        deleteFlag = false;
    }

    @PreUpdate
    public void preUpdate() {
        if (deleteFlag == null) {
            deleteFlag = false;
        }
    }

}
