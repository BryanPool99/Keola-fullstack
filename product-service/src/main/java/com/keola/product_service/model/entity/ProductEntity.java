package com.keola.product_service.model.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
public class ProductEntity {

    @Id
    @Column("product_id")
    private Integer productId;

    @Column("description")
    private String description;

    @Column("stock")
    private Integer stock;

    @Column("price")
    private BigDecimal price;

    @Column("aud_usu_alt_ds")
    private String createdBy;

    @Column("aud_fec_alt_ff")
    private LocalDateTime creationDate;

    @Column("aud_est_cod_in")
    private Integer status;

    @Column("aud_usu_mod_ds")
    private String updatedBy;

    @Column("aud_fec_mod_ff")
    private LocalDateTime updateDate;
}