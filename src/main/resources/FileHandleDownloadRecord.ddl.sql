CREATE TABLE IF NOT EXISTS `FILE_HANDLE_DOWNLOAD_RECORD` (
  `TIMESTAMP` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `REQUESTED_FILE_HANDLE_ID` bigint(20) NOT NULL,
  `DOWNLOADED_FILE_HANDLE_ID` bigint(20) NOT NULL,
  `ASSOCIATION_OBJECT_ID` bigint(20) NOT NULL,
  `ASSOCIATION_OBJECT_TYPE` ENUM ('FileEntity', 'TableEntity', 'WikiAttachment', 'UserProfileAttachment', 'MessageAttachment', 'TeamAttachment', 'SubmissionAttachment', 'VerificationSubmission') NOT NULL,
  PRIMARY KEY (`TIMESTAMP`, `USER_ID`, `REQUESTED_FILE_HANDLE_ID`, `DOWNLOADED_FILE_HANDLE_ID`, `ASSOCIATION_OBJECT_ID`, `ASSOCIATION_OBJECT_TYPE`)
)
