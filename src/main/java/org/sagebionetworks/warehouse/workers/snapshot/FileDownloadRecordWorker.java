package org.sagebionetworks.warehouse.workers.snapshot;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.sagebionetworks.repo.model.audit.ObjectRecord;
import org.sagebionetworks.warehouse.workers.collate.StreamResourceProvider;
import org.sagebionetworks.warehouse.workers.db.snapshot.FileDownloadRecordDao;
import org.sagebionetworks.warehouse.workers.model.FileDownload;
import org.sagebionetworks.warehouse.workers.model.SnapshotHeader;
import org.sagebionetworks.warehouse.workers.utils.FileDownloadRecordUtils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.google.inject.Inject;

public class FileDownloadRecordWorker extends AbstractSnapshotWorker<ObjectRecord, FileDownload> {

	public static final String TEMP_FILE_NAME_PREFIX = "collatedFileDownloadRecord";
	public static final String TEMP_FILE_NAME_SUFFIX = ".csv.gz";

	@Inject
	public FileDownloadRecordWorker(AmazonS3Client s3Client, FileDownloadRecordDao dao,
			StreamResourceProvider streamResourceProvider) {
		super(s3Client, dao, streamResourceProvider);
		log = LogManager.getLogger(FileDownloadRecordWorker.class);
		tempFileNamePrefix = TEMP_FILE_NAME_PREFIX;
		tempFileNameSuffix = TEMP_FILE_NAME_SUFFIX;
		snapshotHeader = SnapshotHeader.OBJECT_RECORD_HEADERS;
		clazz = ObjectRecord.class;
	}

	@Override
	public List<FileDownload> convert(ObjectRecord record) {
		List<FileDownload> records = FileDownloadRecordUtils.getFileDownloadRecordsForFileDownloadRecord(record);
		if (!FileDownloadRecordUtils.isValidFileDownloadRecords(records)) {
			log.error("Invalid FileDownloadRecord from Record: "+ (record == null ? "null" : record.toString()));
			return null;
		}
		return records;
	}
}
