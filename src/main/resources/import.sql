INSERT INTO users (name, last_name, passport, is_deleted) VALUES ('Oleg', 'Ivanov', '4123 123243', false);
INSERT INTO users (name, last_name, passport, is_deleted) VALUES ('Maria', 'Rebusova', '7612 654123', false);
INSERT INTO users (name, last_name, passport, is_deleted) VALUES ('Marat', 'Dimin', '6643 123512', true);
INSERT INTO users (name, last_name, passport, is_deleted) VALUES ('Vlada', 'Kubikova', '5543 512332', false);

INSERT INTO planes (name, flight_number, places, depart, landing, "from", "to", is_deleted) VALUES ('Air France', 11, 204, '2022-11-02T23:05', '2022-11-03T03:05', 'Moscow', 'Paris', false);
INSERT INTO planes (name, flight_number, places, depart, landing, "from", "to", is_deleted) VALUES ('Aeroflot', 13, 322, '2022-11-03T13:00', '2022-11-03T017:25', 'Nizhny Novgorod', 'Almaty', false);
INSERT INTO planes (name, flight_number, places, depart, landing, "from", "to", is_deleted) VALUES ('Globus', 12, 120, '2022-11-14T11:35', '2022-11-14T013:40', 'Moscow', 'Sochi', false);

insert into tickets (price, is_deleted, plane_id, user_id) values ('10.15',false, 1, 1);
insert into tickets (price, is_deleted, plane_id, user_id) values ('12.50',false, 2, 3);
insert into tickets (price, is_deleted, plane_id, user_id) values ('15.45',false, 3, 4);
insert into tickets (price, is_deleted, plane_id, user_id) values ('10.15',false, 1, 2);

insert into planes_tickets(plane_id, tickets_id) values (1, 1);
insert into planes_tickets(plane_id, tickets_id) values (1, 4);
insert into planes_tickets(plane_id, tickets_id) values (2, 2);
insert into planes_tickets(plane_id, tickets_id) values (3, 3);
--
insert into users_tickets(user_id, tickets_id) values (1, 1);
insert into users_tickets(user_id, tickets_id) values (4, 4);
insert into users_tickets(user_id, tickets_id) values (3, 2);
insert into users_tickets(user_id, tickets_id) values (4, 3);
