--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.0
-- Dumped by pg_dump version 9.5.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bands; Type: TABLE; Schema: public; Owner: jturello
--

CREATE TABLE bands (
    id integer NOT NULL,
    name character varying,
    genre character varying
);


ALTER TABLE bands OWNER TO jturello;

--
-- Name: bands_id_seq; Type: SEQUENCE; Schema: public; Owner: jturello
--

CREATE SEQUENCE bands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_id_seq OWNER TO jturello;

--
-- Name: bands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jturello
--

ALTER SEQUENCE bands_id_seq OWNED BY bands.id;


--
-- Name: concerts; Type: TABLE; Schema: public; Owner: jturello
--

CREATE TABLE concerts (
    id integer NOT NULL,
    band_id integer,
    venue_id integer,
    date date,
    name character varying
);


ALTER TABLE concerts OWNER TO jturello;

--
-- Name: concerts_id_seq; Type: SEQUENCE; Schema: public; Owner: jturello
--

CREATE SEQUENCE concerts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE concerts_id_seq OWNER TO jturello;

--
-- Name: concerts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jturello
--

ALTER SEQUENCE concerts_id_seq OWNED BY concerts.id;


--
-- Name: venues; Type: TABLE; Schema: public; Owner: jturello
--

CREATE TABLE venues (
    id integer NOT NULL,
    name character varying,
    location character varying
);


ALTER TABLE venues OWNER TO jturello;

--
-- Name: venues_id_seq; Type: SEQUENCE; Schema: public; Owner: jturello
--

CREATE SEQUENCE venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE venues_id_seq OWNER TO jturello;

--
-- Name: venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jturello
--

ALTER SEQUENCE venues_id_seq OWNED BY venues.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY bands ALTER COLUMN id SET DEFAULT nextval('bands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY concerts ALTER COLUMN id SET DEFAULT nextval('concerts_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY venues ALTER COLUMN id SET DEFAULT nextval('venues_id_seq'::regclass);


--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: jturello
--

COPY bands (id, name, genre) FROM stdin;
\.


--
-- Name: bands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jturello
--

SELECT pg_catalog.setval('bands_id_seq', 1, false);


--
-- Data for Name: concerts; Type: TABLE DATA; Schema: public; Owner: jturello
--

COPY concerts (id, band_id, venue_id, date, name) FROM stdin;
\.


--
-- Name: concerts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jturello
--

SELECT pg_catalog.setval('concerts_id_seq', 1, false);


--
-- Data for Name: venues; Type: TABLE DATA; Schema: public; Owner: jturello
--

COPY venues (id, name, location) FROM stdin;
\.


--
-- Name: venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jturello
--

SELECT pg_catalog.setval('venues_id_seq', 1, false);


--
-- Name: bands_pkey; Type: CONSTRAINT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY bands
    ADD CONSTRAINT bands_pkey PRIMARY KEY (id);


--
-- Name: concerts_pkey; Type: CONSTRAINT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY concerts
    ADD CONSTRAINT concerts_pkey PRIMARY KEY (id);


--
-- Name: venues_pkey; Type: CONSTRAINT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY venues
    ADD CONSTRAINT venues_pkey PRIMARY KEY (id);


--
-- Name: concerts_band_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY concerts
    ADD CONSTRAINT concerts_band_id_fkey FOREIGN KEY (band_id) REFERENCES bands(id);


--
-- Name: concerts_venue_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY concerts
    ADD CONSTRAINT concerts_venue_id_fkey FOREIGN KEY (venue_id) REFERENCES venues(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: jturello
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM jturello;
GRANT ALL ON SCHEMA public TO jturello;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

