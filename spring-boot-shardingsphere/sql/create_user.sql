CREATE TABLE `t_user202012` (
    `user_id` bigint(20) NOT NULL,
    `name` varchar(255) COLLATE utf8_bin NOT NULL,
    `register_date` datetime NOT NULL,
    `id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;