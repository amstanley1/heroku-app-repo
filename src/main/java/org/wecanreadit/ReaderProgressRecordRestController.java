package org.wecanreadit;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReaderProgressRecordRestController {

	@Resource
	ReaderProgressRecordRepository readerProgressRecordRepo;

	@Resource
	ReaderRepository readerRepo;

	@Resource
	GroupBookRepository groupBookRepo;

	@DeleteMapping("/api/deleteReaderProgressRecord")
	public String deleteReaderBookProgress(@RequestParam long readerProgressRecordId) {
		Optional<ReaderProgressRecord> readerProgressRecord = readerProgressRecordRepo.findById(readerProgressRecordId);
		ReaderProgressRecord readerProgressResult = readerProgressRecord.get();
		long readerId = readerProgressResult.getReader().getId();
		readerProgressRecordRepo.delete(readerProgressResult);
		return "redirect:/reader?id=" + readerId;
	}

	@PutMapping("/api/updateFinishedDate")
	public ReaderProgressRecord updateReaderProgressDate(@RequestBody UpdatedDateFinished updatedDateFinished) {
		Optional<ReaderProgressRecord> readerProgressRecord = readerProgressRecordRepo
				.findById(updatedDateFinished.readerProgressRecordId);
		ReaderProgressRecord readerProgressResult = readerProgressRecord.get();
		readerProgressResult.setDateFinished(updatedDateFinished.monthFinished, updatedDateFinished.dayOfMonthFinished,
				updatedDateFinished.yearFinished);
		return readerProgressRecordRepo.save(readerProgressResult);

	}

	@PutMapping("/addReaderProgressRecord")
	public ReaderProgressRecord addReaderProgressRecord(@CookieValue(value = "readerId") long readerId, @RequestBody NewReaderProgressRecord newReaderProgressRecord) {
		Optional<Reader> reader = readerRepo.findById(readerId);
		Reader readerResult = reader.get();
		Optional<GroupBook> groupBook = groupBookRepo.findById(newReaderProgressRecord.groupBookId);
		GroupBook groupBookResult = groupBook.get();
		Collection<ReaderProgressRecord> readerProgressRecords = readerResult.getReaderProgressRecords();
		for (ReaderProgressRecord readerProgressRecord : readerProgressRecords) {
			if (readerProgressRecord.getGroupBook().equals(groupBookResult)) {
				return null;
			}
		}
		ReaderProgressRecord readerProgressRecord = new ReaderProgressRecord(groupBookResult, readerResult,
				newReaderProgressRecord.monthFinished, newReaderProgressRecord.dayOfMonthFinished,
				newReaderProgressRecord.yearFinished);
		readerProgressRecordRepo.save(readerProgressRecord);
		return readerProgressRecord;

	}

	public static class UpdatedDateFinished {
		public int monthFinished;
		public int dayOfMonthFinished;
		public int yearFinished;
		public long readerProgressRecordId;
	}

	public static class NewReaderProgressRecord {
		public long groupBookId;
		public int monthFinished;
		public int dayOfMonthFinished;
		public int yearFinished;
		public long readerId;
	}

}
