

CREATE TABLE company_workflowtype_items_ocr_preset
(
    id uuid NOT NULL primary key,
    identity varchar(45) DEFAULT NULL,
    company_id uuid NOT NULL,
    workflow_type_id uuid NOT NULL,
    preset_name character varying(200) NOT NULL,
    status smallint NOT NULL DEFAULT 1,
    version integer NOT NULL DEFAULT 1,
    created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
    updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
    CONSTRAINT "FK_COMPANY_WORKFLOWTYPE_ITEMS_OCR_PRESET_COMPANY" FOREIGN KEY (company_id)
        REFERENCES public.companies (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALI
);

CREATE TABLE company_workflowtype_items_ocr_preset_items
(
    id uuid NOT NULL primary key ,
    preset_id uuid NOT NULL,
    property_name character varying(200) NOT NULL,
    value character varying(4000),
    ocr_type smallint NOT NULL DEFAULT 1,
    status smallint NOT NULL DEFAULT 1,
    version integer NOT NULL DEFAULT 1,
    created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
    CONSTRAINT "FK_COMPANY_WORKFLOWTYPE_ITEMS_OCR_PRESET_ITEMS_PRESET" FOREIGN KEY (preset_id)
        REFERENCES public.company_workflowtype_items_ocr_preset (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);


