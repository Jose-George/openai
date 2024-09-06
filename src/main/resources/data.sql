INSERT IGNORE INTO `users` (`id`, `name`, `date_of_birth`, `occupation`, `university_registration`, `educational_institution`, `city`, `state`, `email`, `password`, `created_at`, `update_at`, `professional_record`, `is_active`)
VALUES (1, 'sup', NULL, 'estudante', NULL, NULL, NULL, NULL, 'sup@openai.com', '$2a$10$0iF.EQMER5vsXD3UDuGED.KQxAyOZD3KbNAAb1x6.lzDnX6tlCMxi', NOW(), NOW(), NULL, TRUE);

INSERT IGNORE INTO roles (role_id, name) VALUES (1, 'admin');
INSERT IGNORE INTO roles (role_id, name) VALUES (2, 'basic');
INSERT IGNORE INTO roles (role_id, name) VALUES (3, 'sophia');

INSERT IGNORE INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT IGNORE INTO users_roles (user_id, role_id) VALUES (1, 3);

INSERT IGNORE INTO `users` (`id`, `name`, `date_of_birth`, `occupation`, `university_registration`, `educational_institution`, `city`, `state`, `email`, `password`, `created_at`, `update_at`, `professional_record`, `is_active`)
VALUES (2, 'basic user', NULL, 'estudante', NULL, NULL, NULL, NULL, 'basic@openai.com', '$2a$10$0iF.EQMER5vsXD3UDuGED.KQxAyOZD3KbNAAb1x6.lzDnX6tlCMxi', NOW(), NOW(), NULL, TRUE);

INSERT IGNORE INTO users_roles (user_id, role_id) VALUES (2, 2);