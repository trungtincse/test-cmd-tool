-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 04, 2020 at 05:55 PM
-- Server version: 10.0.38-MariaDB-0ubuntu0.16.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dev_command_server`
--

-- --------------------------------------------------------

--
-- Table structure for table `commands`
--

CREATE TABLE `commands` (
  `number` int(11) NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `subcmds` text COLLATE utf8_unicode_ci NOT NULL,
  `feature_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `commands`
--

INSERT INTO `commands` (`number`, `description`, `subcmds`, `feature_id`) VALUES
(1, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"longs8","value":[0,0,0,1]},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"string32","value":""}]}]', 9),
(2, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":null},{"description":"","type":"string16"}]}]', 9),
(3, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"string8s8","value":["c","b","c","aaaaa"]}]}]', 9),
(5, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"short","value":0},{"description":"","type":"long","value":0},{"description":"","type":"string8","value":""},{"description":"","type":"bytes8","value":[]}]}]', 9),
(11, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"longs32","value":[0,11,3]},{"description":"","type":"string32s32","value":["asdasd","asdasdasd"]}]}]', 9),
(111, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"tuple8|byte-byte","value":[[0,1],[0,1]]}]}]', 9),
(1010, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"shorts8","value":[0,1,2,3]},{"description":"","type":"tuple8|byte-byte-byte","value":[[0,1,5],[0,1,1]]}]}]', 9),
(1232, '', '[{"subcmd":1,"description":"","params":[{"description":"","type":"tuple8|byte-byte-byte","value":[[1,2,3],[1,2,4],[3,2,1]]}]}]', 9),
(1333, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"short","value":0},{"description":"","type":"long","value":0},{"description":"","type":"double","value":0}]},{"subcmd":1,"description":"","params":[{"description":"","type":"string8","value":""},{"description":"","type":"string16","value":""},{"description":"","type":"string32","value":""},{"description":"","type":"string8s8","value":["sfsdf","sdfsdfsdf","sfsdsdf"]}]},{"subcmd":2,"description":"","params":[{"description":"","type":"ints8","value":[1]},{"description":"","type":"longs8","value":[1]},{"description":"","type":"string32s16","value":["1"]},{"description":"","type":"tuple16|byte-string8-byte","value":[[1,"1"],[1,"4",5]]}]}]', 9),
(1490, 'Group Media Store: getCount', '[{"subcmd":0,"description":"Lấy tổng số media của tất cả các type","params":[{"description":"","type":"int","value":0}]}]', 6),
(1491, 'Group Media Store: getMediaByType', '[{"subcmd":0,"description":"Lấy list media của group theo type, support paging","params":[{"description":"Type:\\nPHOTO = 1\\nFILE = 2\\nDOODLE = 3\\nVOICE = 4\\nLINK = 5","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"Offset: vị trí bắt đầu tính từ 0","type":"int","value":0},{"description":"Size: số item muốn lấy","type":"int","value":0}]}]', 6),
(1492, 'Group Media Store - removeMediaById', '[{"subcmd":0,"description":"Xóa một media ra khỏi kho","params":[{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"long","value":0}]}]', 6),
(1493, 'Media Store 1-1 : getCount', '[{"subcmd":0,"description":"Lấy tổng số media của tất cả các type","params":[{"description":"","type":"int","value":0}]}]', 6),
(1494, 'Media Store 1-1: getMediaByType', '[{"subcmd":0,"description":"Lấy list media của group theo type, support paging","params":[{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0}]},{"subcmd":1,"description":" Lấy list media của group theo type, support paging","params":[{"description":"\\t\\nĐịnh nghĩa loại physical device đang dùng:\\n\\nAndroid: 1\\niOS: 2","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"Khi get list media, server kiểm tra userId trong packet header có là member của groupId hay không trước khi trả về.\\n","type":"int","value":0},{"description":"Định nghĩa loại media muốn fetch:\\n\\nMEDIA: 1 (photo & video)\\nFILE: 2\\nDOODLE = 3\\nVOICE = 4\\nLINK: 5","type":"byte","value":0},{"description":"Số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\n","type":"int","value":0},{"description":"Số lượng item cần load trong lần đó.\\n","type":"int","value":0},{"description":"Lấy các item có globalId nhỏ hơn lastId này.\\t","type":"long","value":0}]},{"subcmd":2,"description":"","params":[{"description":"Định nghĩa loại physical device đang dùng:\\n\\nAndroid: 1\\niOS: 2","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"Định nghĩa nơi gọi của CMD\\n\\n0: Từ Mediastore.\\n1: Từ Right Menu.","type":"byte","value":0},{"description":"Khi get list media, server kiểm tra userId trong packet header có là member của groupId hay không trước khi trả về.\\n","type":"int","value":0},{"description":"Định nghĩa loại media muốn fetch:\\n\\nMEDIA: 1 (photo & video)\\nFILE: 2\\nDOODLE = 3\\nVOICE = 4\\nLINK: 5","type":"byte","value":0},{"description":"Số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\n","type":"int","value":0},{"description":"Số lượng item cần load trong lần đó.\\n","type":"int","value":0}]}]', 6),
(1495, 'Media Store 1-1:  removeMediaById', '[{"subcmd":0,"description":"Xóa một media ra khỏi kho","params":[{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"long","value":0}]}]', 6),
(1497, 'Group Media Store - removeMediaByIds', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"longs16","value":[0]}]}]', 6),
(1498, 'API này chỉ trả về thông tin cơ bản để client dựng được layout\n\nData simple, mỗi batch có thể request 200 item\n\nParameter theo chuẩn API của group (Chuẩn thiết kế API)', '[{"subcmd":0,"description":"","params":[{"description":"Định nghĩa loại physical device đang dùng:\\n\\nAndroid: 1\\niOS: 2","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"Độ dài tính theo bytes của tracking data bên dưới\\n\\nMăc định không có sẽ truyền 0","type":"byte","value":0},{"description":"Ngôn ngữ của người dùng:\\n\\nTiếng Việt: 0\\nTiếng Anh: 1\\nTiếng Mynamar: 2\\nDo section title ngoài hiển thị ngày tháng dạng DD/MM/YYYY, còn hiển thị dưới dạng text vào các ngày lễ (ví dụ Quốc Khánh, Quốc tế phụ nữ, ...). Vì vậy cần truyền client language để server generate title phù hợp","type":"byte","value":0},{"description":"Định nghĩa nơi gọi của CMD\\n\\nXem spec: Source load data kho","type":"byte","value":0},{"description":"Khi get list media, server kiểm tra userId trong packet header có là member của groupId hay không trước khi trả về.","type":"int","value":0},{"description":"Định nghĩa loại media muốn fetch:\\n\\nMEDIA: 1 (photo & video)\\nFILE: 2\\nLINK: 5","type":"byte","value":0},{"description":"Số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.","type":"int","value":0},{"description":"Số lượng item cần load\\n","type":"byte","value":0},{"description":"Lấy các item có fileIds bé hơn lastFileId. Lần đầu tiên gọi set lastFileId = 0 (sub 0)\\n","type":"long","value":0}]},{"subcmd":1,"description":"Server trả về thêm 2 field \\"globalMsgId\\" & \\"clientMsgId\\".","params":[{"description":"Định nghĩa loại physical device đang dùng:\\n\\nAndroid: 1\\niOS: 2","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"Độ dài tính theo bytes của tracking data bên dưới\\n\\nMăc định không có sẽ truyền 0","type":"int","value":0},{"description":"Ngôn ngữ của người dùng:\\n\\nTiếng Việt: 0\\nTiếng Anh: 1\\nTiếng Mynamar: 2\\nDo section title ngoài hiển thị ngày tháng dạng DD/MM/YYYY, còn hiển thị dưới dạng text vào các ngày lễ (ví dụ Quốc Khánh, Quốc tế phụ nữ, ...). Vì vậy cần truyền client language để server generate title phù hợp","type":"byte","value":0},{"description":"Định nghĩa nơi gọi của CMD\\n\\nXem spec: Source load data kho","type":"byte","value":0},{"description":"Khi get list media, server kiểm tra userId trong packet header có là member của groupId hay không trước khi trả về.","type":"int","value":0},{"description":"Định nghĩa loại media muốn fetch:\\n\\nMEDIA: 1 (photo & video)\\nFILE: 2\\nLINK: 5","type":"byte","value":0},{"description":"Số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.","type":"int","value":0},{"description":"Số lượng item cần load\\n","type":"int","value":0},{"description":"Lấy các item có fileIds bé hơn lastFileId. Lần đầu tiên gọi set lastFileId = 0 (sub 0)","type":"long","value":0}]},{"subcmd":2,"description":"Support cờ loadType\\n","params":[{"description":"Định nghĩa loại physical device đang dùng:\\n\\nAndroid: 1\\niOS: 2","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"\\t\\nĐộ dài tính theo bytes của tracking data bên dưới\\n\\nMăc định không có sẽ truyền 0","type":"int","value":0},{"description":"Ngôn ngữ của người dùng:\\n\\nTiếng Việt: 0\\nTiếng Anh: 1\\nTiếng Mynamar: 2\\nDo section title ngoài hiển thị ngày tháng dạng DD/MM/YYYY, còn hiển thị dưới dạng text vào các ngày lễ (ví dụ Quốc Khánh, Quốc tế phụ nữ, ...). Vì vậy cần truyền client language để server generate title phù hợp","type":"byte","value":0},{"description":"Định nghĩa nơi gọi của CMD\\n\\nXem spec: Source load data kho","type":"byte","value":0},{"description":"Khi get list media, server kiểm tra userId trong packet header có là member của groupId hay không trước khi trả về.\\n","type":"int","value":0},{"description":"Định nghĩa loại media muốn fetch:\\n\\nMEDIA: 1 (photo & video)\\nFILE: 2\\nLINK: 5","type":"byte","value":0},{"description":"\\tSố thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.","type":"int","value":0},{"description":"Số lượng item cần load\\n","type":"int","value":0},{"description":"Lấy các item có fileIds bé hơn lastFileId. Lần đầu tiên gọi set lastFileId = 0 (sub 0)\\n","type":"long","value":0},{"description":"Cơ chế load :\\n\\n0 : backward loading trả về các item cũ hơn lastFileId\\n\\n1 : forward loading trả về các item mới hơn lastFileId","type":"byte","value":0}]}]', 6),
(1499, 'API này trả về thông tin đầy đủ của media item\nParameter theo chuẩn API của group (Chuẩn thiết kế API)', '[{"subcmd":0,"description":"","params":[{"description":"Định nghĩa loại physical device đang dùng:\\n\\nAndroid: 1\\niOS: 2","type":"byte","value":1},{"description":"","type":"int","value":2},{"description":"\\t\\nĐộ dài tính theo bytes của tracking data bên dưới\\n\\nMăc định không có sẽ truyền 0","type":"int","value":0},{"description":"Ngôn ngữ của người dùng:\\n\\nTiếng Việt: 0\\nTiếng Anh: 1\\nTiếng Mynamar: 2\\nDùng để localize text","type":"byte","value":0},{"description":"Định nghĩa nơi gọi của CMD\\n\\nXem spec: Source load data kho ","type":"byte","value":0},{"description":"Khi get list media, server kiểm tra userId trong packet header có là member của groupId hay không trước khi trả về.\\n","type":"int","value":50420512},{"description":"Số lượng fileId request\\n","type":"int","value":3},{"description":"undefined","type":"longs16","value":[4638437031,4638355551,4233724780]}]}]', 6),
(1730, 'API Get list album (1730 - Tab Album): get list album để hiển thị list ngang album hoặc list dọc album khi chọn xem tất cả album trong Tab Album (Kho Media). Mỗi album sẽ trả trước một vài Media Item(số lượng do server quyết định?) đầu tiên để làm thumb, khi mở album detail sẽ tiếp tục sử dụng những media item này ở đầu list và gọi load more album detail', '[{"subcmd":0,"description":"Hỗ trợ các album type: Album thường, Single Suggested Album v1","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.\\t","type":"long","value":0}]},{"subcmd":1,"description":"Hỗ trợ các album type: Single Suggested Album v2, Multi Suggested Album, Empty Suggested Album","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.\\t","type":"long","value":0}]},{"subcmd":2,"description":"Submit thêm param source","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.\\t","type":"long","value":0},{"description":"Tracking source (Album suggestion)","type":"short","value":0}]},{"subcmd":3,"description":"Hỗ trợ album type Memory Suggested Album và subType Album con Memory Album","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.\\t","type":"long","value":0}]}]', 6),
(1731, 'API Get album detail:get thông tin tổng quan của album và list Media Item nằm trong album, có hỗ trợ load more list Media Item', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"Id của album cần get\\t","type":"long","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.\\t","type":"long","value":0}]},{"subcmd":1,"description":" submit thêm param \\"source\\" để tracking source view detail (source dựa theo spec Album suggestion)","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"Id của album cần get\\t","type":"long","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.\\t","type":"long","value":0},{"description":"Tracking source\\t","type":"short","value":0}]},{"subcmd":2,"description":" thêm param \\"language\\", hỗ trợ để hiển thị Album Detail mở từ Memory Album và Single Memory Suggestion","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"Id của album cần get\\t","type":"long","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.\\t","type":"long","value":0},{"description":"","type":"byte","value":0}]},{"subcmd":3,"description":"Thêm param \\"albumType\\". Nguyên nhân thêm param: Sau khi thêm album loại collection, phía server tổ chức lưu trữ loại album này khác so với các loại còn lại, cần truyền lên album type để server phân biệt","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"Id của album cần get\\t","type":"long","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.\\t","type":"long","value":0},{"description":"Loại album. Định nghĩa tại KhoMediaV3 - Album","type":"int","value":0}]}]', 6),
(1732, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"+ 0: nếu user tạo album manual\\n\\n+ >0: nếu tạo từ suggest(lấy từ data của server)","type":"long","value":0},{"description":"album name\\t","type":"string32","value":""},{"description":"Mảng các media được chọn\\t","type":"longs32","value":[0]}]},{"subcmd":1,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"+ 0: nếu user tạo album manual\\n\\n+ >0: nếu tạo từ suggest(lấy từ data của server)","type":"long","value":0},{"description":"album name\\t","type":"string32","value":""},{"description":"Mảng các media được chọn\\t","type":"longs32","value":[0]},{"description":"\\t\\nTracking source\\n\\nList tracking sources tạo album","type":"short","value":0}]}]', 6),
(1733, 'xoá một album khỏi media store của 1 group, không xoá các media trong album', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"Id của album cần xóa\\t","type":"long","value":0}]}]', 6),
(1734, ' API Rename an album:  đổi tên của một album', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"long","value":0},{"description":"new album name\\t","type":"string32","value":""}]}]', 6),
(1735, 'API Add media item(s) to an album: thêm media item trong media store vào một album', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"long","value":0},{"description":"","type":"longs32","value":[0]}]},{"subcmd":1,"description":"thêm param \\"language\\"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"long","value":0},{"description":"Mảng các media được chọn\\t","type":"longs32","value":[0]},{"description":"","type":"byte","value":0}]}]', 6),
(1736, 'API Remove media item(s) from an album: xoá media item khỏi một album, không xoá media item đó khỏi media store', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"long","value":0},{"description":"Mảng các media được chọn\\t","type":"longs32","value":[0]}]},{"subcmd":1,"description":"thêm param \\"language\\"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"long","value":0},{"description":"Mảng các media được chọn\\t","type":"longs32","value":[0]},{"description":"","type":"byte","value":0}]}]', 6),
(1739, 'API Tạo album từ ảnh trong CSC (1739)\nMô tả: tương tự tạo album với ảnh trong kho media, nhưng định danh cho media là sourceUrl thay vì fileId', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"Album name","type":"string32","value":""},{"description":"Mảng sourceUrl lấy từ ảnh chat để server mapping qua media trong Media Store\\t","type":"string32s32","value":[""]},{"description":"\\t\\nTracking source\\n\\nList tracking sources tạo album ","type":"short","value":0}]}]', 6),
(1740, ' API Get list album (simple) :\n-Mục đích: hiện thị list album cho form Album Picker\n\n-Get list album tối giản, chỉ có album, không bao gồm suggest, header...\n\n-Album chỉ cần lấy header, không cần content, footer\n\n-Album bao gồm cover url size 240', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.\\t","type":"long","value":0}]}]', 6),
(1741, 'Mô tả: thêm một hay nhiều ảnh chat vào một hay nhiều album\n\n-Các exception case cần xử lý:\n\n+Tất cả các ảnh đều mapping fail\n\n+Tất cả album được chọn đều không tồn tại/đã bị xóa\n\n-Server cần trả về thêm status_code để xác định các exception case trên', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"List các albumId được chọn\\n\\n","type":"longs32","value":[0]},{"description":"List các sourceUrl từ ảnh chat được chọn\\t","type":"string32s32","value":[""]},{"description":"Tracking source\\t","type":"short","value":0}]}]', 6),
(1743, 'API get list album hiển thị ở phần album section tab Photo.\n\nChỗ này chỉ hiển thị một vài album preview, không cần load more, thuộc các loại sau:\n\n- Suggestion\n\n- Memory\n\n- Collection\n\n- Album thường\n\n \n\nRule pick như sau:\n\n- Lấy tất cả các suggestion\n\n- Các loại Memory, Collection, Album Manual được pick lần lượt theo thứ tự ưu tiên đến khi đủ số lượng N items yêu cầu\n\nHiện tại, nhờ server config trả về N = 6 items\n\n \n\nThứ tự hiển thị các loại album có thể thay đổi nên data cần có index để client có thể sắp xếp trên UI.', '[{"subcmd":0,"description":"","params":[{"description":"Định nghĩa loại physical device đang dùng:\\n\\nAndroid: 1\\niOS: 2","type":"byte","value":2},{"description":"","type":"int","value":309},{"description":"Độ dài tính theo bytes của tracking data bên dưới\\n\\nMăc định không có sẽ truyền 0","type":"int","value":0},{"description":"\\t\\nNgôn ngữ của người dùng:\\n\\nTiếng Việt: 0\\nTiếng Anh: 1\\nTiếng Mynamar: 2\\nDo section title ngoài hiển thị ngày tháng dạng DD/MM/YYYY, còn hiển thị dưới dạng text vào các ngày lễ (ví dụ Quốc Khánh, Quốc tế phụ nữ, ...). Vì vậy cần truyền client language để server generate title phù hợp","type":"byte","value":0},{"description":"Định nghĩa nơi gọi của CMD\\n\\n0: Từ Mediastore.","type":"byte","value":0},{"description":"Khi get list media, server kiểm tra userId trong packet header có là member của groupId hay không trước khi trả về.\\n","type":"int","value":50420512},{"description":"Định nghĩa loại media muốn fetch:\\n\\nMEDIA: 1 (photo & video)\\nFILE: 2\\nLINK: 5","type":"byte","value":0}]}]', 6),
(1744, 'API load list các smart collection album theo collection subtype.\n\nPhục vụ trường hợp cần load tất cả các album thuộc một collection nào đó (ví dụ, cần load tất cả album thuộc collection "Sender")\n\nVới các collection hiện tại là "Video", "Avatar", "Sender", chỉ có collection "Sender" cần dùng đến API này để load, do là tập hợp của nhiều album khác.\n\n2 collection "Video" và "Avatar" thực chất chỉ chứa 1 album nên không cần. Tuy nhiên, server cũng cần chuẩn bị cho trường hợp này.\n\nKhi client gọi API này để load các collection chỉ có 1 album (ví dụ "Video", "Avatar"), server chỉ trả về 1 album và không cho load more.\n\n \n\nLưu ý: Client request album details bằng command 1731 Album (Media store)#CMD_17315.2.APIGetalbumdetail(1731) nhớ dùng subcmd 3 để server phân biệt loại album. Nguyên nhân: album type collection được server tổ chức lưu trữ riêng với các loại khác, cần submit type album để server phân biệt và get details đúng.\n\n \n\nParameter theo chuẩn API của group (Chuẩn thiết kế API)', '[{"subcmd":0,"description":"","params":[{"description":"Định nghĩa loại physical device đang dùng:\\n\\nAndroid: 1\\niOS: 2","type":"byte","value":1},{"description":"","type":"int","value":2},{"description":"\\t\\nĐộ dài tính theo bytes của tracking data bên dưới\\n\\nMăc định không có sẽ truyền 0","type":"int","value":0},{"description":"\\t\\nNgôn ngữ của người dùng:\\n\\nTiếng Việt: 0\\nTiếng Anh: 1\\nTiếng Mynamar: 2\\nDùng để localize text","type":"byte","value":0},{"description":"Định nghĩa nơi gọi của CMD\\n\\nXem KhoMediaV3 - Tracking sources#Trackingsources-6.Sourcecollectionalbums","type":"byte","value":0},{"description":"\\tKhi get list media, server kiểm tra userId trong packet header có là member của groupId hay không trước khi trả về.","type":"int","value":50420512},{"description":"Định nghĩa loại media muốn fetch:\\n\\nMEDIA: 1 (photo & video)\\nFILE: 2\\nLINK: 5","type":"byte","value":1},{"description":"\\t\\nSubtype của collection muốn fetch.\\n\\nRange value định nghĩa tại: Smart collection subtype","type":"byte","value":2},{"description":"Số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.","type":"int","value":0},{"description":"Số lượng item cần load\\n","type":"int","value":40},{"description":"\\t\\nId của album cuối cùng trong lần load trước, phục vụ load more.\\n\\nLần đầu tiên gọi set lastId = 0\\n\\nLoad more: set lastId bằng lastId được trả về trong lần load trước","type":"long","value":0}]}]', 6),
(1745, '<p>API Load Album list V2 (CMD 1745) \nAPI load albums thay thế cho API 1730.\n</p><p>\nThay đổi so với API 1730:\n</p><p>\n- Dùng struct album mới, được định nghĩa tại KhoMediaV3</p><p> - Album (không cần thông tin timeline như bên 1730 nữa)</p><p>\n\n- Bổ sung type album collection\n\n\nParameter theo chuẩn API của group (Chuẩn thiết kế API)</p>', '[{"subcmd":0,"description":"","params":[{"description":"<p>Định nghĩa loại physical device đang dùng:<\\/p><ul><li>Android: 1<\\/li><li>iOS: 2<\\/li><\\/ul>","type":"byte","value":1},{"description":"","type":"int","value":2},{"description":"\\t\\nĐộ dài tính theo bytes của tracking data bên dưới\\n\\nMăc định không có sẽ truyền 0","type":"int","value":0},{"description":"\\t\\nNgôn ngữ của người dùng:\\n\\nTiếng Việt: 0\\nTiếng Anh: 1\\nTiếng Mynamar: 2\\nDùng để localize text","type":"byte","value":0},{"description":"Định nghĩa nơi gọi của CMD\\n\\nXem spec: Source load album list","type":"byte","value":0},{"description":"Khi get list album, server kiểm tra userId trong packet header có là member của groupId hay không trước khi trả về.","type":"int","value":50420512},{"description":"Định nghĩa loại media muốn fetch\\n\\nĐịnh nghĩa tại Kho Media\\n\\nMEDIA: 1 (photo & video)\\nFILE: 2\\nLINK: 5","type":"byte","value":0},{"description":"Số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.","type":"int","value":0},{"description":"Số lượng item cần load","type":"int","value":40},{"description":"Id của album cuối cùng trong lần load trước, phục vụ load more.\\n\\nLần đầu tiên gọi set lastId = 0\\n\\nLoad more: set lastId bằng lastId được trả về trong lần load trước","type":"long","value":0}]}]', 6),
(1770, 'LiveLocation command', '[{"subcmd":0,"description":"Receive user\'s current localtion","params":[{"description":"type: 0 = one persion | 1 = group","type":"byte","value":0},{"description":"groupId or userId","type":"int","value":0},{"description":"latitude","type":"double","value":0},{"description":"longitude","type":"double","value":0},{"description":"minutes to share","type":"short","value":0},{"description":"start_cli_live_id","type":"long","value":0},{"description":"","type":"bytes32","value":[1,2,3]}]}]', 1),
(1771, 'CMD Send Live Location(Socket-Http), send/receive multi (1771)\nClient send realtime không có offine thông tin live location qua CMD này (cho 1-1, nếu là group thì qua CMD 1774) sau khi start 1 MSG LIVE LOCATION. Server bắn thông tin liveVer trong msg tới người nhận.', '[{"subcmd":0,"description":"","params":[{"description":"","type":"tuple32|byte-int-long","value":[[1,2,3]]},{"description":"","type":"double","value":0},{"description":"","type":"double","value":0}]}]', 1),
(1772, 'Client get thông tin live location của 1 MSG thông qua CMD này. Khi View Detail 1 MSG LIVE LOCATION. Server sẽ trả data dạng list. Chứa tất cả các MSG đang live trong group hay 1-1 và của live_location_id input. Server trả về liveVer mỗi item. SubCmd = 1 sẽ trả về chỉ thông tin của live_location_id truyền lên.', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"long","value":0}]}]', 1),
(1773, 'CMD End Live Location (1773)\nKhi cần End Live Location sớm hơn thời gian expired. Client sẽ call CMD này lên server và truyền thêm các info của MSG LIVE LOCATION cần end. Server sẽ broadcast 1 MSG END LIVE LOCATION cho Group or Chat 1-1. Không broadcast MSG này cho user LIVE. Client truyền thêm lon, lat để update.', '[{"subcmd":0,"description":"","params":[{"description":"","type":"tuple32|byte-int-long","value":[[1,1,1]]},{"description":"","type":"double","value":0},{"description":"","type":"double","value":0}]}]', 1),
(1775, 'API cho phép mời người khác (chat 1-1) hoặc các thành viên trong nhóm (chat group) cùng chia sẻ vị trí.', '[{"subcmd":0,"description":"","params":[{"description":"Định nghĩa loại physical device đang dùng:\\n\\nAndroid: 1\\niOS: 2","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"Ngôn ngữ của người dùng:\\n\\nTiếng Việt: 0\\nTiếng Anh: 1\\nTiếng Mynamar: 2","type":"byte","value":0},{"description":"0: oneone\\n\\n1: group","type":"byte","value":0},{"description":"- userId với dest type là chat 1-1 - groupId với dest type là chat group\\n","type":"int","value":0}]}]', 1),
(1776, 'API này cho phép user đang live location có thể edit duration thay vì set cứng 60p như hiện tại.\n\nKhi chọn edit duration thì startTime sẽ được reset tại thời điểm edit.\n', '[{"subcmd":0,"description":"","params":[{"description":"Định nghĩa loại physical device đang dùng:\\n\\nAndroid: 1\\niOS: 2","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"Ngôn ngữ của người dùng:\\n\\nTiếng Việt: 0\\nTiếng Anh: 1\\nTiếng Mynamar: 2","type":"byte","value":0},{"description":"0: oneone\\n\\n1: group","type":"byte","value":0},{"description":"- userId với dest type là chat 1-1 - groupId với dest type là chat group\\n","type":"int","value":0},{"description":"id location muốn edit","type":"long","value":0}]}]', 1),
(1830, 'API Get list Memory Suggested Album (1830 - Tab Ảnh/Video)\nMô tả: Hiện list những albums trả từ server (bao gồm tất cả type: Single Memory Suggestion, album, suggestion... tùy theo define của SPEC Memory flow for group) để hiển thị list ngang suggestion trong Tab Ảnh/Video (Kho Media). Được clone từ CMD 1730 nên struct trả về của CMD này giống với struct của CMD 1730. Mỗi album sẽ trả trước một vài Media Item đầu tiên để làm thumb.', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.\\t","type":"long","value":0},{"description":"Tracking source (Album suggestion)\\t","type":"short","value":0}]}]', 6),
(1831, 'API Get list Memory Suggested Album (1831 - Tab Group)\nMô tả: Hiện list những albums trả từ server (bao gồm tất cả type: Single Memory Suggestion, album, suggestion... tùy theo define của SPEC Memory flow for group) để hiển thị list ngang suggestion trong Tab Group. Được clone từ CMD 1730 nên struct trả về của CMD này giống với struct của CMD 1730. Mỗi album sẽ trả trước một vài Media Item đầu tiên để làm thumb.', '[{"subcmd":0,"description":"Hỗ trợ album type Memory Suggested Album","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"byte","value":0},{"description":"số thứ tự của item cần load (tính từ 0). Lúc đầu set offset giá trị 0, nếu server trả về data.isLoadMore = true thì lần get sau set offset là data.count.\\t","type":"int","value":0},{"description":"số lượng item cần get\\t","type":"int","value":0},{"description":"lấy các item có albumId nhỏ hơn lastId này.","type":"long","value":0},{"description":"Tracking source (Album suggestion)\\t","type":"short","value":0}]}]', 6),
(1853, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"long","value":80},{"description":"","type":"byte","value":0}]}]', 8),
(1880, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0},{"description":"","type":"int","value":0},{"description":"","type":"string8","value":""},{"description":"","type":"short","value":0},{"description":"","type":"int","value":185230354}]}]', 9),
(1971, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"byte","value":0},{"description":"","type":"int","value":0}]}]', 9),
(1973, '', '[{"subcmd":0,"description":"","params":[{"description":"","type":"int","value":1},{"description":"","type":"ints32","value":[1]}]}]', 9);

-- --------------------------------------------------------

--
-- Table structure for table `features`
--

CREATE TABLE `features` (
  `feature_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `slug_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `features`
--

INSERT INTO `features` (`feature_id`, `name`, `slug_name`, `description`) VALUES
(1, 'Live Location', 'live-location', 'this is the description'),
(6, 'Media', 'media', ''),
(8, 'ACKCommand', 'ackcommand', ''),
(9, 'test', 'test', '');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `role` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `accessible_table` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `uid`, `role`, `accessible_table`) VALUES
(1, 185230354, 'ADMIN', ''),
(2, 237250298, 'GUEST', ''),
(3, 264328949, 'TESTER', 'live-location,ackcommand');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `uid` int(11) DEFAULT NULL,
  `name` text COLLATE utf8_unicode_ci NOT NULL,
  `avatar` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`uid`, `name`, `avatar`) VALUES
(185230354, 'Đặng Phú Trung Tín', 'https://s120.avatar.talk.zdn.vn/1/7/b/d/18/120/4e10cd80d961713372695b70a0c57efc.jpg'),
(237250298, 'Huỳnh Hà', 'https://s120.avatar.talk.zdn.vn/5/7/c/0/4/120/8592bebf39beff385a97d47eaf3fb752.jpg'),
(264328949, 'Văn Tiến Cường', 'https://s120.avatar.talk.zdn.vn/6/d/7/c/1/120/f8463f0fe0f1557af2f92c94ed622aa7.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `white_list`
--

CREATE TABLE `white_list` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `white_list`
--

INSERT INTO `white_list` (`id`, `uid`) VALUES
(4, 272773382),
(5, 70015339);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commands`
--
ALTER TABLE `commands`
  ADD PRIMARY KEY (`number`),
  ADD KEY `feature_id` (`feature_id`);

--
-- Indexes for table `features`
--
ALTER TABLE `features`
  ADD PRIMARY KEY (`feature_id`),
  ADD UNIQUE KEY `slug_name` (`slug_name`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `white_list`
--
ALTER TABLE `white_list`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `features`
--
ALTER TABLE `features`
  MODIFY `feature_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `white_list`
--
ALTER TABLE `white_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `commands`
--
ALTER TABLE `commands`
  ADD CONSTRAINT `commands_ibfk_1` FOREIGN KEY (`feature_id`) REFERENCES `features` (`feature_id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
