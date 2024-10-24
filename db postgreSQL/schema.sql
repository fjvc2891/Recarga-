CREATE DATABASE recarga_db;

USE recarga_db;

CREATE TABLE IF NOT EXISTS public.operador
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    nombre character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT operators_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.operador
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.recargas
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    cantidad numeric(38,2) NOT NULL,
    valor numeric(38,2) NOT NULL,
    operador_id bigint NOT NULL,
    vendedor_id bigint NOT NULL,
    fecha_venta time without time zone NOT NULL DEFAULT now(),
    CONSTRAINT recharges_pkey PRIMARY KEY (id),
    CONSTRAINT recharges_operator_id_fkey FOREIGN KEY (operador_id)
        REFERENCES public.operador (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT recharges_salesperson_id_fkey FOREIGN KEY (vendedor_id)
        REFERENCES public.vendedor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.recargas
    OWNER to postgres;

-- Table: public.vendedor

-- DROP TABLE IF EXISTS public.vendedor;

CREATE TABLE IF NOT EXISTS public.vendedor
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    nombre character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT salespersons_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.vendedor
    OWNER to postgres;
