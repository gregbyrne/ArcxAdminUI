CREATE USER arcxadmintest WITH
LOGIN
SUPERUSER
CREATEDB
CREATEROLE
INHERIT
NOREPLICATION
CONNECTION LIMIT -1
PASSWORD '!testertestingthings7!';

ALTER DATABASE arcx OWNER TO arcxadmintest;

CREATE SCHEMA data AUTHORIZATION arcxadmintest;

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-16 10:04:57

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
-- TOC entry 208 (class 1259 OID 73879)
-- Name: regions; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE data.regions (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE data.regions OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 73908)
-- Name: regions_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

ALTER TABLE data.regions ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME data.regions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2727 (class 2606 OID 73886)
-- Name: regions regions_pkey; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.regions
    ADD CONSTRAINT regions_pkey PRIMARY KEY (id);


-- Completed on 2020-10-16 10:04:58

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-16 10:03:50

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
-- TOC entry 202 (class 1259 OID 73842)
-- Name: area_of_interest; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE data.area_of_interest (
    id integer NOT NULL,
    name character varying(200) NOT NULL
);


ALTER TABLE data.area_of_interest OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 73847)
-- Name: area_of_interest_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

ALTER TABLE data.area_of_interest ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME data.area_of_interest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2727 (class 2606 OID 73846)
-- Name: area_of_interest area_of_interest_pkey; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.area_of_interest
    ADD CONSTRAINT area_of_interest_pkey PRIMARY KEY (id);


-- Completed on 2020-10-16 10:03:50

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-16 10:03:16

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
-- TOC entry 209 (class 1259 OID 73887)
-- Name: additional_information; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE data.additional_information (
    id integer NOT NULL,
    parentid integer NOT NULL,
    description character varying
);


ALTER TABLE data.additional_information OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 73910)
-- Name: additional_information_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

ALTER TABLE data.additional_information ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME data.additional_information_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2727 (class 2606 OID 73894)
-- Name: additional_information additional_information_pkey; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.additional_information
    ADD CONSTRAINT additional_information_pkey PRIMARY KEY (id);


--
-- TOC entry 2728 (class 2606 OID 73895)
-- Name: additional_information regions_fk_additional_information; Type: FK CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.additional_information
    ADD CONSTRAINT regions_fk_additional_information FOREIGN KEY (parentid) REFERENCES data.regions(id);


-- Completed on 2020-10-16 10:03:17

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-16 10:04:20

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
-- TOC entry 204 (class 1259 OID 73849)
-- Name: area_of_interest_items; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE data.area_of_interest_items (
    id integer NOT NULL,
    parentid integer NOT NULL,
    name character varying NOT NULL,
    value character varying
);


ALTER TABLE data.area_of_interest_items OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 73875)
-- Name: area_of_interest_items_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

ALTER TABLE data.area_of_interest_items ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME data.area_of_interest_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2727 (class 2606 OID 73856)
-- Name: area_of_interest_items area_of_interest_items_pkey; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.area_of_interest_items
    ADD CONSTRAINT area_of_interest_items_pkey PRIMARY KEY (id);


--
-- TOC entry 2728 (class 2606 OID 73857)
-- Name: area_of_interest_items parent_id_fk_constraint; Type: FK CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.area_of_interest_items
    ADD CONSTRAINT parent_id_fk_constraint FOREIGN KEY (parentid) REFERENCES data.area_of_interest(id);


-- Completed on 2020-10-16 10:04:20

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-16 10:04:43

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
-- TOC entry 205 (class 1259 OID 73862)
-- Name: area_of_interest_sub_items; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE data.area_of_interest_sub_items (
    id integer NOT NULL,
    parentid integer NOT NULL,
    name character varying NOT NULL,
    value character varying
);


ALTER TABLE data.area_of_interest_sub_items OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 73877)
-- Name: area_of_interest_sub_items_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

ALTER TABLE data.area_of_interest_sub_items ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME data.area_of_interest_sub_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2727 (class 2606 OID 73869)
-- Name: area_of_interest_sub_items area_of_interest_items_pk; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.area_of_interest_sub_items
    ADD CONSTRAINT area_of_interest_items_pk PRIMARY KEY (id);


--
-- TOC entry 2728 (class 2606 OID 73870)
-- Name: area_of_interest_sub_items area_of_interest_items_fk_sub; Type: FK CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.area_of_interest_sub_items
    ADD CONSTRAINT area_of_interest_items_fk_sub FOREIGN KEY (parentid) REFERENCES data.area_of_interest_items(id);


-- Completed on 2020-10-16 10:04:43

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-16 10:05:17

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
-- TOC entry 210 (class 1259 OID 73900)
-- Name: steps_to_help_prepare; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE data.steps_to_help_prepare (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE data.steps_to_help_prepare OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 73912)
-- Name: steps_to_help_prepare_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

ALTER TABLE data.steps_to_help_prepare ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME data.steps_to_help_prepare_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2727 (class 2606 OID 73907)
-- Name: steps_to_help_prepare steps_to_help_prepare_pkey; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.steps_to_help_prepare
    ADD CONSTRAINT steps_to_help_prepare_pkey PRIMARY KEY (id);


-- Completed on 2020-10-16 10:05:17

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-16 10:05:39

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
-- TOC entry 214 (class 1259 OID 73914)
-- Name: steps_to_help_prepare_items; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE data.steps_to_help_prepare_items (
    id integer NOT NULL,
    parentid integer NOT NULL,
    name character varying NOT NULL,
    content character varying
);


ALTER TABLE data.steps_to_help_prepare_items OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 73927)
-- Name: steps_to_help_prepare_items_id_seq; Type: SEQUENCE; Schema: data; Owner: postgres
--

ALTER TABLE data.steps_to_help_prepare_items ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME data.steps_to_help_prepare_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2727 (class 2606 OID 73921)
-- Name: steps_to_help_prepare_items steps_to_help_prepare_items_pkey; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.steps_to_help_prepare_items
    ADD CONSTRAINT steps_to_help_prepare_items_pkey PRIMARY KEY (id);


--
-- TOC entry 2728 (class 2606 OID 73922)
-- Name: steps_to_help_prepare_items steps_to_help_prepare_fk_items; Type: FK CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.steps_to_help_prepare_items
    ADD CONSTRAINT steps_to_help_prepare_fk_items FOREIGN KEY (parentid) REFERENCES data.steps_to_help_prepare(id);


-- Completed on 2020-10-16 10:05:39

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-16 10:06:09

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
-- TOC entry 216 (class 1259 OID 73929)
-- Name: steps_to_help_prepare_sub_items; Type: TABLE; Schema: data; Owner: postgres
--

CREATE TABLE data.steps_to_help_prepare_sub_items (
    id integer NOT NULL,
    parentid integer NOT NULL,
    content character varying NOT NULL
);


ALTER TABLE data.steps_to_help_prepare_sub_items OWNER TO postgres;

--
-- TOC entry 2727 (class 2606 OID 73936)
-- Name: steps_to_help_prepare_sub_items steps_to_help_prepare_sub_items_pkey; Type: CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.steps_to_help_prepare_sub_items
    ADD CONSTRAINT steps_to_help_prepare_sub_items_pkey PRIMARY KEY (id);


--
-- TOC entry 2728 (class 2606 OID 73937)
-- Name: steps_to_help_prepare_sub_items steps_to_help_prepare_fk_sub_items; Type: FK CONSTRAINT; Schema: data; Owner: postgres
--

ALTER TABLE ONLY data.steps_to_help_prepare_sub_items
    ADD CONSTRAINT steps_to_help_prepare_fk_sub_items FOREIGN KEY (parentid) REFERENCES data.steps_to_help_prepare_items(id);


-- Completed on 2020-10-16 10:06:09

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-21 16:24:03

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
-- TOC entry 234 (class 1259 OID 82246)
-- Name: user; Type: TABLE; Schema: data; Owner: arcxadmintest
--

CREATE TABLE data."user" (
    id integer NOT NULL,
    email character varying(250) NOT NULL,
    enabled boolean NOT NULL,
    password character varying(250) NOT NULL,
    username character varying(250) NOT NULL
);


ALTER TABLE data."user" OWNER TO arcxadmintest;

--
-- TOC entry 233 (class 1259 OID 82244)
-- Name: user_user_id_seq; Type: SEQUENCE; Schema: data; Owner: arcxadmintest
--

ALTER TABLE data."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME data.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2788 (class 2606 OID 82253)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: data; Owner: arcxadmintest
--

ALTER TABLE ONLY data."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


-- Completed on 2020-10-21 16:24:04

--
-- PostgreSQL database dump complete
--

-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-21 16:25:00

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
-- TOC entry 236 (class 1259 OID 82256)
-- Name: authority; Type: TABLE; Schema: data; Owner: arcxadmintest
--

CREATE TABLE data.authority (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE data.authority OWNER TO arcxadmintest;

--
-- TOC entry 235 (class 1259 OID 82254)
-- Name: authority_authority_id_seq; Type: SEQUENCE; Schema: data; Owner: arcxadmintest
--

ALTER TABLE data.authority ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME data.authority_authority_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2788 (class 2606 OID 82260)
-- Name: authority authority_pkey; Type: CONSTRAINT; Schema: data; Owner: arcxadmintest
--

ALTER TABLE ONLY data.authority
    ADD CONSTRAINT authority_pkey PRIMARY KEY (id);


-- Completed on 2020-10-21 16:25:00

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-10-21 16:25:41

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
-- TOC entry 237 (class 1259 OID 82264)
-- Name: user_authority; Type: TABLE; Schema: data; Owner: arcxadmintest
--

CREATE TABLE data.user_authority (
    user_id integer NOT NULL,
    authority_id integer NOT NULL
);


ALTER TABLE data.user_authority OWNER TO arcxadmintest;

-- Completed on 2020-10-21 16:25:41

--
-- PostgreSQL database dump complete
--




