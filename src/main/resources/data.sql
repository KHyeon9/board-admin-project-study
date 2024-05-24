-- user account
insert into admin_account (user_id, user_password, role_types, nickname, email, memo, created_at, created_by, modified_at, modified_by)
values ('hyeon', '{noop}dummy', 'ADMIN', 'Hyeon', 'hyeon@mail.com', 'Hyeon', now(), 'hyeon', now(), 'hyeon'),
       ('soo', '{noop}dummy', 'MANAGER', 'soo', 'soo@mail.com', 'soo', now(), 'soo', now(), 'soo'),
       ('kim', '{noop}dummy', 'MANAGER,DEVELOPER', 'kim', 'kim@mail.com', 'kim', now(), 'kim', now(), 'kim'),
       ('park', '{noop}dummy', 'USER', 'park', 'park@mail.com', 'park', now(), 'park', now(), 'park')
;

