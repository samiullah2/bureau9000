-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: com-linweb190.srv.combell-ops.net:3306
-- Generation Time: Jun 10, 2021 at 11:20 AM
-- Server version: 5.7.33-36-log
-- PHP Version: 7.1.25-1+0~20181207224605.11+jessie~1.gbpf65b84

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ID328964_bureau9000`
--

-- --------------------------------------------------------

--
-- Table structure for table `analist`
--

CREATE TABLE `analist` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `voornaam` varchar(255) NOT NULL,
  `achternaam` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gebouw`
--

CREATE TABLE `gebouw` (
  `id` int(11) NOT NULL,
  `naam` varchar(50) NOT NULL,
  `hoogte` int(255) NOT NULL COMMENT 'in meters',
  `adres` varchar(255) NOT NULL,
  `postcode` int(4) NOT NULL,
  `gemeente` varchar(255) NOT NULL,
  `functie` varchar(75) NOT NULL,
  `projectId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gebouw`
--

INSERT INTO `gebouw` (`id`, `naam`, `hoogte`, `adres`, `postcode`, `gemeente`, `functie`, `projectId`) VALUES
(1, 'gebouwA', 400, 'Gentse 34', 1080, 'Molenbeek', 'functie', 1),
(2, 'gebouwB', 400, 'Gentse 34', 1080, 'Molenbeek', 'functie', 1),
(3, 'Erasmus', 200, 'Schopbaan 4', 1200, 'Leaken', 'Openbare besturen', 1),
(7, 'gebouw A', 50, 'rozenlaan 45', 1000, 'BRUSSEL', 'Industrie', 6);

-- --------------------------------------------------------

--
-- Table structure for table `gebouwRisico`
--

CREATE TABLE `gebouwRisico` (
  `id` int(11) NOT NULL,
  `beschrijving` varchar(1000) NOT NULL,
  `w` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gebouwRisico`
--

INSERT INTO `gebouwRisico` (`id`, `beschrijving`, `w`) VALUES
(1, 'Gebouw heeft geen bliksemafleider', '2.00'),
(2, 'risico 2', '0.10'),
(3, 'risico 3', '6.00'),
(4, 'risico 4', '0.10');

-- --------------------------------------------------------

--
-- Table structure for table `gebouwRisicoGebouw`
--

CREATE TABLE `gebouwRisicoGebouw` (
  `id` int(11) NOT NULL,
  `gebouwId` int(11) NOT NULL,
  `gebouwRisicoId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gebouwRisicoGebouw`
--

INSERT INTO `gebouwRisicoGebouw` (`id`, `gebouwId`, `gebouwRisicoId`) VALUES
(3, 1, 2),
(4, 1, 4),
(5, 2, 3),
(6, 1, 1),
(7, 2, 1),
(8, 3, 4),
(10, 1, 3),
(11, 2, 2),
(17, 7, 1),
(18, 3, 1),
(19, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `kastRisico`
--

CREATE TABLE `kastRisico` (
  `id` int(10) NOT NULL,
  `beschrijving` varchar(1000) NOT NULL,
  `w` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kastRisico`
--

INSERT INTO `kastRisico` (`id`, `beschrijving`, `w`) VALUES
(1, 'De deuren van elektrische lokalen kunnen vrij geopend worden door onbevoegden. Het lokaal is niet gesloten.', '0.10'),
(2, 'Wisselspanning of gelijkspanning', '0.10'),
(3, 'Nominale waarde in Volt (V)', '0.10'),
(4, 'Waarschuwingspictogram aan te brengen', '6.00'),
(5, 'De toegang(en) tot de ruimte van de elektrische dienst. (AREI Art. 9.4.1)', '0.10'),
(6, 'De elektrische installaties en elektrische materieel op LS dat niet volledig beschermd is tegen rechtstreekse aanraking. (AREI Art. 9.4.1.)', '0.10'),
(7, 'Elektrische kasten of borden staan open. \n(AREI Art. 4.2.2.1. Bescherming dmv omhulsels)', '3.00'),
(8, 'Bij werken kan de elektrische installatie niet geheel spanningsloos gezet worden. (Er kan niet gegarandeerd worden dat de installatie weer onder spanning komt tijdens de werkzaamheden).', '0.10'),
(9, 'Elektrische componenten. schakelaars. stekkers. automaten en/of zekeringen zijn zichtbaar beschadigd.', '0.10'),
(10, 'Automaten zijn niet vingerveilig en van het oude type. waardoor er geen garantie is van effectieve onderbreking.', '0.10'),
(11, 'Er bevinden zich afgeknipte of blanke geleiders/kabels in of buiten de kast.', '0.10'),
(12, 'De kast en/of beschermplaat zijn onvoldoende stevig. groot. isolerend. dicht om aanraking met onder spanningstaande delen onmogelijk te maken.', '6.00'),
(13, 'De isolatieklasse van de kast wordt niet gerespecteerd.', '0.10'),
(14, 'Bij werkzaamheden in de nabije omgeving. kunnen de elementen die onder spanning blijven niet afgeschermd worden.', '0.10'),
(15, 'Elektrische borden zijn in voldoende mate geidentificeerd (nummer. naam).', '2.00'),
(16, 'Stroombanen zijn niet of in onvoldoende mate geidentificeerd.', '0.10'),
(17, 'De nominale spanningen zijn niet weergegeven op de elektrische installatie.', '0.10'),
(18, 'Stroombanen die voor de hoofdschakelaar zijn afgetakt hebben of niet de juiste kleurcode en/of zijn niet aangeduid met een opschrift (steeds onder spanning)', '0.10'),
(19, 'De stroomverdelers zijn aan de uiteinden niet correct afge?soleerd.', '0.10'),
(20, 'Leidingen en kabels zijn in onvoldoende mate vastgemaakt of zit niet achter gesloten kabelgoten = kans op isolatiefouten', '0.10'),
(21, 'Leidingen of kabels worden in de kast gebracht zonder correcte kabeldoorvoer => isolatiefout. \n(AREI Art. 5.2.6.1.)', '0.10'),
(22, 'Er is een slechte luchtkwaliteit. hinderende lichteffecten. lawaai. inhaleringsgevaar door een aanwezig product.', '0.10'),
(23, 'In de elektriciteitskast bevinden zich allerlei losse voorwerpen.', '0.10'),
(24, 'Het schakelmateriaal is zodanig geplaatst dat bediening en onderhoud niet bemoeilijkt wordt.', '0.10'),
(25, 'Bij ontgrendelen van noodstoppen of bij herstellen van veiligheidsfuncties. mag de installatie niet automatisch terug starten.', '2.00'),
(26, 'Er is voor de elektrische installatie geen correct elektrisch schema aanwezig.', '2.00'),
(27, 'De correcte kabelberekeningen zijn niet aanwezig.', '2.00'),
(28, 'Het conformiteitsverslag of indienststellingsverslag zijn niet aanwezig.', '0.10'),
(29, 'De laatste twee periodieke keuringsverslagen van de installatie zijn niet aanwezig.', '0.10'),
(30, 'Er zijn geen opleiding of instructie aan medewerkers gegeven.', '0.10'),
(31, 'Er is geen lijst voorhanden met BA4/BA5 personeel en een oplijsting voor welke installaties hun bevoegheid van toepassing is.', '0.10'),
(32, 'De tabel met uitwendige invloedsfactoren is niet voorhanden.', '0.10'),
(33, 'De toegang tot de elektriciteitskast wordt versperd en de hoogte van 1m50 wordt niet gerespecteerd.\n(AREI Art. 5.3.5.1.)', '0.10'),
(34, 'Hoogteverschillen en/of treden zijn duidelijk aangegeven.', '0.10'),
(35, 'De omgeving van de elektrische installatie of de installatie zelf kan vochtig worden. (condensatie. regen. water)', '0.10'),
(36, 'Welke type net betreft het ( IT. TN-C. TN-CS. TN-S. TT)', '0.10'),
(37, 'De bescherming is niet aangepast aan het aardingsstelsel (netsysteem):\n- TT-net: Gebruik van differentieelschakelaars. gebruik van smeltveiligheden en/of automaten.\n- TN-net: Gebruik van smeltveiligheden en/of automaten.\n- IT-net: Gebruik van isolatiewachter voor waarschuwingen bij een eerste fout. gebruik van smeltveiligheden en/of automaten. differentieelschakelaar.', '0.10'),
(38, 'Er zijn zichtbare doorverbindingen of kortgesloten smeltveiligheden of automaten.', '0.10'),
(39, 'Het chassis. bord of deur van de elektrische kast is niet geaard.', '0.10'),
(40, 'De beschermingsgeleiders zijn niet uitgevoerd met geel/groene isolatie.', '0.10'),
(41, 'Er zijn  beschermingsgeleiders  losgekomen of niet aangesloten.', '0.10'),
(42, 'Niet alle stopcontacten en/of stekkers zijn voorzien van een aarding.', '0.10'),
(43, 'Niet alle metalen onderdelen zijn equipotentiaal met elkaar verbonden (metalen leidingen. alle aardingen. metalen onderdelen. overspanningsafleiders.', '0.10'),
(44, 'Niet alle geleiders (fasen. nulleiding en aardingen) zijn mechanisch spanningsvrij gemonteerd. waardoor verbindingen kunnen loskomen.', '0.10'),
(45, 'Er zijn openingen in de omhulsels waarlangs gereedschap kan naar beneden vallen en kortsluiting kan veroorzaken.', '0.10'),
(46, 'Er zijn geen kortsluitbeveiligingen (automaat of smeltzekering).', '0.10'),
(47, 'Het onderbrekingsvermogen van de automaat is onvoldoende.', '0.10'),
(48, 'Het laagspanningsnet en de vreemde geleidende delen zijn onderling niet met elkaar verbonden.', '0.10'),
(49, 'Niet alle vreemde geleidende delen zijn equipotentiaal verbonden (metalen leidingen. water. gas. verwarming. metalen bordessen).', '0.10'),
(50, 'Zijn er energiebronnen waarvan de energie plots kan  vrijkomen (batterijen. condensatoren. lokale productie. inertie bewegende delen. restdruk in systemen).', '0.10'),
(51, 'De installatie is niet beveiligd tegen blikseminslag.', '0.10'),
(52, 'De ruimtetemperatuur kan zeer hoog oplopen.', '1.00'),
(53, 'Brandgevaar in lokaal.', '0.10'),
(54, 'Er kan explosiegevaar in lokaal ontstaan (vb: Solvent dampen. stofexplosie?).', '0.10'),
(55, 'Er is zichtbare stofophoping in de elektrische kast.', '0.10'),
(56, 'Er is opslag of verwerking van brandbare stoffen en/of vloeistoffen in directe nabijheid van de elektrische installatie.', '0.10'),
(57, 'Aandacht voor aansluitingen en verbindingen', '0.10'),
(58, 'Het elektrisch materieel is zodanig opgesteld dat warmteafgifte gehinderd wordt (onvoldoende natuurlijke verluchting of geen aangepaste koeling aanwezig).', '0.10'),
(59, 'Delen die heet kunnen worden. zijn in onvoldoende mate afgeschermd en gesignaleerd.', '0.10'),
(60, 'Er is geen beveiliging tegen kortsluiting.', '0.10'),
(61, 'Er zijn geen beveiligingscomponenten die overbrugd of geshunterd zijn.', '0.10'),
(62, 'Er is geen beveiliging tegen overbelasting van leidingen en verbruikers.', '0.10'),
(63, 'De smeltveiligheidshouders of houders van automatische schakelaars zijn niet voorzien van gepaste kalibreerelementen. (AREI Art. 5.3.5.5.)', '0.10'),
(64, 'De nominale stroom van de beveiligingscomponent is aangepast aan de sectie van de ge?nstalleerde geleiders. ', '0.10'),
(65, 'Er zijn kritische installaties waarbij het wegvallen van de spanning een noodsituatie zou kunnen teweegbrengen.', '1.00'),
(66, 'Bij het wegvallen van de spanning is er voldoende noodverlichting aanwezig om de veiligheid niet in gedrang te brengen.', '1.00'),
(67, 'De vitale stroombanen in de installatie zijn niet conform de richtlijnen in art 104 uitgevoerd.', '0.10'),
(68, 'Er is geen bliksemafleiding aanwezig', '0.10');

-- --------------------------------------------------------

--
-- Table structure for table `opdrachtgever`
--

CREATE TABLE `opdrachtgever` (
  `Id` int(11) NOT NULL,
  `naam` varchar(150) NOT NULL,
  `adres` varchar(255) NOT NULL,
  `postcode` int(4) NOT NULL,
  `gemeente` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `klantNaam` varchar(100) DEFAULT NULL,
  `projectId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `opdrachtgever`
--

INSERT INTO `opdrachtgever` (`Id`, `naam`, `adres`, `postcode`, `gemeente`, `email`, `klantNaam`, `projectId`) VALUES
(1, 'Abdel Deelam', 'Gentesesteenweg 45', 1000, 'Brussel', 'abdel.56@gmail.com', '', 1),
(2, 'Johan Demhuji', 'palokestraat 66', 1070, 'Anderlecht', 'johan@gmail.com', '', 2),
(5, 'Miguel', 'rozenlaan 45', 1000, 'BRUSSEL', 'MIGUEL@gmail.Com', '', 6);

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `id` int(11) NOT NULL,
  `naam` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`id`, `naam`) VALUES
(1, 'Amazing Pillow'),
(2, 'Police Anderlecht'),
(6, 'DEMOPROJECT'),
(7, 'heyhey'),
(8, 'Demo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `analist`
--
ALTER TABLE `analist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gebouw`
--
ALTER TABLE `gebouw`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gebouwRisico`
--
ALTER TABLE `gebouwRisico`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gebouwRisicoGebouw`
--
ALTER TABLE `gebouwRisicoGebouw`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kastRisico`
--
ALTER TABLE `kastRisico`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `opdrachtgever`
--
ALTER TABLE `opdrachtgever`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `analist`
--
ALTER TABLE `analist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gebouw`
--
ALTER TABLE `gebouw`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `gebouwRisico`
--
ALTER TABLE `gebouwRisico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `gebouwRisicoGebouw`
--
ALTER TABLE `gebouwRisicoGebouw`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `kastRisico`
--
ALTER TABLE `kastRisico`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `opdrachtgever`
--
ALTER TABLE `opdrachtgever`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
