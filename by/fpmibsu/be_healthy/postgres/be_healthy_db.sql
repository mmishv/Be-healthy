--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2023-05-14 14:58:53

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
-- TOC entry 218 (class 1259 OID 27251)
-- Name: article; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.article (
    id integer NOT NULL,
    title character varying(100) NOT NULL,
    publ_date timestamp without time zone DEFAULT CURRENT_DATE NOT NULL,
    full_text text NOT NULL,
    author_id integer NOT NULL,
    moderated boolean DEFAULT false NOT NULL
);


ALTER TABLE public.article OWNER TO postgres;

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
-- TOC entry 3443 (class 0 OID 0)
-- Dependencies: 215
-- Name: article_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.article_category_id_seq OWNED BY public.article_category.id;


--
-- TOC entry 217 (class 1259 OID 27250)
-- Name: article_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.article_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.article_id_seq OWNER TO postgres;

--
-- TOC entry 3444 (class 0 OID 0)
-- Dependencies: 217
-- Name: article_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.article_id_seq OWNED BY public.article.id;


--
-- TOC entry 228 (class 1259 OID 27521)
-- Name: ingredient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingredient (
    id integer NOT NULL,
    recipe_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT ingredient_quantity_check CHECK (((quantity)::numeric >= (0)::numeric))
);


ALTER TABLE public.ingredient OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 27520)
-- Name: ingredient_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ingredient_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ingredient_id_seq OWNER TO postgres;

--
-- TOC entry 3445 (class 0 OID 0)
-- Dependencies: 227
-- Name: ingredient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ingredient_id_seq OWNED BY public.ingredient.id;


--
-- TOC entry 224 (class 1259 OID 27473)
-- Name: meal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meal (
    id integer NOT NULL,
    user_id integer NOT NULL,
    name character varying(100) NOT NULL,
    "time" time without time zone DEFAULT CURRENT_TIME NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL
);


ALTER TABLE public.meal OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 27472)
-- Name: meal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.meal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.meal_id_seq OWNER TO postgres;

--
-- TOC entry 3446 (class 0 OID 0)
-- Dependencies: 223
-- Name: meal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.meal_id_seq OWNED BY public.meal.id;


--
-- TOC entry 226 (class 1259 OID 27493)
-- Name: meal_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meal_product (
    id integer NOT NULL,
    meal_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT meal_product_quantity_check CHECK ((quantity >= 0))
);


ALTER TABLE public.meal_product OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 27492)
-- Name: meal_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.meal_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.meal_product_id_seq OWNER TO postgres;

--
-- TOC entry 3447 (class 0 OID 0)
-- Dependencies: 225
-- Name: meal_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.meal_product_id_seq OWNED BY public.meal_product.id;


--
-- TOC entry 219 (class 1259 OID 27280)
-- Name: mm_category_article; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mm_category_article (
    article_id integer NOT NULL,
    category_id integer NOT NULL
);


ALTER TABLE public.mm_category_article OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 27310)
-- Name: mm_category_recipe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mm_category_recipe (
    recipe_id integer NOT NULL,
    category_id integer NOT NULL
);


ALTER TABLE public.mm_category_recipe OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 27186)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    carbohydrates numeric NOT NULL,
    fats numeric NOT NULL,
    proteins numeric NOT NULL,
    calories numeric NOT NULL,
    unit character varying(50) DEFAULT 'г.'::character varying,
    CONSTRAINT product_calories_check CHECK ((calories >= (0)::numeric)),
    CONSTRAINT product_carbohydrates_check CHECK ((carbohydrates >= (0)::numeric)),
    CONSTRAINT product_fats_check CHECK ((fats >= (0)::numeric)),
    CONSTRAINT product_proteins_check CHECK ((proteins >= (0)::numeric))
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 27185)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 3448 (class 0 OID 0)
-- Dependencies: 211
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 210 (class 1259 OID 27133)
-- Name: profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile (
    id integer NOT NULL,
    name character varying(50),
    email character varying(255),
    login character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    age integer,
    height integer,
    activity_coef numeric DEFAULT 1 NOT NULL,
    weight numeric,
    cal_norm numeric DEFAULT 2000 NOT NULL,
    carb_norm numeric DEFAULT 250 NOT NULL,
    prot_norm numeric DEFAULT 90 NOT NULL,
    fats_norm numeric DEFAULT 60 NOT NULL,
    avatar bytea,
    sex character varying DEFAULT 'женский'::character varying NOT NULL,
    goal numeric DEFAULT 1 NOT NULL,
    role_id integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.profile OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 27132)
-- Name: profile_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profile_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profile_id_seq OWNER TO postgres;

--
-- TOC entry 3449 (class 0 OID 0)
-- Dependencies: 209
-- Name: profile_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profile_id_seq OWNED BY public.profile.id;


--
-- TOC entry 221 (class 1259 OID 27296)
-- Name: recipe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recipe (
    id integer NOT NULL,
    title character varying(100) NOT NULL,
    publ_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    cooking_time integer NOT NULL,
    description text NOT NULL,
    author_id integer NOT NULL,
    photo bytea,
    moderated boolean DEFAULT false NOT NULL,
    CONSTRAINT recipe_cooking_time_check CHECK ((cooking_time >= 0))
);


ALTER TABLE public.recipe OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 27202)
-- Name: recipe_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recipe_category (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.recipe_category OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 27201)
-- Name: recipe_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recipe_category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recipe_category_id_seq OWNER TO postgres;

--
-- TOC entry 3450 (class 0 OID 0)
-- Dependencies: 213
-- Name: recipe_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recipe_category_id_seq OWNED BY public.recipe_category.id;


--
-- TOC entry 220 (class 1259 OID 27295)
-- Name: recipe_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recipe_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recipe_id_seq OWNER TO postgres;

--
-- TOC entry 3451 (class 0 OID 0)
-- Dependencies: 220
-- Name: recipe_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recipe_id_seq OWNED BY public.recipe.id;


--
-- TOC entry 230 (class 1259 OID 28415)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 28414)
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;

--
-- TOC entry 3452 (class 0 OID 0)
-- Dependencies: 229
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- TOC entry 3234 (class 2604 OID 27254)
-- Name: article id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article ALTER COLUMN id SET DEFAULT nextval('public.article_id_seq'::regclass);


--
-- TOC entry 3233 (class 2604 OID 27214)
-- Name: article_category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article_category ALTER COLUMN id SET DEFAULT nextval('public.article_category_id_seq'::regclass);


--
-- TOC entry 3246 (class 2604 OID 27524)
-- Name: ingredient id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient ALTER COLUMN id SET DEFAULT nextval('public.ingredient_id_seq'::regclass);


--
-- TOC entry 3241 (class 2604 OID 27476)
-- Name: meal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal ALTER COLUMN id SET DEFAULT nextval('public.meal_id_seq'::regclass);


--
-- TOC entry 3244 (class 2604 OID 27496)
-- Name: meal_product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal_product ALTER COLUMN id SET DEFAULT nextval('public.meal_product_id_seq'::regclass);


--
-- TOC entry 3226 (class 2604 OID 27189)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 3217 (class 2604 OID 27136)
-- Name: profile id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile ALTER COLUMN id SET DEFAULT nextval('public.profile_id_seq'::regclass);


--
-- TOC entry 3237 (class 2604 OID 27299)
-- Name: recipe id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipe ALTER COLUMN id SET DEFAULT nextval('public.recipe_id_seq'::regclass);


--
-- TOC entry 3232 (class 2604 OID 27205)
-- Name: recipe_category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipe_category ALTER COLUMN id SET DEFAULT nextval('public.recipe_category_id_seq'::regclass);


--
-- TOC entry 3248 (class 2604 OID 28418)
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- TOC entry 3264 (class 2606 OID 27218)
-- Name: article_category article_category_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article_category
    ADD CONSTRAINT article_category_name_key UNIQUE (name);


--
-- TOC entry 3266 (class 2606 OID 27216)
-- Name: article_category article_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article_category
    ADD CONSTRAINT article_category_pkey PRIMARY KEY (id);


--
-- TOC entry 3268 (class 2606 OID 27258)
-- Name: article article_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_pkey PRIMARY KEY (id);


--
-- TOC entry 3280 (class 2606 OID 27529)
-- Name: ingredient ingredient_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_id_key UNIQUE (id);


--
-- TOC entry 3282 (class 2606 OID 27860)
-- Name: ingredient ingredient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_pkey PRIMARY KEY (id);


--
-- TOC entry 3276 (class 2606 OID 27481)
-- Name: meal meal_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal
    ADD CONSTRAINT meal_id_key UNIQUE (id);


--
-- TOC entry 3278 (class 2606 OID 27865)
-- Name: meal_product meal_product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal_product
    ADD CONSTRAINT meal_product_pkey PRIMARY KEY (id);


--
-- TOC entry 3270 (class 2606 OID 27284)
-- Name: mm_category_article mm_category_article_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mm_category_article
    ADD CONSTRAINT mm_category_article_pkey PRIMARY KEY (category_id, article_id);


--
-- TOC entry 3274 (class 2606 OID 27314)
-- Name: mm_category_recipe mm_category_recipe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mm_category_recipe
    ADD CONSTRAINT mm_category_recipe_pkey PRIMARY KEY (category_id, recipe_id);


--
-- TOC entry 3256 (class 2606 OID 27200)
-- Name: product product_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_name_key UNIQUE (name);


--
-- TOC entry 3258 (class 2606 OID 27198)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3250 (class 2606 OID 27142)
-- Name: profile profile_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_email_key UNIQUE (email);


--
-- TOC entry 3252 (class 2606 OID 27144)
-- Name: profile profile_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_login_key UNIQUE (login);


--
-- TOC entry 3254 (class 2606 OID 27140)
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);


--
-- TOC entry 3260 (class 2606 OID 27209)
-- Name: recipe_category recipe_category_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipe_category
    ADD CONSTRAINT recipe_category_name_key UNIQUE (name);


--
-- TOC entry 3262 (class 2606 OID 27207)
-- Name: recipe_category recipe_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipe_category
    ADD CONSTRAINT recipe_category_pkey PRIMARY KEY (id);


--
-- TOC entry 3272 (class 2606 OID 27304)
-- Name: recipe recipe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipe
    ADD CONSTRAINT recipe_pkey PRIMARY KEY (id);


--
-- TOC entry 3284 (class 2606 OID 28422)
-- Name: role role_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_name_key UNIQUE (name);


--
-- TOC entry 3286 (class 2606 OID 28420)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3288 (class 2606 OID 27259)
-- Name: article article_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.article
    ADD CONSTRAINT article_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.profile(id);


--
-- TOC entry 3297 (class 2606 OID 27535)
-- Name: ingredient ingredient_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3298 (class 2606 OID 27930)
-- Name: ingredient ingredient_recipe_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_recipe_id_fkey FOREIGN KEY (recipe_id) REFERENCES public.recipe(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3295 (class 2606 OID 27972)
-- Name: meal_product meal_product_meal_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal_product
    ADD CONSTRAINT meal_product_meal_id_fkey FOREIGN KEY (meal_id) REFERENCES public.meal(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3296 (class 2606 OID 27977)
-- Name: meal_product meal_product_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal_product
    ADD CONSTRAINT meal_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3294 (class 2606 OID 27487)
-- Name: meal meal_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal
    ADD CONSTRAINT meal_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.profile(id);


--
-- TOC entry 3289 (class 2606 OID 28387)
-- Name: mm_category_article mm_category_article_article_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mm_category_article
    ADD CONSTRAINT mm_category_article_article_id_fkey FOREIGN KEY (article_id) REFERENCES public.article(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3290 (class 2606 OID 28392)
-- Name: mm_category_article mm_category_article_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mm_category_article
    ADD CONSTRAINT mm_category_article_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.article_category(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3292 (class 2606 OID 28397)
-- Name: mm_category_recipe mm_category_recipe_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mm_category_recipe
    ADD CONSTRAINT mm_category_recipe_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.recipe_category(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3293 (class 2606 OID 28402)
-- Name: mm_category_recipe mm_category_recipe_recipe_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mm_category_recipe
    ADD CONSTRAINT mm_category_recipe_recipe_id_fkey FOREIGN KEY (recipe_id) REFERENCES public.recipe(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3287 (class 2606 OID 28425)
-- Name: profile profile_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id) NOT VALID;


--
-- TOC entry 3291 (class 2606 OID 27305)
-- Name: recipe recipe_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipe
    ADD CONSTRAINT recipe_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.profile(id);


-- Completed on 2023-05-14 14:58:53

--
-- PostgreSQL database dump complete
--

