CREATE TABLE t_category_info(
    id BIGINT NOT NULL UNIQUE COMMENT '카테고리 정보 PK',
    name VARCHAR(255) NOT NULL COMMENT '카테고리 명',
    parent_id BIGINT COMMENT '상위 카테고리 PK',
    order_number INT COMMENT '노출 순서',
    is_deleted VARCHAR(5) NOT NULL COMMENT '삭제 여부',
    PRIMARY KEY(id),
    FOREIGN KEY (parent_id) REFERENCES t_category_info(id)
);
