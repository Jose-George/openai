CREATE TABLE IF NOT EXISTS `users` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `date_of_birth` DATETIME,
    `occupation` VARCHAR(255) NOT NULL,
    `university_registration` VARCHAR(255),
    `educational_institution` VARCHAR(255),
    `city` VARCHAR(255),
    `state` VARCHAR(255),
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `created_at` DATETIME,
    `update_at` DATETIME,
    `professional_record` TEXT,
    `is_active` BOOLEAN
);
