/*============================== INSERT DATABASE =======================================*/
/*======================================================================================*/
-- Add data Department
INSERT INTO Department	(DepartmentName, author_id, TotalMember	,	CreateDate, 	ModifiedDate)
VALUES                  (N'Marketing'	,	5		,   '1'		,	'2018-03-05',	'2020-03-05'),
						(N'Sale'		,	1		,   '1'		,	'2019-03-05',	'2020-03-07'),
						(N'Bảo vệ'		,	2		,   '1'		,	'2017-03-07',	'2020-03-08'),
						(N'Nhân sự'		,	3		,   '3'		,	'2021-03-08',	'2020-03-10'),
						(N'Kỹ thuật'	,	4		,   '4'		,	'2016-03-10',		NOW()	),
						(N'Tài chính'	,	6		,   '6'		,		NOW(),			NOW()	),
						(N'Phó giám đốc',	7		,   '7'		,		NOW(),		'2020-04-07'),
						(N'Giám đốc'	,	8		,   '8'		,	'2015-04-07',	'2020-04-07'),
						(N'Thư kí'		,	9		,   '9'		,	'2014-04-07',	'2020-04-09'),
						(N'Bán hàng'	,	5		,   '10'	,	'2017-04-09',	'2020-04-09');

-- Add data Account
INSERT INTO `Account`(Email								, Username			, FirstName,	LastName,		 	`password`,														`role`,					DepartmentID	,	CreateDate)
VALUES 				('haidang29productions@gmail.com'	, 'dangblack'		,'Dang'	,		'Nguyen Hai'	,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Manager'	,				'1'			,	'2018-03-05'),
					('account1@gmail.com'				, 'quanganh'		,'Anh'	,		'Tong Quang'	,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Manager'	,				'1'			,	'2019-03-05'),
                    ('account2@gmail.com'				, 'vanchien'		,'Chien',		'Nguyen Van'	,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Manager'	,				'1'			,	'2017-03-07'),
                    ('account3@gmail.com'				, 'cocoduongqua'	,'Do'	,		'Duong'			,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Manager'	,				'3'			,	'2021-03-08'),
                    ('account4@gmail.com'				, 'doccocaubai'		,'Thang',		'Nguyen Chien'  ,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Manager'	,				'4'			,	'2016-03-10'),
                    ('dapphatchetngay@gmail.com'		, 'khabanh'			,'Kha'	,		'Ngo Ba'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Manager'	,				'6'			,	NOW()),
                    ('songcodaoly@gmail.com'			, 'huanhoahong'		,'Huan'	,		'Bui Xuan'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Employee'	,				'7'			,	NOW()),
                    ('sontungmtp@gmail.com'				, 'tungnui'			,'Tung'	,		'Nguyen Thanh'	,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Employee'	,				'8'			,	'2015-04-07'),
                    ('duongghuu@gmail.com'				, 'duongghuu'		,'Huu'	,		'Duong Van'		,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Admin'		,				'9'			,	'2014-04-07'),
                    ('vtiaccademy@gmail.com'			, 'vtiaccademy'		,'Ai'	,		'Vi Ti'			,   '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Manager'	,				'10'		,	'2017-04-09');
                                                                                                              
ALTER TABLE Department                                                                                        
ADD FOREIGN KEY (author_id) REFERENCES `Account`(AccountID) ON DELETE CASCADE;                                