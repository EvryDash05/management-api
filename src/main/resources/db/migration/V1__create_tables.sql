-- Tablas maestras
CREATE TABLE IF NOT EXISTS authorities
(
    authority_id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    authority      VARCHAR(50)                    NOT NULL UNIQUE,
    created_by     VARCHAR(255)                   NOT NULL DEFAULT 'SYSTEM',
    created_at     TIMESTAMP                               DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255)                   NULL,
    last_update_at TIMESTAMP                               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id        INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(50)  NOT NULL,
    created_by     VARCHAR(255) NOT NULL DEFAULT 'SYSTEM',
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users
(
    user_id        INT PRIMARY KEY AUTO_INCREMENT,
    status         TINYINT(1)   NOT NULL DEFAULT 1,
    email          VARCHAR(100) NOT NULL UNIQUE,
    password       VARCHAR(250) NOT NULL,
    created_by     VARCHAR(255) NOT NULL DEFAULT 'SYSTEM',
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS departments
(
    department_id  VARCHAR(15) PRIMARY KEY,
    name           VARCHAR(100) UNIQUE,
    created_by     VARCHAR(255) NOT NULL DEFAULT 'SYSTEM',
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS modalities
(
    modality_id    INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(20)  NOT NULL UNIQUE,
    created_by     VARCHAR(255) NOT NULL DEFAULT 'SYSTEM',
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS educations
(
    education_id   INT PRIMARY KEY AUTO_INCREMENT,
    order_num      INT          NOT NULL UNIQUE,
    status         TINYINT(1)   NOT NULL DEFAULT 1,
    created_by     VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS courses
(
    course_id      INT PRIMARY KEY AUTO_INCREMENT,
    modality_id    INT          NOT NULL,
    order_num      INT          NOT NULL UNIQUE,
    hours          INT          NOT NULL,
    status         TINYINT(1)   NOT NULL DEFAULT 1,
    created_by     VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (modality_id) REFERENCES modalities (modality_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS modules
(
    module_id      INT PRIMARY KEY AUTO_INCREMENT,
    course_id      INT          NOT NULL,
    order_num      INT          NOT NULL UNIQUE,
    status         TINYINT(1)   NOT NULL DEFAULT 1,
    created_by     VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS lessons
(
    lesson_id      INT PRIMARY KEY AUTO_INCREMENT,
    module_id      INT          NOT NULL,
    order_num      INT          NOT NULL UNIQUE,
    status         TINYINT(1)   NOT NULL DEFAULT 1,
    created_by     VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (module_id) REFERENCES modules (module_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS study_materials
(
    study_material_id INT PRIMARY KEY AUTO_INCREMENT,
    lesson_id         INT          NOT NULL,
    created_by        VARCHAR(255) NOT NULL,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_by    VARCHAR(255) NULL,
    last_update_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (lesson_id) REFERENCES lessons (lesson_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS files
(
    file_id        INT PRIMARY KEY AUTO_INCREMENT,
    entity_id      INT          NOT NULL,
    link           TEXT         NOT NULL,
    entity_type    VARCHAR(50)  NOT NULL,
    status         TINYINT(1)   NOT NULL DEFAULT 1,
    created_by     VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE (entity_id, entity_type)
);

CREATE TABLE IF NOT EXISTS banners
(
    banner_id      INT PRIMARY KEY AUTO_INCREMENT,
    redirect_link  TEXT         NOT NULL,
    link           TEXT         NOT NULL,
    order_num      INT          NOT NULL UNIQUE,
    status         TINYINT(1)   NOT NULL DEFAULT 1,
    type           VARCHAR(25)  NOT NULL,
    created_by     VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS translations
(
    translation_id INT PRIMARY KEY AUTO_INCREMENT,
    entity_id      INT          NOT NULL,
    language       VARCHAR(5)   NOT NULL,
    field          VARCHAR(50)  NOT NULL,
    value          TEXT         NOT NULL,
    entity_type    VARCHAR(50)  NOT NULL,
    created_by     VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE (entity_id, language, field, entity_type)
);

CREATE TABLE IF NOT EXISTS calendar
(
    calendar_id    INT PRIMARY KEY AUTO_INCREMENT,
    start_date     DATE         NOT NULL,
    end_date       DATE         NOT NULL,
    start_time     TIME         NOT NULL,
    end_time       TIME         NOT NULL,
    status         TINYINT(1)   NOT NULL DEFAULT 1,
    created_by     VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tablas intermedias y dependientes
CREATE TABLE IF NOT EXISTS provinces
(
    province_id    VARCHAR(15) PRIMARY KEY,
    department_id  VARCHAR(15),
    name           VARCHAR(100) UNIQUE,
    created_by     VARCHAR(255) NOT NULL DEFAULT 'SYSTEM',
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at      TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (department_id) REFERENCES departments (department_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS districts
(
    district_id    VARCHAR(15) PRIMARY KEY,
    province_id    VARCHAR(15),
    name           VARCHAR(100),
    created_by     VARCHAR(255) NOT NULL DEFAULT 'SYSTEM',
    created_at     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at      TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (province_id) REFERENCES provinces (province_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_details
(
    user_detail_id VARCHAR(15) PRIMARY KEY,
    user_id        INT          NOT NULL,
    district_id    VARCHAR(15),
    name           VARCHAR(100),
    last_name      VARCHAR(100),
    avatar         TEXT,
    created_by     VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (district_id) REFERENCES districts (district_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS role_authority
(
    role_id        INT          NOT NULL,
    authority_id   INT          NOT NULL,
    PRIMARY KEY (role_id, authority_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id),
    FOREIGN KEY (authority_id) REFERENCES authorities (authority_id)
);

CREATE TABLE IF NOT EXISTS roles_users
(
    role_id        INT          NOT NULL,
    user_id        INT          NOT NULL,
    PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS education_courses
(
    education_id   INT          NOT NULL,
    course_id      INT          NOT NULL,
    created_by     VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_by VARCHAR(255) NULL,
    last_update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (education_id, course_id),
    FOREIGN KEY (education_id) REFERENCES educations (education_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS courses_teachers
(
    course_teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    teacher_id        INT          NOT NULL,
    course_id         INT          NOT NULL,
    created_by        VARCHAR(255) NOT NULL,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_by    VARCHAR(255) NULL,
    last_update_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE (course_id, teacher_id),
    FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS calendar_courses
(
    calendar_course_id INT PRIMARY KEY AUTO_INCREMENT,
    calendar_id        INT          NOT NULL,
    course_teacher_id  INT          NOT NULL,
    created_by         VARCHAR(255) NOT NULL,
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_by     VARCHAR(255) NULL,
    last_update_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE (calendar_id, course_teacher_id),
    FOREIGN KEY (calendar_id) REFERENCES calendar (calendar_id) ON DELETE CASCADE,
    FOREIGN KEY (course_teacher_id) REFERENCES courses_teachers (course_teacher_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS calendar_courses_students
(
    calendar_course_id INT          NOT NULL,
    student_id         INT          NOT NULL,
    created_by         VARCHAR(255) NOT NULL,
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_by     VARCHAR(255) NULL,
    last_update_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (calendar_course_id, student_id),
    FOREIGN KEY (calendar_course_id) REFERENCES calendar_courses (calendar_course_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS calendar_conference
(
    calendar_conference_id INT PRIMARY KEY AUTO_INCREMENT,
    calendar_id            INT          NOT NULL,
    teacher_id             INT          NOT NULL,
    place                  TEXT         NOT NULL,
    type                   VARCHAR(11)  NOT NULL,
    created_by             VARCHAR(255) NOT NULL,
    created_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_by         VARCHAR(255) NULL,
    last_update_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE (calendar_id, teacher_id),
    FOREIGN KEY (calendar_id) REFERENCES calendar (calendar_id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS learning_sessions
(
    learning_session_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id             INT          NOT NULL,
    role                VARCHAR(15)  NOT NULL,
    ip_address          VARCHAR(45)  NOT NULL,
    device              VARCHAR(15)  NOT NULL,
    user_agent          TEXT         NOT NULL,
    location            TEXT         NOT NULL,
    latitude            VARCHAR(15)  NOT NULL,
    longitude           VARCHAR(15)  NOT NULL,
    login_at            TIMESTAMP    NOT NULL,
    logout_at           TIMESTAMP    NOT NULL,
    duration_seconds    INT          NOT NULL,
    status              TINYINT(1)   NOT NULL DEFAULT 1,
    created_by          VARCHAR(255) NOT NULL,
    created_at          TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_update_by      VARCHAR(255) NULL,
    last_update_at      TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS sessions
(
    session_id    VARCHAR(255) PRIMARY KEY,
    user_id       INT         NULL,
    ip_address    VARCHAR(45) NULL,
    user_agent    TEXT        NULL,
    payload       TEXT        NOT NULL,
    last_activity INT         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS learning_session_events
(
    learning_session_event_id INT PRIMARY KEY AUTO_INCREMENT,
    session_id                VARCHAR(255) NOT NULL,
    event_type                VARCHAR(45)  NOT NULL,
    entity_type               VARCHAR(50)  NOT NULL,
    entity_id                 INT          NOT NULL,
    duration_seconds          INT          NOT NULL,
    created_by                VARCHAR(255) NOT NULL,
    created_at                TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_by            VARCHAR(255) NULL,
    last_update_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (session_id) REFERENCES sessions (session_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS refresh_tokens
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    token_hash VARCHAR(255) NOT NULL,                           -- hash del token, nunca el token plano
    user_id    INT       NOT NULL,                           -- usuario al que pertenece el token
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP, -- cuándo se emitió
    created_by VARCHAR(255) NOT NULL DEFAULT 'SYSTEM',          -- quién lo creó (puede ser 'SYSTEM' o un admin)
    expires_at TIMESTAMP    NOT NULL,                           -- fecha/hora de expiración
    revoked    BOOLEAN      NOT NULL DEFAULT FALSE,             -- indicador si fue revocado
    revoked_at TIMESTAMP    NULL,                               -- cuándo se revocó
    revoked_by VARCHAR(255) NULL,                               -- quién lo revocó (usuario, admin, SYSTEM)
    CONSTRAINT uq_token_hash UNIQUE (token_hash),
    CONSTRAINT fk_refresh_user FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE  IF NOT EXISTS refresh_token_audit
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    token_id    INT          NOT NULL, -- referencia al token
    action_type VARCHAR(50)  NOT NULL, -- CREATED, REVOKED, USED, etc.
    action_by   VARCHAR(255) NOT NULL, -- usuario, admin, SYSTEM
    action_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ip_address  VARCHAR(45)  NULL,     -- ip del usuario
    user_agent  TEXT         NULL,     -- navegador desde donde se hizo la acción
    FOREIGN KEY (token_id) REFERENCES refresh_tokens (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS password_reset_tokens
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    user_id    INT          NOT NULL,
    token_hash VARCHAR(255) NOT NULL, -- hash del token, nunca el valor en claro
    expires_at TIMESTAMP    NOT NULL,
    used_at    TIMESTAMP    NULL,
    revoked_at TIMESTAMP    NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS password_reset_token_audit
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    token_id         INT       NOT NULL, -- relación con el token principal
    created_by       VARCHAR(255) NOT NULL, -- quién solicitó el token
    created_date     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_by   VARCHAR(255) NULL,     -- último que lo modificó (ej: usado, revocado)
    last_update_date TIMESTAMP    NULL,
    revoked_by       VARCHAR(255) NULL,     -- quién lo revocó (puede ser usuario, admin, SYSTEM)
    ip_address       VARCHAR(45)  NULL,     -- IP desde donde se solicitó
    user_agent       TEXT         NULL,     -- navegador/dispositivo
    FOREIGN KEY (token_id) REFERENCES password_reset_tokens (id) ON DELETE CASCADE
);

-- Índices de sessions
CREATE INDEX sessions_user_id_index ON sessions (user_id);
CREATE INDEX sessions_last_activity_index ON sessions (last_activity);
