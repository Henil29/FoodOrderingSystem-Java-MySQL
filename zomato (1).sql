-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 28, 2024 at 11:59 AM
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
-- Database: `zomato`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `addUser` (IN `username` VARCHAR(30), IN `userpass` VARCHAR(30), IN `useremail` VARCHAR(30))   insert into users(user_name,user_pass,user_email) values(username,userpass,useremail)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Dish` (IN `rs_name` VARCHAR(30), IN `ds_name` VARCHAR(30), OUT `price` INT(30))   SELECT dish_price as price from rs_name where ds_name=dish_name$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_amount` (IN `username` VARCHAR(20), OUT `user_amount` INT(30))   select user_amount=amount from user where user_name=username$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `g1`
--

CREATE TABLE `g1` (
  `dish_id` int(11) NOT NULL,
  `dish_name` varchar(255) NOT NULL,
  `dish_price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `g1`
--

INSERT INTO `g1` (`dish_id`, `dish_name`, `dish_price`) VALUES
(1, 'x1', 21),
(2, 'x2', 25),
(3, 'x3', 156),
(4, 'x4', 1589),
(5, 'o1', 152);

-- --------------------------------------------------------

--
-- Table structure for table `g2`
--

CREATE TABLE `g2` (
  `dish_id` int(11) NOT NULL,
  `dish_name` varchar(255) NOT NULL,
  `dish_price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `g2`
--

INSERT INTO `g2` (`dish_id`, `dish_name`, `dish_price`) VALUES
(1, 'burger', 129),
(2, 'coca', 75);

-- --------------------------------------------------------

--
-- Table structure for table `g3`
--

CREATE TABLE `g3` (
  `dish_id` int(11) NOT NULL,
  `dish_name` varchar(255) NOT NULL,
  `dish_price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `g3`
--

INSERT INTO `g3` (`dish_id`, `dish_name`, `dish_price`) VALUES
(1, 'pizza', 129);

-- --------------------------------------------------------

--
-- Table structure for table `g4`
--

CREATE TABLE `g4` (
  `dish_id` int(11) NOT NULL,
  `dish_name` varchar(255) NOT NULL,
  `dish_price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `gt1`
--

CREATE TABLE `gt1` (
  `dish_id` int(11) NOT NULL,
  `dish_name` varchar(255) NOT NULL,
  `dish_price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  `res_id` int(20) NOT NULL,
  `res_name` varchar(20) NOT NULL,
  `res_pass` int(20) NOT NULL,
  `res_address` varchar(20) NOT NULL,
  `res_no` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`res_id`, `res_name`, `res_pass`, `res_address`, `res_no`) VALUES
(1, 'g3', 123, 'fhj', 123),
(2, 'g1', 456, 'rf', 8754),
(3, 'g2', 789, 'rge', 12),
(4, 'g4', 100, 'ghjm', 542),
(5, 'gt1', 159, 'sdgs', 521);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_pass` int(20) NOT NULL,
  `user_methode` varchar(20) DEFAULT NULL,
  `user_data` varchar(20) DEFAULT NULL,
  `user_email` varchar(20) NOT NULL,
  `Amount` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `user_pass`, `user_methode`, `user_data`, `user_email`, `Amount`) VALUES
(1, 'Henil', 123, 'UPI', NULL, 'henil@gmail.com', 3330),
(2, 'Vasu', 789, NULL, NULL, 'tirth@gmail.com', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `g1`
--
ALTER TABLE `g1`
  ADD PRIMARY KEY (`dish_id`);

--
-- Indexes for table `g2`
--
ALTER TABLE `g2`
  ADD PRIMARY KEY (`dish_id`);

--
-- Indexes for table `g3`
--
ALTER TABLE `g3`
  ADD PRIMARY KEY (`dish_id`);

--
-- Indexes for table `g4`
--
ALTER TABLE `g4`
  ADD PRIMARY KEY (`dish_id`);

--
-- Indexes for table `gt1`
--
ALTER TABLE `gt1`
  ADD PRIMARY KEY (`dish_id`);

--
-- Indexes for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`res_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `g1`
--
ALTER TABLE `g1`
  MODIFY `dish_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `g2`
--
ALTER TABLE `g2`
  MODIFY `dish_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `g3`
--
ALTER TABLE `g3`
  MODIFY `dish_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `g4`
--
ALTER TABLE `g4`
  MODIFY `dish_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gt1`
--
ALTER TABLE `gt1`
  MODIFY `dish_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `res_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
