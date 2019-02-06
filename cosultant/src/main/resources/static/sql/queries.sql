drop table EMPLOYEMNT_HIST;
DROP TABLE PERSONAL_INFO;
drop table education;
drop table work;
drop table vendor;
drop table client;
drop table address;




create table WORK (
   WORK_ID number (10) PRIMARY KEY,
   AREA_OF_WORK varchar(30),
   IS_CURR_EMPLOYED varchar (1) not null,
   LAST_EMPLOYER varchar(30) not null,
   WORK_EXPNC_YEAR varchar(5) not null
);

create table EDUCATION (
   EDUCATION_ID number (10) PRIMARY KEY,
   COLLEGE_DEGREE varchar(30),
   HIGHEST_DEGREE varchar (30) not null,
   GRAD_YEAR NUMBER(4) not null,
   LAST_COLLEGE_COUNTRY varchar(10) not null,
   LAST_COLLEGE_NAME varchar (50)
);

create table ADDRESS (
   ADDRESS_ID number (10) PRIMARY KEY,
   ADDRESS_LINE_1 varchar(100) not null,
   ADDRESS_LINE_2 varchar (100),
   CITY varchar(30) not null,
   STATE_ varchar(20) not null,
   ZIP NUMBER(5) not null
);

        
        CREATE TABLE VENDOR (
        VENDOR_ID NUMBER (10) PRIMARY KEY,
        VENDOR_NAME VARCHAR (30) NOT NULL,
        VENDOR_HR_NAME VARCHAR (30),
        VENDOR_HR_EMAIL VARCHAR (30),
        VENDOR_HR_PHONE VARCHAR (30),
        VENDOR_CONTACT_NAME VARCHAR (30),
        VENDOR_CONTACT_EMAIL VARCHAR (30),
        VENDOR_CONTACT_PHONE VARCHAR (30),
        VENDOR_ADDRESS_ID NUMBER (10),
        CONSTRAINT FK_VENDOR FOREIGN KEY  (VENDOR_ADDRESS_ID) REFERENCES ADDRESS(ADDRESS_ID)
        );
        
   
                
        CREATE TABLE CLIENT (
        CLIENT_ID NUMBER (10) PRIMARY KEY,
        CLIENT_NAME VARCHAR (30) NOT NULL,
        CLIENT_HR_NAME VARCHAR (30),
        CLIENT_HR_EMAIL VARCHAR (30),
        CLIENT_HR_PHONE VARCHAR (30),
        CLIENT_CONTACT_NAME VARCHAR (30),
        CLIENT_CONTACT_EMAIL VARCHAR (30),
        CLIENT_CONTACT_PHONE VARCHAR (30),
        CLIENT_ADDRESS_ID NUMBER (10),
        CONSTRAINT FK_CLIENT FOREIGN KEY  (CLIENT_ADDRESS_ID) REFERENCES ADDRESS(ADDRESS_ID)
        );
        
                
                                     
    create table personal_info(
    EMPLOYEE_ID number(10) primary key,
    FIRST_NAME varchar(30) not null,
    MIDDLE_NAME varchar(30),
    LAST_NAME varchar(30) not null,
    DOB varchar(10) not null,
    EMAIL varchar(30) not null,
    PHONE NUMBER (10) not null,
    VISA_STATUS varchar(15) not null,
    COUNTRY varchar(20) not null,    
    PHONE_2 varchar(30) ,
    EMAIL_2 varchar(30) ,
    JOINED varchar(30) ,
    HIRED varchar(30),
    MANAGER_NAME varchar(30) ,
    RECRUITER_NAME varchar(30) ,
    SSN varchar(10) ,
    WORK_ID NUMBER(10),
    EDUCATION_ID NUMBER(10),
    ADDRESS_ID NUMBER(10),
    CONSTRAINT FK_WORK FOREIGN KEY (WORK_ID) REFERENCES WORK (WORK_ID),
    CONSTRAINT FK_EDUCATION FOREIGN KEY (EDUCATION_ID) REFERENCES EDUCATION (EDUCATION_ID),
    CONSTRAINT FK_ADDRESS FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS (ADDRESS_ID)
);


        CREATE TABLE EMPLOYEMNT_HIST (
        EMP_ID NUMBER (10) PRIMARY KEY,
        EMP_STATUS VARCHAR (30),
        START_DATE VARCHAR (30),
        END_DATE VARCHAR (30),
        HOURLY_WAGE_REC VARCHAR (30),
        HOURLY_WAGE_PAY VARCHAR (30),
        TERMINATION_REASON VARCHAR (30),
        WORK_PERMIT_STATUS VARCHAR (30),
        WORK_PERMIT_START VARCHAR (30),
        WORK_PERMIT_END VARCHAR (30),
        JOB_TITLE VARCHAR(30),
        JOB_DESC VARCHAR(30),
        TECHNOLOGY VARCHAR(30),
        VENDOR_ID NUMBER (10),
        CLIENT_ID NUMBER (10),
        EMPLOYEE_ID NUMBER(10),
        CONSTRAINT FK_EMP_VEND FOREIGN KEY  (VENDOR_ID) REFERENCES VENDOR(VENDOR_ID),
        CONSTRAINT FK_EMP_CL FOREIGN KEY  (CLIENT_ID) REFERENCES CLIENT(CLIENT_ID),
    	CONSTRAINT FK_EMP FOREIGN KEY (EMPLOYEE_ID) REFERENCES personal_info (EMPLOYEE_ID)
        );
                 
                     






            