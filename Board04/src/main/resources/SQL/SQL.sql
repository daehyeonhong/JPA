SELECT*FROM board WHERE content LIKE '%'||'ㅁ'||'%' ORDER BY seq DESC
SELECT*FROM board;
DESC board;
SELECT DISTINCT*FROM board WHERE (title LIKE '%'||'1'||'%' OR content LIKE '%'||'1'||'%')ORDER BY seq DESC
SELECT*FROM board WHERE (title LIKE '%'||'임시'||'%' OR content LIKE '%'||'임시'||'%')ORDER BY seq DESC

select*from board order by seq

SELECT*FROM (SELECT*FROM board ORDER BY seq DESC)GROUP BY seq

ALTER TABLE board ADD uploadFile VARCHAR2(100);