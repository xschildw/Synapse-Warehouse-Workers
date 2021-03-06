package org.sagebionetworks.warehouse.workers.snapshot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sagebionetworks.repo.model.audit.ObjectRecord;
import org.sagebionetworks.repo.model.quiz.MultichoiceQuestion;
import org.sagebionetworks.repo.model.quiz.PassingRecord;
import org.sagebionetworks.repo.model.quiz.Question;
import org.sagebionetworks.repo.model.quiz.ResponseCorrectness;
import org.sagebionetworks.schema.adapter.JSONObjectAdapterException;
import org.sagebionetworks.schema.adapter.org.json.EntityFactory;
import org.sagebionetworks.warehouse.workers.model.CertifiedQuizRecord;

public class CertifiedQuizRecordWorkerTest {

	CertifiedQuizRecordWorker worker;

	@Before
	public void before() {
		worker = new CertifiedQuizRecordWorker(null, null, null);
	}

	@Test
	public void testConvertValidRecord() throws JSONObjectAdapterException {
		ObjectRecord record = new ObjectRecord();
		record.setTimestamp(System.currentTimeMillis());
		record.setJsonClassName(PassingRecord.class.getSimpleName().toLowerCase());
		Long responseId = 123L;
		Long questionIndex = 456L;
		Boolean isCorrect = false;
		PassingRecord passingRecord = new PassingRecord();
		passingRecord.setResponseId(responseId);
		ResponseCorrectness correctness = new ResponseCorrectness();
		Question question = new MultichoiceQuestion();
		question.setQuestionIndex(questionIndex);
		correctness.setIsCorrect(isCorrect);
		correctness.setQuestion(question);
		passingRecord.setCorrections(Arrays.asList(correctness));
		passingRecord.setUserId("1");
		passingRecord.setPassedOn(new Date());
		passingRecord.setPassed(true);
		record.setJsonString(EntityFactory.createJSONStringForEntity(passingRecord));
		List<CertifiedQuizRecord> actual = worker.convert(record);
		assertNotNull(actual);
		assertEquals(actual.size(), 1);
	}

	@Test
	public void testConvertInvalidRecord() throws JSONObjectAdapterException {
		assertNull(worker.convert(null));
	}
}
