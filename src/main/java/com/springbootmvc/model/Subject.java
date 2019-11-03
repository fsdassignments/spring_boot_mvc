
package com.springbootmvc.model;
// Generated Aug 16, 2019 11:57:45 PM by Hibernate Tools 5.2.12.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject", catalog = "fsd_assignments")
public class Subject implements java.io.Serializable {

	private static final long serialVersionUID = -7670486138408587741L;
	private Integer subjectId;
	private String subTitle;
	private int duration;
	private Set<Book> books = new HashSet<>(0);

	public Subject() {
	}

	public Subject(String subTitle, int duration) {
		this.subTitle = subTitle;
		this.duration = duration;
	}

	public Subject(String subTitle, int duration, Set<Book> books) {
		this.subTitle = subTitle;
		this.duration = duration;
		this.books = books;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "subjectId", unique = true, nullable = false)
	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name = "subTitle", nullable = false, length = 250)
	public String getSubTitle() {
		return this.subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	@Column(name = "duration", nullable = false)
	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", cascade = CascadeType.ALL, targetEntity = Book.class)
	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		return Objects.hash(books, duration, subTitle, subjectId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return Objects.equals(books, other.books) && duration == other.duration
				&& Objects.equals(subTitle, other.subTitle) && Objects.equals(subjectId, other.subjectId);
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subTitle=" + subTitle + ", duration=" + duration + "]";
	}
}
