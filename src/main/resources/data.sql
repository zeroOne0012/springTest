insert into article (id, title, content) values (1, 'aaa', 'bbb');
insert into article (id, title, content) values (2, 'aaaa', 'bbbb');
insert into article (id, title, content) values (3, 'aaaaa', 'bbbbb');


-- article 더미 데이터
insert into article (id, title, content) values (4, '당신의 인생 영화는?', '댓글로 알려주세요');
insert into Article (id, title, content) values (5, '스프링', '연습');

-- comment 더미 데이터
---- 4번 게시글의 댓글들
insert into comment (id, article_id, nickname, body) values (1, 4, 'Kim', '겨울왕국');
insert into comment (id, article_id, nickname, body) values (2, 4, 'Lee', '라푼젤');
insert into comment (id, article_id, nickname, body) values (3, 4, 'Park', '라퓨타');

---- 5번 게시글의 댓글들
insert into comment (id, article_id, nickname, body) values (4, 5, 'Kim', '재밌다');
insert into comment (id, article_id, nickname, body) values (5, 5, 'Lee', '즐겁다');
insert into comment (id, article_id, nickname, body) values (6, 5, 'Park', '꺄르륵');
