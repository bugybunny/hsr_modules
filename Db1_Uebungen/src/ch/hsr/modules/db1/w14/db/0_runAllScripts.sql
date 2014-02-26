\echo \conninfo
\echo
\echo -n 'client encoding: '\encoding
\echo

SET client_min_messages = ERROR;
\set user bankuser 
\set password '\'bankpw\''
\set database bank
\set promptvar ''

\prompt 'DROP USER [':user'] and DROP DATABASE [':database'] if existing (\\q or Ctrl-C to abort)?' promptvar
:promptvar

DROP DATABASE if exists :database;
DROP USER if exists :user;

\echo -------------------------------------
\echo passwort for user :user = :password
\echo -------------------------------------
\echo

\prompt 'CREATE USER [':user'] and DATABASE [':database'] (\\q or Ctrl-C to abort)?' promptvar
:promptvar

DROP DATABASE if exists :database;
DROP USER if exists :user;

CREATE USER :user WITH PASSWORD :password;
CREATE DATABASE :database WITH OWNER :user ENCODING 'UTF8';
\c :database :user

-- specify encoding to match your files encoding, usually UTF8
-- valid values are: 'UTF8', 'LATIN1', 'WIN1252'
\encoding 'UTF8'

-- create extensions
\ir 1_extensions.sql

-- create schema
\ir 2_schema.sql

-- insert data in slo-mo or use COPY for speedup
\ir 3_inserts.sql

-- create primary keys, constraints, indexes
\ir 4_constraints.sql
