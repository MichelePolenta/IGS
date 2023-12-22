--
-- PostgreSQL database cluster dump
--

-- Started on 2023-12-22 13:11:45 CET

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:poU5hBfG8Gk2V7aMfF4YMg==$YuIUKinXLMUx15VS0JnNsC30IlZ1ZS0WZ7C0UeUw4jY=:Xy11zsnoMwGCa2eHgU7f9DuJJgqJGz8qjBV9j5nakbk=';
CREATE ROLE postgresgiaccaglia;
ALTER ROLE postgresgiaccaglia WITH NOSUPERUSER INHERIT NOCREATEROLE NOCREATEDB LOGIN NOREPLICATION NOBYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:9RJ5RlJbdZyhuIaD83tFGA==$bhlTF5ih9m73/qN6B1nVckm3bV6zaiRY6ela1hcnpRM=:G3uiP1/XOaI8OhUUxIa7IHEqgxhshiCFS5TNeoXHmOY=';

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 15.5 (Ubuntu 15.5-0ubuntu0.23.10.1)
-- Dumped by pg_dump version 16.1 (Ubuntu 16.1-1.pgdg23.10+1)

-- Started on 2023-12-22 13:11:46 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2023-12-22 13:11:46 CET

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 15.5 (Ubuntu 15.5-0ubuntu0.23.10.1)
-- Dumped by pg_dump version 16.1 (Ubuntu 16.1-1.pgdg23.10+1)

-- Started on 2023-12-22 13:11:46 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16401)
-- Name: Contributor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Contributor" (
    "Id_Persona" text NOT NULL
);


ALTER TABLE public."Contributor" OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16394)
-- Name: Persona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Persona" (
    "Nome" text NOT NULL,
    "Cognome " text NOT NULL,
    "Codice_Fiscale" text NOT NULL,
    "Eta" smallint NOT NULL,
    "Citta" text NOT NULL,
    "Residenza" text NOT NULL,
    "CAP" text NOT NULL
);


ALTER TABLE public."Persona" OWNER TO postgres;

--
-- TOC entry 3383 (class 0 OID 16401)
-- Dependencies: 215
-- Data for Name: Contributor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Contributor" ("Id_Persona") FROM stdin;
\.


--
-- TOC entry 3382 (class 0 OID 16394)
-- Dependencies: 214
-- Data for Name: Persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Persona" ("Nome", "Cognome ", "Codice_Fiscale", "Eta", "Citta", "Residenza", "CAP") FROM stdin;
\.


--
-- TOC entry 3239 (class 2606 OID 16400)
-- Name: Persona Persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Persona"
    ADD CONSTRAINT "Persona_pkey" PRIMARY KEY ("Codice_Fiscale");


-- Completed on 2023-12-22 13:11:46 CET

--
-- PostgreSQL database dump complete
--

-- Completed on 2023-12-22 13:11:46 CET

--
-- PostgreSQL database cluster dump complete
--

