package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.springbook.biz.BoardVO;
import com.springbook.biz.common.JDBCUtil;

/*@Component("boardDAO")*/
/*@Repository("boardDAO")*/
public class BoardDAO {
	/* JDBC관련 변수 */
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/* SQL명령어 */
	private final String BOARD_INSERT = "INSERT INTO board(seq,title,writer,content,uploadFile)VALUES((SELECT NVL(MAX(SEQ),0)+1 FROM board),?,?,?,?)";
	private final String BOARD_UPDATE = "UPDATE board SET title=?,content=? WHERE seq=?";
	private final String BOARD_UPDATE_UPLOADFILE = "UPDATE board SET title=?,content=?,uploadFile=? WHERE seq=?";
	private final String BOARD_DELETE = "DELETE FROM board WHERE seq=?";
	private final String BOARD_GET = "SELECT*FROM board WHERE seq=?";
	/* private final String BOARD_LIST = "SELECT*FROM board ORDER BY seq DESC"; */
	private final String BOARD_LIST = "SELECT*FROM board WHERE(title LIKE '%'||?||'%' OR content LIKE '%'||?||'%')ORDER BY seq DESC";
	private final String BOARD_LIST_TITLE = "SELECT*FROM board WHERE title LIKE '%'||?||'%' ORDER BY seq DESC";
	private final String BOARD_LIST_CONTENT = "SELECT*FROM board WHERE content LIKE '%'||?||'%' ORDER BY seq DESC";
	private final String BOARD_UPDATE_COUNT = "UPDATE board SET cnt=NVL(cnt,0)+1 WHERE seq=?";

	/* 글 입력 */
	public void insertBoard(BoardVO vo) {
		System.out.println("==> JDBC로 insertBoard()기능 처리");
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(BOARD_INSERT);
			preparedStatement.setString(1, vo.getTitle());
			preparedStatement.setString(2, vo.getWriter());
			preparedStatement.setString(3, vo.getContent());
			preparedStatement.setString(4, vo.getUploadFile().getOriginalFilename());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
	}

	/* 글 목록 조회 */
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.printf("==> JDBC로 getBoardList()기능 처리\n");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			connection = JDBCUtil.getConnection();
			if (vo.getSearchCondition().equals("TITLE")) {
				preparedStatement = connection.prepareStatement(BOARD_LIST_TITLE);
			} else if (vo.getSearchCondition().equals("CONTENT")) {
				preparedStatement = connection.prepareStatement(BOARD_LIST_CONTENT);
			} else {
				preparedStatement = connection.prepareStatement(BOARD_LIST);
			}
			if (vo.getSearchCondition().equals("TITLE") || vo.getSearchCondition().equals("CONTENT")) {
				preparedStatement.setString(1, vo.getSearchKeyword());
			} else if (vo.getSearchCondition().equals("")) {
				preparedStatement.setString(1, vo.getSearchKeyword());
				preparedStatement.setString(2, vo.getSearchKeyword());
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BoardVO board = new BoardVO();
				int seq = resultSet.getInt("seq"), cnt = resultSet.getInt("cnt");
				String title = resultSet.getString("title"), writer = resultSet.getString("writer"),
						content = resultSet.getString("content");
				Date regDate = resultSet.getDate("regDate");
				board.setSeq(seq);
				board.setTitle(title);
				board.setWriter(writer);
				board.setContent(content);
				board.setRegDate(regDate);
				board.setCnt(cnt);

				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return boardList;
	}

	public BoardVO getBoard(BoardVO vo) {
		System.out.println("==> JDBC로 getBoard()기능 처리");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BoardVO board = new BoardVO();
		/* 조회수 증가 Method 호출 */
		updateBoardCount(vo);
		try {
			int seq = vo.getSeq();
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(BOARD_GET);
			preparedStatement.setInt(1, seq);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				board.setSeq(seq);
				board.setTitle(resultSet.getString("title"));
				board.setWriter(resultSet.getString("writer"));
				board.setContent(resultSet.getString("content"));
				board.setRegDate(resultSet.getDate("regDate"));
				board.setCnt(resultSet.getInt("cnt"));
				System.out.println("c:/upload/" + resultSet.getString("uploadFile"));
				board.setImages("c:/upload/" + resultSet.getString("uploadFile"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return board;
	}

	public void updateBoard(BoardVO vo) {
		System.out.println("==> JDBC로 updateBoard()기능 처리");
		try {
			/* 수정시 getBoard.jsp에서 uploadFile이 넘어오면 file경로 수정 */
			/* 넘어오지 않으면 그대로 적용 *//* File_Upload */
			MultipartFile uploadFile = vo.getUploadFile();
			if (!uploadFile.isEmpty()) {
				preparedStatement = connection.prepareStatement(BOARD_UPDATE_UPLOADFILE);
				preparedStatement.setString(1, vo.getTitle());
				preparedStatement.setString(2, vo.getContent());
				preparedStatement.setString(3, uploadFile.getOriginalFilename());
				preparedStatement.setInt(4, vo.getSeq());
			} else {
				connection = JDBCUtil.getConnection();
				preparedStatement = connection.prepareStatement(BOARD_UPDATE);
				preparedStatement.setString(1, vo.getTitle());
				preparedStatement.setString(2, vo.getContent());
				preparedStatement.setInt(3, vo.getSeq());
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("==> JDBC로 deleteBoard()기능 처리");
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(BOARD_DELETE);
			preparedStatement.setInt(1, vo.getSeq());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
	}

	/* 조회수 증가 Method */
	public void updateBoardCount(BoardVO vo) {
		System.out.println("==> JDBC로 updateCount()기능 처리");
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(BOARD_UPDATE_COUNT);
			preparedStatement.setInt(1, vo.getSeq());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
	}

}