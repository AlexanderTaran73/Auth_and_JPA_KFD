--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

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
-- Name: free_ans_survey; Type: TABLE; Schema: public; Owner: postgres
--
CREATE DATABASE kotlin_auth;
\c kotlin_auth

CREATE TABLE public.free_ans_survey (
    id integer NOT NULL,
    question character varying(255)
);


ALTER TABLE public.free_ans_survey OWNER TO postgres;

--
-- Name: free_ans_survey_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.free_ans_survey_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.free_ans_survey_seq OWNER TO postgres;

--
-- Name: free_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.free_answer (
    id integer NOT NULL,
    users_id integer,
    answer character varying(255),
    free_ans_survey_id integer
);


ALTER TABLE public.free_answer OWNER TO postgres;

--
-- Name: free_answer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.free_answer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.free_answer_seq OWNER TO postgres;

--
-- Name: multy_ans_survey; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.multy_ans_survey (
    id integer NOT NULL,
    question character varying(255)
);


ALTER TABLE public.multy_ans_survey OWNER TO postgres;

--
-- Name: multy_ans_survey_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.multy_ans_survey_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.multy_ans_survey_seq OWNER TO postgres;

--
-- Name: multy_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.multy_answer (
    id integer NOT NULL,
    users_id integer,
    multy_ans_survey_id integer
);


ALTER TABLE public.multy_answer OWNER TO postgres;

--
-- Name: multy_answer_possible_answers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.multy_answer_possible_answers (
    multy_answer_id integer NOT NULL,
    possible_answers_id integer NOT NULL
);


ALTER TABLE public.multy_answer_possible_answers OWNER TO postgres;

--
-- Name: multy_answer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.multy_answer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.multy_answer_seq OWNER TO postgres;

--
-- Name: possible_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.possible_answer (
    id integer NOT NULL,
    answer character varying(255),
    single_ans_survey_id integer,
    multy_ans_survey_id integer
);


ALTER TABLE public.possible_answer OWNER TO postgres;

--
-- Name: possible_answer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.possible_answer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.possible_answer_seq OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_seq OWNER TO postgres;

--
-- Name: single_ans_survey; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.single_ans_survey (
    id integer NOT NULL,
    question character varying(255)
);


ALTER TABLE public.single_ans_survey OWNER TO postgres;

--
-- Name: single_ans_survey_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.single_ans_survey_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.single_ans_survey_seq OWNER TO postgres;

--
-- Name: single_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.single_answer (
    id integer NOT NULL,
    users_id integer,
    possible_answer_id integer NOT NULL,
    single_ans_survey_id integer
);


ALTER TABLE public.single_answer OWNER TO postgres;

--
-- Name: single_answer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.single_answer_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.single_answer_seq OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(255),
    password character varying(255),
    roleid_id integer NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_seq OWNER TO postgres;

--
-- Data for Name: free_ans_survey; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.free_ans_survey (id, question) FROM stdin;
52	FreeAnsSur 1
53	FreeAnsSur 2
\.


--
-- Data for Name: free_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.free_answer (id, users_id, answer, free_ans_survey_id) FROM stdin;
52	2	FreeAns to id 53	53
\.


--
-- Data for Name: multy_ans_survey; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.multy_ans_survey (id, question) FROM stdin;
52	MultyAnsSur 1
53	MultyAnsSur 2
\.


--
-- Data for Name: multy_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.multy_answer (id, users_id, multy_ans_survey_id) FROM stdin;
102	2	53
\.


--
-- Data for Name: multy_answer_possible_answers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.multy_answer_possible_answers (multy_answer_id, possible_answers_id) FROM stdin;
102	109
102	110
\.


--
-- Data for Name: possible_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.possible_answer (id, answer, single_ans_survey_id, multy_ans_survey_id) FROM stdin;
102	Ans 1 to SingleAnsSur 1	52	\N
103	Ans 2 to SingleAnsSur 1	52	\N
104	Ans 1 to SingleAnsSur 2	53	\N
105	Ans 2 to SingleAnsSur 2	53	\N
106	Ans 3 to SingleAnsSur 2	53	\N
107	Ans 1 to MultyAnsSur 1	\N	52
108	Ans 2 to MultyAnsSur 1	\N	52
109	Ans 1 to MultyAnsSur 2	\N	53
110	Ans 2 to MultyAnsSur 2	\N	53
111	Ans 3 to MultyAnsSur 2	\N	53
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, name) FROM stdin;
0	ROLE_USER
1	ROLE_ADMIN
\.


--
-- Data for Name: single_ans_survey; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.single_ans_survey (id, question) FROM stdin;
52	SingleAnsSur 1
53	SingleAnsSur 2
\.


--
-- Data for Name: single_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.single_answer (id, users_id, possible_answer_id, single_ans_survey_id) FROM stdin;
202	2	104	53
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password, roleid_id) FROM stdin;
1	user@gmail.com	$2a$10$J4oE8e.e2ufVIM0EnT4i5OaPlwZ8Xi59kHrR2PTD/61HHoekUwWDK	0
2	admin@gmail.com	$2a$10$bqiOO8zjN8WUP89Eufmrl.k8WlQKiN6o0RgsI7s1lnBXDULKyjIBC	1
\.


--
-- Name: free_ans_survey_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.free_ans_survey_seq', 101, true);


--
-- Name: free_answer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.free_answer_seq', 101, true);


--
-- Name: multy_ans_survey_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.multy_ans_survey_seq', 101, true);


--
-- Name: multy_answer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.multy_answer_seq', 151, true);


--
-- Name: possible_answer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.possible_answer_seq', 151, true);


--
-- Name: role_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_seq', 1, false);


--
-- Name: single_ans_survey_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.single_ans_survey_seq', 101, true);


--
-- Name: single_answer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.single_answer_seq', 251, true);


--
-- Name: users_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_seq', 51, true);


--
-- Name: free_ans_survey free_ans_survey_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.free_ans_survey
    ADD CONSTRAINT free_ans_survey_pkey PRIMARY KEY (id);


--
-- Name: free_answer free_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.free_answer
    ADD CONSTRAINT free_answer_pkey PRIMARY KEY (id);


--
-- Name: multy_ans_survey multy_ans_survey_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multy_ans_survey
    ADD CONSTRAINT multy_ans_survey_pkey PRIMARY KEY (id);


--
-- Name: multy_answer multy_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multy_answer
    ADD CONSTRAINT multy_answer_pkey PRIMARY KEY (id);


--
-- Name: possible_answer possible_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.possible_answer
    ADD CONSTRAINT possible_answer_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: single_ans_survey single_ans_survey_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.single_ans_survey
    ADD CONSTRAINT single_ans_survey_pkey PRIMARY KEY (id);


--
-- Name: single_answer single_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.single_answer
    ADD CONSTRAINT single_answer_pkey PRIMARY KEY (id);


--
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: single_answer fk3p4tq0fc6hkxa0ed67jau9n97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.single_answer
    ADD CONSTRAINT fk3p4tq0fc6hkxa0ed67jau9n97 FOREIGN KEY (possible_answer_id) REFERENCES public.possible_answer(id);


--
-- Name: multy_answer fk8q5vwbeg3s8fpnk64h1vlga5j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multy_answer
    ADD CONSTRAINT fk8q5vwbeg3s8fpnk64h1vlga5j FOREIGN KEY (multy_ans_survey_id) REFERENCES public.multy_ans_survey(id);


--
-- Name: multy_answer_possible_answers fkbfbnnoa9bki2taslw4alkfry5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multy_answer_possible_answers
    ADD CONSTRAINT fkbfbnnoa9bki2taslw4alkfry5 FOREIGN KEY (possible_answers_id) REFERENCES public.possible_answer(id);


--
-- Name: free_answer fkbo5tounm6m2jmn8tisybemrm1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.free_answer
    ADD CONSTRAINT fkbo5tounm6m2jmn8tisybemrm1 FOREIGN KEY (free_ans_survey_id) REFERENCES public.free_ans_survey(id);


--
-- Name: single_answer fkg3rw5gi9ikcjgkp1eu48mbhpb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.single_answer
    ADD CONSTRAINT fkg3rw5gi9ikcjgkp1eu48mbhpb FOREIGN KEY (single_ans_survey_id) REFERENCES public.single_ans_survey(id);


--
-- Name: possible_answer fkgokq48qovflvupyu3l2b2s7oe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.possible_answer
    ADD CONSTRAINT fkgokq48qovflvupyu3l2b2s7oe FOREIGN KEY (multy_ans_survey_id) REFERENCES public.multy_ans_survey(id);


--
-- Name: possible_answer fkhur8j4yk2tmghf7bivo1xaoyk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.possible_answer
    ADD CONSTRAINT fkhur8j4yk2tmghf7bivo1xaoyk FOREIGN KEY (single_ans_survey_id) REFERENCES public.single_ans_survey(id);


--
-- Name: users fkkmachhy9qkus1fk8a5wk8ce4s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkkmachhy9qkus1fk8a5wk8ce4s FOREIGN KEY (roleid_id) REFERENCES public.role(id);


--
-- Name: multy_answer_possible_answers fkwy8vb8voeq9hbs3jumo1blf1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multy_answer_possible_answers
    ADD CONSTRAINT fkwy8vb8voeq9hbs3jumo1blf1 FOREIGN KEY (multy_answer_id) REFERENCES public.multy_answer(id);


--
-- PostgreSQL database dump complete
--

