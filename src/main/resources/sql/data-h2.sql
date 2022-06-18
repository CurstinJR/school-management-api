INSERT INTO students(`student_id`, `email`, `first_name`, `middle_name`, `last_name`)
VALUES ('1', 'joe@email.com', 'Joseph', 'Jose', 'Ro'),
       ('2', 'max@email.com', 'Maximilian', 'La', 'Fate'),
       ('3', 'ethan@email.com', 'Nathaniel', 'Josh', 'Buyers');

INSERT INTO employees(`staff_id`, `email`, `first_name`, `middle_name`, `last_name`)
VALUES ('1', 'joe@email.edu', 'Joey', 'Knight', 'Summer'),
       ('2', 'max@email.edu', 'Maxy', 'Queen', 'Winter'),
       ('3', 'ethan@email.edu', 'Ethan', 'Prince', 'Fay');

INSERT INTO countries(`id`, `name`)
VALUES ('1', 'Abkhazia'),
       ('2', 'Afghanistan'),
       ('3', 'Albania');

INSERT INTO cities(`id`, `name`, `country_id`)
VALUES ('1', 'Sukhumi', '1'),
       ('2', 'Kabul1', '2'),
       ('3', 'Tirana', '3'),
       ('4', 'Abkhazia1', '1'),
       ('5', 'Abkhazia2', '1'),
       ('6', 'Kabul2', '2'),
       ('7', 'Kabul3', '2');

-- INSERT INTO student_address(`student_id`, `complex_name`, `postal_code`, `street_name`, 'street_number',
--                             `unit_number`, `city_id`)
-- VALUES ('1', 'Burj Khalifa', 1234, 'Stoneleigh Place', '81', 'W.11', '1'),
--        ('2', 'Shanghai Tower', 5478, 'Buckfast Street', '78', 'E.2', '2'),
--        ('3', 'Makkah Royal Clock Tower', 3956, 'Abbots Place', '45', 'N.W.6', '3');

-- INSERT INTO student_address(`student_id`, `complex_name`, `postal_code`, `street_name`, 'street_number',
--                             `unit_number`)
-- VALUES ('1', 'Burj Khalifa', 1234, 'Stoneleigh Place', '81', 'W.11'),
--        ('2', 'Shanghai Tower', 5478, 'Buckfast Street', '78', 'E.2'),
--        ('3', 'Makkah Royal Clock Tower', 3956, 'Abbots Place', '45', 'N.W.6');

INSERT INTO student_address(`student_id`, `postal_code`, `city_id`)
VALUES ('1', 6547, '1'),
       ('2', 1234, '1'),
       ('3', 9865, '1');

INSERT INTO employee_address(`staff_id`, `postal_code`, `city_id`)
VALUES ('1', 8754, '1'),
       ('2', 3578, '1'),
       ('3', 1598, '2');
