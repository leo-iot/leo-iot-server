insert into location (id,name) values (1001, 'HTL Leonding');
insert into location (id,name,location_id) values (1002, 'eg', 1001);
insert into location (id,name,location_id) values (1003, 'e581', 1002);
insert into location (id,name,location_id) values (1004, 'e582', 1002);

insert into thing (id, name, location_id) values (1001, 'leobox-e581', 1003);
insert into thing (id, name, location_id) values (1002, 'leobox-e582', 1004);
