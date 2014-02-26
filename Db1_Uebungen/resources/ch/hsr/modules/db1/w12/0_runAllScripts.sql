\echo \conninfo
\echo
\echo -n 'client encoding: '\encoding
\echo

SET client_min_messages = ERROR;
\set user jdbctut 
\set password '\'jdbctut\''
\set database jdbctut
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

