INSERT INTO TB_BRAND (ID, FANTASY_NAME, CORPORATE_NAME, ACTIVE, CREATION_DATE, UPDATE_DATE)
VALUES(50, 'Test', 'Test', TRUE, CURRENT_TIMESTAMP, NULL);

INSERT INTO TB_BRAND (ID, FANTASY_NAME, CORPORATE_NAME, ACTIVE, CREATION_DATE, UPDATE_DATE)
VALUES(51, 'Test', 'Test', TRUE, CURRENT_TIMESTAMP, NULL);

INSERT INTO TB_BRAND (ID, FANTASY_NAME, CORPORATE_NAME, ACTIVE, CREATION_DATE, UPDATE_DATE)
VALUES(52, 'Test', 'Test', FALSE, CURRENT_TIMESTAMP, NULL);

INSERT INTO TB_BRAND (ID, FANTASY_NAME, CORPORATE_NAME, ACTIVE, CREATION_DATE, UPDATE_DATE)
VALUES(53, 'Test', 'Test', TRUE, CURRENT_TIMESTAMP, NULL);


INSERT INTO TB_PRODUCT (ID, NAME, DESCRIPTION, ID_BRAND, QUANTITY, VALUE, ACTIVE, CREATION_DATE, UPDATE_DATE)
VALUES (50, 'Controle Xbox 360', 'Cor branca.', 50, 20, 99.99, TRUE, CURRENT_TIMESTAMP, NULL);

INSERT INTO TB_PRODUCT (ID, NAME, DESCRIPTION, ID_BRAND, QUANTITY, VALUE, ACTIVE, CREATION_DATE, UPDATE_DATE)
VALUES (51, 'Controle Xbox 360', 'Cor branca.', 50, 20, 99.99, FALSE, CURRENT_TIMESTAMP, NULL);

INSERT INTO TB_PRODUCT (ID, NAME, DESCRIPTION, ID_BRAND, QUANTITY, VALUE, ACTIVE, CREATION_DATE, UPDATE_DATE)
VALUES (52, 'Controle Xbox 360', 'Cor branca.', 50, 20, 99.99, TRUE, CURRENT_TIMESTAMP, NULL);
