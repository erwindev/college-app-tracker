insert into college (id, name) values ('c5a50c4b-4448-4f81-bc48-bab1f9af1848', 'University of North Florida');
insert into college (id, name) values ('9cadb84b-b4b5-4ce0-8e67-87e1ee8c640e', 'University of Florida');
insert into college (id, name) values ('89cf2d02-3118-42af-ab50-4b62146135e4', 'University of Central Florida');


insert into
  address (
    address_id,
    address_type_id,
    street1,
    street2,
    city,
    state,
    country,
    zipcode
  ) values
  (
  'c5a50c4b-4448-4f81-bc48-bab1f9af1848',
  1001,
  '123 main st',
  null,
  'Jacksonville',
  'FL',
  'USA',
  '32256');

insert into
  address (
    address_id,
    address_type_id,
    street1,
    street2,
    city,
    state,
    country,
    zipcode
  ) values
  (
  '9cadb84b-b4b5-4ce0-8e67-87e1ee8c640e',
  1001,
  '123 main st',
  null,
  'Gainesville',
  'FL',
  'USA',
  '32293');

insert into
  address (
    address_id,
    address_type_id,
    street1,
    street2,
    city,
    state,
    country,
    zipcode
  ) values
  (
  '89cf2d02-3118-42af-ab50-4b62146135e4',
  1001,
  '123 main st',
  null,
  'Orlando',
  'FL',
  'USA',
  '34004');

insert into student (id, first_name, last_name, email, password)
  values ('72b32928-2b13-473d-ad63-7fe93d94aa9f', 'Erwin', 'Alberto', 'ealberto@me.com', '$2a$10$PNGP4VMWt7bbA..yLUviS.Yp.2o5zHPkr11OSGKKV6UGcvD2NLmZu');

insert into
  address (
    address_id,
    address_type_id,
    street1,
    street2,
    city,
    state,
    country,
    zipcode
  ) values
  (
  '72b32928-2b13-473d-ad63-7fe93d94aa9f',
  1002,
  '10082 Brighton st',
  null,
  'Jacksonville',
  'FL',
  'USA',
  '32256');