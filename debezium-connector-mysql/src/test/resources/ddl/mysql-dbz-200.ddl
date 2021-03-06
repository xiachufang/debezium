CREATE TABLE `customfield` (
 `ENCODEDKEY` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, 
 `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL, 
 `CREATIONDATE` datetime DEFAULT NULL, 
 `LASTMODIFIEDDATE` datetime DEFAULT NULL,
 `DATATYPE` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL, 
 `ISDEFAULT` bit(1) DEFAULT NULL, 
 `ISREQUIRED` bit(1) DEFAULT NULL, 
 `NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL, 
 `VALUES` mediumblob, 
 `AMOUNTS` mediumblob, 
 `DESCRIPTION` varchar(256) DEFAULT NULL,
 `TYPE` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL, 
 `VALUELENGTH` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'SHORT', 
 `INDEXINLIST` int(11) DEFAULT '-1', 
 `CUSTOMFIELDSET_ENCODEDKEY_OID` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, 
 `STATE` varchar(256) NOT NULL DEFAULT 'NORMAL', 
 `VALIDATIONPATTERN` varchar(256) DEFAULT NULL, 
 `VIEWUSAGERIGHTSKEY` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL, 
 `EDITUSAGERIGHTSKEY` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL, 
 `BUILTINCUSTOMFIELDID` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL, 
 UNIQUE varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, 
 KEY `index1` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
