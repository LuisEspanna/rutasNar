-- Script para la creacion de tablas

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-13 21:29:08

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
-- TOC entry 203 (class 1259 OID 16397)
-- Name: Biblioteca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Biblioteca" (
    usuario_id_usuario character varying(20)[] NOT NULL,
    ruta_id_ruta character(4)[] NOT NULL,
    evento_id_evento character(4)[] NOT NULL
);


ALTER TABLE public."Biblioteca" OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16400)
-- Name: Evento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Evento" (
    "Id_evento" character(4)[] NOT NULL,
    nombre_evento character varying(60)[] NOT NULL,
    imagen_evento bytea NOT NULL,
    fecha_evento date NOT NULL,
    tipo_evento character varying(10)[] NOT NULL
);


ALTER TABLE public."Evento" OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16403)
-- Name: Lista; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Lista" (
    nombre_lista character(4)[] NOT NULL,
    "Ruta_id_ruta" character(4)[] NOT NULL,
    "Evento_id_evento" character(4)[] NOT NULL
);


ALTER TABLE public."Lista" OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16406)
-- Name: Ruta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Ruta" (
    "Id_ruta" character(4)[] NOT NULL,
    nombre_ruta character varying(30)[] NOT NULL,
    descrip_ruta character varying(60)[] NOT NULL,
    imagen_ruta bytea NOT NULL,
    origen_ruta character varying(20)[] NOT NULL,
    destino_ruta character varying(20)[] NOT NULL,
    tiempo_ruta integer NOT NULL
);


ALTER TABLE public."Ruta" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16394)
-- Name: Usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Usuario" (
    "Id_usuario" character varying(20)[] NOT NULL,
    "Lista_nombre_lista" character(4)[] NOT NULL,
    "contraseña_usuario" character varying(20)[] NOT NULL,
    nombre_usuario character varying(20)[] NOT NULL
);


ALTER TABLE public."Usuario" OWNER TO postgres;

--
-- TOC entry 2849 (class 0 OID 16397)
-- Dependencies: 203
-- Data for Name: Biblioteca; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Biblioteca" (usuario_id_usuario, ruta_id_ruta, evento_id_evento) FROM stdin;
\.


--
-- TOC entry 2850 (class 0 OID 16400)
-- Dependencies: 204
-- Data for Name: Evento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Evento" ("Id_evento", nombre_evento, imagen_evento, fecha_evento, tipo_evento) FROM stdin;
\.


--
-- TOC entry 2851 (class 0 OID 16403)
-- Dependencies: 205
-- Data for Name: Lista; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Lista" (nombre_lista, "Ruta_id_ruta", "Evento_id_evento") FROM stdin;
\.


--
-- TOC entry 2852 (class 0 OID 16406)
-- Dependencies: 206
-- Data for Name: Ruta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Ruta" ("Id_ruta", nombre_ruta, descrip_ruta, imagen_ruta, origen_ruta, destino_ruta, tiempo_ruta) FROM stdin;
\.


--
-- TOC entry 2848 (class 0 OID 16394)
-- Dependencies: 202
-- Data for Name: Usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Usuario" ("Id_usuario", "Lista_nombre_lista", "contraseña_usuario", nombre_usuario) FROM stdin;
\.


--
-- TOC entry 2709 (class 2606 OID 16418)
-- Name: Biblioteca Biblioteca_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Biblioteca"
    ADD CONSTRAINT "Biblioteca_pkey" PRIMARY KEY (usuario_id_usuario);


--
-- TOC entry 2711 (class 2606 OID 16428)
-- Name: Evento Evento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Evento"
    ADD CONSTRAINT "Evento_pkey" PRIMARY KEY ("Id_evento");


--
-- TOC entry 2713 (class 2606 OID 16433)
-- Name: Lista Lista_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Lista"
    ADD CONSTRAINT "Lista_pkey" PRIMARY KEY (nombre_lista);


--
-- TOC entry 2715 (class 2606 OID 16438)
-- Name: Ruta Ruta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Ruta"
    ADD CONSTRAINT "Ruta_pkey" PRIMARY KEY ("Id_ruta");


--
-- TOC entry 2707 (class 2606 OID 16413)
-- Name: Usuario Usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Usuario"
    ADD CONSTRAINT "Usuario_pkey" PRIMARY KEY ("Id_usuario");


--
-- TOC entry 2717 (class 2606 OID 16419)
-- Name: Biblioteca Biblioteca_FKIndex1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Biblioteca"
    ADD CONSTRAINT "Biblioteca_FKIndex1" FOREIGN KEY (usuario_id_usuario) REFERENCES public."Usuario"("Id_usuario") NOT VALID;


--
-- TOC entry 2719 (class 2606 OID 16449)
-- Name: Biblioteca Biblioteca_FKIndex2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Biblioteca"
    ADD CONSTRAINT "Biblioteca_FKIndex2" FOREIGN KEY (evento_id_evento) REFERENCES public."Evento"("Id_evento") NOT VALID;


--
-- TOC entry 2718 (class 2606 OID 16444)
-- Name: Biblioteca Biblioteca_FKIndex3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Biblioteca"
    ADD CONSTRAINT "Biblioteca_FKIndex3" FOREIGN KEY (ruta_id_ruta) REFERENCES public."Ruta"("Id_ruta") NOT VALID;


--
-- TOC entry 2716 (class 2606 OID 16439)
-- Name: Usuario Usuario_FKIndex1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Usuario"
    ADD CONSTRAINT "Usuario_FKIndex1" FOREIGN KEY ("Lista_nombre_lista") REFERENCES public."Lista"(nombre_lista) NOT VALID;


--
-- TOC entry 2720 (class 2606 OID 16454)
-- Name: Lista lista_FKIndex1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Lista"
    ADD CONSTRAINT "lista_FKIndex1" FOREIGN KEY ("Evento_id_evento") REFERENCES public."Evento"("Id_evento") NOT VALID;


--
-- TOC entry 2721 (class 2606 OID 16459)
-- Name: Lista lista_FKIndex2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Lista"
    ADD CONSTRAINT "lista_FKIndex2" FOREIGN KEY ("Ruta_id_ruta") REFERENCES public."Ruta"("Id_ruta") NOT VALID;


-- Completed on 2020-04-13 21:29:10

--
-- PostgreSQL database dump complete
--

