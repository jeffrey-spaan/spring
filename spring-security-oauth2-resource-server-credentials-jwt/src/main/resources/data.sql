INSERT INTO roles (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d471', 'ROLE_USER');
INSERT INTO roles (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d472', 'ROLE_MODERATOR');
INSERT INTO roles (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d473', 'ROLE_ADMIN');

INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d571', 'user:read');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d572', 'user:update');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d573', 'user:create');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d574', 'user:delete');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d575', 'moderator:read');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d576', 'moderator:update');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d577', 'moderator:create');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d578', 'moderator:delete');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d579', 'admin:read');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d580', 'admin:update');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d581', 'admin:create');
INSERT INTO permissions (id, name) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d582', 'admin:delete');

INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d471', 'f47ac10b-58cc-4372-a567-0e02b2c3d571');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d471', 'f47ac10b-58cc-4372-a567-0e02b2c3d572');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d471', 'f47ac10b-58cc-4372-a567-0e02b2c3d573');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d471', 'f47ac10b-58cc-4372-a567-0e02b2c3d574');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d472', 'f47ac10b-58cc-4372-a567-0e02b2c3d575');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d472', 'f47ac10b-58cc-4372-a567-0e02b2c3d576');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d472', 'f47ac10b-58cc-4372-a567-0e02b2c3d577');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d472', 'f47ac10b-58cc-4372-a567-0e02b2c3d578');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d473', 'f47ac10b-58cc-4372-a567-0e02b2c3d579');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d473', 'f47ac10b-58cc-4372-a567-0e02b2c3d580');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d473', 'f47ac10b-58cc-4372-a567-0e02b2c3d581');
INSERT INTO roles_permissions(role_id, permission_id) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d473', 'f47ac10b-58cc-4372-a567-0e02b2c3d582');

INSERT INTO users (id, username, email, password, role_id, enabled) VALUES ('550e8400-e29b-41d4-a716-446655440201', 'user', 'user@mail.com', '$argon2id$v=19$m=16384,t=2,p=1$+sNfYg6WWxWUS+c2FY1qQw$q+nWGRmQOt3tz3sqFfyNu546buc4O2tRei0eNVVrme0', 'f47ac10b-58cc-4372-a567-0e02b2c3d471', false);
INSERT INTO users (id, username, email, password, role_id, enabled) VALUES ('550e8400-e29b-41d4-a716-446655440202', 'moderator', 'moderator@mail.com', '$argon2id$v=19$m=16384,t=2,p=1$+sNfYg6WWxWUS+c2FY1qQw$q+nWGRmQOt3tz3sqFfyNu546buc4O2tRei0eNVVrme0', 'f47ac10b-58cc-4372-a567-0e02b2c3d472', false);
INSERT INTO users (id, username, email, password, role_id, enabled) VALUES ('550e8400-e29b-41d4-a716-446655440203', 'admin', 'admin@mail.com', '$argon2id$v=19$m=16384,t=2,p=1$+sNfYg6WWxWUS+c2FY1qQw$q+nWGRmQOt3tz3sqFfyNu546buc4O2tRei0eNVVrme0', 'f47ac10b-58cc-4372-a567-0e02b2c3d473', false);