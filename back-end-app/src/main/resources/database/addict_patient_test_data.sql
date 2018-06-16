insert into patient(patient_first_name, patient_last_name, patient_id) values ("Zavisnik", "Zavisnikic", 6);
insert into chart(chart_id, patient_patient_id) values (6, 6);


insert into examination(exam_id, exam_date, disease_disease_id, doctor_doctor_licence_id, prescription_prescription_id) values  (61, "2017-02-01", 3, 1, 2);
insert into examination(exam_id, exam_date, disease_disease_id, doctor_doctor_licence_id, prescription_prescription_id) values  (62, "2016-02-15", 4, 2, 7);
insert into examination(exam_id, exam_date, disease_disease_id, doctor_doctor_licence_id, prescription_prescription_id) values  (63, "2017-03-01", 9, 3, 10);
insert into examination(exam_id, exam_date, disease_disease_id, doctor_doctor_licence_id, prescription_prescription_id) values  (64, "2017-03-15", 3, 2, 7);
insert into examination(exam_id, exam_date, disease_disease_id, doctor_doctor_licence_id, prescription_prescription_id) values  (65, "2017-04-01", 4, 3, 10);
insert into examination(exam_id, exam_date, disease_disease_id, doctor_doctor_licence_id, prescription_prescription_id) values  (66, "2017-04-15", 9, 1, 2);
insert into examination(exam_id, exam_date, disease_disease_id, doctor_doctor_licence_id, prescription_prescription_id) values  (67, "2018-05-01", 3, 3, 10);
insert into examination(exam_id, exam_date, disease_disease_id, doctor_doctor_licence_id, prescription_prescription_id) values  (68, "2018-05-15", 4, 1, 2);
insert into examination(exam_id, exam_date, disease_disease_id, doctor_doctor_licence_id, prescription_prescription_id) values  (69, "2018-06-01", 9, 2, 7);

insert into exam_symptoms(exam_id, symptoms_id) values (61, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (61, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (61, 4);
insert into exam_symptoms(exam_id, symptoms_id) values (61, 6);
insert into exam_symptoms(exam_id, symptoms_id) values (61, 2);
insert into exam_symptoms(exam_id, symptoms_id) values (64, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (64, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (64, 4);
insert into exam_symptoms(exam_id, symptoms_id) values (64, 6);
insert into exam_symptoms(exam_id, symptoms_id) values (64, 12);
insert into exam_symptoms(exam_id, symptoms_id) values (67, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (67, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (67, 4);
insert into exam_symptoms(exam_id, symptoms_id) values (67, 6);
insert into exam_symptoms(exam_id, symptoms_id) values (67, 10);

insert into exam_symptoms(exam_id, symptoms_id) values (62, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (62, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (62, 12);
insert into exam_symptoms(exam_id, symptoms_id) values (62, 6);
insert into exam_symptoms(exam_id, symptoms_id) values (62, 5);
insert into exam_symptoms(exam_id, symptoms_id) values (65, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (65, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (65, 12);
insert into exam_symptoms(exam_id, symptoms_id) values (65, 6);
insert into exam_symptoms(exam_id, symptoms_id) values (65, 15);
insert into exam_symptoms(exam_id, symptoms_id) values (68, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (68, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (68, 12);
insert into exam_symptoms(exam_id, symptoms_id) values (68, 6);
insert into exam_symptoms(exam_id, symptoms_id) values (68, 10);

insert into exam_symptoms(exam_id, symptoms_id) values (63, 17);
insert into exam_symptoms(exam_id, symptoms_id) values (63, 21);
insert into exam_symptoms(exam_id, symptoms_id) values (63, 24);
insert into exam_symptoms(exam_id, symptoms_id) values (63, 20);
insert into exam_symptoms(exam_id, symptoms_id) values (66, 17);
insert into exam_symptoms(exam_id, symptoms_id) values (66, 21);
insert into exam_symptoms(exam_id, symptoms_id) values (66, 24);
insert into exam_symptoms(exam_id, symptoms_id) values (66, 5);
insert into exam_symptoms(exam_id, symptoms_id) values (69, 17);
insert into exam_symptoms(exam_id, symptoms_id) values (69, 21);
insert into exam_symptoms(exam_id, symptoms_id) values (69, 24);
insert into exam_symptoms(exam_id, symptoms_id) values (69, 3);

insert into chart_exams(chart_id, exam_id) values (6, 61);
insert into chart_exams(chart_id, exam_id) values (6, 62);
insert into chart_exams(chart_id, exam_id) values (6, 63);
insert into chart_exams(chart_id, exam_id) values (6, 64);
insert into chart_exams(chart_id, exam_id) values (6, 65);
insert into chart_exams(chart_id, exam_id) values (6, 66);
insert into chart_exams(chart_id, exam_id) values (6, 67);
insert into chart_exams(chart_id, exam_id) values (6, 68);
insert into chart_exams(chart_id, exam_id) values (6, 69);