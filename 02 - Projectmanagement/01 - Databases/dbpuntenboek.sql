-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 07, 2025 at 07:14 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbpuntenboek`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblgebruiker`
--

DROP TABLE IF EXISTS `tblgebruiker`;
CREATE TABLE IF NOT EXISTS `tblgebruiker` (
  `nummer` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(30) NOT NULL,
  `voornaam` varchar(30) NOT NULL,
  `straat` varchar(30) NOT NULL,
  `nr` varchar(4) NOT NULL,
  `postcode` varchar(30) NOT NULL,
  `plaats` varchar(30) NOT NULL,
  `geslacht` varchar(30) NOT NULL,
  `klas` varchar(10) NOT NULL,
  `wachtwoord` varchar(30) NOT NULL,
  PRIMARY KEY (`nummer`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblgebruiker`
--

INSERT INTO `tblgebruiker` (`nummer`, `naam`, `voornaam`, `straat`, `nr`, `postcode`, `plaats`, `geslacht`, `klas`, `wachtwoord`) VALUES
(1, 'Andries', 'Mylan', 'Guldensporenlaan', '1', '2820', 'BONHEIDEN', 'M', '6AD', 'wachtwoord'),
(2, 'Aoulad Abdelkader', 'Mohamed', 'Guldensporenlaan', '2', '2820', 'BONHEIDEN', 'M', '6AD', 'wachtwoord'),
(3, 'Berrag', 'Imad', 'Guldensporenlaan', '3', '2820', 'BONHEIDEN', 'M', '6AD', 'wachtwoord'),
(4, 'Borges', 'Daniel', 'Guldensporenlaan', '4', '2820', 'BONHEIDEN', 'M', '6AD', 'wachtwoord'),
(5, 'Bossaert', 'Warre', 'Guldensporenlaan', '5', '2820', 'BONHEIDEN', 'M', '6AD', 'wachtwoord'),
(6, 'Casteels', 'Lowie', 'Guldensporenlaan', '6', '2820', 'BONHEIDEN', 'M', '6AD', 'wachtwoord'),
(7, 'Cornelis', 'Jelbe', 'Vogelstraat', '7', '2580', 'PUTTE', 'M', '6AD', 'wachtwoord'),
(8, 'De Nies', 'Jonathan', 'Vogelstraat', '8', '2580', 'PUTTE', 'M', '6AD', 'wachtwoord'),
(9, 'Doms', 'Anton', 'Vogelstraat', '9', '2580', 'PUTTE', 'M', '6AD', 'wachtwoord'),
(10, 'El Hachioui', 'Abdelilah', 'Vogelstraat', '10', '2580', 'PUTTE', 'M', '6AD', 'wachtwoord'),
(11, 'Evloev', 'Abdullah', 'Frans Halsvest', '11', '2800', 'MECHELEN', 'M', '6AD', 'wachtwoord'),
(12, 'Possemiers', 'Jentl', 'Frans Halsvest', '12', '2800', 'MECHELEN', 'M', '6AD', 'wachtwoord'),
(13, 'Raes', 'Thibeau', 'Frans Halsvest', '13', '2800', 'MECHELEN', 'M', '6AD', 'wachtwoord'),
(14, 'Van Puyenbroeck', 'Siebe', 'Frans Halsvest', '14', '2800', 'MECHELEN', 'M', '6AD', 'wachtwoord'),
(15, 'Van Vliet', 'Lucas', 'Frans Halsvest', '15', '2800', 'MECHELEN', 'M', '6AD', 'wachtwoord'),
(16, 'Wijns', 'Gerrit', '', '', '', '', '', 'LKR', 'WachtwoordWijns'),
(17, 'Maes', 'Hanne', '', '', '', '', '', 'LKR', 'WachtwoordMaes');

-- --------------------------------------------------------

--
-- Table structure for table `tblpunt`
--

DROP TABLE IF EXISTS `tblpunt`;
CREATE TABLE IF NOT EXISTS `tblpunt` (
  `volgnummer` int(11) NOT NULL AUTO_INCREMENT,
  `leerlingnummer` int(11) NOT NULL,
  `vaknaam` varchar(20) NOT NULL,
  `score` int(11) NOT NULL,
  `maximum` int(11) NOT NULL,
  PRIMARY KEY (`volgnummer`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblpunt`
--

INSERT INTO `tblpunt` (`volgnummer`, `leerlingnummer`, `vaknaam`, `score`, `maximum`) VALUES
(1, 2, 'Wiskunde', 7, 10),
(2, 2, 'Wiskunde', 8, 15),
(3, 3, 'Wiskunde', 2, 10),
(4, 3, 'Wiskunde', 12, 15),
(5, 2, 'Databases', 4, 10),
(6, 2, 'Databases', 7, 15),
(7, 3, 'Databases', 10, 10),
(8, 3, 'Databases', 12, 15),
(9, 2, 'Software', 5, 10),
(10, 2, 'Software', 7, 15),
(11, 3, 'Software', 10, 10),
(12, 3, 'Software', 15, 15);

-- --------------------------------------------------------

--
-- Table structure for table `tblpuntenboek`
--

DROP TABLE IF EXISTS `tblpuntenboek`;
CREATE TABLE IF NOT EXISTS `tblpuntenboek` (
  `puntenboeknr` int(11) NOT NULL AUTO_INCREMENT,
  `leerkrachtnr` int(11) NOT NULL,
  `vaknaam` varchar(20) NOT NULL,
  `klasnaam` varchar(6) NOT NULL,
  PRIMARY KEY (`puntenboeknr`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblpuntenboek`
--

INSERT INTO `tblpuntenboek` (`puntenboeknr`, `leerkrachtnr`, `vaknaam`, `klasnaam`) VALUES
(1, 16, 'Wiskunde', '6AD'),
(2, 16, 'Databases', '6AD'),
(3, 17, 'Software', '6AD'),
(4, 17, 'Hardware', '6AD');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
