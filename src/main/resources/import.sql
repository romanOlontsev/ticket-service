
insert into plane (depart, duration, "from", is_deleted, name, places, to, id) values ('2022-05-23T23:05', '18000000000000', 'Moscow', 'false', 'Air', '17', 'Nizhnii Novgorod', '1');
insert into plane (depart, duration, "from", is_deleted, name, places, to, id) values ('2022-06-23T23:01', '9000000000000', 'Taganrog', 'false', 'AirMed', '15', 'Nizhnii Novgorod', '2');
insert into plane (depart, duration, "from", is_deleted, name, places, to, id) values ('2022-07-1T12:00', '1000000000000', 'Ufa', 'false', 'AirBad', '9', 'Sarov', '3');

insert into user (is_deleted, lastname, name, passport, id) values ('false', 'Pushka', 'Nadia', 'RF404', '3');
insert into user (is_deleted, lastname, name, passport, id) values ('false', 'Dinov', 'Sasha', 'RF201', '4');

insert into ticket (is_deleted, plane_id, price, user_id, id) values ('false', '1', '9', '3', '5');
insert into ticket (is_deleted, plane_id, price, user_id, id) values ('false', '1', '9', '4', '6');
insert into ticket (is_deleted, plane_id, price, id) values ('false', '1', '9', '7');
insert into ticket (is_deleted, plane_id, price, id) values ('false', '1', '9', '8');
insert into ticket (is_deleted, plane_id, price, id) values ('false', '2', '20', '9');
insert into ticket (is_deleted, plane_id, price, id) values ('false', '2', '20', '10');
insert into ticket (is_deleted, plane_id, price, id) values ('false', '2', '20', '11');
insert into ticket (is_deleted, plane_id, price, id) values ('false', '2', '20', '12');
insert into ticket (is_deleted, plane_id, price, id) values ('false', '3', '20', '13');
insert into ticket (is_deleted, plane_id, price, id) values ('false', '3', '20', '14');
insert into ticket (is_deleted, plane_id, price, id) values ('false', '3', '20', '15');

insert into plane_tickets(plane_id, tickets_id) values ('1', '5');
insert into plane_tickets(plane_id, tickets_id) values ('1', '6');
insert into plane_tickets(plane_id, tickets_id) values ('1', '7');
insert into plane_tickets(plane_id, tickets_id) values ('1', '8');
insert into plane_tickets(plane_id, tickets_id) values ('2', '9');
insert into plane_tickets(plane_id, tickets_id) values ('2', '10');
insert into plane_tickets(plane_id, tickets_id) values ('2', '11');
insert into plane_tickets(plane_id, tickets_id) values ('2', '12');
insert into plane_tickets(plane_id, tickets_id) values ('3', '13');
insert into plane_tickets(plane_id, tickets_id) values ('3', '14');
insert into plane_tickets(plane_id, tickets_id) values ('3', '15');

insert into user_tickets(user_id, tickets_id) values ('3', '5');
insert into user_tickets(user_id, tickets_id) values ('4', '6');
insert into user_tickets(user_id, tickets_id) values ('4', '12');