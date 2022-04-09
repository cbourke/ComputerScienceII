use yourlogin;

drop table if exists availability;
drop table if exists platform;
drop table if exists game;
drop table if exists publisher;

#--INDEX and KEY are the same in MySQL

create table publisher (
       publisherId int not null auto_increment primary key,
       name       varchar(255) not null
)Engine=InnoDB,collate=latin1_general_cs;

insert into publisher (publisherId, name) values (1, 'LucasArts');
insert into publisher (publisherId, name) values (2, 'Sony Computer Entertainment');
insert into publisher (publisherId, name) values (3, 'Square Enix');
insert into publisher (publisherId, name) values (4, 'Sega');
insert into publisher (publisherId, name) values (5, 'Nintendo');
insert into publisher (publisherId, name) values (6, 'Rockstar Games');
insert into publisher (publisherId, name) values (7, 'Blizzard Entertainment');
insert into publisher (publisherId, name) values (8, 'Capcom');
insert into publisher (publisherId, name) values (9, 'Atari');
insert into publisher (publisherId, name) values (10, 'Eidos');
insert into publisher (publisherId, name) values (11, 'Valve');
insert into publisher (publisherId, name) values (12, 'Namco');
insert into publisher (publisherId, name) values (13, 'Delphine Software');

SELECT * FROM publisher;

create table platform (
       platformId int not null auto_increment primary key,
       name varchar(255) not null
)Engine=InnoDB,collate=latin1_general_cs;

insert into platform (platformId, name) values (1, 'Xbox');
insert into platform (platformId, name) values (2, 'Xbox 360');
insert into platform (platformId, name) values (3, 'PlayStation');
insert into platform (platformId, name) values (4, 'PlayStation 2');
insert into platform (platformId, name) values (5, 'PlayStation 3');
insert into platform (platformId, name) values (6, 'NES');
insert into platform (platformId, name) values (7, 'PC');
insert into platform (platformId, name) values (8, 'Mac');
insert into platform (platformId, name) values (9, 'Game Boy');
insert into platform (platformId, name) values (10, 'Super NES');

SELECT * FROM platform;

create table game (
       gameId int not null auto_increment primary key,
       name      varchar(255) not null,
       publisherId int not null,
       foreign key(publisherId) references publisher(publisherId)
)Engine=InnoDB,collate=latin1_general_cs;

insert into game (gameId, name, publisherId) values (1, 'GTA IV', 6);
insert into game (gameId, name, publisherId) values (2, 'GTA 3', 6);
insert into game (gameId, name, publisherId) values (3, 'GTA 2', 6);
insert into game (gameId, name, publisherId) values (4, 'GTA', 6);
insert into game (gameId, name, publisherId) values (5, 'Legend of Zelda', 5);
insert into game (gameId, name, publisherId) values (6, 'Super Mario Brothers', 5);
insert into game (gameId, name, publisherId) values (7, 'Zelda II: The Adventure of Link', 5);
insert into game (gameId, name, publisherId) values (8, 'Mega Man 3', 8);
#--Publisher should be Capcom, intentionally made incorrect to correct later
insert into game (gameId, name, publisherId) values (9, 'Mega Man 4', 10);
insert into game (gameId, name, publisherId) values (10, 'Tetris', 5);
insert into game (gameId, name, publisherId) values (11, 'Super Mario Land', 5);
insert into game (gameId, name, publisherId) values (12, 'Tomb Raider', 10);
insert into game (gameId, name, publisherId) values (13, 'Portal', 11);
insert into game (gameId, name, publisherId) values (14, 'Portal 2', 11);
insert into game (gameId, name, publisherId) values (15, 'Katamari Damacy', 12);
insert into game (gameId, name, publisherId) values (16, 'Lego Star Wars', 1);
insert into game (gameId, name, publisherId) values (17, 'Tie Fighter', 1);
insert into game (gameId, name, publisherId) values (18, 'X-Wing vs Tie Fighter', 1);
insert into game (gameId, name, publisherId) values (19, 'Contra', 8);
insert into game (gameId, name, publisherId) values (20, 'Out of this World', 13);

create table availability (
       availabilityId int not null auto_increment primary key,
       gameId INT NOT NULL,
       platformId INT NOT NULL,
       publishYear INT,
       key(availabilityId,gameId,platformId),
       foreign key(gameId) references game(gameId),
       foreign key(platformId) references platform(platformId)
)Engine=InnoDB,collate=latin1_general_cs;

insert into availability (gameId, platformId, publishYear) values (1,2,2008);

#--GTA 4
insert into availability (gameId, platformId, publishYear) values (1, 7, 2008);

#--NES games
insert into availability (gameId, platformId, publishYear) values (5, 6, 1987);
insert into availability (gameId, platformId, publishYear) values (6, 6, 1985);
insert into availability (gameId, platformId, publishYear) values (7, 6, 1988);
insert into availability (gameId, platformId, publishYear) values (8, 6, 1990);

insert into availability (gameId, platformId, publishYear) values (10, 9, 1989);
insert into availability (gameId, platformId, publishYear) values (11, 9, 1989);

#--Lego Star Wars
insert into availability (gameId, platformId, publishYear) values (16, 1, 2005);
insert into availability (gameId, platformId, publishYear) values (16, 4, 2005);
insert into availability (gameId, platformId, publishYear) values (16, 7, 2005);
insert into availability (gameId, platformId, publishYear) values (16, 8, 2005);

#--Out of this world
insert into availability (gameId, platformId, publishYear) values (20, 7, 1992);
insert into availability (gameId, platformId, publishYear) values (20, 10, 1992);

#--Tomb Raider
insert into availability (gameId, platformId, publishYear) values (12, 3, 1996);
insert into availability (gameId, platformId, publishYear) values (12, 7, 1996);

#--Portal
insert into availability (gameId, platformId, publishYear) values (13, 5, 2007);
insert into availability (gameId, platformId, publishYear) values (13, 7, 2007);
insert into availability (gameId, platformId, publishYear) values (13, 2, 2007);

#--Portal 2
insert into availability (gameId, platformId, publishYear) values (14, 2, 2011);
insert into availability (gameId, platformId, publishYear) values (14, 5, 2011);
insert into availability (gameId, platformId, publishYear) values (14, 7, 2011);
insert into availability (gameId, platformId, publishYear) values (14, 8, 2011);

#--Katamari Damacy
insert into availability (gameId, platformId, publishYear) values (15, 4, 2004);

#--Lucas Arts
insert into availability (gameId, platformId, publishYear) values (17, 7, 1994);
insert into availability (gameId, platformId, publishYear) values (18, 7, 1997);
