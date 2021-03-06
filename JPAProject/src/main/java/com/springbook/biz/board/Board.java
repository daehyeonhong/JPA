package com.springbook.biz.board;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity /* VO에 @Entity Annotation 설정시 속성 중 @Id Annotation을 가진 속성이 존재해야 함 */
@Table(name = "UrBoard") /* Object, Relation(Table) Mapping */
@SequenceGenerator(name = "board_seq", /* SEQUENCE Generator 이름 */
sequenceName = "board_seq", /* DB_SEQUENCE_NAME */
initialValue = 1, /* 시작 값 */
allocationSize = 1) /* 메모리 할당 사이즈 */
public class Board implements Serializable {

	private static final long serialVersionUID = 1L;

	/* 속성 */
	@Id /* Key Annotation */
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq") /*
																					 * 자동생성
																					 * Annotation
																					 */
	private int seq;
	private String title, writer, content;
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();/* DB의 DateType Mapping Annotation */
	private int cnt;

	/* Constructor */
	public Board() {}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Board [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", cnt=" + cnt + "]";
	}

}
