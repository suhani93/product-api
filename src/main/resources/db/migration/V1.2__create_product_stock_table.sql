CREATE TABLE t_product_stock(
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT COMMENT '상품 재고 PK',
    product_info_id BIGINT NOT NULL UNIQUE COMMENT '상품 정보 FK',
    quantity INT NOT NULL COMMENT '재고 수량',
    created_at DATETIME(6) COMMENT '등록일',
    created_by VARCHAR(255) COMMENT '등록자',
    PRIMARY KEY(id),
    FOREIGN KEY(product_info_id) REFERENCES t_product_info(id)
) ;
