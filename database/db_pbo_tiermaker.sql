-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 21, 2026 at 11:54 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_pbo_tiermaker`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_hero`
--

CREATE TABLE `tb_hero` (
  `id_hero` bigint NOT NULL,
  `nama_hero` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `gambar` text,
  `gambar_kustom` varchar(255) DEFAULT NULL,
  `status_hero` varchar(255) DEFAULT NULL,
  `gambar_custom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_hero`
--

INSERT INTO `tb_hero` (`id_hero`, `nama_hero`, `role`, `status`, `gambar`, `gambar_kustom`, `status_hero`, `gambar_custom`) VALUES
(1, 'Marcel', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_42/100_df7603c292198bf4aa7b551d401ea5c1.png', NULL, NULL, NULL),
(2, 'Sora', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_40/100_8143d7bbd4318d7c699908e808de885e.png', NULL, NULL, NULL),
(3, 'Obsidia', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_18/100_9c3daef5625bfade5c4cb8a219845dd3.png', NULL, NULL, NULL),
(4, 'Zetian', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_90/100_8d965f05f84621a51f799aeb8fb5f4c4.png', NULL, NULL, NULL),
(6, 'Lukas', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_20/100_63040edd0cf15b815fcbbb8b2d08d7f7.png', NULL, NULL, NULL),
(7, 'Suyou', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_20/100_5e4ca70172332dde18bb1dc158ccc5c8.png', NULL, NULL, NULL),
(8, 'Zhuxin', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_92/100_595f85bc90df3889c711c6b1f02dc02d.png', NULL, NULL, NULL),
(9, 'Chip', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_79224d297f14377ad2eda8543432330d.png', NULL, NULL, NULL),
(10, 'Cici', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_60e3e11da30f404c77fff9e22d3bdc72.png', NULL, NULL, NULL),
(11, 'Nolan', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_9012ee9f73fbb4db4e1953e5fb5172e1.png', NULL, NULL, NULL),
(12, 'Ixia', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_f070d82521ecd2e14d4ef3f25880830a.png', NULL, NULL, NULL),
(13, 'Arlott', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_f666faa5ec6be5353f10dcd1d8997a42.png', NULL, NULL, NULL),
(14, 'Novaria', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_57eeea6d5bd8d21229de8df79751db9f.png', NULL, NULL, NULL),
(15, 'Joy', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_d3a3a55c22000ae78732fed8cba2efef.png', NULL, NULL, NULL),
(16, 'Fredrinn', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_dea170bfd5f26c41fb04e5edf72afedb.png', NULL, NULL, NULL),
(17, 'Julian', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_5511ddc0ad2789b525f32ef572b017eb.png', NULL, NULL, NULL),
(18, 'Xavier', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_df0a8dfc494f85ed9bbc4512cc1e5d3c.png', NULL, NULL, NULL),
(19, 'Melissa', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_1fa7436301fea3f13fbcd4772051d22d.png', NULL, NULL, NULL),
(20, 'Yin', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_92/100_ee1c12c84f49514a30e405fb5c617796.png', NULL, NULL, NULL),
(21, 'Floryn', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_5a57b91e4914cf071a3849e352e530a5.png', NULL, NULL, NULL),
(22, 'Edith', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_daab57918de01a6d5bb2ed6f45808a7e.png', NULL, NULL, NULL),
(23, 'Valentina', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_24837021ec9d7aaf41b13fa55b6d13c9.png', NULL, NULL, NULL),
(24, 'Aamon', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_b3a7602fe7ffd1e54bf8ea79ceadfa72.png', NULL, NULL, NULL),
(25, 'Aulus', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_60_2/100_1366d775809e52ee6526b5b58d93cdff.png', NULL, NULL, NULL),
(26, 'Natan', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_da02742f59013365923b216420bc4082.png', NULL, NULL, NULL),
(27, 'Phoveus', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_06/100_af21c0dd8b5308c27974bff900803a9a.png', NULL, NULL, NULL),
(28, 'Beatrix', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_4687eca874feec9017b448a0b9110d65.png', NULL, NULL, NULL),
(29, 'Gloo', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_642/100_c472fe0233e5ef84a3ac9ba4a229d09f.png', NULL, NULL, NULL),
(30, 'Paquito', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_e2b6d9c6d82b4a27f0bb2710c8ead3e8.png', NULL, NULL, NULL),
(31, 'Mathilda', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_24d08ed788a9f5984bcf3b732ddcaf04.png', NULL, NULL, NULL),
(32, 'Yve', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_92/100_d9b0a1d92b1c4b3643d6332fd66aa8e6.png', NULL, NULL, NULL),
(33, 'Brody', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_f18e45a9a4cb45897e1f614593ff4497.png', NULL, NULL, NULL),
(34, 'Barats', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_6495be044c2d28106e200f6918391d54.png', NULL, NULL, NULL),
(35, 'Khaleed', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_540a6a9d343842674ce002082366ec9d.png', NULL, NULL, NULL),
(36, 'Benedetta', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_b4a5e537894bdc00787e80e4d3ada5dd.png', NULL, NULL, NULL),
(37, 'Luo Yi', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_103541726507f5ce102689f04fe215e8.png', NULL, NULL, NULL),
(38, 'Yu Zhong', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_92/100_9823ce4fe5eb89c0082b24f6d5ea67f6.png', NULL, NULL, NULL),
(39, 'Popol and Kupa', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_1acbb23b9a50f412104047a60eb18808.png', NULL, NULL, NULL),
(40, 'Atlas', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_d974ac796678180ff8724b88e192898b.png', NULL, NULL, NULL),
(41, 'Carmilla', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_6fb8f120dafdcbc1b5da2a2667016ad5.png', NULL, NULL, NULL),
(42, 'Cecilion', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_045399c265021d47da6512e6de20b64f.png', NULL, NULL, NULL),
(43, 'Silvanna', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_5e7b297af7c6e32a420b897aa4998071.png', NULL, NULL, NULL),
(44, 'Wanwan', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_47/100_44bfa1dc44deb8d7620605faaa9ffae7.png', NULL, NULL, NULL),
(45, 'Masha', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_c97a314dc260dfad1311512ddc03f936.png', NULL, NULL, NULL),
(46, 'Baxia', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_80e408203869ca99302af195ac4f756c.png', NULL, NULL, NULL),
(47, 'Lylia', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_706573f138055c3df3df94948b4f26bd.png', NULL, NULL, NULL),
(48, 'Dyrroth', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_18/100_8ec13b59871b2862d773beac2d69fa3e.png', NULL, NULL, NULL),
(49, 'Ling', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_92/100_017bade52b9fc94bbc12615de6d75c08.png', NULL, NULL, NULL),
(50, 'X.Borg', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_efe9c7ed8d8f84f1bb0f88a3e08de5fc.png', NULL, NULL, NULL),
(51, 'Terizla', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_c4bb270c28c7be663f57e993b0d6d3d8.png', NULL, NULL, NULL),
(52, 'Esmeralda', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_4661a64a2c6b724e7b67032fcbface27.png', NULL, NULL, NULL),
(53, 'Guinevere', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_901bdd5d4432a2c0290dfc71df615a5a.png', NULL, NULL, NULL),
(54, 'Granger', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_22/100_a7729262d5b4abdc34ae5181e964c235.png', NULL, NULL, NULL),
(55, 'Khufra', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_2fe99f4001211d18b3d2b95d0d3dc395.png', NULL, NULL, NULL),
(56, 'Badang', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_9fb1784545a48aef42241fc7a719c575.png', NULL, NULL, NULL),
(57, 'Faramis', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_04e575d648d7f7ac1174f4369595c3a2.png', NULL, NULL, NULL),
(58, 'Kadita', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_42b40ae7741d2eb81148c8f1e1ff614c.png', NULL, NULL, NULL),
(59, 'Minsitthar', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_c5d24a1dad6cef21de2698a4ed1d80ce.png', NULL, NULL, NULL),
(60, 'Harith', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_d228a823b477fdf6c458c829c2e62bcf.png', NULL, NULL, NULL),
(61, 'Thamuz', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_18/100_b5ad55e408b68acf6a86e7ff76f5f569.png', NULL, NULL, NULL),
(62, 'Kimmy', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_642/100_61295dfefd369004e5e4a7f4fc86647b.png', NULL, NULL, NULL),
(63, 'Belerick', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_5f2a9eca0bafd4ed8dc39a93c771b3af.png', NULL, NULL, NULL),
(64, 'Hanzo', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_47/100_d2d28d2fcb060726fa27553920ca1a33.png', NULL, NULL, NULL),
(65, 'Lunox', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_e162688afdcaf3f6498af30badfe31c5.png', NULL, NULL, NULL),
(66, 'Leomord', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_554cdf13a7587fe08c5deed60132c61c.png', NULL, NULL, NULL),
(67, 'Vale', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_2a7a8d1531a1c4f8524880413535348d.png', NULL, NULL, NULL),
(68, 'Claude', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_e1097b9d3e1d5e9f14600d32e8b18acd.png', NULL, NULL, NULL),
(69, 'Aldous', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_0eb32187d08f14779585a8be53b83f01.png', NULL, NULL, NULL),
(70, 'Selena', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_c3f967121519ae40509c5b2fdf52b19d.png', NULL, NULL, NULL),
(71, 'Kaja', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_a23983833ad5bee7c83422c8ff727115.png', NULL, NULL, NULL),
(72, 'Chang\'e', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_46cfe9c30e8a14f85b4d022496aca274.png', NULL, NULL, NULL),
(73, 'Hanabi', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_8a9c1966feb34e85d7bdcc1ed01ffb5d.png', NULL, NULL, NULL),
(74, 'Uranus', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_2e15a0a506aaecd9b3de40a8cc9f7ec7.png', NULL, NULL, NULL),
(75, 'Martis', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_cc8124b0eb6ca22d950b000744d69fbf.png', NULL, NULL, NULL),
(76, 'Valir', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_bf16690876761b80822df90eb3320d69.png', NULL, NULL, NULL),
(77, 'Gusion', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_c6cd232de60da5372a7101a203e56554.png', NULL, NULL, NULL),
(78, 'Angela', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_c7e0183956b2c2fd6d3fa0b18fe46917.png', NULL, NULL, NULL),
(79, 'Jawhead', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_bd87c30b6c7de6ae3b5aa56162c48c8b.png', NULL, NULL, NULL),
(80, 'Lesley', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_08770893183018333f54a98e63c41ee3.png', NULL, NULL, NULL),
(81, 'Pharsa', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_10151d43910e62f7aa9ce08df481a20f.png', NULL, NULL, NULL),
(82, 'Helcurt', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_6abf0c552b59b8ca4cbc1af3662ef176.png', NULL, NULL, NULL),
(83, 'Zhask', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_d8f170087cb2d5b71bb22a0a4664a927.png', NULL, NULL, NULL),
(84, 'Hylos', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_195ad9af866afaab415ae23a6be13b45.png', NULL, NULL, NULL),
(85, 'Diggie', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_b1bdf46136cb8a7903dae6d58e8349cb.png', NULL, NULL, NULL),
(86, 'Lancelot', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_dc4b821e49b904715172136017798da3.png', NULL, NULL, NULL),
(87, 'Odette', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_47/100_d9251718a8894546ba04cfa9ca68dedc.png', NULL, NULL, NULL),
(88, 'Argus', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_0bd96658e5b8ec578226ea1622bd7231.png', NULL, NULL, NULL),
(89, 'Grock', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_902/100_f8b8c8964d3202b7b762947ac96f1ed3.png', NULL, NULL, NULL),
(90, 'Irithel', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_a7c51b517dbce49dac4e537bd4ab4f87.png', NULL, NULL, NULL),
(91, 'Harley', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_3aa6964c07a70f9b125da447f320e1ac.png', NULL, NULL, NULL),
(92, 'Gatotkaca', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_96f9dbbc096e0f0a28f9b9e587d06a9c.png', NULL, NULL, NULL),
(93, 'Karrie', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_294576f2bd0dcbcc9041031969d0eb09.png', NULL, NULL, NULL),
(94, 'Roger', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_b40e578f13465b3ae99d0a9baac7ecc9.png', NULL, NULL, NULL),
(95, 'Vexana', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_743edd1702084c4ed247908d698bca77.png', NULL, NULL, NULL),
(96, 'Lapu-Lapu', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_28036c4584c914c5aad4d2feeafb2452.png', NULL, NULL, NULL),
(97, 'Aurora', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_f9b2be0150361018bb98f941b9667c42.png', NULL, NULL, NULL),
(98, 'Hilda', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_acd700708dc515f5c63a08f8835a9941.png', NULL, NULL, NULL),
(100, 'Cyclops', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_68da456f9c01b2f88d29ee320db181ed.png', NULL, NULL, NULL),
(101, 'Johnson', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_85ff988c6660b4d04f8dd3d40df988bb.png', NULL, NULL, NULL),
(102, 'Moskov', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_a60a866ab6752a7f66766e720da987ea.png', NULL, NULL, NULL),
(103, 'Yi Sun-shin', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_961378be3f498d42c25b3defd1635ad1.png', NULL, NULL, NULL),
(104, 'Ruby', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_4b96c75b136290576d849309722d4d20.png', NULL, NULL, NULL),
(105, 'Alpha', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_86c9f91f530727db6498f920d19180d1.png', NULL, NULL, NULL),
(106, 'Sun', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_117b5cdcc13232157075ce7b7f6177e9.png', NULL, NULL, NULL),
(107, 'Chou', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_158209b180032c4564b8f3bde8c48888.png', NULL, NULL, NULL),
(108, 'Kagura', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_da424b020b8ac8235d64a1b8a09aa749.png', NULL, NULL, NULL),
(109, 'Natalia', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_fbb5065b6ff174d1a20ccf5b7f523514.png', NULL, NULL, NULL),
(110, 'Gord', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_47/100_9513f5e10ec33f76747732eaf2082259.png', NULL, NULL, NULL),
(111, 'Freya', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_18_2/100_b9c34c88762ca8e66ccd4d84071bf0bc.png', NULL, NULL, NULL),
(112, 'Hayabusa', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_9cc074562291a02644a0ddae28eeaa42.png', NULL, NULL, NULL),
(113, 'Lolita', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_474cea36a4bfdc7bf7d94530853a99b2.png', NULL, NULL, NULL),
(114, 'Minotaur', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_3ecd2c0843df7ec85044dafff6bf4553.png', NULL, NULL, NULL),
(115, 'Layla', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_b2b38e9406ea0de0b866db7674feea0f.png', NULL, NULL, NULL),
(116, 'Fanny', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_3391df36d6dcc54dd1c417098e15ec59.png', NULL, NULL, NULL),
(117, 'Zilong', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_9db57da4f0daef6d432676e4f19101ed.png', NULL, NULL, NULL),
(118, 'Eudora', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_42/100_87b2a655b254c136dce8976e21935a80.png', NULL, NULL, NULL),
(119, 'Rafaela', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_68277dce415742c4a98883151c693a07.png', NULL, NULL, NULL),
(120, 'Clint', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_5a8345c69a4c9c611fdfce91089fe74a.png', NULL, NULL, NULL),
(121, 'Bruno', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_ff39deb9c6afec3d977fdbe9d86f78cb.png', NULL, NULL, NULL),
(122, 'Bane', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_7e4e74bb161da0f477cc0d1819fa39e6.png', NULL, NULL, NULL),
(123, 'Franco', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_60_2/100_9bed3c0095335606e0ce616c6e5a8553.png', NULL, NULL, NULL),
(124, 'Akai', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_28f223447f0174336ff0922d364d81d3.png', NULL, NULL, NULL),
(125, 'Karina', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_f49394186dc0e55d545da8377be83280.png', NULL, NULL, NULL),
(126, 'Alucard', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_0f51b6906e08897aa02330d65b0deeac.png', NULL, NULL, NULL),
(127, 'Tigreal', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_8b30576754be1a4f8bebd09df8d6bec7.png', NULL, NULL, NULL),
(128, 'Nana', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_ee1e0d80d87bb614a0c552ef028f85ce.png', NULL, NULL, NULL),
(129, 'Alice', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_18/100_18300163a5a912a84adb52b8d59b4618.png', NULL, NULL, NULL),
(130, 'Saber', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_6bbfac806f29d29f17fea9e98d2d2fee.png', NULL, NULL, NULL),
(131, 'Balmond', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_2_1_18_2/100_df9a6990b1946d045e4d3a46c90725cf.png', NULL, NULL, NULL),
(132, 'Miya', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_da894b37bfb5cadb32307f371f31918a.png', NULL, NULL, NULL),
(133, 'Kalea', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage_1_9_64/100_4f3d4649e301c76daf20bd8811f3095c.png', NULL, NULL, NULL),
(134, 'Estes', 'Unset', 'API', 'https://akmweb.youngjoygame.com/web/svnres/img/mlbb/homepage/100_ff4de3b0aabd1d2f6e184db1c831f6a9.png', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tierlist`
--

CREATE TABLE `tb_tierlist` (
  `id_tier` bigint NOT NULL,
  `id_hero` bigint DEFAULT NULL,
  `lane` varchar(100) DEFAULT NULL,
  `grade_tier` varchar(10) DEFAULT NULL,
  `jenis_tierlist` varchar(50) DEFAULT NULL,
  `nama_tierlist` varchar(255) DEFAULT NULL,
  `susunan_hero` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_tierlist`
--

INSERT INTO `tb_tierlist` (`id_tier`, `id_hero`, `lane`, `grade_tier`, `jenis_tierlist`, `nama_tierlist`, `susunan_hero`) VALUES
(7, NULL, NULL, NULL, 'COMPETITIVE', 'Apakah', '{\"C\":[\"Kalea\"],\"D\":[\"Obsidia\"],\"F\":[\"Marcel\",\"Cici\"]}'),
(8, NULL, NULL, NULL, 'CASUAL', 'Atur', '{\"S\":[\"Kalea\"],\"D\":[\"Obsidia\"],\"F\":[\"Marcel\"]}'),
(9, NULL, NULL, NULL, 'CASUAL', 'test', '{\"S\":[\"Suyou\",\"Marcel\"],\"F\":[\"Zhuxin\"]}'),
(10, NULL, NULL, NULL, 'CASUAL', 'Atur', '{\"C\":[\"Zhuxin\"]}'),
(11, NULL, NULL, NULL, 'CASUAL', 'tesatest', '{\"S\":[\"Zetian\",\"Chip\",\"Cici\",\"Ixia\"],\"A\":[\"Sora\"],\"B\":[\"Marcel\",\"Obsidia\"],\"C\":[\"Suyou\"],\"D\":[\"Lukas\"],\"F\":[\"Zhuxin\"]}');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_hero`
--
ALTER TABLE `tb_hero`
  ADD PRIMARY KEY (`id_hero`);

--
-- Indexes for table `tb_tierlist`
--
ALTER TABLE `tb_tierlist`
  ADD PRIMARY KEY (`id_tier`),
  ADD KEY `id_hero` (`id_hero`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_hero`
--
ALTER TABLE `tb_hero`
  MODIFY `id_hero` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=138;

--
-- AUTO_INCREMENT for table `tb_tierlist`
--
ALTER TABLE `tb_tierlist`
  MODIFY `id_tier` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_tierlist`
--
ALTER TABLE `tb_tierlist`
  ADD CONSTRAINT `tb_tierlist_ibfk_1` FOREIGN KEY (`id_hero`) REFERENCES `tb_hero` (`id_hero`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
