package com.qts.icam.dao.admission;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qts.icam.model.admission.AdmissionDrive;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.admission.AdmissionProcess;
import com.qts.icam.model.admission.LocationDetailsForPortal;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.AdmissionFormForPortalStudents;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Data;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.ProgrammeDetailsForPortal;
import com.qts.icam.model.common.Storage;

public interface AdmissionDAO {

	public List<AdmissionForm> getAdmissionDriveList(String strDrive);

	public List<Storage> getAttachmentList(String folderName);
	
	public List<AdmissionForm> getAdmissionFormList();

	public List<AdmissionProcess> insertAdmissionForm(
			List<AdmissionProcess> formSubmissionList,
			AdmissionProcess formSubmissionOtherInformation);

	public List<AdmissionProcess> insertInterviewSchedule(
			AdmissionProcess interviewschedule);

	public List<AdmissionProcess> getInterviewScheduleList(AdmissionProcess admissionProcess);

	public List<AdmissionProcess> getGeneratedFormIdList(
			AdmissionProcess formSubmission);

	public String getCurrentAdmissionOnProcessStatus(
			AdmissionProcess admissionProcess);

	public List<AdmissionProcess> insertResultDetails(
			AdmissionProcess interviewResultToDB);

	public List<AdmissionProcess> getInterviewedCandidateList(
			AdmissionProcess interviewResult);

	public List<AdmissionProcess> updateResultDetails(
			AdmissionProcess interviewResultToDB);

	public AdmissionForm getLastFormId(AdmissionForm admissionFormName);

	public List<AdmissionForm> insertAdmissionDrive(AdmissionForm admissionForm);

	public String insertAdmissionForm(ArrayList<AdmissionForm> admissionFormList);

	public List<AdmissionProcess> getCourseClasses();

	public AdmissionProcess getFormDetails(
			AdmissionProcess submitAdmissionFormForEdit);

	public List<AdmissionProcess> updateAdmissionForm(
			List<AdmissionProcess> formsubmissionList,
			AdmissionProcess formSubmission);

	public AdmissionProcess getInterviewSchedule(
			AdmissionProcess admissionProcess);

	public AdmissionProcess getInterviewScheduleForEdit(
			AdmissionProcess admissionProcess);

	public List<AdmissionProcess> updateInterviewSchedule(
			AdmissionProcess interviewscheduleToUpadate);

	public AdmissionProcess getInterviewResultForEdit(
			AdmissionProcess admissionProcess);

	public List<AdmissionProcess> getInterviewResultForViewMeritList(
			AdmissionProcess interviewResult);

	public List<AdmissionProcess> getRequiredMeritList(
			AdmissionProcess admissionProcess);

	public List<AdmissionProcess> insertSelectedCandidate(
			AdmissionProcess interviewResultToUpdate);

	public List<AdmissionProcess> getRequiredCandidates(
			AdmissionProcess admissionProcess);

	public AdmissionProcess getPaymentList(AdmissionProcess interviewResultToDAO);

	public List<AdmissionForm> getAdmissionsOnProcessList();

	public List<AdmissionProcess> getScheduledFormList(
			AdmissionProcess admissionProcess);

	public List<AdmissionProcess> goBackFromReview(
			AdmissionProcess admissionProcess);

	public String getFullName(AdmissionProcess admissionProcess);

	public void admitStudents(AdmissionProcess admissionProcess);

	public List<AdmissionProcess> getPaymentListToDisplayBack(
			AdmissionProcess admissionProcess);

	public List<AdmissionProcess> getAdmissionFormList(
			AdmissionProcess formSubmission);

	public List<AdmissionProcess> getSearchList(
			AdmissionProcess searchingParameter);

	public List<AdmissionProcess> getHistorySearchList(
			AdmissionProcess searchingParameter);

	public List<AdmissionForm> admissionDriveSearch(
			AdmissionForm searchingParameter);

	public List<AdmissionProcess> getPreviousAdmissionList(
			AdmissionProcess admissionProcess);

	public AdmissionForm getAdmissionForm(AdmissionForm admissionForm);

	public List<AdmissionForm> getAdmittedDriveList();

	public List<AdmissionProcess> admittedDriveListDetails(
			AdmissionProcess admissionProcess);

	public AcademicYear getYearClassList();

	public AdmissionForm getFormDetails(AdmissionForm af);

	public AcademicYear submitSaleFormDetails(AdmissionForm admissionForm);

	public AdmissionForm getStreamClassYearCourseDetails(
			AdmissionProcess formSubmission);

	public List<AdmissionForm> getActiveAdmissionDrives();

	public int checkOpeningAdmissionDriveStatus(AdmissionForm admissionForm);

	public List<AdmissionProcess> admittedDriveListDetailsSearch(AdmissionProcess searchingParameter);

	public List<AdmissionForm> getAdmissionFormDetailsSearch(Map<String, Object> parameters);

	public List<AdmissionForm> getAdmissionsOnProcessSearch(Map<String, Object> parameters);

	public List<AdmissionForm> getActiveAdmissionDriveSearch(Map<String, Object> parameters);

	public List<LoggingAspect> getAdmissionPaymentDateSetupLogDetails(
			String admissonDriveName);

	public List<LoggingAspect> getAdmissionInterviewResultLogDetails(
			String admissonDriveName);

	public List<LoggingAspect> getAdmissionScheduleInterviewLogDetails(
			String admissonDriveName);

	public List<LoggingAspect> getSaleFormLogDetails();

	public List<LoggingAspect> getAdmissionDriveLogDetails();

	public List<AdmissionProcess> getSubmittedFormDetails(
			AdmissionProcess admissionProcess);

	public List<AdmissionProcess> getinterviewScheduledList(
			AdmissionProcess admissionProcess);

	public List<AdmissionProcess> getSelectedReviewdNonselectedStudentList(
			AdmissionProcess admissionProcess);

	public List<AdmissionProcess> getResultForViewMeritList(
			AdmissionProcess interviewResultToDB);

	public String getAdmissionDriveState(String strDriveName);

	public String getstatusOfAdmissionDrive(AdmissionProcess admissionProcess);

	public List<AdmissionProcess> getAllStudentForMeritList(
			AdmissionProcess interviewResult);

	public String updadateAdmissonDriveStatus(String driveName);
	
	public String saveCandidateDetailsFromPortal(AdmissionFormForPortalStudents candidate, String userId);

	public String saveScrutinizedCandidatesFromPortal(AdmissionFormForPortalStudents candidate, String userId);

	public String saveSelectedCandidatesFromPortal(AdmissionFormForPortalStudents candidate, String userId);

	public List<Data> setProgrammeDetailsListForPortal();

	public List<LocationDetailsForPortal> setLocationDetailsForPortal();

	public String saveAdmittedCandidatesFromPortal(AdmissionFormForPortalStudents candidate, String userId);

	public List<Data> setAlumniDataToPortal();

	public ProgrammeDetailsForPortal setProgrammeDetailsForPortal(String courseCode);

	public List<Course> getUnsendProgramListToPortal();

	public String updateProgramStatus(String courseCode);

	public List<LocationDetailsForPortal> setInterviewLocationDetailsToPortal(String courseCode);

	public List<NoticeBoard> getNoticeList();

	public List<Course> getUnsendProgramsWithLocations();

	public List<Course> getDriveListForStudents();

	public String getDrivesAgainstCourse(String course);

	public String getDriveWithUnsentLocationAgainstCourse(String course);

	public String updateLocationSendStatusForDrive(String drive);
}
