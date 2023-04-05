CREATE TABLE bao_authority
(
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_bao_authority PRIMARY KEY (name)
);

CREATE TABLE jhi_user
(
    id         VARCHAR(255) NOT NULL,
    login      VARCHAR(50)  NOT NULL,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    email      VARCHAR(254),
    activated  BOOLEAN      NOT NULL,
    lang_key   VARCHAR(10),
    image_url  VARCHAR(256),
    CONSTRAINT pk_jhi_user PRIMARY KEY (id)
);

CREATE TABLE jhi_user_authority
(
    authority_name VARCHAR(50)  NOT NULL,
    user_id        VARCHAR(255) NOT NULL,
    CONSTRAINT pk_jhi_user_authority PRIMARY KEY (authority_name, user_id)
);

ALTER TABLE jhi_user
    ADD CONSTRAINT uc_jhi_user_email UNIQUE (email);

ALTER TABLE jhi_user
    ADD CONSTRAINT uc_jhi_user_login UNIQUE (login);

ALTER TABLE jhi_user_authority
    ADD CONSTRAINT fk_jhiuseaut_on_authority FOREIGN KEY (authority_name) REFERENCES bao_authority (name);

ALTER TABLE jhi_user_authority
    ADD CONSTRAINT fk_jhiuseaut_on_user FOREIGN KEY (user_id) REFERENCES jhi_user (id);

CREATE TABLE Customer
(
    id                 BIGINT       NOT NULL,
    created_by         VARCHAR(50)  NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by   VARCHAR(50),
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255) NOT NULL,
    surname            VARCHAR(255) NOT NULL,
    msisdn             VARCHAR(255) NOT NULL,
    email              VARCHAR(255) NOT NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

ALTER TABLE Customer
    ADD CONSTRAINT uc_customer_email UNIQUE (email);

ALTER TABLE Customer
    ADD CONSTRAINT uc_customer_msisdn UNIQUE (msisdn);

CREATE TABLE bank_account_type
(
    id          BIGINT       NOT NULL,
    type        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT pk_bank_account_type PRIMARY KEY (id)
);

ALTER TABLE bank_account_type
    ADD CONSTRAINT uc_bank_account_type_description UNIQUE (description);

ALTER TABLE bank_account_type
    ADD CONSTRAINT uc_bank_account_type_type UNIQUE (type);

CREATE TABLE bank_account
(
    id                 BIGINT       NOT NULL,
    account_id         VARCHAR(255) NOT NULL,
    balance            BIGINT,
    bankAccountType_id BIGINT,
    owner_id           BIGINT,
    CONSTRAINT pk_bank_account PRIMARY KEY (id)
);

ALTER TABLE bank_account
    ADD CONSTRAINT uc_bank_account_account UNIQUE (account_id);

ALTER TABLE bank_account
    ADD CONSTRAINT FK_BANK_ACCOUNT_ON_BANKACCOUNTTYPE FOREIGN KEY (bankAccountType_id) REFERENCES bank_account_type (id);

ALTER TABLE bank_account
    ADD CONSTRAINT FK_BANK_ACCOUNT_ON_OWNER FOREIGN KEY (owner_id) REFERENCES Customer (id);