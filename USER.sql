/*
Navicat SQL Server Data Transfer

Source Server         : TYY
Source Server Version : 105000
Source Host           : TYY\SQLEXPRESS:1433
Source Database       : MATP
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 105000
File Encoding         : 65001

Date: 2017-01-13 23:18:42
*/


-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE [dbo].[USER]
GO
CREATE TABLE [dbo].[USER] (
[ID] varchar(20) NULL ,
[password] varchar(20) NULL ,
[authority] varchar(20) NULL ,
[time] datetime2(7) NULL 
)


GO

-- ----------------------------
-- Records of USER
-- ----------------------------
INSERT INTO [dbo].[USER] ([ID], [password], [authority], [time]) VALUES (N'admin', N'123456', N'admin', N'2017-01-10 22:43:32.0000000')
GO
GO
