CREATE TABLE IF NOT EXISTS `PROCESSED_ACCESS_RECORD` (
  `SESSION_ID` char(36) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  `TIMESTAMP` bigint NOT NULL,
  `ENTITY_ID` bigint(20) DEFAULT NULL,
  `CLIENT` ENUM('R','PYTHON','WEB', 'JAVA', 'COMMAND_LINE', 'ELB_HEALTHCHECKER', 'UNKNOWN') NOT NULL,
  `NORMALIZED_METHOD_SIGNATURE` varchar(100) CHARACTER SET latin1 COLLATE latin1_bin,
  PRIMARY KEY (`SESSION_ID`, `TIMESTAMP`),
  INDEX (ENTITY_ID),
  INDEX (CLIENT),
  INDEX (NORMALIZED_METHOD_SIGNATURE)
)
