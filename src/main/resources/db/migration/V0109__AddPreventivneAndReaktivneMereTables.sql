INSERT INTO `page` (`page_id`, `title`, `type`, `parent_id`, `order`) VALUES
('93', 'Третман ризика', 'TABLE', '1', '3'),
('94', 'Третман ризика', 'TABLE', '2', '3'),
('95', 'Третман ризика', 'TABLE', '3', '3'),
('96', 'Третман ризика', 'TABLE', '4', '3'),
('97', 'Третман ризика', 'TABLE', '5', '3'),
('98', 'Третман ризика', 'TABLE', '6', '3'),
('99', 'Третман ризика', 'TABLE', '7', '3'),
('100', 'Третман ризика', 'TABLE', '8', '3'),
('101', 'Третман ризика', 'TABLE', '9', '3'),
('102', 'Третман ризика', 'TABLE', '10', '3'),
('103', 'Третман ризика', 'TABLE', '11', '3'),
('104', 'Третман ризика', 'TABLE', '79', '3'),
('105', 'Третман ризика', 'TABLE', '12', '3'),
('106', 'Третман ризика', 'TABLE', '13', '3');

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(191, 93, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1428, 191, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1429, 191, 2, 'Мера', 'STRING', 1, NULL, 0),
(1430, 191, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1431, 191, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1432, 191, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1433, 191, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(192, 93, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1434, 192, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1435, 192, 2, 'Мера', 'STRING', 1, NULL, 0),
(1436, 192, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1437, 192, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1438, 192, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1439, 192, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(193, 94, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1440, 193, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1441, 193, 2, 'Мера', 'STRING', 1, NULL, 0),
(1442, 193, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1443, 193, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1444, 193, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1445, 193, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(194, 94, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1446, 194, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1447, 194, 2, 'Мера', 'STRING', 1, NULL, 0),
(1448, 194, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1449, 194, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1450, 194, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1451, 194, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(195, 95, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1452, 195, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1453, 195, 2, 'Мера', 'STRING', 1, NULL, 0),
(1454, 195, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1455, 195, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1456, 195, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1457, 195, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(196, 95, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1458, 196, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1459, 196, 2, 'Мера', 'STRING', 1, NULL, 0),
(1460, 196, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1461, 196, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1462, 196, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1463, 196, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(197, 96, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1464, 197, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1465, 197, 2, 'Мера', 'STRING', 1, NULL, 0),
(1466, 197, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1467, 197, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1468, 197, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1469, 197, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(198, 96, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1470, 198, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1471, 198, 2, 'Мера', 'STRING', 1, NULL, 0),
(1472, 198, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1473, 198, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1474, 198, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1475, 198, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(199, 97, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1476, 199, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1477, 199, 2, 'Мера', 'STRING', 1, NULL, 0),
(1478, 199, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1479, 199, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1480, 199, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1481, 199, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(200, 97, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1482, 200, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1483, 200, 2, 'Мера', 'STRING', 1, NULL, 0),
(1484, 200, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1485, 200, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1486, 200, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1487, 200, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(201, 98, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1488, 201, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1489, 201, 2, 'Мера', 'STRING', 1, NULL, 0),
(1490, 201, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1491, 201, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1492, 201, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1493, 201, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(202, 98, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1494, 202, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1495, 202, 2, 'Мера', 'STRING', 1, NULL, 0),
(1496, 202, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1497, 202, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1498, 202, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1499, 202, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(203, 99, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1500, 203, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1501, 203, 2, 'Мера', 'STRING', 1, NULL, 0),
(1502, 203, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1503, 203, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1504, 203, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1505, 203, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(204, 99, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1506, 204, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1507, 204, 2, 'Мера', 'STRING', 1, NULL, 0),
(1508, 204, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1509, 204, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1510, 204, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1511, 204, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(205, 100, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1512, 205, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1513, 205, 2, 'Мера', 'STRING', 1, NULL, 0),
(1514, 205, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1515, 205, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1516, 205, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1517, 205, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(206, 100, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1518, 206, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1519, 206, 2, 'Мера', 'STRING', 1, NULL, 0),
(1520, 206, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1521, 206, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1522, 206, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1523, 206, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(207, 101, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1524, 207, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1525, 207, 2, 'Мера', 'STRING', 1, NULL, 0),
(1526, 207, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1527, 207, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1528, 207, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1529, 207, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(208, 101, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1530, 208, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1531, 208, 2, 'Мера', 'STRING', 1, NULL, 0),
(1532, 208, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1533, 208, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1534, 208, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1535, 208, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(209, 102, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1536, 209, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1537, 209, 2, 'Мера', 'STRING', 1, NULL, 0),
(1538, 209, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1539, 209, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1540, 209, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1541, 209, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(210, 102, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1542, 210, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1543, 210, 2, 'Мера', 'STRING', 1, NULL, 0),
(1544, 210, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1545, 210, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1546, 210, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1547, 210, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(211, 103, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1548, 211, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1549, 211, 2, 'Мера', 'STRING', 1, NULL, 0),
(1550, 211, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1551, 211, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1552, 211, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1553, 211, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(212, 103, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1554, 212, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1555, 212, 2, 'Мера', 'STRING', 1, NULL, 0),
(1556, 212, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1557, 212, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1558, 212, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1559, 212, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(213, 104, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1560, 213, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1561, 213, 2, 'Мера', 'STRING', 1, NULL, 0),
(1562, 213, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1563, 213, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1564, 213, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1565, 213, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(214, 104, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1566, 214, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1567, 214, 2, 'Мера', 'STRING', 1, NULL, 0),
(1568, 214, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1569, 214, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1570, 214, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1571, 214, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(215, 105, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1572, 215, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1573, 215, 2, 'Мера', 'STRING', 1, NULL, 0),
(1574, 215, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1575, 215, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1576, 215, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1577, 215, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(216, 105, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1578, 216, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1579, 216, 2, 'Мера', 'STRING', 1, NULL, 0),
(1580, 216, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1581, 216, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1582, 216, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1583, 216, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(217, 106, 1, 0, 'Превентивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1584, 217, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1585, 217, 2, 'Мера', 'STRING', 1, NULL, 0),
(1586, 217, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1587, 217, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1588, 217, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1589, 217, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(218, 106, 2, 0, 'Реактивне мере');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1590, 218, 1, 'Област', 'AGGREGATE', 1, NULL, 0),
(1591, 218, 2, 'Мера', 'STRING', 1, NULL, 0),
(1592, 218, 3, 'Носилац активности', 'STRING', 1, NULL, 0),
(1593, 218, 4, 'Време реализације', 'STRING', 1, NULL, 0),
(1594, 218, 5, 'Сарадници у реализацији активности', 'STRING', 1, NULL, 0),
(1595, 218, 6, 'Време и начин извештавања', 'STRING', 1, NULL, 0);
