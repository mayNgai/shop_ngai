-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 31, 2017 at 11:54 AM
-- Server version: 10.1.22-MariaDB
-- PHP Version: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shop_ngai`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `c_id` int(11) NOT NULL,
  `c_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`c_id`, `c_name`) VALUES
(1, 'Costume'),
(2, 'Mobile'),
(3, 'Camera'),
(4, 'Computer'),
(5, 'Watch'),
(6, 'Bicycle'),
(7, 'Toys'),
(8, 'Travel'),
(9, 'Game'),
(10, 'Books');

-- --------------------------------------------------------

--
-- Table structure for table `detail_order`
--

CREATE TABLE `detail_order` (
  `id` int(11) NOT NULL,
  `o_id` int(11) DEFAULT NULL,
  `do_size` double DEFAULT NULL,
  `do_amount` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `detail_order`
--

INSERT INTO `detail_order` (`id`, `o_id`, `do_size`, `do_amount`) VALUES
(1, 1, 37, 10),
(2, 1, 38, 10),
(3, 1, 39, 10),
(4, 1, 40, 10),
(5, 1, 41, 10),
(6, 2, 37, 10),
(7, 2, 38, 10),
(8, 2, 39, 10),
(9, 2, 40, 10),
(10, 2, 41, 10),
(11, 3, 37, 10),
(12, 3, 38, 10),
(13, 3, 39, 10),
(14, 3, 40, 10),
(15, 3, 41, 10);

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `d_id` int(11) NOT NULL,
  `d_code` text COLLATE utf8_unicode_ci,
  `d_s_date` date DEFAULT NULL,
  `d_e_date` datetime DEFAULT NULL,
  `d_name_pic` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `discount`
--

INSERT INTO `discount` (`d_id`, `d_code`, `d_s_date`, `d_e_date`, `d_name_pic`) VALUES
(1, '1', '2017-08-23', '2017-08-31 00:00:00', 'TopBanner_Vouchers.PNG'),
(2, '2', '2017-08-23', '2017-08-31 00:00:00', 'TopBanner_Vouchers150.JPG'),
(3, '3', '2017-08-23', '2017-08-31 00:00:00', 'TopBanner_Vouchers350.JPG');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `m_id` int(11) NOT NULL,
  `m_password` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_authentication` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_status` int(11) DEFAULT NULL,
  `m_tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_email` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_user_id` bigint(100) DEFAULT NULL,
  `m_date_create` datetime DEFAULT NULL,
  `m_first_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_last_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`m_id`, `m_password`, `m_authentication`, `m_status`, `m_tel`, `m_email`, `m_user_id`, `m_date_create`, `m_first_name`, `m_last_name`) VALUES
(1, '1234', 'user', 0, '0123456789', 'aa@aa.a', 0, '2017-08-23 08:47:07', 'a', 'aa'),
(2, '1111', 'user', 0, '0111111110', 'bb@bb.b', 0, '2017-08-28 00:00:00', 'b', 'bbb'),
(3, '2222', 'user', 0, '0744121453', 'cc@cc.c', 0, '2017-08-28 00:00:00', 'c', 'ccc'),
(4, '4444', 'user', 0, '0142745230', 'dd@dd.d', 0, '2017-08-28 00:00:00', 'dd', 'dddd');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `o_id` int(11) NOT NULL,
  `o_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `o_c_id` int(11) DEFAULT NULL,
  `o_vendor_id` int(11) DEFAULT NULL,
  `o_start_date` datetime DEFAULT NULL,
  `o_end_date` datetime DEFAULT NULL,
  `o_price` int(11) DEFAULT NULL,
  `o_discount` int(11) DEFAULT NULL,
  `o_ratting` int(11) DEFAULT NULL,
  `o_amount` int(11) DEFAULT NULL,
  `o_type` int(11) DEFAULT NULL,
  `o_like` bigint(20) DEFAULT NULL,
  `o_price_ship` int(11) DEFAULT NULL,
  `o_brand` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `o_ship_from` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `o_detail` text COLLATE utf8_unicode_ci,
  `o_count_ratting` bigint(20) DEFAULT NULL,
  `o_lat` float DEFAULT NULL,
  `o_lan` float DEFAULT NULL,
  `o_pic_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`o_id`, `o_name`, `o_c_id`, `o_vendor_id`, `o_start_date`, `o_end_date`, `o_price`, `o_discount`, `o_ratting`, `o_amount`, `o_type`, `o_like`, `o_price_ship`, `o_brand`, `o_ship_from`, `o_detail`, `o_count_ratting`, `o_lat`, `o_lan`, `o_pic_name`) VALUES
(1, 'รองเท้า Convert สีขาว', 1, 1, '2017-08-28 00:00:00', '2017-08-28 00:00:00', 2000, 10, 0, 50, 1, 0, 0, 'Convert', 'bangna', 'size 37-42\r\ncolor ขาว', 0, 13.666, 100.581, 'a_convert_white_01.JPG'),
(2, 'รองเท้า Convert สีดำ', 1, 1, '2017-08-28 00:00:00', '2017-08-28 00:00:00', 2000, 10, 0, 50, 1, 0, 0, 'Convert', 'bangna', 'size 37-41\r\ncolor ดำ', 0, 13.666, 100.581, 'a_convert_black_01.JPG'),
(3, 'รองเท้า Convert สีกรม', 1, 1, '2017-08-28 00:00:00', '2017-08-28 00:00:00', 2000, 10, 0, 50, 1, 0, 0, 'Convert', 'bangna', 'size 37-41\r\ncolor กรม', 0, 13.666, 100.581, 'a_convert_navy_01.JPG');

-- --------------------------------------------------------

--
-- Table structure for table `picture`
--

CREATE TABLE `picture` (
  `id` int(11) NOT NULL,
  `o_id` int(11) DEFAULT NULL,
  `p_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `p_detail` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `picture`
--

INSERT INTO `picture` (`id`, `o_id`, `p_name`, `p_detail`) VALUES
(1, 1, 'a_convert_white_01.JPG', NULL),
(2, 1, 'a_convert_white_02.JPG', NULL),
(3, 1, 'a_convert_white_03.JPG', NULL),
(4, 1, 'a_convert_white_04.JPG', NULL),
(5, 2, 'a_convert_black_01.JPG', NULL),
(6, 2, 'a_convert_black_02.JPG', NULL),
(7, 3, 'a_convert_navy_01.JPG', NULL),
(8, 3, 'a_convert_navy_02.JPG', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `detail_order`
--
ALTER TABLE `detail_order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`d_id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`m_id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`o_id`);

--
-- Indexes for table `picture`
--
ALTER TABLE `picture`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `detail_order`
--
ALTER TABLE `detail_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `discount`
--
ALTER TABLE `discount`
  MODIFY `d_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `m_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `o_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `picture`
--
ALTER TABLE `picture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
