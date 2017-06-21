create table student (
  id UUID PRIMARY KEY,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  email VARCHAR(100),
  password VARCHAR(100),
  created TIMESTAMP DEFAULT (now()),
  last_login TIMESTAMP,
  last_modified TIMESTAMP DEFAULT (now())
);

create table college (
  id UUID PRIMARY KEY,
  name VARCHAR(250),
  created TIMESTAMP DEFAULT (now()),
  last_modified TIMESTAMP DEFAULT (now())
);

create table college_app (
  student_id UUID,
  college_id UUID,
  created TIMESTAMP DEFAULT (now()),
  college_app_type VARCHAR(100),
  PRIMARY KEY(student_id, college_id)
);

create table address (
  address_id UUID,
  address_type_id int,
  street1 VARCHAR(100),
  street2 VARCHAR(100),
  state VARCHAR(100),
  country VARCHAR(100),
  zipcode VARCHAR(15),
  PRIMARY KEY(address_id, address_type_id)
);