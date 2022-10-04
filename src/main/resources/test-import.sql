insert into location (id,name) values (1001, 'HTL Leonding');
insert into location (id,name,location_id) values (1002, 'eg', 1001);
insert into location (id,name,location_id) values (1003, 'e581', 1002);
insert into location (id,name,location_id) values (1004, 'e582', 1002);

insert into thing (id, name, location_id) values (1001, 'leobox-e581', 1003);
insert into thing (id, name, location_id) values (1002, 'leobox-e582', 1004);

insert into unit (id, symbol) values (1001, '°C');
insert into unit (id, symbol) values (1002, 'PPM');

insert into sensortype (id, name, unit_id) values (1001, 'Temperature', 1001);
insert into sensortype (id, name, unit_id) values (1002, 'Co2', 1002);

insert into sensor (id, sensortype_id, thing_id) values (1001, 1001, 1001); -- leobox-e581 - temperature
insert into sensor (id, sensortype_id, thing_id) values (1002, 1002, 1001); -- leobox-e581 - Co2
insert into sensor (id, sensortype_id, thing_id) values (1003, 1001, 1002); -- leobox-e582 - temperature
insert into sensor (id, sensortype_id, thing_id) values (1004, 1002, 1002); -- leobox-e582 - Co2

insert into measurement (timestamp, value, sensor_id) values ('2020-02-05 19:10', 20.0, 1001); -- leobox-e581 - 20 °C
insert into measurement (timestamp, value, sensor_id) values ('2020-02-05 19:11', 19.0, 1001); -- leobox-e581 - 19 °C
insert into measurement (timestamp, value, sensor_id) values ('2020-02-05 19:10', 500.0, 1002); -- leobox-e581 - 500 PPM
insert into measurement (timestamp, value, sensor_id) values ('2020-02-05 19:11', 600.0, 1002); -- leobox-e581 - 600 PPM

insert into measurement (timestamp, value, sensor_id) values ('2020-02-05 19:10', 17.0, 1003); -- leobox-e582 - 17 °C
insert into measurement (timestamp, value, sensor_id) values ('2020-02-05 19:11', 18.0, 1003); -- leobox-e582 - 18 °C
insert into measurement (timestamp, value, sensor_id) values ('2020-02-05 19:10', 400.0, 1004); -- leobox-e582 - 400 PPM
insert into measurement (timestamp, value, sensor_id) values ('2020-02-05 19:11', 700.0, 1004); -- leobox-e582 - 700 PPM
