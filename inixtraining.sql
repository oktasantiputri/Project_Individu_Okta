-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 08, 2022 at 09:13 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inixtraining`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_kelas`
--

CREATE TABLE `tb_detail_kelas` (
  `id_detail_kls` int(10) NOT NULL,
  `id_kls` int(10) NOT NULL,
  `id_pst` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_detail_kelas`
--

INSERT INTO `tb_detail_kelas` (`id_detail_kls`, `id_kls`, `id_pst`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 2, 6),
(7, 2, 7),
(8, 2, 8),
(9, 2, 9),
(10, 2, 10),
(11, 3, 11),
(12, 3, 12),
(13, 3, 13),
(14, 3, 14),
(15, 3, 15),
(16, 4, 1),
(17, 4, 2),
(18, 4, 3),
(19, 4, 4),
(20, 4, 5),
(21, 5, 6),
(22, 5, 7),
(23, 5, 8),
(24, 5, 9),
(25, 5, 10),
(26, 6, 11),
(27, 6, 12),
(28, 6, 13),
(29, 6, 14),
(30, 6, 15),
(31, 7, 1),
(32, 7, 2),
(33, 7, 3),
(34, 7, 4),
(35, 7, 5),
(36, 8, 6),
(37, 8, 7),
(38, 8, 8),
(39, 8, 9),
(40, 8, 10),
(41, 9, 11),
(42, 9, 12),
(43, 9, 13),
(44, 9, 14),
(45, 9, 15),
(46, 10, 1),
(47, 10, 2),
(48, 10, 3),
(49, 10, 4),
(50, 10, 5),
(51, 11, 6),
(52, 11, 7),
(53, 11, 8),
(54, 11, 9),
(55, 11, 10),
(56, 12, 11),
(57, 12, 12),
(58, 12, 13),
(59, 12, 14),
(60, 12, 15),
(61, 13, 1),
(62, 13, 2),
(63, 13, 3),
(64, 13, 4),
(65, 13, 5),
(66, 14, 6),
(67, 14, 7),
(68, 14, 8),
(69, 14, 9),
(70, 14, 10),
(71, 15, 11),
(72, 15, 12),
(73, 15, 13),
(74, 15, 14),
(75, 15, 15),
(76, 16, 1),
(77, 16, 2),
(78, 16, 3),
(79, 16, 4),
(80, 16, 5),
(81, 17, 6),
(82, 17, 7),
(83, 17, 8),
(84, 17, 9),
(85, 17, 10),
(86, 18, 11),
(87, 18, 12),
(88, 18, 13),
(89, 18, 14),
(90, 18, 15),
(91, 19, 1),
(92, 19, 2),
(93, 19, 3),
(94, 19, 4),
(95, 19, 5),
(96, 20, 6),
(97, 20, 7),
(98, 20, 8),
(99, 20, 9),
(100, 20, 10);

-- --------------------------------------------------------

--
-- Table structure for table `tb_instruktur`
--

CREATE TABLE `tb_instruktur` (
  `id_ins` int(10) NOT NULL,
  `nama_ins` varchar(50) NOT NULL,
  `email_ins` varchar(50) NOT NULL,
  `hp_ins` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_instruktur`
--

INSERT INTO `tb_instruktur` (`id_ins`, `nama_ins`, `email_ins`, `hp_ins`) VALUES
(1, 'Rondi Hidayat', 'rondihidayat@gmail.com', '085213695821'),
(2, 'Yusuf Rizal', 'yusufrizal@gmail.com', '082145698741'),
(3, 'Jonah Marais', 'jonahmarais@gmail.com', '085214789632'),
(4, 'Zach Herron', 'zherron@gmail.com', '085147962587'),
(5, 'Risa Putri', 'risputri@gmail.com', '081547963258'),
(6, 'Rean Putra', 'reanputra@gmail.com', '081547963258'),
(7, 'Rena Riana', 'renariana@gmail.com', '081568947523'),
(8, 'Ronald Saputra', 'ronsaputra@gmail.com', '083854692514'),
(9, 'Rina Kamala', 'rinkamala@gmail.com', '085216985234'),
(10, 'Sharon Kamala', 'skamala@gmail.com', '083856984520');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kelas`
--

CREATE TABLE `tb_kelas` (
  `id_kls` int(10) NOT NULL,
  `tgl_mulai_kls` date NOT NULL,
  `tgl_akhir_kls` date NOT NULL,
  `id_ins` int(10) NOT NULL,
  `id_mat` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_kelas`
--

INSERT INTO `tb_kelas` (`id_kls`, `tgl_mulai_kls`, `tgl_akhir_kls`, `id_ins`, `id_mat`) VALUES
(1, '2022-01-03', '2022-02-03', 1, 1),
(2, '2022-02-04', '2022-03-04', 2, 2),
(3, '2022-03-07', '2022-04-07', 3, 3),
(4, '2022-04-08', '2022-05-09', 4, 4),
(5, '2022-05-10', '2022-05-27', 5, 5),
(6, '2022-05-30', '2022-06-10', 6, 6),
(7, '2022-06-13', '2022-06-30', 7, 7),
(8, '2022-07-01', '2022-07-15', 8, 8),
(9, '2022-07-16', '2022-07-31', 9, 9),
(10, '2022-08-01', '2022-08-05', 10, 10),
(11, '2022-08-08', '2022-08-12', 1, 1),
(12, '2022-08-15', '2022-08-20', 2, 2),
(13, '2022-08-22', '2022-08-27', 3, 3),
(14, '2022-09-01', '2022-09-08', 4, 4),
(15, '2022-09-09', '2022-09-23', 5, 5),
(16, '2022-10-01', '2022-10-14', 6, 6),
(17, '2022-10-15', '2022-10-29', 7, 7),
(18, '2022-10-31', '2022-11-14', 8, 8),
(19, '2022-11-15', '2022-11-30', 9, 9),
(20, '2022-12-01', '2022-12-31', 10, 10);

-- --------------------------------------------------------

--
-- Table structure for table `tb_materi`
--

CREATE TABLE `tb_materi` (
  `id_mat` int(10) NOT NULL,
  `nama_mat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_materi`
--

INSERT INTO `tb_materi` (`id_mat`, `nama_mat`) VALUES
(1, 'Introduction to SQL: Oracle'),
(2, 'Android Programming with Java'),
(3, 'MySQL Full Package'),
(4, 'Data Management and Data Governance'),
(5, 'Data Wranglinig with Python'),
(6, 'Information System Auditor'),
(7, 'Information Security Manager'),
(8, 'UX Designer Fundamental'),
(9, 'Data Wranglinig with Python'),
(10, 'Mobile App Development with Ionic');

-- --------------------------------------------------------

--
-- Table structure for table `tb_peserta`
--

CREATE TABLE `tb_peserta` (
  `id_pst` int(10) NOT NULL,
  `nama_pst` varchar(50) NOT NULL,
  `email_pst` varchar(50) NOT NULL,
  `hp_pst` varchar(13) NOT NULL,
  `instansi_pst` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_peserta`
--

INSERT INTO `tb_peserta` (`id_pst`, `nama_pst`, `email_pst`, `hp_pst`, `instansi_pst`) VALUES
(1, 'Oktasanti Puteri Kusumawardhani', 'oktasantiputri@gmail.com', '085217963915', 'Maybank'),
(2, 'Adi Dwi Anugerah', 'adanugerah@gmail.com', '085147963251', 'Personal'),
(3, 'Yentity Marbun', 'yenmarbun@gmail.com', '082110802409', 'PT. Kumon'),
(4, 'R Agung Setiawan', 'rasetiwan@gmail.com', '085147236958', 'PT. Cipta Mandiri'),
(5, 'Ari Wulandari', 'ariwulandari@gmail.com', '085156987412', 'PT. Sehat Sejahtera'),
(6, 'Arnes Sibuea', 'asibuea@gmail.com', '085624196587', 'PT. Teknologi Maju'),
(7, 'Maruli Sibuea', 'msibuea@gmail.com', '085689254712', 'PT. Cipta Mandiri'),
(8, 'Sarwo Wibowo', 'sarwow@gmail.com', '081258965478', 'PT. Teknologi Maju'),
(9, 'Natal Kristanto', 'natkris@gmail.com', '085147236958', 'PT. Sehat Sejahtera'),
(10, 'Netratna Krisnara Budi', 'nkrisnara@gmail.com', '081526345896', 'PT. Kumon'),
(11, 'Nabilla Wulan', 'nabillaw@gmail.com', '085256987412', 'Personal'),
(12, 'Rosevin Marbun', 'rosmarbun@gmail.com', '085147236958', 'PT. Cipta Mandiri'),
(13, 'Rudyard Sianipar', 'rudysianipar@gmail.com', '081655223478', 'Personal'),
(14, 'Ida Lestari', 'idalestari@gmail.com', '085266998547', 'Personal'),
(15, 'Neriza Arviana', 'narviana@gmail.com', '085122447896', 'Personal');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  ADD PRIMARY KEY (`id_detail_kls`),
  ADD KEY `id_kls` (`id_kls`),
  ADD KEY `id_pst` (`id_pst`);

--
-- Indexes for table `tb_instruktur`
--
ALTER TABLE `tb_instruktur`
  ADD PRIMARY KEY (`id_ins`);

--
-- Indexes for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD PRIMARY KEY (`id_kls`),
  ADD KEY `id_ins` (`id_ins`),
  ADD KEY `id_mat` (`id_mat`);

--
-- Indexes for table `tb_materi`
--
ALTER TABLE `tb_materi`
  ADD PRIMARY KEY (`id_mat`);

--
-- Indexes for table `tb_peserta`
--
ALTER TABLE `tb_peserta`
  ADD PRIMARY KEY (`id_pst`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  MODIFY `id_detail_kls` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `tb_instruktur`
--
ALTER TABLE `tb_instruktur`
  MODIFY `id_ins` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  MODIFY `id_kls` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `tb_materi`
--
ALTER TABLE `tb_materi`
  MODIFY `id_mat` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tb_peserta`
--
ALTER TABLE `tb_peserta`
  MODIFY `id_pst` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  ADD CONSTRAINT `tb_detail_kelas_ibfk_1` FOREIGN KEY (`id_kls`) REFERENCES `tb_kelas` (`id_kls`),
  ADD CONSTRAINT `tb_detail_kelas_ibfk_2` FOREIGN KEY (`id_pst`) REFERENCES `tb_peserta` (`id_pst`);

--
-- Constraints for table `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD CONSTRAINT `tb_kelas_ibfk_1` FOREIGN KEY (`id_ins`) REFERENCES `tb_instruktur` (`id_ins`),
  ADD CONSTRAINT `tb_kelas_ibfk_2` FOREIGN KEY (`id_mat`) REFERENCES `tb_materi` (`id_mat`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
