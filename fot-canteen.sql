-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2021 at 07:11 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fot-canteen`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_order_procedure` (IN `u_id` VARCHAR(6), IN `itm_id` VARCHAR(6), IN `quantity` INT(5))  BEGIN
DECLARE unit_price FLOAT(2);
DECLARE total FLOAT(2); 
SELECT price INTO unit_price FROM items WHERE id = itm_id;
SET total = unit_price * quantity;
INSERT INTO item_orders (`user_id`, `item_id`, `quantity`, `order_amount`) VALUES (u_id, itm_id, quantity, total);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `create_user` (IN `id` VARCHAR(6), IN `u_name` VARCHAR(255), IN `email` VARCHAR(100), IN `u_password` VARCHAR(50), IN `u_role` VARCHAR(8))  BEGIN
INSERT INTO `user`(`id`, `email`, `password`, `type`, `username`) VALUES (id , email, u_password, u_role,u_name)
;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `findAllBuyers` ()  BEGIN
	select * from user where type ="Buyer";
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `findAllSeller` ()  BEGIN
	select * from user where type="Seller ";
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetItem` ()  BEGIN
	select * from items;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `new_procedure` ()  BEGIN
	select * from user where type="Seller ";
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `user_account_delete_procedure` (IN `id` VARCHAR(6))  BEGIN
DELETE FROM `user` WHERE `id` = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `user_update_procedure` (IN `u_id` VARCHAR(6), IN `u_name` VARCHAR(255), IN `u_email` VARCHAR(100), IN `u_password` VARCHAR(50), IN `u_role` VARCHAR(8))  BEGIN
UPDATE `user` 
SET 
`id` = u_id, 
`username` = u_name, 
`email` = u_email, 
`password` = u_password,
`type` = u_role

WHERE `id` = u_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `view_user` ()  BEGIN
	select * from user;
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `calculate_daily_order_count_function` () RETURNS FLOAT BEGIN
DECLARE transactions_count FLOAT DEFAULT 0.0;
SELECT count(id) INTO transactions_count 
FROM `orders` 
WHERE DATE(`date`) = DATE(NOW()) 
GROUP BY `date`;
RETURN (transactions_count);
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `calculate_total_transaction_amount_function` () RETURNS FLOAT BEGIN
DECLARE transactions_amount FLOAT DEFAULT 0.0;
SELECT SUM(total) INTO transactions_amount 
FROM `orders` 
WHERE DATE(`date`) = DATE(NOW()) 
GROUP BY `date`;
RETURN (transactions_amount);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(36),
(36),
(36),
(36),
(36),
(36),
(36);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `seller` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `available` bit(1) DEFAULT NULL,
  `bonus` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `image`, `info`, `name`, `price`, `qty`, `seller`, `type`, `available`, `bonus`) VALUES
(3, 'Pol-Roti-Coconut-Roti-6665-780x1169.jpg', 'pol roti with sambal', 'Rotie', 40, 28, 'rashmininayanathara@gmail.com', 'Breakfast', NULL, NULL),
(8, 'Pol-Roti-Coconut-Roti-6665-780x1169.jpg', 'ghhh', 'Rice and cury', 150, 6, 'hmailxxx', 'Dinner', NULL, NULL),
(12, 'P1013087-2.jpg', '1 portion', 'Noodles', 120, 398, 'check02', 'Dinner', NULL, NULL),
(14, '58219273.jpg', '1 cup with honey and icecream', 'fruit salad', 100, 95, 'check02', 'Lunch', NULL, NULL),
(17, 'P1013087-2.jpg', 'DGHDG', 'Rice', 120, 50, 'rashmininayanathara@gmail.com', 'Dinner', NULL, NULL),
(21, '58219273.jpg', 'qfefeg', 'papaya juice', 100, 98, 'rashmininayanathara@gmail.com', 'Breakfast', NULL, NULL),
(24, 'mango-juice.jpg', 'dsfdf', 'mango juice', 100, 97, 'rashmininayanathara@gmail.com', 'Breakfast', NULL, NULL),
(27, 'koththuroti03.jpg', 'one portion, egg and chicken included', 'Koththu rotie', 220, 296, 'test20@gmail.com', 'Dinner', NULL, NULL),
(32, NULL, NULL, 'vegitable salads', 120, 48, 'seller', 'dinner', NULL, NULL),
(29, 'images.jpg', 'Egg rolls', ' Rolls', 50, 48, 'test20@gmail.com', 'Breakfast', NULL, NULL);

--
-- Triggers `items`
--
DELIMITER $$
CREATE TRIGGER ` item_insert_order_trigger` AFTER INSERT ON `items` FOR EACH ROW BEGIN
UPDATE `items` SET `qty`= (`quantity`- NEW.`qty`) WHERE `id` = NEW.`id` ;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `in_insert_order_trigger` AFTER INSERT ON `items` FOR EACH ROW BEGIN
UPDATE `items` SET `qty`= (`quantity`- NEW.`qty`) WHERE `id` = NEW.`id` ;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `items_orders`
--

CREATE TABLE `items_orders` (
  `item_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `user_id` int(10) NOT NULL,
  `order_amount` decimal(10,2) NOT NULL,
  `orders_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `buyer` varchar(255) DEFAULT NULL,
  `meal_type` varchar(255) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `fk_item` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `buyer`, `meal_type`, `qty`, `status`, `total`, `fk_item`, `date`, `time`) VALUES
(6, 'tara', 'Breakfast', 3, 'NOT PAID', 120, 3, NULL, NULL),
(10, 'check01', 'Dinner', 4, 'NOT PAID', 600, 8, NULL, NULL),
(13, 'tara', 'Dinner', 2, 'NOT PAID', 240, 12, NULL, NULL),
(15, 'tara', 'Lunch', 5, 'NOT PAID', 500, 14, NULL, NULL),
(18, 'tara', 'Breakfast', 1, 'NOT PAID', 40, 3, NULL, NULL),
(19, 'tara', 'Breakfast', 4, 'NOT PAID', 160, 3, NULL, NULL),
(20, 'tara', 'Breakfast', 4, 'NOT PAID', 160, 3, NULL, NULL),
(23, 'tara', 'Breakfast', 4, 'NOT PAID', 160, 3, NULL, NULL),
(25, 'tara', 'Breakfast', 4, 'NOT PAID', 160, 3, NULL, NULL),
(28, 'tara', 'Dinner', 2, 'NOT PAID', 440, 27, NULL, NULL),
(0, 'tara', 'breckfirst', 2, NULL, 100, NULL, NULL, NULL),
(31, 'tara', 'Breakfast', 2, 'NOT PAID', 100, 29, NULL, NULL),
(32, 'tara', 'Breakfast', 2, 'NOT PAID', 200, 21, NULL, NULL),
(33, 'tara', 'Dinner', 2, 'NOT PAID', 440, 27, NULL, NULL),
(34, 'tara hewavitharana', 'Breakfast', 3, 'NOT PAID', 300, 24, NULL, NULL),
(35, 'tara', 'Breakfast', 2, 'NOT PAID', 80, 3, NULL, NULL);

--
-- Triggers `orders`
--
DELIMITER $$
CREATE TRIGGER `after_insert_order_trigger` AFTER INSERT ON `orders` FOR EACH ROW BEGIN

DECLARE user_data VARCHAR(255);
DECLARE user VARCHAR(50);
DECLARE operation VARCHAR(8);
DECLARE changedat DATETIME;

SET user_data  = CONCAT_WS(', ', new.id, new.buyer, new.meal_type, new.qty, new.status,new.total,new.fk_item);
SET user = CURRENT_USER;
SET operation = 'INSERT';
SET changedat = now();

INSERT INTO order_log (order_details, user, operation, changed_at)
VALUES
(user_data, user, operation, changedat);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_update_order_trigger` AFTER UPDATE ON `orders` FOR EACH ROW BEGIN

DECLARE user_data VARCHAR(255);
DECLARE user VARCHAR(50);
DECLARE operation VARCHAR(8);
DECLARE changedat DATETIME;

SET user_data  = CONCAT_WS(', ', new.id, new.buyer, new.meal_type, new.qty, new.status,new.total,new.fk_item);
SET user = CURRENT_USER;
SET operation = 'UPDATE';
SET changedat = now();

INSERT INTO order_log (order_details, user, operation, changed_at)
VALUES
(user_data, user, operation, changedat);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `item_update_order_trigger` AFTER INSERT ON `orders` FOR EACH ROW BEGIN
UPDATE `items` SET `qty`= (`qty`- NEW.`qty`) WHERE `id` = NEW.`id` ;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `order_details`
-- (See below for the actual view)
--
CREATE TABLE `order_details` (
`buyer` varchar(255)
,`total` double
);

-- --------------------------------------------------------

--
-- Table structure for table `order_log`
--

CREATE TABLE `order_log` (
  `order_details` varchar(255) NOT NULL,
  `user` varchar(200) NOT NULL,
  `operation` varchar(100) NOT NULL,
  `changed_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_log`
--

INSERT INTO `order_log` (`order_details`, `user`, `operation`, `changed_at`) VALUES
('35, tara, Breakfast, 2, NOT PAID, 80, 3', 'root@localhost', 'INSERT', '2021-07-16 12:03:38'),
('34, tara hewavitharana, Breakfast, 3, NOT PAID, 300, 24', 'root@localhost', 'UPDATE', '2021-07-16 14:54:11');

-- --------------------------------------------------------

--
-- Table structure for table `order_summery`
--

CREATE TABLE `order_summery` (
  `date` date NOT NULL,
  `total_amount` varchar(100) NOT NULL,
  `order_count` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `type`, `username`) VALUES
(1, 'rashmininayanathara@gmail.com', 'tara', 'Buyer', 'rashmininayanathara@gmail.com'),
(2, 'rashmininayanathara@gmail.com', '1234', 'Seller', 'rashmininayanathara@gmail.com'),
(4, 'rash@gmail.com', '1234', 'Seller', 'rash@gmail.com'),
(5, 'tara@gmail.com', '1234', 'Buyer', 'tara'),
(7, 'ghpiyumi@gmail.com', '111222333', 'Seller', 'hmailxxx'),
(9, 'rashmininayaathara@gmail.com', '111222333', 'Buyer', 'check01'),
(11, 'shashini@gmail.com', '1234', 'Seller', 'check02'),
(0, '123', 'buyer', 'Tara', 'tara@gmail.com'),
(23, '123', 'buyer', 'Tara', 'tara@gmail.com'),
(24, 'seller', '456', 'shen', 'shen@gmail.com'),
(30, '1111', 'buyer', 'test1', 'test@gmail.com'),
(16, 'test@gmail.com', '1234', 'Buyer', 'check03'),
(22, 'test@gmail.com', '1234', 'Seller', 'test1'),
(20, 'piyumi@gmail.com', '123456', 'seller', 'piyumi@gmail.com'),
(3, 'triger@gmail.com', '123', 'buyer', 'triger@gmail.com'),
(32, 'test5@gmail.com', '123', 'seller', 'test5@gmail.com'),
(50, 'one@gmail.com', '111', 'seller', 'one@gmail.com'),
(51, 'two@gmail.com', '222', 'seller', 'two@gmail.com'),
(53, 'test53@yahoo.com', '1111', 'buyer', 'test53@yahoo.com'),
(54, 'test54@gmail.com', '7777', 'seller', 'test54@gmail.com'),
(26, 'test40@gmail.com', '2222', 'Buyer', 'test40@gmail.com'),
(33, 'test50@gmail.com', '1111', 'buyer', 'test50@gmail.com'),
(34, 'check200@gmail.com', '1111', 'seller', 'check200@gmail.com'),
(55, 'check100@gmail.com', '123456', 'buyer', 'check100@gmail.com'),
(6, 'check101@gmail.com', '1234', 'buyer', 'check101@gmail.com');

--
-- Triggers `user`
--
DELIMITER $$
CREATE TRIGGER `after_update_user` AFTER UPDATE ON `user` FOR EACH ROW BEGIN

DECLARE user_data VARCHAR(255);
DECLARE userp VARCHAR(50);
DECLARE operation VARCHAR(8);
DECLARE changedat DATETIME;

SET user_data  = CONCAT_WS(', ', new.id, new.email, new.password, new.type, new.username);
SET userp = CURRENT_USER;
SET operation = 'UPDATE';
SET changedat = now();

INSERT INTO use_log (user_details, user, operation, changed_at)
VALUES
(user_data, userp, operation, changedat);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_detele_user` BEFORE DELETE ON `user` FOR EACH ROW BEGIN

DECLARE user_data VARCHAR(255);
DECLARE userp VARCHAR(50);
DECLARE operation VARCHAR(8);
DECLARE changedat DATETIME;


SET operation = 'DELETE';


INSERT INTO use_log (user_details, user, operation, changed_at)
VALUES
(user_data, userp, operation, changedat);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insert_user_tri` AFTER INSERT ON `user` FOR EACH ROW BEGIN

DECLARE user_data VARCHAR(255);
DECLARE userp VARCHAR(50);
DECLARE operation VARCHAR(8);
DECLARE changedat DATETIME;

SET user_data  = CONCAT_WS(', ', new.id, new.email, new.password, new.type, new.username);
SET userp = CURRENT_USER;
SET operation = 'INSERT';
SET changedat = now();

INSERT INTO use_log (user_details, user, operation, changed_at)
VALUES
(user_data, userp, operation, changedat);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insert_user_trigger` AFTER INSERT ON `user` FOR EACH ROW BEGIN

DECLARE user_data VARCHAR(255);
DECLARE userp VARCHAR(50);
DECLARE operation VARCHAR(8);
DECLARE changedat DATETIME;

SET user_data  = CONCAT_WS(', ', new.id, new.email, new.password, new.type, new.username);
SET userp = CURRENT_USER;
SET operation = 'INSERT';
SET changedat = now();

INSERT INTO use_log (user_details, user, operation, changed_at)
VALUES
(user_data, userp, operation, changedat);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `use_log`
--

CREATE TABLE `use_log` (
  `user_details` varchar(255) NOT NULL,
  `user` varchar(50) NOT NULL,
  `operation` varchar(8) NOT NULL,
  `changed_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `use_log`
--

INSERT INTO `use_log` (`user_details`, `user`, `operation`, `changed_at`) VALUES
('two@gmail.com5122251seller51two@gmail.com', 'root@localhost', 'INSERT', '2021-07-16 00:08:57'),
('53, test53@yahoo.com, 1111, buyer, test53@yahoo.com', 'root@localhost', 'INSERT', '2021-07-16 00:10:38'),
('54, test54@gmail.com, 7777, seller, test54@gmail.com', 'root@localhost', 'INSERT', '2021-07-16 00:12:23'),
('26, test20@gmail.com, 1111, Seller, test20@gmail.com', 'root@localhost', 'INSERT', '2021-07-16 00:21:53'),
('26, test40@gmail.com, 2222, Buyer, test40@gmail.com', 'root@localhost', 'UPDATE', '2021-07-16 10:58:00'),
('34, check20@gmail.com, 1111, seller, check20@gmail.com', 'root@localhost', 'INSERT', '2021-07-16 11:40:29'),
('34, check20@gmail.com, 1111, seller, check20@gmail.com', 'root@localhost', 'INSERT', '2021-07-16 11:40:29'),
('34, check200@gmail.com, 1111, seller, check200@gmail.com', 'root@localhost', 'UPDATE', '2021-07-16 14:54:52'),
('6, check101@gmail.com, 1234, buyer, check101@gmail.com', 'root@localhost', 'INSERT', '2021-07-16 15:01:12'),
('6, check101@gmail.com, 1234, buyer, check101@gmail.com', 'root@localhost', 'INSERT', '2021-07-16 15:01:12');

-- --------------------------------------------------------

--
-- Structure for view `order_details`
--
DROP TABLE IF EXISTS `order_details`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `order_details`  AS SELECT `orders`.`buyer` AS `buyer`, `orders`.`total` AS `total` FROM `orders` ORDER BY `orders`.`total` DESC ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `items_orders`
--
ALTER TABLE `items_orders`
  ADD UNIQUE KEY `UK_a89aecrjem76yynxo35gpqb4g` (`quantity`),
  ADD KEY `FKt4c2pxefq37i5qkvpiq0l6rxm` (`orders_id`),
  ADD KEY `FK6rr5eaxpbk8a9qc5gpqvbm0lt` (`item_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgr8kpmh5bl7v63hym9juroqb7` (`fk_item`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `daily_order_summary_event` ON SCHEDULE EVERY 1 DAY STARTS '2021-07-15 23:59:59' ON COMPLETION PRESERVE ENABLE DO INSERT INTO `order_summery`
(`date`, `total_amount`, `order_countcalculate_daily_order_count_function`)
VALUES
(DATE(NOW()), calculate_total_transaction_amount_function(), calculate_daily_order_count_function())$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
