
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- ���� ������: `new3`
--

-- --------------------------------------------------------

--
-- ��������� ������� `entityfields`
--

CREATE TABLE IF NOT EXISTS `entityfields` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classname` varchar(255) DEFAULT NULL,
  `colname` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `propname` varchar(255) DEFAULT NULL,
  `ismainpageprop` bit(1) DEFAULT NULL,
  `isdeleted` bit(1) DEFAULT NULL,
  `hidden` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=162 ;

--
-- ���� ������ ������� `entityfields`
--

INSERT INTO `entityfields` (`id`, `classname`, `colname`, `priority`, `propname`, `ismainpageprop`, `isdeleted`, `hidden`) VALUES
(43, 'GoodItem', '�� ', 1, 'id', b'1', b'0', NULL),
(44, 'GoodItem', '������������ ', 2, 'name', b'1', b'0', NULL),
(45, 'GoodItem', '��������� �������� ', 3, 'description', b'1', b'0', NULL),
(46, 'GoodItem', '���� �������� ', 4, 'creation_date', NULL, b'0', NULL),
(47, 'GoodItem', '���� ��������� ', 5, 'updating_date', b'1', b'0', NULL),
(48, 'GoodItem', '���� ', 6, 'price', b'1', b'0', NULL),
(49, 'GoodItem', '��������� ', 7, 'category', NULL, b'0', NULL),
(50, 'GoodItem', '��������� ', 8, 'state', NULL, b'0', NULL),
(51, 'GoodItem', '��� ����� ', 9, 'isTopGood', NULL, b'0', NULL),
(52, 'GoodItem', '����� ������', 10, 'isSeasonGood', NULL, b'0', NULL),
(53, 'News', '�� ', 2, 'id', b'1', b'0', NULL),
(54, 'News', '��������� ', 2, 'title', b'1', b'0', NULL),
(55, 'News', '���� � ������', 3, 'thumb', NULL, b'0', NULL),
(56, 'News', '��� �������', 4, 'istop', b'1', b'0', NULL),
(57, 'News', '����������', 5, 'promotext', b'1', b'0', NULL),
(58, 'News', '���� ��������', 6, 'creation', b'1', b'0', NULL),
(59, 'News', '��������� ', 7, 'category', NULL, b'0', NULL),
(60, 'News', '���� ���������', 8, 'updating', NULL, b'0', NULL),
(61, 'Category', '��', 1, 'id', b'1', b'0', NULL),
(62, 'Category', '��������', 2, 'name', b'1', b'0', NULL),
(63, 'Category', '�������� ���������', 3, 'isFinalCategory', b'1', b'0', NULL),
(64, 'Category', '�����', 4, 'alias', b'0', b'1', NULL),
(65, 'Category', '���� � ��������', 5, 'thumb', b'0', b'0', NULL),
(66, 'Category', '��� ���������', 6, 'istop', b'0', b'0', NULL),
(67, 'Category', '������������ ���������', 7, 'parent_category', b'0', b'0', NULL),
(68, 'Order', '��', 1, 'id', b'1', b'0', NULL),
(69, 'Order', '������������', 2, 'usr', b'1', b'0', NULL),
(70, 'Order', '���� � �����', 3, 'time', b'1', b'0', NULL),
(71, 'Order', '�����', 4, 'amount', b'1', b'0', NULL),
(72, 'Order', '��� �������', 5, 'clientname', b'0', b'0', NULL),
(73, 'Order', '�-���� �������', 6, 'clientemail', b'1', b'0', NULL),
(74, 'Order', '������� �� �������', 7, 'cred_term', b'0', b'0', NULL),
(75, 'Order', '�����������', 8, 'comment', b'0', b'0', NULL),
(76, 'Order', '��������� ��', 9, 'delivtime', b'1', b'0', NULL),
(77, 'Order', '�����', 10, 'clienttown', b'0', b'0', NULL),
(78, 'Order', '��� ������', 11, 'paytype', b'0', b'0', NULL),
(79, 'Order', '��� ��������', 12, 'deliverytype', b'0', b'0', NULL),
(80, 'Order', '����� ��������', 13, 'adress', b'0', b'0', NULL),
(81, 'Order', '�������� �������', 14, 'client_company', b'0', b'0', NULL),
(82, 'Order', '���� �������', 15, 'client_okpo', b'0', b'0', NULL),
(83, 'User', '��', 1, 'id', b'1', b'0', NULL),
(84, 'User', '���', 2, 'name', b'1', b'0', NULL),
(85, 'User', '���-�����', 3, 'username', b'1', NULL, NULL),
(86, 'User', '������ (���)', 4, 'password', b'1', b'0', NULL),
(87, 'User', 'IP-������', 6, 'ip', b'1', b'0', NULL),
(88, 'User', '������', 5, 'originpassword', b'1', b'0', NULL),
(89, 'User', '������� �����������', 7, 'proved', b'1', b'0', NULL),
(90, 'User', '�����', 8, 'town', b'1', b'0', NULL),
(91, 'User', '���� �����������', 9, 'regdate', b'0', b'0', NULL),
(92, 'User', '���� ��������', 10, 'birthdate', b'0', b'0', NULL),
(93, 'User', '�����', 11, 'adress', b'0', b'0', NULL),
(94, 'User', '���', 12, 'nick', b'0', b'0', NULL),
(95, 'User', '���', 13, 'isbanned', b'0', b'0', NULL),
(96, 'Role', '��', 1, 'authority', b'1', b'0', NULL),
(97, 'Role', '��������', 2, 'description', b'1', b'0', NULL),
(98, 'PhoneNumber', '��', 1, 'id', b'1', b'0', NULL),
(99, 'PhoneNumber', '�����', 2, 'numb', b'1', b'0', NULL),
(100, 'Action', '��', 1, 'id', b'1', b'0', NULL),
(101, 'Action', '��������', 2, 'name', b'1', b'0', NULL),
(102, 'Action', '��������', 3, 'isactive', b'1', b'0', NULL),
(103, 'Action', '������������� ��', 4, 'endtime', b'1', b'0', NULL),
(104, 'Action', '���� � ��������', 5, 'thumb', b'0', b'0', NULL),
(105, 'Action', '��� �����', 6, 'istop', b'1', b'0', NULL),
(106, 'DeliveryType', '��', 1, 'id', b'1', b'0', NULL),
(107, 'DeliveryType', '��������', 2, 'name', b'1', b'0', NULL),
(108, 'DeliveryType', '�������� ��������', 3, 'shortname', b'1', b'0', NULL),
(109, 'PayType', '��', 1, 'id', b'1', b'0', NULL),
(110, 'PayType', '��������', 2, 'name', b'1', b'0', NULL),
(111, 'PayType', '�������� ��������', 3, 'shortname', b'1', b'0', NULL),
(112, 'OrderRow', '��', 1, 'id', b'1', b'0', NULL),
(113, 'OrderRow', '�����', 2, 'good', b'1', b'0', NULL),
(114, 'OrderRow', '����������', 3, 'goodcount', b'1', b'0', NULL),
(115, 'OrderRow', '�� ������', 4, 'ord', b'1', b'0', NULL),
(116, 'Comment', '��', 1, 'id', b'1', b'0', NULL),
(117, 'Comment', '����������� � ������', 2, 'good', b'1', b'0', NULL),
(118, 'Comment', '���� � �����', 3, 'datetime', b'1', b'0', NULL),
(119, 'Comment', '����� �����������', 4, 'text', b'1', b'0', NULL),
(120, 'Comment', '�����', 5, 'author', b'1', b'0', NULL),
(121, 'Comment', '��� ��� ���� �����������', 6, 'authorname', b'0', b'0', NULL),
(122, 'Comment', '����� ��� ����� �����������', 7, 'authoremail', b'0', b'0', NULL),
(123, 'Comment', '����� ������', 8, 'plus', b'0', b'0', NULL),
(124, 'Comment', '������ ������', 9, 'minus', b'0', b'0', NULL),
(125, 'Comment', '������ ������', 10, 'note', b'0', b'0', NULL),
(126, 'Producer', '��', 1, 'id', b'1', b'0', b'0'),
(127, 'Producer', '��������', 2, 'name', b'1', b'0', b'0'),
(128, 'Producer', '���� � ��������', 3, 'thumb', b'1', b'0', b'0'),
(129, 'PageGroup', '1', 1, 'id', b'1', b'0', b'0'),
(130, 'PageGroup', '��������', 2, 'name', b'1', b'0', b'0'),
(131, 'PageGroup', '���� � ��������', 3, 'thumb', b'1', b'0', b'0'),
(132, 'GoodItem', '�������������', 11, 'producer', b'1', b'0', b'0'),
(133, 'Guarantie', '��', 1, 'id', b'1', b'0', b'0'),
(134, 'Guarantie', '��� ���������� �������', 2, 'periodtype', b'1', b'0', b'0'),
(135, 'Guarantie', '���������� ��������', 3, 'count', b'1', b'0', b'0'),
(136, 'GoodStatus', '��', 1, 'id', b'1', b'0', b'0'),
(137, 'GoodStatus', '����� ������� ������', 2, 'text', b'1', b'0', b'0'),
(138, 'GoodStatus', '���� � ��������', 3, 'thumb', b'1', b'0', b'0'),
(139, 'Page', '��', 1, 'id', b'1', b'0', b'0'),
(140, 'Page', '���-�����', 2, 'name', b'1', b'0', b'0'),
(141, 'Page', 'URL ��������', 3, 'url', b'1', b'0', b'0'),
(142, 'Page', '��������� title', 4, 'title', b'1', b'0', b'0'),
(143, 'Page', '��������� h1', 5, 'h1', b'1', b'0', b'0'),
(144, 'Page', '������ url', 6, 'fullurl', b'1', b'0', b'0'),
(145, 'Page', '�������������� ��������', 7, 'isinfopage', b'1', b'0', b'0'),
(146, 'Page', '�����. � ������', 8, 'isinfooter', b'1', b'0', b'0'),
(147, 'Page', '�����. � ������', 9, 'isinheader', b'1', b'0', b'0'),
(148, NULL, '�����. � ������� ����', 10, 'isinmainmenu', b'1', b'0', b'0'),
(149, 'Configuration', '��', 1, 'id', b'1', b'0', b'0'),
(150, 'Configuration', '���������', 2, 'name', b'1', b'0', b'0'),
(151, 'Configuration', 'hasActionsOnMain', 3, 'actionscount', b'1', b'0', b'0'),
(152, 'Configuration', '���������� �������', 4, 'hasNewsOnMain', b'1', b'0', b'0'),
(153, 'Configuration', '���������� ��������', 5, 'newscount', b'1', b'0', b'0'),
(154, 'Configuration', '������ ���-���������', 6, 'hasTopGoodsListOnMain', b'1', b'0', b'0'),
(155, 'Configuration', '������ ���-�������', 7, 'hasDayAndWeekTopOnMain', b'1', b'0', b'0'),
(156, 'Configuration', '�������', 8, 'hasSlider', b'1', b'0', b'0'),
(157, 'Configuration', '��������� ������������', 9, 'commentsModeration', b'1', b'0', b'0'),
(158, 'Configuration', '������ �����������', 10, 'isactive', b'1', b'0', b'0'),
(159, 'NewsType', '��', 1, 'id', b'1', b'0', b'0'),
(160, 'NewsType', '��� ���� ��������', 2, 'name', b'1', b'0', b'0'),
(161, 'NewsType', '��������� ���� ��������', 3, 'title', b'1', b'0', b'0');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
