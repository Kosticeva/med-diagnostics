insert into disease(disease_id, disease_name) values (2, "Prehlada");
insert into disease(disease_id, disease_name) values (3, "Groznica");
insert into disease(disease_id, disease_name) values (4, "Upala krajnika");
insert into disease(disease_id, disease_name) values (5, "Sinusna infekcija");
insert into disease(disease_id, disease_name) values (6, "Hipertenzija");
insert into disease(disease_id, disease_name) values (7, "Dijabetes");
insert into disease(disease_id, disease_name) values (8, "Hronicna bubrezna bolest");
insert into disease(disease_id, disease_name) values (9, "Akutna bubrezna povreda");


insert into symptom (symptom_id, symptom_name) values (1, "Glavobolja");
insert into symptom (symptom_id, symptom_name) values (2, "Curenje iz nosa");
insert into symptom (symptom_id, symptom_name) values (3, "Bol u grlu");
insert into symptom (symptom_id, symptom_name) values (4, "Kijanje");
insert into symptom (symptom_id, symptom_name) values (5, "Kasalj");
insert into symptom (symptom_id, symptom_name) values (6, "Drhtavica");
insert into symptom (symptom_id, symptom_name) values (7, "Temperatura veca od 38");
insert into symptom (symptom_id, symptom_name) values (8, "Bol koji se siri do usiju");
insert into symptom (symptom_id, symptom_name) values (9, "Temperatura od 40 do 41");
insert into symptom (symptom_id, symptom_name) values (10, "Umor");
insert into symptom (symptom_id, symptom_name) values (11, "Zuti sekret iz nosa");
insert into symptom (symptom_id, symptom_name) values (12, "Gubitak apetita");
insert into symptom (symptom_id, symptom_name) values (13, "Oticanje oko ociju");
insert into symptom (symptom_id, symptom_name) values (14, "Povisen krvni pritisak");
insert into symptom (symptom_id, symptom_name) values (15, "Cesto uriniranje");
insert into symptom (symptom_id, symptom_name) values (16, "Gubitak telesne tezine");
insert into symptom (symptom_id, symptom_name) values (17, "Zamor");
insert into symptom (symptom_id, symptom_name) values (18, "Mucnina i povracanje");
insert into symptom (symptom_id, symptom_name) values (19, "Otoci nogu i zglobova");
insert into symptom (symptom_id, symptom_name) values (20, "Nocturia");
insert into symptom (symptom_id, symptom_name) values (21, "Gusenje");
insert into symptom (symptom_id, symptom_name) values (22, "Bol u grudima");
insert into symptom (symptom_id, symptom_name) values (23, "Dijareja");
insert into symptom (symptom_id, symptom_name) values (24, "Oporavlja se od operacije");


insert into link(disease_disease_id, symptom_symptom_id) values (2, 1);
insert into link(disease_disease_id, symptom_symptom_id) values (2, 2);
insert into link(disease_disease_id, symptom_symptom_id) values (2, 3);
insert into link(disease_disease_id, symptom_symptom_id) values (2, 4);
insert into link(disease_disease_id, symptom_symptom_id) values (2, 5);

insert into link(disease_disease_id, symptom_symptom_id) values (3, 2);
insert into link(disease_disease_id, symptom_symptom_id) values (3, 3);
insert into link(disease_disease_id, symptom_symptom_id) values (3, 1);
insert into link(disease_disease_id, symptom_symptom_id) values (3, 4);
insert into link(disease_disease_id, symptom_symptom_id) values (3, 5);
insert into link(disease_disease_id, symptom_symptom_id) values (3, 6);
insert into link(disease_disease_id, symptom_symptom_id) values (3, 7);

insert into link(disease_disease_id, symptom_symptom_id) values (4, 3);
insert into link(disease_disease_id, symptom_symptom_id) values (4, 8);
insert into link(disease_disease_id, symptom_symptom_id) values (4, 9);
insert into link(disease_disease_id, symptom_symptom_id) values (4, 1);
insert into link(disease_disease_id, symptom_symptom_id) values (4, 6);
insert into link(disease_disease_id, symptom_symptom_id) values (4, 10);
insert into link(disease_disease_id, symptom_symptom_id) values (4, 11);
insert into link(disease_disease_id, symptom_symptom_id) values (4, 12);

insert into link(disease_disease_id, symptom_symptom_id) values (5, 13);
insert into link(disease_disease_id, symptom_symptom_id) values (5, 1);
insert into link(disease_disease_id, symptom_symptom_id) values (5, 3);
insert into link(disease_disease_id, symptom_symptom_id) values (5, 7);
insert into link(disease_disease_id, symptom_symptom_id) values (5, 11);
insert into link(disease_disease_id, symptom_symptom_id) values (5, 5);

insert into link(disease_disease_id, symptom_symptom_id) values (6, 14);

insert into link(disease_disease_id, symptom_symptom_id) values (7, 15);
insert into link(disease_disease_id, symptom_symptom_id) values (7, 16);
insert into link(disease_disease_id, symptom_symptom_id) values (7, 17);
insert into link(disease_disease_id, symptom_symptom_id) values (7, 18);

insert into link(disease_disease_id, symptom_symptom_id) values (8, 17);
insert into link(disease_disease_id, symptom_symptom_id) values (8, 20);
insert into link(disease_disease_id, symptom_symptom_id) values (8, 19);
insert into link(disease_disease_id, symptom_symptom_id) values (8, 21);
insert into link(disease_disease_id, symptom_symptom_id) values (8, 22);

insert into link(disease_disease_id, symptom_symptom_id) values (9, 17);
insert into link(disease_disease_id, symptom_symptom_id) values (9, 19);
insert into link(disease_disease_id, symptom_symptom_id) values (9, 21);
insert into link(disease_disease_id, symptom_symptom_id) values (9, 22);
insert into link(disease_disease_id, symptom_symptom_id) values (9, 23);
insert into link(disease_disease_id, symptom_symptom_id) values (9, 24);


insert into ingredient(ingredient_id, ingredient_name) values (1, "Penicilin");
insert into ingredient(ingredient_id, ingredient_name) values (2, "Acetil-salicilna kiselina");
insert into ingredient(ingredient_id, ingredient_name) values (3, "Laktoza");
insert into ingredient(ingredient_id, ingredient_name) values (4, "Bumetanid");
insert into ingredient(ingredient_id, ingredient_name) values (5, "Piridoksin");
insert into ingredient(ingredient_id, ingredient_name) values (6, "Bisoprol-frumarat");
insert into ingredient(ingredient_id, ingredient_name) values (7, "Pipemidinska kiselina");
insert into ingredient(ingredient_id, ingredient_name) values (8, "Metronidazol");
insert into ingredient(ingredient_id, ingredient_name) values (9, "Lizinopril");
insert into ingredient(ingredient_id, ingredient_name) values (10, "Ibuprofen");
insert into ingredient(ingredient_id, ingredient_name) values (11, "Ramipril");


insert into drug(drug_id, drug_name, drug_type) values (12, "Vivace", "OTHER");
insert into drug(drug_id, drug_name, drug_type) values (13, "Pipem", "ANTIBIOTIC");
insert into drug(drug_id, drug_name, drug_type) values (14, "Yurinex", "ANTIBIOTIC");
insert into drug(drug_id, drug_name, drug_type) values (16, "Penicilin", "ANTIBIOTIC");
insert into drug(drug_id, drug_name, drug_type) values (17, "Aspirin", "ANALGESIC");
insert into drug(drug_id, drug_name, drug_type) values (18, "Brufen", "ANALGESIC");
insert into drug(drug_id, drug_name, drug_type) values (19, "Kafetin", "ANALGESIC");
insert into drug(drug_id, drug_name, drug_type) values (21, "Delagil", "ANALGESIC");
insert into drug(drug_id, drug_name, drug_type) values (22, "Cardiopirin", "OTHER");
insert into drug(drug_id, drug_name, drug_type) values (23, "Defrinol", "OTHER");
insert into drug(drug_id, drug_name, drug_type) values (24, "Skopryl", "OTHER");
insert into drug(drug_id, drug_name, drug_type) values (25, "Orvagil", "OTHER");
insert into drug(drug_id, drug_name, drug_type) values (27, "Bisoprol", "OTHER");
insert into drug(drug_id, drug_name, drug_type) values (28, "Lysobact", "OTHER");


insert into drug_ingredients(drug_id, ingredient_id) values (17, 2);
insert into drug_ingredients(drug_id, ingredient_id) values (17, 3);

insert into drug_ingredients(drug_id, ingredient_id) values (27, 6);
insert into drug_ingredients(drug_id, ingredient_id) values (27, 3);

insert into drug_ingredients(drug_id, ingredient_id) values (18, 3);
insert into drug_ingredients(drug_id, ingredient_id) values (18, 2);

insert into drug_ingredients(drug_id, ingredient_id) values (22, 2);
insert into drug_ingredients(drug_id, ingredient_id) values (22, 3);

insert into drug_ingredients(drug_id, ingredient_id) values (23, 10);

insert into drug_ingredients(drug_id, ingredient_id) values (19, 2);

insert into drug_ingredients(drug_id, ingredient_id) values (28, 3);
insert into drug_ingredients(drug_id, ingredient_id) values (28, 5);

insert into drug_ingredients(drug_id, ingredient_id) values (25, 8);

insert into drug_ingredients(drug_id, ingredient_id) values (16, 1);

insert into drug_ingredients(drug_id, ingredient_id) values (13, 7);

insert into drug_ingredients(drug_id, ingredient_id) values (24, 9);

insert into drug_ingredients(drug_id, ingredient_id) values (12, 11);

insert into drug_ingredients(drug_id, ingredient_id) values (14, 4);


insert into doctor(doctor_licence_id, doctor_first_name, doctor_last_name, doctor_username, doctor_password, doctor_type) values (1, "Jelena", "Kostic", "drJelena", "drJelena123", "ADMINISTRATOR");
insert into doctor(doctor_licence_id, doctor_first_name, doctor_last_name, doctor_username, doctor_password, doctor_type) values (2, "Lidija", "Kostic", "drRuKLidija", "drLidija123", "REGULAR");
insert into doctor(doctor_licence_id, doctor_first_name, doctor_last_name, doctor_username, doctor_password, doctor_type) values (3, "Nebojsa", "Kostic", "drPostaNebojsa", "drNebojsa123", "REGULAR");
insert into doctor(doctor_licence_id, doctor_first_name, doctor_last_name, doctor_username, doctor_password, doctor_type) values (4, "Marko", "Kostic", "drKoleKopMaster", "drMarko88", "REGULAR");


insert into patient(patient_id, patient_first_name, patient_last_name) values (1, "Milica", "Petrovic");
insert into patient(patient_id, patient_first_name, patient_last_name) values (2, "Mitar", "Miric");
insert into patient(patient_id, patient_first_name, patient_last_name) values (3, "Zoran", "Karan");
insert into patient(patient_id, patient_first_name, patient_last_name) values (4, "Silvana", "Armenulic");

insert into allergy(allergy_id, allergy_name) values (1, "Penicilin");
insert into allergy(allergy_id, allergy_name) values (2, "Laktoza");
insert into allergy(allergy_id, allergy_name) values (3, "Acetil-salicilna kiselina");

insert into patient_allergies(patient_id, allergy_id) values (2, 1);
insert into patient_allergies(patient_id, allergy_id) values (3, 1);
insert into patient_allergies(patient_id, allergy_id) values (3, 2);
insert into patient_allergies(patient_id, allergy_id) values (4, 1);
insert into patient_allergies(patient_id, allergy_id) values (4, 2);
insert into patient_allergies(patient_id, allergy_id) values (4, 3);


insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (1, "Dva puta dnevno po 1 tableta", 14);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (2, "Tri puta dnevno po 1 tableta", 17);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (3, "Jednom dnevno po 2 tablete", 27);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (4, "Jednom dnevno po 1 tableta", 28);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (5, "Dva puta dnevno po 2 tablete", 14);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (6, "Tri puta dnevno po 2 tablete", 18);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (7, "Dva puta dnevno po 1 tableta", 18);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (8, "Tri puta dnevno po 1 tableta", 17);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (9, "Jednom dnevno po 1 tableta", 27);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (10, "Dva puta dnevno po 1 tableta", 19);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (11, "Dva puta dnevno po 1 tableta", 25);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (12, "Tri puta dnevno po 1 tableta", 21);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (13, "Tri puta dnevno po 1 tableta", 22);
insert into prescription(prescription_id, prescription_plan, drug_drug_id) values (14, "Jednom dnevno po 1 tableta", 23);

insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (1, "2017-10-26", 1, 2, 1);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (2, "2017-9-25", 2, 3, 2);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (3, "2017-8-24", 3, 2, 3);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (4, "2017-7-23", 4, 4, 4);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (5, "2017-6-22", 1, 2, 5);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (6, "2017-5-21", 2, 5, 6);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (7, "2017-4-20", 3, 2, 7);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (8, "2017-3-19", 4, 6, 8);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (9, "2017-2-18", 1, 2, 9);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (10, "2017-1-17", 2, 7, 10);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (11, "2016-12-16", 3, 2, 11);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (12, "2016-11-15", 4, 8, 12);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (13, "2016-10-14", 1, 2, 13);
insert into examination(exam_id, exam_date, doctor_doctor_licence_id, disease_disease_id, prescription_prescription_id) values (14, "2016-9-13", 2, 9, 14);


insert into exam_symptoms(exam_id, symptoms_id) values (1, 2);
insert into exam_symptoms(exam_id, symptoms_id) values (1, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (1, 4);
insert into exam_symptoms(exam_id, symptoms_id) values (1, 5);

insert into exam_symptoms(exam_id, symptoms_id) values (2, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (2, 2);
insert into exam_symptoms(exam_id, symptoms_id) values (2, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (2, 4);
insert into exam_symptoms(exam_id, symptoms_id) values (2, 5);
insert into exam_symptoms(exam_id, symptoms_id) values (2, 6);

insert into exam_symptoms(exam_id, symptoms_id) values (3, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (3, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (3, 4);
insert into exam_symptoms(exam_id, symptoms_id) values (3, 5);

insert into exam_symptoms(exam_id, symptoms_id) values (4, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (4, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (4, 8);
insert into exam_symptoms(exam_id, symptoms_id) values (4, 9);

insert into exam_symptoms(exam_id, symptoms_id) values (5, 2);
insert into exam_symptoms(exam_id, symptoms_id) values (5, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (5, 4);
insert into exam_symptoms(exam_id, symptoms_id) values (5, 5);

insert into exam_symptoms(exam_id, symptoms_id) values (6, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (6, 4);
insert into exam_symptoms(exam_id, symptoms_id) values (6, 9);
insert into exam_symptoms(exam_id, symptoms_id) values (6, 11);

insert into exam_symptoms(exam_id, symptoms_id) values (7, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (7, 2);
insert into exam_symptoms(exam_id, symptoms_id) values (7, 4);
insert into exam_symptoms(exam_id, symptoms_id) values (7, 5);

insert into exam_symptoms(exam_id, symptoms_id) values (8, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (8, 10);
insert into exam_symptoms(exam_id, symptoms_id) values (8, 14);

insert into exam_symptoms(exam_id, symptoms_id) values (9, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (9, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (9, 5);

insert into exam_symptoms(exam_id, symptoms_id) values (10, 15);
insert into exam_symptoms(exam_id, symptoms_id) values (10, 16);
insert into exam_symptoms(exam_id, symptoms_id) values (10, 17);
insert into exam_symptoms(exam_id, symptoms_id) values (10, 18);

insert into exam_symptoms(exam_id, symptoms_id) values (11, 1);
insert into exam_symptoms(exam_id, symptoms_id) values (11, 3);
insert into exam_symptoms(exam_id, symptoms_id) values (11, 5);
insert into exam_symptoms(exam_id, symptoms_id) values (11, 2);
insert into exam_symptoms(exam_id, symptoms_id) values (11, 4);

insert into exam_symptoms(exam_id, symptoms_id) values (12, 20);
insert into exam_symptoms(exam_id, symptoms_id) values (12, 23);
insert into exam_symptoms(exam_id, symptoms_id) values (12, 19);
insert into exam_symptoms(exam_id, symptoms_id) values (12, 18);
insert into exam_symptoms(exam_id, symptoms_id) values (12, 6);

insert into exam_symptoms(exam_id, symptoms_id) values (13, 2);
insert into exam_symptoms(exam_id, symptoms_id) values (13, 4);
insert into exam_symptoms(exam_id, symptoms_id) values (13, 1);

insert into exam_symptoms(exam_id, symptoms_id) values (14, 24);
insert into exam_symptoms(exam_id, symptoms_id) values (14, 23);
insert into exam_symptoms(exam_id, symptoms_id) values (14, 22);
insert into exam_symptoms(exam_id, symptoms_id) values (14, 21);
insert into exam_symptoms(exam_id, symptoms_id) values (14, 20);


insert into chart (chart_id, patient_patient_id) values (1,1);
insert into chart (chart_id, patient_patient_id) values (2,2);
insert into chart (chart_id, patient_patient_id) values (3,3);
insert into chart (chart_id, patient_patient_id) values (4,4);

insert into chart_exams(chart_id, exam_id) values (1, 1);
insert into chart_exams(chart_id, exam_id) values (1, 3);
insert into chart_exams(chart_id, exam_id) values (1, 5);
insert into chart_exams(chart_id, exam_id) values (1, 6);
insert into chart_exams(chart_id, exam_id) values (1, 14);

insert into chart_exams(chart_id, exam_id) values (2, 7);
insert into chart_exams(chart_id, exam_id) values (2, 9);
insert into chart_exams(chart_id, exam_id) values (2, 8);
insert into chart_exams(chart_id, exam_id) values (2, 10);

insert into chart_exams(chart_id, exam_id) values (3, 11);
insert into chart_exams(chart_id, exam_id) values (3, 13);
insert into chart_exams(chart_id, exam_id) values (3, 12);

insert into chart_exams(chart_id, exam_id) values (4, 2);
insert into chart_exams(chart_id, exam_id) values (4, 4);
