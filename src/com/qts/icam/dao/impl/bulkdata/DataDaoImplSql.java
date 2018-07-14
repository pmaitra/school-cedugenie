package com.qts.icam.dao.impl.bulkdata;

public interface DataDaoImplSql {
	
	public static String insertForFunctionalityRoleMapping = "UPDATE functionality_role_mapping  SET "+
			"updated_by = (SELECT rec_id FROM resource WHERE user_id= ? AND is_active = true) , "+
			"updated_on = (SELECT extract(epoch FROM now())),  "+
			"date_of_creation = (SELECT extract(epoch FROM now())), "+
			"view = ?,  "+
			"write= ?,  "+
			"update = ? "+
			"WHERE "+
			"is_active =true "+
			"AND module_functionality =(SELECT rec_id FROM module_functionality WHERE functionality_name = ? AND is_active =true ) "+  
			"AND module = (SELECT rec_id FROM module WHERE module_code= ? AND is_active =true) "+
			"AND role = (SELECT rec_id FROM role WHERE role_name = ? AND is_active =true)";
	
	
	
	public static String checkAuthorFullName =
			"SELECT a.author_full_name "+
			"FROM author a "+
			"WHERE "+
			"a.is_active = true "+
			"AND a.author_full_name = ?";
	
	
	public static String batchInsertForBookAuthor =
			"INSERT INTO author(rec_id, obj_id, updated_by, updated_on, date_of_creation, is_active, author_full_name) "+
			"VALUES (uuid_generate_v4(), ?, (SELECT rec_id FROM resource WHERE user_id = ?), "+
			"(SELECT extract(epoch FROM now())), (SELECT extract(epoch FROM now())), "+
			"TRUE, ?); ";
	
	
	public static String checkBookPublisher =
			"SELECT bp.book_publisher_name "+
			"FROM book_publisher bp "+
	 		"WHERE "+
	 		"bp.is_active = true "+
			"AND bp.book_publisher_name = ? ";
	
	
	public static String batchInsertForBookPublisher =
			"INSERT INTO book_publisher(rec_id, obj_id, updated_by, updated_on, date_of_creation, is_active, "+
			"book_publisher_code, book_publisher_name, book_publisher_desc) VALUES (uuid_generate_v4(), "+
			"?, (SELECT rec_id FROM resource WHERE user_id = ?), (SELECT extract(epoch FROM now())), "+
			"(SELECT extract(epoch FROM now())), TRUE, " +
			"(SELECT ('BKPB-'||(select COALESCE((SELECT MAX(book_publisher_id) FROM book_publisher), 0 )+1))), ?, ?); ";
	
	
	public static String batchInsertForBookMedium =
			"INSERT INTO book_medium(rec_id, obj_id, updated_by, updated_on, date_of_creation, is_active, "+
			"book_medium_code, book_medium_name, book_medium_desc) VALUES (uuid_generate_v4(), "+
			"?, (SELECT rec_id FROM resource WHERE user_id = ?), "+
			"(SELECT extract(epoch FROM now())), (SELECT extract(epoch FROM now())), "+
			"TRUE, ?, ?, ?); ";
	
	
	public static String checkBookMedium =
			"SELECT bm.book_medium_name "+
			"FROM book_medium bm "+
			"WHERE "+
			"bm.is_active = true "+
			"AND "+
			"bm.book_medium_name = ?";
	
	
	public static String checkBookLanguage =
			"SELECT bl.book_language_name "+
			"FROM book_language bl "+
			"WHERE "+
			"bl.is_active = true "+
			"AND "+
			"bl.book_language_name = ?";
	
	
	public static String batchInsertForBookLanguage =
			"INSERT INTO book_language(rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
			"is_active, book_language_code, book_language_name, book_language_desc) VALUES "+
			"(uuid_generate_v4(), ?, (SELECT rec_id FROM resource WHERE user_id = ?), "+
			"(SELECT extract(epoch FROM now())), (SELECT extract(epoch FROM now())), "+
			"TRUE, ?, ?, ?); ";
	
	
	public static String checkBook = 
			"SELECT b.book_code "+
			"FROM book b "+
			"WHERE "+
			"b.is_active = true "+
			"AND "+
			"b.book_name ILIKE ? ";
	
	
	public static String batchInsertForBook =
		"INSERT INTO book(rec_id, obj_id, updated_by, updated_on, date_of_creation, is_active, "+
			"book_code, book_name, book_desc, " +
			"book_publisher, book_medium, book_isbn, book_edition, "+
			"book_place, book_periodicity, total_no_of_copies, " +
			"book_language, total_no_of_copies_available, "+
			"total_no_of_copies_lended, total_no_of_copies_reserved, total_no_of_copies_retired, "+
			"book_type, subject) " +
		"VALUES " +
			"(uuid_generate_v4(), ?, (SELECT rec_id FROM resource WHERE user_id = ?), "+
			"(SELECT extract(epoch FROM now())), (SELECT extract(epoch FROM now())), "+
			"TRUE, (SELECT ('BK-'||(select COALESCE((SELECT MAX(book_id) FROM book), 0 )+1))), ?, ?, "+
			"(SELECT rec_id FROM book_publisher WHERE book_publisher_name = ? AND is_active = TRUE), "+
			"(SELECT rec_id FROM book_medium WHERE book_medium_name = ? AND is_active = TRUE), ?, ?, ?, ?, ?, "+
			"(SELECT rec_id FROM book_language WHERE book_language_name = ? AND is_active = TRUE), ?, 0, 0, 0, ?, " +
			"(SELECT rec_id FROM subject WHERE subject_name = ? AND is_active = TRUE)); ";
	
	
	public static String batchInsertIndividualBook =
			"INSERT INTO book_id "+
				"(rec_id, obj_id, updated_by, updated_on, date_of_creation, is_active, "+
				"book, book_entry_date, sequence_no, book_operational_code, " +
				"book_lifecycle_code, price, accession_number, book_category)"+
			"VALUES ( "+
				"uuid_generate_v4(), "+
				"?, "+
				"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = TRUE), "+
				"(SELECT extract(epoch FROM now())), "+
				"(SELECT extract(epoch FROM now())), "+
				"TRUE, "+
				"(SELECT rec_id FROM book WHERE book_code = ? AND is_active = TRUE), "+
				"(SELECT extract(epoch FROM now())), "+
				"(SELECT (SELECT COALESCE((SELECT MAX(sequence_no) FROM book_id WHERE book = (SELECT rec_id FROM book WHERE book_code = ? AND is_active = TRUE)), 0 )+1)), "+
				"(SELECT rec_id FROM status_flag WHERE status_sym = 'AVLB'), "+
				"(SELECT rec_id FROM status_flag WHERE status_sym = 'PUB'), "+
				"?, ?, ?);";
	
	
	public static String checkBookAuthor = 
			"SELECT ba.obj_id "+
			"FROM book_author ba "+
			"JOIN book b ON (b.rec_id = ba.book) AND (b.is_active = TRUE) "+
			"JOIN author a ON (a.rec_id = ba.author) AND (a.is_active = TRUE) "+
			"WHERE "+
			"ba.is_active = TRUE "+
			"AND "+
			"b.book_code ILIKE ? "+
			"AND "+
			"a.author_full_name ILIKE ?; ";
	
	
	public static String batchInsertBookAuthor =
			"INSERT INTO book_author(rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
			"is_active, book, author) VALUES (uuid_generate_v4(), ?, (SELECT rec_id FROM resource WHERE user_id = ?), " +
			"(SELECT extract(epoch FROM now())), (SELECT extract(epoch FROM now())), "+
			"TRUE, (SELECT rec_id FROM book WHERE book_code = ? AND is_active = TRUE), " +
			"(SELECT rec_id FROM author WHERE author_full_name = ? AND is_active = TRUE)); ";
	
	
	public static String updateBook =
		"UPDATE book "+
		"SET "+ 
		    "updated_on = (SELECT extract(epoch FROM now())),"+
		    "total_no_of_copies = total_no_of_copies + ?,"+
		    "total_no_of_copies_available = total_no_of_copies_available + ? "+ 
		"WHERE book_code = ? "+
		"AND is_active = true;";
	
	
	public static String batchInsertForCommodity = 
			"INSERT INTO commodity( "+
	            "rec_id, obj_id, updated_by, updated_on, date_of_creation, is_active, "+
	            "commodity_code, commodity_name, commodity_desc, commodity_instock, "+
	            "is_horse_ration, threshold,ledger_number,page_number,commodity_sub_type) "+
			"VALUES (uuid_generate_v4(), ?, "+
			   "(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
			   "extract(epoch FROM now()), extract(epoch FROM now()), TRUE, "+
			   "?, ?, ?, ?, ?, ?, ?, ?, "+
			   "(SELECT "+
				"	cst.rec_id "+
				"FROM "+
				"	commodity_sub_type cst "+
				"JOIN "+
				"	commodity_type ct ON (ct.rec_id = cst.commodity_type) AND (ct.is_active = true) "+
				"WHERE "+
				"	ct.commodity_type_code = #{commodityType} "+
				"AND "+
				"	cst.commodity_sub_type_code = #{commoditySubType} "+
				"AND "+
				"	cst.is_active = true) "+
			   "); "+
			  " );";
	
	
	
	public static String batchInsertMessCommodity = 
		"INSERT INTO mess_commodity( "+
        	"rec_id, obj_id, updated_by, updated_on, date_of_creation, is_active, "+
        	"commodity) "+
    	"VALUES (uuid_generate_v4(), ?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
			"extract(epoch FROM now()), extract(epoch FROM now()), TRUE, "+
			"(SELECT rec_id FROM commodity WHERE commodity_name = ? AND is_active = true));";
	
	
	public static String batchInsertResourceDetails =
		"INSERT INTO resource( "+
            "rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
            "resource_type, gender, user_id, "+
            "first_name, middle_name, last_name, date_of_birth, "+
            "father_first_name, father_middle_name, father_last_name, father_occupation, "+
            "mother_first_name, mother_middle_name, mother_last_name, mother_occupation, "+
            "email_address, mobile_no, blood_group, mother_tongue, religion, nationality, social_category, "+
            "passport_id, voter_id, pan_no, aadhar_card_no, voting_constituency, parliamentary_constituency, "+         
            "bank_name, branch, bank_account_number, account_type, account_holder_name, medical_status) "+
   		"VALUES ((select uuid_generate_v4()), "+
    		"?, "+
    		"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
    		"(SELECT extract(epoch FROM now())), "+
            "(SELECT extract(epoch FROM now())), "+
            "(SELECT rec_id from resource_type WHERE resource_type_name = ? AND is_active = true), "+
            "?, "+
            "LOWER(?), "+
            "?, "+
            "?, "+
            "?, "+
            "(SELECT extract(epoch from (SELECT to_timestamp(?,'DD-MM-YYYY')))), "+
            "?, "+
            "?, "+
            "?, "+
            "?, "+ 
            "?, "+
            "?, "+
            "?, "+
            "?, "+            
            "?, "+
            "?, "+            
            "?, "+
            "?, "+
            "?, "+
            "?, "+                        
            "(SELECT rec_id FROM social_category WHERE social_category_name = ? AND is_active = true), "+            
            "?, "+
            "?, "+
            "?, "+
            "?, "+
            "?, "+
            "?, "+
            "?, "+
            "?, "+
            "?, "+
            "?, "+
            "?, "+
            "?);";


	public static String batchInsertEmployeeDetails =
		"INSERT INTO employee_details( "+
            "rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
            "erp_code, doj, date_of_retirement, job_type, designation, department, "+ 	             
            "qualification_summary, marital_status, spouse_name, resource) "+
      	"VALUES ((select uuid_generate_v4()), "+
    		"'EMP-DET-OBJ', "+
    		"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
    		"(SELECT extract(epoch FROM now())), "+
            "(SELECT extract(epoch FROM now())), "+
            "?, "+
            "(SELECT extract(epoch from (SELECT to_timestamp(?, 'DD-MM-YYYY')))), "+
            "(SELECT extract(epoch from (SELECT to_timestamp(?, 'DD-MM-YYYY')))), "+
            "(SELECT rec_id from job_type WHERE job_type_name = ? AND is_active = true), "+
            "(SELECT rec_id from designation WHERE designation_name = ? AND is_active = true), "+
            "(SELECT rec_id from department WHERE department_name = ? AND is_active = true), "+       
            "?, "+
            "?, "+    
            "?, "+  
            "(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true));";
	
	
	public static String batchInsertForCity =
		"INSERT INTO city( "+
			"rec_id, obj_id, updated_by, updated_on, "+ 
			"date_of_creation, city_code, city_name) "+
		"VALUES (uuid_generate_v4(), "+
			"?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+ 
			"(SELECT extract(epoch FROM now())), (SELECT extract(epoch FROM now())), "+
			"?, ?);";
	
	
	public static String batchInsertForDistrict =
		"INSERT INTO district( "+
			"rec_id, obj_id, updated_by, updated_on, "+ 
			"date_of_creation, district_code, district_name) "+
		"VALUES (uuid_generate_v4(), "+
			"?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+  
			"(SELECT extract(epoch FROM now())), (SELECT extract(epoch FROM now())), "+
			"?, ?);";
	
	
	public static String batchInsertEmployeeAddress =
		"INSERT INTO address( "+
			"rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
			"address_code, address_line, land_mark, city, district, postal_code, "+
			"state, country, police_station, post_office, "+
			"address_type, railway_station, phone, resource) "+
		"VALUES ( "+
			"(select uuid_generate_v4()), "+
			"?, "+	
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
			"(SELECT extract(epoch FROM now())), "+
			"(SELECT extract(epoch FROM now())), "+
			"?, "+
			"?, "+
			"?, "+
			"(SELECT rec_id FROM city WHERE city_name = ? AND is_active = true), "+
			"(SELECT rec_id FROM district WHERE district_name = ? AND is_active = true), "+
			"?, "+
			"(SELECT rec_id FROM state WHERE state_name = ? AND is_active = true), "+
			"(SELECT rec_id FROM country WHERE country_name = ? AND is_active = true), "+
			"?, "+
			"?, "+
			"?, "+
			"?, "+
			"?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true)"+
		");";
	
	
	public static String batchInsertEmployeeQualification =
		"INSERT INTO qualification( "+
			"rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
        	"qualification_code, qualification_name, qualification_desc, "+
        	"year_of_passing, school_college_name, university_name, "+
        	"resource, subject_specilization, percent_of_marks_obtained, qualification_type) "+
		"VALUES ( "+
			"(SELECT uuid_generate_v4()), "+
			"?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
			"(SELECT extract(epoch FROM now())), "+
			"(SELECT extract(epoch FROM now())), "+
			"(SELECT ('QUALI-' ||(select COALESCE((SELECT MAX(qualification_id) FROM qualification), 0 )+1))), "+
			"?, "+
			"?, "+
			"?, "+
			"?, "+
			"?, "+			
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
			"?, "+
			"?, "+
			"(SELECT rec_id FROM qualification_type WHERE qualification_type_name = ? AND is_active = true)); ";
	
	
	public static String batchInsertForEmployeeOrganization =
		"INSERT INTO organization( "+
       	 	"rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
        	"organization_code, organization_name, organization_desc, "+
        	"organization_website, school_contact_number, " +
        	"from_date, to_date, resource) "+
	    "VALUES ( "+
			"(SELECT uuid_generate_v4()), "+
			"?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
			"(SELECT extract(epoch FROM now())), "+
			"(SELECT extract(epoch FROM now())),	"+	  
			"(SELECT ('ORG-' ||(select COALESCE((SELECT MAX(organization_id) FROM organization), 0 )+1))), "+
			"?, "+
			"?, "+
			"?, "+
			"?, "+
			"?, "+
			"?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true));";
	
	
	public static String batchInsertForEmployeePublication =
		"INSERT INTO publication( "+
            "rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
            "publication_code, publication_name, publication_desc, " +
            "co_publisher, date_of_publication, resource) "+
		"VALUES ( "+
			"(SELECT uuid_generate_v4()), "+
			"?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
			"(SELECT extract(epoch FROM now())), "+
			"(SELECT extract(epoch FROM now())), "+
			"(SELECT ('PUB-' ||(select COALESCE((SELECT MAX(publication_id) FROM publication), 0 )+1))), "+
			"?, "+
			"?, "+
			"?, "+
			"?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true));";
	
	
	public static String batchInsertForEmployeeDependent =
		"INSERT INTO employee_dependents( "+
            "rec_id, obj_id, updated_by, updated_on, date_of_creation, employee_dependents_code, "+
            "child_name, child_gender, child_date_of_birth, resource) "+
		"VALUES ((SELECT uuid_generate_v4()), "+
			"?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
			"(SELECT extract(epoch FROM now())), "+
			"(SELECT extract(epoch FROM now())), "+
            "(SELECT ('EMP_DEP-' ||(select COALESCE((SELECT MAX(employee_dependents_id) FROM employee_dependents), 0 )+1))), "+
            "?, "+
            "?, "+
            "(SELECT extract(epoch from (SELECT to_timestamp(?,'DD-MM-YYYY')))), "+
            "(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true));";
	
	
	public static String checkCity =
		"SELECT city_name "+
		"FROM city "+
		"WHERE "+
			"is_active = true "+
		"AND "+
			"city_name = ?";
	
	
	public static String checkDistrict =
		"SELECT district_name "+
		"FROM district "+
		"WHERE "+
			"is_active = true "+
		"AND "+
			"district_name = ?";
	

public static String batchInsertStandardSubjectMapping = 
		"INSERT INTO standard_subject_mapping( "+
			"rec_id, obj_id, updated_by, updated_on, "+
			"date_of_creation, standard, subject) "+
		"VALUES ((select uuid_generate_v4()), ?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
			"(SELECT extract(epoch FROM now())),(SELECT extract(epoch FROM now())), "+
			"(SELECT rec_id FROM standard WHERE standard_name = ? AND is_active = true), "+
			"(SELECT rec_id FROM subject WHERE subject_name = ? AND is_active = true));";



public static String batchInsertStudentSubjectMapping =
		"INSERT INTO student_subject_mapping( "+
			"rec_id, obj_id, updated_by, updated_on, "+
			"date_of_creation, student, subject) "+
		"VALUES ((select uuid_generate_v4()), ?, "+
			"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
			"(SELECT extract(epoch FROM now())),(SELECT extract(epoch FROM now())), "+
			"(SELECT rec_id FROM resource WHERE roll_number = ? AND is_active = true), "+
			"(SELECT rec_id FROM subject WHERE subject_name = ? AND is_active = true));";

public static String batchInsertStudentDetails =
"INSERT INTO resource( "+
	"rec_id, obj_id, updated_by, updated_on, date_of_creation, is_active, "+
	"resource_type, gender, date_of_admission, roll_number, "+
	"first_name, middle_name, last_name, date_of_birth, father_first_name, "+
	"father_middle_name, father_last_name, father_annual_income, "+
	"mother_first_name, mother_middle_name, mother_last_name, "+
	"mother_annual_income, guardian_first_name, guardian_middle_name, "+
	"guardian_last_name, email_address, blood_group, mother_tongue, "+
	"religion, nationality, social_category, section, hostel, state_of_domicile, "+
	"bank_name, branch, bank_account_number, scholarship, father_in_defence, "+
	"father_service_status, father_defence_category, father_rank, "+
	"father_mobile, father_email, mother_mobile, mother_email, student_income, "+
	"family_income, guardian_mobile, guardian_email, previous_scholl_phone, "+
	"previous_school_website, previous_school_email, previous_school_address,"+
	"previous_school, user_id, medical_status) "+
"VALUES ((select uuid_generate_v4()), " +
	"?, "+
	"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
	"(SELECT extract(epoch FROM now())),(SELECT extract(epoch FROM now())), true, "+
	"(SELECT rec_id FROM resource_type WHERE resource_type_code = 'STUDENT' AND is_active = true), "+
	"?, (SELECT extract(epoch from (SELECT to_timestamp(?,'DD/MM/YYYY')))), "+
	"?, ?, ?, ?, "+
	"(SELECT extract(epoch from (SELECT to_timestamp(?,'DD/MM/YYYY')))), "+
	"?, ?, ?, ?, "+
	"?, ?, ?, ?, "+
	"?, ?, ?, "+
	"?, ?, ?, ?, ?, "+
	"(SELECT rec_id FROM social_category WHERE social_category_name = ? AND is_active = true), "+
	"(SELECT rec_id FROM section WHERE standard = (SELECT rec_id FROM standard WHERE standard_name = ?) AND section_name = ? AND is_active = true), "+
	"(SELECT rec_id FROM hostel WHERE hostel_name = ? AND is_active = true), ?, ?, ?, "+
	"?, (SELECT rec_id FROM scholarship WHERE scholarship_name = ? AND is_active = true), ?, "+
	"?, ?, ?, ?, ?, "+
	"?, ?, ?, ?, ?, ?, ?, "+
	"?, ?, ?, ?, (?::text), ?);";


public static String batchInsertUsedRollNumber = "INSERT INTO used_roll_number_and_user_id(roll_number) VALUES (?);";
public static String batchInsertStudentAddress =
"INSERT INTO address( "+
	"rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
	"address_code, address_line, land_mark, city, district, postal_code, "+
	"state, country, police_station, post_office, "+
	"address_type, railway_station, phone, resource) "+
"VALUES ( "+
	"(select uuid_generate_v4()), "+
	"?, "+	
	"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
	"(SELECT extract(epoch FROM now())), "+
	"(SELECT extract(epoch FROM now())), "+
	"?, "+
	"?, "+
	"?, "+
	"(SELECT rec_id FROM city WHERE city_name = ? AND is_active = true), "+
	"(SELECT rec_id FROM district WHERE district_name = ? AND is_active = true), "+
	"?, "+
	"(SELECT rec_id FROM state WHERE state_name = ? AND is_active = true), "+
	"(SELECT rec_id FROM country WHERE country_name = ? AND is_active = true), "+
	"?, "+
	"?, "+
	"?, "+
	"?, "+
	"?, "+
	"(SELECT rec_id FROM resource WHERE roll_number = ? AND is_active = true)"+
");";


public static String batchInsertCBSEMarks =
	"INSERT INTO student_marks( "+
	"rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
	"student_roll, student_name, year, standard, section, "+
	"exam, subject, theory, theory_pass, practical, practical_pass, total, pass, theory_obtained, "+
	"practical_obtained, total_obtained, pass_fail, weightage, weightage_obtained, inserted_by) "+
	"VALUES ((select uuid_generate_v4()), ?, "+
	"(SELECT rec_id FROM resource WHERE user_id =? AND is_active = true), "+
	"(SELECT extract(epoch FROM now())),(SELECT extract(epoch FROM now())), ?, "+
	"(SELECT first_name || ' ' || COALESCE(middle_name,'') || ' ' || COALESCE(last_name,'') FROM resource WHERE roll_number=?::integer AND is_active=true), "+
	"(SELECT academic_year_name FROM academic_year WHERE is_active = true AND year_status = (SELECT rec_id FROM status_flag WHERE status_sym = 'C')), "+
	"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "+
	"(SELECT weightage FROM exam WHERE exam_code =? AND is_active = true), "+
	"(? * (SELECT weightage FROM exam WHERE exam_code =? AND is_active = true)), ?); ";

public static String checkForExistingCBSEMarks =
	"select count(*) as num FROM student_marks "+
	"WHERE student_roll=? AND standard=? AND section=? AND exam=? AND subject=? ";

public static String batchUpdateCBSEMarks =
	"UPDATE student_marks SET "+
	"updated_by =(SELECT rec_id FROM resource WHERE user_id =? AND is_active = true), "+
	"updated_on=(SELECT extract(epoch FROM now())), theory=?, practical=?, "+
	"total=?, pass=?, theory_obtained=?, "+
	"practical_obtained=?, total_obtained=?, "+
	"pass_fail=?, theory_pass=?, practical_pass=?, "+
	"weightage=(SELECT weightage FROM exam WHERE exam_code =? AND is_active = true), "+
	"weightage_obtained=(?*(SELECT weightage FROM exam WHERE exam_code =? AND is_active = true)) "+
	"WHERE student_roll=? AND standard=? AND "+
	"section=? AND exam=? AND subject=?; ";


public static String batchInsertUserMarks =
	"INSERT INTO student_marks_user_exam( "+
	"rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
	"student_roll, student_name, year, standard, section, "+
	"exam, subject, theory, theory_pass, practical, practical_pass, total, pass, theory_obtained, "+
	"practical_obtained, total_obtained, pass_fail, inserted_by) "+
	"VALUES ((select uuid_generate_v4()), ?, "+
	"(SELECT rec_id FROM resource WHERE user_id =? AND is_active = true), "+
	"(SELECT extract(epoch FROM now())),(SELECT extract(epoch FROM now())), ?, "+
	"(SELECT first_name || ' ' || COALESCE(middle_name,'') || ' ' || COALESCE(last_name,'') FROM resource WHERE roll_number=?::integer AND is_active=true), "+
	"(SELECT academic_year_name FROM academic_year WHERE is_active = true AND year_status = (SELECT rec_id FROM status_flag WHERE status_sym = 'C')), "+
	"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

public static String checkForExistingUserMarks =
	"select count(*) as num FROM student_marks_user_exam "+
	"WHERE student_roll=? AND standard=? AND section=? AND exam=? AND subject=? ";

public static String batchUpdateUserMarks =
	"UPDATE student_marks_user_exam SET "+
	"updated_by =(SELECT rec_id FROM resource WHERE user_id =? AND is_active = true), "+
	"updated_on=(SELECT extract(epoch FROM now())), theory=?, practical=?, "+
	"total=?, pass=?, theory_obtained=?, "+
	"practical_obtained=?, total_obtained=?, "+
	"pass_fail=?, theory_pass=?, practical_pass=?, "+
	"WHERE student_roll=? AND standard=? AND "+
	"section=? AND exam=? AND subject=?; ";


public static String checkAssetName =
	"SELECT "+
		"asset_name "+
	"FROM "+
		"asset "+
	"WHERE "+
		"asset_name = ? "+
	"AND "+
		"is_active = true; ";


public static String batchInsertAssetDetails =
	"INSERT INTO asset( "+
	    "rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
	    "asset_name, asset_code, department, asset_property, asset_price, "+
	    "purchase_date, ledger_number, page_number, ledger_balance, asset_sub_type, asset_unit) "+
	"VALUES ( "+
		"(select uuid_generate_v4()), "+
		"?, "+
		"(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true), "+
	  	"extract(epoch FROM now()), "+
	  	"extract(epoch FROM now()), "+
	  	"?, "+
	  	"(SELECT 'AST-' ||COALESCE((SELECT MAX(asset_id) FROM asset), 0 )+1), "+
	    "(SELECT rec_id FROM department WHERE department_name = ? AND is_active = true), "+
	    "?, "+
	    "?, "+
	    "(SELECT extract(epoch from (SELECT to_timestamp(?,'DD-MM-YYYY')))), "+
	    "?, "+
	    "?, "+
	    "?, "+
        "(SELECT rec_id FROM asset_sub_type WHERE asset_sub_type_name = ? AND asset_type = (SELECT rec_id FROM asset_type WHERE asset_type_name = ? AND is_active = true) AND is_active = true), "+
        "? ); ";

public static String GetShiftDetails = "SELECT shift_code, shift_start_time, shift_end_time FROM \"Shift\" s "+
		"JOIN \"Status_Flag\" sf ON (s.active = sf.rec_id) WHERE sf.status_sym='A';";


public static String InsertTeacherAttendance =
		"INSERT INTO teacher_attendance_details("+
		"rec_id, obj_id, updated_by, updated_on, date_of_creation, "+
		"date_of_attendance, resource,month,year)"+ 
		"VALUES ((uuid_generate_v4()),"+
		"?,(SELECT rec_id FROM resource WHERE user_id = ? AND is_active = true),"+
		"(SELECT extract(epoch FROM now())),"+
		"(SELECT extract(epoch FROM now())),"+
		"(SELECT extract(epoch from (SELECT to_timestamp(?,'DD/MM/YYYY'))))," +
		"(SELECT r.rec_id FROM resource r JOIN resource_type rt ON (r.resource_type = rt.rec_id AND rt.is_active = true) WHERE r.is_active = true AND rt.resource_type_name = ? AND r.user_id = ? )" +
		",?,?);";

public static String GetAssignedShiftForResource =
		"SELECT distinct sh.shift_code FROM \"Time_Table\" tt JOIN \"Status_Flag\" sf ON (tt.active = sf.rec_id) "+ 
		"JOIN \"Time_Table_Details\" ttd ON(tt.rec_id = ttd.time_table) AND (ttd.active = sf.rec_id) "+ 
		"JOIN \"Class\" c ON(tt.class = c.rec_id) AND (c.active = sf.rec_id) "+ 
		"JOIN \"Stream\" s ON(tt.stream = s.rec_id) AND (s.active = sf.rec_id) "+ 
		"JOIN \"Course\" cr ON(cr.active = sf.rec_id) AND (cr.course_class = c.rec_id) AND (tt.course = cr.rec_id) "+ 
		"JOIN \"Class_Stream_Mapping\" csm ON(csm.class = c.rec_id) AND (csm.stream = s.rec_id) "+ 
		"JOIN \"Section\" sec ON(tt.section = sec.rec_id) AND (sec.active = sf.rec_id) AND (sec.class_stream_mapping = csm.rec_id) "+ 
		"JOIN \"Shift\" sh ON(tt.shift = sh.rec_id) AND (sh.active = sf.rec_id) "+ 
		"JOIN \"Academic_Year\" ay ON (ay.active = sf.rec_id) AND (ay.rec_id = tt.academic_year) "+ 
		"JOIN \"Status_Flag\" sf1 ON (sf1.rec_id = ay.year_status) "+ 
		"LEFT OUTER JOIN \"Resource\" rs ON (ttd.resource = rs.rec_id) AND (rs.active = sf.rec_id) "+ 
		"WHERE sf.status_sym='A' AND sf1.status_sym = 'CY' AND rs.user_id = ?;";	

}



