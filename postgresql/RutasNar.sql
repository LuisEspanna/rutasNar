--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.16
-- Dumped by pg_dump version 9.6.16

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

--
-- Name: RutasNar; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "RutasNar" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';


ALTER DATABASE "RutasNar" OWNER TO postgres;

\connect "RutasNar"

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

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: nuevacoordenada(character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.nuevacoordenada(idruta character varying, lat character varying, lon character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
INSERT INTO coordenadas VALUES ((select now()), idruta, lat, lon);
END;
$$;


ALTER FUNCTION public.nuevacoordenada(idruta character varying, lat character varying, lon character varying) OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: actividades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actividades (
    id_actividad character varying,
    id_usuario character varying NOT NULL,
    nom_actividad character varying NOT NULL,
    id_ruta character varying,
    id_evento character varying
);


ALTER TABLE public.actividades OWNER TO postgres;

--
-- Name: coordenadas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coordenadas (
    id_coordenada character varying NOT NULL,
    id_ruta character varying,
    latitud character varying,
    longitud character varying
);


ALTER TABLE public.coordenadas OWNER TO postgres;

--
-- Name: eventos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.eventos (
    id_evento character varying NOT NULL,
    id_municipio character varying,
    nom_evento character varying,
    desc_evento character varying,
    img_evento character varying,
    fecha_evento character varying,
    disponible character varying,
    latitud character varying,
    longitud character varying
);


ALTER TABLE public.eventos OWNER TO postgres;

--
-- Name: municipios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.municipios (
    id_municipio character varying NOT NULL,
    nom_municipio character varying,
    latitud character varying,
    longitud character varying
);


ALTER TABLE public.municipios OWNER TO postgres;

--
-- Name: rutas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rutas (
    id_ruta character varying NOT NULL,
    id_municipio character varying,
    nom_ruta character varying,
    desc_ruta character varying,
    img_ruta character varying,
    tiempo_ruta character varying
);


ALTER TABLE public.rutas OWNER TO postgres;

--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id_usuario character varying NOT NULL,
    nom_usuario character varying,
    clave_usuario character varying
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- Data for Name: actividades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actividades (id_actividad, id_usuario, nom_actividad, id_ruta, id_evento) FROM stdin;
1588648340997	123456	Reserva Natural rio ñambi 	1588048354458	
1589001739031	123456	Santuario de la corota 	1588047131237	
1589001768772	123456	Concurso Departamental de Bandas Musicales		1587407659538
\.


--
-- Data for Name: coordenadas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.coordenadas (id_coordenada, id_ruta, latitud, longitud) FROM stdin;
2020-04-27 23:12:11.592803-05	1588047131237	1.2134783384995906	-77.27614196777253
2020-04-27 23:12:11.679781-05	1588047131237	1.1305832469874708	-77.148833599089
2020-04-27 23:12:11.689652-05	1588047131237	1.2038674402833827	-77.21966537475477
2020-04-27 23:12:11.696106-05	1588047131237	1.1613044875179992	-77.15306076049723
2020-04-27 23:12:11.703993-05	1588047131237	1.1305832469874708	-77.148833599089
2020-04-27 23:16:56.418726-05	1588047414627	1.2138215842344944	-77.27579864501897
2020-04-27 23:16:56.49688-05	1588047414627	0.8269589340925689	-77.62427124023445
2020-04-27 23:16:56.501929-05	1588047414627	0.9079738110987646	-77.77807983398449
2020-04-27 23:20:29.637916-05	1588047629260	1.2090161399867616	-77.26996215820286
2020-04-27 23:20:29.705582-05	1588047629260	0.8279887949429852	-77.64281066894527
2020-04-27 23:25:42.160327-05	1588047941779	1.2151945667381323	-77.27751525878861
2020-04-27 23:25:42.253514-05	1588047941779	1.3559214300098048	-77.28232177734309
2020-04-27 23:25:42.262741-05	1588047941779	1.668235958040349	-77.00972351074236
2020-04-27 23:25:42.271964-05	1588047941779	1.5968538160689352	-76.96989807128854
2020-04-27 23:28:17.108608-05	1588048097037	1.2145080755734161	-77.28369506835918
2020-04-27 23:28:17.170515-05	1588048097037	1.207643154352211	-78.01428588867162
2020-04-27 23:28:17.173043-05	1588048097037	1.8006977940496716	-78.7833288574218
2020-04-27 23:32:34.548216-05	1588048354458	1.2158810577283	-77.26996215820286
2020-04-27 23:32:34.604799-05	1588048354458	1.0854447477193188	-77.61740478515584
2020-04-27 23:32:34.617275-05	1588048354458	1.3215985267170836	-78.12552246093747
2020-04-27 23:32:34.622236-05	1588048354458	1.6757858427588865	-78.13925537109381
\.


--
-- Data for Name: eventos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.eventos (id_evento, id_municipio, nom_evento, desc_evento, img_evento, fecha_evento, disponible, latitud, longitud) FROM stdin;
1587407659538	1587406677102	Concurso Departamental de Bandas Musicales	Más de mil artistas tejiendo sonidos de paz, los mejores tríos, danzas nacionales e internacionales y las noches de Samaniego.\r\n\r\n¡No te lo imagines, vívelo! Las Bandas Musicales te esperan. Ven y disfruta de este encuentro con la música y la cultura. 	samaniego-01.png	2020-08-16	true	1.3	-77.594
1587410159004	1587409948816	Festividades de Inti Raymi	Esta celebración precolombina de los pueblos indígenas de Colombia, Ecuador, Perú y Bolivia se realiza como un agradecimiento al sol y a la madre tierra por las cosechas. Durante esta actividad se busca fortalecer la cultura de la gente y enseñar a las nuevas generaciones la tradición.\r\n\r\nDurante tres días se realizan rituales de purificación, bailes, música tradicional, así mismo se comparten chanchuco (aguardiente casero) y chicha mascada. Los indígenas y habitantes de los resguardos de la región bailan en círculos por horas y zapatean para mantener la tierra despierta.  	cumbal-inti-raymi.png	2020-06-01	true	0.9	-77.8
1587410975747	1587410779236	El Carnaval del Fuego 	El Carnaval del Fuego es el evento más importante de la “Perla del Pacífico” Tumaco, ahí las marimbas, cununos, bombos y guasá reciben a sus visitantes en una fiesta de cuatro días llenos de color y alegría que evidencian la fusión de la cultura africana y andina. \r\n\r\nEl Desfile Náutico es uno de los eventos que más convoca en el Carnaval; durante el mismo las playas de Tumaco se llenan de embarcaciones que aglomeran a cientos de personas, mientras las reinas salen en traje de baño.	tumaco-carnaval-fuego.jpg	2021-03-01	false	1.8	-78.75
1587411304067	1587279297110	 Fiesta de la Virgen de las Mercedes	Pasto celebra la Fiesta de su “Gobernadora”, la Virgen de las Mercedes. Sus devotos visitan y acompañan su imagen que posa en el Santuario de Nuestra Señora de La Merced, y celebran con actividades culturales y artísticas que se programan en la ciudad. De igual forma, la ciudad ofrece una programación musical y cultural en torno a la celebración, miles de personas se reúnen en la Plaza de Nariño para disfrutar de presentaciones artísticas y fuegos artificiales.  	pasto-virgen-mercedes.jpg	2020-09-24	true	1.2	-77.267
1587411574649	1587279297110	 Festival Internacional de Teatro de Pasto	La capital nariñense es el escenario para uno de los festivales más importantes del mapa teatral colombiano y latinoamericano, se trata del Festival Internacional de Teatro de Pasto que ya lleva más de veinte versiones. \r\n\r\nDurante la celebración del festival, el teatro se toma los parques, salas y auditorios de la ciudad. Así mismo se realizan talleres, tertulias y conversatorios con maestros y artistas invitados para promover la cultura de este arte y convoca a afianzar lazos de unión entre la comunidad que participa. 	pasto-festival-teatro.jpg	2020-08-17	true	1.2	-77.267
1587412305333	1587279297110	Encuentro de Carnavales de Fiestas Tradicional del Mundo 	El Encuentro de Carnavales de Fiestas Tradicional del Mundo es un espacio intercultural,  que reúne manifestaciones festivas reconocidas a nivel mundial por el tratamiento al patrimonio cultural Inmaterial de la humanidad, es así como Brasil, México, Bolivia, Perú, España, entre otros, hacen gala de su cultura en la senda del carnaval de Pasto. \r\n\r\nEn su programación recoge eventos académicos que dan reconocimiento al valor cultural de cada representación; eventos musicales con artistas de reconocimiento nacional e internacional; y un desfile que es símbolo de hermandad de culturas y muestras artísticas del mundo. 	pasto-carnaval-internacional.jpg	2020-06-01	true	1.2	-77.267
1587412978093	1587279297110	Arcoíris en el Asfalto	El Arco Iris de Asfalto congrega a niños, niñas, jóvenes, adultos y personas mayores en una celebración de color, arte y libre pensamiento. Todos los invitados pintan las principales calles de la ciudad, dejando un mensaje de unión, sentido de pertenencia y cuidado de la ciudad. \r\n \r\nEste es un evento  precarnaval que nació como una alternativa cultural y ecológica al carnaval de agua que se realizaba anteriormente en San Juan de Pasto, durante el 28 de diciembre de cada año. Hoy en día busca promover entre turistas y habitantes de la zona la conciencia sobre el cuidado de los recursos hídricos a partir de una fiesta de color y cultura. 	pasto-arcoiris.jpg	2020-12-28	true	1.2	-77.267
1587413683559	1587279297110	Carnaval del Cuy y la Cultura Campesina 	Los campesinos de diferentes corregimientos de Pasto se reúnen en un festival para deleitar con su exquisito plato típico, el cuy y otras delicias de la gastronomía nariñense, en el Carnaval del Cuy y la Cultura Campesina, un evento que se realiza a las afueras de la ciudad rumbo a Anganoy. \r\n \r\nDurante el evento una gran muestra de platos típicos se ofrece para cautivar el paladar de los visitantes; el cuy asado, frito pastuso, dulces y manjares hacen parte de la gastronomía de esta celebración. 	pasto-carnaval-cuy.jpg	2021-01-07	false	1.2	-77.267
1587413967487	1587279297110	Desfile de Años Viejos 	Con creatividad y humor los habitantes de Pasto despiden el año que termina en medio de un gran desfile que recorre la senda del carnaval, ahí propios y visitantes pueden observar los muñecos gigantes en pequeñas carrozas que representan el “año viejo” y hacen gracia de acontecer colombiano. \r\n\r\nLos artesanos juegan con ironía haciendo alegoría a los hechos del año, en especial temas políticos  y sociales, sus muñecos o “años viejos” dejan un testamento sobre lo que deja el año que termina tanto para la ciudad como para el país. 	pasto-años-viejos.jpg	2020-12-31	true	1.2	-77.267
1587414486741	1587279297110	Encuentro de Sabores Andinos, Pasto Capital Gastrodiversa	La Secretaría de Desarrollo Económico y la Subsecretaría de Turismo realizan el festival más esperado por los amantes de la gastronomía nariñese, se trata del encuentro más grande de sabores andinos, afros y amazónicos que se da en la capital del sur. \r\n \r\nEl Encuentro de Sabores Andinos, Pasto Capital Gastrodiversa, es una feria que convoca cocineros locales y cocineros destacados del país, agricultores y expertos en la cocina colombiana para dar gala a la gastronomía tradicional de la región y relacionar el comer y cocinar con la importancia del campo y nuestros productos en la construcción de país.	pasto-sabor-andino.jpg	2020-09-26	true	1.2	-77.267
1587414810443	1587414624057	Festival Internacional Ipiales Cuna de Grandes Tríos 	El Festival Internacional Ipiales Cuna de Grandes Tríos es el evento cultural más importante de la música de cuerdas en Colombia y reconocido como patrimonio cultural de la nación en 1.894 por el Congreso de la República. \r\n\r\nEl certamen reúne a las más talentosas agrupaciones musicales ipialeñas, así como también invita a Tríos de reconocimiento nacional e internacional para brindar a propios y visitantes el mejor espectáculo de música de cuerdas, bailes y cantos de una tradición por décadas. 	ipiales-cuna-trios.jpg	2020-10-18	true	0.828752	-77.6426
1587415112896	1587406677102	Fiestas de Verano 	En esta festividad los sandoneños celebran con agrupaciones musicales y orquestas la mejor época del año. \r\n \r\nOrquestas y agrupaciones musicales de carácter nacional e internacional se dan cita en las plaza central de este municipio, donde llegan también visitantes para unirse a la celebración. \r\n \r\nEn el día se ven en las calles diferentes exposiciones artesanales con las obras elaboradas en paja toquilla, técnica representativa del municipio de Sandoná. \r\n \r\nEsta celebración es la oportunidad para disfrutar de la cultura, la tradición y la gastronomía del pueblo sandoneño.	samaniego-fiesta-verano.jpg	2020-08-01	true	1.3	-77.594
1587415566433	1587414624057	Carnaval Multicolor de la Frontera 	El Carnaval Multicolor de la Frontera es el evento más importante que se realiza en Ipiales, se trata de una celebración semejante al Carnaval de Negros y Blancos de la capital nariñense, San Juan de Pasto.  \r\n\r\nLas carrozas, comparsas, disfraces, murgas y agrupaciones musicales son una muestra del talento artístico ipialeño, una expresión del ingenio y la creatividad folclórica y cultural de la tradición de este municipio. 	ipiales-carnaval.jpg	2020-12-31	true	0.828752	-77.6426
1587416371193	1587416037741	Feria del Maíz	En el corregimiento Villamoreno de Buesaco se celebra anualmente la Feria del Maíz en honor al producto de vital importancia para las regiones andinas, fuente de alimentación sana para familias y animales.\r\n\r\nLas comunidades se preparan con las cosechas para las épocas de fiestas patronales. Durante el festejo se realizan desfiles, presentaciones musicales, torneos deportivos y por su puesto una exposición artesanal y gastronómica de platos tradicionales elaborados con maíz, donde no puede faltar el choclo con queso y café.\r\n\r\nEn esta celebración se da a conocer la importancia de este producto y sus diferentes usos, como es el de tipo medicinal; por ejemplo el pelo del maíz es usado para los problemas renales, entre otros.	buesaco-feria-maiz.jpg	2020-06-01	true	1.383	-77.15
1587416687276	1587416037741	Carnaval de Rojos	En Buesaco se celebra el Carnaval de Rojos el día 7 de enero, una tradición que llevan los buesaqueños desde hace más de 25 años.  Durante este día un desfile viste de rojo las calles del municipio con comparsas de atuendos elaborados con materiales naturales como látigo, fique, semillas, entre otros, acompañas de grupos musicales que imprimen alegría a la celebración. \r\n \r\nAsí mismo, sus habitantes usan el cosmético rojo para celebrar con propios y visitantes, haciendo de esta una fiesta de mucha alegría y jolgorio. En la plaza principal se realizan presentaciones musicales, un punto de encuentro para dar cierre a una de las celebraciones más representativas de Nariño. 	buesaco-carnaval-rojos.jpg	2021-01-07	false	1.383	-77.15
1587416920080	1587414624057	Fiesta de Nuestra Señora del Rosario Las Lajas	Para los devotos de la Virgen del Rosario, los admiradores de la cultura o los interesados en la historia, la Fiesta de Nuestra Señora del Rosario Las Lajas es el escenario para vivir la tradición de los municipios colombianos de Ipiales y Túquerres, junto a la ciudad de Tulcán del vecino país Ecuador.\r\n\r\nDurante esta festividad se conmemora la aparición de la virgen y se rinde homenaje a la figura mariana. Usualmente, en la víspera de la celebración se realizan serenatas y conciertos con bandas de música del municipio.	ipiales-fiesta-lajas.jpg	2020-09-16	true	0.828752	-77.6426
1589000260289	1587279297110	Carnaval de Negros y Blancos	Una forma de reencontrarse, de reconstruirse y de darle al corazón los latidos necesarios para el nuevo año. Un ritual que te hace correr el riesgo de encontrar magia alegre por donde quiera que mires. Bienvenidos a la única fiesta donde pintarse la cara, es patrimonio humano.	carnaval.png	2021-01-02	true	1.2105875640988848	-77.27641421198769
\.


--
-- Data for Name: municipios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.municipios (id_municipio, nom_municipio, latitud, longitud) FROM stdin;
1587279297110	Pasto	1.2	-77.267
1587406677102	Samaniego	1.3	-77.594
1587409948816	Cumbal	0.9	-77.8
1587410779236	Tumaco	1.8	 -78.75
1587414624057	Ipiales	0.828752	-77.6426
1587416037741	Buesaco	1.383	-77.15
1588046673205	El encano 	1.1620	-77.1553
1588046730768	La cruz	1.5206	-76.48
1588046757892	Barbacoas	1.548	-77.229
\.


--
-- Data for Name: rutas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rutas (id_ruta, id_municipio, nom_ruta, desc_ruta, img_ruta, tiempo_ruta) FROM stdin;
1588047131237	1588046673205	Santuario de la corota 	En la ruta que iniciaremos desde san juan de pasto pasaremos por los conociendo los municipios de la laguna y el encano, después viajaremos en lancha hasta el suntuario de la laguna 	descarga.jpg	3 horas
1588047414627	1587409948816	Asenso volcan cumbal 	Desde pasto viajaremos al municipio de ipiales después hasta el municipio del cumbal donde allí nos prepararemos para ascender a la cima del volcán cumbal 	volcan_cumbal.jpg	10 horas
1588047629260	1587279297110	Santuario de las lajas 	Viajaremos desde pasto hasta el municipio de ipiales haciendo una parada para recorrer el pueblo después nos dirigiremos hasta el hermoso santuario de las lajas 	Santuario de las lajas.jpg	3 horas
1588047941779	1588046730768	termales de tajumbina	Recorreremos desde pasto hasta llegar a las termales de tajumbina	termales tajumbina.jpg	6 horas
1588048097037	1587410779236	playa el morro	viajaremos desde pasto hasta el municipio de tumaco y haciendo una parada en ricaurte para conocer el municipio 	El morro.jpg	5 horas
1588048354458	1588046757892	Reserva Natural rio ñambi 	viajaremos hasta barbacoas desde pasto para conocer la reserva natural rió ñambi haciendo paradas a los municipios de tuquerres y junin	Reserva rio ñambi.jpg	6 horas
\.


--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id_usuario, nom_usuario, clave_usuario) FROM stdin;
123456	Luis España	clave_1234
\.


--
-- Name: actividades actividades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT actividades_pkey PRIMARY KEY (id_usuario, nom_actividad);


--
-- Name: coordenadas coordenadas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coordenadas
    ADD CONSTRAINT coordenadas_pkey PRIMARY KEY (id_coordenada);


--
-- Name: eventos eventos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eventos
    ADD CONSTRAINT eventos_pkey PRIMARY KEY (id_evento);


--
-- Name: municipios municipios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipios
    ADD CONSTRAINT municipios_pkey PRIMARY KEY (id_municipio);


--
-- Name: rutas rutas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rutas
    ADD CONSTRAINT rutas_pkey PRIMARY KEY (id_ruta);


--
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario);


--
-- Name: actividades fk_activ_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actividades
    ADD CONSTRAINT fk_activ_user FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) NOT VALID;


--
-- Name: coordenadas fk_coord_routes; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coordenadas
    ADD CONSTRAINT fk_coord_routes FOREIGN KEY (id_ruta) REFERENCES public.rutas(id_ruta);


--
-- Name: eventos fk_events_municipio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eventos
    ADD CONSTRAINT fk_events_municipio FOREIGN KEY (id_municipio) REFERENCES public.municipios(id_municipio);


--
-- Name: rutas fk_routes_municip; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rutas
    ADD CONSTRAINT fk_routes_municip FOREIGN KEY (id_municipio) REFERENCES public.municipios(id_municipio);


--
-- PostgreSQL database dump complete
--

