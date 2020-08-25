package com.springbook.biz;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardVO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@XmlAttribute
	private int seq;
	private int cnt;
	private String title, writer, content;
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();/* 초기화 */

	@Transient
	@XmlTransient
	private MultipartFile uploadFile;
	@XmlTransient
	@Transient
	private String searchCondition;
	private String searchKeyword;
	private String images;

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

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "BoardVO [글 번호: " + seq + ", 글 제목: " + title + ", 작성자: " + writer + ", 내용: " + content + ", 등록 일: "
				+ regDate + ", 조회 수: " + cnt + "]";
	}

}