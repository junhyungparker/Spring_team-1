package com.mycom.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mycom.myapp.bean.BoardVO;
import com.mycom.myapp.dao.BoardDAO;

public class BoardDAO {
	
//	Connection conn = null;
//	PreparedStatement stmt = null;
//	ResultSet rs = null;

	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	private final String BOARD_INSERT = "insert into BOARD2 (category, writer, gender, birthDate, content, phone) values (?,?,?,?,?,?)";
	private final String BOARD_UPDATE = "update BOARD2 set category=?, writer=?, gender=?, birthDate=?, content=?, phone=? where id=?";
	private final String BOARD_DELETE = "delete from BOARD2  where id=?";
	private final String BOARD_GET = "select * from BOARD2  where id=?";
	private final String BOARD_LIST = "select * from BOARD2 order by id desc";
	
	
		public int insertBoard(BoardVO vo) {
			return template.update(BOARD_INSERT, new Object[] {vo.getCategory(),vo.getWriter(), vo.getGender(), vo.getBirthDate(), vo.getContent(), vo.getPhone()});
		}
		
		public int deleteBoard(int id) {
			return template.update(BOARD_DELETE,new Object[]{id});
		}
		
		public int updateBoard(BoardVO vo) {
			return template.update(BOARD_UPDATE,new Object[] {vo.getCategory(),vo.getWriter(), vo.getGender(), vo.getBirthDate(), vo.getContent(), vo.getPhone()});
		}
		
		public BoardVO getBoard(int id) {
			return template.queryForObject(BOARD_GET,new Object[] {id},new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
		}

		public List<BoardVO> getBoardList(){
			return template.query(BOARD_LIST,new RowMapper<BoardVO>(){
				
				@Override
				public BoardVO mapRow(ResultSet rs,int rowNum) throws SQLException{
					BoardVO data = new BoardVO();
					data.setId(rs.getInt("id"));
					data.setCategory(rs.getString("category"));
					data.setWriter(rs.getString("writer"));
					data.setGender(rs.getString("gender"));
					data.setBirthDate(rs.getString("birthDate"));
					data.setContent(rs.getString("content"));
					data.setPhone(rs.getString("phone"));
					data.setRegdate(rs.getDate("regdate"));
					return data;
				}
			});
		}
//	public int insertBoard(BoardVO vo) {
//		System.out.println("===> JDBC로 insertBoard() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_INSERT);
//			stmt.setString(1, vo.getTitle());
//			stmt.setString(2, vo.getWriter());
//			stmt.setString(3, vo.getContent());
//			stmt.executeUpdate();
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//
//	// 글 삭제
//	public void deleteBoard(BoardVO vo) {
//		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_DELETE);
//			stmt.setInt(1, vo.getSeq());
//			stmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public int updateBoard(BoardVO vo) {
//		System.out.println("===> JDBC로 updateBoard() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_UPDATE);
//			stmt.setString(1, vo.getTitle());
//			stmt.setString(2, vo.getWriter());
//			stmt.setString(3, vo.getContent());
//			stmt.setInt(4, vo.getSeq());
//			
//			
//			System.out.println(vo.getTitle() + "-" + vo.getWriter() + "-" + vo.getContent() + "-" + vo.getSeq());
//			stmt.executeUpdate();
//			return 1;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}	
//	
//	public BoardVO getBoard(int seq) {
//		BoardVO one = new BoardVO();
//		System.out.println("===> JDBC로 getBoard() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_GET);
//			stmt.setInt(1, seq);
//			rs = stmt.executeQuery();
//			if(rs.next()) {
//				one.setSeq(rs.getInt("seq"));
//				one.setTitle(rs.getString("title"));
//				one.setWriter(rs.getString("writer"));
//				one.setContent(rs.getString("content"));
//				one.setCnt(rs.getInt("cnt"));
//			}
//			rs.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return one;
//	}
//	
//	public List<BoardVO> getBoardList(){
//		List<BoardVO> list = new ArrayList<BoardVO>();
//		System.out.println("===> JDBC로 getBoardList() 기능 처리");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_LIST);
//			rs = stmt.executeQuery();
//			while(rs.next()) {
//				BoardVO one = new BoardVO();
//				one.setSeq(rs.getInt("seq"));
//				one.setTitle(rs.getString("title"));
//				one.setWriter(rs.getString("writer"));
//				one.setContent(rs.getString("content"));
//				one.setRegdate(rs.getDate("regdate"));
//				one.setCnt(rs.getInt("cnt"));
//				list.add(one);
//			}
//			rs.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//		return list;
//	}
}
