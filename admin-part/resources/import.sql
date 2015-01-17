
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `new3`
--

-- --------------------------------------------------------

--
-- Структура таблицы `entityfields`
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
-- Дамп данных таблицы `entityfields`
--

INSERT INTO `entityfields` (`id`, `classname`, `colname`, `priority`, `propname`, `ismainpageprop`, `isdeleted`, `hidden`) VALUES
(43, 'GoodItem', 'ИД ', 1, 'id', b'1', b'0', NULL),
(44, 'GoodItem', 'Наименование ', 2, 'name', b'1', b'0', NULL),
(45, 'GoodItem', 'Текстовое описание ', 3, 'description', b'1', b'0', NULL),
(46, 'GoodItem', 'Дата создания ', 4, 'creation_date', NULL, b'0', NULL),
(47, 'GoodItem', 'Дата изменения ', 5, 'updating_date', b'1', b'0', NULL),
(48, 'GoodItem', 'Цена ', 6, 'price', b'1', b'0', NULL),
(49, 'GoodItem', 'Категория ', 7, 'category', NULL, b'0', NULL),
(50, 'GoodItem', 'Состояние ', 8, 'state', NULL, b'0', NULL),
(51, 'GoodItem', 'Топ товар ', 9, 'isTopGood', NULL, b'0', NULL),
(52, 'GoodItem', 'Товар сезона', 10, 'isSeasonGood', NULL, b'0', NULL),
(53, 'News', 'ИД ', 2, 'id', b'1', b'0', NULL),
(54, 'News', 'Заголовок ', 2, 'title', b'1', b'0', NULL),
(55, 'News', 'Путь к иконке', 3, 'thumb', NULL, b'0', NULL),
(56, 'News', 'Топ новость', 4, 'istop', b'1', b'0', NULL),
(57, 'News', 'Промотекст', 5, 'promotext', b'1', b'0', NULL),
(58, 'News', 'Дата создания', 6, 'creation', b'1', b'0', NULL),
(59, 'News', 'Категория ', 7, 'category', NULL, b'0', NULL),
(60, 'News', 'Дата изменения', 8, 'updating', NULL, b'0', NULL),
(61, 'Category', 'ИД', 1, 'id', b'1', b'0', NULL),
(62, 'Category', 'Название', 2, 'name', b'1', b'0', NULL),
(63, 'Category', 'Конечная категория', 3, 'isFinalCategory', b'1', b'0', NULL),
(64, 'Category', 'Алиас', 4, 'alias', b'0', b'1', NULL),
(65, 'Category', 'Путь к аватарке', 5, 'thumb', b'0', b'0', NULL),
(66, 'Category', 'Топ категория', 6, 'istop', b'0', b'0', NULL),
(67, 'Category', 'Родительская категория', 7, 'parent_category', b'0', b'0', NULL),
(68, 'Order', 'ИД', 1, 'id', b'1', b'0', NULL),
(69, 'Order', 'Пользователь', 2, 'usr', b'1', b'0', NULL),
(70, 'Order', 'Дата и время', 3, 'time', b'1', b'0', NULL),
(71, 'Order', 'Сумма', 4, 'amount', b'1', b'0', NULL),
(72, 'Order', 'Имя клиента', 5, 'clientname', b'0', b'0', NULL),
(73, 'Order', 'Е-мейл клиента', 6, 'clientemail', b'1', b'0', NULL),
(74, 'Order', 'Условия по кредиту', 7, 'cred_term', b'0', b'0', NULL),
(75, 'Order', 'Комментарий', 8, 'comment', b'0', b'0', NULL),
(76, 'Order', 'Доставить до', 9, 'delivtime', b'1', b'0', NULL),
(77, 'Order', 'Город', 10, 'clienttown', b'0', b'0', NULL),
(78, 'Order', 'Тип оплаты', 11, 'paytype', b'0', b'0', NULL),
(79, 'Order', 'Вид доставки', 12, 'deliverytype', b'0', b'0', NULL),
(80, 'Order', 'Адрес доставки', 13, 'adress', b'0', b'0', NULL),
(81, 'Order', 'Компания клиента', 14, 'client_company', b'0', b'0', NULL),
(82, 'Order', 'ОКПО клиента', 15, 'client_okpo', b'0', b'0', NULL),
(83, 'User', 'ИД', 1, 'id', b'1', b'0', NULL),
(84, 'User', 'Имя', 2, 'name', b'1', b'0', NULL),
(85, 'User', 'Имя-логин', 3, 'username', b'1', NULL, NULL),
(86, 'User', 'Пароль (хеш)', 4, 'password', b'1', b'0', NULL),
(87, 'User', 'IP-адресс', 6, 'ip', b'1', b'0', NULL),
(88, 'User', 'Пароль', 5, 'originpassword', b'1', b'0', NULL),
(89, 'User', 'Профиль активирован', 7, 'proved', b'1', b'0', NULL),
(90, 'User', 'Город', 8, 'town', b'1', b'0', NULL),
(91, 'User', 'Дата регистрации', 9, 'regdate', b'0', b'0', NULL),
(92, 'User', 'Дата рождения', 10, 'birthdate', b'0', b'0', NULL),
(93, 'User', 'Адрес', 11, 'adress', b'0', b'0', NULL),
(94, 'User', 'Ник', 12, 'nick', b'0', b'0', NULL),
(95, 'User', 'Бан', 13, 'isbanned', b'0', b'0', NULL),
(96, 'Role', 'ИД', 1, 'authority', b'1', b'0', NULL),
(97, 'Role', 'Описание', 2, 'description', b'1', b'0', NULL),
(98, 'PhoneNumber', 'ИД', 1, 'id', b'1', b'0', NULL),
(99, 'PhoneNumber', 'Номер', 2, 'numb', b'1', b'0', NULL),
(100, 'Action', 'ИД', 1, 'id', b'1', b'0', NULL),
(101, 'Action', 'Название', 2, 'name', b'1', b'0', NULL),
(102, 'Action', 'Активная', 3, 'isactive', b'1', b'0', NULL),
(103, 'Action', 'Действительна до', 4, 'endtime', b'1', b'0', NULL),
(104, 'Action', 'Путь к аватарке', 5, 'thumb', b'0', b'0', NULL),
(105, 'Action', 'Топ акция', 6, 'istop', b'1', b'0', NULL),
(106, 'DeliveryType', 'ИД', 1, 'id', b'1', b'0', NULL),
(107, 'DeliveryType', 'Название', 2, 'name', b'1', b'0', NULL),
(108, 'DeliveryType', 'Короткое название', 3, 'shortname', b'1', b'0', NULL),
(109, 'PayType', 'ИД', 1, 'id', b'1', b'0', NULL),
(110, 'PayType', 'Название', 2, 'name', b'1', b'0', NULL),
(111, 'PayType', 'Короткое название', 3, 'shortname', b'1', b'0', NULL),
(112, 'OrderRow', 'ИД', 1, 'id', b'1', b'0', NULL),
(113, 'OrderRow', 'Товар', 2, 'good', b'1', b'0', NULL),
(114, 'OrderRow', 'Количество', 3, 'goodcount', b'1', b'0', NULL),
(115, 'OrderRow', 'ИД заказа', 4, 'ord', b'1', b'0', NULL),
(116, 'Comment', 'ИД', 1, 'id', b'1', b'0', NULL),
(117, 'Comment', 'Комментарий к товару', 2, 'good', b'1', b'0', NULL),
(118, 'Comment', 'Дата и время', 3, 'datetime', b'1', b'0', NULL),
(119, 'Comment', 'Текст комментария', 4, 'text', b'1', b'0', NULL),
(120, 'Comment', 'Автор', 5, 'author', b'1', b'0', NULL),
(121, 'Comment', 'Имя при воде комментария', 6, 'authorname', b'0', b'0', NULL),
(122, 'Comment', 'Почта при вводе комментария', 7, 'authoremail', b'0', b'0', NULL),
(123, 'Comment', 'Плюсы товара', 8, 'plus', b'0', b'0', NULL),
(124, 'Comment', 'Минусы товара', 9, 'minus', b'0', b'0', NULL),
(125, 'Comment', 'Оценка товара', 10, 'note', b'0', b'0', NULL),
(126, 'Producer', 'ИД', 1, 'id', b'1', b'0', b'0'),
(127, 'Producer', 'Название', 2, 'name', b'1', b'0', b'0'),
(128, 'Producer', 'Путь к аватарке', 3, 'thumb', b'1', b'0', b'0'),
(129, 'PageGroup', '1', 1, 'id', b'1', b'0', b'0'),
(130, 'PageGroup', 'Название', 2, 'name', b'1', b'0', b'0'),
(131, 'PageGroup', 'Путь к аватарке', 3, 'thumb', b'1', b'0', b'0'),
(132, 'GoodItem', 'Производитель', 11, 'producer', b'1', b'0', b'0'),
(133, 'Guarantie', 'ИД', 1, 'id', b'1', b'0', b'0'),
(134, 'Guarantie', 'Тип временного периода', 2, 'periodtype', b'1', b'0', b'0'),
(135, 'Guarantie', 'Количество периодов', 3, 'count', b'1', b'0', b'0'),
(136, 'GoodStatus', 'ИД', 1, 'id', b'1', b'0', b'0'),
(137, 'GoodStatus', 'Текст статуса товара', 2, 'text', b'1', b'0', b'0'),
(138, 'GoodStatus', 'Путь к аватарке', 3, 'thumb', b'1', b'0', b'0'),
(139, 'Page', 'ИД', 1, 'id', b'1', b'0', b'0'),
(140, 'Page', 'Имя-анкор', 2, 'name', b'1', b'0', b'0'),
(141, 'Page', 'URL страницы', 3, 'url', b'1', b'0', b'0'),
(142, 'Page', 'Заголовок title', 4, 'title', b'1', b'0', b'0'),
(143, 'Page', 'Заголовок h1', 5, 'h1', b'1', b'0', b'0'),
(144, 'Page', 'Полный url', 6, 'fullurl', b'1', b'0', b'0'),
(145, 'Page', 'Информационная страница', 7, 'isinfopage', b'1', b'0', b'0'),
(146, 'Page', 'Показ. в футере', 8, 'isinfooter', b'1', b'0', b'0'),
(147, 'Page', 'Показ. в хедере', 9, 'isinheader', b'1', b'0', b'0'),
(148, NULL, 'Показ. в главном меню', 10, 'isinmainmenu', b'1', b'0', b'0'),
(149, 'Configuration', 'ИД', 1, 'id', b'1', b'0', b'0'),
(150, 'Configuration', 'Приоритет', 2, 'name', b'1', b'0', b'0'),
(151, 'Configuration', 'hasActionsOnMain', 3, 'actionscount', b'1', b'0', b'0'),
(152, 'Configuration', 'Показывать новости', 4, 'hasNewsOnMain', b'1', b'0', b'0'),
(153, 'Configuration', 'Количество новостей', 5, 'newscount', b'1', b'0', b'0'),
(154, 'Configuration', 'Секция топ-категорий', 6, 'hasTopGoodsListOnMain', b'1', b'0', b'0'),
(155, 'Configuration', 'Секция топ-товаров', 7, 'hasDayAndWeekTopOnMain', b'1', b'0', b'0'),
(156, 'Configuration', 'Слайдер', 8, 'hasSlider', b'1', b'0', b'0'),
(157, 'Configuration', 'Модерация комментариев', 9, 'commentsModeration', b'1', b'0', b'0'),
(158, 'Configuration', 'Шаблон активирован', 10, 'isactive', b'1', b'0', b'0'),
(159, 'NewsType', 'ИД', 1, 'id', b'1', b'0', b'0'),
(160, 'NewsType', 'Имя типа новостей', 2, 'name', b'1', b'0', b'0'),
(161, 'NewsType', 'Заголовок типа новостей', 3, 'title', b'1', b'0', b'0');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
