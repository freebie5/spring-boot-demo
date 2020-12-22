CREATE TABLE `t_order_0` (
    `order_id` bigint(32) NOT NULL,
    `name` varchar(255) NOT NULL,
    `id` bigint(32) NOT NULL,
    `user_id` bigint(32) NOT NULL,
    `money` decimal(20,2) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;