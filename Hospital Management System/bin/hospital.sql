-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 20, 2024 at 09:51 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `displayData` ()   BEGIN
  SELECT * FROM patients;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertData` (IN `in_nurse_fullname` VARCHAR(30), IN `in_nurse_contact_no` VARCHAR(10))   BEGIN
	INSERT INTO nurses(nurse_fullname, nurse_contact_no)
    VALUES(in_nurse_fullname,in_nurse_contact_no);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `login_id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`login_id`, `password`) VALUES
('', ''),
('admin', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appointment_id` int(11) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `appointment_date` date DEFAULT NULL,
  `appointment_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appointment_id`, `patient_id`, `doctor_id`, `appointment_date`, `appointment_time`) VALUES
(4, 3, 3, '2020-01-21', '04:34:23'),
(5, 2, 4, '2021-01-19', '11:23:12'),
(6, 2, 3, '2024-01-21', '00:34:45');

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE `doctors` (
  `doctor_id` int(11) NOT NULL,
  `doctor_fullname` varchar(25) NOT NULL,
  `doctor_specialization` varchar(15) NOT NULL,
  `doctor_contact_no` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`doctor_id`, `doctor_fullname`, `doctor_specialization`, `doctor_contact_no`) VALUES
(2, 'Dr.Zmit', 'Cyco', '6789'),
(3, 'Dr.Amit', 'e', '1212'),
(4, 'Dr.Eaa', 'ewe', '42323'),
(6, 'Dr.Charmi', 'Anatomy', '1234'),
(8, 'Dr.Anil', 'j', '676'),
(9, 'Dr.Ansh Sharma', 'wew', '1234987678'),
(12, 'Dr. Ritu K.Patel', 'Orthopedic', '1234567001'),
(13, 'Dr. Rita P.Shah', 'Cardiology', '7934365621'),
(14, 'Dr. ERRE PATEL', 'Orthopedic', '1234567890');

--
-- Triggers `doctors`
--
DELIMITER $$
CREATE TRIGGER `fetch_doctor_data` AFTER INSERT ON `doctors` FOR EACH ROW BEGIN
    UPDATE medical_history m
    SET m.doctor_fullname = (SELECT doctor_fullname FROM doctors WHERE doctor_id = NEW.doctor_id),
        m.doctor_specialization = (SELECT doctor_specialization FROM doctors WHERE doctor_id = NEW.doctor_id),
        m.doctor_contact_no = (SELECT doctor_contact_no FROM doctors WHERE doctor_id = NEW.doctor_id)
    WHERE m.doctor_id = NEW.doctor_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `medical_history`
--

CREATE TABLE `medical_history` (
  `medical_history_id` int(11) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `prescription_id` int(11) DEFAULT NULL,
  `medicine_name` varchar(255) DEFAULT NULL,
  `patient_fullname` varchar(255) DEFAULT NULL,
  `patient_age` int(11) DEFAULT NULL,
  `patient_address` varchar(255) DEFAULT NULL,
  `patient_contact_no` varchar(20) DEFAULT NULL,
  `patient_gender` varchar(10) DEFAULT NULL,
  `patient_suffer` varchar(255) DEFAULT NULL,
  `doctor_fullname` varchar(255) DEFAULT NULL,
  `doctor_specialization` varchar(255) DEFAULT NULL,
  `doctor_contact_no` varchar(20) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `medical_history`
--

INSERT INTO `medical_history` (`medical_history_id`, `patient_id`, `doctor_id`, `prescription_id`, `medicine_name`, `patient_fullname`, `patient_age`, `patient_address`, `patient_contact_no`, `patient_gender`, `patient_suffer`, `doctor_fullname`, `doctor_specialization`, `doctor_contact_no`, `time`) VALUES
(14, 2, 3, 1, 'Pepsi', 'w', 11, 'WQWQWQ', '1221', 'Fe', 'Chicken', 'Dr.Amit', 'e', '1212', '2024-08-23 10:20:19'),
(15, 2, 2, 2, 'Doodh', 'w', 11, 'WQWQWQ', '1221', 'Fe', 'Chicken', 'Dr.Zmit', 'Cyco', '6789', '2024-08-23 10:20:43');

-- --------------------------------------------------------

--
-- Table structure for table `nurses`
--

CREATE TABLE `nurses` (
  `nurse_id` int(11) NOT NULL,
  `nurse_fullname` varchar(100) DEFAULT NULL,
  `nurse_contact_no` varchar(15) DEFAULT NULL,
  `room_number` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nurses`
--

INSERT INTO `nurses` (`nurse_id`, `nurse_fullname`, `nurse_contact_no`, `room_number`, `patient_id`) VALUES
(2, 'Kiran', '1243', 10, 2),
(3, 'Sunita', '6789', 11, 3),
(4, 'qm', '323242', NULL, NULL),
(5, 'SWQ', '6565', NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `patient_id` int(11) NOT NULL,
  `patient_fullname` varchar(25) NOT NULL,
  `patient_age` int(4) NOT NULL,
  `patient_address` varchar(250) NOT NULL,
  `patient_contact_no` varchar(10) NOT NULL,
  `patient_gender` varchar(6) NOT NULL,
  `patient_suffer` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`patient_id`, `patient_fullname`, `patient_age`, `patient_address`, `patient_contact_no`, `patient_gender`, `patient_suffer`) VALUES
(2, 'w', 11, 'WQWQWQ', '1221', 'Fe', 'Chicken'),
(3, 'Amit', 12, 'ddd', '12334', 'Female', 'weak'),
(4, 'Aqre', 31, 'rgfg', '453434', 'er', 'hgh'),
(5, 'Smit', 18, '10,New', '794867150', 'Male', 'Tubur'),
(6, 'Hitar', 11, 'grgg', '45303', 'MF', 'wq');

--
-- Triggers `patients`
--
DELIMITER $$
CREATE TRIGGER `fetch_patient_data` AFTER INSERT ON `patients` FOR EACH ROW BEGIN
    UPDATE medical_history m
    SET m.patient_fullname = (SELECT patient_fullname FROM patients WHERE patient_id = NEW.patient_id),
        m.patient_age = (SELECT patient_age FROM patients WHERE patient_id = NEW.patient_id),
        m.patient_address = (SELECT patient_address FROM patients WHERE patient_id = NEW.patient_id),
        m.patient_contact_no = (SELECT patient_contact_no FROM patients WHERE patient_id = NEW.patient_id),
        m.patient_gender = (SELECT patient_gender FROM patients WHERE patient_id = NEW.patient_id)
    WHERE m.patient_id = NEW.patient_id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updated_p_suffer` BEFORE INSERT ON `patients` FOR EACH ROW BEGIN
    UPDATE prescriptions p
    SET p.patient_suffer = (SELECT patient_suffer FROM patients WHERE patient_id = NEW.patient_id)
    WHERE NEW.patient_id IN (SELECT patient_id FROM patients);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `prescriptions`
--

CREATE TABLE `prescriptions` (
  `prescription_id` int(11) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `medicine_name` varchar(50) DEFAULT NULL,
  `dosage` varchar(50) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_suffer` varchar(50) DEFAULT NULL,
  `Time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prescriptions`
--

INSERT INTO `prescriptions` (`prescription_id`, `patient_id`, `medicine_name`, `dosage`, `doctor_id`, `patient_suffer`, `Time`) VALUES
(1, 3, 'Pepsi', '4-5/day', 6, 'weak', '2024-08-22 15:39:20'),
(2, 2, 'Doodh', '1/day', 2, 'Chicken', '2024-08-22 15:41:45');

--
-- Triggers `prescriptions`
--
DELIMITER $$
CREATE TRIGGER `fetch_prescription_data` AFTER INSERT ON `prescriptions` FOR EACH ROW BEGIN
    UPDATE medical_history m
    SET m.medicine_name = (SELECT medicine_name FROM prescriptions WHERE prescription_id = NEW.prescription_id)
    WHERE m.prescription_id = NEW.prescription_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `room_id` int(11) NOT NULL,
  `room_type` varchar(15) DEFAULT NULL,
  `room_number` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `fees` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `room_type`, `room_number`, `patient_id`, `fees`) VALUES
(10, 'ICU', 10, NULL, 500.00),
(11, 'ICU', 11, 3, 500.00),
(12, 'ICU', 12, NULL, 500.00),
(17, 'General Ward', 1, NULL, 100.00),
(18, 'General Ward', 2, NULL, 100.00),
(19, 'General Ward', 3, NULL, 100.00),
(21, 'Private Ward', 5, NULL, 200.00),
(22, 'Private Ward', 6, NULL, 200.00),
(23, 'Private Ward', 7, NULL, 200.00),
(24, 'ICU', 8, NULL, 500.00),
(25, 'ICU', 9, 4, 500.00),
(26, 'Private Ward', 50, NULL, 200.00),
(28, 'ICU', 14, NULL, 500.00),
(29, 'General Ward', 15, NULL, 100.00);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_nurses`
-- (See below for the actual view)
--
CREATE TABLE `v_nurses` (
`nurse_id` int(11)
,`nurse_fullname` varchar(100)
,`nurse_contact_no` varchar(15)
,`room_number` int(11)
,`patient_id` int(11)
);

-- --------------------------------------------------------

--
-- Structure for view `v_nurses`
--
DROP TABLE IF EXISTS `v_nurses`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_nurses`  AS SELECT `nurses`.`nurse_id` AS `nurse_id`, `nurses`.`nurse_fullname` AS `nurse_fullname`, `nurses`.`nurse_contact_no` AS `nurse_contact_no`, `nurses`.`room_number` AS `room_number`, `nurses`.`patient_id` AS `patient_id` FROM `nurses` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `doctor_id` (`doctor_id`);

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`doctor_id`);

--
-- Indexes for table `medical_history`
--
ALTER TABLE `medical_history`
  ADD PRIMARY KEY (`medical_history_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `prescription_id` (`prescription_id`);

--
-- Indexes for table `nurses`
--
ALTER TABLE `nurses`
  ADD PRIMARY KEY (`nurse_id`),
  ADD KEY `room_number` (`room_number`),
  ADD KEY `patient_id` (`patient_id`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `prescriptions`
--
ALTER TABLE `prescriptions`
  ADD PRIMARY KEY (`prescription_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `doctor_id` (`doctor_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`room_id`),
  ADD UNIQUE KEY `room_number` (`room_number`),
  ADD UNIQUE KEY `room_number_2` (`room_number`),
  ADD KEY `rooms_ibfk_1` (`patient_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `doctors`
--
ALTER TABLE `doctors`
  MODIFY `doctor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `medical_history`
--
ALTER TABLE `medical_history`
  MODIFY `medical_history_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `nurses`
--
ALTER TABLE `nurses`
  MODIFY `nurse_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `prescriptions`
--
ALTER TABLE `prescriptions`
  MODIFY `prescription_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`);

--
-- Constraints for table `medical_history`
--
ALTER TABLE `medical_history`
  ADD CONSTRAINT `medical_history_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  ADD CONSTRAINT `medical_history_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`),
  ADD CONSTRAINT `medical_history_ibfk_3` FOREIGN KEY (`prescription_id`) REFERENCES `prescriptions` (`prescription_id`);

--
-- Constraints for table `nurses`
--
ALTER TABLE `nurses`
  ADD CONSTRAINT `nurses_ibfk_1` FOREIGN KEY (`room_number`) REFERENCES `rooms` (`room_number`),
  ADD CONSTRAINT `nurses_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`);

--
-- Constraints for table `prescriptions`
--
ALTER TABLE `prescriptions`
  ADD CONSTRAINT `prescriptions_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  ADD CONSTRAINT `prescriptions_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`);

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
