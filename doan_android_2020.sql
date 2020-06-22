-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 22, 2020 lúc 01:48 PM
-- Phiên bản máy phục vụ: 10.4.8-MariaDB
-- Phiên bản PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `doan_android_2020`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `categories_id` int(11) NOT NULL,
  `categories_name` varchar(100) NOT NULL,
  `categories_image` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`categories_id`, `categories_name`, `categories_image`) VALUES
(1, 'Danh mục 1', 'Danh mục 1'),
(2, 'Danh mục 2', 'Danh mục 2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `items`
--

CREATE TABLE `items` (
  `item_ids` int(11) NOT NULL,
  `item_name` varchar(50) NOT NULL,
  `item_image` varchar(100) NOT NULL,
  `item_image1` varchar(100) NOT NULL,
  `item_image2` varchar(100) NOT NULL,
  `item_image3` varchar(100) NOT NULL,
  `item_image4` varchar(100) NOT NULL,
  `item_price` int(12) NOT NULL,
  `item_bio` varchar(1024) NOT NULL,
  `item_review` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `items`
--

INSERT INTO `items` (`item_ids`, `item_name`, `item_image`, `item_image1`, `item_image2`, `item_image3`, `item_image4`, `item_price`, `item_bio`, `item_review`) VALUES
(0, 'Dại demo 1Dại demo 1Dại demo 1Dại demo 1Dại demo 1', 'https://i.imgur.com/7nKatVz.jpg', '', '', '', '', 6000, 'Demo 7 bio', 4.5),
(8, 'Đêmô 8', 'https://images-na.ssl-images-amazon.com/images/I/41hjK1ALseL._SY679_.jpg', '', '', '', '', 2000, 'Demo 8 bio', 5),
(9, 'Demo 9', 'https://i.imgur.com/z2KYzpq.jpg', '', '', '', '', 12000, 'Demo 9 bio', 2),
(10, 'Demo 10', 'https://i.imgur.com/rrhG1Jm.jpg', '', '', '', '', 33000, 'Demo 10 bio', 2),
(11, 'Demo 11', 'https://i.imgur.com/F3hmBmq.jpg', '', '', '', '', 2000, 'Demo 11 bio', 2),
(12, 'Demo 12', 'https://i.imgur.com/mSWuG2L.jpg', '', '', '', '', 74000, 'Demo 12 bio', 2),
(13, 'Demo 12', 'https://i.imgur.com/mSWuG2L.jpg', '', '', '', '', 74000, 'Demo 12 bio', 2),
(14, 'Demo 8', 'https://i.imgur.com/QeVp7Fc.jpg', '', '', '', '', 2000, 'Demo 8 bio', 5),
(15, 'Demo 9', 'https://i.imgur.com/z2KYzpq.jpg', '', '', '', '', 12000, 'Demo 9 bio', 2),
(16, 'Demo 11', 'https://i.imgur.com/F3hmBmq.jpg', '', '', '', '', 2000, 'Demo 11 bio', 2),
(17, 'Demo 12', 'https://i.imgur.com/mSWuG2L.jpg', '', '', '', '', 74000, 'Demo 12 bio', 2),
(18, 'Demo 7', 'https://i.imgur.com/7nKatVz.jpg', '', '', '', '', 6000, 'Demo 7 bio', 4.5),
(19, 'Demo 8', 'https://i.imgur.com/QeVp7Fc.jpg', '', '', '', '', 2000, 'Demo 8 bio', 5),
(20, 'Demo 9', 'https://i.imgur.com/z2KYzpq.jpg', '', '', '', '', 12000, 'Demo 9 bio', 2),
(21, 'Demo 10', 'https://i.imgur.com/rrhG1Jm.jpg', '', '', '', '', 33000, 'Demo 10 bio', 2),
(22, 'Demo 11', 'https://i.imgur.com/F3hmBmq.jpg', '', '', '', '', 2000, 'Demo 11 bio', 2),
(23, 'Demo 12', 'https://i.imgur.com/mSWuG2L.jpg', '', '', '', '', 74000, 'Demo 12 bio', 2),
(24, 'Demo 12', 'https://i.imgur.com/mSWuG2L.jpg', '', '', '', '', 74000, 'Demo 12 bio', 2),
(25, 'Demo 8', 'https://i.imgur.com/QeVp7Fc.jpg', '', '', '', '', 2000, 'Demo 8 bio', 5),
(26, 'Demo 9', 'https://i.imgur.com/z2KYzpq.jpg', '', '', '', '', 12000, 'Demo 9 bio', 2),
(27, 'Demo 11', 'https://i.imgur.com/F3hmBmq.jpg', '', '', '', '', 2000, 'Demo 11 bio', 2),
(28, 'Demo 12', 'https://i.imgur.com/mSWuG2L.jpg', '', '', '', '', 74000, 'Demo 12 bio', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `member`
--

CREATE TABLE `member` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_fullname` varchar(50) CHARACTER SET utf8 NOT NULL,
  `user_image` varchar(100) NOT NULL,
  `user_email` varchar(50) CHARACTER SET utf8 NOT NULL,
  `user_phone` varchar(50) CHARACTER SET utf8 NOT NULL,
  `user_address` varchar(512) CHARACTER SET utf8 NOT NULL,
  `user_bio` varchar(512) CHARACTER SET utf8 NOT NULL,
  `user_gender` varchar(20) CHARACTER SET utf8 NOT NULL,
  `password_hash` varchar(256) NOT NULL,
  `salt` varchar(256) NOT NULL,
  `user_createddate` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `member`
--

INSERT INTO `member` (`user_id`, `user_name`, `user_fullname`, `user_image`, `user_email`, `user_phone`, `user_address`, `user_bio`, `user_gender`, `password_hash`, `salt`, `user_createddate`) VALUES
(1, 'dai', 'Oke', 'http://192.168.1.7/DoAn_Android_2020/users/userImage/daiAvatar.jpg', 'đ', '22299292228282828', 'đ', 'đ', 'Female', '$2y$10$jJsIIvnnkAdEwrQf25iENuzl23KkFm.T6ijDYzCngFrKADYVHnRSq', '586337d71fec281a69a62e1909349f01cf1de360a25565e257116ac1c52409b4', '2020-02-28 23:20:18'),
(2, 'hoang', 'Dai', 'http://192.168.1.7/DoAn_Android_2020/users/userImage/hoangAvatar.jpg', 'hoangphidai123', '@gmail.com', '321', '123', 'Male', '$2y$10$e5fb18zH3tN.jwz7J.AexODgL0Hy9oIiHw3OmqeQRCJsJUa7lad5a', 'c65999bd3a096c81cc9b88a38b93cfba480a91c2cafa95716ed9722c3c93e42e', '2020-02-28 23:30:24'),
(3, 'oke', 'oke', 'http://192.168.1.7/DoAn_Android_2020/users/userImage/okeAvatar.jpg', '123', '123', '', '', 'Male', '$2y$10$O3VUBZMbDOMAf2oXEuv6WuXsJjCjAAQMdDa7yCufaB91l8d2nUjLO', 'df3808c2ec9a95f86ed5110b7c9c9f652cdb27416041150ac2ee259ce009e43c', '2020-02-28 23:33:27'),
(4, '123', '123', 'http://192.168.1.7/DoAn_Android_2020/users/userImage/123Avatar.jpg', '123', '321', '', '', 'Male', '$2y$10$m5Igk4yixOh4fJ7m.5jT8e6bfTLFRIqYPuTBApqujRD5rJFgB7h0m', '871968c231d2b462ef748023814ef42907ba9de23b0c304f329c7ba33fe9710c', '2020-02-29 20:54:43'),
(5, '11', '11', 'http://192.168.1.7/DoAn_Android_2020/users/userImage/11Avatar.jpg', 'Oke', '653', '', '', 'Male', '$2y$10$QO6Sj3O83JBnTWDdabDV4.odK1fzGGg7gSadarr2nONnxEQ3XkdYG', 'daca87ef50c3dfe5f27be35f273dabec7fec32fe811a4fec15fb898d4443a7b1', '2020-02-29 21:49:51'),
(6, '1233333', '1233333', '', '', '', '', '', '', '$2y$10$HN.ymVHdMRft4lhGU2Ndiu8roRb/hz2QXRz4lVxkvHf20YTQ9ASA.', '55518e0a5ad8f295133df955ab195ba92a1f568878d4396eca7e688df0908d7e', '2020-02-29 21:53:06'),
(7, '1', '1', 'http://192.168.1.7/DoAn_Android_2020/users/userImage/1Avatar.jpg', 'dai', '123', '', '', 'Female', '$2y$10$vyKHPqbElfJ55xT4coxyROoALWqajFe0ijkLA.btIibW8jQzUxhiW', 'c95ae3e355f81e6420064912da7808603e176f9e27fcd88fbc3e547ca753e45a', '2020-02-29 21:54:08'),
(8, '13333', '13333', '', '', '', '', '', '', '$2y$10$rV3futVYk8pWF6ZnFxZ9ruz0cUZ1E9LQHLD5.gXGv84depaZlcZiK', '56342f4381448232ea869655407c7725ae19c5f42db5dae27116d92efb2f9d93', '2020-02-29 21:55:23'),
(9, '1123123', '1123123', '', '', '', '', '', '', '$2y$10$q0W4cERJX9K97LDYmRhBZek9WlaLD2yHOX0NcllDyI2Jga3w3aPiy', 'b881b30f9c47cfd0c1a2f5f2fe3ad37cb306ff465599ee24e2d77fd427544b94', '2020-02-29 22:00:36'),
(10, 'sda', 'sda', '', '', '', '', '', '', '$2y$10$7hv4Wv/bqPqCIWoSjoI3Eu1yynKYUF4/h.yyFQQrnmFT63QfFLZga', '5ed9d746702b29e7de45d5348d6542d6319ae1ce30e03ee25dc2dd7656a40b3f', '2020-02-29 22:56:11'),
(11, 'hoang123', 'hoang123', '', '', '', '', '', '', '$2y$10$ba/ODgcC1y.jRSogWRp1SOVmXXpTFyej3GvmB5ruQfRoyfAn8.bMG', 'ffbe9c6e0bad3d0f22d22bb5cd5a99dd069d371a310c27dbfe482915325786d5', '2020-03-01 21:27:39'),
(12, 'hoangphid', 'dai', '', '1', '2', '3', '4', '5', '$2y$10$ziqFfVVLEtMUO1.26K49d.GpcW3opzXWDRBK5Grn2uxZsWPqQkrvS', '35fa222972ab556c623beefb238834ebf8acfd184cbd4fc06873413b30eee492', '2020-03-02 14:10:47'),
(13, '321', '321', '', '', '', '', '', '', '$2y$10$W9JdOlf8ymuSQG8JosMrHuAL.bCOFul2lc0heotUZVEmmeslt.bDK', 'bcf8deab734f0eb56f6137c773369db72e5303e9ca8ed87def7c1714e98a8dc3', '2020-03-02 14:24:44'),
(14, '12', 'dai', '', 'dai', '22', '65', 'b', 'b', '$2y$10$cYDT961ibLojxf2PXMBlue.emdAmrpuyJSStiD6BgA7/3sw5vniFu', 'da4b69f7f900d506c62c970a76514c0bad582007357dab9632cb00c28a3da2d0', '2020-03-02 14:31:13'),
(15, '1123', 'GUEST', '', '', '', '', '', '', '$2y$10$N1xQvf.Fm4DUQHlxk0zsP.tZo3oCg8VXhD3wVM0fUODYu3ismRBRm', 'e630d6d17e4f6c376aa88f3dc8ebd6490f58637bd4811be9f7928675ef242db7', '2020-03-02 14:48:47'),
(16, 'okee', 'GUEST', '', '', '', '', '', '', '$2y$10$7IwzTWFzsQGYy6mVUSenzehTF8a2jX9y2y4L616p4IQFUODe2pCkC', '8f48c7de3fb5f7bbc189bf247aff8bd801cb618f4ab9ed4cc7f9cf6d34eff709', '2020-03-03 16:15:10'),
(17, '123123123', 'GUEST', '', '', '', '', '', '', '$2y$10$ppfgaAH./0pDybv/goyCwOI9kfTMZWDM4nZhcC1KgK9w79IozSSyK', '5c5f342de357b4fb38dd3109c0bf61d561d9dc3388c2c7b8448eba76f9df74a0', '2020-03-03 16:17:18'),
(18, 'dd112', '', '', '', '', '', '', '', '$2y$10$YeYltSdu6Ec1x5ez2DNjmO.vHDKUlDnK2Xz5pJBWZYQ7VMcJDCkYG', '36f537a6493d5851564d6495598e1009921225aa075a23b42c290fe417f6247c', '2020-03-03 17:39:05'),
(19, '123d', '', '', '', '', '', '', '', '$2y$10$c8wHu8s9CqN93rRHLk1BVewb9wwXsw4v9lAT/.NXr3mQ7bndEbpKK', 'c8a5cd1f7a8e1f3d5e3268dd9d3f4a7f77b4287acc1772306fe1bd8e68539a13', '2020-03-03 18:37:23'),
(20, 'dai123', '123', '', '123', '', '', '', '', '$2y$10$/C8sVcwq8XgKe.7Zgzbh8./RRBJXC.FPV1u.RK/iqD8XjtWZwcAr.', '5b251b3d3467dd6c8a6e45fd5eeb599ef1b7ea14d8e79874998461b13bbeffba', '2020-03-03 18:39:34'),
(21, 'daiasd', '', '', '', '', '', '', '', '$2y$10$xfKRZLnfnwuaVTcIz7QnmeqduyIw20n2MLSoJ18fhsEfgerIwJamG', 'ea7c71de058c20131ed7661040a462121bf12f4a15ba2ce180fdd2462bebb747', '2020-03-03 18:40:46'),
(22, 'dai123321', '', '', '', '', '', '', '', '$2y$10$h5Ht9G.l5jDgaudU/5YWRuA4cHseGJQp8Fh1clsL/sOwiXimxfop.', 'e5a835c59bef70a4a32f0cbf16274dd5e95e77261fb1def7906e205b517ad7a1', '2020-03-05 23:31:24'),
(23, 'daiabc', '', '', '', '', '', '', '', '$2y$10$9iR9ZmNPH3Af5PcdWawYNOZbvY5/9GOD4y6RwSwW4gUO5f76Gi9fK', 'e3bc8c5e7bff49fc3367d134c40b7e3d6317498ea978d95d297cac8b4e55b58d', '2020-03-06 02:01:23'),
(24, 'dai1231', '', '', '', '', '', '', '', '$2y$10$UmhaKdVGynH3icnCC17mEuZjfFkQovxwNW39HVk.Jh4IiQaZp/C7a', 'bef5f1baec6eba8d7d7e7d8d332f7ef06b889542fc85614f042737384967cf2d', '2020-03-06 14:15:15'),
(25, 'test', '', '', '', '', '', '', '', '$2y$10$Hhi5GeI7V8ZRXB9QEmwj/u7lIXudHlsL2oSmC58URkseuVc.iwHky', '35bcab661db58927c30810ca401e5bc5b25bccb6479b47a8450b2c2fa54f4f11', '2020-03-06 15:01:26'),
(26, '123321', 'Dai', '', 'null', 'null', 'null', 'null', 'Female', '$2y$10$3L/nKsFHdZ6iimrRD6.yf.OoFglRgmV10.2IK/gcEcrEGCZrTyV4.', '097b4dc8d29cac3c6a6f10c705f29f9188ad842c4bbce37d47cfa3d4ef3e1e8d', '2020-03-07 12:25:37'),
(27, 'daq', 'Guest', '', '', '', '', '', '', '$2y$10$HSmZDSJ5scGUiQiFMp7/AObFIZt9At.i7Ea3Jv87RGUd4Nnzf.y3K', 'fe41c87fb88d3683d65efb9c8965af52d7a20422a4108ee4d0a9987297776d24', '2020-03-07 12:30:59'),
(28, 'daiii', 'Oke', '', 'hoangphidai123@gmail.com', '000777', '', '', 'Female', '$2y$10$IyX4J1Gy4niMpCQCnTJ4zO7LrJrMDAXTBefQDKNKlyyxyj/QKMWAK', '87e04c3606f3b81e083eb4e7e7b3be8377d4b2568a80ba65f05ee34b2bea235f', '2020-03-07 12:52:56'),
(29, 'daiiiiiii', 'Guest', '', '', '', '', '', '', '$2y$10$UVeIWZSw0VTidTnZwWpbJO48G/5XE52RdVBI3NE0NLYR5Mdd6..Dq', '36a2105b2ff781a73ecfeaa2900bec446e862f94417dbfd8d8359643b5d08844', '2020-03-07 12:58:05'),
(30, 'daiiii123', 'daine', '', 'da', '32', '', '', 'Female', '$2y$10$862dtlf/gyYGUM5sby75buasZHJB7c6F8rGOLu9LHPfDT23xNRnu2', 'f1882266464084dde0c7f0839da31fa10174da204dfb38706ee21319be4984fc', '2020-03-07 12:59:53'),
(31, 'teoem', 'Teoem', '', 'teo', '08823', '', '', 'Male', '$2y$10$uJUCOLPnqf3jyCHM5R2/kOYX4SMUwvA4izd4y6mzgTSq9DJQR6xtC', '3d14090b28dc6a61cc3e63c9a771a3f82603f9f5d312c8ae91efb7b530d782e7', '2020-03-16 18:21:36'),
(32, 'kuem', 'Teooo', 'http://192.168.1.7/DoAn_Android_2020/users/userImage/kuemAvatar.jpg', 'hoangphidai123@gmail.com', '0777230926', '', '', 'Male', '$2y$10$vUtlssZpQKNmN83yog8DBukswLnPZk4LFU2Zgi2VlNSdhIaSzGahW', '47e6415b25653059054bdb8778070c10141f8a8fa09228e4d165c21a42b1ea5f', '2020-03-17 19:23:59'),
(33, 'daiii123', 'dai', 'http://192.168.1.7/DoAn_Android_2020/users/userImage/daiii123Avatar.jpg', '1', '2', '', '', 'Male', '$2y$10$U4lZLTit3q2kYkb3BbUXfOIf4ho1QajuWvB66uABNQZzl9VGtvT2O', 'e31fc3e85cb4d5e483bbd9285fdbb9e46640b9924a45219ca4f1be603db3d641', '2020-03-17 19:58:44'),
(34, 'kem', 'Fida', 'http://192.168.1.7/DoAn_Android_2020/users/userImage/kemAvatar.jpg', 'qeqeqew', '37397373', 'asa', 'dggdep trai', 'Male', '$2y$10$R4WwsjGr3fxdU9WA7b7GxOcHvgxZTKPgtc/UPSqIGbjoHQRClc67S', 'e46a36a9682bb3fb66030323e07d4450fc0377a5f1cf34608054df65863b975f', '2020-05-28 18:58:22');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`categories_id`);

--
-- Chỉ mục cho bảng `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_ids`);

--
-- Chỉ mục cho bảng `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`user_name`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `categories_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `items`
--
ALTER TABLE `items`
  MODIFY `item_ids` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT cho bảng `member`
--
ALTER TABLE `member`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
