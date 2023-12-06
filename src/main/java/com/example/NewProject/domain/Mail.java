package com.example.NewProject.domain;

import com.example.NewProject.domain.enumeration.Language;
import com.example.NewProject.domain.enumeration.MailEvent;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sent_emails")
public class Mail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  @Size(max = 5000)
  private String content;

  @Column(name = "event")
  @Enumerated(EnumType.STRING)
  private MailEvent mailEvent;
  @Column(name = "language")
  @Enumerated(EnumType.STRING)
  private Language language;

  @Column(name = "create_time")
  private LocalDateTime createDatetime;

  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  public Long getId() {
    return id;
  }

  public Mail setId(Long id) {
    this.id = id;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public Mail setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public Mail setContent(String content) {
    this.content = content;
    return this;
  }

  public MailEvent getMailEvent() {
    return mailEvent;
  }

  public Mail setMailEvent(MailEvent mailEvent) {
    this.mailEvent = mailEvent;
    return this;
  }

  public Language getLanguage() {
    return language;
  }

  public Mail setLanguage(Language language) {
    this.language = language;
    return this;
  }

  public User getUser() {
    return user;
  }

  public Mail setUser(User user) {
    this.user = user;
    return this;
  }

  public LocalDateTime getCreateDatetime() {
    return createDatetime;
  }

  public Mail setCreateDatetime(LocalDateTime createDatetime) {
    this.createDatetime = createDatetime;
    return this;
  }

  public Mail() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Mail sentEmail = (Mail) o;
    return Objects.equals(id, sentEmail.id) && Objects.equals(title, sentEmail.title) && Objects.equals(content, sentEmail.content) && mailEvent == sentEmail.mailEvent && language == sentEmail.language && Objects.equals(createDatetime, sentEmail.createDatetime) && Objects.equals(user, sentEmail.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, content, mailEvent, language, createDatetime, user);
  }

  @Override
  public String toString() {
    return "SentEmail{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", content='" + content + '\'' +
      ", mailEvent=" + mailEvent +
      ", language=" + language +
      ", createDatetime=" + createDatetime +
      ", user=" + user +
      '}';
  }
}

