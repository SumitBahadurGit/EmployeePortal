DROP TABLE documents;


DROP TABLE employemnt_hist;

DROP TABLE timesheets;

DROP TABLE personal_info;

DROP TABLE education;

DROP TABLE work;

DROP TABLE vendor;

DROP TABLE client;

DROP TABLE address;

DROP TABLE LOG_IN;


CREATE TABLE LOG_IN (
    login_id        NUMBER(10) PRIMARY KEY,
    username_       VARCHAR(15) NOT NULL,
    old_password_   VARCHAR(15),
    password_       VARCHAR(15) NOT NULL,
    cur_status      VARCHAR(15) NOT NULL,
    attempts        NUMBER(3),
    user_role VARCHAR(20) NOT NULL,
    employee_id     NUMBER(10) UNIQUE NOT NULL
);

CREATE TABLE work (
    work_id            NUMBER(10) PRIMARY KEY,
    area_of_work       VARCHAR(30),
    is_curr_employed   VARCHAR(1) NOT NULL,
    last_employer      VARCHAR(30) NOT NULL,
    work_expnc_year    VARCHAR(5) NOT NULL
);

CREATE TABLE education (
    education_id           NUMBER(10) PRIMARY KEY,
    college_degree         VARCHAR(30),
    highest_degree         VARCHAR(30) NOT NULL,
    grad_year              NUMBER(4) NOT NULL,
    last_college_country   VARCHAR(10) NOT NULL,
    last_college_name      VARCHAR(50)
);

CREATE TABLE address (
    address_id       NUMBER(10) PRIMARY KEY,
    address_line_1   VARCHAR(100) NOT NULL,
    address_line_2   VARCHAR(100),
    city             VARCHAR(30) NOT NULL,
    state_           VARCHAR(20) NOT NULL,
    zip              NUMBER(5) NOT NULL
);

CREATE TABLE vendor (
    vendor_id              NUMBER(10) PRIMARY KEY,
    vendor_name            VARCHAR(30) NOT NULL,
    vendor_hr_name         VARCHAR(30),
    vendor_hr_email        VARCHAR(30),
    vendor_hr_phone        VARCHAR(30),
    vendor_contact_name    VARCHAR(30),
    vendor_contact_email   VARCHAR(30),
    vendor_contact_phone   VARCHAR(30),
    vendor_address_id      NUMBER(10),
    CONSTRAINT fk_vendor FOREIGN KEY ( vendor_address_id )
        REFERENCES address ( address_id )
);

CREATE TABLE client (
    client_id              NUMBER(10) PRIMARY KEY,
    client_name            VARCHAR(30) NOT NULL,
    client_hr_name         VARCHAR(30),
    client_hr_email        VARCHAR(30),
    client_hr_phone        VARCHAR(30),
    client_contact_name    VARCHAR(30),
    client_contact_email   VARCHAR(30),
    client_contact_phone   VARCHAR(30),
    client_address_id      NUMBER(10),
    CONSTRAINT fk_client FOREIGN KEY ( client_address_id )
        REFERENCES address ( address_id )
);

CREATE TABLE personal_info (
    employee_id      NUMBER(10) PRIMARY KEY,
    first_name       VARCHAR(30) NOT NULL,
    middle_name      VARCHAR(30),
    last_name        VARCHAR(30) NOT NULL,
    dob              VARCHAR(10) NOT NULL,
    email            VARCHAR(30) NOT NULL,
    phone            NUMBER(10) NOT NULL,
    visa_status      VARCHAR(15) NOT NULL ,
    country          VARCHAR(20) NOT NULL,
    phone_2          VARCHAR(30),
    email_2          VARCHAR(30),
    joined           VARCHAR(30),
    hired            VARCHAR(30),
    manager_name     VARCHAR(30),
    recruiter_name   VARCHAR(30),
    ssn              VARCHAR(10),
    work_id          NUMBER(10),
    education_id     NUMBER(10),
    address_id       NUMBER(10),
    last_updated     TIMESTAMP,
    CONSTRAINT fk_work FOREIGN KEY ( work_id )
        REFERENCES work ( work_id ),
    CONSTRAINT fk_education FOREIGN KEY ( education_id )
        REFERENCES education ( education_id ),
    CONSTRAINT fk_address FOREIGN KEY ( address_id )
        REFERENCES address ( address_id ),
    CONSTRAINT fk_lg FOREIGN KEY ( employee_id )
        REFERENCES LOG_IN ( employee_id )
);

CREATE TABLE employemnt_hist (
    emp_id               NUMBER(10) PRIMARY KEY,
    emp_status           VARCHAR(30),
    start_date           VARCHAR(30),
    end_date             VARCHAR(30),
    hourly_wage_rec      VARCHAR(30),
    hourly_wage_pay      VARCHAR(30),
    termination_reason   VARCHAR(30),
    work_permit_status   VARCHAR(30),
    work_permit_start    VARCHAR(30),
    work_permit_end      VARCHAR(30),
    job_title            VARCHAR(30),
    job_desc             VARCHAR(30),
    technology           VARCHAR(30),
    vendor_id            NUMBER(10),
    client_id            NUMBER(10),
    employee_id          NUMBER(10),
    CONSTRAINT fk_emp_vend FOREIGN KEY ( vendor_id )
        REFERENCES vendor ( vendor_id ),
    CONSTRAINT fk_emp_cl FOREIGN KEY ( client_id )
        REFERENCES client ( client_id ),
    CONSTRAINT fk_emp FOREIGN KEY ( employee_id )
        REFERENCES personal_info ( employee_id )
);

CREATE TABLE documents (
    doc_id         NUMBER(10) PRIMARY KEY,
    employee_id    NUMBER(10) NOT NULL,
    file_name      VARCHAR(100) NOT NULL,
    file_type      VARCHAR(20) NOT NULL,
    file_size      VARCHAR(10) NOT NULL,
    last_updated   TIMESTAMP,
    doc_desc       VARCHAR(100),
    CONSTRAINT fk_doc_emp FOREIGN KEY ( employee_id )
        REFERENCES personal_info ( employee_id )
);

CREATE TABLE timesheets (
    timesheet_id         NUMBER(10) PRIMARY KEY,
    timesheet_group_id   NUMBER(10) NOT NULL,
    employee_id          NUMBER(10) NOT NULL,
    status               VARCHAR(30) NOT NULL,
    timesheet_date       DATE NOT NULL,
    start_time           VARCHAR(20) NOT NULL,
    end_time             VARCHAR(10) NOT NULL,
    total_hours          FLOAT NOT NULL,
    over_time            FLOAT NOT NULL,
    project_details      VARCHAR(100),
    project_location     VARCHAR(100),
    is_approved          VARCHAR(10) NOT NULL,
    last_updated         TIMESTAMP,
    submitted            TIMESTAMP,
    approved             TIMESTAMP,
    approved_by          VARCHAR(50),
    ts_desc              VARCHAR(100),
    CONSTRAINT fk_ts_emp FOREIGN KEY ( employee_id )
        REFERENCES personal_info ( employee_id )
);


CREATE OR REPLACE TRIGGER last_updated_timesheets BEFORE
    INSERT OR UPDATE ON timesheets
    FOR EACH ROW
BEGIN
    :new.last_updated := SYSDATE;
END;

CREATE OR REPLACE TRIGGER last_updated_personal_info BEFORE
    INSERT OR UPDATE ON personal_info
    FOR EACH ROW
BEGIN
    :new.last_updated := SYSDATE;
END;

CREATE OR REPLACE TRIGGER last_updated_documents BEFORE
    INSERT OR UPDATE ON documents
    FOR EACH ROW
BEGIN
    :new.last_updated := SYSDATE;
END;

