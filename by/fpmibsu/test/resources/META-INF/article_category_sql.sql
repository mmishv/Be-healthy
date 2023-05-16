--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2023-05-15 19:30:37

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
-- TOC entry 216 (class 1259 OID 27211)
-- Name: article_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.article_category (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.article_category OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 27210)
-- Name: article_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.article_category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.article_category_id_seq OWNER TO postgres;

--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 215
-- Name: article_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.article_category_id_seq OWNED BY public.article_category.id;


--
-- TOC entry 3205 (class 2604 OID 27214)
-- Name: article_category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article_category ALTER COLUMN id SET DEFAULT nextval('public.article_category_id_seq'::regclass);


--
-- TOC entry 3350 (class 0 OID 27211)
-- Dependencies: 216
-- Data for Name: article_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.article_category VALUES (1, 'Вегетерианство');
INSERT INTO public.article_category VALUES (3, 'Беременность');
INSERT INTO public.article_category VALUES (4, 'Здоровое питание');
INSERT INTO public.article_category VALUES (5, 'Фитнес');
INSERT INTO public.article_category VALUES (6, 'Йога');
INSERT INTO public.article_category VALUES (7, 'Разгрузочный день');
INSERT INTO public.article_category VALUES (8, 'Диета');
INSERT INTO public.article_category VALUES (9, 'Бег');
INSERT INTO public.article_category VALUES (10, 'Бодибилдинг');
INSERT INTO public.article_category VALUES (11, 'Пилатес');
INSERT INTO public.article_category VALUES (12, 'Веганство');
INSERT INTO public.article_category VALUES (13, 'ЗОЖ для начинающих');
INSERT INTO public.article_category VALUES (14, 'Аэробика');
INSERT INTO public.article_category VALUES (15, 'Метаболизм');
INSERT INTO public.article_category VALUES (16, 'Здоровое похудение');
INSERT INTO public.article_category VALUES (17, 'Спорт');
INSERT INTO public.article_category VALUES (18, 'Иммунитет');
INSERT INTO public.article_category VALUES (19, 'Функциональный тренинг');
INSERT INTO public.article_category VALUES (21, 'Массаж');
INSERT INTO public.article_category VALUES (22, 'Спортивное питание');
INSERT INTO public.article_category VALUES (23, 'Водные виды спорта');
INSERT INTO public.article_category VALUES (24, 'Танцы');
INSERT INTO public.article_category VALUES (25, 'Хатха-йога');
INSERT INTO public.article_category VALUES (26, 'Кроссфит');
INSERT INTO public.article_category VALUES (27, 'Здоровый сон');
INSERT INTO public.article_category VALUES (28, 'Витаминотерапия');
INSERT INTO public.article_category VALUES (29, 'Спорт и психология');
INSERT INTO public.article_category VALUES (30, 'Бег на длительные дистанции');
INSERT INTO public.article_category VALUES (2, 'Семейный отдых');
INSERT INTO public.article_category VALUES (20, 'Антистресc');


--
-- TOC entry 3357 (class 0 OID 0)
-- Dependencies: 215
-- Name: article_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.article_category_id_seq', 33, true);


--
-- TOC entry 3207 (class 2606 OID 27218)
-- Name: article_category article_category_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article_category
    ADD CONSTRAINT article_category_name_key UNIQUE (name);


--
-- TOC entry 3209 (class 2606 OID 27216)
-- Name: article_category article_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article_category
    ADD CONSTRAINT article_category_pkey PRIMARY KEY (id);


-- Completed on 2023-05-15 19:30:37

--
-- PostgreSQL database dump complete
--

